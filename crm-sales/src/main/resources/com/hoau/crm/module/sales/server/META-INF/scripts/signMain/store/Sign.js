/**
 * 客户签到Store
 */
Ext.define("crm.store.Sign", {
    extend: "Ext.data.Store",
    model: "crm.model.Sign",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'signAction!querySignAction.action',
        reader: {
            type: 'json',
            rootProperty: 'signList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('signMainViewId').getSignSearchForm().getForm();
			//查询条件
			var customerName=queryForm.findField('customerName').getValue();
			var signAddress=queryForm.findField('signAddress').getValue();
			var createDate=queryForm.findField('createDate').getValue();
			var createEndDate=queryForm.findField('createEndDate').getValue();
			
			if (queryForm != null) {
				var params = {
						'sign.customerName' : customerName,
						'sign.signAddress' : signAddress,
						'sign.createDate' : createDate,
						'sign.createEndDate' : createEndDate,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
			var imgHtml='<img src="" >';
			Ext.getCmp('signMainViewId').getSignImgGrid().getEl().update(imgHtml);
		}
	},
    autoLoad: true
});
