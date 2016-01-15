Ext.define("crm.view.Viewport", {
	id : 'lookContractViewId',
    extend: "Ext.container.Viewport",
    layout: "fit",
    items: [{
        xtype:"lookContractForm"
    }],
    getLookContractForm : function(){
    	return this.items.get(0);
    }
});
