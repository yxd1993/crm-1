Ext.define("crm.view.Viewport", {
	id : 'personalCustomerViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"personalCustomerSearch"
    },{
        xtype:"personalCustomerList"
    }],
    getPersonalCustomerSearchForm : function(){
    	return this.items.get(0);
    },
    getPersonalCustomerGrid : function(){
    	return this.items.get(1);
    }
});
