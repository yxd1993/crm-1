/**
 *  洽谈回访随机抽取查询FORM
 */
Ext.define("crm.view.chatsRandom.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.chatsRandomsearch',
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
        fieldLabel: '客户全称',
        name: 'customerName'
    },{
        fieldLabel: '联系人手机号',
        name: 'cellphone'
    },{
        fieldLabel: '开始时间',
        name: 'startDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
       fieldLabel: '结束时间',
       name: 'endDate',
       xtype : 'datefield',
		format : 'Y-m-d'
    },{
    	xtype : "dataDictionarySelector",
		name : "chatType",
		fieldLabel : "洽谈方式",
		store: getDataDictionary().getDataDictionaryStore('CUSTOMER_YXLX', null, null, null)
    },{
    	xtype : "dataDictionarySelector",
        fieldLabel: '审核状态',
        name: 'status',
    	store: getDataDictionary().getDataDictionaryStore('RANDOM_CHAT_STATUS', null, null, null)
    },{
        fieldLabel: '创建人',
        name: 'createUserName'
    },{
        fieldLabel: '部门',
        name: 'createUserDept'
    }],
    buttons: [ {
        text: '查询',
        action : 'search'
     },{
        text: '清空',
        action : 'reset'
     },{
     	text: '审核',
        action : 'visit',
        hidden : isButtonHide('103007002')
     }
    //,{
      //  text: '记录导出',
      //  action : 'export',
      //  hidden : isButtonHide('103002002')
      //}
    ]
});
