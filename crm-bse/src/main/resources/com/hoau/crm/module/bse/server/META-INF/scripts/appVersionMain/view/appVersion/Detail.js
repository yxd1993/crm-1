Ext.define("crm.view.appVersion.Detail", {
	id : 'appVersionDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.appVersionDetailWin",
	title : "App版本信息详情",
	width : 800,
	height : 200,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		autoScroll: true, 
		record : null,
		defaultType: 'textfield',
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.45,
			labelWidth : 115
		},
		defaults:{
			margin : '5 0 5 30 ',
    		readOnly : true
    	},
		items : [{
			xtype : "hiddenfield",
			name : "id"
		}, {
			name : "versionCode",
			fieldLabel : 'App版本号'
		}, {
			name : "apkName",
			fieldLabel : "APK名字"
		}, {
			name : "isMust",
			fieldLabel : "是否强制更新",
		}, {
			name : "isNow",
			fieldLabel : "是否当前版本"
		}, {
			name : "description",
			fieldLabel : '版本描述'
		}, {
			name : "createDate",
			fieldLabel : "版本创建时间"
		}],

	}
});
