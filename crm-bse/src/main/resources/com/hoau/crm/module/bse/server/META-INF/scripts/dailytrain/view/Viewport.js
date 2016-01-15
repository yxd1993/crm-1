Ext.define("crm.view.Viewport", {
	id : 'dailytrainMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"dailytrainSearch"
    },{
        xtype:"dailytrainList"
    },{
    	xtype:"dailytrainrightPanel"
    }
//    ,{
//    	xtype:"dailytrainDetailsList"
//    }
    ],
    getDailytrainSearchForm : function(){
    	return this.items.get(0);
    },
    getDailytrainGrid : function(){
    	return this.items.get(1);
    },
    getDailytrainrightPanel : function(){
    	return this.items.get(2);
    },
//    getDailytrainDetailsList : function(){
//    	return this.items.get(3);
//    },
    tabChange : function(){
    	// 刷新数据
    	this.getDailytrainGrid().getStore().reload();
    }
}); 