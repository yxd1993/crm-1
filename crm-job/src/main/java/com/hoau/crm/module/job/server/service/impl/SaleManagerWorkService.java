/**
 * 
 */
package com.hoau.crm.module.job.server.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hoau.crm.module.appcore.api.server.service.IMessageInfoService;
import com.hoau.crm.module.appcore.api.shared.domain.MessageInfoEntity;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.job.server.dao.SaleManagerWorkMapper;
import com.hoau.crm.module.job.server.service.ISaleManagerWorkService;

/**
 *
 * @author 丁勇
 * @date 2015年10月12日
 */
@org.springframework.stereotype.Service
public class SaleManagerWorkService implements ISaleManagerWorkService {
	
	private static Logger LOG = LoggerFactory
			.getLogger(SaleManagerWorkService.class);
	@Resource
	SaleManagerWorkMapper saleManagerWorkMapper;
	
	@Resource
	IMessageInfoService iMessageInfoService;
	
	@Override
	@Transactional
	public void pushSaleManagerWork() {
		//执行存储过程
		saleManagerWorkMapper.insertSaleManagerWork();
		//所有销售经理日,月工作情况
		List<Map<String,Object>> maps = saleManagerWorkMapper.querySaleManagerWork();
		//判断查询是否异常
		if(maps.size()>0&&!StringUtils.isEmpty(maps)){
			//每个销售经理工作情况
			for (Map<String, Object> map : maps) {
				//消息推送
				MessageInfoEntity messageEntity = new MessageInfoEntity();
				//消息类型
				messageEntity.setMessageType(BseConstants.MESSAGE_TYPE_SALEGZTX);
				//接收人工号
				messageEntity.setReceiveUserId(map.get("empCode").toString());
				//记录id
				messageEntity.setCondition(map.get("id").toString());
				//消息内容
				StringBuffer sb = new StringBuffer();
				sb.append("您好，昨天您一共拜访"+map.get("signsDaily").toString()+"个客户，");
				sb.append("新增"+map.get("chatsDaily").toString()+"个签约客户，");
				sb.append("昨日总产值"+map.get("billingAmountDaily").toString()+"元，");
				sb.append("其中签约客户产值"+map.get("billingAmountBySignDaily").toString()+"元。");
				sb.append("本月共拜访"+map.get("chatsMonth").toString()+"个客户，");
				sb.append("本月新增"+map.get("signsMonth").toString()+"个签约客户，");
				sb.append("本月总产值"+map.get("billingAmountMonth").toString()+"元，");
				sb.append("本月签约客户产值"+map.get("billingAmountBySignsMonth").toString()+"元，");
				sb.append("本月签约客户产值占比"+map.get("billingAmountPercentageMonth").toString());
				sb.append("。今日请再接再厉，加油！");
				messageEntity.setMessageContent(sb.toString());
				Calendar now = Calendar.getInstance();  
		        now.set(Calendar.HOUR_OF_DAY, 9);
		        now.set(Calendar.SECOND, 0);
		        now.set(Calendar.MINUTE, 0);
				messageEntity.setAllowSendTime(now.getTime());
				//保存消息推送
				iMessageInfoService.addMessage(messageEntity,null);
			}	
			LOG.info("保存成功,共"+maps.size()+"条记录"); 
		}else{
			LOG.info("查询异常,共"+maps.size()+"条记录");
		}
	}
}

