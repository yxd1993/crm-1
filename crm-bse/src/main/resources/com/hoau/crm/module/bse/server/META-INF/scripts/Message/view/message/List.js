/**
 * 客户信息GRID
 */
Ext.define("crm.view.message.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.messagelist',
    store: "Message",
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
	        { text: '消息类型', dataIndex: 'messageType', flex:1, renderer: messageTypeRenderer},
	        { text: '创建时间', dataIndex: 'createDate', flex:1, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
	        { text: '消息内容', dataIndex: 'messageContent',flex:4, renderer : function(value, p, record) {
	        	  return '<div style="white-space:normal;">' + value + '</div>';
	        }}
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
 * 消息类型渲染功能
 * 
 * @param value
 * @returns
 * @author 蒋落琛
 * @date 2015-5-21
 * @update
 */
function messageTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "MESSAGE_TYPE");
}
