/*回滚版本*/
/**
 * 新增联系人WINDOW
 */
Ext.define("crm.view.addCustomer.AddContact", {
    extend: "Ext.window.Window",
    alias: "widget.addContactWin",
    title: "新增客户联系人信息",
    width: 600,
    height: 220,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        record : null, // 客户信息
        defaultType: 'textfield',
        fieldDefaults: {
            labelAlign: 'right',
            columnWidth : 0.49,
            margin : '5 0 5 0 ',
            labelWidth: 100,
            regex : /^\S*$/,
            regexText : '不能含有空格'
        },
        items: [
            { xtype: "hiddenfield", name: "id"},
            { 
            	fieldLabel: '联系人姓名<font color="#FF0000"><b>*</b></font>',
                name: 'contactName',
                allowBlank : false,
                maxLength:200,
                maxLengthText:"联系人姓名不能超过200个字符" 
            },{
            	maxLength : 11,       		
            	fieldLabel: '手机<font color="#FF0000"><b>*</b></font>',
                name: 'cellphone',
                regex : /^\d+$/,
                regexText :"手机填写不合法",
                emptyText : '"手机"与"座机"必须二选一',
                minLength : 11
	            ,listeners : {
	            	change : function(field, newValue, oldValue){
	            		// 手机号合法性判断
	            		if(/^\d+$/.test(newValue) && /^\S*$/.test(newValue) && newValue.length == 11){   
	            			var params = {};
	            			var phoneInfoEntity = {};
	            			phoneInfoEntity.mobile = newValue;
	            			params.phoneInfoEntity = phoneInfoEntity;
	            			crm.requestJsonAjax('customerAction!queryPhoneInfoByPhone.action', params, 
	            					function(response){
	            						var phoneInfo = response.phoneInfoEntity;
	            						if(phoneInfo&&!Ext.isEmpty(phoneInfo.province)){
	            							field.nextSibling().setValue(phoneInfo.areaCode);
	            						}
	            					}, 
	            					function(response){
	            						Ext.MessageBox.alert('提示', response.message); 
	            					});
	            	    }  
	            	}
	            }
            }
            ,{
        		fieldLabel: '区号<font color="#FF0000"><b>*</b></font>',
        		name: 'districtNumber',
        		maxLength : 4,
                allowBlank : false,
                hidden :true,
                disabled : true,
                regex : /^\d+$/,
                regexText :"区号填写不合法"
        	}
            ,{
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
        		name: 'job',
        		maxLength : 40
        	}
        ],
        buttons: [{
            text: '还原',
            action : 'reset'
        }, {
            text: '提交',
            action : 'submit'
        }]
    }
});
