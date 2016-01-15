//window对象
var ChatsWin;
//全局变量,用于记录导出查询条件
var Chat ={}

Ext.application({
    name: "crm",
    appFolder: '../scripts/sales/chatsMain',
    controllers: ["Chats"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	 // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("103002", Ext.getCmp('chatsMainViewId'));
    	// 判断如果原来没有打开洽谈界面
    	Ext.getCmp('chatsMainViewId').tabChange();
    }
});
