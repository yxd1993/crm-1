Ext.define("crm.view.Viewport", {
	id : 'userOperationMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"userOperationSearch"
    },{
        xtype:"userOperationList"
    }],
    getUserOperationSearchForm : function(){
    	return this.items.get(0);
    },
    getUserOperationGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	this.getUserOperationGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(UserOperationWin){
        		// 关闭新增窗口
    			UserOperationWin.close();
        		// 重新打开窗口
        		ShowUserOperationWin();
        	} else {
        		// 重新打开窗口
        		ShowUserOperationWin();
        	}
    	}
    }
}); 