/**
 * 联系人信息STORE
 */
Ext.define("crm.store.Contact", {
    extend: "Ext.data.Store",
    model: "crm.model.Contact",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'customerAction!queryContactList.action',
        reader: {
            type: 'json',
            rootProperty: 'contactList',
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