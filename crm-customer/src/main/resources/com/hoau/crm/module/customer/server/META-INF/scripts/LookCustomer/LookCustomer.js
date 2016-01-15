/*回滚版本*/
//window对象
var intentionWin;


Ext.application({
    name: "crm",
    appFolder: '../scripts/customer/LookCustomer',
    controllers: ["LookCustomer"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
    	// LOADMASK初始化
    	lookCustomerMask = new Ext.LoadMask({
    	    msg    : '数据保存中...',
    	    target : Ext.getCmp('lookCustomerViewId')
    	});
        // 如果cId不为空，则为修改客户信息
    	if(!Ext.isEmpty(cId)){
    		var params = {};
    		var customerEntity = {};
    		customerEntity.id = cId;
    		params.customerEntity = customerEntity;
    		crm.requestJsonAjax('customerAction!queryAllCustomerInfoById.action', params, 
    				function(response){
    					if(response.success){
    						var customerEntity = response.customerEntity;
    						var contactEntity = response.customerEntity.contactEntity;
    						// 组装参数
    						var form = Ext.getCmp('lookCustomerViewId').getAddCustomerForm().getForm();
    						// 主键
    						form.findField('cId').setValue(cId);
    						form.findField('isCreateObhAccount').setValue(customerEntity.isCreateObhAccount);
    						// 客户信息
    						form.findField('accountCode').setValue(customerEntity.accountCode);
    						form.findField('dcAccount').setValue(customerEntity.dcAccount);
    						form.findField('tierCode').setValue(customerEntity.tierCode);
    						form.findField('signTierCode').setValue(customerEntity.signTierCode);
    						form.findField('statusCode').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.statusCode, "CUSTOMER_STATUS"));
    						// 事业部、大区、事业部名称
    						form.findField('businessUnitCode').setValue(customerEntity.businessUnitName);
    						form.findField('regionsCode').setValue(customerEntity.regionsName);
    						form.findField('roadAreaCode').setValue(customerEntity.roadAreaName);
    						form.findField('accountType').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountType, "CUSTOMER_NATURE"));
    						form.findField('accountRatingCode').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountRatingCode, "CUSTOMER_LEVEL"));
    						form.findField('enterpriseName').setValue(customerEntity.enterpriseName);
    						form.findField('enterpriseBillHead').setValue(customerEntity.enterpriseBillHead);
    						//企业性质  关联字段DISABLED
    						form.findField('enterpriseType').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.enterpriseType, "CUSTOMER_ENTERPRISE_NATURE"));
    						//企业性质  关联字段DISABLED
    						form.findField('accountSub').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountSub, "CUSTOMER_GRADETYPE"));
    						// 所属总公司名称
    						form.findField('parentCompany').setValue(customerEntity.parentCompanyName);
    						form.findField('industryCode').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.industryCode, "CUSTOMER_INDUSTRY"));
    						form.findField('startingTime').setValue(Ext.isEmpty(customerEntity.startingTime) ? null : Ext.util.Format.date(new Date(customerEntity.startingTime), 'Y-m-d H:i:s'));
    						form.findField('discountRate').setValue(customerEntity.discountRate);
    						form.findField('accountCreditGrade').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountCreditGrade, "CUSTOMER_CREDIT_GRADE"));
    						form.findField('accountChannel').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.accountChannel, "CUSTOMER_CHANNEL"));
    						form.findField('detailedAddress').setValue(customerEntity.detailedAddress);
    						form.findField('detailedAddressPostalCode').setValue(customerEntity.detailedAddressPostalCode);
    						form.findField('mainGoodsName').setValue(customerEntity.mainGoodsName);
    						form.findField('deliveryAddress').setValue(customerEntity.deliveryAddress);
    						form.findField('deliveryAddressPostalCode').setValue(customerEntity.deliveryAddressPostalCode);
    						form.findField('typeOfPackage').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.typeOfPackage, "CUSTOMER_PACKAGING"));
    						form.findField('accountPeriod').setValue(customerEntity.accountPeriod);
    						// 负责人
    						if(!Ext.isEmpty(customerEntity.manageEmpCode) && !Ext.isEmpty(customerEntity.managePerson)){
    							form.findField('managePerson').setValue(customerEntity.managePerson + '【'+ customerEntity.manageEmpCode + '】');
    						}else if(!Ext.isEmpty(customerEntity.managePerson)){
    							form.findField('managePerson').setValue(customerEntity.managePerson);
    						}
    						form.findField('accountRemark').setValue(customerEntity.accountRemark);
    						//网厅账号
    						form.findField('obhAccount').setValue(customerEntity.obhAccount);
    						//期望营销类型
    						form.findField('marketActiveType').setValue(getDataDictionary().rendererSubmitToDisplay(customerEntity.marketActiveType,"MARKETING_TYPE"));
    						//营销描述
    						form.findField('marketActiveRemark').setValue(customerEntity.marketActiveRemark);
    						// 发单日志
    						form.findField('waybillLog').setValue(customerEntity.waybillLog);
    						// 联系人信息
    						/*form.findField('contactName').setValue(contactEntity.contactName);
    						form.findField('districtNumber').setValue(contactEntity.districtNumber);
    						form.findField('telephone').setValue(contactEntity.telephone);
    						form.findField('cellphone').setValue(contactEntity.cellphone);
    						form.findField('eMailAddress').setValue(contactEntity.eMailAddress);*/
    					}else{
    						Ext.MessageBox.alert('提示','查询客户信息失败'); 
    					}
    				}, 
    				function(){Ext.MessageBox.alert('提示','查询客户信息失败'); });
    	}
    	 // 页面加载完成之后执行
    	parent.allChildrenPanelMap.put("101001201", Ext.getCmp('lookCustomerViewId'));
    	// 判断如果原来没有打开意向界面
    	Ext.getCmp('lookCustomerViewId').tabChange();
    }
});
