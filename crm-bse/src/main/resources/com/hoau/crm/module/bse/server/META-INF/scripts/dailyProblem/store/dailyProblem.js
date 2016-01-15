/**
 * 资源需求Store
 */
Ext.define("crm.store.dailyProblem", {
    extend: "Ext.data.Store",
    model: "crm.model.dailyProblem",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'dailyProblemAction!queryDailyProblemAction.action',
        reader: {
            type: 'json',
            rootProperty: 'dailyProblemList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('dailyProblemMainViewId').getDailyProblemSearchForm().getForm();
			//查询条件
			var createUser=queryForm.findField('createUser').getValue();
			var yltime=queryForm.findField('yltime').getValue();
			var ylway=queryForm.findField('ylway').getValue();
			var wtdescribe=queryForm.findField('wtdescribe').getValue();
			var jcdescribe=queryForm.findField('jcdescribe').getValue();
			
			if (queryForm != null) {
				var params = {
						'dailyProblem.createUser' : createUser,
						'dailyProblem.yltime' : yltime,
						'dailyProblem.ylway' : ylway,
						'dailyProblem.wtdescribe' : wtdescribe,
						'dailyProblem.jcdescribe' : jcdescribe,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
