/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.role.Search", {
    extend: "Ext.form.Panel",
    title:'查询条件',
    alias: 'widget.roleSearch',
    width : '100%',
    height : 113,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '8 10 5 10',
		labelWidth : 100,
		columnWidth : 0.3,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [{
        fieldLabel: '角色编码',
        name: 'roleCode',
        maxLength : 200
    },{
        fieldLabel: '角色名称',
        name: 'roleName',
        maxLength : 100
    }],
    buttons: [{
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
