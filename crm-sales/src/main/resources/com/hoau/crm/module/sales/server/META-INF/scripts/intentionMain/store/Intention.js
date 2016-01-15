/**
 * 意向客户Store
 */
Ext.define("crm.store.Intention", {
    extend: "Ext.data.Store",
    model: "crm.model.Intention",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'intentionAction!queryIntentionInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'intentionList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('intentionMainViewId').getIntentionSearchForm().getForm();
			//查询条件
			var customerScore = queryForm.findField('customerScore').getValue();
			var contactName = queryForm.findField('contactName').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			if (queryForm != null) {
				var params = {
						'intentionVo.intentionEntity.customerScore' : customerScore,
						'intentionVo.intentionEntity.contactEntity.contactName' : contactName,
						'intentionVo.intentionEntity.customerEntity.enterpriseName' : enterpriseName
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});