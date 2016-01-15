Ext.define("crm.view.chatsRandom.Detail", {
	id : 'chatsRandomDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.chatsRandomDetailWin",
	title : "洽谈审核详情",
	width : 750,
	height : 240,
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
			columnWidth : 0.440,
			labelWidth : 115
		},
		defaults:{
			margin : '5 0 5 30 ',
    		readOnly : true
    	},
		items : [{
			name : "customerName",
			fieldLabel : '客户企业全称'
		}, {
			name : "status",
			fieldLabel : "审核状态"
		}, {
			xtype:'textarea',
			name:"chatContent",
			fieldLabel : "洽谈内容",
			columnWidth: .987,
			height:70
		}, {
			xtype:'textarea',
			name:"checkResult",
			fieldLabel : "审核结果",
			columnWidth: .987,
			height:70
		}]
	}
});
