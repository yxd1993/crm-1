/**
 * 权限信息STORE
 */
Ext.define('crm.store.Auth', {
	extend: 'Ext.data.TreeStore',
    model: 'crm.model.Auth',
	proxy:{
		type:'ajax',
		url:'loadFunctionChooseAllTree.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes',
		},
		default:{expanded : true }
	},
	listeners : {
		beforeload : function(store, operation, eOpts) {
			// 选中的数据
	    	var selectArr = Ext.getCmp('roleViewId').getRoleListGrid().getSelectionModel().getSelection();
			var searchParams = store.proxy.extraParams;
			var params = {
					'node' : searchParams.node,
					'modify' : 1,
					'roleCode': selectArr.length > 0 ? selectArr[0].get('roleCode') : ''
				}
			Ext.apply(store.proxy.extraParams, params);  
		}
	}
});