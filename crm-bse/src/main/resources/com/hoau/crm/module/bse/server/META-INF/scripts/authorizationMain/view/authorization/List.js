/**
 * 授权信息信息GRID
 */
Ext.define("crm.view.authorization.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.authorizationList',
    store: "Authorization",
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
		            { text : '序号',	width : 50,	xtype : 'rownumberer'},
		            { text: 'id', dataIndex: 'id',xtype :'hiddenfield'},
		         	{ text: '授权人工号', dataIndex: 'authorizedPerson', flex:0.5},
		         	{ text: '被授权人工号', dataIndex: 'wasAuthorizedPerson', flex:0.5},
		         	{ text: '授权开始时间', dataIndex: 'authorizedStartTime',xtype : 'datecolumn', format : 'Y-m-d H:i:s',flex:0.5},
		         	{ text: '授权结束时间', dataIndex: 'authorizedEndTime',xtype : 'datecolumn', format : 'Y-m-d H:i:s',flex:0.5},
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

