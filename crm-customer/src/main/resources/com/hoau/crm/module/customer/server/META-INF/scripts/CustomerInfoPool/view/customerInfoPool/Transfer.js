Ext.define("crm.view.customerInfoPool.Transfer", {
	extend : "Ext.window.Window",
	alias : "widget.customerInfoPoolTransferWin",
	title : "转让共享客户",
	width : 400,
	height : 150,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		fieldDefaults : {
			msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.99,
    		labelAlign: 'left',
            regex : /^\S*$/,
            regexText : '不能含有空格',
            allowBlank : false
		},
		items : [{
			xtype : "saleEmpSelector",
			name : "newManagerCode",
			fieldLabel : '新负责人<font color="#FF0000"><b>*</b></font>',
		}],
		buttons : [ {
			text : '还原',
			action : 'reset'
		},{
			text : '提交',
			action : 'submit'
		}]
	}
});
