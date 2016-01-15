/**
 * 资源需求Store
 */
Ext.define("crm.store.meetingAttach", {
    extend: "Ext.data.Store",
    model: "crm.model.meetingAttach",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'dailyMeetingAction!queryMeetingAttachAction.action',
        reader: {
            type: 'json',
            rootProperty: 'attachmentEntities',
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var selectResult=Ext.getCmp('dailytrainMainViewId').getDailyMeetingGrid().getSelectionModel().getSelection();
			if (selectResult != null && selectResult[0] != null && selectResult[0].get('meetingSignId') != null) {
				var params = {};
				params.meetingSignId=selectResult[0].get('meetingSignId');
				Ext.apply(store.proxy.extraParams, params);  
			}
			
			var imgHtml='<img src="" >';
			Ext.getCmp('dailytrainMainViewId').getDailyMeetingrightPanel().getEl().update(imgHtml);
		}
	},
    autoLoad: false
});
