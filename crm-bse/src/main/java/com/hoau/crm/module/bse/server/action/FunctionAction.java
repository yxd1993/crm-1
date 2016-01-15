package com.hoau.crm.module.bse.server.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.server.service.IFunctionService;
import com.hoau.crm.module.bse.api.server.service.IRoleService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.FunctionEntity;
import com.hoau.crm.module.bse.api.shared.vo.FunctionTreeNode;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @描 述：菜单权限ACTION
 * @作 者：蒋落琛
 * @创建日期：2015-5-13
 */
@Controller
@Scope("prototype")
public class FunctionAction extends AbstractAction {

	private static final long serialVersionUID = 5490905806474476603L;

	/**
	 * SERVICE
	 */
	@Resource
	private IFunctionService functionService;
	
	/**
	 * ROLE SERVICE
	 */
	@Resource
	private IRoleService iRoleService;

	/**
	 * 菜单实体信息
	 */
	private FunctionEntity functionEntity;

	/**
	 * 菜单信息集合
	 */
	@SuppressWarnings("rawtypes")
	private List<FunctionTreeNode> nodes;

	/**
	 * 当前节点ID
	 */
	private String node;
	
	/**
	 * 当前操作状态 1修改 0查看 2新增
	 */
	private int modify;
	
	/**
	 * 角色编码
	 */
	private String roleCode;

	/**
	 * 查询当前节点子节点信息
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-13
	 * @update
	 */
	@SuppressWarnings("rawtypes")
	public String queryTreeNodeList() {
		try {
			if(node.equals("root")){
				node = "1";
			}
			// 查询条件封装
			FunctionEntity functionEntity = new FunctionEntity();
			FunctionEntity parentFunctionEntity = new FunctionEntity();
			parentFunctionEntity.setFunctionCode(node);
			functionEntity.setParentCode(parentFunctionEntity);
			functionEntity.setFunctionType(BseConstants.FUNCTION_MODULE);
			// 返回的节点信息
			nodes = new ArrayList<FunctionTreeNode>();
			// 查询子节点信息
			List<FunctionEntity> fList = functionService
					.queryTreeNodeListByHomeMenu(functionEntity);
			// 遍历进行转换
			for (FunctionEntity res : fList) {
				// 转换菜单对象为节点对象
				FunctionTreeNode<FunctionEntity> treeNode = changeResToTreeNode(res);
				nodes.add(treeNode);
			}
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
	private FunctionTreeNode<FunctionEntity> changeResToTreeNode(
			FunctionEntity function) {
		FunctionTreeNode<FunctionEntity> treeNode = new FunctionTreeNode<FunctionEntity>();
		treeNode.setId(function.getFunctionCode());
		treeNode.setText(function.getName());
		treeNode.setExpandable(!BseConstants.YES.equalsIgnoreCase(function
				.getLeaf()));
		treeNode.setCls(function.getCls());
		treeNode.setIconCls(function.getIconCls());
		treeNode.setDisplayOrder(function.getDisplayOrder());
		treeNode.setUrl(function.getUri());
		// 查询条件封装 查询是否有子节点
		FunctionEntity functionEntity = new FunctionEntity();
		FunctionEntity parentFunctionEntity = new FunctionEntity();
		parentFunctionEntity.setFunctionCode(function.getFunctionCode());
		functionEntity.setParentCode(parentFunctionEntity);
		functionEntity.setFunctionType(BseConstants.FUNCTION_MODULE);
		// 查询子节点信息
		List<FunctionEntity> fList = functionService
				.queryTreeNodeListByHomeMenu(functionEntity);
		if (fList != null && fList.size() > 0) {
			treeNode.setLeaf(false);
		} else {
			treeNode.setLeaf(true);
		}
		if (function.getParentCode() != null) {
			treeNode.setParentId(function.getParentCode().getFunctionCode());
		} else {
			treeNode.setParentId(null);
		}
		treeNode.setPath(function.getFunctionSeq());
		// treeNode.setEntity(res);
		return treeNode;
	}
	
	/**
	 * 加载角色权限树
	 * 
	 * @return
	 * @author 蒋落琛
	 * @date 2015-5-29
	 * @update
	 */
	public String loadFunctionChooseAllTree() {
		if(node.equals("root")){
			node = "1";
		}
		FunctionEntity function = new FunctionEntity();
		FunctionEntity parentFunction = new FunctionEntity();
		parentFunction.setFunctionCode(node);
		function.setParentCode(parentFunction);
		
		this.nodes = this.queryCheckedTreeNodes(roleCode, function, modify);
		return returnSuccess();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<FunctionTreeNode> queryCheckedTreeNodes(String roleCode, FunctionEntity function, int state) {
		List<FunctionTreeNode> nodes = new ArrayList();
		List<FunctionEntity> functionList = functionService
				.queryTreeNodeList(function);
		if (functionList.size() > 0) {
			List<String> selectedIds = null;
			if (state != 2) {
				selectedIds = iRoleService.queryAllFunctionIdByRoleId(roleCode);
			}
			
			/* 拼装TreeNode并确认是否选中 */
			for (FunctionEntity fun : functionList) {
				FunctionTreeNode treeNode = new FunctionTreeNode();
				treeNode.setId(fun.getFunctionCode());
				treeNode.setText(fun.getFunctionName());
				if (fun.getParentCode() != null) {
					treeNode.setParentId(fun.getParentCode().getFunctionCode());
				} else {
					treeNode.setParentId(null);
				}
				if (fun.getLeaf().equals(BseConstants.YES)) {
					treeNode.setLeaf(true);
				} else {
					treeNode.setLeaf(false);
				}
				treeNode.setExpandable(!BseConstants.YES.equalsIgnoreCase(function
						.getLeaf()));
				treeNode.setEntity(fun);
				boolean checked = false;// 选中标示
				if (selectedIds != null && selectedIds.size() > 0) {
					if (selectedIds.contains(fun.getFunctionCode())) {
						checked = true;
					}
				}
				if (state == 1 || state == 2) {
					treeNode.setChecked(checked);
					nodes.add(treeNode);
				} else {
					if (checked) {
						nodes.add(treeNode);
					}
				}
			}
		}
		return nodes;
	}

	public FunctionEntity getFunctionEntity() {
		return functionEntity;
	}

	public void setFunctionEntity(FunctionEntity functionEntity) {
		this.functionEntity = functionEntity;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@SuppressWarnings("rawtypes")
	public List<FunctionTreeNode> getNodes() {
		return nodes;
	}

	@SuppressWarnings("rawtypes")
	public void setNodes(List<FunctionTreeNode> nodes) {
		this.nodes = nodes;
	}

	public int getModify() {
		return modify;
	}

	public void setModify(int modify) {
		this.modify = modify;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}
