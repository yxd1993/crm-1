Ext.define("crm.view.Viewport", {
	id : 'chatsRandomViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"chatsRandomsearch"
    },{
        xtype:"chatsRandomlist"
    }],
    getChatsSearchForm : function(){
    	return this.items.get(0);
    },
    getChatsGrid : function(){
    	return this.items.get(1);
    }
});
