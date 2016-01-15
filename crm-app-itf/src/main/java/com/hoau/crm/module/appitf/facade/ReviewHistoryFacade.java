/**
 * 
 */
package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IReviewHistoryAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ReviewHistoryAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 历史和回顾 接口模块调用
 * 
 * @author 丁勇
 * @date
 */
@SuppressWarnings("rawtypes")
@Service
@Path("/reviewHistory")
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
public class ReviewHistoryFacade extends AppBaseFacade {
	@Resource
	IReviewHistoryAppService IRservice;

	@Path("getHistory")
	@POST
	public ResponseBaseEntity getHistory(ReviewHistoryAppVo rVo) {
		return IRservice.queryHistoryList(rVo,loginName);
	}

	@Path("getReview")
	@POST
	public ResponseBaseEntity getReview(ReviewHistoryAppVo rVo) {
		return IRservice.queryReviewList(rVo,loginName);
	}
}
