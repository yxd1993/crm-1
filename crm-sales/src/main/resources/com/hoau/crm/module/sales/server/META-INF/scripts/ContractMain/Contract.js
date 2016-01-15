var ContractAddWin; 

Ext.application({
    name: "crm",
    appFolder: '../scripts/sales/ContractMain',
    controllers: ["Contract"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("103004", Ext.getCmp('contractMainViewId'));
    	// 判断如果原来没有打开新增合同界面
    	Ext.getCmp('contractMainViewId').tabChange();
    }
});
