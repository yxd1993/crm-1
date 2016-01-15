/*回滚版本*/
/**
 * 客户信息新增表格
 */
Ext.define('crm.controller.LookCustomer', {
	extend : 'Ext.app.Controller',
	stores: ['Contact','History'],
    models: ['Contact','History'],
	views : [ 'Viewport', 'lookCustomer.LookCustomForm', 'lookCustomer.ContactList','lookCustomer.HistoryList','addIntention.AddIntentionForm'],
	init: function () {
        this.control({
        	'lookCustomForm button[action=addContract]': {
                click: this.addContract
            },
            'lookCustomForm button[action=addReserve]': {
                click: this.addReserve
            },
            'lookCustomForm button[action=addChats]': {
                click: this.addChats
            },
            'lookCustomForm button[action=register]': {
                click: this.registerObhAccount
            },
            'lookCustomForm button[action=turnToIntention]': {
                click: this.turnToIntention
            },
            'intentionAddWin button[action=submitIntention]':{
            	click:this.submitIntention
            },
            'intentionAddWin button[action=close]':{
            	click:this.closeWin
          	}
        });
    },
    addContract : function(btn){
    	// 获取DC账号
		var customerForm = Ext.getCmp('lookCustomerViewId').getAddCustomerForm().getForm();
		var dcAccount = customerForm.findField('dcAccount').getValue();
		if(customerForm.findField('accountType').getValue()=='意向客户' || customerForm.findField('accountType').getValue()=='发货客户' ){
			window.parent.allChildrenParamsMap.put('dcAccount', dcAccount);
			window.parent.initTabpanel('103004', '合同列表', '/crm-web/sales/contractMain.action', true);
		}else{
			Ext.MessageBox.alert('提示','该客户不满足新增合同的条件！');
		}
    },
    addReserve:function(btn){
    	turnToAddInfo(btn,'reserve');
    },
    addChats:function(btn){
    	turnToAddInfo(btn,'chats');
    },
    registerObhAccount : function(btn){
    	// 选中的数据
    	var customerForm = Ext.getCmp('lookCustomerViewId').getAddCustomerForm().getForm();
    	var isCreateObhAccount = customerForm.findField('isCreateObhAccount').getValue();
		if(isCreateObhAccount == 'Y'){
			 Ext.MessageBox.alert('提示','该客户已经存在官网个人账户');
			 return;
		}else {
			var params = {};
			var customerEntity = {};
			var cId = customerForm.findField('cId').getValue();
			
			customerEntity.id = cId;
			
			params.customerEntity = customerEntity;
			Ext.Msg.confirm('提示', '您确定要注册账号？', function(btn) {
				if (btn == "yes"){
					// 提示正在保存中
					lookCustomerMask.show();
					// 保存信息
					crm.requestJsonAjax('customerAction!registerObhAccount.action', params, 
						function(response){
							lookCustomerMask.hide();
							if(response.success){
                                if(Ext.isEmpty(response.message)){
                                    Ext.MessageBox.alert('提示','账号注册成功',function(){
                                        window.parent.delTabpanel('101001201');
                                    });
                                }else{
                                    Ext.MessageBox.alert('提示',response.message,function(){
                                        window.parent.delTabpanel('101001201');
                                    });
                                }
							}},
						function(response){
							lookCustomerMask.hide();
							Ext.MessageBox.alert('提示', response.message); 
						});
				}
	    	})
		}
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
    	var customerEntity = {};
    	//id
    	customerEntity.id = form.findField('accountId').getValue();
    	//主要货物名称
    	customerEntity.mainGoodsName = form.findField('mainGoodsName').getValue();
		//主要包装方式
    	customerEntity.typeOfPackage = form.findField('typeOfPackage').getValue()
		//邮编
		customerEntity.deliveryAddressPostalCode = form.findField('deliveryAddressPostalCode').getValue();
		//上门取货地址
    	customerEntity.deliveryAddress = form.findField('deliveryAddress').getValue();
		//期望营销类型
    	customerEntity.marketActiveType = form.findField('marketActiveType').getValue();
		//信用评分
    	customerEntity.accountCreditGrade = form.findField('accountCreditGrade').getValue();
		//营销描述
    	customerEntity.marketActiveRemark = form.findField('marketActiveRemark').getValue();
    	
    	params.customerEntity = customerEntity;
    	// AJAX请求
    	win.getEl().mask("数据保存中...");
		crm.requestJsonAjax('customerAction!updateCustomerTurnIntention.action', params, 
				function(response){
				win.getEl().unmask();
					Ext.MessageBox.alert('提示','转为意向成功',function(){
						window.parent.delTabpanel('101001201');
						window.parent.initTabpanel('101001', '客户管理', '/crm-web/customer/index.action', true);
					});
					win.close();
				}, 
				function(response){
					win.getEl().unmask();
					Ext.MessageBox.alert('提示', response.message);
				});
    },
    //打开意向
    turnToIntention:function(btn){
    	turnToIntention();
    },
    closeWin:function(btn){
    	// 关闭
    	var win = btn.up('window');
    	win.close();
    },
});
/**
 * 打开意向界面
 * 
 * @author 丁勇
 * @date 2015年7月2日
 * @update
 */
function turnToIntention(){
	var win = Ext.widget("intentionAddWin"); 
	var form = win.down('form').getForm();
	intentionWin = win;
	// 判断是否是从其它页面跳转过来
	if(parent.allChildrenParamsMap.get('accountId')){
		//alert(parent.allChildrenParamsMap.get('accountId'))
		getCustomerInfo(form,parent.allChildrenParamsMap.get('accountId'))
		parent.allChildrenParamsMap.put('accountId', undefined)
		win.show();
	}else{
		// 获取id
		var customerForm = Ext.getCmp('lookCustomerViewId').getAddCustomerForm().getForm();
		var cId = customerForm.findField('cId').getValue();
		if(customerForm.findField('accountType').getValue()=='洽谈客户'){
			// 组装参数
			var params = {};
			var customerEntity = {};
			customerEntity.id = cId;
			params.customerEntity = customerEntity;
	    	if(!Ext.isEmpty(id)){
				crm.requestJsonAjax('customerAction!queryCustomerInfoById.action', params, 
						function(response){
						    	setIntentionData(form,response);
						},
						function(){Ext.MessageBox.alert('提示','查询客户信息失败');}
						)
			}
	    	win.show();
		}else{
			Ext.MessageBox.alert('提示','该用户不是洽谈用户,不能转为意向');
		}
        
	}
}
/**
 * 获取客户基本数据
 * 
 * @param id
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function getCustomerInfo(form,id){
	var customerEntity = {};
	var params ={};
	customerEntity.id = id;
	params.customerEntity = customerEntity;
	crm.requestJsonAjax('../customer/customerAction!queryCustomerInfoById.action', params, 
			function(response){
				setIntentionData(form,response);
			},function(){Ext.MessageBox.alert('提示','查询客户信息失败');});
}
/**
 * 判断跳转
 * @param btn
 * @param type
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function turnToAddInfo(btn,type){
		// 组装参数
		var customerForm = Ext.getCmp('lookCustomerViewId').getAddCustomerForm().getForm();
		var cId = customerForm.findField('cId').getValue();
		window.parent.allChildrenParamsMap.put('cId', cId);
		if(type=='reserve'){
	    	// 预约页面
			window.parent.initTabpanel('103001', '预约列表', '/crm-web/sales/reserveMain.action', true);
		}else if(type=='chats'){
			// 洽谈页面
			window.parent.initTabpanel('103002', '洽谈列表', '/crm-web/sales/chatsMain.action', true);
		}
}
/**
 * 意向信息赋值
 * 
 * @param form
 * @param response
 * @author 丁勇
 * @date 2015年6月24日
 * @update
 */
function setIntentionData(form,response){
	var customerEntity = response.customerEntity;
	if(customerEntity!=null){
		//id
		form.findField('accountId').setValue(customerEntity.id);
		//企业
		form.findField('enterpriseName').setValue(customerEntity.enterpriseName);
		//主要货物名称
		form.findField('mainGoodsName').setValue(customerEntity.mainGoodsName);
		//主要包装方式
		form.findField('typeOfPackage').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.typeOfPackage, "CUSTOMER_PACKAGING"))
		//邮编
		form.findField('deliveryAddressPostalCode').setValue(customerEntity.deliveryAddressPostalCode);
		//上门取货地址
		form.findField('deliveryAddress').setValue(customerEntity.deliveryAddress);
		//期望营销类型
		form.findField('marketActiveType').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.marketActiveType,"MARKETING_TYPE"));
		//信用评分
		form.findField('accountCreditGrade').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountCreditGrade,"CUSTOMER_CREDIT_GRADE"));
		//营销描述
		form.findField('marketActiveRemark').setValue(customerEntity.marketActiveRemark);
	}
}