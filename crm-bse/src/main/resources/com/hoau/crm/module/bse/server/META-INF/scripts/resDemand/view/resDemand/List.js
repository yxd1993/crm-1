/**
 * App版本信息GRID
 */
Ext.define("crm.view.resDemand.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.resDemandList',
    store: "resDemand",
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
		            { text: 'uuid', dataIndex: 'id',xtype :'hiddenfield'},
		            { text: '创建人', dataIndex: 'createUser',flex:0.5},
		         	{ text: '期望解决时间', dataIndex: 'solvetime',flex:0.5, xtype : 'datecolumn', format : 'Y-m-d'},
		         	{ text: '需求资源', dataIndex: 'resources', flex:0.8,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '是否与大区总沟通', dataIndex: 'isgt', flex:0.6,renderer:dispenseIsMustRenderer},
		         	{ text: '大区总意见', dataIndex: 'regviews', flex:0.8,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '集团意见', dataIndex: 'groupopin', flex:0.8,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '是否答复', dataIndex: 'isreply', flex:0.4,renderer:dispenseIsMustRenderer},
		         	{ text: '创建时间', dataIndex: 'createDate', flex:0.7, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
		         	
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

function dispenseIsMustRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "APP_VERSION_ISMUST");
}
