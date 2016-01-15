// 为了解决权限树第一次打开加载两次问题，定义一个全局变量
var IS_FIRST = true;

Ext.application({
    name: "crm",
    appFolder: '../scripts/bse/Role',
    controllers: ["Role"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    }
});
