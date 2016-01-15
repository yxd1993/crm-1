package com.hoau.crm.module.appitf.facade;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.bse.api.server.service.IWayBillAppService;
import com.hoau.crm.module.bse.api.shared.vo.WayBillVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 运单模块对外接口
 *
 * @author 蒋落琛
 * @date 2015-7-6
 */
@Service
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
@Path("/waybill")
public class WayBillFacade {

	@Resource
	private IWayBillAppService iWayBillAppService;
	
	@Path("addWayBillInfo")
	@POST
	public ResponseBaseEntity<WayBillVo> addWayBillInfo(WayBillVo waybillVo){
		return iWayBillAppService.addWayBillInfo(waybillVo);
	}
	@Path("queryWayBillLogByAccountId")
	@POST
	public ResponseBaseEntity<WayBillVo> queryWayBillLogByAccountId(WayBillVo waybillVo){
		return iWayBillAppService.queryWayBillLogByAccountId(waybillVo);
	}
}
