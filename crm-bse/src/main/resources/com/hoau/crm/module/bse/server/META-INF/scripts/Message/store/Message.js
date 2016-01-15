/**
 * 客户信息STORE
 */
Ext.define("crm.store.Message", {
    extend: "Ext.data.Store",
    model: "crm.model.Message",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'queryMessage.action',
        reader: {
            type: 'json',
            rootProperty: 'messageList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('messageViewId').getMessageSearchForm().getForm();
			//查询条件
			var messageType = queryForm.findField('messageType').getValue();
			var startDate = queryForm.findField('startDate').getValue();
			var endDate = queryForm.findField('endDate').getValue();
			if (queryForm != null) {
				var params = {
						'messageInfoVo.messageInfoEntity.messageType' : messageType,
						'messageInfoVo.startDate' : startDate,
						'messageInfoVo.endDate' : endDate
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});