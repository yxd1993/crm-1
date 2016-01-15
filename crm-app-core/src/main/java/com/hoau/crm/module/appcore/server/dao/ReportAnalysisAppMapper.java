package com.hoau.crm.module.appcore.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisAuthEntity;
import com.hoau.crm.module.appcore.api.shared.domain.ReportAnalysisEntity;

/**
 * 自定义报表DAO
 * 
 * @author: 何斌
 * @create: 2015年9月8日 下午2:33:38
 */
@Repository
public interface ReportAnalysisAppMapper {

	/**
	 *	获得自定义报表数据
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public List<ReportAnalysisEntity> getReportAnalysisInfos(Map<String,String> params,RowBounds rb);
	
	/**
	 * 统计自定义报表数据总数
	 * 
	 * @param params
	 * @param rb
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public long countReportAnalysisInfos(Map<String,String> params,RowBounds rb);
	
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
	 * 根据用户名查询角色编码
	 * 
	 * @param userCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public String getRoleCodeByUserCode(String userCode);
	
	/**
	 * 获取销售人员信息
	 * 
	 * @param deptCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月9日
	 * @update 
	 */
	public Map<String,String> getManagerInfo(String empCode);
	
	/**
	 * 是否默认管理组织
	 * 
	 * @param orgType
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月9日
	 * @update 
	 */
	public long isDefaultOrgType(Map<String,String> params);
	
	/**
	 * orgType在当前角色菜单中的排序
	 * 
	 * @param roleCode
	 * @param orgType
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月14日
	 * @update 
	 */
	public String getSortNumInMenu(Map<String,String> params);
	
	/**
	 * 计算占比
	 * 
	 * @param params
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月15日
	 * @update 
	 */
	public void execPercent(Map<String,String> params);
	
	/**
	 * 获得上级的部门编码
	 * 
	 * @param orgCode
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月25日
	 * @update 
	 */
	public String getSupOrgCode(String orgCode);
	
}
