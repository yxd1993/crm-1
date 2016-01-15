Ext.define("crm.view.Viewport", {
	id : 'sweepSignMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"sweepSignSearch"
    },{
        xtype:"sweepSignList"
    },{
    	xtype:"imgPanel"
    }],
    getSweepSignSearchForm : function(){
    	return this.items.get(0);
    },
    getSweepSignGrid : function(){
    	return this.items.get(1);
    },
    getSweepSignImgGrid : function(){
    	return this.items.get(2);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getSweepSignGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(SweepSignWin){
        		// 关闭新增窗口
    			SweepSignWin.close();
        		// 重新打开窗口
        		ShowSweepSignWin();
        	} else {
        		// 重新打开窗口
        		ShowSweepSignWin();
        	}
    	}
    }
}); 