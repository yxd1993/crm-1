/**
 * 授权信息Controller
 */
Ext.define('crm.controller.Authorization', {
	extend : 'Ext.app.Controller',
	stores : [ 'Authorization' ],
	models : [ 'Authorization' ],
	views : [ 'Viewport', 'authorization.List', 'authorization.Search','authorization.Add'],
	init : function() {
		this.control({
			'authorizationSearch button[action=reset]' : {
				click : this.resetAuthorizationSearchForm
			},
			'authorizationSearch button[action=select]' : {
				click : this.reloadAuthorizationGridStore
			},
			'authorizationSearch button[action=delete]' : {
				click : this.deleteAuthorization
			},
			'authorizationSearch button[action=add]' : {
				click : this.addAuthorization
			},
			'authorizationSearch button[action=update]' : {
				click : this.updateAuthorization
			},
			'authorizationList' : {
				itemdblclick : this.lookAuthorizationInfo
			},
            'authorizationAddWin button[action=reset]': {
            	click: this.resetAddForm
            },

		});
	},
	resetAuthorizationSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadAuthorizationGridStore : function(btn) {
		Ext.getCmp('authorizationMainViewId').getAuthorizationGrid()
				.getPagingToolbar().moveFirst();
	},
	
	deleteAuthorization : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('authorizationMainViewId').getAuthorizationGrid().getSelectionModel().getSelection();
    	var boolean =true;
    	if(selectArr.length > 0){
				Ext.Msg.confirm('提示', '您确定要删除挑中的授权信息？', function(btn) {
					if(btn == 'yes') {
						//删除的信息集合
						var deleteInfoArr = [];
						for(var i=0; i<selectArr.length; i++){
							deleteInfoArr.push(selectArr[i].getData().id)
						}
						var params = {};
						params.ids = deleteInfoArr;
						// AJAX请求
						crm.requestJsonAjax('authorizationAction!deleteAuthorization.action', params, 
								function(){
									Ext.MessageBox.alert('提示','授权信息删除成功');
									Ext.getCmp('authorizationMainViewId').getAuthorizationGrid().getStore().reload();
								}, 
								function(){Ext.MessageBox.alert('提示','授权信息删除失败');});
					}
				})
							
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的授权信息');
		}
		
	},
	
	addAuthorization :function(btn){
    	// 新增授权信息
    	var win = Ext.widget("authorizationAddWin");
    	win.show();
	},
	
/*	updateAuthorization: function(btn) {
		 // 选中的数据
		 var selectArr = Ext.getCmp('authorizationMainViewId').getAuthorizationGrid().getSelectionModel().getSelection();
		 if (selectArr.length == 0) {
			 Ext.MessageBox.alert('提示', '请选择需要修改的授权信息');
			 return;
		 } else if (selectArr.length > 1) {
			 Ext.MessageBox.alert('提示', '只能选择一个授权信息进行修改');
			 return;
		 }
		 var win = Ext.widget("authorizationAddWin");
		 var form = win.down("form").getForm();
		 // 获取id
		 var id = selectArr[0].get('id');
		 var params = {};
		 var authorization = {};
		 authorization.id = id;
		 params.authorization = authorization;
		 crm.requestJsonAjax('authorizationAction!queryAuthorizationById.action',params, function(response) {
			 	initAuthorizationData(form, response);
	 		}, function() {
		 		Ext.MessageBox.alert('提示', '查询授权信息失败');
		 	})
		 win.title = '修改授权信息';
		 var resetbtn = win.down('button[action=reset]');
		 resetbtn.setText('关闭');
		 resetbtn.on('click',function(){
			 win.close();
		 })
		 win.show();
		 },*/
	
	updateAuthorization: function(btn) {
		 // 选中的数据
		 var selectArr = Ext.getCmp('authorizationMainViewId').getAuthorizationGrid().getSelectionModel().getSelection();
		 if (selectArr.length == 0) {
			 Ext.MessageBox.alert('提示', '请选择需要修改的授权信息');
			 return;
		 } else if (selectArr.length > 1) {
			 Ext.MessageBox.alert('提示', '只能选择一个授权信息进行修改');
			 return;
		 }
		 
		 var win = Ext.widget("authorizationAddWin");
		 var form = win.down("form").getForm();
		 
		 win.title = '修改授权信息';
		 var resetbtn = win.down('button[action=reset]');
		 resetbtn.setText('关闭');
		 resetbtn.on('click',function(){
			 win.close();
		 })
    	 form.record = selectArr[0];
    	 win.down("form").loadRecord(selectArr[0]);
    	 form.findField('authorizedPerson').setReadOnly(true);
    	 form.findField('wasAuthorizedPerson').setReadOnly(true);	
    	 win.show();
	},
	
    resetAddForm : function(btn){
    	// 清空数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    }
		 
});


/**
 * 初始化授权修改信息
 *
 * @author 潘强
 * @date 2015-10-08
 * @update
 */
/*function initAuthorizationData(form, response){
	//获取后台传入的相关授权信息
	var authorization=response.authorization;
	//将相应信息写入form中
	form.findField('id').setValue(authorization.id);
	form.findField('authorizedPerson').setValue(authorization.authorizedPerson);
	form.findField('wasAuthorizedPerson').setValue(authorization.wasAuthorizedPerson);
	form.findField('authorizedStartTime').setValue(new Date(authorization.authorizedStartTime).format("yyyy-MM-ddh:m:s"));
	form.findField('authorizedEndTime').setValue(new Date(authorization.authorizedEndTime).format("yyyy-MM-ddh:m:s"));
	form.findField('authorizedPerson').setReadOnly(true);
	form.findField('wasAuthorizedPerson').setReadOnly(true);
}*/

