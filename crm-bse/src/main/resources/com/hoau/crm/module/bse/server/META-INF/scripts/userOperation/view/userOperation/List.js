/**
 * 客户操作信息GRID
 */
Ext.define("crm.view.userOperation.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.userOperationList',
    store: "UserOperation",
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
		         	{ text: '客户操作类型', dataIndex: 'userOperationType', flex:0.4, renderer:userOperationTypeRenderer},
		         	{ text: '客户姓名', dataIndex: 'userOperationName', flex:0.5},
		         	{ text: '客户IP', dataIndex: 'userOperationIp', flex:0.5},
		         	{ text: '客户操作时间', dataIndex: 'userOperationTime', flex:0.7, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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

//渲染
function userOperationTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "OPER_CUSTOMER_TYPE");
}
