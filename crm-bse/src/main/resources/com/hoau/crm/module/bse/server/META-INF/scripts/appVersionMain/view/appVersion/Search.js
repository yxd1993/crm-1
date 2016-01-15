/**
 * App版本信息查询Form
 */
Ext.define("crm.view.appVersion.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.appVersionSearch',
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
        fieldLabel: 'App版本号',
        name: 'versionCode'
    },{
        fieldLabel: 'APK名字',
        name: 'apkName'
    },{
        fieldLabel: '创建版本时间',
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
    }, {
        text: '删除',
        action : 'delete',
        hidden : isButtonHide('101005003')
    },{
        text: '新增',
        action : 'add',
        hidden : isButtonHide('101005001')
    },{
    	text: '设为当前版本',
        action : 'updateIsNow',
        hidden : isButtonHide('101005002')
    }/*,{
    	text: '下载当前版本',
    	hidden:true,
        action : 'export',
        hidden : isButtonHide('101005002')
    }*/]
});
