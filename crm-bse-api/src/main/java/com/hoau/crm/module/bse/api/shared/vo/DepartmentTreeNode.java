package com.hoau.crm.module.bse.api.shared.vo;

import com.hoau.hbdp.framework.entity.BaseEntity;

@SuppressWarnings("rawtypes")
public class DepartmentTreeNode<T extends BaseEntity> extends
		TreeNode<T, DepartmentTreeNode> {

	// 是否可展开
	private Boolean expandable;

	// 是否展开
	private Boolean expend;

	public Boolean getExpandable() {
		return expandable;
	}

	public void setExpandable(Boolean expandable) {
		this.expandable = expandable;
	}

	public Boolean getExpend() {
		return expend;
	}

	public void setExpend(Boolean expend) {
		this.expend = expend;
	}

}
