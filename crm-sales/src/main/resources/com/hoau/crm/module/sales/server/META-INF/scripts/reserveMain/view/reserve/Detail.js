Ext.define("crm.view.reserve.Detail", {
	id : 'reserveDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.reserveDetailWin",
	title : "预约详情",
	width : 940,
	height : 320,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		autoScroll: true, 
		record : null,
		defaultType: 'textfield',
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.330,
			labelWidth : 115
		},
		defaults:{
			margin : '5 0 5 30 ',
    		readOnly : true
    	},
		items : [{
			xtype : "hiddenfield",
			name : "id"
		}, {
			xtype : "hiddenfield",
			name : "accountId"
		}, {
			name : "enterpriseName",
			fieldLabel : '客户企业全称'
		}, {
			name : "reserveStartTime",
			fieldLabel : "约定时间"
		}, {
			name : "reserveEndTime",
			fieldLabel : "预约结束时间"
		}, {
			name : "warningTime",
			fieldLabel : "提醒时间"
		}, {
			name : "contactName",
			fieldLabel : '联系人姓名'
		}, {
			name : "cellphone",
			fieldLabel : "联系电话"
		}, {
			name : "reserveType",
			fieldLabel : "预约方式",
		}, {
			name : "detailedAddress",
			fieldLabel : "办公地址",
			columnWidth: .658
		},{
			name : 'tierEmpName',
			fieldLabel: '门店经理陪同',
			hidden:true
		},{
			name : 'teamManagerEmpName',
			fieldLabel: '团队经理陪同',
			hidden:true
		},{
			name : 'saleManEmpName',
			fieldLabel: '客户经理陪同',
			hidden:true
		},{
			name : 'roadEmpName',
			fieldLabel: '路区经理陪同',
			hidden:true
		},{
			name : 'regionsEmpName',
			fieldLabel: '大区总经理陪同',
			hidden:true
		},{
			name : 'businessUnitEmpName',
			fieldLabel: '事业部总经理陪同',
			hidden:true
		},{
			name : 'odEmpName',
			fieldLabel: '营运副总陪同',
			hidden:true
		},{
			name : 'ceoEmpName',
			fieldLabel: '集团总裁陪同',
			hidden:true
		},{
			name : "reserveTheme",
			fieldLabel : "预约主题",
			columnWidth: .987
		},{
			xtype:'textarea',
			name:"reserveContents",
			fieldLabel : "预约内容",
			columnWidth: .987,
			height:70
		}],
		buttons:[ {
			text : '赴约',
			action : 'addChats'
		}]
	}
});
