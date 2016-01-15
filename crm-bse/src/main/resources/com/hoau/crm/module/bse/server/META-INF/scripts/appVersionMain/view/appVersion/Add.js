/**
 * 新增App版本信息WINDOW
 */
Ext.define("crm.view.appVersion.Add", {
	id:"appVersionAddWin",
    extend: "Ext.window.Window",
    alias: "widget.appVersionAddWin",
    title: "新增App版本信息",
    width: 700,
    height: 220,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        //fileUpload:  true,
        record : null, // 客户信息
        defaultType: 'textfield',
        fieldDefaults: {
            labelAlign: 'right',
            columnWidth : 0.49,
            margin : '5 0 5 0 ',
            labelWidth: 100,
            regex : /^\S*$/,
        },
        items: [
            {
    			xtype : "textfield",
    			name : "versionCode",
    			fieldLabel : 'App版本号<font color="#FF0000"><b>*</b></font>',
    			regex : /^[0-9]*$/,
    			regexText :"版本号填写必须为整数且不能含有空格",
    			allowBlank : false
    		},{
        		name : 'file',
        		xtype : 'filefield',
        		fieldLabel : 'App版本',
        		msgTarget : 'side',
        		allowBlank : false,
        		regex:/.apk/,
        		regexText :"请输入正确的apk文件格式",
        		buttonText : '请选择文件',
        	},
    		/*{
    			xtype :"textfield" , 
    			name  :"apkName",
    			fieldLabel : 'APK名字<font color="#FF0000"><b>*</b></font>',
    			allowBlank : false
    		}*/
    		{
    			name : "isMust",
    			fieldLabel : '是否强制更新<font color="#FF0000"><b>*</b></font>',
                xtype : 'dataDictionarySelector',
                store: getDataDictionary().getDataDictionaryStore('APP_VERSION_ISMUST', null, null, null),
    			allowBlank : false
    		},{
    			name : "isNow",
    			fieldLabel : '是否当前版本<font color="#FF0000"><b>*</b></font>',
                xtype : 'dataDictionarySelector',
                store: getDataDictionary().getDataDictionaryStore('APP_VERSION_ISNOW', null, null, null),
    			allowBlank : false
    		},{
    			name :"description",
    			fieldLabel : '描述<font color="#FF0000"><b>*</b></font>',
    			xtype: "textareafield",
    			maxLength : 500,
    			columnWidth : 0.98,
    			allowBlank : true
    		}
    	
        ],
        buttons: [{
            text: '还原',
            action : 'reset'
        }, {
            text: '提交',
            handler :function(){
            	var form=this.up('form').getForm();
            	var appVersion={};
            	appVersion.versionCode = form.findField('versionCode').getValue();
              //appVersion.apkName = form.findField('apkName').getValue();
            	appVersion.isMust = form.findField('isMust').getValue();
            	appVersion.isNow = form.findField('isNow').getValue();
            	appVersion.description = form.findField('description').getValue();
            	if(!form.isValid()){
            		return;
            	}
            	submit(form,appVersion);
            	
            }
        }]
    }
});

function submit(form,appVersion){
	if(form.isValid()){
		form.submit({
			url :'appVersionAction!addAppVersion.action',
			headers:{'Content-Type':'multipart/form-data;charset=UTF-8'},
			waitMsg:'App版本信息上传中,请稍候。。。',
			params :{
				'appVersion.versionCode':appVersion.versionCode + '',
			  //'appVersion.apkName':appVersion.apkName,
				'appVersion.isMust':appVersion.isMust,
				'appVersion.isNow':appVersion.isNow,
				'appVersion.description':appVersion.description
			},
			success : function(form, response) {
				Ext.MessageBox.alert('提示','App版本信息上传成功',function(){
				Ext.getCmp('appVersionAddWin').close();
				Ext.getCmp('appVersionMainViewId').getAppVersionGrid().getStore().reload();
				});
			},
			failure : function(form, response) {
				var result = response.result;
				if(result.message == undefined){
					Ext.MessageBox.alert('提示',"系统异常,请联系系统管理员!");
				}else{
					//业务异常
					Ext.MessageBox.alert('提示',result.message);
				}
			}
			
		});
	}
	
}





