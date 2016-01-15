Ext.define("crm.view.customer.AddIntentionForm", {
	id : 'intentionAddWinId',
	extend : "Ext.window.Window",
	alias : "widget.intentionAddWin",
	title : "转为意向",
	width : 850,
	height : 280,
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
			regex : /^\S*$/,
			regexText : '不能含有空格',
			allowBlank : false
		},
		items : [ {
			xtype : 'textfield',
			name : "enterpriseName",
			fieldLabel : '客户企业全称',
			readOnly:true,
		},{
			xtype : "hiddenfield",
			name : "accountId"
		}, {
			xtype : "textfield",
			name:"mainGoodsName",
			fieldLabel : '主要货物名称<font color="#FF0000"><b>*</b></font>',
			maxLength : 200
		},{
			xtype : 'dataDictionarySelector',
			name: 'typeOfPackage',
			fieldLabel: '主要包装方式<font color="#FF0000"><b>*</b></font>',
	        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_PACKAGING', null, null, null),
		},{
			xtype : "textfield",
			name: 'deliveryAddressPostalCode',
			fieldLabel:'取货邮编',
            maxLength : 10,
            regex : /^\d+$/,
			allowBlank : true		
		},{
			xtype : "textfield",
			name : "deliveryAddress",
			fieldLabel : "上门取货地址",
			maxLength : 200,
			columnWidth: .658,	
			allowBlank : true
		},{
			xtype : 'dataDictionarySelector',
			name: 'marketActiveType',
			fieldLabel: '期望营销活动<font color="#FF0000"><b>*</b></font>',
	        store: getDataDictionary().getDataDictionaryStore('MARKETING_TYPE', null, null, null)
		},{
			xtype : 'dataDictionarySelector',
			name: 'accountCreditGrade',
			fieldLabel: '客户信用评分',
	        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null),
	        allowBlank : true
		},{
			xtype:"textarea",
			name:"marketActiveRemark",
			fieldLabel : '营销活动详述<font color="#FF0000"><b>*</b></font>',
			maxLength : 500,
			columnWidth: .987,
			height:70
		}],
		buttons : [ {
			id:'submit',
			text :'保存意向信息',
			action :'submitIntention'
		},{
			text :'取消',
			action :'close'
		}]
	}
});
