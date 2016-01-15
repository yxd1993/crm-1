Ext.define("crm.view.personalcustomer.contactWin", {
    extend: "Ext.window.Window",
    alias: "widget.personalCustomerContactListWin",
    title: "个人客户联系人信息",
    width: 600,
    height: 200,
    layout: "fit",
    autoScroll : true,
    bodyPadding: 5,
    items: [{
    	xtype:"personalCustomerContactPanel"
    }]
});