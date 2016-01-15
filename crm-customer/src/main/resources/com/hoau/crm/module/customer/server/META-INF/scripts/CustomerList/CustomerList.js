var initializeCustomerMask;
var Customer = {};

Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/CustomerList',
    controllers: ["Customer"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	// LOADMASK初始化
    	initializeCustomerMask = new Ext.LoadMask({
    	    msg    : '客户初始化中...',
    	    target : Ext.getCmp('customerViewId')
    	});
        // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("101001", Ext.getCmp('customerViewId'));
    	// 判断如果原来没有打开意向界面
    	Ext.getCmp('customerViewId').tabChange();
    }
});
