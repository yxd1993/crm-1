/**
 * 主界面菜单Store
 */
Ext.define('CRM.store.login.MenuStore', {
	extend: 'Ext.data.TreeStore',
    model: 'CRM.model.login.MenuModel',
	proxy:{
		type:'ajax',
		url:'../bse/queryTreeNodeList.action',
		actionMethods:'POST',
		reader:{
			type:'json',
			rootProperty:'nodes',
		},
		default:{expanded : true }
	},
	/*autoLoad:true,*/
	folderSort: true
});