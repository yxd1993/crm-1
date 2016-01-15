/*回滚版本*/
var addCustomerMask; 
Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/AddCustomer',
    controllers: ["AddCustomer"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	// LOADMASK初始化
    	addCustomerMask = new Ext.LoadMask({
    	    msg    : '数据保存中...',
    	    target : Ext.getCmp('addCustomerViewId')
    	});
		
        // 如果cId不为空，则为修改客户信息
    	if(!Ext.isEmpty(cId)){
    		var params = {};
    		var customerEntity = {};
    		customerEntity.id = cId;
    		params.customerEntity = customerEntity;
    		crm.requestJsonAjax('customerAction!queryCustomerInfoById.action', params, 
    				function(response){
    					if(response.success){
    						var customerEntity = response.customerEntity;
    						var contactEntity = response.customerEntity.contactEntity;
    						// 组装参数
    						var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
    						// 主键
    						form.findField('cId').setValue(cId);
    						//dc账号
    						form.findField('dcAccount').setValue(customerEntity.dcAccount);
    						form.findField('accountCode').setValue(customerEntity.accountCode);
    						// 二级编码
    						form.findField('tierCode').setCombValue(customerEntity.tierCode, customerEntity.tierCode);
    						form.findField('tierCode').setRealValue(customerEntity.tierCode);
    						// 改签门店
    						form.findField('signTierCode').setCombValue(customerEntity.signTierCode, customerEntity.signTierCode);
    						form.findField('signTierCode').setRealValue(customerEntity.signTierCode);
    						
    						form.findField('statusCode').setValue(customerEntity.statusCode);
    						form.findField('businessUnitCode').deptCode = customerEntity.businessUnitCode;
    						form.findField('regionsCode').deptCode = customerEntity.regionsCode;
    						form.findField('roadAreaCode').deptCode = customerEntity.roadAreaCode;
    						// 事业部、大区、事业部名称
    						form.findField('businessUnitCode').setValue(customerEntity.businessUnitName);
    						form.findField('regionsCode').setValue(customerEntity.regionsName);
    						form.findField('roadAreaCode').setValue(customerEntity.roadAreaName);
    						//不能修改,权限判断
                            if(!isButtonHide("101001017")){
                                form.findField('accountType').setReadOnlyByCss(false);
                            }else{
                                form.findField('accountType').setReadOnlyByCss(true);
                            }
    						form.findField('accountType').setValue(customerEntity.accountType);
    						form.findField('accountRatingCode').setValue(customerEntity.accountRatingCode);
    						form.findField('accountRatingCode').setReadOnlyByCss(false);
    						form.findField('enterpriseName').setValue(customerEntity.enterpriseName);
    						form.findField('enterpriseBillHead').setValue(customerEntity.enterpriseBillHead);
    						//企业性质  关联字段DISABLED
    						if(!Ext.isEmpty(customerEntity.enterpriseType)){
    							form.findField('enterpriseType').setValue(customerEntity.enterpriseType);
    							form.findField('enterpriseType').fireEvent('select', form.findField('enterpriseType'));
    						}
    						//企业性质  关联字段DISABLED
    						if(!Ext.isEmpty(customerEntity.accountSub)){
    							form.findField('accountSub').setValue(customerEntity.accountSub);
    							form.findField('accountSub').fireEvent('select', form.findField('accountSub'));
    						}
    						// 所属总公司名称
    						form.findField('parentCompany').setCombValue(customerEntity.parentCompanyName, customerEntity.parentCompany);
    						form.findField('parentCompany').setRealValue(customerEntity.parentCompany);
    						form.findField('industryCode').setValue(customerEntity.industryCode);
    						form.findField('accountCreditGrade').setValue(customerEntity.accountCreditGrade);
    						form.findField('accountChannel').setValue(customerEntity.accountChannel);
    						form.findField('detailedAddress').setValue(customerEntity.detailedAddress);
    						form.findField('detailedAddressPostalCode').setValue(customerEntity.detailedAddressPostalCode);
    						form.findField('mainGoodsName').setValue(customerEntity.mainGoodsName);
    						form.findField('deliveryAddress').setValue(customerEntity.deliveryAddress);
    						form.findField('deliveryAddressPostalCode').setValue(customerEntity.deliveryAddressPostalCode);
    						form.findField('typeOfPackage').setValue(customerEntity.typeOfPackage);
    						// 负责人
    						form.findField('managePerson').setCombValue(customerEntity.managePerson, customerEntity.managePerson);
    						form.findField('managePerson').setRealValue(customerEntity.manageEmpCode);
    						form.findField('accountRemark').setValue(customerEntity.accountRemark);
    						//期望营销类型
    						form.findField('marketActiveType').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.marketActiveType,"MARKETING_TYPE"));
    						//营销描述
    						form.findField('marketActiveRemark').setValue(customerEntity.marketActiveRemark);
    						// 联系人信息
    						/*form.findField('contactName').setValue(contactEntity.contactName);
    						form.findField('districtNumber').setValue(contactEntity.districtNumber);
    						form.findField('telephone').setValue(contactEntity.telephone);
    						form.findField('cellphone').isFirst = true;
    						form.findField('cellphone').setValue(contactEntity.cellphone);
    						form.findField('eMailAddress').setValue(contactEntity.eMailAddress);*/
    					}else{
    						Ext.MessageBox.alert('提示','查询客户信息失败'); 
    					}
    				}, 
    				function(){Ext.MessageBox.alert('提示','查询客户信息失败'); });
    		
    		// 联系人信息
    		Ext.getCmp('addCustomerViewId').getContactGrid().getStore().load();
    	}else{
    		// 组装参数
			var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
			// 手机、座机二选一
			form.findField('cellphone').setValue(null);
			form.findField('telephone').setValue(null);
    		// 全称/负责人/门店修改
			form.findField('managePerson').setReadOnlyByCss(true);
			form.findField('managePerson').setCombValue(getUserContext().getCurrentUser().empEntity.empName, getUserContext().getCurrentUser().empEntity.empCode);
			form.findField('accountRatingCode').setReadOnlyByCss(true);
			form.findField('enterpriseName').setReadOnly(false);
			form.findField('tierCode').setReadOnlyByCss(false);
			form.findField('signTierCode').setHidden(true);
            // 客户性质不能编辑,默认为潜在客户
            if(!isButtonHide("101001017")){
                form.findField('accountType').setReadOnlyByCss(false);
            }else{
                form.findField('accountType').setReadOnlyByCss(true);
            }
			// 如果customerInfoPoolId不为空，则为客户共享转潜在客户信息
			if(!Ext.isEmpty(customerInfoPoolId)){
				var params = {};
	    		var customerInfoPoolEntity = {};
	    		customerInfoPoolEntity.id = customerInfoPoolId;
	    		params.customerInfoPoolEntity = customerInfoPoolEntity;
	    		crm.requestJsonAjax('customerInfoPoolAction!queryCustomerInfoPoolById.action', params, 
	    				function(response){
	    					if(response.success){
	    						var customerInfoPoolEntity = response.customerInfoPoolEntity;
	    						// 组装参数
	    						var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
	    						//初始化参数
	    						form.findField('enterpriseName').setValue(customerInfoPoolEntity.companyName);
	    						form.findField('contactName').setValue(customerInfoPoolEntity.contactPerson);
	    						form.findField('cellphone').setValue(customerInfoPoolEntity.contactWay);
	    						form.findField('telephone').setValue(customerInfoPoolEntity.contactWay);
	    						form.findField('eMailAddress').setValue(customerInfoPoolEntity.email);
	    						form.findField('detailedAddress').setValue(customerInfoPoolEntity.companyAddress);
	    					}else{
	    						Ext.MessageBox.alert('提示','查询共享客户信息失败'); 
	    					}
	    				}, 
	    				function(){Ext.MessageBox.alert('提示','查询共享客户信息失败'); });
			}else if(!Ext.isEmpty(obhUserId)){
				var params = {};
	    		params.cId = obhUserId;
	    		crm.requestJsonAjax('personalCustomerAction!lookPersonalCustomer.action', params, 
	    				function(response){
	    					if(response.success){
	    						var personalCustomerEntity = response.personalCustomerEntity;
	    						// 组装参数
	    						var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
	    						//初始化参数
	    						form.findField('enterpriseName').setValue(personalCustomerEntity.enterpriseName);
	    						form.findField('contactName').setValue(personalCustomerEntity.contactName);
	    						form.findField('cellphone').setValue(personalCustomerEntity.cellphone);
	    						form.findField('telephone').setValue(personalCustomerEntity.telephone);
	    						form.findField('eMailAddress').setValue(personalCustomerEntity.email);
	    						form.findField('detailedAddress').setValue(personalCustomerEntity.detailedAddress);
	    						form.findField('cellphone').setReadOnly(true);
	    						form.findField('cellphone').setFieldStyle("background-color:#ececec;");
	    						form.findField('managePerson').setReadOnlyByCss(false);
	    					}else{
	    						Ext.MessageBox.alert('提示','查询个人客户信息失败'); 
	    					}
	    				}, 
	    				function(){Ext.MessageBox.alert('提示','查询个人客户信息失败'); });
			}
    	}
    }
});
