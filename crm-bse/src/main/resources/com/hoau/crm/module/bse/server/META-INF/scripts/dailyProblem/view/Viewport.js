Ext.define("crm.view.Viewport", {
	id : 'dailyProblemMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"dailyProblemSearch"
    },{
        xtype:"dailyProblemList"
    },{
    	xtype:"dailyProblemrightPanel"
    },
//    {
//    	xtype:"dailyProblemDetailsList"
//    }
    ],
    getDailyProblemSearchForm : function(){
    	return this.items.get(0);
    },
    getDailyProblemGrid : function(){
    	return this.items.get(1);
    },
    getDailyProblemrightPanel : function(){
    	return this.items.get(2);
    },
//    getDailyProblemDetailsList : function(){
//    	return this.items.get(3);
//    },
    tabChange : function(){
    	// 刷新数据
    	this.getDailyProblemGrid().getStore().reload();
    }
}); 