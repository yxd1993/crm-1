/**
 * App版本信息GRID
 */
Ext.define("crm.view.dailytrain.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.dailytrainList',
    store: "dailytrain",
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
		            { text: 'meetingSignId', dataIndex: 'meetingSignId',xtype :'hiddenfield'},
		            { text: '创建人', dataIndex: 'createUser',width:100},
		         	{ text: '会议时间', dataIndex: 'hyDate',width:150, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
		         	{ text: '培训类型', dataIndex: 'pxType', width:100,renderer:dispensepxTypeRenderer},
		         	{ text: '会议地址', dataIndex: 'hyaddress', width:200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '参与人员', dataIndex: 'cyEmp', width:200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '培训内容', dataIndex: 'pxcontext', width:200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
			        { text: '创建时间', dataIndex: 'createDate', width:150, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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

function dispensepxTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "DAILY_DPXLX");
}
