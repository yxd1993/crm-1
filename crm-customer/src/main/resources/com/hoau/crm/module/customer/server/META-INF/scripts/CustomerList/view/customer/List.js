/**
 * 客户信息GRID
 */
Ext.define("crm.view.customer.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.customerlist',
    store: "Customer",
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
		    {text : '序号',	width : 50,	xtype : 'rownumberer'},
	        { text: '客户企业全称', dataIndex: 'enterpriseName', flex:2},
	        { text: 'DC账号', dataIndex: 'dcAccount', flex:1},
			{ text: '负责人', dataIndex: 'managePerson', flex:1},
			{ text: '大区', dataIndex: 'regionsName', flex:1},
			{ text: '手机', dataIndex: 'cellphone', flex:1,renderer:isHidden},
			{ text: '详细地址', dataIndex: 'detailedAddress', flex:2,renderer:isHidden},
			{ text: '所属行业', dataIndex: 'industryCode', flex:1.5, renderer: industryRenderer},
			{ text: '客户性质', dataIndex: 'accountType', flex:1, renderer: accountTypeRenderer},
			{ text: '创建时间', dataIndex: 'createDate', flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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
 * 
 * @param value
 * @returns
 * @author 蒋落琛
 * @date 2015-5-21
 * @update
 */
function industryRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_INDUSTRY");
}

/**
 * 客户性质渲染功能
 * 
 * @param value
 * @returns
 * @author 蒋落琛
 * @date 2015-5-21
 * @update
 */
function accountTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_NATURE");
}