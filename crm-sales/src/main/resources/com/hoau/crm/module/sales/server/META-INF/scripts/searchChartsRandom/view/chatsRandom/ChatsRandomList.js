/**
 * 洽谈回访随机抽取查询GRID
 */
Ext.define("crm.view.chatsRandom.ChatsRandomList", {
	extend : "Ext.grid.Panel",
	alias : 'widget.chatsRandomlist',
	store : "ChatsRandom",
	region : 'center',
    autoHeight: true,
    autoWidth: true,
    height: 600,
    width: 1000,
	emptyText : '没有查询结果,换个查询条件试试.',
	width : '100%',
	viewConfig : {
		enableTextSelection : true
	},
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	columns : {
		defaults : {
			align : 'left'
		},
		items : [ {
			text : '序号',
			width : 50,
			xtype : 'rownumberer'
		}, {
			text : '审核状态',
			dataIndex : 'status',
			width : 80,
			renderer:chatStatusRenderer
		}, {
			text : '客户全称',
			dataIndex : 'customerName',
			width: 165
		}, {
			text : '联系人手机',
			dataIndex : 'cellphone'
		}, {
			text : '洽谈时间',
			dataIndex : 'chatStartTime',
			xtype : 'datecolumn',
			format : 'Y-m-d'
		}, {
			text : '洽谈方式',
			dataIndex : 'chatType',
			renderer : chatTypeRenderer
		}, {
			text : '洽谈内容',
			dataIndex : 'chatContent'
		}, {
			text : '创建时间',
			dataIndex : 'createDate',
			xtype : 'datecolumn',
			format : 'Y-m-d'
		}, {
			text : '创建人',
			width : 70,
			dataIndex : 'createUserName'
		}, {
			text : '部门',
			width : 70,
			dataIndex : 'createUserDept'
		}, {
			text : '一级部门',
			dataIndex : 'oneLevelUnit',
			width: 100
		}, {
			text : '二级部门',
			dataIndex : 'twoLevelUnit',
			width: 100
		}, {
			text : '三级部门',
			dataIndex : 'threeLevelUnit',
			width: 100
		} ]
	},
	pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
				store : this.store,
				dock : 'bottom',
				displayInfo : true
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
//洽谈类型 渲染
function chatTypeRenderer(value) {
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_YXLX");
}

//抽查状态渲染
function chatStatusRenderer(value) {
	return getDataDictionary().rendererSubmitToDisplay(value, "RANDOM_CHAT_STATUS");
}
