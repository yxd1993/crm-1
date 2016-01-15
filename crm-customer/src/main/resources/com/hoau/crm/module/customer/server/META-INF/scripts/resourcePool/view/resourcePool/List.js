/**
 * 客户信息GRID
 */
Ext.define("crm.view.resourcePool.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.resourcePoollist',
    store: "resourcePool",
    region : 'center',
    emptyText : '没有查询结果,换个查询条件试试.',
    width : '100%',
    viewConfig:{
		enableTextSelection:true
	},
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
	        { text: '客户企业全称', dataIndex: 'enterpriseName', flex:2},
	        { text: '客户性质', dataIndex: 'accountType', flex:1,renderer:accountTypeRenderer},
	        { text: '所属行业', dataIndex: 'industryCode', flex:1,renderer:industryRenderer},
			{ text: '所属大区', dataIndex: 'regions', flex:1},
			{ text: '联系人姓名', dataIndex: 'contactName', flex:1.2},
			{ text: '联系手机', dataIndex: 'cellphone', flex:1,renderer:isHiddenByPool},
			{ text: '座机号', dataIndex: 'telephone', flex:1,renderer:isHiddenByPool},
			{ text: '地址', dataIndex: 'address', flex:2},
			{ text: '流入时间', dataIndex: 'flowDate', flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
			{ text: '创建时间', dataIndex:'createDate',flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
		 ]
    },
    pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
				store : this.store,
				dock: 'bottom',
		        displayInfo: true
			});
		}
		return this.pagingToolbar;
	},
    constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.bbar = me.getPagingToolbar();
		me.callParent([ cfg ]);
	}
});

/**
 * 所属行业渲染功能
 */
function industryRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_INDUSTRY");
}

/**
 * 
 * 客户性质渲染
 * @param value
 * @returns
 * @author 丁勇
 * @date 2015年10月22日
 * @update
 */
function accountTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_NATURE");
}

/**
 * 隐藏敏感信息
 * 
 * @param value
 * @param meta
 * @param record
 * @returns
 * @author 丁勇
 * @date 2015年10月22日
 * @update
 */
function isHiddenByPool(value,meta,record){
	//var currenEmpCode = getUserContext().getCurrentUserEmp().empCode;
	if(!Ext.isEmpty(value)){
		return "*********";
	}
	return value;
}