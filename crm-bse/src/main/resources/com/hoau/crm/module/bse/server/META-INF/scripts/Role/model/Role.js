/**
 * 客户信息MODEL
 */
Ext.define('crm.model.Role', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'roleCode'},
        { name: 'roleName'},
        { name: 'roleDesc'},
        { name: 'modifyDate', type : 'date', dateFormat:'time'}
    ]
});