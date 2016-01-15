/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.message.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.messageSearch',
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
        fieldLabel: '消息类型',
        name: 'messageType',
        xtype : 'combo',
        store: getDataDictionary().getDataDictionaryStore('MESSAGE_TYPE', null, null, null),
        queryMode: 'local',
        displayField: 'valueName',
        valueField: 'valueCode',
        editable : false
    },{
        fieldLabel: '创建时间',
        name: 'startDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'endDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    }],
    buttons: [{
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }/*, {
        text: '广播',
        action : 'insert'
    }, {
        text: '单播',
        action : 'singleDevice'
    }*/]
});
