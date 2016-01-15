package com.hoau.crm.module.sales.server.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.EmployeeMapper;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.sales.api.server.service.ISaleReserveService;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.crm.module.sales.server.dao.SaleReserveMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 
 * @author 丁勇
 * @date 2015年6月9日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SaleReserveServiceImpl implements ISaleReserveService {
	@Resource
	SaleReserveMapper reserveMapper;
	@Resource
	IReviewHistoryService ireviewhistoty;
	@Resource
	IMessageInfoService iMessageInfoService;
	@Resource
	EmployeeMapper  employeeMapper;
	@Resource
	ICustomerService iCustomerService;
	//查询条件
	Map<String, Object> map;
	//格式化时间
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m");
	@Override
	public List<SaleReserveEntity> getReserveByPaging(SaleReserveVo reserveVo,
			RowBounds rb) {
		//查询参数封装
		map = new HashMap<String, Object>();
		
		if (rb == null) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		if (!StringUtils.isEmpty(reserveVo)) {
			if (!StringUtils.isEmpty(reserveVo.getReserveEntity())) {
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getCustomerEntity())) {
					// 获取客户对象
					CustomerEntity customerEntity = reserveVo.getReserveEntity().getCustomerEntity();
					// 客户企业全称
					if (!StringUtil.isEmpty(customerEntity.getEnterpriseName())) {
						map.put("enterpriseName","%" + customerEntity.getEnterpriseName() + "%");
					}
				}
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getContactEntity())) {
					// 获取联系人对象
					ContactEntity contactEntity = reserveVo.getReserveEntity().getContactEntity();
					// 联系人名称
					if (!StringUtils.isEmpty(contactEntity.getContactName())) {
						map.put("contactName","%" + contactEntity.getContactName() + "%");
					}
				}
				
				// 预约开始时间
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveStartTime())) {
					map.put("reserveStartTime", sdf.format(reserveVo.getReserveEntity().getReserveStartTime()));
				}
				// 预约结束时间
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveEndTime())) {
					map.put("reserveEndTime", sdf.format(BseConstants.getEndDate(reserveVo.getReserveEntity().getReserveEndTime())));
				}
				//预约类型
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveType())) {
					map.put("reserveType", reserveVo.getReserveEntity().getReserveType());
				}
				//是否已赴约
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getIsAgreement())) {
					map.put("isAgreement", reserveVo.getReserveEntity().getIsAgreement());
				}
				//创建人
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getCreateUser())) {
					map.put("empName", "%" +reserveVo.getReserveEntity().getCreateUser()+"%");
				}
				// 当前用户
				UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
				Set<String> functionCodes = currentUser.getFunctionCodes();
				if (!functionCodes.contains(BseConstants.ALLDATA_RESERVE)) {
					if (currentUser != null	&& !StringUtil.isEmpty(currentUser.getEmpEntity().getEmpCode())) {
						map.put("userCode", currentUser.getEmpEntity().getEmpCode());
					}
				}
			}
		}
		return reserveMapper.getReserveByPaging(map, rb);
	}

	@Override
	public long reserveCount(SaleReserveVo reserveVo) {
		map = new HashMap<String, Object>();
		// 查询条件
		if (!StringUtils.isEmpty(reserveVo)) {
			if (!StringUtils.isEmpty(reserveVo.getReserveEntity())) {
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getCustomerEntity())) {
					// 获取客户对象
					CustomerEntity customerEntity = reserveVo.getReserveEntity().getCustomerEntity();
					// 客户企业全称
					if (!StringUtil.isEmpty(customerEntity.getEnterpriseName())) {
						map.put("enterpriseName","%" + customerEntity.getEnterpriseName() + "%");
					}
				}
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getContactEntity())) {
					// 获取联系人对象
					ContactEntity contactEntity = reserveVo.getReserveEntity().getContactEntity();
					// 联系人名称
					if (!StringUtils.isEmpty(contactEntity.getContactName())) {
						map.put("contactName","%" + contactEntity.getContactName() + "%");
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
				// 预约开始时间
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveStartTime())) {
					map.put("reserveStartTime", sdf.format(reserveVo.getReserveEntity().getReserveStartTime()));
				}
				// 预约结束时间
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveEndTime())) {
					map.put("reserveEndTime", sdf.format(BseConstants.getEndDate(reserveVo.getReserveEntity().getReserveEndTime())));
				}
				//预约类型
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getReserveType())) {
					map.put("reserveType", reserveVo.getReserveEntity().getReserveType());
				}
				//是否已赴约
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getIsAgreement())) {
					map.put("isAgreement", reserveVo.getReserveEntity().getIsAgreement());
				}
				//创建人
				if (!StringUtils.isEmpty(reserveVo.getReserveEntity().getCreateUser())) {
					map.put("empName", "%" +reserveVo.getReserveEntity().getCreateUser()+"%");
				}
				// 当前用户
				UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
				Set<String> functionCodes = currentUser.getFunctionCodes();
				if (!functionCodes.contains(BseConstants.ALLDATA_RESERVE)) {
					if (currentUser != null  && !StringUtil.isEmpty(currentUser.getEmpEntity().getEmpCode())) {
						map.put("userCode", currentUser.getEmpEntity().getEmpCode());
					}
				}
			}
		}
		return reserveMapper.reserveCount(map);
	}

	@Override
	@Transactional
	public void saveOrUpdateReservePlan(SaleReserveEntity reserveEntity,
			UserEntity currUser) {
		// 预约信息是否为空
		if (StringUtils.isEmpty(reserveEntity)) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 时间参数不能为空
		if (StringUtils.isEmpty(reserveEntity.getReserveStartTime())
				|| StringUtils.isEmpty(reserveEntity.getReserveEndTime())) {
			throw new SalesCommonException(
					SalesCommonException.ADD_DATETIME_ISNULL);
		}
		// 时间参数不符合
		if (reserveEntity.getReserveStartTime().getTime() >= reserveEntity
				.getReserveEndTime().getTime()) {
			throw new SalesCommonException(
					SalesCommonException.ADD_DATETIME_INVALID);
		}
		// 预约时间不能小于当前时间
		if (reserveEntity.getReserveStartTime().getTime() <= new Date()
				.getTime()) {
			throw new SalesCommonException(
					SalesCommonException.ADD_STARTTIME_INVALID);
		}
		//客户是否是空
		if(StringUtils.isEmpty(reserveEntity.getCustomerEntity())
			||StringUtils.isEmpty(reserveEntity.getCustomerEntity().getId())){
			throw new CustomerException(CustomerException.ADD_CUSTOMER_ID_NULL);
		}
		// 保存预约消息提醒
		StringBuffer sb = new StringBuffer();
		//获取选中的客户
		CustomerEntity customerEntity  = iCustomerService.queryCustomerInfoById(reserveEntity.getCustomerEntity());
		// 判断是新增还是修改
		if (StringUtil.isEmpty(reserveEntity.getId())) {
			// 主键赋值
			String uuid = UUIDUtil.getUUID();
			reserveEntity.setId(uuid);
			reserveEntity.setCreateUser(currUser.getEmpEntity().getEmpCode());
			// 执行保存
			reserveMapper.addCusReservePlan(reserveEntity);
			// 保存新增记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, reserveEntity, null, null,
					BseConstants.OPER_CREATE_RESERVE, currUser.getEmpEntity()
							.getEmpCode()));
			MessageInfoEntity messageEntity = new MessageInfoEntity();
			// 判断是否有陪同
			if (!StringUtils.isEmpty(reserveEntity.getComTierEmpCode())
					|| !StringUtils.isEmpty(reserveEntity.getComRoadEmpCode())
					|| !StringUtils.isEmpty(reserveEntity
							.getComRegionsEmpCode())
					|| !StringUtils.isEmpty(reserveEntity
							.getComBusinessEmpCode())
					|| !StringUtils.isEmpty(reserveEntity.getComOdEmpCode())
					|| !StringUtils.isEmpty(reserveEntity.getComCeoEmpCode())
					||!StringUtils.isEmpty(reserveEntity.getTeamManagerEmpCode())
					||!StringUtils.isEmpty(reserveEntity.getSaleManEmpCode())) {

				// 获取陪同人员
				List<String> empcodelist = new ArrayList<String>();
				//门店
				if (!StringUtils.isEmpty(reserveEntity.getComTierEmpCode())) {
					empcodelist.add(reserveEntity.getComTierEmpCode());
				}
				//路区
				if (!StringUtils.isEmpty(reserveEntity.getComRoadEmpCode())) {
					empcodelist.add(reserveEntity.getComRoadEmpCode());
				}
				//大区
				if (!StringUtils.isEmpty(reserveEntity.getComRegionsEmpCode())) {
					empcodelist.add(reserveEntity.getComRegionsEmpCode());
				}
				//事业部
				if (!StringUtils.isEmpty(reserveEntity.getComBusinessEmpCode())) {
					empcodelist.add(reserveEntity.getComBusinessEmpCode());
				}
				//副总
				if (!StringUtils.isEmpty(reserveEntity.getComOdEmpCode())) {
					empcodelist.add(reserveEntity.getComOdEmpCode());
				}
				//总裁
				if (!StringUtils.isEmpty(reserveEntity.getComCeoEmpCode())) {
					empcodelist.add(reserveEntity.getComCeoEmpCode());
				}
				//团队经理
				if (!StringUtils.isEmpty(reserveEntity.getTeamManagerEmpCode())) {
					empcodelist.add(reserveEntity.getTeamManagerEmpCode());
				}
				//客户经理
				if (!StringUtils.isEmpty(reserveEntity.getSaleManEmpCode())) {
					empcodelist.add(reserveEntity.getSaleManEmpCode());
				}
				//预约消息陪同人员推送添加公共方法
				addCommonEmpByReserve(empcodelist, uuid, currUser,
						reserveEntity, customerEntity);
			}
						
			//保存当前用户预约推送信息
			//消息类型
			messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_ZSBFL);
			//接收人
			messageEntity.setReceiveUserId(currUser.getEmpEntity().getEmpCode());
			//预约id
			messageEntity.setCondition(uuid);
			messageEntity.setMessageContent(addMessageContent(reserveEntity, sb, messageEntity, customerEntity));
			iMessageInfoService.addMessage(messageEntity,currUser);
		} else {
			map = new HashMap<String,Object>();
			reserveEntity.setModifyUser(currUser.getEmpEntity().getEmpCode());
			map.put("id", reserveEntity.getId());
			Set<String> functions = currUser.getFunctionCodes();
			if(!functions.contains(BseConstants.ALLDATA_RESERVE)){
				map.put("userCode", currUser.getEmpEntity().getEmpCode());
			}
			//查询当前选择的预约信息
			SaleReserveEntity reserve = reserveMapper.getReserveDetailByIdUseUpdate(map);
			// 判断原来的数据是否已经删除
			if (StringUtils.isEmpty(reserve)||StringUtil.isEmpty(reserve.getActive())
					||reserve.getActive().equals(BseConstants.INACTIVE)) {
				throw new SalesCommonException(
						SalesCommonException.UPDATE_IS_DELETE);
			}
			// 赴约状态
			reserveEntity.setIsAgreement(reserve.getIsAgreement());
			// 执行修改
			reserveMapper.updateReserveplan(reserveEntity);
			// 保存修改记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, reserveEntity, null, null,
					BseConstants.OPER_UPDATE_RESERVE, currUser.getEmpEntity()
							.getEmpCode()));
			//陪同人员集合
			List<String> commonEmpList = new ArrayList<String>();
			//修改时间
			if(reserve.getReserveStartTime()!=reserveEntity.getReserveStartTime()
					||reserve.getReserveEndTime()!=reserveEntity.getReserveEndTime()||reserve.getWarningTime()!=reserveEntity.getWarningTime()){
				MessageInfoEntity messageEntity = new MessageInfoEntity();
				messageEntity.setCondition(reserve.getId());
				messageEntity.setReceiveUserId(currUser.getEmpEntity().getEmpCode());
				messageEntity.setMessageContent(addMessageContent(reserveEntity, sb, messageEntity, customerEntity));
				iMessageInfoService.updateMessageByCondition(messageEntity, currUser);
			}
			//修改了不一样陪同人员 (门店) 
			if(!StringUtils.isEmpty(reserve.getComTierEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComTierEmpCode())){
				//勾选没有的值,
				if(!reserve.getComTierEmpCode().equals(reserveEntity.getComTierEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComTierEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getComTierEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComTierEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComTierEmpCode());
				}
			}
			//修改了不一样陪同人员 (路区)
			if(!StringUtils.isEmpty(reserve.getComRoadEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComRoadEmpCode())){
				if(!reserve.getComRoadEmpCode().equals(reserveEntity.getComRoadEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComRoadEmpCode());
				}
			//修改时添加了陪同人员 
			}else if(StringUtils.isEmpty(reserve.getComRoadEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComRoadEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComRoadEmpCode());
				}
			}
			//修改了不一样陪同人员(大区)
			if(!StringUtils.isEmpty(reserve.getComRegionsEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComRegionsEmpCode())){
				if(!reserve.getComRegionsEmpCode().equals(reserveEntity.getComRegionsEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComRegionsEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getComRegionsEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComRegionsEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComRegionsEmpCode());
				}
			}
			//修改了不一样陪同人员(事业部)
			if(!StringUtils.isEmpty(reserve.getComBusinessEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComBusinessEmpCode())){
				if(!reserve.getComBusinessEmpCode().equals(reserveEntity.getComBusinessEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComBusinessEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getComBusinessEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComBusinessEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComBusinessEmpCode());
				}
			}
			//修改了不一样陪同人员(副总)
			if(!StringUtils.isEmpty(reserve.getComOdEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComOdEmpCode())){
				if(!reserve.getComOdEmpCode().equals(reserveEntity.getComOdEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComOdEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getComOdEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComOdEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComOdEmpCode());
				}
			}
			//修改了不一样陪同人员(总裁)
			if(!StringUtils.isEmpty(reserve.getComCeoEmpCode())&&!StringUtils.isEmpty(reserveEntity.getComCeoEmpCode())){
				if(!reserve.getComCeoEmpCode().equals(reserveEntity.getComCeoEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getComCeoEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getComCeoEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getComCeoEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getComCeoEmpCode());
				}
			}
			//修改了不一样陪同人员(团队经理)
			if(!StringUtils.isEmpty(reserve.getTeamManagerEmpCode())&&!StringUtils.isEmpty(reserveEntity.getTeamManagerEmpCode())){
				if(!reserve.getTeamManagerEmpCode().equals(reserveEntity.getTeamManagerEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getTeamManagerEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getTeamManagerEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getTeamManagerEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getTeamManagerEmpCode());
				}
			}
			//修改了不一样陪同人员(客户经理)
			if(!StringUtils.isEmpty(reserve.getSaleManEmpCode())&&!StringUtils.isEmpty(reserveEntity.getSaleManEmpCode())){
				if(!reserve.getSaleManEmpCode().equals(reserveEntity.getSaleManEmpCode())){
					//添加新的推送消息的员工
					commonEmpList.add(reserveEntity.getSaleManEmpCode());
				}
			//修改时添加了陪同人员
			}else if(StringUtils.isEmpty(reserve.getSaleManEmpCode())){
				if(!StringUtils.isEmpty(reserveEntity.getSaleManEmpCode())){
					//添加需要推送消息的员工
					commonEmpList.add(reserveEntity.getSaleManEmpCode());
				}
			}
			addCommonEmpByReserve(commonEmpList, reserveEntity.getId(), currUser, reserveEntity, customerEntity);
		}
	}

	@Override
	public SaleReserveEntity getReserveDetailByIdUseUpdate(SaleReserveEntity reserveplan,UserEntity currUser) {
		map  = new HashMap<String,Object>();
		map.put("id", reserveplan.getId());
		Set<String> functions = currUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_RESERVE)){
			map.put("userCode", currUser.getEmpEntity().getEmpCode());
		}
		return reserveMapper.getReserveDetailByIdUseUpdate(map);
	}

	@Override
	public SaleReserveVo getReserveDetailById(SaleReserveEntity reserveEntity) {
		return reserveMapper.getReserveDetailById(reserveEntity.getId());
	}

	@Override
	public void delete(List<String> ids, String delDesc, UserEntity currUser) {
		if (ids.size() <= 0 || ids == null) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 删除的必须填写原因
		if (StringUtils.isEmpty(delDesc)) {
			throw new SalesCommonException(SalesCommonException.DEL_DESC_NULL);
		}
		map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("username", currUser.getEmpEntity().getEmpCode());
		map.put("delDesc", delDesc);
		reserveMapper.deleteReserve(map);
		for (int i = 0; i < ids.size(); i++) {
			map.put("id", ids.get(i));
			Set<String> functions = currUser.getFunctionCodes();
			if(!functions.contains(BseConstants.ALLDATA_RESERVE)){
				map.put("userCode", currUser.getEmpEntity().getEmpCode());
			}
			SaleReserveEntity reserveEntity = reserveMapper.getReserveDetailByIdUseUpdate(map);
			// 保存删除记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, reserveEntity, null, null,
					BseConstants.OPER_DELETE_RESERVE, currUser.getEmpEntity()
							.getEmpCode()));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hoau.crm.module.sales.api.server.service.ISaleReserveService#
	 * queryNotReserve(com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo)
	 */
	@Override
	public List<SaleReserveVo> queryNotReserve(SaleReserveVo saleReserveVo,
			UserEntity currUser) {
		map = new HashMap<String, Object>();
		// 用户信息
		// UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		//当前用户拥有的功能信息ID集合
		Set<String> functions = currUser.getFunctionCodes();
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERMANAGER)){
			map.put("createUser", currUser.getEmpEntity().getEmpCode());
		}
		map.put("accountId", saleReserveVo.getAccountId());
		
		return reserveMapper.queryNotReserve(map);
	}
	
	/**
	 * 预约消息陪同人员推送添加公共方法
	 * @param commonEmpList
	 * @param id
	 * @param currUser
	 * @param reserveEntity
	 * @param customerEntity
	 * @author 丁勇
	 * @date 2015年7月31日
	 * @update 
	 */
	public void addCommonEmpByReserve(List<String> commonEmpList,String id,UserEntity currUser,
			SaleReserveEntity reserveEntity,CustomerEntity customerEntity){
		if(commonEmpList.size()>0){
			//新增消息内容
			for (String empCode : commonEmpList) {
				StringBuffer sbs = new StringBuffer();
				MessageInfoEntity addCommonEmpMessage = new MessageInfoEntity();
				addCommonEmpMessage.setMessageType(BseConstants.MESSAGE_TYPE_XTBFL);
				addCommonEmpMessage.setReceiveUserId(empCode);
				//预约id
				addCommonEmpMessage.setCondition(id);
				sbs.append("您的同事:"+currUser.getUserName()+currUser.getEmpEntity().getEmpName());
				sbs.append(",邀请您"+sdf.format(reserveEntity.getReserveStartTime()));
				sbs.append("到"+customerEntity.getDetailedAddress());
				sbs.append("拜访"+customerEntity.getEnterpriseName());
				sbs.append(",您可以到拜访列表进行查看");
				//陪同人员拜访信息
				addCommonEmpMessage.setMessageContent(sbs.toString());
				iMessageInfoService.addMessage(addCommonEmpMessage,currUser);
			}
		}
	}
	/**
	 * 预约推送消息录入者公共添加方法
	 * @param reserveEntity
	 * @param sb
	 * @param messageEntity
	 * @param customerEntity
	 * @return
	 * @author 丁勇
	 * @date 2015年7月31日
	 * @update 
	 */
	public String addMessageContent(SaleReserveEntity reserveEntity,StringBuffer sb,MessageInfoEntity messageEntity,
			CustomerEntity customerEntity){
		//获取提醒时间
		long sendtime = reserveEntity.getReserveStartTime().getTime()-
				(Integer.valueOf(reserveEntity.getWarningTime())*60*1000);
		try {
			sb.append("您计划在"+reserveEntity.getWarningTime()+"分钟后,");
			messageEntity.setAllowSendTime(sdf.parse(sdf.format(sendtime)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(BseConstants.CHATSTYPE_PHONE.equals(reserveEntity.getReserveType())){
			sb.append("电话拜访");
		}else if(BseConstants.CHATSTYPE_VISIT.equals(reserveEntity.getReserveType())){
			sb.append("实地拜访");
		}
		sb.append(customerEntity.getEnterpriseName()+"客户,请知晓。");
		return sb.toString();
	}
}
