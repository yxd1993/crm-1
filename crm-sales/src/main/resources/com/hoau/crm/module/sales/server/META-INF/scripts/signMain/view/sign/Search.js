/**
 * 客户签到信息查询Form
 */
Ext.define("crm.view.sign.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.signSearch',
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
        fieldLabel: '签到人',
        name: 'customerName'
    },{
        fieldLabel: '签到地址',
        name: 'signAddress'
    },{
        fieldLabel: '签到开始时间',
        name: 'createDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至签到结束时间',
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
