Ext.define("crm.view.Viewport", {
	id : 'intentionMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"intentionSearch"
    },{
        xtype:"intentionlist"
    }],
    getIntentionSearchForm : function(){
    	return this.items.get(0);
    },
    getIntentionGrid : function(){
    	return this.items.get(1);
    }
});
