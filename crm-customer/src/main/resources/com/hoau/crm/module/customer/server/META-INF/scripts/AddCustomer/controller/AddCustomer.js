/*回滚版本*/
/**
 * 客户信息新增表格
 */
Ext.define('crm.controller.AddCustomer', {
	extend : 'Ext.app.Controller',
	stores: ['Contact'],
    models: ['Contact'],
	views : [ 'Viewport', 'addCustomer.AddCustomForm', 'addCustomer.AddContact', 'addCustomer.ContactList'],
	init : function() {
		this.control({
			'addCustomerForm button[action=reset]': {
                click: this.resetAddCustomerForm
            },
            'addCustomerForm button[action=submit]': {
                click: this.submitAddCustomerForm
            },
            'contactList button[action=add]': {
                click: this.addContact
            },
            'contactList button[action=edit]': {
                click: this.updateContact
            },
            'contactList button[action=delete]': {
                click: this.deleteContact
            },
            'contactList button[action=default]': {
                click: this.settingDefault
            },
            'addContactWin button[action=reset]': {
            	click: this.resetAddForm
            },
            'addContactWin button[action=submit]': {
            	click: this.submitAddForm
            }
		});
	},
	resetAddCustomerForm : function(btn) {
		//Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm().reset();
		var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
		// 关闭页面
		if(Ext.isEmpty(form.findField('cId').getValue())){
			parent.delTabpanel('101002');
		}else{
			parent.delTabpanel('101003');
		}
	},
	submitAddCustomerForm : function(btn) {
		// 组装参数
		var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
		//客户性质为意向时,参数必填
		if(form.findField('accountType').getValue() == '3'){
			form.findField('deliveryAddress').allowBlank = false;
			form.findField('deliveryAddressPostalCode').allowBlank = false;
			form.findField('typeOfPackage').allowBlank = false;
			form.findField('mainGoodsName').allowBlank = false;
			form.findField('marketActiveType').allowBlank = false;
		}
		if(!form.isValid()){
			Ext.MessageBox.alert('提示','请完善必填字段信息'); 
			return;
		}
		var params = {};
		var customerEntity = {};
		var contactEntity = {};
		// 路区、大区、事业部
		customerEntity.businessUnitCode = form.findField('businessUnitCode').deptCode;
		customerEntity.regionsCode = form.findField('regionsCode').deptCode;
		customerEntity.roadAreaCode = form.findField('roadAreaCode').deptCode;
		/*if(Ext.isEmpty(customerEntity.roadAreaCode)){
			Ext.MessageBox.alert('提示','请重新选择正确的门店代码'); 
			return;
		}*/
		//负责人
		customerEntity.manageEmpCode = form.findField('managePerson').getRealValue();
		customerEntity.managePerson = form.findField('managePerson').getRealName();
		// 二级公司
		customerEntity.tierCode = form.findField('tierCode').getRealValue();
		//改签门店
		customerEntity.signTierCode = form.findField('signTierCode').getRealValue();
		var tierName = form.findField('tierCode').getRawValue();
		// 所属总公司
		customerEntity.parentCompany = form.findField('parentCompany').getRealValue();
		var parentCompanyName = form.findField('parentCompany').getRawValue();
		if(!Ext.isEmpty(tierName) && Ext.isEmpty(customerEntity.tierCode)){
			Ext.MessageBox.alert('提示','门店代码选择错误，输入门店条件并点击查询，查询结果中选择门店'); 
			return;
		}
		if(!Ext.isEmpty(parentCompanyName) && Ext.isEmpty(customerEntity.parentCompany)){
			Ext.MessageBox.alert('提示','所属总公司选择错误，输入所属总公司条件并点击查询，查询结果中选择所属总公司'); 
			return;
		}
		/*if(!Ext.isEmpty(customerEntity.managePerson) && Ext.isEmpty(customerEntity.manageEmpCode)){
			Ext.MessageBox.alert('提示','负责人选择错误，输入负责人条件并点击查询，查询结果中选择负责人'); 
			return;
		}*/
		if(Ext.isEmpty(customerEntity.managePerson) && Ext.isEmpty(customerEntity.manageEmpCode)){
			Ext.MessageBox.alert('提示','负责人选择错误，输入负责人条件并点击查询，查询结果中选择负责人'); 
			return;
		}
		// 客户信息
		customerEntity.id = form.findField('cId').getValue();
		customerEntity.dcAccount = form.findField('dcAccount').getValue();
		customerEntity.accountCode = form.findField('accountCode').getValue();
		customerEntity.discountRate = 1;  //折扣率，默认为1
		customerEntity.statusCode = form.findField('statusCode').getValue();
		customerEntity.accountType = form.findField('accountType').getValue();
		customerEntity.accountRatingCode = form.findField('accountRatingCode').getValue();
		customerEntity.enterpriseType = form.findField('enterpriseType').getValue();
		customerEntity.enterpriseName = form.findField('enterpriseName').getValue();
		customerEntity.enterpriseBillHead = form.findField('enterpriseBillHead').getValue();
		customerEntity.accountSub = form.findField('accountSub').getValue();
		customerEntity.industryCode = form.findField('industryCode').getValue();
		customerEntity.accountCreditGrade = form.findField('accountCreditGrade').getValue();
		customerEntity.detailedAddress = form.findField('detailedAddress').getValue();
		customerEntity.detailedAddressPostalCode = form.findField('detailedAddressPostalCode').getValue();
		customerEntity.mainGoodsName = form.findField('mainGoodsName').getValue();
		customerEntity.deliveryAddress = form.findField('deliveryAddress').getValue();
		customerEntity.deliveryAddressPostalCode = form.findField('deliveryAddressPostalCode').getValue();
		customerEntity.accountChannel = form.findField('accountChannel').getValue();
		customerEntity.typeOfPackage = form.findField('typeOfPackage').getValue();
		customerEntity.accountRemark = form.findField('accountRemark').getValue();
		//期望营销类型
    	customerEntity.marketActiveType = form.findField('marketActiveType').getValue();
		//营销描述
    	customerEntity.marketActiveRemark = form.findField('marketActiveRemark').getValue();
		// 地址判断是否为默认
		if(!Ext.isEmpty(customerEntity.detailedAddress) && customerEntity.detailedAddress.substring(customerEntity.detailedAddress.length-1, customerEntity.detailedAddress.length) == '市'){
			Ext.MessageBox.alert('提示','请完善办公地址信息'); 
			return;
		}
		if(!Ext.isEmpty(customerEntity.deliveryAddress) && customerEntity.deliveryAddress.substring(customerEntity.deliveryAddress.length-1, customerEntity.deliveryAddress.length) == '市'){
			Ext.MessageBox.alert('提示','请完善取货详细地址信息'); 
			return;
		}
		// 联系人信息
		if(Ext.isEmpty(customerEntity.id)){
			contactEntity.accountId = form.findField('cId').getValue();
			contactEntity.contactName = form.findField('contactName').getValue();
			contactEntity.districtNumber = form.findField('districtNumber').getValue();
			contactEntity.telephone = form.findField('telephone').getValue();
			contactEntity.cellphone = form.findField('cellphone').getValue();
			contactEntity.job = form.findField('job').getValue();
			contactEntity.eMailAddress = form.findField('eMailAddress').getValue();
			// 手机与电话二选一
			if(Ext.isEmpty(contactEntity.telephone) && Ext.isEmpty(contactEntity.cellphone)){
				Ext.MessageBox.alert('提示','"手机"与"座机"必须二选一，建议都填'); 
				return;
			}
		}
		//如果是客户共享里面过来的客户保存成功在去修改共享里面的状态为'N'
    	if(!Ext.isEmpty(customerInfoPoolId)){
    		customerEntity.customerInfoPoolId = customerInfoPoolId;
    	}
    	//如果是个人客户过来的客户保存成功修改个人客户状态
    	if(!Ext.isEmpty(obhUserId)){
    		customerEntity.obhUserId = obhUserId;
    	}
    	
		// 组装参数
		customerEntity.contactEntity= contactEntity;
		params.customerEntity = customerEntity;
		// 提示正在保存中
		addCustomerMask.show();
		crm.requestJsonAjax('customerAction!addCustomer.action', params, 
				function(response){
					// 提示正在保存中
					addCustomerMask.hide();
					if(response.success){
						Ext.MessageBox.alert('提示','保存客户信息成功', function(){
							//如果是客户共享里面过来的客户保存成功在去修改共享里面的状态为'N'
//					    	if(!Ext.isEmpty(customerInfoPoolId)){
//					    		var param = {};
//					    		param.customerPoolID = customerInfoPoolId;
//					    		crm.requestJsonAjax('customerInfoPoolAction!updateCustomeriSpotential.action', param, function(response){},function(response){});
//					    	}
							parent.allChildrenParamsMap.put('success', 'success')
							window.parent.initTabpanel('101001', '客户管理', '/crm-web/customer/index.action', true);
							// 关闭页面
							if(Ext.isEmpty(customerEntity.id)){
								parent.delTabpanel('101002');
							}else{
								parent.delTabpanel('101003');
							}
							
						}); 
					}else{
						Ext.MessageBox.alert('提示','保存客户信息失败'); 
					}
				}, 
				function(response){
					// 提示正在保存中
					addCustomerMask.hide();
					Ext.MessageBox.alert('提示', response.message); 
				});
	},
	addContact : function(){
		// 新增客户
    	var win = Ext.widget("addContactWin");
    	win.show();
    	var form = win.down('form').getForm();
    	form.findField('cellphone').setValue(null);
    	form.findField('telephone').setValue(null);
	},
    updateContact : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('addCustomerViewId').getContactGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的联系人信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个联系人信息进行修改');
			return;
		}
    	var win = Ext.widget("addContactWin"); 
    	var form = win.down("form");
    	win.title = '修改客户联系人信息';
    	win.show();
    	form.record = selectArr[0];
    	win.down("form").loadRecord(selectArr[0]);
/*    	form.getForm().findField('cellphone').setValue(null);
    	form.getForm().findField('telephone').setValue(null);*/
    	form.getForm().findField('districtNumber').setHidden(false);
    	form.getForm().findField('districtNumber').setDisabled(false);
    },
    deleteContact : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('addCustomerViewId').getContactGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
			Ext.Msg.confirm('提示', '您确定要删除选中的联系人信息？', function(btn) {
				if(btn == 'yes') {
					//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].getData().id)
					}
					var params = {};
					params.ids = deleteInfoArr;
					// AJAX请求
					crm.requestJsonAjax('customerAction!deleteContact.action', params, 
							function(){
								Ext.MessageBox.alert('提示','联系人信息删除成功');
								Ext.getCmp('addCustomerViewId').getContactGrid().getStore().reload();
							}, 
							function(response){Ext.MessageBox.alert('提示', response.message);});
				}
			})
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的联系人信息');
		}
    },
    settingDefault : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('addCustomerViewId').getContactGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 1){
    		Ext.MessageBox.alert('提示','只能选择一个联系人进行设置');
		}else if(selectArr.length == 1){
    		//删除的信息集合
			var params = {};
			var contactEntity = {};
			contactEntity.id = selectArr[0].id;
			contactEntity.accountId = cId;
			params.contactEntity = contactEntity;
			// AJAX请求
			crm.requestJsonAjax('customerAction!updateContactIsDefault.action', params, 
					function(){
						Ext.MessageBox.alert('提示','默认联系人设置成功');
						Ext.getCmp('addCustomerViewId').getContactGrid().getStore().reload();
					}, 
					function(response){
						Ext.MessageBox.alert('提示', response.message);
						Ext.getCmp('addCustomerViewId').getContactGrid().getStore().reload();
					});
		}else{
			Ext.MessageBox.alert('提示','请选择需要设置的联系人信息');
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
    	var contactEntity = {};
    	contactEntity.id = form.findField('id').getValue();
    	contactEntity.accountId = cId;
    	contactEntity.cellphone = form.findField('cellphone').getValue();
    	contactEntity.job = form.findField('job').getValue();
    	contactEntity.contactName = form.findField('contactName').getValue();
    	contactEntity.telephone = form.findField('telephone').getValue();
    	contactEntity.districtNumber = form.findField('districtNumber').getValue();
    	contactEntity.eMailAddress = form.findField('eMailAddress').getValue();
    	// 手机与电话二选一
		if(Ext.isEmpty(contactEntity.telephone) && Ext.isEmpty(contactEntity.cellphone)){
			Ext.MessageBox.alert('提示','"手机"与"座机"必须二选一，建议都填'); 
			return;
		}
    	params.contactEntity = contactEntity;
    	var actionUrl = null;
    	if(Ext.isEmpty(contactEntity.id)){
    		actionUrl = 'customerAction!addContact.action';
    	}else{
    		actionUrl = 'customerAction!editContact.action';
    	}
    	// 提示正在保存中
    	win.getEl().mask("数据保存中...");
		// AJAX请求
		crm.requestJsonAjax(actionUrl, params, 
				function(response){
					// 提示正在保存中
					win.getEl().unmask();
					Ext.MessageBox.alert('提示','联系人信息保存成功');
					Ext.getCmp('addCustomerViewId').getContactGrid().getStore().reload();
					win.close();
				}, 
				function(response){
					// 提示正在保存中
					win.getEl().unmask();
					Ext.MessageBox.alert('提示', response.message);
					Ext.getCmp('addCustomerViewId').getContactGrid().getStore().reload();
				});
    }
});
