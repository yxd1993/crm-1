/**
 * 销售合同GRID
 */
Ext.define("crm.view.contract.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.contractList',
    store: "Contract",
    region : 'center',
    autoHeight: true,
    autoWidth: true,
    height: 600,
    width: 1000,
    emptyText : '没有查询结果,换个查询条件试试.',
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
	        { text: '合同状态', dataIndex: 'status',renderer:contractStatusRenderer},
	 		{ text: 'CRM账号', dataIndex: 'crmAccount',width: 125},
			{ text: 'DC账号', dataIndex: 'dcAccount'},
			{ text: '客户企业全称', dataIndex: 'enterpriseName',width: 150},
			{ text: '门店', dataIndex: 'applyUserDeclareName'},
			{ text: '路区', dataIndex: 'road'},
			{ text: '大区', dataIndex: 'regions'},
			{ text: '事业部', dataIndex: 'businessUnit'},
			{ text: '合同流水号', dataIndex: 'workflowCode',width: 165},
			{ text: '合同开始时间', dataIndex: 'contractStartDate', xtype : 'datecolumn', format : 'Y-m-d',width: 105},
			{ text: '合同结束时间', dataIndex: 'contractEndDate', xtype : 'datecolumn', format : 'Y-m-d',width: 105},
			{ text: '合同创建时间', dataIndex: 'applyDate', xtype : 'datecolumn', format : 'Y-m-d',width: 105},
			{ text: '合同归档时间', dataIndex: 'fileDate', xtype : 'datecolumn', format : 'Y-m-d',width: 105}
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
 * 合同状态渲染功能
 * 
 * @param value
 * @returns
 * @author 蒋落琛
 * @date 2015-5-21
 * @update
 */
function contractStatusRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CONTRACT_STATUS");
}