/**
 * 意向信息查询FORM
 */
Ext.define("crm.view.intention.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.intentionSearch',
    width : '100%',
    height : 80,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '10 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [{
        fieldLabel: '联系人姓名',
        name: 'contactName'
    },{
    	xtype : 'dataDictionarySelector',
		fieldLabel: '客户信用评分',
        name: 'customerScore',
        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null),
    },{
        fieldLabel: '客户企业全称',
        name: 'enterpriseName'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }, {
        text: '新增',
        action : 'add',
        hidden : isButtonHide('101001001')
    }, {
        text: '修改',
        action : 'update',
        hidden : isButtonHide('101001002')
//  }, {
//      text: '新增',
//      action : 'add',
//      hidden : isButtonHide('101001003')
//  }, {
//      text: '新增合同',
//      action : 'addContract',
//      hidden : isButtonHide('101001004')
    }]
});
