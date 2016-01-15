/**
 * 客户信息GRID
 */
Ext.define("crm.view.customerInfoPool.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.customerPoollist',
    store: "CustomerInfoPool",
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
			{ text: '公司名称', dataIndex: 'companyName', flex:1.5},
			{ text: '联系人', dataIndex: 'contactPerson', flex:1},
			{ text: '联系方式', dataIndex: 'contactWay', flex:1.5},
			{ text: '所属事业部', dataIndex: 'business', flex:1.2,renderer:businessRenderer},
			{ text: '所属大区', dataIndex: 'regions'},
			{ text: '所属大区编码', dataIndex: 'regionsCode',xtype :'hiddenfield'},
			{ text: '省', dataIndex: 'provinceCode',xtype :'hiddenfield'},
			{ text: '市', dataIndex: 'cityCode' ,xtype :'hiddenfield'},
			{ text: '区', dataIndex: 'areaCode' ,xtype :'hiddenfield'},
			{ text: '详细地址', dataIndex: 'companyAddress', flex:2},
			{ text: '状态', dataIndex: 'dispenseStatus', flex:1,renderer:dispenseStatusRenderer},
			{ text: '负责人', dataIndex: 'managePerson', flex:1},
			{ text: '上传人部门', dataIndex: 'uploadDept', flex:1.2},
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

/**
 * 分发状态渲染功能
 * 
 * @param value
 * @returns
 * @author 何斌
 * @date 2015-6-23
 * @update
 */
function dispenseStatusRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_DISSTATE");
}

/**
 * 事业部渲染功能
 * 
 * @param value
 * @returns
 * @author 何斌
 * @date 2015-7-23
 * @update
 */
function businessRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMERINFOPOOL_BUSINESS");
}


/**
 * 公司地址渲染功能
 * 
 * @param value
 * @returns
 * @author 何斌
 * @date 2015-6-23
 * @update
 *//*
function companyAddressRenderer(v,meta,record){
	if(v==null||v==''){
		return record.get('province')+record.get('city')+record.get('area')+record.get('companyAddress');
	}
}*/