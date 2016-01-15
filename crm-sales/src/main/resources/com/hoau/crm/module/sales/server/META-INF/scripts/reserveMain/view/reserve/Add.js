/*回滚版本*/
Ext.define("crm.view.reserve.Add", {
	id : 'reserveAddWinId',
	extend : "Ext.window.Window",
	alias : "widget.reserveAddWin",
	title : "新增预约",
	width : 940,
	height : 370,
	layout : "fit",
	modal : true,
	items : {
		xtype : "form",
		margin : 5,
		border : false,
		layout : "column",
		record : null,
		autoScroll:true,
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.329,
			labelWidth : 130,
			allowBlank : false
		},
		defaults:{
			margin : '5 0 5 30 '
		},
		items : [ {
			xtype : "hiddenfield",
			name : "id"
		}, {
			xtype : 'customerCommonSelectorByAuth',
			name : "enterpriseName",
			fieldLabel : '客户企业全称<font color="#FF0000"><b>*</b></font>',
			maxLength : 100,
			regex : /^\S*$/,
			regexText : '不能含有空格',
			listeners : {
				'select' : function(combo, recrod){
					var form = combo.up('form').getForm();
					// 办公地址
					form.findField('detailedAddress').setValue(recrod[0].get('detailedAddress'));
					form.findField('accountId').setValue(recrod[0].get('id'));
					// 刷新联系人
					var contactName = form.findField('contactName');
					contactName.getStore().setAccountId(recrod[0].get('id'));
					contactName.reset();
					contactName.getStore().load();
					// 刷新陪同人员信息
					var params = {};
					var deptEntity = {};
					if(recrod[0].get('tierCode')==""){
						Ext.MessageBox.alert('提示','门店编号为空')
					}else{
						deptEntity.logistCode = recrod[0].get('tierCode');
						params.deptEntity = deptEntity;
						params.customerManageEmpCode = recrod[0].get('manageEmpCode');
						params.customerManageEmpName = recrod[0].get('managePerson');
						crm.requestJsonAjax('../bse/queryDeptSuperiorDept.action', params, 
								function(response){
									setEntourageValue(form, response);
								}, 
								function(){Ext.MessageBox.alert('提示','查询陪同人员信息失败');});
					}
				}
			}
		}, {
			xtype : "hiddenfield",
			name : "accountId"
		}, {
			xtype : "dataDictionarySelector",
			name : "warningTime",
			fieldLabel : '提醒时间<font color="#FF0000"><b>*</b></font>',
			store:getDataDictionary().getDataDictionaryStore('REMIND_DATE',null,null,null)
		}, {
			xtype : "dataDictionarySelector",
			name : "reserveType",
			fieldLabel : '预约方式<font color="#FF0000"><b>*</b></font>',
			store: getDataDictionary().getDataDictionaryStore('CUSTOMER_YXLX', null, null, null)
		}, {
			xtype : "datefield",
			columnWidth : 0.329,
			name : "startDate",
			fieldLabel : '约定日期<font color="#FF0000"><b>*</b></font>',
			format : 'Y-m-d'
		},{
			xtype : "numberfield",
			name:"hour",
			columnWidth : 0.2545,
			fieldLabel : '约定时间(24小时制)<font color="#FF0000"><b>*</b></font>',
			margin : '5 0 5 30 ',
			minValue: 0,
           	maxValue: 23,
           	regex : /^\d+$/,
           	enableKeyEvents: true,
           	listeners: {
                 keyup: function(field, e){
                     if (field.getValue() > 23){
                         e.stopEvent();
                         field.setValue(23);
                     }else if(!Ext.isEmpty(field.getValue())){
                    	 field.setValue(field.getValue().toFixed(0))
                     }
                 }
             }
		}, {
			xtype : "numberfield",
			name:"minute",
			columnWidth : 0.0745,
			labelWidth : 0,
			margin : '5 0 5 0 ',
			minValue: 0,
           	maxValue: 59,
           	regex : /^\d+$/,
           	enableKeyEvents: true,
           	listeners: {
                 keyup: function(field, e){
                     if (field.getValue() > 59){
                         e.stopEvent();
                         field.setValue(59);
                     }else if(!Ext.isEmpty(field.getValue())){
                    	 field.setValue(field.getValue().toFixed(0))
                     }
                 }
             }
		}, {
			xtype : "dataDictionarySelector",
			name : "differentDate",
			fieldLabel : '预约时长<font color="#FF0000"><b>*</b></font>',
			store: getDataDictionary().getDataDictionaryStore('RESERVEL_LENGTH', null, null, null),
		}, {
			xtype : "combo",
			name : "contactName",
			fieldLabel : '联系人姓名',
			store : 'Contact',
			displayField : 'contactName',
			valueField : 'id', 
			editable : false,
			allowBlank : true,
			listeners : {
				'select' : function(combo, record){
					var form = combo.up('form').getForm();
					// 联系电话
					if(!Ext.isEmpty(record[0].data['cellphone'])){
						form.findField('cellphone').setValue(record[0].data['cellphone']);
					} else if(!Ext.isEmpty(record[0].data['telephone'])){
						form.findField('cellphone').setValue(record[0].data['telephone']);
					} else {
						form.findField('cellphone').reset();
					}
				}
			}
		}, {
			xtype : "textfield",
			name : "cellphone",
			fieldLabel : "联系电话",
			maxLength : 200,
			readOnly : true,
			allowBlank : true
		}, {
			xtype : "textfield",
			name : "detailedAddress",
			fieldLabel : "办公地址",
			maxLength : 200,
			columnWidth: 1,		
			readOnly : true,
			allowBlank : true
		},{
			xtype:"checkbox",
			name:"comTierEmpCode",
			fieldLabel: '门店经理陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}
		},{
			name : 'tierEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			fieldLabel: '门店经理',
			xtype : "displayfield",
			disabled : true,
			hidden:true,	
			columnWidth: .3			
		},{
			xtype:"checkbox",
			name:"teamManagerEmpCode",
			fieldLabel: '团队经理陪同',
			columnWidth: .2,
			hidden :true,
			listeners : {
				'change' : setFieldDisabled
			}
		},{
			name : 'teamManagerEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			fieldLabel: '团队经理',
			xtype : "displayfield",
			disabled : true,
			hidden :true,
			columnWidth: .3	
		},{
			xtype:"checkbox",
			name:"saleManEmpCode",
			fieldLabel: '客户经理陪同',
			columnWidth: .2,
			hidden :true,
			listeners : {
				'change' : setFieldDisabled
			}
		},{
			name : 'saleManEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			fieldLabel: '客户经理',
			xtype : "displayfield",
			disabled : true,
			hidden :true,
			columnWidth: .3
		},{
			xtype:"checkbox",
			name:"comRoadEmpCode",
			fieldLabel: '路区经理陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}			
		},{
			name : 'roadEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			fieldLabel: '路区经理',
			xtype : "displayfield",
			disabled : true,
			hidden:true,
			columnWidth: .3			
		},{
			xtype:"checkbox",
			name:"comRegionsEmpCode",
			fieldLabel: '大区总经理陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}			
		},{
			name : 'regionsEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			xtype : "displayfield",
			fieldLabel: '大区总经理',
			disabled : true,
			hidden:true,
			columnWidth: .3			
		},{
			xtype:"checkbox",
			name:"comBusinessEmpCode",
			fieldLabel: '事业部总经理陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}			
		},{
			name : 'businessUnitEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			xtype : "displayfield",
			fieldLabel: '事业部总经理',
			disabled : true,
			hidden:true,	
			columnWidth: .3			
		},{
			xtype:"checkbox",
			name:"comOdEmpCode",
			fieldLabel: '营运副总陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}			
		},{
			name : 'odEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			xtype : "displayfield",
			fieldLabel: '营运副总',
			disabled : true,
			hidden:true,
			columnWidth: .3			
		},{
			xtype:"checkbox",
			name:"comCeoEmpCode",
			fieldLabel: '集团总裁陪同',
			columnWidth: .2,
			hidden:true,
			listeners : {
				'change' : setFieldDisabled
			}			
		},{
			name : 'ceoEmpName',
			empCode : null,
			getEmpCode : function(){
				return this.empCode;
			},
			setEmpCode : function(empCode){
				this.empCode = empCode
			},
			xtype : "displayfield",
			fieldLabel: '集团总裁',
			disabled : true,
			hidden:true,
			columnWidth: .3			
		},{
			xtype:"textfield",
			columnWidth: .987,
			name : "reserveTheme",
			fieldLabel : '预约主题<font color="#FF0000"><b>*</b></font>',
			maxLength : 50,
			minLength:5,
			maxLengthText:'超出字数范围',
			regex : /^\S*$/,
			regexText : '不能含有空格'
		},{
			xtype:"textarea",
			columnWidth: .987,
			height:60,
			name:"reserveContents",
			fieldLabel : '预约内容<font color="#FF0000"><b>*</b></font>',
			maxLength : 300,
			minLength:15,
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
	},
	listeners : {
		'beforeclose' : function(){
			ReserveWin = undefined;
		}
	}
});

/**
 * 将组织置为DISABLED状态
 * 
 * @author 蒋落琛
 * @date 2015-6-17
 * @update
 */
function setFieldDisabled(box, newValue){
	if(newValue){
		box.nextSibling().setDisabled(false);
	}else{
		box.nextSibling().setDisabled(true);
	}
}
