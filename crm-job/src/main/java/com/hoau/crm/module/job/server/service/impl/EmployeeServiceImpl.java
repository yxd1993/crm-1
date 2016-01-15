package com.hoau.crm.module.job.server.service.impl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.job.server.dao.EmployeeMapper;
import com.hoau.crm.module.job.server.service.IEmployeeService;
import com.hoau.crm.module.job.server.wsdl.oa.OrgEmpService;
import com.hoau.crm.module.job.server.wsdl.oa.OrgEmpServicePortType;
import com.hoau.crm.module.job.shared.domain.EmpBean;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 人员service
 * 
 * @author: 何斌
 * @create: 2015年5月20日 上午10:04:15
 */

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements IEmployeeService{

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Resource(name="employeeSynMapper")
	private EmployeeMapper employeeMapper;
	
	@Override
	@Transactional
	public void empSynJob(String url, String synDay, String password) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		LOG.info("empSynJob start : " + sDateFormat.format(new Date()) + ",synDay : " + synDay);
		//默认当天
		List<EmpBean> empInfoList = queryEmpInfo(url, synDay,password);
		//查询用户信息
		if(null != empInfoList && empInfoList.size() > 0){
			for(EmpBean emp : empInfoList){
				EmpBean empBean = employeeMapper.queryByEmpcode(emp.getWorkcode());
				Map<String, String> map = new HashMap<String, String>();
				map.put("workcode", emp.getWorkcode());
				map.put("account", emp.getAccount());
				//判断查询该人员是否已经存在
				if(null !=empBean){
					 //更新  5--离职
					if("5".equals(emp.getStatus())){
						map.put("active", "N");
					}else{
						map.put("active", "Y");
					}
					map.put("password", emp.getPassword());
					//更新用户表状态
					employeeMapper.updateUser(map);
					//更新人员表状态
					employeeMapper.update(emp);
					//角色自动分配(门店经理,路区经理,客户经理)
					//岗位对应角色编码
					String roleCode = BseConstants.convertJobCode(emp.getJobname());
					if(!StringUtil.isEmpty(roleCode)){
						map.put("roleCode", roleCode);
						String currentRoleCode = employeeMapper.isExistRole(map);
						if(StringUtil.isEmpty(currentRoleCode)){
							employeeMapper.autoInsertRole(map);
						}else{
							if(!roleCode.equals(currentRoleCode)){
								//先删除原来的角色,新增
								employeeMapper.deleteRole(map);
								employeeMapper.autoInsertRole(map);
							}
						}
					}
				}else{
					//人员表新增
					employeeMapper.insert(emp);
					//用户表新增
					employeeMapper.insertUser(emp);
					//角色自动分配(门店经理,路区经理,客户经理)
					//岗位对应角色编码
					String roleCode = BseConstants.convertJobCode(emp.getJobname());
					if(!StringUtil.isEmpty(roleCode)){
						map.put("roleCode", roleCode);
						employeeMapper.autoInsertRole(map);
					}
				}
			}
		}else{
			LOG.info("查询OA变更人员信息返回为空");
		}
		LOG.info("empSynJob end : " + sDateFormat.format(new Date()));
	}
	

	/**
	 * 查询OA变更人员信息
	 * 
	 * @param url
	 * @param soapAction
	 * @param methName
	 * @param synDay
	 * @param password
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月20日
	 * @update 
	 */
	public List<EmpBean> queryEmpInfo(String url, String synDay, String password){
		List<EmpBean> empInfoList = null;
		//判断相关参数是否为空
		try {
			OrgEmpService orgService = new OrgEmpService(new URL(url));
			OrgEmpServicePortType orgType = orgService.getOrgEmpServiceHttpPort();
			String empInfoJson = orgType.getEmp(synDay, password);
			empInfoList = JSON.parseArray(empInfoJson, EmpBean.class);
			LOG.info("queryEmpInfo EmpBean Size : "
					+ empInfoList.size());
		} catch (Exception e) {
			LOG.error("queryEmpInfo exception :" +e);
			e.printStackTrace();
		}
		return empInfoList;
	}

	public static void main(String[] args) {
		EmployeeServiceImpl service = new EmployeeServiceImpl();
		service.empSynJob(null, null, null);
	}
}