/**
 * 授权信息查询Form
 */
Ext.define("crm.view.authorization.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.authorizationSearch',
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
        fieldLabel: '授权人工号',
        name: 'authorizedPerson'
    },{
        fieldLabel: '被授权人工号',
        name: 'wasAuthorizedPerson'
    },{
        fieldLabel: '授权开始时间',
        name: 'authorizedStartTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '授权结束时间',
        name: 'authorizedEndTime',
        xtype : 'datefield',
		format : 'Y-m-d'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }, {
        text: '删除',
        action : 'delete',
        hidden : isButtonHide('101005003')
    },{
        text: '新增',
        action : 'add',
        hidden : isButtonHide('101005001')
    },{
        text: '修改',
        action : 'update'
      }
    ]
});
