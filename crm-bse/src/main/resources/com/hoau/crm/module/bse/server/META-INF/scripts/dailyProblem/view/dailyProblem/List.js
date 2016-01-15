/**
 * App版本信息GRID
 */
Ext.define("crm.view.dailyProblem.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.dailyProblemList',
    store: "dailyProblem",
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
		         	{ text: '演练时间', dataIndex: 'yltime',width : 150, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
		         	{ text: '演练方式', dataIndex: 'ylway', width : 100,renderer:dispenseylwayRenderer},
		         	{ text: '演练地址', dataIndex: 'yladdress', width : 200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '参与人员', dataIndex: 'cyEmp', width : 200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '问题描述', dataIndex: 'wtdescribe', width : 200,renderer : function(value, p, record) {
		         		if(!Ext.isEmpty(value)){
		         			return '<div style="white-space:normal;">' + value + '</div>';
		         		}
			        }},
		         	{ text: '举措描述', dataIndex: 'jcdescribe', width : 200,renderer : function(value, p, record) {
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

function dispenseylwayRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "DAILY_DYLXS");
}
