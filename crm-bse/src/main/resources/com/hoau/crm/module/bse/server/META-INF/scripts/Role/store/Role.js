/**
 * 客户信息STORE
 */
Ext.define("crm.store.Role", {
    extend: "Ext.data.Store",
    model: "crm.model.Role",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'queryRoleList.action',
        reader: {
            type: 'json',
            rootProperty: 'roleList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('roleViewId').getRoleSearchForm().getForm();
			//查询条件
			var roleCode = queryForm.findField('roleCode').getValue();
			var roleName = queryForm.findField('roleName').getValue();
			if (queryForm != null) {
				var params = {
						'role.roleCode' : roleCode,
						'role.roleName' : roleName
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: true
});