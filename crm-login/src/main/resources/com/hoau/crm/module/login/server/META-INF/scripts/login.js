Ext.define('Crm.model.login.UserEntity', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'userName',
		type : 'string'
	}, {
		name : 'password',
		type : 'string'
	} ]
});

/**
 * 登录表单
 */
Ext.define('Crm.view.login.LoginForm', {
	extend : 'Ext.form.Panel',
	frame : true,
	width : 410,
	id : 'loginFormId',
	defaultType : 'textfield',
	cls : 'ui_login_form',
	fieldDefaults: {
        margin: '0 0 20 0'
    },
	items : [{
		id : 'usernameId',
		allowBlank : false,
		name : 'userName',
		cls : 'ui_login_input_user',
		fieldCls : 'ui_login_input_size',
		emptyText : '请输入您的用户名'
	},{
		allowBlank : false,
		name : 'password',
		cls : 'ui_login_input_lock',
		fieldCls : 'ui_login_input_size',
		emptyText : '请输入您的密码',
		inputType : 'password'
	}],
/*,
	buttons : [
			{
				text : '重置',
				handler : function() {
					this.up('form').getForm().reset();
				}
			},
			{
				text : '登录',
				handler : function() {
					var form = this.up('form').getForm();
					if (form.isValid()) { // 校验form是否通过校验
						var userModel = new Crm.model.login.UserEntity();
						form.updateRecord(userModel); // 将FORM中数据设置到MODEL里面
						var params = {
							'currentUser' : userModel.data
						};
						var successFun = function(json) {
							window.location.href = "main.action";
						};
						var failureFun = function(json) {
							if (Ext.isEmpty(json)) {
								crm.showErrorMes('连接超时'); // 请求超时
							} else {
								var message = json.message;
								crm.showErrorMes(message); // 提示失败原因
							}
						};
						crm.requestJsonAjax('login.action', params, successFun,
								failureFun); // 发送AJAX请求
					}
				}
			} ],
*/
	initComponent : function() {
		this.defaults = {
			anchor : '100%',
			labelWidth : 60
		};
		this.callParent();
	}
});

function login(){
	var form = Ext.getCmp("loginFormId").getForm();	
	if (form.isValid()) { // 校验form是否通过校验
		var userModel = new Crm.model.login.UserEntity();
		form.updateRecord(userModel); // 将FORM中数据设置到MODEL里面
		var params = {
			'currentUser' : userModel.data
		};
		var successFun = function(json) {
			//校验是否记住密码
			remember(); 
			window.location.href = "main.action";
		};
		var failureFun = function(json) {
			if (Ext.isEmpty(json)) {
				//crm.showErrorMes('连接超时'); // 请求超时
				document.getElementById("msg").style.display = "block";
				document.getElementById("error").innerText = "连接超时!";
			} else {
				var message = json.message;
				//crm.showErrorMes(message); // 提示失败原因
				document.getElementById("msg").style.display = "block";
				document.getElementById("error").innerText = message+"!";
			}
		};
		crm.requestJsonAjax('login.action', params, successFun,
				failureFun); // 发送AJAX请求
	}
}

Ext.onReady(function() {
	Ext.QuickTips.init();
	var loginForm = Ext.create('Crm.view.login.LoginForm');
	Ext.create('Ext.panel.Panel', {
		renderTo : 'login_form_div',
		items : [ loginForm ]
	});
	Ext.getCmp('usernameId').focus(false,100);
	//加载完调用密码显示
	showPassword();
});

function setCookie(name,value) {
    var Days = 7; //此 cookie 将被保存 7 天
    var exp  = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = "username=" + name + "&"+value+";expires=" + exp.toGMTString();
//    document.cookie = + name + "="+value+";expires=" + exp.toGMTString();
   }
function remember() { 
	var form = Ext.getCmp("loginFormId").getForm();
    if(document.getElementById("login_checkbox").checked==true) {
//    	Ext.Msg.alert("信息",form.findField('userName').getValue());
//    	Ext.Msg.alert("信息",form.findField('password').getValue());
    	setCookie(form.findField('userName').getValue(),form.findField('password').getValue());
    }else{
    	var date = new Date(); 
    	//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间 
    	date.setTime(date.getTime() - 10000); 
//    	document.cookie = form.findField('userName').getValue() + "=n;expires=" + date.toGMTString();
    	document.cookie = "username=n;expires=" + date.toGMTString();
    }
}

//得到username字符串长度  Start
function getCookie() {
	var arr;
	if(document.cookie.indexOf(";", 0) != -1)
		if((((document.cookie.split(';')[0]).split('=')[1]).indexOf('&')) != -1)
		arr = ((document.cookie.split(';')[0]).split('=')[1]).split('&');
	return arr;
//	 var username = Ext.getCmp('usernameId').getValue() + "=";  
//	 var username_len = username.length;                   
//	 var cookie_len = document.cookie.length;
//	 var i = 0;
//	 while(i<cookie_len) {                                
//	  var j = i + username_len;                         
//	     if(document.cookie.substring(i,j)==username) return getCookieValue(j);
//	  i = document.cookie.indexOf(" ",j);  
//	  if(i==-1) 
//	  return null;
//	 }
}
//读取cookie中的password对应的值
function getCookieValue(offset) {
	Ext.Msg.alert("信息",document.cookie);
    var password_len = document.cookie.indexOf(";",offset);
    if(password_len==-1)                                          
    password_len = document.cookie.length;
    return document.cookie.substring(offset,password_len);
}

//将cookie中存储的password显示出来
function showPassword() {
 var username = getCookie();
 if(username!=null) {
	 Ext.getCmp('usernameId').setValue(username[0]);
	 document.all.password.value=username[1];
	 document.getElementById("login_checkbox").checked = true;
 }else{
	 document.getElementById("login_checkbox").checked = false;
 }
}