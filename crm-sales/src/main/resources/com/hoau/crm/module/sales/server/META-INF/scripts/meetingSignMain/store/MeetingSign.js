/**
 * 会议签到Store
 */
Ext.define("crm.store.MeetingSign", {
    extend: "Ext.data.Store",
    model: "crm.model.MeetingSign",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'meetingSignAction1!queryMeetingSignAction.action',
        reader: {
            type: 'json',
            rootProperty: 'meetingSignList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('meetingSignMainViewId').getMeetingSignSearchForm().getForm();
			//查询条件
			var sweepPeopType=queryForm.findField('sweepPeopType').getValue();
			var sweepPeopName=queryForm.findField('sweepPeopName').getValue();
			//var wasSweepPeopName=queryForm.findField('wasSweepPeopName').getValue();
			var signAddress=queryForm.findField('signAddress').getValue();
			var sweepTime=queryForm.findField('sweepTime').getValue();
			var sweepEndTime=queryForm.findField('sweepEndTime').getValue();
			
			if (queryForm != null) {
				var params = {
						'meetingSign.sweepPeopType':sweepPeopType,
						'meetingSign.sweepPeopName' : sweepPeopName,
						 //'sweepSign.wasSweepPeopName' : wasSweepPeopName,
						'meetingSign.signAddress' : signAddress,
						'meetingSign.sweepTime' : sweepTime,
						'meetingSign.sweepEndTime' : sweepEndTime,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
			var imgHtml='<img src="" >';
			Ext.getCmp('meetingSignMainViewId').getMeetingSignImgGrid().getEl().update(imgHtml);
		}
	},
    autoLoad: true
});
