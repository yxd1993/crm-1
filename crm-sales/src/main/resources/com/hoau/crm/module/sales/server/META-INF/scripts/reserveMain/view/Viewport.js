Ext.define("crm.view.Viewport", {
	id : 'reserveMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"reserveSearch"
    },{
        xtype:"reservelist"
    }],
    getReserveSearchForm : function(){
    	return this.items.get(0);
    },
    getReserveGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getReserveGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('ChartsInfo')){
    		if(ReserveWin){
        		// 关闭新增窗口
        		ReserveWin.close();
        		// 重新打开窗口
        		ShowReserveAddWin();
        	} else {
        		// 重新打开窗口
        		ShowReserveAddWin();
        	}
    	}else if(parent.allChildrenParamsMap.get('cId')){
    		if(ReserveWin){
        		// 关闭新增窗口
        		ReserveWin.close();
        		// 重新打开窗口
        		ShowReserveAddWin();
        	} else {
        		// 重新打开窗口
        		ShowReserveAddWin();
        	}
    	}
    }
});
