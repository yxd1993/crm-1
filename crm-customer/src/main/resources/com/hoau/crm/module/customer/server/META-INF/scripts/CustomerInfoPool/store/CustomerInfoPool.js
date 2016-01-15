/**
 * 客户信息STORE
 */
Ext.define("crm.store.CustomerInfoPool", {
    extend: "Ext.data.Store",
    model: "crm.model.CustomerInfoPool",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'customerInfoPoolAction!queryCustomerInfoPool.action',
        reader: {
            type: 'json',
            rootProperty: 'customerInfoPoolList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolSearchForm().getForm();
			//查询条件
			var uploadDept = queryForm.findField('uploadDept').getValue();
			var companyName = queryForm.findField('companyName').getValue();
			var contactPerson = queryForm.findField('contactPerson').getValue();
			var contactWay = queryForm.findField('contactWay').getValue();
			var regions = queryForm.findField('regions').getValue();
			var managePerson = queryForm.findField('managePerson').getValue();
			var dispenseStatus = queryForm.findField('dispenseStatus').getValue();
			var companyAddress = queryForm.findField('companyAddress').getValue();
			var startDate = queryForm.findField('startDate').getValue();
			var endDate = queryForm.findField('endDate').getValue();
			// 为了导出功能准确性，条件赋值给全局变量
			CustomerInfoPool.uploadDept = uploadDept;
			CustomerInfoPool.companyName = companyName;
			CustomerInfoPool.contactPerson = contactPerson;
			CustomerInfoPool.contactWay = contactWay;
			CustomerInfoPool.regions = regions;
			CustomerInfoPool.managePerson = managePerson;
			CustomerInfoPool.companyAddress = companyAddress;
			CustomerInfoPool.dispenseStatus = dispenseStatus;
			CustomerInfoPool.startDate = startDate;
			CustomerInfoPool.endDate = endDate;
			if (queryForm != null) {
				var params = {
						'customerInfoPoolVo.customerInfoPoolEntity.employeeEntity.deptname' : uploadDept,
						'customerInfoPoolVo.customerInfoPoolEntity.companyName' : companyName,
						'customerInfoPoolVo.customerInfoPoolEntity.contactPerson' : contactPerson,
						'customerInfoPoolVo.customerInfoPoolEntity.contactWay' : contactWay,
						'customerInfoPoolVo.customerInfoPoolEntity.regions' : regions,
						'customerInfoPoolVo.customerInfoPoolEntity.managePerson' : managePerson,
						'customerInfoPoolVo.customerInfoPoolEntity.dispenseStatus' : dispenseStatus,
						'customerInfoPoolVo.customerInfoPoolEntity.companyAddress' : companyAddress,
						'customerInfoPoolVo.startDate' : startDate,
						'customerInfoPoolVo.endDate' : endDate
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});