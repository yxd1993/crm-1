/**
 * 意向意向Controller
 */
Ext.define('crm.controller.Intention', {
    extend: 'Ext.app.Controller',
    stores: ['Intention', 'Contact' ],
    models: ['Intention', 'Contact' ],
    views: ['Viewport', 'intention.List', 'intention.Search','intention.Add','intention.Detail'],
    init: function () {
        this.control({
            'intentionSearch button[action=reset]': {
                click: this.resetIntentionSearchForm
            },
            'intentionSearch button[action=select]': {
                click: this.reloadIntentionGridStore
            },
            'intentionSearch button[action=add]': {
                click: this.addIntention
            },
            'intentionSearch button[action=update]': {
                click: this.updateIntention
            },
//            'intentionSearch button[action=delete]': {
//              click: this.deleteIntention
//          	},
//          'intentionSearch button[action=addContract]': {
//              click: this.addContract
//          },
            'intentionlist': {
                itemdblclick: this.updateIntention
            },
            'intentionAddWin button[action=submit]':{
            	click:this.submitIntention
            },
        });
    },
    resetIntentionSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadIntentionGridStore : function(btn){
    	Ext.getCmp('intentionMainViewId').getIntentionGrid().getPagingToolbar().moveFirst();
    },
    addIntention : function(){
    	var win = Ext.widget('intentionAddWin');
    	win.show();
    },
    updateIntention : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('intentionMainViewId').getIntentionGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的意向信息');
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个意向信息进行修改');
		}
    	// 获取id
        var id = selectArr[0].get('id');
        var params = {};
		var intentionEntity = {};
		intentionEntity.id = id;
		params.intentionEntity = intentionEntity;
    	if(!Ext.isEmpty(id)){
			crm.requestJsonAjax('intentionAction!queryIntentionInfoById.action', params, 
					function(response){
						if(btn.action!=undefined){
							var win = Ext.widget("intentionAddWin"); 
					    	var form = win.down('form').getForm();
					    	setIntentionData(form, response);
							win.title = "修改意向信息";
							win.show();
							
						}else{
							var win = Ext.widget("intentionDetailWin"); 
							var form = win.down('form').getForm();
							initIntentionData(form,response);
							win.show();
						}
					},
					function(){Ext.MessageBox.alert('提示','查询意向信息失败');
					})
		}
    },
//    deleteIntention:function(btn){
//    	// 选中的数据
//		var selectArr = Ext.getCmp('intentionMainViewId').getIntentionGrid()
//				.getSelectionModel().getSelection();
//		if (selectArr.length > 0) {
//			 Ext.MessageBox.show({
// 		        title: "意向信息删除",
// 		        msg: "请填写删除原因：",
// 		        width: 350,
// 		        buttons: Ext.MessageBox.OKCANCEL,
// 		        multiline: true,
// 		        animateTarget: btn,
// 		        fn: delIntention
// 		    });
//		} else {
//			Ext.MessageBox.alert('提示', '请选择需要删除的意向信息');
//		}
//		 function delIntention(btn,text){
//			 if (btn == 'ok') {
//				 if(text!=''){
//					// 删除的信息集合
//					var deleteInfoArr = [];
//					for (var i = 0; i < selectArr.length; i++) {
//						deleteInfoArr.push(selectArr[i].getData().id)
//					}
//					var params = {};
//					var intentionEntity = {};
//					params.ids = deleteInfoArr;
//					intentionEntity.delDesc = text;
//					params.intentionEntity = intentionEntity;
//					// AJAX请求
//					crm.requestJsonAjax('saleChatsAction!delChats.action', params,
//							function() {
//								Ext.MessageBox.alert('提示', '意向信息删除成功');
//								Ext.getCmp('chatsMainViewId').getChatsGrid()
//										.getStore().reload();
//							}, function() {
//								Ext.MessageBox.alert('提示', '意向信息删除失败');
//							});
//				 }else{
//					 Ext.MessageBox.alert('提示', '删除失败,请填写删除原因');
//				 }
//			 }
//		 }
//    },
    addContract:function(btn){
    	
    },
    submitIntention:function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		Ext.MessageBox.alert('提示','必填信息或输入信息有误');
    		return;
    	}
    	// 保存信息
    	var params = {};
    	var intentionEntity = {};
    	var customerEntity = {};
    	var contactEntity = {};
    	// id
    	intentionEntity.id = form.findField('id').getValue();    
    	//主要货物名称
    	intentionEntity.goodsName = form.findField('goodsName').getValue();
    	//包装方式
    	intentionEntity.packaging = form.findField('packaging').getValue();
    	// 客户企业名称
        customerEntity.id = form.findField('accountId').getValue();
    	// 联系人
    	contactEntity.id = form.findField('contactName').getValue();
    	//最期望的营销活动类型
    	intentionEntity.activityType = form.findField('activityType').getValue();
    	// 客户评分
    	intentionEntity.customerScore = form.findField('customerScore').getValue();
    	// 最期望的营销活动描述
    	intentionEntity.activityRemarks =form.findField('activityRemarks').getValue();
    	intentionEntity.customerEntity = customerEntity;
    	intentionEntity.contactEntity = contactEntity;
    	params.intentionEntity = intentionEntity;
    	crm.requestJsonAjax('intentionAction!addIntentionInfo.action', params, 
				function(response){
					Ext.MessageBox.alert('提示','意向信息保存成功');
					Ext.getCmp('intentionMainViewId').getIntentionGrid().getStore().reload();
					win.close();
				}, 
				function(response){Ext.MessageBox.alert('提示', response.message);});
    }
});
/**
 * 修改信息赋值
 * 
 * @param form
 * @param response
 * @author 丁勇
 * @date 2015年6月24日
 * @update
 */
function setIntentionData(form,response){
	var intentionEntity = response.intentionEntity;
	//id
	form.findField('id').setValue(intentionEntity.id);
	if(intentionEntity.customerEntity!=null){
		form.findField('accountId').setValue(intentionEntity.customerEntity.id);
		//企业
		form.findField('enterpriseName').setCombValue(intentionEntity.customerEntity.enterpriseName,intentionEntity.customerEntity.enterpriseName);
		// 办公地址
		form.findField('detailedAddress').setValue(intentionEntity.customerEntity.detailedAddress);
		//邮编
		form.findField('deliveryAddressPostalCode').setValue(intentionEntity.customerEntity.deliveryAddressPostalCode);
		//上门取货地址
		form.findField('deliveryAddress').setValue(intentionEntity.customerEntity.deliveryAddress);
	}
	//商品名称
	form.findField('goodsName').setValue(intentionEntity.goodsName);
	//包装方式
	form.findField('packaging').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.packaging, "CUSTOMER_PACKAGING"));
	//联系人
	if(intentionEntity.contactEntity!=null){
		var contactName = form.findField('contactName');
		contactName.getStore().setCurrContactId(intentionEntity.contactEntity.id)
		contactName.getStore().setAccountId(intentionEntity.customerEntity.id);
		contactName.getStore().load();
	}
	//营销活动类型
	form.findField('activityType').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.customerScore, "CUSTOMER_CREDIT_GRADE"));
	//客户信用评分
	form.findField('customerScore').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.customerScore, "CUSTOMER_CREDIT_GRADE"));
	//活动详述
	form.findField('activityRemarks').setValue(intentionEntity.activityRemarks);
}
/**
 * 初始化详情
 * 
 * @param form
 * @param response
 * @author 丁勇
 * @date 2015年6月24日
 * @update
 */
function initIntentionData(form,response){
	var intentionEntity = response.intentionEntity;
	if(intentionEntity.customerEntity!=null){
		//企业
		form.findField('enterpriseName').setValue(intentionEntity.customerEntity.enterpriseName);
		// 办公地址
		form.findField('detailedAddress').setValue(intentionEntity.customerEntity.detailedAddress);
		//邮编
		form.findField('deliveryAddressPostalCode').setValue(intentionEntity.customerEntity.deliveryAddressPostalCode);
		//上门取货地址
		form.findField('deliveryAddress').setValue(intentionEntity.customerEntity.deliveryAddress);
	}
	//商品名称
	form.findField('goodsName').setValue(intentionEntity.goodsName);
	//包装方式
	form.findField('packaging').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.packaging, "CUSTOMER_PACKAGING"));
	// 联系人
	if (intentionEntity.contactEntity != null) {
		form.findField('contactName').setValue(
				intentionEntity.contactEntity.contactName);
		// 联系电话
		form.findField('cellphone').setValue(
				intentionEntity.contactEntity.cellphone);
	}
	//营销活动类型
	form.findField('activityType').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.customerScore, "CUSTOMER_CREDIT_GRADE"));
	//客户信用评分
	form.findField('customerScore').setValue(getDataDictionary().rendererSubmitToDisplay(intentionEntity.customerScore, "CUSTOMER_CREDIT_GRADE"));
	//活动详述
	form.findField('activityRemarks').setValue(intentionEntity.activityRemarks);
}