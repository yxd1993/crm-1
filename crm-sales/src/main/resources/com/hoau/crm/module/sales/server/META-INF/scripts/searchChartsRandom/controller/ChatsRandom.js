/**
 *  洽谈回访随机抽取Controller
 */
Ext.define('crm.controller.ChatsRandom',{
		extend : 'Ext.app.Controller',
		stores : ['ChatsRandom'],
		models : ['ChatsRandom'],
		views : [ 'Viewport', 'chatsRandom.ChatsRandomList', 'chatsRandom.Search','chatsRandom.Detail','chatsRandom.UpdateChatsResult'],
		init: function () {
	        this.control({
	        	'chatsRandomsearch button[action=search]': {
	                click: this.reloadChatsRandomSearchForm
	            },
	            'chatsRandomsearch button[action=reset]': {
	                click: this.resetChatsRandomSearchForm
	            },
	            'chatsRandomsearch button[action=visit]': {
	                click: this.updateByResult
	            },
	            'chatsRandomlist': {
	                itemdblclick: this.lookChatsRandomInfo
	            },
	            
	            'updateChatsResultWin button[action=submit]':{
	            	click: this.updateChatsSubmit
	            },
	            'updateChatsResultWin button[action=reset]':{
	            	click: this.resetSubmitForm
	            }
	        })
		},
		reloadChatsRandomSearchForm:function(btn){
			Ext.getCmp('chatsRandomViewId').getChatsGrid().getPagingToolbar().moveFirst();
		},
		resetChatsRandomSearchForm : function(btn){
	    	// 重置查询表单
	    	btn.up('form').getForm().reset();
	    },
	    updateByResult:function(btn){
	    	var selectArr = Ext.getCmp('chatsRandomViewId').getChatsGrid().getSelectionModel().getSelection();
	    	if(selectArr.length > 0){
	    		if(selectArr[0].get('status')=='1'){
		    		return Ext.MessageBox.alert('提示','请选择未审核的进行回访');
		    	}else{
		    		var win = Ext.widget("updateChatsResultWin"); 
			    	var form = win.down('form').getForm();
			        win.show();
		    	}
	    	}else{
	    		Ext.MessageBox.alert('提示','请选择需要审核的洽谈信息');
	    	}
	    },
	    lookChatsRandomInfo:function(){
	    	var selectArr = Ext.getCmp('chatsRandomViewId').getChatsGrid().getSelectionModel().getSelection();
	    	if(selectArr[0].get('status')=='0'){
	    		return Ext.MessageBox.alert('提示', '请选择已审核的洽谈信息');
	    	}else{
	    		// 获取id
		        var id = selectArr[0].get('id');
	    		var win = Ext.widget("chatsRandomDetailWin"); 
		    	var form = win.down('form').getForm();
		    	if(!Ext.isEmpty(id)){
			        var params = {};
					var saleChatRandomEntity = {};
					saleChatRandomEntity.id = id;
					params.saleChatRandomEntity = saleChatRandomEntity;
			        crm.requestJsonAjax('../sales/saleChatsAction!queryChatsRandomById.action', params, 
							function(response){
			        			var saleChatRandomEntity = response.saleChatRandomEntity;
			        			form.findField('customerName').setValue(saleChatRandomEntity.customerName);
			        			form.findField('status').setValue(getDataDictionary().rendererSubmitToDisplay(saleChatRandomEntity.status, "RANDOM_CHAT_STATUS"));
			        			form.findField('chatContent').setValue(saleChatRandomEntity.chatContent);
			        			form.findField('checkResult').setValue(saleChatRandomEntity.checkResult);
							},
							function(){Ext.MessageBox.alert('提示','查询洽谈审核信详情失败');
							})
			        win.title = '洽谈审核详情';
			        win.show();
		    	}
	    	}
	    },
	    updateChatsSubmit:function(btn){
	    	var selectArr = Ext.getCmp('chatsRandomViewId').getChatsGrid().getSelectionModel().getSelection();
	    	var form = btn.up('form').getForm();
	    	var win = btn.up('window');
	    	if(!form.isValid()){
	    		Ext.MessageBox.alert('提示','必填信息或输入信息有误');
	    		return;
	    	}
	    	if(!form.isValid()){
				Ext.MessageBox.alert('提示','必填信息或输入信息有误');
				return;
			}
	    	// 更新的信息集合
			var deleteInfoArr = [];
			for (var i = 0; i < selectArr.length; i++) {
				deleteInfoArr.push(selectArr[i].getData().id)
			}
			var params = {};
			var saleChatRandomEntity = {};
			var saleChatRandomVo = {}
			saleChatRandomEntity.status = form.findField('status').getValue();
			saleChatRandomEntity.checkResult = form.findField('checkResult').getValue();
			saleChatRandomVo.ids = deleteInfoArr;
			saleChatRandomVo.saleChatRandomEntity = saleChatRandomEntity;
			params.saleChatRandomVo = saleChatRandomVo;
			// AJAX请求
			crm.requestJsonAjax('../sales/saleChatsAction!updateCheckResult.action', params,
				function(response){
					Ext.MessageBox.alert('提示', '保存成功',function(){
						Ext.getCmp('chatsRandomViewId').getChatsGrid().getStore().reload();
						win.close();
					});
				}, function() {Ext.MessageBox.alert('提示', '保存失败')});
			
	    },
	    resetSubmitForm:function(btn){
	    	// 清空或还原数据
        	var form = btn.up('form');
        	var record = form.record;
        	if(Ext.isEmpty(record)){
        		form.getForm().reset();
        	}else{
        		form.loadRecord(record);
        	}
	    }
});

