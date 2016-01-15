/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.resourcePool.Search", {
	extend : "Ext.form.Panel",
	alias : 'widget.resourcePoolSearch',
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
				fieldLabel : '客户名称',
				name : 'enterpriseName'
			},{
				fieldLabel : '客户性质',
				name : 'accountType',
				xtype : 'combo',
				store : getDataDictionary().getDataDictionaryStore(
						'CUSTOMER_NATURE', null, null, null),
				queryMode : 'local',
				displayField : 'valueName',
				valueField : 'valueCode',
				editable : false
			},{
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
			},{
				fieldLabel : '所属大区',
				name : 'regions'
			},{
				fieldLabel : '联系人姓名',
				name : 'contactName'
			},{
				fieldLabel: '联系手机',
				name:'cellphone',
				blankText:'输入完整手机号'
			},{
				fieldLabel : '联系地址',
				name : 'address'
			},{
				fieldLabel : '流入时间从',
				name : 'startDate',
				xtype : 'datefield',
				format : 'Y-m-d',
				value : startDate
			},{
				fieldLabel : '到',
				name : 'endDate',
				xtype : 'datefield',
				format : 'Y-m-d',
				value : endDate
			} ],
			buttons : [{
					text : '查询',
					action : 'select'
				}, {
					text : '清空',
					action : 'reset'
				}, {
					text : '认领',
					action : 'claim',
					hidden : isButtonHide('101002002')
				} ]
});
