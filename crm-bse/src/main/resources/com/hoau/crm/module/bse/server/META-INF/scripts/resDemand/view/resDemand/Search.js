/**
 * 资源需求查询Form
 */
Ext.define("crm.view.resDemand.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.resDemandSearch',
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
        fieldLabel: '期望解决时间',
        name: 'solvetime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '需求资源',
        name: 'resources'
    },{
        fieldLabel: '创建时间',
        name: 'createDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至结束时间',
        name: 'createEndDate',
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
