/**
 * App版本信息Controller
 */
Ext.define('crm.controller.AppVersion', {
	extend : 'Ext.app.Controller',
	stores : [ 'AppVersion' ],
	models : [ 'AppVersion' ],
	views : [ 'Viewport', 'appVersion.List', 'appVersion.Search' ,'appVersion.Detail','appVersion.Add'],
	init : function() {
		this.control({
			'appVersionSearch button[action=reset]' : {
				click : this.resetAppVersionSearchForm
			},
			'appVersionSearch button[action=select]' : {
				click : this.reloadAppVersionGridStore
			},
			'appVersionSearch button[action=delete]' : {
				click : this.deleteAppVersion
			},
			'appVersionSearch button[action=add]' : {
				click : this.addAppVersion
			},	
			'appVersionSearch button[action=updateIsNow]' : {
				click : this.updateIsNow
			},
			'appVersionSearch button[action=export]' : {
				click : this.export
			},
			'appVersionList' : {
				itemdblclick : this.lookAppVersionInfo
			},
            'appVersionAddWin button[action=reset]': {
            	click: this.resetAddForm
            },

		});
	},
	resetAppVersionSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadAppVersionGridStore : function(btn) {
		Ext.getCmp('appVersionMainViewId').getAppVersionGrid()
				.getPagingToolbar().moveFirst();
	},
	
	updateIsNow :function(btn){
		// 选中的数据
    	var selectArr = Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 1){
			Ext.Msg.confirm('提示', '您确定要设置该App为当前版本？', function(btn) {
				if(btn == 'yes') {
					//删除的信息集合
					var params = {};
					params.versionCode = selectArr[0].getData().versionCode;
					// AJAX请求
					crm.requestJsonAjax('appVersionAction!updateVersionCode.action', params, 
							function(){
								Ext.MessageBox.alert('提示','App当前版本设置成功');
								Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getStore().reload();
							}, 
							function(){Ext.MessageBox.alert('提示','App当前版本设置失败');});
				}
			})
		}else{
			Ext.MessageBox.alert('提示','请选择1条数据设置为当前版本');
		}
	},
	
	deleteAppVersion : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getSelectionModel().getSelection();
    	var boolean =true;
    	if(selectArr.length > 0){
    		for(var i=0; i<selectArr.length; i++){
					if(selectArr[i].getData().isNow==1){
						boolean=false;
							Ext.Msg.confirm('提示', '不能删除当前版本App信息', function(btn){
								if(btn == 'yes') {Ext.MessageBox.alert('提示','请重新选择');}
							})
						}
    		      }
					if(boolean){
							Ext.Msg.confirm('提示', '您确定要删除挑中的App版本信息？', function(btn) {
								if(btn == 'yes') {
									//删除的信息集合
									var deleteInfoArr = [];
									for(var i=0; i<selectArr.length; i++){
										deleteInfoArr.push(selectArr[i].getData().id)
									}
									var params = {};
									params.ids = deleteInfoArr;
									// AJAX请求
									crm.requestJsonAjax('appVersionAction!deleteAppVersion.action', params, 
											function(){
												Ext.MessageBox.alert('提示','App版本信息删除成功');
												Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getStore().reload();
											}, 
											function(){Ext.MessageBox.alert('提示','App版本信息删除失败');});
								}
							})
							
							}
					
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的App版本信息');
		}
		
	},
	
	addAppVersion :function(btn){
    	// 新增App版本信息
    	var win = Ext.widget("appVersionAddWin");
    	win.show();
	},
	
	lookAppVersionInfo:function(btn,record){
		var selectResult=Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getSelectionModel().getSelection();
		var win = Ext.widget("appVersionDetailWin"); 
    	var form = win.down("form").getForm();
    	win.down("form").loadRecord(record);
    	
		form.findField('id').setValue(selectResult[0].get('id'));
		form.findField('versionCode').setValue(selectResult[0].get('versionCode'));
		form.findField('apkName').setValue(selectResult[0].get('apkName'));
		//form.findField('isMust').setValue(selectResult[0].get('isMust'));
		//form.findField('isNow').setValue(selectResult[0].get('isNow'));
		form.findField('description').setValue(selectResult[0].get('description'));
    	form.findField('createDate').setValue(Ext.util.Format.date(new Date(selectResult[0].get('createDate')), 'Y-m-d H:i:s'));
    	form.findField('isMust').setValue(getDataDictionary().rendererSubmitToDisplay(record.get('isMust'), "APP_VERSION_ISMUST"));
    	form.findField('isNow').setValue(getDataDictionary().rendererSubmitToDisplay(record.get('isNow'), "APP_VERSION_ISNOW"));
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
    },
    
/*    submitAddForm : function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	// 保存信息
    	var params = {};
    	var appVersion = {};
    	appVersion.versionCode = form.findField('versionCode').getValue();
    	appVersion.apkName = form.findField('apkName').getValue();
    	appVersion.isMust = form.findField('isMust').getValue();
    	appVersion.isNow = form.findField('isNow').getValue();
    	appVersion.description = form.findField('description').getValue();
    	
    	
    	params.appVersion = appVersion;
		// AJAX请求
		crm.requestJsonAjax('appVersionAction!addAppVersion.action', params, 
				function(response){
					Ext.MessageBox.alert('提示','App版本信息保存成功');
					Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getStore().reload();
					win.close();
				}, 
				function(response){Ext.MessageBox.alert('提示', response.message);}
			);
    },*/
    //导出当前版本App
    export:function(){
    	Ext.MessageBox.confirm('提示','确定要下载当前版本App？',function(btn){
    		if(btn == 'yes'){
    			crm.requestJsonAjax('appVersionAction!exportAppVersion.action',null,function(response){
					Ext.MessageBox.alert('提示','App版本下载成功');
				}, 
				function(response){
					Ext.MessageBox.alert('提示', response.message);
					}	
    			);
    			
    		}
    		
    	}
    )},
    
    
});