Ext.application({
    name: "crm",
    appFolder: '../scripts/bse/Homepage',
    controllers: ["Homepage"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    	Ext.getCmp('homePageViewId').getHomePagePanel().doLayout();
    }
});
