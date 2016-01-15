/**
 * 客户信息STORE
 */
Ext.define("crm.store.Customer", {
    extend: "Ext.data.Store",
    model: "crm.model.Customer",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'customerAction!queryCustomeInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'customerList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('customerViewId').getCustomerSearchForm().getForm();
			//查询条件
			var dcAccount = queryForm.findField('dcAccount').getValue();
			var cellphone = queryForm.findField('cellphone').getValue();
			var industryCode = queryForm.findField('industryCode').getValue();
			var accountType = queryForm.findField('accountType').getValue();
			var regionsName = queryForm.findField('regionsName').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var managePerson= queryForm.findField('managePerson').getValue();
			var tierCode = queryForm.findField('tierCode').getValue();
			var startDate = queryForm.findField('startDate').getValue();
			var endDate = queryForm.findField('endDate').getValue();
			if (queryForm != null) {
				var params = {
						'customerVo.customerEntity.dcAccount' : dcAccount,
						'customerVo.customerEntity.contactEntity.cellphone' : cellphone,
						'customerVo.customerEntity.industryCode' : industryCode,
						'customerVo.customerEntity.accountType' : accountType,
						'customerVo.customerEntity.regionsName' : regionsName,
						'customerVo.customerEntity.enterpriseName' : enterpriseName,
						'customerVo.customerEntity.managePerson' : managePerson,
						'customerVo.customerEntity.tierCode' : tierCode,
						'customerVo.startDate' : startDate,
						'customerVo.endDate' : endDate
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});