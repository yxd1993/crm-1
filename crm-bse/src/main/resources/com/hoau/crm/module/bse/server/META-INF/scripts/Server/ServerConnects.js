Ext.application({
    name: "crm",
    appFolder: '../scripts/bse/Server',
    controllers: ["ServerConnects"],
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    	Ext.getCmp('serverConnectsViewId').getLookConnectPanel().doLayout();
    }
});
