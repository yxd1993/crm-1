/**
 * 资源需求查询Form
 */
Ext.define("crm.view.dailyMeeting.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.dailyMeetingSearch',
    width : '100%',
    height : 75,
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
        fieldLabel: '创建人',
        name: 'createUser'
    },{
        fieldLabel: '会议时间',
        name: 'hyDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '会议类型',
        name: 'hyform',
        xtype : 'combo',
        store : getDataDictionary().getDataDictionaryStore(
				'DAILY_DHYLX', null, null, null),
		queryMode : 'local',
	    displayField : 'valueName',
		valueField : 'valueCode',
		editable : false,
		matchFieldWidth : false,
    },{
        fieldLabel: '会议形式',
        name: 'hyType',
        xtype : 'combo',
        store : getDataDictionary().getDataDictionaryStore(
				'DAILY_DHYXS', null, null, null),
		queryMode : 'local',
	    displayField : 'valueName',
		valueField : 'valueCode',
		editable : false,
		matchFieldWidth : false,				
    },{
        fieldLabel: '会议地址',
        name: 'hyaddress'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
