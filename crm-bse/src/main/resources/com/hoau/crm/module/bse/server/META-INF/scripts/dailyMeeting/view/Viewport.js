Ext.define("crm.view.Viewport", {
	id : 'dailyMeetingMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
//    defaults: 
//    {
//    	split: true,                  //是否有分割线
//     //   collapsible: false,           //是否可以折叠
//       // bodyStyle: 'padding:1px'  
//    },
    items: [{
        xtype:"dailyMeetingSearch"
    },{
        xtype:"dailyMeetingList"
    },{
    	xtype:"dailyMeetingrightPanel"
    },
//    {
//    	xtype:"dailyMeetingDetailsList"
//    }
    ],
    getDailyMeetingSearchForm : function(){
    	return this.items.get(0);
    },
    getDailyMeetingGrid : function(){
    	return this.items.get(1);
    },
    getDailyMeetingrightPanel : function(){
    	return this.items.get(2);
    },
//    getDailyMeetingDetailsList : function(){
//    	return this.items.get(3);
//    },
    tabChange : function(){
    	// 刷新数据
    	this.getDailyMeetingGrid().getStore().reload();
    }
}); 