/**
 * 角色信息STORE
 */
Ext.define("crm.store.Role", {
    extend: "Ext.data.Store",
    model: "crm.model.Role",
    pageSize : 100,
    proxy: {
        type: 'ajax',
        url: 'queryRoleList.action',
        reader: {
            type: 'json',
            rootProperty: 'roleList',
            totalProperty: 'totalCount'
        }
    },
    autoLoad: true
});