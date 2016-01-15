/**
 * 客户信息STORE
 */
Ext.define("crm.store.PersonalCustomer", {
    extend: "Ext.data.Store",
    model: "crm.model.PersonalCustomer",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'personalCustomerAction!queryPersonalCustomerInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'personalCustomers',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('personalCustomerViewId').getPersonalCustomerSearchForm().getForm();
			//查询条件
			var userName = queryForm.findField('userName').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var detailedAddress = queryForm.findField('detailedAddress').getValue();
			var contactName = queryForm.findField('contactName').getValue();
			var cellphone = queryForm.findField('cellphone').getValue();
			var telephone = queryForm.findField('telephone').getValue();
			var email = queryForm.findField('email').getValue();
			var source = queryForm.findField('source').getValue();
			var isTurnCustomer = queryForm.findField('isTurnCustomer').getValue();
			if (queryForm != null) {
				var params = {
						'personalCustomerEntity.userName' : userName,
						'personalCustomerEntity.enterpriseName' : enterpriseName,
						'personalCustomerEntity.detailedAddress' : detailedAddress,
						'personalCustomerEntity.contactName' : contactName,
						'personalCustomerEntity.cellphone' : cellphone,
						'personalCustomerEntity.telephone' : telephone,
						'personalCustomerEntity.email' : email,
						'personalCustomerEntity.source' : source,
						'personalCustomerEntity.isTurnCustomer' : isTurnCustomer
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});