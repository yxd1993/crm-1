/**
 * 新增消息FORM
 */
Ext.define("crm.view.message.Add", {
	extend : "Ext.window.Window",
	alias : "widget.addMessageWin",
	title : "新增消息",
	width : 550,
	height : 330,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		record : null,
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.99,
			labelWidth : 80,
			allowBlank : false
		},
		defaults:{
			margin : '5 30 5 30 '
		},
		items : [ {
			xtype : "hiddenfield",
			name : "id"
		}, {
			xtype : "dataDictionarySelector",
			name : "messageType",
			fieldLabel : '消息类型<font color="#FF0000"><b>*</b></font>',
			store: getDataDictionary().getDataDictionaryStore('MESSAGE_TYPE', null, null, null)
		}, {
			xtype : 'datetimefield',
			format : 'Y-m-d H:i:s',
			name : "allowSendTime",
			editable : false,
			fieldLabel : '发送时间<font color="#FF0000"><b>*</b></font>'
		}, {
			xtype : 'combo',
			name : "roleId",
			fieldLabel : '接收角色<font color="#FF0000"><b>*</b></font>',
		    store: 'Role',
		    editable : false,
		    queryMode: 'local',
		    displayField: 'roleName',
		    valueField: 'roleCode'
		}, {
			xtype : 'textarea',
			name : 'messageContent',
			fieldLabel : '消息内容<font color="#FF0000"><b>*</b></font>',
			height:130,
			maxLength : 1000,
			regex : /^\S*$/,
			regexText : '不能含有空格'
		}],
		buttons : [{
			text : '保存',
			action : 'submit'
		}, {
			id:'reset',
			text : '重置',
			action : 'reset'
		}]
	}
});

