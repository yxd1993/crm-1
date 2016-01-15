package com.hoau.crm.module.appcore.api.server.service;

import java.util.List;

import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisAuthEntity;
import com.hoau.crm.module.appcore.api.shared.vo.ReportAnalysisAppVo;
import com.hoau.hbdp.webservice.components.rest.entity.ResponseBaseEntity;

/**
 * 自定义报表SERVICE
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午2:03:19
 */
public interface IReportAnalysisAppService {

	/**
	 * 查询自定义报表数据
	 * 
	 * @param reportAnalysisAppVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfos(ReportAnalysisAppVo reportAnalysisAppVo,String loginName);
	
	/**
	 * 根据角色编码获得组织类型功能菜单
	 * 
	 * @param roleCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public List<ReportAnalysisAuthEntity> getReportAnalysisAuthByRoleCode(String roleCode);
	
	/**
	 * 根据部门编码查询自定义报表数据
	 * 
	 * @param reportAnalysisAppVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月9日
	 * @update 
	 */
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfoByDeptCode(ReportAnalysisAppVo reportAnalysisAppVo,String loginName);
	
	/**
	 * 是否默认管理组织
	 * 
	 * @param orgType
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月9日
	 * @update 
	 */
	public boolean isDefaultOrgType(String orgType,String roleCode);
	
	/**
	 * 根据部门编码查询自定义报表数据(新)
	 * 
	 * @param reportAnalysisAppVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月9日
	 * @update 
	 */
	public ResponseBaseEntity<ReportAnalysisAppVo> getReportAnalysisInfoData(ReportAnalysisAppVo reportAnalysisAppVo,String loginName);

	
}
