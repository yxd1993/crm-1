var addIntentionMask; 

Ext.application({
    name: "crm",
    appFolder: '../scripts/sales/addIntention',
    controllers: ["AddIntention"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	// LOADMASK初始化
    	addIntentionMask = new Ext.LoadMask({
    	    msg    : '数据保存中...',
    	    target : Ext.getCmp('addIntentionViewId')
    	});
    	
        // 如果fId不为空，则为修改意向信息
    	if(!Ext.isEmpty(fId)){
    		var params = {};
    		var IntentionEntity = {};
    		IntentionEntity.id = fId;
    		params.IntentionEntity = IntentionEntity;
    		crm.requestJsonAjax('IntentionAction!queryIntentionInfoById.action', params, 
    				function(response){
    					if(response.success){
    						var IntentionEntity = response.IntentionEntity;
    						// 组装参数
    						var form = Ext.getCmp('addIntentionViewId').getAddIntentionForm().getForm();
    						// 主键
    						form.findField('fId').setValue(fId);
    						// 全称
    						form.findField('enterpriseName').setCombValue(IntentionEntity.customerEntity.enterpriseName, IntentionEntity.customerEntity.enterpriseName);
    						//主要货物名称
    						form.findField('goodsname').setValue(IntentionEntity.goodsname);
    						//主要包装方式
    						form.findField('packaging').setValue(IntentionEntity.packaging);
    						//联系人姓名
    						form.findField('linkanName').setValue(IntentionEntity.linkanName);
    						//联系人电话
    						form.findField('mobile').setValue(IntentionEntity.mobile);
    						//办公地址
    						form.findField('offaddress').setValue(IntentionEntity.offaddress);
    						// 上门取货地址
    						form.findField('dooraddress').setValue(IntentionEntity.dooraddress);
    						//取货邮编地址
    						form.findField('zipaddress').setValue(IntentionEntity.zipaddress);
    						//客户信用评分
    						form.findField('creditcustomer').setValue(IntentionEntity.creditcustomer);
    						//客户最期望的营销活动类型
    						form.findField('qwcustomer').setValue(IntentionEntity.qwcustomer);
    						//客户最期望的营销活动详述
    						form.findField('describecustomer').setValue(IntentionEntity.describecustomer);
    					}else{
    						Ext.MessageBox.alert('提示','查询意向信息失败'); 
    					}
    				}, 
    				function(){Ext.MessageBox.alert('提示','查询意向信息失败'); });
    	}else{
    		// 组装参数
			var form = Ext.getCmp('addIntentionViewId').getAddIntentionForm().getForm();
    	}
    }
});
