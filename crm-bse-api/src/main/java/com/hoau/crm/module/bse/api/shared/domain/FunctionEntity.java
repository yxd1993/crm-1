package com.hoau.crm.module.bse.api.shared.domain;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.hbdp.framework.entity.BaseEntity;
import com.hoau.hbdp.framework.entity.IFunction;
import com.hoau.hbdp.framework.entity.IModule;

/**
 * 菜单权限信息实体
 *
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class FunctionEntity extends BaseEntity implements IFunction {
	
	private static final long serialVersionUID = 8204215052602820708L;

	//功能编码
	private String functionCode;

	//功能名称
	private String functionName;

	//功能入口URI
	private String uri;

	//功能层次
	private String functionLevel;

	//父功能
	private FunctionEntity parentCode;

	//是否有效
	private String active;
	
	//显示顺序
	private String displayOrder;
	
	//是否权限检查
	private String checkable;
	
	//功能类型
	private String functionType;
	
	//是否叶子节点
	private String leaf;
	
	//图标的CSS样式
	private String iconCls;
	
	//节点的CSS样式
	private String cls;
	
	//功能描述
	private String functionDesc;

	//功能路径描述
	private String functionSeq;
	
	//所属系统类型(WEB/GUI)
	private String systemType;
	
	public FunctionEntity() { }
	
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public FunctionEntity(String functionCode) {
		super();
		this.functionCode = functionCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public FunctionEntity getParentCode() {
		return parentCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setParentCode(FunctionEntity parentCode) {
		this.parentCode = parentCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getFunctionSeq() {
		return functionSeq;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionSeq(String functionSeq) {
		this.functionSeq = functionSeq;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getFunctionDesc() {
		return functionDesc;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getIconCls() {
		return iconCls;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getSystemType() {
		return systemType;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getActive() {
		return active;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setActive(String active) {
		this.active = active;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getDisplayOrder() {
		return displayOrder;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getCheckable() {
		return checkable;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setCheckable(String checkable) {
		this.checkable = checkable;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getFunctionType() {
		return functionType;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getLeaf() {
		return leaf;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getCls() {
		return cls;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setCls(String cls) {
		this.cls = cls;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getFunctionLevel() {
		return functionLevel;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionLevel(String functionLevel) {
		this.functionLevel = functionLevel;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public IModule getModule() {
		return null;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public String getUri() {
		return this.uri;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public String getKey() {
		return this.getId();
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public String getFunctionCode() {
		return this.functionCode;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public Boolean getValidFlag() {
		return BseConstants.ACTIVE.equalsIgnoreCase(active);
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	@Override
	public String getName() {
		return this.functionName;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public String getFunctionName() {
		return functionName;
	}
	/** 
	 * GET方法 获取数据
	 * SET方法 设置数据
	 * */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}
