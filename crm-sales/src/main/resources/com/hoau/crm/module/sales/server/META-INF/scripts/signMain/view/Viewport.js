Ext.define("crm.view.Viewport", {
	id : 'signMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"signSearch"
    },{
    	xtype:"signList"
    },{
    	xtype:"imgPanel"
    }],
    getSignSearchForm : function(){
    	return this.items.get(0);
    },
    getSignGrid : function(){
    	return this.items.get(1);
    },
    getSignImgGrid : function(){
    	return this.items.get(2);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getSignGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(SignWin){
        		// 关闭新增窗口
    			SignWin.close();
        		// 重新打开窗口
        		ShowSignWin();
        	} else {
        		// 重新打开窗口
        		ShowSignWin();
        	}
    	}
    }
}); 