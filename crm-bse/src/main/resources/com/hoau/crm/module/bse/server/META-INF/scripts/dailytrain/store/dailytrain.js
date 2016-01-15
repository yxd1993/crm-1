/**
 * 资源需求Store
 */
Ext.define("crm.store.dailytrain", {
    extend: "Ext.data.Store",
    model: "crm.model.dailytrain",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'dailyTrainAction!queryDailyTrainAction.action',
        reader: {
            type: 'json',
            rootProperty: 'dailyTrainList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('dailytrainMainViewId').getDailytrainSearchForm().getForm();
			//查询条件
			var createUser=queryForm.findField('createUser').getValue();
			var hyDate=queryForm.findField('hyDate').getValue();
			var pxType=queryForm.findField('pxType').getValue();
			var hyaddress=queryForm.findField('hyaddress').getValue();
			
			if (queryForm != null) {
				var params = {
						'dailyTrain.createUser' : createUser,
						'dailyTrain.hyDate' : hyDate,
						'dailyTrain.pxType' : pxType,
						'dailyTrain.hyaddress' : hyaddress,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
