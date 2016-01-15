/**
 * 洽谈信息GRID
 */
Ext.define("crm.view.chats.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.chatslist',
    store: "Chats",
    region : 'center',
    emptyText:'没有查询结果,换个查询条件试试.',
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
			        { text: '洽谈开始时间', dataIndex: 'chatStartTime', flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
			 		{ text: '洽谈方式', dataIndex: 'chatType', flex:1,renderer:chatTypeRenderer},
					{ text: '洽谈主题', dataIndex: 'chatTheme', flex:1},
					{ text: '客户全称', dataIndex: 'enterpriseName', flex:1},
					{ text: '联系人姓名', dataIndex: 'contactName', flex:1},
					{ text: '联系电话', dataIndex: 'cellphone', flex:1,renderer:contactTypeRenderer},
					{ text: '拜访地址', dataIndex: 'reserveAddress', flex:2,renderer:isHidden},
					{ text: '创建人', dataIndex: 'empName', flex:1},
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
// 渲染
function chatTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_YXLX");
}
