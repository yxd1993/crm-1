Ext.define("crm.view.intention.Detail", {
	id : 'intentionDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.intentionDetailWin",
	title : "意向详情",
	width : 850,
	height : 385,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		record : null,
		defaultType: 'textfield',
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.330,
			margin : '5 0 5 30 ',
			labelWidth : 115
		},
		defaults:{
    		readOnly : true
    	},
		items : [ {
			name : "enterpriseName",
			fieldLabel : '客户企业全称'
		}, {
			name : "goodsName",
			fieldLabel : "主要货物名称"
		}, {
			name : "packaging",
			fieldLabel : "主要包装方式"
		}, {
			name : "contactName",
			fieldLabel : "联系人姓名"
		}, {
			name : "cellphone",
			fieldLabel : "联系电话"
		}, {
			name : "deliveryAddressPostalCode",
			fieldLabel : '取货邮编地址'
		}, {
			name : "detailedAddress",
			fieldLabel : "办公地址",
			columnWidth: .987
		}, {
			name : "deliveryAddress",
			fieldLabel : "上门取货地址",
			columnWidth: .987
		},{
			name : "activityType",
			fieldLabel : "营销活动类型"
		},{
			name : "customerScore",
			fieldLabel : "客户信用评分"
		},{
			xtype:'textarea',
			name : "activityRemarks",
			fieldLabel : "营销活动详述",
			columnWidth: .987,
			height:130
		}]
	}
});
