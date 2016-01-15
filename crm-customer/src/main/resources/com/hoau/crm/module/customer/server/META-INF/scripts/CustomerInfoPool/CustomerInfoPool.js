//系统全局变量
var CustomerInfoPool = {};

Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/CustomerInfoPool',
    controllers: ["CustomerInfoPool"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    }
});
