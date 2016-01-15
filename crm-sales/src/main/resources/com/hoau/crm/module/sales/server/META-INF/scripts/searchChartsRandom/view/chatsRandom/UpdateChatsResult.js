Ext.define("crm.view.chatsRandom.UpdateChatsResult", {
	id : 'updateChatsResultWinId',
	extend : "Ext.window.Window",
	alias : "widget.updateChatsResultWin",
	title : "洽谈信息审核",
	width : 650,
	height : 220,
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
			columnWidth : 0.440,
			labelWidth : 130
		},
		defaults:{
			margin : '5 0 5 30 '
		},
		items : [{
			xtype : "combo",
			name : "status",
			value:1,
			fieldLabel : '审核状态',
			store:Ext.create('Ext.data.Store',{
			        fields:['id','name'],
			        data:[{
			        		'id':1,
			        		'name':'审核成功'
			        	 },{
			        		'id':2,
			        		'name':'审核失败'
			        	 }]
			     }),
		    displayField:'name',
		    valueField:'id',
			editable : false,
			listeners : {
				'select' : function(combo, record){
					var form = combo.up('form').getForm();
					//id
					if(record[0].data['id']===2){
						form.findField('checkResult').allowBlank = false;
					}else{
						form.findField('checkResult').allowBlank = true;
					}
				}
			}
		}, {
			xtype:'textarea',
			name:"checkResult",
			fieldLabel : '审核结果',
			columnWidth: .987,
			height:80,
			maxLength : 400,
			maxLengthText:'超出字数范围',
			regex : /^\S*$/,
			regexText : '不能含有空格'
		}],
		buttons : [{
			text : '保存',
			action : 'submit'
		}, {
			text : '重置',
			action : 'reset'
		}]
	}
});

