/**
 * 
 */
package com.hoau.crm.module.appcore.server.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IChatsAppService;
import com.hoau.crm.module.appcore.api.server.service.ISignAppService;
import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.appcore.api.shared.exception.SignException;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.vo.ChatsAppVo;
import com.hoau.crm.module.appcore.server.dao.ChatsAppMapper;
import com.hoau.crm.module.appcore.server.dao.SignAppMapper;
import com.hoau.crm.module.bse.api.server.service.IEmployeeService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.EmployeeEntity;
import com.hoau.crm.module.bse.api.shared.domain.SignAccompanyEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.login.server.service.ILoginService;
import com.hoau.crm.module.sales.api.server.service.ISaleChatsService;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 洽谈 restful 接口实现
 * 
 * @author 丁勇
 * @date 2015年7月8日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class ChatsAppService implements IChatsAppService {
	// 注入相应的资源
	@Resource
	ChatsAppMapper chatsAppMapper;
	@Resource
	SignAppMapper signAppMapper;
	@Resource
	ILoginService iLoginService;
	@Resource
	ISaleChatsService ISaleChatsService;
	@Resource
	ISignAppService iSignAppService;
	@Resource
	IEmployeeService iEmployeeService;
	
	@Override
	public ResponseBaseEntity<ChatsAppVo> queryChatsById(ChatsAppVo chatsAppVo,String loginName) {
		//实体信息和id都不能为空
		if (StringUtils.isEmpty(chatsAppVo.getChatsEntity())
				|| StringUtils.isEmpty(chatsAppVo.getChatsEntity().getId())) {
			throw new SalesCommonException(SalesCommonException.ADD_CHATS_NULL);
		}
		chatsAppVo = chatsAppMapper.queryChatsById(chatsAppVo.getChatsEntity()
				.getId());
		ResponseBaseEntity<ChatsAppVo> result = new ResponseBaseEntity<ChatsAppVo>();
		result.setResult(chatsAppVo);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@Override
	@Transactional
	public ResponseBaseEntity<ChatsAppVo> merge(ChatsAppVo chatsAppVo,
			String loginName) {
		//实体信息不能为空
		if (StringUtils.isEmpty(chatsAppVo.getChatsEntity())) {
			throw new SalesCommonException(SalesCommonException.ADD_CHATS_NULL);
		}
		// 用户
		UserEntity user = iLoginService.getUserByLoginName(loginName);
		//app客户端录入
		chatsAppVo.getChatsEntity().setPlatform("Y");
		// 保存
		ISaleChatsService.saveOrUpdateSaleChat(chatsAppVo.getChatsEntity(),
				user);
		ResponseBaseEntity<ChatsAppVo> result = new ResponseBaseEntity<ChatsAppVo>();
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public ResponseBaseEntity<ChatsAppVo> addChartsAndSign(ChatsAppVo chatsAppVo,
			String loginName) {
		ResponseBaseEntity<ChatsAppVo> result = new ResponseBaseEntity<ChatsAppVo>();
		//实体信息不能为空
		if (chatsAppVo == null || chatsAppVo.getChatsEntity() == null || StringUtils.isEmpty(chatsAppVo.getChatsEntity().getChatType())) {
			throw new SalesCommonException(SalesCommonException.ADD_CHATS_NULL);
		}
		// 电话洽谈无需关联签到
		if(chatsAppVo.getChatsEntity().getChatType().equals(BseConstants.CHATSTYPE_PHONE)){
			// 用户
			UserEntity user = iLoginService.getUserByLoginName(loginName);
			//app客户端录入
			chatsAppVo.getChatsEntity().setPlatform("Y");
			// 保存
			ISaleChatsService.saveOrUpdateSaleChat(chatsAppVo.getChatsEntity(),
					user);
			
			// 成功返回
			result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
			// 上门拜访
		} else {
			if(chatsAppVo.getSignVo() == null || chatsAppVo.getSignVo().getSignEntity() == null){
				result.setErrorCode(AppUtil.EXCEPTION_STATUS_BUSINESS_ERROR);
				result.setErrorMessage(AppUtil.REQUEST_PARAMETERS_EXCEPTION);
				// 判断是否有签到ID
			} else if(StringUtils.isEmpty(chatsAppVo.getSignVo().getSignEntity().getId()) || chatsAppVo.getChatsEntity().getSign() == null || 
					StringUtils.isEmpty(chatsAppVo.getChatsEntity().getSign().getId())){
				throw new SalesCommonException(SalesCommonException.ADD_CHATS_SIGN_NULL);
			} else {
				//查询上传的签到信息
				SignEntity sign = iSignAppService.isHasThereSign(chatsAppVo.getSignVo().getSignEntity());
				if(sign!=null&&"Y".equals(sign.getIsRelationChat())){
					throw new SalesCommonException(SalesCommonException.ADD_CHATS_THERE_SIGN);
				}
				// 用户
				UserEntity user = iLoginService.getUserByLoginName(loginName);
				//app客户端录入
				chatsAppVo.getChatsEntity().setPlatform("Y");
				// 保存
				ISaleChatsService.saveOrUpdateSaleChat(chatsAppVo.getChatsEntity(),
						user);
				//没有上传的签到
				if(sign==null){
					// 初始化签到时间(为了历史回顾中先后顺序显示正确)
					if(chatsAppVo.getSignVo().getSignEntity().getCreateDate() == null 
							|| chatsAppVo.getSignVo().getSignEntity().getCreateDate().getTime() >= new Date().getTime()){
						// 当前时间
						Date currDate = new Date();
						// 获取昨天的年月
						Calendar cal = Calendar.getInstance();
						cal.setTime(currDate);
						cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
						chatsAppVo.getSignVo().getSignEntity().setCreateDate(cal.getTime());
					}
					// 新增签到
					ResponseBaseEntity r = iSignAppService.addSignInfo(chatsAppVo.getSignVo(), loginName);
					if(!r.getErrorCode().equals("0")){
						throw new SignException(SignException.SAVE_SIGN_FAILURE_NULL);
					}
					//保存签到扫描陪同人
					if(!StringUtils.isEmpty(chatsAppVo.getEmpCodeList())&&chatsAppVo.getEmpCodeList().size()>0){
						//工号集合
						List<Map<String,Object>> empCodeList = chatsAppVo.getEmpCodeList();
						//创建保存扫描人的集合
						List<SignAccompanyEntity> signAccompanyList = new ArrayList<SignAccompanyEntity>();
						//根据被扫描工号集合查询员工信息
						for (Map<String,Object> empCodelist: empCodeList) {
							//判断工号
							if(!StringUtils.isEmpty(empCodelist.get("empCode"))){
								EmployeeEntity emp = new EmployeeEntity();
								emp.setEmpCode(empCodelist.get("empCode").toString());
								emp = iEmployeeService.queryEmployeeByEmpcode(emp);
								if(emp!=null){
									//被扫描人信息
									SignAccompanyEntity signAccompanyEntity = new SignAccompanyEntity();
									signAccompanyEntity.setSignId(chatsAppVo.getSignVo().getSignEntity().getId());
									signAccompanyEntity.setScanAccompanyEmpCode(emp.getEmpCode());
									signAccompanyEntity.setScanAccompanyEmpName(emp.getEmpName());
									signAccompanyEntity.setScanAccompanyJobCode(emp.getJobcode());
									signAccompanyEntity.setScanAccompanyJobName(emp.getJobname());
									signAccompanyEntity.setScanAccompanyDeptCode(emp.getDeptcode());
									signAccompanyEntity.setScanAccompanyDeptName(emp.getDeptname());
									signAccompanyEntity.setCreateUser(loginName);
									signAccompanyList.add(signAccompanyEntity);
								}
							}
						}
						if(signAccompanyList.size()>0){
							//执行保存
							Map<String,Object> map = new HashMap<String,Object>();
							map.put("signAccompanyList", signAccompanyList);
							signAppMapper.addScanAccompany(map);
						}
					}
				}
				//修改俩种情况下的签到状态
				if (BseConstants.CHATSTYPE_VISIT.equals(chatsAppVo.getChatsEntity().getChatType()) && !StringUtils.isEmpty(chatsAppVo.getSignVo().getSignEntity().getId())) {
					signAppMapper.updateRelationChatStatus(chatsAppVo.getSignVo().getSignEntity().getId());
				}
				// 成功返回
				result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
			}
		}
		return result;
	}
}
