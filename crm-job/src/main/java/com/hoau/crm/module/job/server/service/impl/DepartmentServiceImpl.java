package com.hoau.crm.module.job.server.service.impl;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.job.server.dao.DepartmentMapper;
import com.hoau.crm.module.job.server.service.IDepartmentService;
import com.hoau.crm.module.job.server.wsdl.oa.OrgEmpService;
import com.hoau.crm.module.job.server.wsdl.oa.OrgEmpServicePortType;
import com.hoau.crm.module.job.shared.domain.OrgBean;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 部门service
 * 
 * @author: 何斌
 * @create: 2015年5月19日 下午5:24:50
 */

@org.springframework.stereotype.Service
public class DepartmentServiceImpl implements IDepartmentService{
	
	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(DepartmentServiceImpl.class);

	@Resource(name="departmentSynMapper")
	private DepartmentMapper departmentMapper;
	
	@Resource
	private ICustomerService customerService;
	
	//@Resource
	//private IEmployeeService employeeService;
	
	@Override
	@Transactional
	public void deptSynJob(String url, String synDay, String password) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		LOG.info("deptSynJob start : " + sDateFormat.format(new Date()) + ",synDay : " + synDay);
		//默认当天
		List<OrgBean> deptInfoList = queryDeptInfo(url,synDay,password);
		//查询用户信息
		if(null != deptInfoList && deptInfoList.size() > 0){
			for(OrgBean org : deptInfoList){
				OrgBean orgBean = departmentMapper.queryByDeptcode(org.getDeptcode());
				//判断该新增的部门代码：logistcode是否为门店  1-是,0-不是
				if(this.isStore(org.getLogistcode())){
					//是门店
					org.setIsStore("1");
				}else{
					//不是门店
					org.setIsStore("0");
				}
				//判断查询该部门是否已经存在
				if(null != orgBean){
					 //更新
					departmentMapper.update(org);
					// 新的部门负责人
					String newManager = org.getManagerid();
					String oldManager = orgBean.getManagerid();
					//如果新的部门负责人不为空， 老的部门负责人为空或者不等于新的部门负责人，则认为异动
					if(!StringUtil.isEmpty(newManager) && (StringUtil.isEmpty(oldManager) || !oldManager.equals(newManager)) && "1".equals(org.getIsStore()) ){
						// 先进行数据备份
						Map<String, String> map = new HashMap<String, String>();
						map.put("tierCode", org.getLogistcode());
						customerService.backupUpdateCustomerManagerId(map);
						// 再进行批量修改
						map.put("manageEmpCode", org.getManagerid());
						map.put("managePerson", org.getLastname());
						customerService.updateCustomerManagerId(map);
						// 新的部门负责人为空，老的负责人不为空，则将这部分的数据负责人置为上级
					}else if (StringUtil.isEmpty(newManager) && !StringUtil.isEmpty(oldManager) && "1".equals(org.getIsStore())){
						//负责人为空,找上级路区
						OrgBean parentOrgBean = this.getSupDeptCodeManager(org.getSupdeptcode());
						// 先进行数据备份
						Map<String, String> map = new HashMap<String, String>();
						map.put("tierCode", org.getLogistcode());
						customerService.backupUpdateCustomerManagerId(map);
						// 再进行批量修改
						map.put("manageEmpCode", parentOrgBean.getManagerid());
						map.put("managePerson", parentOrgBean.getLastname());
						customerService.updateCustomerManagerId(map);
					}
				}else{
					//新增组织信息
					departmentMapper.insert(org);
				}
			}
		}else{
			LOG.info("查询OA变更组织信息返回为空");
		}
		LOG.info("deptSynJob end : " + sDateFormat.format(new Date()));
	}


	/**
	 * 新增客户信息
	 * 
	 * @param org
	 * @param cEntity
	 * @author: 何斌
	 * @date: 2015年8月9日
	 * @update 
	 */
	/*private void addCustomerInfo(OrgBean org, CustomerEntity cEntity) {
		//清空主键
		cEntity.setId(null);
		//负责人
		cEntity.setManageEmpCode(org.getManagerid());
		cEntity.setManagePerson(org.getLastname());
		
		UserEntity currUser = new UserEntity();
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity.setEmpCode(org.getManagerid());
		EmployeeEntity eEntity = employeeService.queryEmployeeByEmpcode(employeeEntity);
		currUser.setUserName(eEntity.getAccount());
		currUser.setEmpEntity(eEntity);
		customerService.addCustomer(cEntity, currUser);
	}*/
	
	/**
	 * 递归获得上级部门负责人
	 * 
	 * @param supDeptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月9日
	 * @update 
	 */
	private OrgBean getSupDeptCodeManager(String supDeptCode){
		OrgBean supOrgBean = departmentMapper.queryByDeptcode(supDeptCode);
		if(supOrgBean != null && StringUtil.isEmpty(supOrgBean.getManagerid()) && !StringUtil.isEmpty(supOrgBean.getSupdeptcode())){
			return getSupDeptCodeManager(supOrgBean.getSupdeptcode());
		} else {
			return supOrgBean;
		}
	}
	
	/**
	 * 查询OA变更组织信息
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
	public List<OrgBean> queryDeptInfo(String url, String synDay, String password){
		List<OrgBean> deptInfoList = null;
		//判断相关参数是否为空
		try {
			OrgEmpService orgService = new OrgEmpService(new URL(url));
			OrgEmpServicePortType orgType = orgService.getOrgEmpServiceHttpPort();
			String deptInfoJson = orgType.getOrg(synDay, password);
			deptInfoList = JSON.parseArray(deptInfoJson, OrgBean.class);
			LOG.info("queryDeptInfo OrgBean Size : "
					+ deptInfoList.size());
		} catch (Exception e) {
			LOG.error("queryDeptInfo exception :" +e);
			e.printStackTrace();
		}
		return deptInfoList;
	}
	
	/**
	 * 判断是否为门店
	 * 
	 * @param logistcode
	 * @return
	 * @author: 何斌
	 * @date: 2015年6月3日
	 * @update 
	 */
	private boolean isStore(String logistcode){
		boolean isF = true;
		String regex="[\\u4e00-\\u9fa5]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(logistcode);
		//先判断部门代码是否为纯中文
		if(match.matches()){
			isF =  false;
		}else{
			//再判断是否平台(代码包含P或者T)
			if(logistcode.indexOf("P") != -1){
				isF = false;
			}
			if(logistcode.indexOf("T") != -1){
				isF = false;
			}
		}
		return isF;
	}
}
