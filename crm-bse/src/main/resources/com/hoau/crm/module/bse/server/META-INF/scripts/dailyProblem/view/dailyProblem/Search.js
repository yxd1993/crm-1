/**
 * 资源需求查询Form
 */
Ext.define("crm.view.dailyProblem.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.dailyProblemSearch',
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
        fieldLabel: '演练时间',
        name: 'yltime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '演练方式',
        name: 'ylway',
        xtype : 'combo',
        store : getDataDictionary().getDataDictionaryStore(
				'DAILY_DYLXS', null, null, null),
		queryMode : 'local',
        displayField : 'valueName',
		valueField : 'valueCode',
		editable : false,
		matchFieldWidth : false
    },{
        fieldLabel: '问题描述',
        name: 'wtdescribe'
    },{
        fieldLabel: '举措描述',
        name: 'jcdescribe'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
