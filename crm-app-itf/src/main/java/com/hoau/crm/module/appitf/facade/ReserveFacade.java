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

import com.hoau.crm.module.appcore.api.server.service.IReserveAppService;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.ReserveAppVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleReserveVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 预约模块 接口调用
 * @author 丁勇
 * @date 2015年7月6日
 */
@SuppressWarnings("rawtypes")
@Service
@Path("/reserve")
@Produces(HttpConstant.DATATYPE)
@Consumes(HttpConstant.DATATYPE)
public class ReserveFacade extends AppBaseFacade {
	@Resource
	IReserveAppService iReserveAppService;
	
	@POST
	@Path("merge")
	public ResponseBaseEntity merge(ReserveAppVo reserveVo){
		return iReserveAppService.saveOrUpdate(reserveVo,loginName);
	}
	@POST
	@Path("queryReserveById")
	public ResponseBaseEntity queryDetailById(ReserveAppVo reserveVo){
		return iReserveAppService.queryReserveById(reserveVo,loginName);
	}
	@POST
	@Path("delReserve")
	public ResponseBaseEntity delReserve(ReserveAppVo reserveVo){
		return iReserveAppService.delReserve(reserveVo, loginName);
	}
	@POST
	@Path("getReserveByPaging")
	public ResponseBaseEntity getReserveByPaging(ReserveAppVo reserveVo){
		return iReserveAppService.getReserveByPaging(reserveVo, loginName);
	}
	@POST
	@Path("queryNoReserve")
	public ResponseBaseEntity queryNoReserve(SaleReserveVo reserveVo){
		return iReserveAppService.queryNoReserve(reserveVo, loginName);
	}
}
