/**
 * 洽谈信息查询FORM
 */
Ext.define("crm.view.chats.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.chatssearch',
    width : '100%',
    height : 120,
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
        fieldLabel: '洽谈时间',
        name: 'chatStartTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'chatEndTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '客户企业全称',
        name: 'enterpriseName'
    },{
    	xtype : "dataDictionarySelector",
		name : "chatType",
		fieldLabel : "洽谈方式",
		store: getDataDictionary().getDataDictionaryStore('CUSTOMER_YXLX', null, null, null)
    },{
        fieldLabel: '创建人',
        name: 'createUserName'
    },{
        fieldLabel: '门店代码',
        name: 'tierCode'
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
//    }, {
//        text: '删除',
//        action : 'delete'
    }, {
        text: '记录导出',
        action : 'export',
        hidden : isButtonHide('103002002')
    }]
});
