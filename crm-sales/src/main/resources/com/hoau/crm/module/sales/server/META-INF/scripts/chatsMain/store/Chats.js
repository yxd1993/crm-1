/**
 * 洽谈客户Store
 */
Ext.define("crm.store.Chats", {
    extend: "Ext.data.Store",
    model: "crm.model.Chats",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../sales/saleChatsAction!queryChatsByPageing.action',
        reader: {
            type: 'json',
            rootProperty: 'chatsList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('chatsMainViewId').getChatsSearchForm().getForm();
			//查询条件
			var contactName = queryForm.findField('contactName').getValue();   //联系人姓名
			var chatType = queryForm.findField('chatType').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var chatStartTime = queryForm.findField('chatStartTime').getValue();
			var chatEndTime = queryForm.findField('chatEndTime').getValue();
			var createUserName = queryForm.findField('createUserName').getValue();
			var tierCode = queryForm.findField('tierCode').getValue();
			// 为了导出功能准确性，条件赋值给全局变量
			Chat.enterpriseName = enterpriseName;
			Chat.contactName = contactName;
			Chat.chatType = chatType;
			Chat.chatStartTime = chatStartTime;
			Chat.chatEndTime = chatEndTime;
			Chat.createUserName = createUserName;
			Chat.tierCode = tierCode;
			if (queryForm != null) {
				var params = {
						'chatsVo.chatsEntity.contactEntity.contactName' : contactName,
						'chatsVo.chatsEntity.chatType' : chatType,
						'chatsVo.chatsEntity.customerEntity.enterpriseName' : enterpriseName,
						'chatsVo.chatsEntity.customerEntity.tierCode' : tierCode,
						'chatsVo.chatsEntity.chatStartTime' : chatStartTime,
						'chatsVo.chatsEntity.chatEndTime' : chatEndTime,
						'chatsVo.createUserName':createUserName
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});