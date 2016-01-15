/**
 * 新增权限信息WINDOW
 */
Ext.define("crm.view.authorization.Add", {
	id:"authorizationAddWin",
    extend: "Ext.window.Window",
    alias: "widget.authorizationAddWin",
    title: "新增授权信息",
    width: 600,
    height: 160,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        record : null, 
        //defaultType: 'textfield',
        fieldDefaults: {
            labelAlign: 'left',
            columnWidth : 0.485,
            margin : '5 0 5 0 ',
            labelWidth: 130,
            regex : /^\S*$/,
        },
        items: [
			{
				name : "id",//授权人工号
				xtype : "hidden"
			},{
    			name : "authorizedPerson",//授权人工号
    			xtype : "textfield",
    			fieldLabel : '授权人工号<font color="#FF0000"><b>*</b></font>',
    			//regex : /^[0-9]*$/,
    			//regexText :"授权人工号必须为整数且不能含有空格",
    			allowBlank : false
    		},{
        		name : 'wasAuthorizedPerson',
        		xtype : 'textfield',
        		fieldLabel : '被授权人工号',
        		regex : /^[0-9]*$/,
    			regexText :"被授权人工号必须为整数且不能含有空格",
        		allowBlank : false
        	},{
    			name :"authorizedStartTime",
    			fieldLabel : '授权开始时间<font color="#FF0000"><b>*</b></font>',
    			xtype: "datetimefield",
    			format : 'Y-m-dH:i:s',
    			allowBlank : false
    		},{
				name :"authorizedEndTime",
				fieldLabel : '授权结束时间<font color="#FF0000"><b>*</b></font>',
				xtype: "datetimefield",
				format : 'Y-m-dH:i:s',
				allowBlank : false
    		}/*,{
    			name :"createUser",
    			fieldLabel : '创建人姓名<font color="#FF0000"><b>*</b></font>',
    			xtype: "textfield",
    			columnWidth : 0.49,
    			allowBlank : false
    		}*/
        ],
        buttons: [{
            text: '还原',
            action : 'reset'
        }, {
            text: '提交',
            handler :function(){
            	var form=this.up('form').getForm();
            	var authorization={};
            	authorization.id=form.findField('id').getValue();
            	authorization.authorizedPerson = form.findField('authorizedPerson').getValue();
            	authorization.wasAuthorizedPerson = form.findField('wasAuthorizedPerson').getValue();
            	authorization.authorizedStartTime = form.findField('authorizedStartTime').getValue();
            	authorization.authorizedEndTime = form.findField('authorizedEndTime').getValue();
            	if(!form.isValid()){
            		return;
            	}
            	submit(form,authorization);
            	
            }
        }]
    }
});

function submit(form,authorization){
	if(form.isValid()){
		form.submit({
			url :'authorizationAction!addOrUpdateAuthorization.action',
			//headers:{'Content-Type':'multipart/form-data;charset=UTF-8'},
			waitMsg:'授权信息提交中,请稍候。。。',
			params :{
				'authorization.id':authorization.id,
				'authorization.authorizedPerson':authorization.authorizedPerson,
			    'authorization.wasAuthorizedPerson':authorization.wasAuthorizedPerson,
				'authorization.authorizedStartTime':authorization.authorizedStartTime,
				'authorization.authorizedEndTime':authorization.authorizedEndTime
			},
			success : function(form, response) {
				Ext.MessageBox.alert('提示','授权信息上传成功',function(){
				Ext.getCmp('authorizationAddWin').close();
				Ext.getCmp('authorizationMainViewId').getAuthorizationGrid().getStore().reload();
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





