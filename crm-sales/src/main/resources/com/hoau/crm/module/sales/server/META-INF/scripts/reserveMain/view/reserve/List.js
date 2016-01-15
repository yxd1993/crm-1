/**
 * 预约信息GRID
 */
Ext.define("crm.view.reserve.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.reservelist',
    store: "Reserve",
    region : 'center',
    emptyText : '没有查询结果,换个查询条件试试.',
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
				    {text : '状态',	dataIndex:'isAgreement',width : 50,	flex:1,renderer:reserveStatus},
			        { text: '预约开始时间', dataIndex: 'reserveStartTime', flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
			 		{ text: '预约方式', dataIndex: 'reserveType', flex:1,renderer:reserveTypeRenderer},
					{ text: '预约主题', dataIndex: 'reserveTheme', flex:1},
					{ text: '客户全称', dataIndex: 'enterpriseName', flex:1},
					{ text: '联系人姓名', dataIndex: 'contactName', flex:1},
					{ text: '联系电话', dataIndex: 'cellphone', flex:1,renderer:contactTypeRenderer},
					{ text: '办公地址', dataIndex: 'detailedAddress', flex:2,renderer:isHidden},
					{ text: '创建人', dataIndex: 'empName', flex:1}
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
function reserveTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_YXLX");
}
//预约状态
function reserveStatus(value){
	if(value=='Y'){
		return '已赴约';
	}else if(value=='N'){
		return '未赴约';
	}
}
