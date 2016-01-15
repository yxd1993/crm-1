/**
 * 意向信息GRID
 */
Ext.define("crm.view.intention.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.intentionlist',
    store: "Intention",
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
		    { text: '客户信用评分', dataIndex: 'customerScore', flex:1,renderer:customerScoreRenderer},
	        { text: '客户企业全称', dataIndex: 'enterpriseName', flex:2},
	 		{ text: '联系人姓名', dataIndex: 'contactName', flex:1},
			{ text: '手机', dataIndex: 'cellphone', flex:1},
			{ text: '办公地点', dataIndex: 'detailedAddress', flex:2},
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
function customerScoreRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_CREDIT_GRADE");
}