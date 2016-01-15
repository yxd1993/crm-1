Ext.define("crm.view.Viewport", {
	id : 'nearCustomerViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"nearCustomerSearch"
    }],
    getNearCustomerSearchForm : function(){
    	return this.items.get(0);
    }
});
