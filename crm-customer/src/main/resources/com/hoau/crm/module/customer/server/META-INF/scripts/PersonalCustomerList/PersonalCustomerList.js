//系统全局变量
var PersonalCustomer = {};

Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/PersonalCustomerList',
    controllers: ["PersonalCustomer"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    }
});
