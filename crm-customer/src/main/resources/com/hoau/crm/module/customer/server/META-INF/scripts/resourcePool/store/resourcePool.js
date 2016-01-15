/**
 * 客户信息STORE
 */
Ext.define("crm.store.resourcePool", {
    extend: "Ext.data.Store",
    model: "crm.model.resourcePool",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'customerResourcePoolAction!queryCustomerResourcePools.action',
        reader: {
            type: 'json',
            rootProperty: 'customerResourcePools',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('resourcePoolViewId').getResourcePoolSearchForm().getForm();
			//查询条件
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var accountType = queryForm.findField('accountType').getValue();
			var industryCode = queryForm.findField('industryCode').getValue();
			var regions = queryForm.findField('regions').getValue();
			var contactName= queryForm.findField('contactName').getValue();
			var cellphone = queryForm.findField('cellphone').getValue();
			var address = queryForm.findField('address').getValue();
			var startDate = queryForm.findField('startDate').getValue();
			var endDate = queryForm.findField('endDate').getValue();
			if (queryForm != null) {
				var params = {
						'customerResourcePoolVo.customerResourcePoolEntity.industryCode' : industryCode,
						'customerResourcePoolVo.customerResourcePoolEntity.accountType':accountType,
						'customerResourcePoolVo.customerResourcePoolEntity.regions' : regions,
						'customerResourcePoolVo.customerResourcePoolEntity.enterpriseName' : enterpriseName,
						'customerResourcePoolVo.customerResourcePoolEntity.contactName' : contactName,
						'customerResourcePoolVo.customerResourcePoolEntity.cellphone':cellphone,
						'customerResourcePoolVo.customerResourcePoolEntity.address' : address,
						'customerResourcePoolVo.startDate' : startDate,
						'customerResourcePoolVo.endDate' : endDate
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});