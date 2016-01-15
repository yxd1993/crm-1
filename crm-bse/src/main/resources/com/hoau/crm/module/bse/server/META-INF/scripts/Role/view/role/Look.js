Ext.define("crm.view.role.Look", {
    extend: "Ext.window.Window",
    alias: "widget.roleInfoLookWin",
    title: "查看角色信息",
    width: 600,
    height: 130,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        record : null, //客户信息
        fieldDefaults: {
            labelAlign: 'right',
            columnWidth : 0.45,
            margin : '5 0 5 0 ',
            labelWidth: 100,
            allowBlank : false
        },
        items: [
            { xtype: "displayfield", name: "roleCode", fieldLabel: "角色编码", maxLength : 64 },
            { xtype: "displayfield", name: "roleName", fieldLabel: "角色名称", maxLength : 64 },
            { xtype: "displayfield", name: "roleDesc", fieldLabel: "角色描述", maxLength : 256, columnWidth : 0.9}
        ]
    }
});
