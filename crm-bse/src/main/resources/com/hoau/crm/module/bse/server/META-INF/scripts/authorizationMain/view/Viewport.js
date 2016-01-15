Ext.define("crm.view.Viewport", {
	id : 'authorizationMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"authorizationSearch"
    },{
        xtype:"authorizationList"
    }],
    getAuthorizationSearchForm : function(){
    	return this.items.get(0);
    },
    getAuthorizationGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	this.getAuthorizationGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(AuthorizationWin){
        		// 关闭新增窗口
    			AuthorizationWin.close();
        		// 重新打开窗口
        		ShowAuthorizationWin();
        	} else {
        		// 重新打开窗口
        		ShowAuthorizationWin();
        	}
    	}
    }
}); 