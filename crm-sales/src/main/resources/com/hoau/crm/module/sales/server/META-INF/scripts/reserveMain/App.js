//全局变量
var ReserveWin;

Ext.application({
    name: "crm",
    appFolder: '../scripts/sales/reserveMain',
    controllers: ["Reserve"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("103001", Ext.getCmp('reserveMainViewId'));
    	// 判断如果原来没有打开预约界面
    	Ext.getCmp('reserveMainViewId').tabChange();
    }
});
