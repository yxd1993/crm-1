/**
 * 合同Store
 */
Ext.define("crm.store.Contract", {
    extend: "Ext.data.Store",
    model: "crm.model.Contract",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'saleContractAction!querySaleContractInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'saleContractList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('contractMainViewId').getContractSearchForm().getForm();
			//查询条件
			var status = queryForm.findField('status').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var workflowCode = queryForm.findField('workflowCode').getValue();
			var crmAccount = queryForm.findField('crmAccount').getValue();
			var dcAccount = queryForm.findField('dcAccount').getValue();
			var firstStartDate = queryForm.findField('firstStartDate').getValue();
			var firstEndDate = queryForm.findField('firstEndDate').getValue();
			var secondStartDate = queryForm.findField('secondStartDate').getValue();
			var secondEndDate = queryForm.findField('secondEndDate').getValue();
			if (queryForm != null) {
				var params = {
						'saleContractVo.status' : status,
						'saleContractVo.enterpriseName' : enterpriseName,
						'saleContractVo.workflowCode' : workflowCode,
						'saleContractVo.crmAccount' : crmAccount,
						'saleContractVo.dcAccount' : dcAccount,
						'saleContractVo.firstStartDate' : firstStartDate,
						'saleContractVo.firstEndDate' : firstEndDate,
						'saleContractVo.secondStartDate' : secondStartDate,
						'saleContractVo.secondEndDate' : secondEndDate
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});