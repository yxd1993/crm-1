/**
 * 权限信息Store
 */
Ext.define("crm.store.Authorization", {
    extend: "Ext.data.Store",
    model: "crm.model.Authorization",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'authorizationAction!queryAuthorization.action',
        reader: {
            type: 'json',
            rootProperty: 'authorizationList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('authorizationMainViewId').getAuthorizationSearchForm().getForm();
			//查询条件
			var authorizedPerson=queryForm.findField('authorizedPerson').getValue();
			var wasAuthorizedPerson=queryForm.findField('wasAuthorizedPerson').getValue();
			var authorizedStartTime=queryForm.findField('authorizedStartTime').getValue();
			var authorizedEndTime=queryForm.findField('authorizedEndTime').getValue();
			
			if (queryForm != null) {
				var params = {
						'authorization.authorizedPerson' : authorizedPerson,
						'authorization.wasAuthorizedPerson' : wasAuthorizedPerson,
						'authorization.authorizedStartTime' : authorizedStartTime,
						'authorization.authorizedEndTime' : authorizedEndTime,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
