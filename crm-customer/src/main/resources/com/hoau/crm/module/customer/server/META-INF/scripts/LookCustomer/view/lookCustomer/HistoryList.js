/**
 * 历史信息GRID
 */
Ext.define("crm.view.lookCustomer.HistoryList", {
    extend: "Ext.grid.Panel",
    alias: 'widget.historyList',
    store: "History",
    region : 'center',
    emptyText : '没有查询结果,换个查询条件试试.',
    width : '100%',
    viewConfig:{
		enableTextSelection:true
	},
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		    { text : '序号',	width : 50,	xtype : 'rownumberer'},
	        { text: '操作类型', dataIndex: 'operateName', flex:1},
	        { text: '主要信息',renderer: operateContent,flex:3},
			{ text: '创建时间', dataIndex: 'createTime', flex:1, xtype : 'datecolumn', format : 'Y-m-d H:i:s'},
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
/**
 *根据不同的类型显示不同内容
 * 
 * @param value
 * @param meta
 * @param record
 * @returns
 * @author 丁勇
 * @date 2015年9月8日
 * @update
 */
function operateContent(value,meta,record){
	if(record.get('operateType')==1){
		return "签到地址："+record.get('signAddress');
	}else if(record.get('operateType')==2||record.get('operateType')==3){
		return "联系人名称："+record.get('contactName');
	}else if(record.get('operateType')==5){
		return "预约开始时间："+Ext.util.Format.date(new Date(record.get('reserveStartTime')), 'Y-m-d H:i')+"，主题："+record.get('reserveTheme');
	}else if(record.get('operateType')==6||record.get('operateType')==7){
		return "洽谈开始时间："+Ext.util.Format.date(new Date(record.get('chatStartTime')), 'Y-m-d H:i')+"，主题："+record.get('chatTheme');
	}else if(record.get('operateType')==8){
		return "主要货物名称："+record.get('mainGoodsName');
	}
}