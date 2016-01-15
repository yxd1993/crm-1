package com.hoau.crm.module.bse.server.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IDepartmentService;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentTreeNode;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 组织ACTION
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
@Controller
@Scope("prototype")
@SuppressWarnings("rawtypes")
public class DepartmentAction extends AbstractAction {

	private static final long serialVersionUID = 3227942162519328364L;

	/**
	 * 组织信息
	 */
	private DepartmentVo departmentVo;

	/**
	 * 组织信息
	 */
	private DepartmentEntity deptEntity;

	/**
	 * 部门信息集合
	 */
	private List<DepartmentTreeNode> nodes;

	/**
	 * 部门信息集合
	 */
	private List<DepartmentEntity> deptList;

	/**
	 * 树节点
	 */
	private String node;
	
	/**
	 * 当前客户负责人工号
	 */
	private String customerManageEmpCode;
	
	/**
	 * 当前客户负责人姓名
	 */
	private String customerManageEmpName;

	/**
	 * 部门信息SERVICE
	 */
	@Resource
	private IDepartmentService iDepartmentService;

	/**
	 * 根据父节点查询部门信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	public String queryChildDeptList() {
		try {
			// 封装参数
			DepartmentEntity departmentEntity = new DepartmentEntity();
			DepartmentEntity parentDeptEntity = new DepartmentEntity();
			if(node == null || node.equals("root")){
				node = "root";
			}
			parentDeptEntity.setDeptCode(node);
			departmentEntity.setParentDeptEntity(parentDeptEntity);
			// 返回的节点信息
			nodes = new ArrayList<DepartmentTreeNode>();
			// 查询信息
			List<DepartmentEntity> deptList = iDepartmentService
					.queryChildDeptList(departmentEntity);
			// 遍历进行转换
			for (DepartmentEntity res : deptList) {
				// 转换菜单对象为节点对象
				DepartmentTreeNode<DepartmentEntity> treeNode = changeResToTreeNode(res);
				nodes.add(treeNode);
			}
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 分页查询组织信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryDeptList() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			deptList = iDepartmentService.queryDeptList(departmentVo, rb, currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 根据组织编码查询组织信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-15
	 * @update
	 */
	public String queryDeptByDeptCode() {
		try {
			deptEntity = iDepartmentService.queryDeptByDeptCode(deptEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 查询组织的上级组织信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-22
	 * @update
	 */
	public String queryDeptSuperiorDept() {
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			Map<String, String> customerInfo = new HashMap<String, String>();
			customerInfo.put("logistCode", deptEntity.getLogistCode());
			if(StringUtil.isEmpty(customerManageEmpCode) || StringUtil.isEmpty(customerManageEmpName)){
				customerInfo.put("customerManageEmpCode", null);
				customerInfo.put("customerManageEmpName", null);
			} else {
				customerInfo.put("customerManageEmpCode", customerManageEmpCode);
				customerInfo.put("customerManageEmpName", customerManageEmpName);
			}
			departmentVo = iDepartmentService.queryDeptSuperiorDeptByCurrUser(customerInfo, currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 查询组织的上级组织信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-22
	 * @update
	 */
	public String queryDeptSuperiorDeptByCustomer() {
		try {
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			departmentVo = iDepartmentService.queryDeptSuperiorDept(deptEntity,currentUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 转换菜单对象为树节点对象
	 * 
	 * @param function
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-14
	 * @update
	 */
	private DepartmentTreeNode<DepartmentEntity> changeResToTreeNode(
			DepartmentEntity dept) {
		DepartmentTreeNode<DepartmentEntity> treeNode = new DepartmentTreeNode<DepartmentEntity>();
		treeNode.setId(dept.getDeptCode());
		treeNode.setText(dept.getDeptName());
		if (dept.getChildDeptList() != null
				&& dept.getChildDeptList().size() > 0) {
			treeNode.setLeaf(false);
			treeNode.setExpandable(true);
		} else {
			treeNode.setLeaf(true);
			treeNode.setExpandable(false);
		}
		if (dept.getParentDeptEntity() != null
				&& dept.getParentDeptEntity().getDeptCode() != null) {
			treeNode.setParentId(dept.getParentDeptEntity().getDeptCode());
		} else {
			treeNode.setParentId(null);
		}
		return treeNode;
	}

	/**
	 * 
	 * 首页面
	 * 
	 * @return
	 * @author 莫涛
	 * @date 2015年5月18日
	 * @update
	 */
	public String index() {
		return returnSuccess();
	}

	public DepartmentVo getDepartmentVo() {
		return departmentVo;
	}

	public void setDepartmentVo(DepartmentVo departmentVo) {
		this.departmentVo = departmentVo;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public List<DepartmentTreeNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<DepartmentTreeNode> nodes) {
		this.nodes = nodes;
	}

	public List<DepartmentEntity> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<DepartmentEntity> deptList) {
		this.deptList = deptList;
	}

	public DepartmentEntity getDeptEntity() {
		return deptEntity;
	}

	public void setDeptEntity(DepartmentEntity deptEntity) {
		this.deptEntity = deptEntity;
	}

	public String getCustomerManageEmpCode() {
		return customerManageEmpCode;
	}

	public void setCustomerManageEmpCode(String customerManageEmpCode) {
		this.customerManageEmpCode = customerManageEmpCode;
	}

	public String getCustomerManageEmpName() {
		return customerManageEmpName;
	}

	public void setCustomerManageEmpName(String customerManageEmpName) {
		this.customerManageEmpName = customerManageEmpName;
	}
	
}
