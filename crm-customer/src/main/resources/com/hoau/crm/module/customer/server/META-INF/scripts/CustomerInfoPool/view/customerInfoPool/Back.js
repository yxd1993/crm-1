Ext.define("crm.view.customerInfoPool.Back", {
    extend: "Ext.window.Window",
    alias: "widget.customerInfoPoolBackWin",
    title: "回退信息",
    width: 500,
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
            columnWidth : 0.99,
            margin : '5 0 5 0 ',
            labelWidth: 100,
            allowBlank : false,
            regex : /^\S*$/,
            regexText : '不能含有空格'
        },
        items: [
            { xtype: "hiddenfield", name: "id"},
            { xtype: "textareafield", name: "backReason", fieldLabel: "退回原因", maxLength : 500 }
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
