Ext.define("crm.view.Viewport", {
	id : 'homePageViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"homepageLook"
    }],
    getHomePagePanel : function(){
    	return this.items.get(0);
    },
});
