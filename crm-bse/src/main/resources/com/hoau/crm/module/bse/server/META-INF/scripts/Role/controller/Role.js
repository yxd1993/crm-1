/**
 * 角色信息Controller
 */
Ext.define('crm.controller.Role', {
    extend: 'Ext.app.Controller',
    stores: ['Auth', 'Role'],
    models: ['Role', 'Auth'],
    views: ['Viewport', 'role.List', 'role.Search', 'role.Add', 'role.Look', 'role.Auth', 'role.AuthTree'],
    init: function () {
        this.control({
            'roleSearch button[action=reset]': {
                click: this.resetSearchForm
            },
            'roleSearch button[action=select]': {
                click: this.reloadGridStore
            },
            'rolelist button[action=add]': {
                click: this.addRole
            },
            'rolelist button[action=update]': {
                click: this.updateRole
            },
            'rolelist button[action=delete]': {
                click: this.deleteRole
            },
            'rolelist': {
                itemdblclick: this.lookRole
            },
            'rolelist button[action=auth]': {
                click: this.authWin
            },
            'roleInfoWin button[action=reset]': {
            	click: this.resetAddForm
            },
            'roleInfoWin button[action=submit]': {
            	click: this.submitAddForm
            },
            'authWin button[action=expand]': {
            	click: this.expandTree
            },
            'authWin button[action=submit]': {
            	click: this.submitAuth
            }
        });
    },
    resetSearchForm : function(btn){
    	// 清空查询条件
    	btn.up('form').getForm().reset();
    },
    reloadGridStore : function(btn){
    	// 重新加载表格数据
    	Ext.getCmp('roleViewId').getRoleListGrid().getPagingToolbar().moveFirst();
    },
    addRole : function(){
    	var win = Ext.widget("roleInfoWin"); 
        win.show();
    },
    updateRole : function(){
    	// 选中的数据
    	var selectArr = Ext.getCmp('roleViewId').getRoleListGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的客角色信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个角色信息进行修改');
			return;
		}
    	var win = Ext.widget("roleInfoWin"); 
    	var form = win.down("form");
    	win.title = '修改角色信息';
    	form.record = selectArr[0];
    	form.getForm().findField('roleCode').setEditable(false);
    	win.down("form").loadRecord(selectArr[0]);
        win.show();
    },
    lookRole : function(grid, record){
    	var win = Ext.widget("roleInfoLookWin"); 
    	var form = win.down("form");
    	form.loadRecord(record);
        win.show();
    },
    deleteRole : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('roleViewId').getRoleListGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
			Ext.Msg.confirm('提示', '您确定要删除选中的角色信息？', function(btn) {
				if(btn == 'yes') {
					//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].getData().id)
					}
					var params = {};
					params.roles = deleteInfoArr;
					// AJAX请求
					crm.requestJsonAjax('deleteRole.action', params, 
							function(response){
								Ext.MessageBox.alert('提示','角色信息删除成功');
								Ext.getCmp('roleViewId').getRoleListGrid().getStore().reload();
							}, 
							function(response){
								Ext.MessageBox.alert('提示', response.message);
								Ext.getCmp('roleViewId').getRoleListGrid().getStore().reload();
							});
				}
			})
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的角色信息');
		}
    },
    resetAddForm : function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    },
    submitAddForm : function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	// 保存信息
    	var params = {};
    	var role = {};
    	role.id = form.findField('id').getValue();
    	role.roleCode = form.findField('roleCode').getValue();
    	role.roleName = form.findField('roleName').getValue();
    	role.roleDesc = form.findField('roleDesc').getValue();
    	params.role = role;
    	if(Ext.isEmpty(role.id)){
    		// AJAX请求
    		crm.requestJsonAjax('addRole.action', params, 
    				function(response){
    					Ext.MessageBox.alert('提示','角色信息保存成功');
    					Ext.getCmp('roleViewId').getRoleListGrid().getStore().reload();
    					win.close();
    				}, 
    				function(response){Ext.MessageBox.alert('提示', response.message);});
    	}else{
    		// AJAX请求
    		crm.requestJsonAjax('updateRole.action', params, 
    				function(response){
    					Ext.MessageBox.alert('提示','角色信息保存成功');
    					Ext.getCmp('roleViewId').getRoleListGrid().getStore().reload();
    					win.close();
    				}, 
    				function(response){Ext.MessageBox.alert('提示', response.message);});
    	}
    },
    authWin : function(){
    	// 选中的数据
    	var selectArr = Ext.getCmp('roleViewId').getRoleListGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要配置的客角色信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个角色信息进行配置');
			return;
		}
    	var win = Ext.widget("authWin");
    	if(IS_FIRST){
    		IS_FIRST = false;
    	}else{
    		win.down("treepanel").getStore().load();
    	}
    	// 加载权限树
    	win.show();
    	setTimeout(function(){win.down("treepanel").expandAll();}, 200);
    },
    expandTree : function(btn){
    	var win = btn.up('window');
    	win.down("treepanel").expandAll();
    },
    submitAuth : function(btn){
    	var win = btn.up('window');
    	var tree = win.down("treepanel");
    	var treeModel = tree.getChecked();
    	if(treeModel.length == 0){
    		Ext.MessageBox.alert('提示','请选择需要配置的权限信息');
			return;
    	}
    	// 参数
    	var params = {};
    	// 权限集合
    	var funCodes = [];
    	Ext.each(treeModel, function(record) {
    		funCodes.push(record.id);
    	});
    	params.funCodes = funCodes;
    	// 选中的数据
    	var selectArr = Ext.getCmp('roleViewId').getRoleListGrid().getSelectionModel().getSelection();
    	var role = {};
    	role.roleCode = selectArr[0].get('roleCode');
    	params.role = role;
    	// 提示正在保存中
    	win.getEl().mask("数据保存中...");
    	// AJAX请求
		crm.requestJsonAjax('addRoleFun.action', params, 
				function(response){
					// 提示正在保存中
					win.getEl().unmask();
					Ext.MessageBox.alert('提示','角色权限信息保存成功');
					Ext.getCmp('roleViewId').getRoleListGrid().getStore().reload();
					win.close();
				}, 
				function(response){
					// 提示正在保存中
					win.getEl().unmask();
					Ext.MessageBox.alert('提示', response.message);
				});
    }
});
