Ext.define("crm.view.Viewport", {
	id : 'roleViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"roleSearch"
    },{
        xtype:"rolelist"
    }],
    getRoleSearchForm : function(){
    	return this.items.get(0);
    },
    getRoleListGrid : function(){
    	return this.items.get(1);
    }
});
