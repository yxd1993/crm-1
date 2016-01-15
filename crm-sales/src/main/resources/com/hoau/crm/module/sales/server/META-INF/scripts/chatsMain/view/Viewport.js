Ext.define("crm.view.Viewport", {
	id : 'chatsMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"chatssearch"
    },{
        xtype:"chatslist"
    }],
    getChatsSearchForm : function(){
    	return this.items.get(0);
    },
    getChatsGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	//Ext.getCmp('chatsMainViewId').getChatsGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('cId')){
    		if(ChatsWin){
        		// 关闭新增窗口
        		ChatsWin.close();
        		// 重新打开窗口
        		ShowChatsAddWin();
        	} else {
        		// 重新打开窗口
        		ShowChatsAddWin();
        	}
    	}else if(parent.allChildrenParamsMap.get('reserveInfo')){
    		if(ChatsWin){
        		// 关闭新增窗口
        		ChatsWin.close();
        		// 重新打开窗口
        		ShowChatsAddWin();
        	} else {
        		// 重新打开窗口
        		ShowChatsAddWin();
        	}
    	}
    }
});
