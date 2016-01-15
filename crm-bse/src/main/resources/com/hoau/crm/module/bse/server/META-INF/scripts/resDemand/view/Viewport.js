Ext.define("crm.view.Viewport", {
	id : 'resDemandMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"resDemandSearch"
    },{
        xtype:"resDemandList"
    }],
    getResDemandSearchForm : function(){
    	return this.items.get(0);
    },
    getResDemandGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	this.getResDemandGrid().getStore().reload();
    }
}); 