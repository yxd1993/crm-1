Ext.define("crm.view.Viewport", {
	id : 'serverConnectsViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"lookConnects"
    }],
    getLookConnectPanel : function(){
    	return this.items.get(0);
    },
});
