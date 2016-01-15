/**
 * 资源需求Store
 */
Ext.define("crm.store.resDemand", {
    extend: "Ext.data.Store",
    model: "crm.model.resDemand",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'resDemandAction!queryResDemandAction.action',
        reader: {
            type: 'json',
            rootProperty: 'resDemandList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('resDemandMainViewId').getResDemandSearchForm().getForm();
			//查询条件
			var createUser=queryForm.findField('createUser').getValue();
			var solvetime=queryForm.findField('solvetime').getValue();
			var resources=queryForm.findField('resources').getValue();
			var createDate=queryForm.findField('createDate').getValue();
			var createEndDate=queryForm.findField('createEndDate').getValue();
			
			if (queryForm != null) {
				var params = {
						'resDemand.createUser' : createUser,
						'resDemand.solvetime' : solvetime,
						'resDemand.resources' : resources,
						'resDemand.createDate' : createDate,
						'resDemand.createEndDate' : createEndDate,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
