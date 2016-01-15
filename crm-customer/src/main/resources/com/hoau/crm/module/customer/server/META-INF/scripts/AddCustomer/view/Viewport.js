Ext.define("crm.view.Viewport", {
	id : 'addCustomerViewId',
    extend: "Ext.container.Viewport",
    layout : 'fit',
    items: [{
        xtype:"addCustomerForm"
    }],
    getAddCustomerForm : function(){
    	return this.items.get(0);
    },
    getContactGrid : function(){
    	return this.items.get(0).items.get(3).items.get(0);
    }
});
