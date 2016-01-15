package com.hoau.crm.module.bse.api.shared.vo;

import com.hoau.hbdp.framework.entity.BaseEntity;

@SuppressWarnings("rawtypes")
public class FunctionTreeNode<T extends BaseEntity> extends
		TreeNode<T, FunctionTreeNode> {

	// 连接
	private String url;

	// 是否可展开
	private Boolean expandable;

	// 是否展开
	private Boolean expend;

	// 显示图标
	private String iconCls;

	// 菜单的CSS
	private String cls;

	// 菜单的显示顺序
	private String displayOrder;

	// 层级路径
	private String path;

	public String getCls() {
		return cls;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setCls(String cls) {
		this.cls = cls;
	}

	/**
	 * GET方法，获取相关属性值
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * GET方法，获取相关属性值
	 */
	public Boolean getExpandable() {
		return expandable;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * GET方法，获取相关属性值
	 */
	public Boolean getExpend() {
		return expend;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setExpend(Boolean expend) {
		this.expend = expend;
	}

	/**
	 * GET方法，获取相关属性值
	 */
	public String getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * GET方法，获取相关属性值
	 */
	public String getPath() {
		return path;
	}

	/**
	 * SET方法，给相关属性赋值
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
