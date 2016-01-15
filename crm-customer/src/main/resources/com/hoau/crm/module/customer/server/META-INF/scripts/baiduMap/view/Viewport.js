Ext.define("crm.view.Viewport", {
	id : 'userScopeViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"userScopeSearch"
    }],
    getUserScopeSearchForm : function(){
    	return this.items.get(0);
    }
});
