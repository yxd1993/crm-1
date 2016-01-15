/**
 * 客户信息新增表格
 */
Ext.define('crm.controller.AddIntention', {
	extend : 'Ext.app.Controller',
	views : [ 'Viewport', 'addIntention.AddIntentionForm'],
	init : function() {
		this.control({
			'addIntentionForm button[action=reset]': {
                click: this.resetAddIntentionForm
            },
            'addIntentionForm button[action=submit]': {
                click: this.submitAddIntentionForm
            }
		});
	},
	resetAddIntentionForm : function(btn) {
		//Ext.getCmp('addIntentionViewId').getAddIntentionForm().getForm().reset();
		var form = Ext.getCmp('addIntentionViewId').getAddIntentionForm().getForm();
		// 关闭页面
		if(Ext.isEmpty(form.findField('fId').getValue())){
			parent.delTabpanel('101002');
		}else{
			parent.delTabpanel('101003');
		}
	},
	submitAddIntentionForm : function(btn) {
		// 组装参数
		var form = Ext.getCmp('addIntentionViewId').getAddIntentionForm().getForm();
		if(!form.isValid()){
			Ext.MessageBox.alert('提示','请完善必填字段信息'); 
			return;
		}
		var params = {};
		var intentionEntity = {};
		// 客户信息
		intentionEntity.id = form.findField('fId').getValue();
		intentionEntity.goodsname = form.findField('goodsname').getValue();
		intentionEntity.packaging = form.findField('packaging').getValue();
		intentionEntity.linkanName = form.findField('linkanName').getValue();
		intentionEntity.mobile = form.findField('mobile').getValue();
		intentionEntity.offaddress = form.findField('offaddress').getValue();
		intentionEntity.dooraddress = form.findField('dooraddress').getValue();
		intentionEntity.zipaddress = form.findField('zipaddress').getValue();
		intentionEntity.creditcustomer = form.findField('creditcustomer').getValue();
		intentionEntity.qwcustomer = form.findField('qwcustomer').getValue();
		intentionEntity.describecustomer = form.findField('describecustomer').getValue();
		// 组装参数
		params.intentionEntity = intentionEntity;
		// 提示正在保存中
		addIntentionMask.show();
		crm.requestJsonAjax('intentionAction!addIntention.action', params, 
				function(response){
					// 提示正在保存中
					addIntentionMask.hide();
					if(response.success){
						Ext.MessageBox.alert('提示','保存意向信息成功', function(){
							//客户列表
					    	window.parent.initTabpanel('101001', '意向列表', '/crm-web/sales/intentionMain.action', true);
							// 关闭页面
							if(Ext.isEmpty(intentionEntity.id)){
								parent.delTabpanel('101002');
							}else{
								parent.delTabpanel('101003');
							}
						}); 
					}else{
						Ext.MessageBox.alert('提示','保存意向信息失败'); 
					}
				}, 
				function(response){
					// 提示正在保存中
					addIntentionMask.hide();
					Ext.MessageBox.alert('提示', response.message); 
				});
	}
});
