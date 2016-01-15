/**
 * 客户信息GRID
 */
Ext.define("crm.view.personalcustomer.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.personalCustomerList',
    store: "PersonalCustomer",
    region : 'center',
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
		    { text: '客户状态', dataIndex: 'isTurnCustomer', flex:1,renderer:customerStatusRenderer},
		    { text: '负责人', dataIndex: 'managePerson', flex:1},
		    { text: '渠道来源', dataIndex: 'source', flex:1},
	        { text: '用户名', dataIndex: 'userName', flex:1},
	        { text: '客户名称', dataIndex: 'enterpriseName', flex:1},
	        { text: '详细地址', dataIndex: 'detailedAddress', flex:2},
	        { text: '联系人', dataIndex: 'contactName', flex:1},
			{ text: '手机号', dataIndex: 'cellphone', flex:1},
			{ text: '座机号', dataIndex: 'telephone', flex:1},
			{ text: '邮箱', dataIndex: 'email', flex:1.5}
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
 * 客户状态渲染功能
 * 
 * @date 2015-12-22
 * @update
 */
function customerStatusRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "PERSONALCUSTOMER_STATUS");
}