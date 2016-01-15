/**
 * 用户反馈信息查询Form
 */
Ext.define("crm.view.feedBackInfo.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.feedBackInfoSearch',
    width : '100%',
    height : 120,
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
    	fieldLabel: '反馈类型',
	    name: 'fbType',
	    store: getDataDictionary().getDataDictionaryStore('FEEDBACK_TYPE', null, null, null)
    },{
        fieldLabel: '反馈主题',
        name: 'fbTitle',
    },{
        fieldLabel: '反馈内容',
        name: 'fbContent',
    },{
        fieldLabel: '反馈开始时间',
        name: 'fbTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至反馈结束时间',
        name: 'fbEndTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '反馈用户姓名',
        name: 'userName'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
