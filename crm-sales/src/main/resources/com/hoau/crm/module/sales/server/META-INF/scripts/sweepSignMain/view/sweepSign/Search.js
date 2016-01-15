/**
 * 扫描签到信息查询Form
 */
Ext.define("crm.view.sweepSign.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.sweepSignSearch',
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
        fieldLabel: '扫描客户姓名',
        name: 'sweepPeopName'
    },{
        fieldLabel: '被扫描客户姓名',
        name: 'wasSweepPeopName'
    },{
        fieldLabel: '扫描签到地址',
        name: 'signAddress'
    },{
        fieldLabel: '扫描开始时间',
        name: 'sweepTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至扫描结束时间',
        name: 'sweepEndTime',
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
