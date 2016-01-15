/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.customer.Search", {
	extend : "Ext.form.Panel",
	alias : 'widget.customerSearch',
	width : '100%',
	height : 110,
	layout : 'column',
	region : 'north',
	defaults : {
		msgTarget : 'qtip',
		margin : '5 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign : 'right'
	},
	defaultType : 'textfield',
	items : [
			{
				fieldLabel : 'DC账号',
				name : 'dcAccount'
			},
			{
				fieldLabel : '负责人',
				name : 'managePerson'
			},
			{
				fieldLabel : '手机',
				name : 'cellphone'
			},
			{
				fieldLabel : '所属行业',
				name : 'industryCode',
				xtype : 'combo',
				store : getDataDictionary().getDataDictionaryStore(
						'CUSTOMER_INDUSTRY', null, null, null),
				queryMode : 'local',
				displayField : 'valueName',
				valueField : 'valueCode',
				matchFieldWidth : false,
				editable : false
			},
			{
				fieldLabel : '客户性质',
				name : 'accountType',
				xtype : 'combo',
				store : getDataDictionary().getDataDictionaryStore(
						'CUSTOMER_NATURE', null, null, null),
				queryMode : 'local',
				displayField : 'valueName',
				valueField : 'valueCode',
				editable : false
			}, {
				fieldLabel : '大区名称',
				name : 'regionsName'
			}, {
				fieldLabel : '客户企业全称',
				name : 'enterpriseName'
			}, {
				fieldLabel : '门店代码',
				name : 'tierCode'
			}, {
				fieldLabel : '创建时间',
				name : 'startDate',
				xtype : 'datefield',
				format : 'Y-m-d',
				value : startDate
			}, {
				fieldLabel : '至',
				name : 'endDate',
				xtype : 'datefield',
				format : 'Y-m-d',
				value : endDate
			} ],
	buttons : [ 
/*	            {
					text : '附近客户',
					action : 'nearCustomer'
				}, */
//				{
//					text : '初始化客户坐标',
//					action : 'initializeCustomer'
//				},
				{
					text : '查询',
					action : 'select'
				}, {
					text : '清空',
					action : 'reset'
				}, {
					text : '新增',
					action : 'add',
					hidden : isButtonHide('101001001')
				}, {
					text : '修改',
					action : 'update',
					hidden : isButtonHide('101001002')
				}, {
					text : '删除',
					action : 'delete',
					hidden : isButtonHide('101001003')
				}, {
			        text: '转让',
			        action : 'transfer',
			        hidden : isButtonHide('101001016')
			    } ]
});
