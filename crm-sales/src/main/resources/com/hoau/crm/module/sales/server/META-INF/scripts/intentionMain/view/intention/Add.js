Ext.define("crm.view.intention.Add", {
	id : 'intentionAddWinId',
	extend : "Ext.window.Window",
	alias : "widget.intentionAddWin",
	title : "新增意向",
	width : 850,
	height : 420,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		record : null,
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.329,
			margin : '5 0 5 30 ',
			labelWidth : 115,
			allowBlank : false
		},
		items : [ {
			xtype : "hiddenfield",
			name : "id"
		}, {
			xtype : 'customerCommonSelector',
			name : "enterpriseName",
			fieldLabel : '客户企业全称<font color="#FF0000"><b>*</b></font>',
			maxLength : 100,
			regex : /^\S*$/,
			regexText : '不能含有空格',
			listeners : {
				'select' : function(combo, recrod){
					var form = combo.up('form').getForm();
					// 办公地址
					form.findField('detailedAddress').setValue(recrod[0].get('detailedAddress'));
					//上门取货地址
					form.findField('deliveryAddress').setValue(recrod[0].get('deliveryAddress'));
					//取货邮编地址
					form.findField('deliveryAddressPostalCode').setValue(recrod[0].get('deliveryAddressPostalCode'));
					//客户id
					form.findField('accountId').setValue(recrod[0].get('id'));
					// 刷新联系人
					var contactName = form.findField('contactName');
					contactName.getStore().setAccountId(recrod[0].get('id'));
					contactName.reset();
					contactName.getStore().load();
				}
			}
		}, {
			xtype : "hiddenfield",
			name : "accountId"
		},{
			xtype : "textfield",
			name:"goodsName",
			fieldLabel : '主要货物名称<font color="#FF0000"><b>*</b></font>',
			regex : /^\S*$/,
			regexText : '不能含有空格',
			maxLength : 200
		},{
			xtype : 'dataDictionarySelector',
			name: 'packaging',
			fieldLabel: '主要包装方式<font color="#FF0000"><b>*</b></font>',
	        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_PACKAGING', null, null, null),
		}, {
			xtype : "combo",
			name : "contactName",
			fieldLabel : '联系人姓名',
			store : 'Contact',
			displayField : 'contactName',
			valueField : 'id', 
			editable : false,
			allowBlank : true,
			listeners : {
				'select' : function(combo, record){
					var form = combo.up('form').getForm();
					// 联系电话
					if(!Ext.isEmpty(record[0].data['cellphone'])){
						form.findField('cellphone').setValue(record[0].data['cellphone']);
					} else if(!Ext.isEmpty(record[0].data['telephone'])){
						form.findField('cellphone').setValue(record[0].data['telephone']);
					} else {
						form.findField('cellphone').reset();
					}
				}
			}
		}, {
			xtype : "textfield",
			name : "cellphone",
			fieldLabel : "联系电话",
			maxLength : 200,
			readOnly : true,
			allowBlank : true
		},{
			xtype : "textfield",
			name : "deliveryAddressPostalCode",
			fieldLabel : "取货邮编",
			maxLength : 200,
			readOnly : true,
			allowBlank : true
		}, {
			xtype : "textfield",
			name : "detailedAddress",
			fieldLabel : "办公地址",
			maxLength : 200,
			columnWidth: 1,		
			readOnly : true,
			allowBlank : true
		},{
			xtype : "textfield",
			name : "deliveryAddress",
			fieldLabel : "上门取货地址",
			maxLength : 200,
			columnWidth: 1,	
			readOnly : true,
			allowBlank : true
		},{
			xtype : 'dataDictionarySelector',
			name: 'activityType',
			fieldLabel: '营销活动类型',
	        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null),
	        allowBlank : true
		},{
			xtype : 'dataDictionarySelector',
			name: 'customerScore',
			fieldLabel: '客户信用评分',
	        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null),
	        allowBlank : true
		},{
			
			xtype:"textarea",
			name:"activityRemarks",
			fieldLabel : "营销活动详述",
			maxLength : 400,
			columnWidth: .987,
			height:130,
			regex : /^\S*$/,
			regexText : '不能含有空格',
			allowBlank : true
		}],
		buttons : [ {
			id:'submit',
			text : '保存',
			action : 'submit'
		}, {
			id:'reset',
			text : '重置',
			action : 'reset'
		}]
	}
});
