Ext.define("crm.view.Viewport", {
	id : 'messageViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"messageSearch"
    },{
        xtype:"messagelist"
    }],
    getMessageSearchForm : function(){
    	return this.items.get(0);
    },
    getMessageGrid : function(){
    	return this.items.get(1);
    }
});
