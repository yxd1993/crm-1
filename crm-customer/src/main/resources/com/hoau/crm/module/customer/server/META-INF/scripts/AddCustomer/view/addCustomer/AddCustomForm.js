/*回滚版本*/
/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.addCustomer.AddCustomForm", {
    extend: "Ext.form.Panel",
    alias: 'widget.addCustomerForm',
    width : '100%',
    autoScroll : true,
    layout: 'column',
    region : 'north',
    items: [{
		xtype: 'form',
		columnWidth : 1,
        title: '客户基本信息',
        animCollapse: true,
        bodyPadding: 5,
        height : 255,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        defaultType: 'textfield',
        defaults:{
    		msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.33,
    		labelAlign: 'left',
            regex : /^\S*$/,
            regexText : '不能含有空格'
    	},
    	items : [{
            xtype: 'hiddenfield',
            name: 'cId'
        },{
            xtype: 'hiddenfield',
            name: 'dcAccount'
        },{
            xtype: 'hiddenfield',
            name: 'accountCode'
        },{
    		fieldLabel: '负责人<font color="#FF0000"><b>*</b></font>',
            name: 'managePerson',
            xtype : 'empCommonSelector',
            fieldStyle : 'background-color:#ececec;',
            readOnly  : true
    	},{
    		fieldLabel: '门店代码<font color="#FF0000"><b>*</b></font>',
            name: 'tierCode',
            xtype : 'deptCommonSelector',
            allowBlank : false,
            fieldStyle : isButtonHide('101001005') ? 'background-color:#ececec;' : null,
            readOnly  : isButtonHide('101001005'),
            listeners : {
            	'select' : function(combo, record){
            		// 部门代码
            		var logistCode = record[0].get('logistCode');
            		// 路区组件
					var businessUnitField = combo.nextSibling().nextSibling();
					var regionsField = businessUnitField.nextSibling();
					var roadAreaField = regionsField.nextSibling();
            		// 验证是否包含汉字、数字
            		if(!logistCode.match(/.*[\u4e00-\u9fa5]+.*$/) || !logistCode.match(/\d+/g)) { 
            			Ext.MessageBox.alert('提示','请选择正确的门店信息'); 
            			// 清空路区、大区、事业部
            			combo.reset();
            			roadAreaField.setValue(null);
						roadAreaField.setDeptCode(null);
						regionsField.setValue(null);
						regionsField.setDeptCode(null);
						businessUnitField.setValue(null);
						businessUnitField.setDeptCode(null);
						return;
            		}
            		// 调用后台查询路区、大区、事业部信息
            		var params = {};
					params.deptEntity = {"logistCode" : logistCode};
            		crm.requestJsonAjax('../bse/queryDeptSuperiorDeptByCustomer.action', params, 
							function(response){
								if(response.success){
									roadAreaField.setValue(response.departmentVo.roadAreaName);
									roadAreaField.setDeptCode(response.departmentVo.roadAreaCode);
									regionsField.setValue(response.departmentVo.regionsName);
									regionsField.setDeptCode(response.departmentVo.regionsCode);
									businessUnitField.setValue(response.departmentVo.businessUnitName);
									businessUnitField.setDeptCode(response.departmentVo.businessUnitCode);
								}else{
									Ext.MessageBox.alert('提示','查询路区、大区、事业部信息失败'); 
								}
							}, 
							function(){Ext.MessageBox.alert('提示','查询路区、大区、事业部信息失败'); });
            	}
            }
    	},{
    		fieldLabel: '改签门店',
            name: 'signTierCode',
            xtype : 'deptCommonSelector',
            hidden :isButtonHide('101001014')
    	},{
    		fieldLabel: '事业部',
            name: 'businessUnitCode',
            deptCode : null,
            setDeptCode : function(deptCode){
            	this.deptCode = deptCode
            },
            editable : false
    	},{
    		fieldLabel: '经营大区',
            name: 'regionsCode',
            deptCode : null,
            setDeptCode : function(deptCode){
            	this.deptCode = deptCode
            },
            editable : false
    	},{
    		fieldLabel: '路区',
            name: 'roadAreaCode',
            deptCode : null,
            setDeptCode : function(deptCode){
            	this.deptCode = deptCode
            },
            editable : false
    	},{
    		fieldLabel: '客户级别',
            name: 'accountRatingCode',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_LEVEL', null, null, null),
            value : '200001'
    	},{
    		fieldLabel: '客户性质',
            name: 'accountType',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_NATURE', null, null, null),
            value : '1'
    	},{
    		fieldLabel: '状态描述',
            name: 'statusCode',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_STATUS', null, null, null),
            value : '1'
    	},{
    		fieldLabel: '客户信用评分',
            name: 'accountCreditGrade',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null)
    	},{
    		fieldLabel: '客户渠道<font color="#FF0000"><b>*</b></font>',
            name: 'accountChannel',
            xtype : 'dataDictionarySelector',
            allowBlank : false,
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CHANNEL', null, null, null)
    	},{
    		fieldLabel: '所属行业<font color="#FF0000"><b>*</b></font>',
            name: 'industryCode',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_INDUSTRY', null, null, null),
            allowBlank : false
    	},{
    		fieldLabel: '客户企业全称<font color="#FF0000"><b>*</b></font>',
            name: 'enterpriseName',
            allowBlank : false,
            maxLength:200,
            maxLengthText:"客户企业全称不能超过200个字符",
            readOnly  : isButtonHide('101001005'),
            regex : /^[\u4e00-\u9fa5a-zA-z()（）]+$/,
            regexText : '只能包含中文、字母与括号',
            listeners : {
            	'change' : function(filed){
            		var enterpriseTypeField = filed.nextSibling();
            		var accountSubField = enterpriseTypeField.nextSibling(); 
            		var parentCompanyField = accountSubField.nextSibling();
            		var enterpriseBillHeadField = parentCompanyField.nextSibling();
            		enterpriseBillHeadField.setValue(filed.getValue());
            	}
            }
    	},
    	{
    		fieldLabel: '企业性质<font color="#FF0000"><b>*</b></font>',
            name: 'enterpriseType',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_ENTERPRISE_NATURE', null, null, null),
            value : '0',
            allowBlank : false,
            listeners : {
            	'select' : function(combo){
            		var accountSubField = combo.nextSibling(); 
            		var parentCompanyField = accountSubField.nextSibling();
            		// 个人
            		if(combo.getValue() == '1'){
            			accountSubField.setValue(null);
            			parentCompanyField.setValue(null);
            			accountSubField.setDisabled(true);
            			parentCompanyField.setDisabled(true);
            		// 企业
            		}else{
            			accountSubField.setDisabled(false);
            			parentCompanyField.setDisabled(false);
            		}
            	}
            }
    	},{
    		fieldLabel: '客户分类',
            name: 'accountSub',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_GRADETYPE', null, null, null),
            value : '1',
            listeners : {
            	'select' : function(combo){
            		var parentCompanyField = combo.nextSibling();
            		// 个人
            		if(combo.getValue() == '1'){
            			parentCompanyField.setValue(null);
            			parentCompanyField.setDisabled(true);
            		// 企业
            		}else{
            			parentCompanyField.setDisabled(false);
            		}
            	}
            }
    	},{
    		fieldLabel: '所属总公司',
            name: 'parentCompany',
            xtype : 'customerCommonSelector',
            disabled : true
    	},{
    		fieldLabel: '企业发票抬头',
            name: 'enterpriseBillHead'
    	}]
	},{
		xtype: 'form',
		columnWidth : 1,
        title: '联系人信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 120,
        defaultType: 'textfield',
        disabled : !Ext.isEmpty(cId),
        hidden : !Ext.isEmpty(cId),
        defaults:{
    		msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.33,
    		labelAlign: 'left',
            regex : /^\S*$/,
            regexText : '不能含有空格'
    	},
    	items : [{
    		fieldLabel: '联系人姓名<font color="#FF0000"><b>*</b></font>',
            name: 'contactName',
            allowBlank : false,
            maxLength:200,
            maxLengthText:"联系人姓名不能超过200个字符"
    	},{
    		maxLength : 11,
    		fieldLabel: '手机<font color="#FF0000"><b>*</b></font>',
    		emptyText : '"手机"与"座机"必须二选一',
            name: 'cellphone',
            regex : /^\d+$/,
            regexText :"手机填写不合法",
            minLength : 11,
            isFirst : false,
            listeners : {
            	change : function(field, newValue, oldValue){
            		if(field.isFirst){
            			field.isFirst = false;
            			return;
            		}
            		// 手机号合法性判断
            		if(/^\d+$/.test(newValue) && /^\S*$/.test(newValue) && newValue.length == 11){   
            			var params = {};
            			var phoneInfoEntity = {};
            			phoneInfoEntity.mobile = newValue;
            			params.phoneInfoEntity = phoneInfoEntity;
            			crm.requestJsonAjax('customerAction!queryPhoneInfoByPhone.action', params, 
            					function(response){
            						var phoneInfo = response.phoneInfoEntity;
            						if(!Ext.isEmpty(phoneInfo.province)){
            							var form = Ext.getCmp('addCustomerViewId').getAddCustomerForm().getForm();
            							var province = phoneInfo.province;
            							var address = "";
            							if(province == '北京' || province == '天津' || province == '上海' || province == '重庆'){
            								address = phoneInfo.city + '市';
            							}else if(province == '内蒙古'){
            								address = '内蒙古自治区' + phoneInfo.city + '市';
            							}else if(province == '新疆'){
            								address = '新疆维吾尔自治区' + phoneInfo.city + '市';
            							}else if(province == '广西'){
            								address = '广西壮族自治区' + phoneInfo.city + '市';
            							}else if(province == '宁夏'){
            								address = '宁夏回族自治区' + phoneInfo.city + '市';
            							}else if(province == '西藏'){
            								address = '西藏自治区' + phoneInfo.city + '市';
            							}else{
            								address = phoneInfo.province + '省' + phoneInfo.city + '市';
            							}
            							form.findField('districtNumber').setValue(phoneInfo.areaCode);
            							form.findField('detailedAddress').setValue(address);
            							form.findField('detailedAddressPostalCode').setValue(phoneInfo.postCode);
            							form.findField('deliveryAddress').setValue(address);
            							form.findField('deliveryAddressPostalCode').setValue(phoneInfo.postCode);
            						}
            					}, 
            					function(response){
            						Ext.MessageBox.alert('提示', response.message); 
            					});
            	    }  
            	}
            }
    	},{
    		fieldLabel: '区号<font color="#FF0000"><b>*</b></font>',
    		name: 'districtNumber',
    		maxLength : 4,
            allowBlank : false,
            regex : /^\d+$/,
            regexText :"区号填写不合法"
    	},{
    		maxLength : 20,
    		fieldLabel: '座机<font color="#FF0000"><b>*</b></font>',
    		emptyText : '"手机"与"座机"必须二选一',
            name: 'telephone'
    	},{
    		fieldLabel: '电子邮件',
    		vtype:'email',
            name: 'eMailAddress'
    	},{
    		fieldLabel: '职位',
            name: 'job'
    	}]
    },{
		xtype: 'form',
		columnWidth : 1,
        title: '客户发货信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 310,
        defaultType: 'textfield',
        defaults:{
    		msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.33,
    		labelAlign: 'left',
            regex : /^\S*$/,
            regexText : '不能含有空格'
    	},
    	items : [{
    		fieldLabel: '办公地址<font color="#FF0000"><b>*</b></font>',
            name: 'detailedAddress',
            maxLength:200,
            columnWidth : 0.66,
            allowBlank : false,
            maxLengthText:"办公地址不能超过200个字符"
    	},{
    		maxLength : 10,
    		fieldLabel: '办公地址邮编',
            name: 'detailedAddressPostalCode',
            regex : /^\d+$/,
            regexText: "办公地址邮编填写不合法"
    	},{
    		fieldLabel: '取货详细地址',
            name: 'deliveryAddress',
            columnWidth : 0.66,
            maxLength:400,
            maxLengthText:"取货详细地址不能超过400个字符"
    	},{
    		fieldLabel: '取货地址邮编',
            name: 'deliveryAddressPostalCode',
            maxLength : 10,
            regex : /^\d+$/,
            regexText: "取货地址邮编填写不合法"
    	},{
    		fieldLabel: '主要货物名称',
            name: 'mainGoodsName',
            maxLength:200,
            maxLengthText:"主要货物名称不能超过200个字符"
    	},{
    		fieldLabel: '主要包装方式',
            name: 'typeOfPackage',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_PACKAGING', null, null, null)
    	},{
			xtype : 'dataDictionarySelector',
			name: 'marketActiveType',
			fieldLabel: '期望营销活动',
	        store: getDataDictionary().getDataDictionaryStore('MARKETING_TYPE', null, null, null)
    	},{
    		xtype:"textarea",
			name:"marketActiveRemark",
			fieldLabel : '营销活动详述',
			maxLength : 500,
			columnWidth : 0.99,
            maxLengthText : "营销活动详述不能超过500个字符" 
    	},{
    		fieldLabel: '备注',
            name: 'accountRemark',
            xtype : 'textareafield',
            columnWidth : 0.99,
            maxLength : 500,
            maxLengthText : "客户备注不能超过500个字符" 
    	}]
	},{
		xtype: 'form',
		columnWidth : 1,
        title: '客户联系人信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        style : 'margin-bottom:20px;',
        //height : 200,
        hidden : Ext.isEmpty(cId),
        defaultType: 'textfield',
    	items : [{
    		columnWidth : 0.99,
    		//height : 145,
    		xtype : 'contactList'
    	}]
	}],
    buttons: [{
        text: '保存',
        action : 'submit'
    }, {
        text: '取消',
        action : 'reset'
    }]
});
