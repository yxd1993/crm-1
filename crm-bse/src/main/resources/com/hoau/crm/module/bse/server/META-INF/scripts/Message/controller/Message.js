/**
 * 客户信息Controller
 */
Ext.define('crm.controller.Message', {
    extend: 'Ext.app.Controller',
    stores: ['Message','Role'],
    models: ['Message','Role'],
    views: ['Viewport', 'message.List', 'message.Search', 'message.Add'],
    init: function () {
        this.control({
            'messageSearch button[action=reset]': {
                click: this.resetMessageSearchForm
            },
            'messageSearch button[action=select]': {
                click: this.reloadMessageGridStore
            },
            'messageSearch button[action=insert]': {
                click: this.addMessageWin
            },
            'messageSearch button[action=singleDevice]':{
            	click:this.addSingleDeviceMessageWin
            },
            'addMessageWin button[action=submit]':{
            	click:this.submitAddMessageForm
            },
            'addMessageWin button[action=reset]':{
            	click:this.resetAddMessageForm
            }
        });
    },
    resetMessageSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadMessageGridStore : function(btn){
    	Ext.getCmp('messageViewId').getMessageGrid().getPagingToolbar().moveFirst();
    },
    addMessageWin : function(){
    	// 新增消息
    	var win = Ext.widget("addMessageWin"); 
    	var form = win.down('form').getForm();
    	form.findField('roleId').setDisabled(true);
    	form.findField('roleId').setHidden(true);
        win.show();
    },
    addSingleDeviceMessageWin : function(type){
    	// 新增消息
    	var win = Ext.widget("addMessageWin");
        win.show();
    },
    submitAddMessageForm:function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		Ext.MessageBox.alert('提示','必填信息或输入信息有误');
    		return;
    	}
    	// 消息信息
    	var params = {};
    	var messageEntity = {};
    	var messageInfoVo = {};
    	// 消息类型
    	messageEntity.messageType = form.findField('messageType').getValue();
    	// 消息内容
    	messageEntity.messageContent = form.findField('messageContent').getValue();
    	// 消息发送时间
    	messageEntity.allowSendTime = form.findField('allowSendTime').getValue();
    	// 角色 ID
    	messageInfoVo.roleId = form.findField('roleId').getValue();
    	messageInfoVo.messageInfoEntity = messageEntity;
    	params.messageInfoVo = messageInfoVo;
    	// 请求提交路径
    	var surl = 'messageAction!addMessage.action';
    	if(!form.findField('roleId').hidden){
    		surl = 'messageAction!addSingleDeviceMessage.action';
    	}
    	// 提示正在保存中
    	win.getEl().mask("数据保存中...");
    	// AJAX请求
    	Ext.Ajax.request({
    		url:surl,
    		jsonData:params,
    		timeout: 90000,   // 90秒
    		success:function(response){
    			// 取消正在保存中
    			win.getEl().unmask();
				Ext.MessageBox.alert('提示','消息新增成功',function(){
					Ext.getCmp('messageViewId').getMessageGrid().getStore().reload();
				});
				win.close();
			}, 
    		failure:function(response){
				// 提示正在保存中
				win.getEl().unmask();
				Ext.MessageBox.alert('提示', response.message);
			},
    		exception:function(response){
    			var result = Ext.decode(response.responseText);
    			failFn(result);
    		}
    	});
    },
    resetAddMessageForm : function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	form.getForm().reset();
    }
});
