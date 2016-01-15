Ext.define("crm.view.Viewport", {
	id : 'lookCustomerViewId',
    extend: "Ext.container.Viewport",
    layout : 'fit',
    items: [{
        xtype:"lookCustomForm"
    }],
    getAddCustomerForm : function(){
    	return this.items.get(0);
    },
    tabChange : function(){
    	if(parent.allChildrenParamsMap.get('accountId')){
    		if(intentionWin){
        		// 关闭新增窗口
    			intentionWin.close();
        		// 重新打开窗口
        		turnToIntention();
        	} else {
        		// 重新打开窗口
        		turnToIntention();
        	}
    	}
    }
});
