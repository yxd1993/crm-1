Ext.define("crm.view.Viewport", {
	id : 'customerInfoPoolViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"customerPoolSearch"
    },{
        xtype:"customerPoollist"
    }],
    getCustomerInfoPoolSearchForm : function(){
    	return this.items.get(0);
    },
    getCustomerInfoPoolGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getCustomerInfoPoolGrid().getStore().reload();
    }
});
