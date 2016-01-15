/**
 *  随机抽取洽谈回访Store
 */
Ext.define("crm.store.ChatsRandom", {
    extend: "Ext.data.Store",
    model: "crm.model.ChatsRandom",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../sales/saleChatsAction!getRandomChatInfos.action',
        reader: {
            type: 'json',
            rootProperty: 'saleChatRandomInfos',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('chatsRandomViewId').getChatsSearchForm().getForm();
			//查询条件
			var customerName = queryForm.findField('customerName').getValue();//客户全称
			var cellphone = queryForm.findField('cellphone').getValue();   //手机
			var startDate = queryForm.findField('startDate').getValue();  //创建开始时间
			var endDate = queryForm.findField('endDate').getValue();  //创建结束时间
			var chatType = queryForm.findField('chatType').getValue();		//洽谈方式
			var status = queryForm.findField('status').getValue();     //状态
			var createUserName = queryForm.findField('createUserName').getValue();  //创建人
			var createUserDept = queryForm.findField('createUserDept').getValue();   //部门
			if (queryForm != null) {
				var params = {
						'saleChatRandomVo.saleChatRandomEntity.customerName' : customerName,
						'saleChatRandomVo.saleChatRandomEntity.cellphone' : cellphone,
						'saleChatRandomVo.saleChatRandomEntity.chatType' : chatType,
						'saleChatRandomVo.startDate' : startDate,
						'saleChatRandomVo.endDate' : endDate,
						'saleChatRandomVo.saleChatRandomEntity.status' : status,
						'saleChatRandomVo.saleChatRandomEntity.createUserName' : createUserName,
						'saleChatRandomVo.saleChatRandomEntity.createUserDept':createUserDept
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});