/**
 * 部门Store
 */
Ext.define('CRM.store.bse.UserMenuStore', {
	extend: 'Ext.data.TreeStore',
    model: 'CRM.model.bse.UserMenuModel',
	proxy:{
		type:'ajax',
		url:'queryChildDeptList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes',
		},
		default:{expanded : true }
	},
	autoLoad:true,
	folderSort: true
});


/**
 * 用户信息store
 */
Ext.define('CRM.store.bse.UserStore', {
	extend : 'Ext.data.Store',
	model : 'CRM.model.bse.EmployeeEntity', 
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'queryUserList.action',
		reader : {
			type : 'json',
			rootProperty : 'userList', 
			totalProperty : 'totalCount' //总个数
		}
	}
	/*,
	listeners : {
		beforeload : function (store, operation, eOpts) {
			var queryForm = Ext.getCmp('queryForm');
			if (queryForm != null) {
				var params =  { 
						'empEntity.empCode' : queryForm.getForm().findField('empCode').getValue(),
						'empEntity.empName' : queryForm.getForm().findField('empName').getValue(),
						'empEntity.deptcode' : queryForm.getForm().findField('deptcode').getValue()
					};
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	}*/
});

//角色store
Ext.define('CRM.store.bse.roleStore',{
	extend: 'Ext.data.Store',
	model : 'CRM.model.bse.roleModel'/*,
	proxy : {
		type : 'ajax',
		actionMethods : 'POST',
		url : 'findAllRole.action',
		reader : {
			type : 'json',
			root : 'roleList',
			totalProperty : 'totalCount'
		}
	}*/
});