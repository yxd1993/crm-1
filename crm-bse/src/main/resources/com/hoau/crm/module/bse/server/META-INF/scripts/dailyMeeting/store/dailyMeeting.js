/**
 * 资源需求Store
 */
Ext.define("crm.store.dailyMeeting", {
    extend: "Ext.data.Store",
    model: "crm.model.dailyMeeting",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'dailyMeetingAction!queryDailyMeetingAction.action',
        reader: {
            type: 'json',
            rootProperty: 'dailyMeetingList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingSearchForm().getForm();
			//查询条件
			var createUser=queryForm.findField('createUser').getValue();
			var hyDate=queryForm.findField('hyDate').getValue();
			var hyform=queryForm.findField('hyform').getValue();
			var hyType=queryForm.findField('hyType').getValue();
			var hyaddress=queryForm.findField('hyaddress').getValue();
			
			if (queryForm != null) {
				var params = {
						'dailyMeeting.createUser' : createUser,
						'dailyMeeting.hyDate' : hyDate,
						'dailyMeeting.hyform' : hyform,
						'dailyMeeting.hyType' : hyType,
						'dailyMeeting.hyaddress' : hyaddress,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
