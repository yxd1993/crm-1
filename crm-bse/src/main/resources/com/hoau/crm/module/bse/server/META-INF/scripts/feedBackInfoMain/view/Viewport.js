Ext.define("crm.view.Viewport", {
	id : 'feedBackInfoMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"feedBackInfoSearch"
    },{
        xtype:"feedBackInfolist"
    }],
    getFeedBackInfoSearchForm : function(){
    	return this.items.get(0);
    },
    getFeedBackInfoGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	this.getFeedBackInfoGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(FeedBackInfoWin){
        		// 关闭新增窗口
    			FeedBackInfoWin.close();
        		// 重新打开窗口
        		ShowFeedBackInfoWin();
        	} else {
        		// 重新打开窗口
        		ShowFeedBackInfoWin();
        	}
    	}
    }
}); 
    
    