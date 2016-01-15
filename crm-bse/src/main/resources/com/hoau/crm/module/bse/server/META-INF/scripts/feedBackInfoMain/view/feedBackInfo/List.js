/**
 * 反馈信息GRID
 */
Ext.define("crm.view.feedBackInfo.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.feedBackInfolist',
    store: "FeedBackInfo",
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
		         	{ text: '反馈类型', dataIndex: 'fbType', flex:0.4, renderer:feedBackInfoTypeRenderer},
		         	{ text: '反馈主题', dataIndex: 'fbTitle', flex:1, renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';}},
		         	{ text: '反馈内容', dataIndex: 'fbContent', flex:1.7, renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		            { text: '反馈用户姓名', dataIndex: 'userName', flex:0.5},
		            { text: '反馈时间', dataIndex: 'fbTime', flex:0.7, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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
function feedBackInfoTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "FEEDBACK_TYPE");
}
