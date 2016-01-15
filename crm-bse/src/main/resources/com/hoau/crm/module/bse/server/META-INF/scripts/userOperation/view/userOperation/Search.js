/**
 * 客户操作信息查询Form
 */
Ext.define("crm.view.userOperation.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.userOperationSearch',
    width : '100%',
    height : 80,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '10 0 5 0 ',
		labelWidth : 100,
		columnWidth :  0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',	
    items: [{
		xtype:"dataDictionarySelector",
    	fieldLabel: '客户操作类型',
	    name: 'userOperationType',
	    store: getDataDictionary().getDataDictionaryStore('OPER_CUSTOMER_TYPE', null, null, null)
    },{
        fieldLabel: '客户姓名',
        name: 'userOperationName'
    },{
        fieldLabel: '客户IP',
        name: 'userOperationIp'
    },{
        fieldLabel: '操作开始时间',
        name: 'userOperationTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至操作结束时间',
        name: 'userOperationEndTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
