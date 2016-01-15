/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.personalcustomer.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.personalCustomerSearch',
    width : '100%',
    height : 110,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '5 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [{
        fieldLabel: '渠道来源',
        name: 'source'
    },{
        fieldLabel: '用户名',
        name: 'userName'
    },{
        fieldLabel: '客户名称',
        name: 'enterpriseName'
    },{
        fieldLabel: '详细地址',
        name: 'detailedAddress'
    },{
        fieldLabel: '联系人',
        name: 'contactName'
    },{
        fieldLabel: '手机号',
        name: 'cellphone'
    },{
        fieldLabel: '座机号',
        name: 'telephone'
    },{
        fieldLabel: '邮箱',
        name: 'email'
    },
	{
		fieldLabel : '客户状态',
		name : 'isTurnCustomer',
		xtype : 'combo',
		store : getDataDictionary().getDataDictionaryStore('PERSONALCUSTOMER_STATUS', null, null, null),
		queryMode : 'local',
		displayField : 'valueName',
		valueField : 'valueCode',
		editable : false
	}],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    },{
    	text: '转为企业客户',
    	action : 'turnCustomer'
    }]
});
