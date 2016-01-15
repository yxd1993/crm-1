Ext.define("crm.view.customerInfoPool.Add", {
	extend : "Ext.window.Window",
	alias : "widget.customerInfoPoolAddWin",
	title : "新增共享客户信息",
	width : 650,
	height : 255,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		record : null, // 客户信息
		fieldDefaults : {
			msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.49,
    		labelAlign: 'left',
            regex : /^\S*$/,
            regexText : '不能含有空格',
            allowBlank : false
		},
		items : [ {
			xtype : "hiddenfield",
			name : "id"
		}, {
			xtype : "textfield",
			name : "companyName",
			fieldLabel : '公司名称<font color="#FF0000"><b>*</b></font>',
			maxLength : 200
		}, {
			xtype : "textfield",
			name : "contactPerson",
			fieldLabel : '联系人<font color="#FF0000"><b>*</b></font>',
			maxLength : 100
		}, {
			xtype : "textfield",
			name : "contactWay",
			fieldLabel : '联系方式<font color="#FF0000"><b>*</b></font>'
		}, {
			name : "business",
			fieldLabel : '所属事业部<font color="#FF0000"><b>*</b></font>',
            xtype : 'businessSelector'
		}, {
			name : "regionsCode",
			fieldLabel : '所属大区<font color="#FF0000"><b>*</b></font>',
            xtype : 'regionsSelector'
		}, {
			xtype : "textfield",
			name : "email",
			fieldLabel : "邮箱",
			vtype : 'email',
			maxLength : 100,
			allowBlank : true
		}, {
			xtype : "districtProvinceSelector",
			name : "provinceCode",
			fieldLabel : '省<font color="#FF0000"><b>*</b></font>'
		}, {
			xtype : "districtCitySelector",
			name : "cityCode",
			fieldLabel : '市<font color="#FF0000"><b>*</b></font>'
		}, {
			xtype : "districtAreaSelector",
			name : "areaCode",
			fieldLabel : '区<font color="#FF0000"><b>*</b></font>'
		}, {
			xtype : "textfield",
			name : "companyAddress",
			fieldLabel : '公司地址<font color="#FF0000"><b>*</b></font>',
			maxLength : 400
		} ],
		buttons : [ {
			text : '还原',
			action : 'reset'
		}, {
			text : '提交',
			action : 'submit'
		} ]
	}
});
