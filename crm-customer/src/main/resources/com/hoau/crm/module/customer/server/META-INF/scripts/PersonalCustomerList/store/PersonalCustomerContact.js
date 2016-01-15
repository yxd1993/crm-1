/**
 * 客户信息STORE
 */
Ext.define("crm.store.PersonalCustomerContact", {
    extend: "Ext.data.Store",
    model: "crm.model.PersonalCustomerContact",
    pageSize : 100,
    proxy: {
        type: 'ajax',
        url: 'personalCustomerAction!queryPersonalCustomerContactInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'personalCustomerContacts'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			//查询条件
			var userId = PersonalCustomer.sourceId;
			if (userId != null) {
				var params = {
						'personalCustomerContactEntity.userId' : userId
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});