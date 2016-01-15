Ext.define("crm.view.resourcePool.Detail", {
	id : 'resourcePoolDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.resourcePoolDetailWin",
	title : "资源客户详情",
	width : 940,
	height : 190,
	layout:'fit',
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
			columnWidth : 0.329,
			labelWidth : 130,
		},
		defaults:{
			margin : '5 0 5 30 ',
    		readOnly : true
    	},
		items : [ {
			name : "enterpriseName",
			fieldLabel : '客户企业全称'
		}, {
			name : "industryCode",
			fieldLabel : "所属行业"
		}, {
			name : "regions",
			fieldLabel : "所属大区"
		}, {
			name : "contactName",
			fieldLabel : '联系人姓名'
		}, {
			name : "cellphone",
			fieldLabel : "联系电话"
		}, {
			name : "telephone",
			fieldLabel : "座机号"
		}, {
			name : "flowDate",
			fieldLabel : "流入时间"
		}, {
			name : "address",
			fieldLabel : "联系地址",
			columnWidth: .987
		}]
	}
});
