/**
 * 预约信息查询FORM
 */
Ext.define("crm.view.reserve.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.reserveSearch',
    width : '100%',
    height :120,
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
        fieldLabel: '联系人姓名',
        name: 'contactName'
    },{
        fieldLabel: '约定时间',
        name: 'reserveStartTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'reserveEndTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
		fieldLabel: '客户企业全称',
	    name: 'enterpriseName'
    },{
    	xtype : "dataDictionarySelector",
		name : "reserveType",
		fieldLabel : "预约方式",
		store: getDataDictionary().getDataDictionaryStore('CUSTOMER_YXLX', null, null, null)
    },{
    	xtype : "combo",
		fieldLabel: '是否已赴约',
	    name: 'isAgreement',
	    store: [['N','未赴约'],['Y','已赴约']]
    },{
		fieldLabel: '创建人',
	    name: 'createUser'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }, {
        text: '新增',
        action : 'add'
    }, {
        text: '修改',
        action : 'update'
    }, {
        text: '作废',
        action : 'delete'
    }]
});
