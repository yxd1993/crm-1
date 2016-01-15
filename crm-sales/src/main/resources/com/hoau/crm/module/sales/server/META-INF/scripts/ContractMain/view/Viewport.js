Ext.define("crm.view.Viewport", {
	id : 'contractMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"contractSearch"
    },{
        xtype:"contractList"
    }],
    getContractSearchForm : function(){
    	return this.items.get(0);
    },
    getContractGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getContractGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(ContractAddWin){
        		// 关闭新增窗口
    			ContractAddWin.close();
        		// 重新打开窗口
        		ShowContractAddWin();
        	} else {
        		// 重新打开窗口
        		ShowContractAddWin();
        	}
    	}
    }
});
