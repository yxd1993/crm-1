Ext.define("crm.view.Viewport", {
	id : 'customerViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"customerSearch"
    },{
        xtype:"customerlist"
    }],
    getCustomerSearchForm : function(){
    	return this.items.get(0);
    },
    getCustomerGrid : function(){
    	return this.items.get(1);
    },
    tabChange : function(){
    	// 刷新数据
    	if(parent.allChildrenParamsMap.get('success')){
    		this.getCustomerGrid().getStore().reload();
    	}
    	parent.allChildrenParamsMap.put('success', undefined)
    	if(parent.allChildrenParamsMap.get('accountId')){
    		this.getCustomerGrid().getStore().reload();
    	}
    	parent.allChildrenParamsMap.put('accountId', undefined)
    }
});
