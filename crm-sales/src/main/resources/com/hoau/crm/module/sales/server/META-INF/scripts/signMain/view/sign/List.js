/**
 * 客户签到信息GRID
 */
Ext.define("crm.view.sign.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.signList',
    store: "Sign",
    emptyText : '没有查询结果,换个查询条件试试.',
    region : 'center',
    viewConfig:{
		enableTextSelection:true
	},
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		         	{ text: '签到人', dataIndex: 'customerName', flex:0.3},
		         	{ text: '签到地址', dataIndex: 'signAddress', flex:0.7,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	//{ text: '图片文件名', dataIndex: 'imgName', flex:0.5},
		         	//{ text: '图片路径', dataIndex: 'imgUrl', flex:1.0},
		         	//{ text: '经度', dataIndex: 'longitude', flex:0.4},
		         	//{ text: '纬度', dataIndex: 'latitude', flex:0.4},
		         	{ text: '签到时间', dataIndex: 'createDate', flex:0.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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

