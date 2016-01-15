Ext.define("crm.view.Viewport", {
	id : 'addIntentionViewId',
    extend: "Ext.container.Viewport",
    layout : 'fit',
    items: [{
        xtype:"addIntentionForm"
    }],
    getAddIntentionForm : function(){
    	return this.items.get(0);
    }
});
