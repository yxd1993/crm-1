/**
 * 客户信息Controller
 */
Ext.define('crm.controller.Customer', {
    extend: 'Ext.app.Controller',
    stores: ['Customer'],
    models: ['Customer'],
    views: ['Viewport', 'customer.List','customer.Search','customer.Transfer','customer.AddIntentionForm'],
    init: function () {
        this.control({
            'customerSearch button[action=reset]': {
                click: this.resetCustomerSearchForm
            },
            'customerSearch button[action=select]': {
                click: this.reloadCustomerGridStore
            },
            'customerSearch button[action=add]': {
                click: this.addCustomer
            },
            'customerSearch button[action=update]': {
                click: this.updateCustomer
            },
            'customerSearch button[action=delete]': {
                click: this.deleteCustomer
            },
            'customerSearch button[action=transfer]': {
                click: this.transferCustomer
            },
            'customerlist': {
                itemdblclick: this.lookCustomer
            },
            'customerSearch button[action=addContract]': {
                click: this.addContract
            },
            'customerSearch button[action=addReserve]': {
                click: this.addReserve
            },
            'customerSearch button[action=addChats]': {
                click: this.addChats
            },
            'customerSearch button[action=turnToIntention]': {
                click: this.turnToIntention
            },
            'intentionAddWin button[action=submitIntention]':{
            	click:this.submitIntention
            },
            'intentionAddWin button[action=close]':{
            	click:this.closeWin
            },
            'customerSearch button[action=nearCustomer]': {
            	click: this.nearCustomer
        	},
            'customerTransferWin button[action=reset]': {
            	click: this.resetTransferCustomer
        	},
            'customerTransferWin button[action=submit]': {
            	click: this.submitTransferCustomer
        	}
        });
    },
    resetCustomerSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadCustomerGridStore : function(btn){
    	Ext.getCmp('customerViewId').getCustomerGrid().getPagingToolbar().moveFirst();
    },
    addCustomer : function(){
    	//新增客户
    	window.parent.initTabpanel('101002', '新增客户', '/crm-web/customer/addCustomer.action', true);
    },
    updateCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerViewId').getCustomerGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的客户信息');
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个客户信息进行修改');
		}else {
			// 修改客户
        	window.parent.delTabpanel('101003');
        	var cId = selectArr[0].get('id');
        	var url = '/crm-web/customer/addCustomer.action?cId=' + cId;
        	window.parent.initTabpanel('101003', '修改客户', url, true);
		}
    },
    deleteCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerViewId').getCustomerGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
				//删除的信息集合
				var deleteInfoArr = [];
				for(var i=0; i<selectArr.length; i++){
					deleteInfoArr.push(selectArr[i].get('id'))
				}
				Ext.MessageBox.show({
			        title: "删除客户",
			        msg: "请填写删除原因：",
			        width: 350,
			        buttons: Ext.MessageBox.OKCANCEL,
			        multiline: true,
			        animateTarget: btn,
			        fn: delCustomer,
			    });
				function delCustomer(btn,text){
					if (btn == 'ok') {
						Ext.Msg.confirm('提示', '您确定要删除选中的客户信息？该操作不可恢复', function(btn) {
							if(btn == 'yes') {
								if(text.length>500){
				    				Ext.MessageBox.alert('提示','删除客户信息原因过长');
				    				return ;
				    			}else{
					    			if(text!=''){
					    				var params = {};
										var customerEntity = {};
										customerEntity.delDesc = text
										params.ids = deleteInfoArr;
										params.customerEntity = customerEntity;
										// AJAX请求
										crm.requestJsonAjax('customerAction!deleteCustomer.action', params, 
												function(){
													Ext.MessageBox.alert('提示','客户信息删除成功');
													Ext.getCmp('customerViewId').getCustomerGrid().getStore().reload();
												}, 
												function(){Ext.MessageBox.alert('提示','客户信息删除失败');});
					    			}else{
					    				Ext.MessageBox.alert('提示', '删除失败,尚未填写删除原因');
					    				return;
					    			}
								}
							}
						})
					}
				}
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的客户信息');
		}
    },
    transferCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerViewId').getCustomerGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
				//转让的信息集合
				var transferInfoArr = [];
				for(var i=0; i<selectArr.length; i++){
					transferInfoArr.push(selectArr[i].get('id'))
				}
				Customer.ids = transferInfoArr;
				var win = Ext.widget("customerTransferWin");
				win.show();
		}else{
			Ext.MessageBox.alert('提示','请选择需要转让的客户信息');
		}
    },
    lookCustomer : function(btn){
    	// 查看客户
    	window.parent.delTabpanel('101001201');
    	var selectArr = Ext.getCmp('customerViewId').getCustomerGrid().getSelectionModel().getSelection();
    	var cId = selectArr[0].get('id');
    	var url = '/crm-web/customer/lookCustomer.action?cId=' + cId;
    	window.parent.initTabpanel('101001201', '查看客户信息', url, true);
    },
    addReserve:function(btn){
    	turnToAddInfo(btn,'reserve');
    },
    addChats:function(btn){
    	turnToAddInfo(btn,'chats');
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
		crm.requestJsonAjax('customerAction!updateCustomerTurnIntention.action', params, 
				function(response){
					Ext.MessageBox.alert('提示','转为意向成功');
					Ext.getCmp('customerViewId').getCustomerGrid().getStore().reload();
					win.close();
				}, 
				function(response){Ext.MessageBox.alert('提示', response.message);});
    	
    },
    turnToIntention:function(btn){
    	turnToIntention();
    },
    closeWin:function(btn){
    	// 关闭
    	var win = btn.up('window');
    	win.close();
    },
    nearCustomer:function(btn){
    	window.parent.initTabpanel('103004', '附近客户', '/crm-web/customer/nearCustomerMap.action', true);
    },
//    initializeCustomer:function(btn){
//    	initializeCustomerMask.show();
//    	crm.requestJsonAjax('customerLatlngAction!initializeCustomerLatLng.action', null, 
//				function(response){
//    		initializeCustomerMask.hide();
//    			Ext.MessageBox.alert('提示','客户信息初始化完成');
//				}, 
//		function(response){initializeCustomerMask.hide();Ext.MessageBox.alert('提示', response.message);});
//    }
    resetTransferCustomer:function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    },
    submitTransferCustomer:function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	// 提示正在保存中
    	win.getEl().mask("数据保存中...");
    	Ext.Msg.confirm('提示', '您确定要转让选中的客户？', function(btn) {
			if (btn == "yes"){
				// 保存信息
		    	var params = {};
		    	var transferCustomerVO = {};
		    	transferCustomerVO.ids = Customer.ids;
		    	transferCustomerVO.newManagerCode = form.findField('newManagerCode').getValue();
		    	params.transferCustomerVO = transferCustomerVO;
				// AJAX请求
				crm.requestJsonAjax('customerAction!transferCustomer.action', params, 
					function(response){
						// 提示正在保存中
						win.getEl().unmask();
						Ext.MessageBox.alert('提示','客户转让成功',function(){
							Ext.getCmp('customerViewId').getCustomerGrid().getStore().reload();
					    	win.close();
						});
					},
					function(response){
						// 提示正在保存中
						win.getEl().unmask();
						Ext.MessageBox.alert('提示', '客户转让失败');
					});
			}
    	})
    }
});