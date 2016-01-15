/**
 * App版本信息Store
 */
Ext.define("crm.store.AppVersion", {
    extend: "Ext.data.Store",
    model: "crm.model.AppVersion",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'appVersionAction!queryAppVersionAction.action',
        reader: {
            type: 'json',
            rootProperty: 'appVersionList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('appVersionMainViewId').getAppVersionSearchForm().getForm();
			//查询条件
			var versionCode=queryForm.findField('versionCode').getValue();
			var apkName=queryForm.findField('apkName').getValue();
			var createDate=queryForm.findField('createDate').getValue();
			var createEndDate=queryForm.findField('createEndDate').getValue();
			
			if (queryForm != null) {
				var params = {
						'appVersion.versionCode' : versionCode,
						'appVersion.apkName' : apkName,
						'appVersion.createDate' : createDate,
						'appVersion.createEndDate' : createEndDate,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
