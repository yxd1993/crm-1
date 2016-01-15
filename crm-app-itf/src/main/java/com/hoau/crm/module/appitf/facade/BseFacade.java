package com.hoau.crm.module.appitf.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Service;

import com.hoau.crm.module.appcore.api.server.service.IBseAppService;
import com.hoau.crm.module.appcore.api.shared.domain.AppSweepCardsEntity;
import com.hoau.crm.module.appcore.api.shared.util.AppUtil;
import com.hoau.crm.module.appcore.api.shared.util.HttpConstant;
import com.hoau.crm.module.appcore.api.shared.vo.BseAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.DataDictionaryAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

@Service
@Path("/bse")
@Consumes(HttpConstant.DATATYPE)
@Produces(HttpConstant.DATATYPE)
public class BseFacade extends AppBaseFacade {

	@Resource
	IBseAppService iBseAppService;
	
	@GET
	@Path("queryDataDictionaryVersion")
	public ResponseBaseEntity<DataDictionaryAppVo> queryDataDictionaryVersion() {
		return iBseAppService.queryDataDictionaryVersion();
	}
	
	@GET
	@Path("queryAllDataDictionary")
	public ResponseBaseEntity<DataDictionaryAppVo> queryAllDataDictionary() {
		return iBseAppService.queryAllDataDictionary();
	}

	@POST
	@Path("queryDeptListByUser")
	public ResponseBaseEntity<BseAppVo> queryDeptListByUser(BseAppVo bseAppVo) {
		return iBseAppService.queryDeptList(bseAppVo, loginName);
	}
	
	@POST
	@Path("queryDeptSuperiorDept")
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDept(BseAppVo bseAppVo) {
		return iBseAppService.queryDeptSuperiorDept(bseAppVo,loginName);
	}
	
	@POST
	@Path("queryDeptSuperiorDeptByCurrUser")
	public ResponseBaseEntity<BseAppVo> queryDeptSuperiorDeptByCurrUser(BseAppVo bseAppVo) {
		return iBseAppService.queryDeptSuperiorDeptByCurrUser(bseAppVo, loginName);
	}
	
	@POST
	@Path("queryEmpList")
	public ResponseBaseEntity<BseAppVo> queryEmpList(BseAppVo bseAppVo) {
		return iBseAppService.queryEmpList(bseAppVo);
	}
	
	@SuppressWarnings("rawtypes")
	@GET
	@Path("getServiceDate")
	public ResponseBaseEntity<Map> getServiceDate() {
		ResponseBaseEntity<Map> result = new ResponseBaseEntity<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceDate", new Date());
		result.setResult(map);
		result.setErrorCode(AppUtil.EXCEPTION_STATUS_SUCCESS);
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@POST
	@Path("insertSweepCardsInfo")
	public ResponseBaseEntity insertSweepCardsInfo(AppSweepCardsEntity appSweepCardsEntity){
		return iBseAppService.insertSweepCardsInfo(appSweepCardsEntity, loginName);
	}
	
	@POST
	@Path("querySaleEmpLists")
	public ResponseBaseEntity<BseAppVo> querySaleEmpList(BseAppVo bseAppVo){
		return iBseAppService.querySaleEmpLists(this.loginName,bseAppVo);
	}
}
