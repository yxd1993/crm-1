/**
 * 用户反馈Store
 */
Ext.define("crm.store.FeedBackInfo", {
    extend: "Ext.data.Store",
    model: "crm.model.FeedBackInfo",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'feedBackInfo!queryFeedBackInfo.action',
        reader: {
            type: 'json',
            rootProperty: 'feedBackInfolist',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('feedBackInfoMainViewId').getFeedBackInfoSearchForm().getForm();
			//查询条件
			var userName=queryForm.findField('userName').getValue();
			var title=queryForm.findField('fbTitle').getValue();
			var feedBackInfoType=queryForm.findField('fbType').getValue();
			var content=queryForm.findField('fbContent').getValue();
			var feedBeginTime=queryForm.findField('fbTime').getValue();
			var feedEndTime=queryForm.findField('fbEndTime').getValue();
			if (queryForm != null) {
				var params = {
						'feedBackInfo.userName' : userName,
						'feedBackInfo.fbTitle' : title,
						'feedBackInfo.fbType' : feedBackInfoType,
						'feedBackInfo.fbContent' : content,
						'feedBackInfo.fbTime' : feedBeginTime,
						'feedBackInfo.fbEndTime' :feedEndTime
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});
