Ext.define("crm.view.role.Add", {
    extend: "Ext.window.Window",
    alias: "widget.roleInfoWin",
    title: "新增角色信息",
    width: 600,
    height: 200,
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
            { xtype: "hiddenfield", name: "id"},
            { xtype: "textfield", name: "roleCode", fieldLabel: "角色编码", maxLength : 64 },
            { xtype: "textfield", name: "roleName", fieldLabel: "角色名称", maxLength : 64 },
            { xtype: "textareafield", name: "roleDesc", fieldLabel: "角色描述", maxLength : 256, columnWidth : 0.9}
        ],
        buttons: [{
            text: '还原',
            action : 'reset'
        }, {
            text: '提交',
            action : 'submit'
        }]
    }
});
