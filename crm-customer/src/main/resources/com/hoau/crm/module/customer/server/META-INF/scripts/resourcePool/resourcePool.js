var initializeCustomerMask; 

Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/resourcePool',
    controllers: ["resourcePool"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	// LOADMASK初始化
    	initializeCustomerMask = new Ext.LoadMask({
    	    msg    : '客户池初始化中...',
    	    target : Ext.getCmp('resourcePoolViewId')
    	});
        // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("101001", Ext.getCmp('resourcePoolViewId'));
    	// 判断如果原来没有打开意向界面
    }
});
