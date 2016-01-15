package com.hoau.crm.module.bse.server.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IDataDictionaryService;
import com.hoau.crm.module.bse.api.server.service.IDataDictionaryValueService;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryEntity;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.exception.MessageType;
import com.hoau.crm.module.bse.api.shared.vo.DataDictionaryVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.server.web.result.json.annotation.JSON;

/**
 * @author：高佳
 * @create：2015年5月14日 下午5:12:52
 * @description：数据字典action
 */
@Controller
@Scope("prototype")
public class DataDictionaryAction extends AbstractAction{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private IDataDictionaryValueService dataDictionaryValueService;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	
	private DataDictionaryVo dataDictionaryVo;
	
	
	/**
	 * 客户端数据字典版本号
	 */
	private long clientVersionNo;
	
	@Override
	public String execute() throws Exception {
		return returnSuccess();
	}
	/**
	 * 
	 * 查询所有数据字典数据
	 * @return
	 * @author 高佳
	 * @date 2015年5月15日
	 * @update
	 */
	@JSON
	public String searchAllDataDictionary(){
		try {
			dataDictionaryVo = new DataDictionaryVo();
			dataDictionaryVo.setDataDictionaryEntitys(
				dataDictionaryService.queryAllDataDictionary()
			);
			long lLastChangeVersionNo = dataDictionaryValueService.getLastChangeVersionNo();
			clientVersionNo = lLastChangeVersionNo;
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
	}
	/**
	 * 检测数据字典是否有更新
	 * @return
	 * @author 高佳
	 * @date 2015年5月15日
	 * @update 
	 */
	@JSON
	public String isDictionaryHasChanged() {
		try {
			long lLastChangeVersionNo = dataDictionaryValueService.getLastChangeVersionNo();
			if(clientVersionNo == lLastChangeVersionNo) {
				return returnSuccess("keep");
			} else {
				clientVersionNo = lLastChangeVersionNo;
				return returnSuccess("refresh");
			}
		} catch (BusinessException e) {
			return returnError(e.getMessage());
		}
	}
	
	
	/**
	 * 根据条件查询数据字典
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	@JSON
	public String queryDataDictionaryValueByEntity(){
		try {
			List<DataDictionaryValueEntity> dataDictionaryValueEntityList = dataDictionaryValueService.queryDataDictionaryValueByEntity(dataDictionaryVo.getDataDictionaryValueEntity(),start,limit);
			dataDictionaryVo.setDataDictionaryValueEntityList(dataDictionaryValueEntityList);
			totalCount = dataDictionaryValueService.queryDataDictionaryValueByEntityCount(dataDictionaryVo.getDataDictionaryValueEntity());
			return returnSuccess();
		} catch (BusinessException e) {
			return returnError(e);
		}
		
	}
	
	/**
	 * 根据条件查找词条
	 * @return
	 * @author 高佳
	 * @date 2015年5月18日
	 * @update 
	 */
	@JSON
	public String queryDataDictionaryByEntity(){
		try {
			List<DataDictionaryEntity> dataDictionaryEntitys = dataDictionaryService.queryDataDictionaryByEntity(dataDictionaryVo.getDataDictionaryEntity(),limit,start);
			dataDictionaryVo.setDataDictionaryEntitys(dataDictionaryEntitys);
			totalCount = dataDictionaryService.queryDataDictionaryByEntityCount(dataDictionaryVo.getDataDictionaryEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 新增数据字典信息
	 * @return
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	@JSON
	public String addDataDictionaryValue(){
		try {
			dataDictionaryValueService.addDataDictionaryValue(dataDictionaryVo.getDataDictionaryValueEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.SAVE_SUCCESS);
	}
	/**
	 * 删除数据字典信息
	 * @return
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	@JSON
	public String deleteDataDictionaryValue(){
		try {
			dataDictionaryValueService.deleteDataDictionaryValueMore(dataDictionaryVo.getDataDictionaryValueEntityList());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.DELETE_SUCCESS);
	}
	/**
	 * 新增数据字典词条信息
	 * @return
	 * @author 高佳
	 * @date 2015年5月19日
	 * @update 
	 */
	@JSON
	public String addDataDictionary(){
		try {
			dataDictionaryService.addDataDictionary(dataDictionaryVo.getDataDictionaryEntity());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess(MessageType.SAVE_SUCCESS);
	}
	
	
	public DataDictionaryVo getDataDictionaryVo() {
		return dataDictionaryVo;
	}

	public void setDataDictionaryVo(DataDictionaryVo dataDictionaryVo) {
		this.dataDictionaryVo = dataDictionaryVo;
	}


	public long getClientVersionNo() {
		return clientVersionNo;
	}


	public void setClientVersionNo(long clientVersionNo) {
		this.clientVersionNo = clientVersionNo;
	}
	
	
	
}
