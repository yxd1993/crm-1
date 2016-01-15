Ext.define("crm.view.Viewport", {
	id : 'appVersionMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"appVersionSearch"
    },{
        xtype:"appVersionList"
    }],
    getAppVersionSearchForm : function(){
    	return this.items.get(0);
    },
    getAppVersionGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	this.getAppVersionGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(AppVersionWin){
        		// 关闭新增窗口
    			AppVersionWin.close();
        		// 重新打开窗口
        		ShowAppVersionWin();
        	} else {
        		// 重新打开窗口
        		ShowAppVersionWin();
        	}
    	}
    }
}); 