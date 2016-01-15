package com.hoau.crm.module.job.server.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hoau.crm.module.customer.api.shared.domain.ObhUserEntity;
import com.hoau.crm.module.customer.server.service.impl.CustomerSyncService;
import com.hoau.crm.module.job.server.wsdl.oa.ObjectFactory;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerNatureConvertEntity;
import com.hoau.crm.module.job.server.dao.StatusChangeMapper;
import com.hoau.crm.module.job.server.service.IStatusChangeService;
import com.hoau.crm.module.sales.api.server.service.IReportEmpWorkService;
import com.hoau.crm.module.util.UUIDUtil;

/**
 * 状态装换SERVICE实现
 * 
 * @author: 何斌
 * @create: 2015年7月13日 上午10:33:48
 */
@org.springframework.stereotype.Service
public class StatusChangeServiceImpl implements IStatusChangeService {
	/**
	 * 日志
	 */
	private static  Logger LOG = LoggerFactory.getLogger(StatusChangeServiceImpl.class);
	
	@Resource
	private StatusChangeMapper statusChangeMapper;
	
	@Resource
	private IMessageInfoService iMessageInfoService;
	
	@Resource
	private IReportEmpWorkService iReportEmpWorkService;
	
	@Resource
	private ICustomerService iCustomerService;

    @Resource
    private CustomerSyncService customerSyncService;

	@Override
	@Transactional
	public void statusChangeOfContract() {
		try {
			//刷新已过期合同状态
			statusChangeMapper.StatusChangeOfContract();
			//客户三个月有发货：发货客户,无为:流失客户
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar ca3 = Calendar.getInstance();
			ca3.setTime(new Date());
			ca3.set(Calendar.MONTH, ca3.get(Calendar.MONTH) - 3);
			String stopTime = sDateFormat1.format(ca3.getTime());
			// 参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("stopTime", stopTime);
			//记录客户性质转换
			List<Map<String,Object>> customerNatureSList = statusChangeMapper.getCustomerStatusIsOutOfTimeContract(map);
			for(Map<String,Object> cMap :customerNatureSList){
				String id = (String) cMap.get("id");
				String accountType = (String) cMap.get("accountType");
				Date lastShipmentsTime = (Date) cMap.get("lastShipmentsTime");
				CustomerNatureConvertEntity cNatureConvertEntity = new CustomerNatureConvertEntity();
				//主键
				cNatureConvertEntity.setId(UUIDUtil.getUUID());
				//客户id
				cNatureConvertEntity.setAccountId(id);
				//原状态
				cNatureConvertEntity.setOldStatus(accountType);
				//新状态
				if(lastShipmentsTime == null || lastShipmentsTime.getTime() < sDateFormat.parse(stopTime).getTime()){
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_RUNOFF);
				}else{
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_DELIVER);
				}
				//转换人
				cNatureConvertEntity.setConvertUser(BseConstants.SYSADMIN);
				iCustomerService.addCustomerConvertRecord(cNatureConvertEntity);
			}
			//更新客户的状态：已过期合同,签约变发货或流失
			statusChangeMapper.refreshCustomerStatusOutOfTimeContract(map);
            // 数据同步到DC
            for(Map<String,Object> customerMap :customerNatureSList){
                String id = (String) customerMap.get("id");
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(id);
                CustomerEntity newCustomerEntity = iCustomerService.queryCustomerInfoById(customerEntity);
                if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                    customerSyncService.updateDcAccount(newCustomerEntity);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("合同过期状态更新失败！");
		}
	}

	@Override
	@Transactional
	public void statusChangeOfCustomerToSign() {
		try {
			Map<String,String> map = new HashMap<String, String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();  
			c.setTime(new Date());  
			map.put("takeEffectTime", sdf.format(c.getTime()));
			//记录客户性质转换
			List<Map<String,String>> customerNatureSList = statusChangeMapper.getCustomerStatusIsSign(map);
			for(Map<String,String> cMap :customerNatureSList){
				String id = cMap.get("id");
				String accountType =cMap.get("accountType");
				CustomerNatureConvertEntity cNatureConvertEntity = new CustomerNatureConvertEntity();
				//主键
				cNatureConvertEntity.setId(UUIDUtil.getUUID());
				//客户id
				cNatureConvertEntity.setAccountId(id);
				//原状态
				cNatureConvertEntity.setOldStatus(accountType);
				//新状态
				cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_SIGN);
				//转换人
				cNatureConvertEntity.setConvertUser(BseConstants.SYSADMIN);
				iCustomerService.addCustomerConvertRecord(cNatureConvertEntity);
			}
			// 合同归档,更新客户性质为签约客户
			statusChangeMapper.statusChangeOfCustomerToSign(map);
            // 数据同步到DC
            for(Map<String,String> customerMap :customerNatureSList){
                String id = customerMap.get("id");
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(id);
                CustomerEntity newCustomerEntity = iCustomerService.queryCustomerInfoById(customerEntity);
                if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                    customerSyncService.updateDcAccount(newCustomerEntity);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("客户状态更新失败！");
		}
	}

	@Override
	@Transactional
	public void refreshCustomerStatus() {
		try {
			// 更新客户的状态 签约、发货
			// 获取3月前的今天0点
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd 00:00:00");
			LOG.info("refreshCustomerStatus start : " + sDateFormat.format(new Date()));
			Calendar ca3 = Calendar.getInstance();
			ca3.setTime(new Date());
			ca3.set(Calendar.MONTH, ca3.get(Calendar.MONTH) - 3);
			String stopTime = sDateFormat1.format(ca3.getTime());
			// 参数
			Map<String, String> map = new HashMap<String, String>();
			map.put("stopTime", stopTime);
			// 查询需要改变状态的客户信息
			List<Map<String, String>> list = statusChangeMapper.selectCustomerStatusIsLoss(map);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 9);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MINUTE, 0);
			Date allowSendDate = cal.getTime();
			List<CustomerNatureConvertEntity> cNatureConvertEntities = new ArrayList<CustomerNatureConvertEntity>();
			for(int i=0; i<list.size(); i++){
				Map<String, String> customer = list.get(i);
				CustomerNatureConvertEntity cNatureConvertEntity = new CustomerNatureConvertEntity();
				String id = customer.get("id");
				String enterpriseName = customer.get("enterpriseName");
				String manageEmpCode = customer.get("manageEmpCode");
				String dCaccount = customer.get("dCaccount");
				String accountType = customer.get("accountType");
				// 新增消息
				MessageInfoEntity messageEntity = new MessageInfoEntity();
				messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_KHLSL);
				messageEntity.setReceiveUserId(manageEmpCode);
				messageEntity.setCondition(dCaccount);
				messageEntity.setAllowSendTime(allowSendDate);
				messageEntity.setMessageContent("您的客户：" + enterpriseName
						+ "，由于已累计三个月没在我司发货，系统已将此客户转为流失客户，请及时跟进。");
				iMessageInfoService.addMessage(messageEntity, null);
				//记录客户性质装换
				//客户id
				cNatureConvertEntity.setAccountId(id);
				//原状态
				cNatureConvertEntity.setOldStatus(accountType);
				cNatureConvertEntities.add(cNatureConvertEntity);
			}
			// 更新客户的状态：签约、发货置为流失
			statusChangeMapper.refreshCustomerStatus(map);
			// 更新客户性质转换
			for(CustomerNatureConvertEntity c: cNatureConvertEntities){
				//主键
				c.setId(UUIDUtil.getUUID());
				//新状态
				c.setNewStatus(BseConstants.CUSTOMER_NATURE_RUNOFF);
				//转换人
				c.setConvertUser(BseConstants.SYSADMIN);
				iCustomerService.addCustomerConvertRecord(c);
			}
            //数据同步DC
            for (int i = 0; i < list.size(); i++) {
                Map<String,String> customer = list.get(i);
                String id = customer.get("id");
                CustomerEntity customerEntity  = new CustomerEntity();
                customerEntity.setId(id);
                CustomerEntity newCustomerEntity = iCustomerService.queryCustomerInfoById(customerEntity);
                if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                    customerSyncService.updateDcAccount(newCustomerEntity);
                }
            }
            LOG.info("refreshCustomerStatus end : " + sDateFormat.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("更新客户的状态：签约、发货置为流失更新失败！");
		}
	}
	
	@Override
	@Transactional
	public void refreshCustomerWayBillTime() {
		try {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd 00:00:00");
			LOG.info("refreshCustomerWayBillTime start : " + sDateFormat.format(new Date()));
			// 只更新当天的运单数据到客户表
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1); 
			Map<String, String> map = new HashMap<String, String>();
			map.put("startTime", sDateFormat1.format(cal.getTime()));
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			map.put("endTime", sDateFormat1.format(cal.getTime()));
			// 查询客户的状态：意向、流失置为发货
			List<Map<String,Object>> customerStatusIsDelivers = statusChangeMapper.getCustomerStatusIsDeliJver(map);
			//记录客户性质转换
			for(Map<String,Object> cMap : customerStatusIsDelivers){
				String id = (String) cMap.get("id");
				String accountType = (String) cMap.get("accountType");
				Date contractStartTime = (Date) cMap.get("contractStartTime");
				Date contractEndTime = (Date) cMap.get("contractEndTime");
				CustomerNatureConvertEntity cNatureConvertEntity = new CustomerNatureConvertEntity();
				//主键
				cNatureConvertEntity.setId(UUIDUtil.getUUID());
				//客户Id
				cNatureConvertEntity.setAccountId(id);
				//原状态
				cNatureConvertEntity.setOldStatus(accountType);
				//新状态
				if(BseConstants.CUSTOMER_NATURE_INTENTION.equals(accountType)){
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_DELIVER);
				}else if(BseConstants.CUSTOMER_NATURE_RUNOFF.equals(accountType) && contractStartTime == null){
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_DELIVER);
				}else if(BseConstants.CUSTOMER_NATURE_RUNOFF.equals(accountType) && contractStartTime != null
						&& (contractStartTime.getTime() > cal.getTime().getTime()
								|| contractEndTime.getTime() < cal.getTime().getTime() )){
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_DELIVER);
				}else if(BseConstants.CUSTOMER_NATURE_RUNOFF.equals(accountType) && contractStartTime != null
						&& (contractStartTime.getTime() <= cal.getTime().getTime()
								|| contractEndTime.getTime() >= cal.getTime().getTime() )){
					cNatureConvertEntity.setNewStatus(BseConstants.CUSTOMER_NATURE_SIGN);
				}
				//转换人
				cNatureConvertEntity.setConvertUser(BseConstants.SYSADMIN);
				iCustomerService.addCustomerConvertRecord(cNatureConvertEntity);
			}
			// 更新客户的第1单、最后1单发货以及根据发货改变客户状态
			statusChangeMapper.refreshCustomerWayBillTime(map);
            // 数据同步DC
            for(Map<String,Object> customerMap : customerStatusIsDelivers){
                String id = (String) customerMap.get("id");
                CustomerEntity customerEntity = new CustomerEntity();
                customerEntity.setId(id);
                CustomerEntity newCustomerEntity = iCustomerService.queryCustomerInfoById(customerEntity);
                if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                    customerSyncService.updateDcAccount(newCustomerEntity);
                }
            }
			LOG.info("refreshCustomerWayBillTime end : " + sDateFormat.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("更新客户的第1单、最后1单发货以及根据发货改变客户状态失败！");
		}
	}
	
	@Override
	@Transactional
	public void refreshCustomerWayBillLog() {
		try {
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd 00:00:00");
			LOG.info("refreshCustomerWayBillLog start : " + sDateFormat.format(new Date()));
			// 只更新当天的运单数据到客户表
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) - 1); 
			Map<String, String> map = new HashMap<String, String>();
			map.put("startTime", sDateFormat1.format(cal.getTime()));
			cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
			map.put("endTime", sDateFormat1.format(cal.getTime()));
			// 更新客户发货日志
			statusChangeMapper.refreshCustomerWayBillLog(map);
			LOG.info("refreshCustomerWayBillLog end : " + sDateFormat.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("更新客户发货日志失败");
		}
	}
	
	@Override
	@Transactional
	public void deleteWayBillByKeepDay(int keepDay){
		try {
			SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			LOG.info("deleteWayBillByKeepDay start : " + sDateFormat1.format(new Date()));
			// 保留时间
			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd 00:00:00");
			// 参数
			Map<String, String> map = new HashMap<String, String>();
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, -keepDay);
			String keepTime = sDateFormat.format(cal.getTime());
			map.put("keepTime", keepTime);
			// 删除运单
			statusChangeMapper.deleteWayBillByKeepDay(map);
			// 删除签收
			statusChangeMapper.deleteWaybillSignByKeepDay(map);
			LOG.info("deleteWayBillByKeepDay end : " + sDateFormat.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
			LOG.info("根据保留天数删除运单与运单签收信息失败！");
		}
	}
	
	@Override
	@Transactional
	public void indexMessageRemind(){
		SimpleDateFormat sDateFormat1 = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		LOG.info("indexMessageRemind start : " + sDateFormat1.format(new Date()));
		try {
			// 查询未完成指标的人员信息
			List<Map<String,Object>> list = iReportEmpWorkService.querEmpAllIndex();
			if(list != null && list.size() > 0){
				// 消息发送时间
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 12);
		        cal.set(Calendar.SECOND, 0);
		        cal.set(Calendar.MINUTE, 0);
				Date allowSendDate = cal.getTime();
				for(int i=0; i<list.size(); i++){
					Map<String,Object> map = list.get(i);
					//内容生成
					StringBuilder sb = new StringBuilder();
					// 工号
					String empCode = (String)map.get("empCode");
					// 电话洽谈数
					Integer telephones = Integer.valueOf(map.get("telephones").toString());
					// 电话指标数
					Integer telephoneIndex = Integer.valueOf(map.get("telephoneIndex").toString());
					// 登门洽谈数
					Integer visits = Integer.valueOf(map.get("visits").toString());
					// 登门指标数
					Integer visitIndex = Integer.valueOf(map.get("visitIndex").toString());
					// 新增消息
					MessageInfoEntity messageEntity = new MessageInfoEntity();
					messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_BFZBL);
					messageEntity.setReceiveUserId(empCode);
					messageEntity.setCondition(empCode);
					messageEntity.setAllowSendTime(allowSendDate);
					if(telephones != null && telephoneIndex != null &&visits!=null&&visitIndex!=null){
						if(map.get("pushType").toString().equals("0")){
							//您本周共有X次登门拜访指标、8个电话营销指标，已完成X次登门拜访、X个电话营销，还剩X次登门拜访、X个电话营销未完成，请尽快完成任务！
							sb.append("本周您共有").append(visitIndex).append("次登门拜访指标、");
							sb.append(telephoneIndex).append("个电话营销指标，");
							sb.append("已完成").append(visits).append("次登门拜访、");
							sb.append(telephones).append("个电话营销，");
							//判断指标完成情况
							if(visitIndex-visits<=0){
								sb.append("还剩0次登门拜访、");
							}else{
								sb.append("还剩").append(visitIndex - visits).append("次登门拜访、");
							}
							if(telephoneIndex-telephones<=0){
								sb.append("0个电话营销未完成，请尽快完成任务！");
							}else{
								sb.append(telephoneIndex - telephones).append("个电话营销未完成，请尽快完成任务！");
							}
							messageEntity.setMessageContent(sb.toString());
						}else{
							//您本周共有2次登门拜访指标，已完成X次登门拜访，还剩X次登门拜访，请尽快完成任务！
							sb.append("本周您共有").append(visitIndex).append("条登门拜访指标，");
							sb.append("已完成").append(visits).append("次登门拜访，");
							sb.append("还剩").append(visitIndex - visits).append("次登门拜访。请尽快完成任务！");
							messageEntity.setMessageContent(sb.toString());
						}
					}
					iMessageInfoService.addMessage(messageEntity, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("indexMessageRemind end : " + sDateFormat1.format(new Date()));
	}

	@Override
	@Transactional
	public void contractOutOfTimeRemind() {
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(new Date());
		theCa.add(Calendar.DATE,29);
		Date date = theCa.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String endTime = sdf.format(date);
		//刷新合同状态
		statusChangeMapper.updateContractStatusOutofTimeRemind(endTime);
		//生成消息提醒
		List<String> contracts = statusChangeMapper.contractOutOfTimeRemind(endTime);
		if(contracts!=null){
			for(String str :contracts){
				//根据DC账号查询客户信息
				CustomerEntity customerEntity = iCustomerService.queryCustomerInfoByDcAcconut(str);
				//生成消息提醒
				MessageInfoEntity messageEntity = new MessageInfoEntity();
				//消息类型
				messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_XSHTL);
				//接收人
				messageEntity.setReceiveUserId(customerEntity.getManageEmpCode());
				//客户id
				messageEntity.setCondition(customerEntity.getId());
				// 消息发送时间
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.HOUR_OF_DAY, 9);
		        cal.set(Calendar.SECOND, 0);
		        cal.set(Calendar.MINUTE, 0);
				Date allowSendDate = cal.getTime();
				messageEntity.setAllowSendTime(allowSendDate);
				//消息内容
				String messageContent = "合同到期提醒：您的签约客户"+customerEntity.getEnterpriseName()
						+"的销售合同将于"+sdf.format(date)+"到期，请提前联系客户商量续约相关事宜。";
				messageEntity.setMessageContent(messageContent);
				iMessageInfoService.addMessage(messageEntity, null);
			}
		}
	}

	@Override
	@Transactional
	public void randomChatInfos() {
		statusChangeMapper.executeRandomChatInfosProc();
	}
}
