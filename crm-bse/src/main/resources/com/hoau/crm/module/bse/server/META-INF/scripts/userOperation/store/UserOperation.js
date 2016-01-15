/**
 * 客户操作Store
 */
Ext.define("crm.store.UserOperation", {
    extend: "Ext.data.Store",
    model: "crm.model.UserOperation",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'userOperationAction!queryUserOperationAction.action',
        reader: {
            type: 'json',
            rootProperty: 'userOperationList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('userOperationMainViewId').getUserOperationSearchForm().getForm();
			//查询条件
			var userOperationType=queryForm.findField('userOperationType').getValue();
			var userOperationName=queryForm.findField('userOperationName').getValue();
			var userOperationIp=queryForm.findField('userOperationIp').getValue();
			var userOperationTime=queryForm.findField('userOperationTime').getValue();
			var userOperationEndTime=queryForm.findField('userOperationEndTime').getValue();
			
			if (queryForm != null) {
				var params = {
						'userOperation.userOperationType' : userOperationType,
						'userOperation.userOperationName' : userOperationName,
						'userOperation.userOperationIp' : userOperationIp,
						'userOperation.userOperationTime' : userOperationTime,
						'userOperation.userOperationEndTime' : userOperationEndTime,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
