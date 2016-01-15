/**
 * 联系人信息STORE
 */
Ext.define("crm.store.History", {
    extend: "Ext.data.Store",
    model: "crm.model.History",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'customerAction!queryHistory.action',
        reader: {
            type: 'json',
            rootProperty: 'historyList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var params = {
					'customerEntity.id' : cId
				}
			Ext.apply(store.proxy.extraParams, params);  
		}
	},
	autoLoad : true,
});