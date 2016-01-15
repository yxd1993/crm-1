Ext.define("crm.view.Viewport", {
	id : 'resourcePoolViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"resourcePoolSearch"
    },{
        xtype:"resourcePoollist"
    }],
    getResourcePoolSearchForm : function(){
    	return this.items.get(0);
    },
    getResourcePoolGrid : function(){
    	return this.items.get(1);
    }
});
