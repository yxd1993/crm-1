/**
 * 意向信息查询FORM
 */
Ext.define("crm.view.addIntention.AddIntentionForm", {
    extend: "Ext.form.Panel",
    alias: 'widget.addIntentionForm',
    width : '100%',
    autoScroll : true,
    layout: 'column',
    region : 'north',
    items: [{
		xtype: 'form',
		columnWidth : 1,
        title: '发货意向信息',
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
            name: 'fId'
        },{
    		fieldLabel: '客户企业全称',
            name: 'enterpriseName',
            xtype : 'customerCommonSelector',
            allowBlank : false,
            listeners : {
            	'select' : function(combo,record){
            		alter(record.data['depCode']);
            	}
            }
    	},{
    		fieldLabel: '主要货物名称<font color="#FF0000"><b>*</b></font>',
            name: 'goodsname',
            maxLength:200,
            allowBlank : false,
            maxLengthText:"主要货物名称不能超过200个字符"
    	},{
    		fieldLabel: '主要包装方式',
            name: 'packaging',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_PACKAGING', null, null, null)
    	},{
    		fieldLabel: '联系人姓名<font color="#FF0000"><b>*</b></font>',
            name: 'linkanName',
            allowBlank : false,
            maxLength:200,
            maxLengthText:"联系人姓名不能超过200个字符"
    	},{
    		maxLength : 20,
    		fieldLabel: '联系人电话',
            name: 'mobile'
    	},{
    		fieldLabel: '办公地址<font color="#FF0000"><b>*</b></font>',
            name: 'offaddress',
            maxLength:200,
            allowBlank : false,
            maxLengthText:"办公地址不能超过200个字符"
    	},{
    		fieldLabel: '上门取货地址<font color="#FF0000"><b>*</b></font>',
            name: 'dooraddress',
            maxLength:200,
            allowBlank : false,
            maxLengthText:"上门取货地址不能超过200个字符"
    	},{
    		fieldLabel: '取货邮编地址',
            name: 'zipaddress',
            maxLength:400,
            maxLengthText:"取货详细地址不能超过400个字符"
    	},{
    		fieldLabel: '客户信用评分',
            name: 'creditcustomer',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_CREDIT_GRADE', null, null, null)
    	},{
    		fieldLabel: '客户最期望的营销活动类型',
            name: 'qwcustomer',
            xtype : 'dataDictionarySelector',
            store: getDataDictionary().getDataDictionaryStore('CUSTOMER_YXLX', null, null, null)
    	},{
    		fieldLabel: '客户最期望的营销活动详述',
            name: 'describecustomer',
            columnWidth : 0.66,
            maxLength:400,
            maxLengthText:"客户最期望的营销活动详述不能超过400个字符"
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
