/*回滚版本*/
//动态提交方法
function getparams(btn, submittype) {
	var form = btn.up('form').getForm();
	var win = btn.up('window');
	if (!form.isValid()) {
		Ext.MessageBox.alert('提示', '必填信息或输入信息有误');
		return;
	}
	// 保存信息
	var params = {};
	var chatsEntity = {};
	var customerEntity = {};
	var contactEntity = {};
	var reserveEntity = {};
	var sign = {};
	var chatsVo = {};
	// id
	chatsEntity.id = form.findField('id').getValue();
	// 客户企业名称
	customerEntity.id = form.findField('accountId').getValue();
	// 洽谈开始时间
	chatsEntity.chatStartTime = getDateTime(form)[0];
	// 洽谈结束时间
	chatsEntity.chatEndTime = getDateTime(form)[1];
	// 联系人
	contactEntity.id = form.findField('contactName').getValue();
	// 关联预约
	reserveEntity.id = form.findField('reserveInfo').getValue();
	// 关联签到
	sign.id = form.findField('signId').getValue();
	
	// 洽谈方式
	chatsEntity.chatType = form.findField('chatType').getValue();

	// 参与人员
	if (!form.findField('tierEmpName').isDisabled()) {
		chatsEntity.comTierEmpCode = form.findField('tierEmpName').getEmpCode();
	}
	
	if (!form.findField('roadEmpName').isDisabled()) {
		chatsEntity.comRoadEmpCode = form.findField('roadEmpName').getEmpCode();
	}
	if (!form.findField('regionsEmpName').isDisabled()) {
		chatsEntity.comRegionsEmpCode = form.findField('regionsEmpName')
				.getEmpCode();
	}
	if (!form.findField('businessUnitEmpName').isDisabled()) {
		chatsEntity.comBusinessEmpCode = form.findField('businessUnitEmpName')
				.getEmpCode();
	}
	if (!form.findField('odEmpName').isDisabled()) {
		chatsEntity.comOdEmpCode = form.findField('odEmpName').getEmpCode();
	}
	if (!form.findField('ceoEmpName').isDisabled()) {
		chatsEntity.comCeoEmpCode = form.findField('ceoEmpName').getEmpCode();
	}
	if (!form.findField('teamManagerEmpName').isDisabled()) {
		chatsEntity.teamManagerEmpCode = form.findField('teamManagerEmpName').getEmpCode();
	}
	if (!form.findField('saleManEmpName').isDisabled()) {
		chatsEntity.saleManEmpCode = form.findField('saleManEmpName').getEmpCode();
	}
	// 实际地址
	//chatsEntity.reserveAddress = form.findField('detailedAddress').getValue();
	// 洽谈主题
	chatsEntity.chatTheme = form.findField('chatTheme').getValue();
	// 洽谈内容
	chatsEntity.chatContents = form.findField('chatContents').getValue();
	// 判断是否是选择了客户
	if (Ext.isEmpty(customerEntity.id)) {
		Ext.MessageBox.alert('提示', '客户选择错误，输入条件并点击查询，查询结果中选择客户');
		return;
	}
	chatsEntity.customerEntity = customerEntity;
	chatsEntity.contactEntity = contactEntity;
	chatsEntity.reserveEntity = reserveEntity;
	chatsEntity.sign = sign;
	chatsVo.chatsEntity = chatsEntity;
	params.chatsVo = chatsVo;
	win.getEl().mask("数据保存中...");
	// AJAX请求
	crm
			.requestJsonAjax(
					'saveOrUpdateChatsContents.action',
					params,
					function(response) {
						win.getEl().unmask();
						Ext.MessageBox.alert('提示','洽谈信息保存成功',
										function() {
											window.parent.delTabpanel('101001201');
											Ext.getCmp('chatsMainViewId').getChatsGrid().getStore().reload();
											// 保存类型(1.保存并录入拜访信息;2.保存洽谈)
											if (submittype == 1) {
												// 预约页面
												window.parent.allChildrenParamsMap.put('ChartsInfo',chatsVo);
												window.parent.initTabpanel('103001','预约列表','/crm-web/sales/reserveMain.action',true);
											} else if (submittype == 2) {
												var param = {};
												param.customerEntity = customerEntity;
												// AJAX请求 判断客户是否已经改为洽谈用户
												crm.requestJsonAjax('../customer/customerAction!queryCustomerInfoById.action',param,
													function(response) {
														// console.log(response.customerEntity.accountType);
														if (response.customerEntity.accountType == 6) {
															window.parent.allChildrenParamsMap.put('accountId',customerEntity.id);
															var url = '/crm-web/customer/lookCustomer.action?cId='+ customerEntity.id;
															window.parent.initTabpanel('101001201','查看客户信息',url,true);
														} else {
															return Ext.MessageBox.alert('提示','该用户不属于洽谈用户不能转为意向');
														}
													},
													function() {
														Ext.MessageBox.alert('提示','查询客户信息失败');
													})
											}
										});
						Ext.getCmp('chatsMainViewId').getChatsGrid().getStore().reload();
						win.close();
					}, function(response) {
						win.getEl().unmask();
						Ext.MessageBox.alert('提示', response.message);
					});
}

/**
 * 洽谈客户Controller
 */
Ext
		.define(
				'crm.controller.Chats',
				{
					extend : 'Ext.app.Controller',
					stores : [ 'Chats', 'Contact', 'Reserve','Sign' ],
					models : [ 'Chats', 'Contact', 'Reserve','Sign' ],
					views : [ 'Viewport', 'chats.List', 'chats.Search',
							'chats.AddChats', 'chats.Detail' ],
					init : function() {
						this.control({
							'chatssearch button[action=reset]' : {
								click : this.resetChatsSearchForm
							},
							'chatssearch button[action=select]' : {
								click : this.reloadChatsGridStore
							},
							'chatssearch button[action=add]' : {
								click : this.addChats
							},
							'chatslist' : {
								itemdblclick : this.lookChatsInfo
							},
							 'chatssearch button[action=update]' : {
							 click : this.updateChats
							 },
							'chatssearch button[action=delete]' : {
								click : this.deleteChats
							},
							'chatsAddWin button[action=submitInReserve]' : {
								click : this.submitInReserveDataForm
							},
							'chatsAddWin button[action=submitInIntention]' : {
								click : this.submitInIntentionDataForm
							},
							'chatsAddWin button[action=submit]' : {
								click : this.submitChatsDataForm
							},
							'chatsAddWin button[action=reset]' : {
								click : this.resetChatsDataForm
							},
							'chatssearch button[action=export]' : {
								click : this.exportChatInfo
							}
						});
					},
					resetChatsSearchForm : function(btn) {
						btn.up('form').getForm().reset();
					},
					reloadChatsGridStore : function(btn) {
						Ext.getCmp('chatsMainViewId').getChatsGrid().getPagingToolbar().moveFirst();
					},
					addChats : function() {
						ShowChatsAddWin();
					},
					lookChatsInfo : function() {
						var win = Ext.widget("chatsDetailWin");
						win.show();
						var form = win.down("form").getForm();
						var selectArr = Ext.getCmp('chatsMainViewId').getChatsGrid().getSelectionModel().getSelection();
						var id = selectArr[0].get('id');
						if (!Ext.isEmpty(id)) {
							var params = {};
							var chatsEntity = {};
							chatsEntity.id = id;
							params.chatsEntity = chatsEntity;
							crm.requestJsonAjax('saleChatsAction!queryChatsByIdGetVo.action',params, function(response) {
											setChatsDataInForm(form,response,win);
										}, 
										function() {Ext.MessageBox.alert('提示','查询洽谈信息失败');})
						}
					},
					 updateChats : function(btn) {
					 // 选中的数据
					 var selectArr = Ext.getCmp('chatsMainViewId').getChatsGrid().getSelectionModel().getSelection();
					 if (selectArr.length == 0) {
						 Ext.MessageBox.alert('提示', '请选择需要修改的洽谈信息');
						 return;
					 } else if (selectArr.length > 1) {
						 Ext.MessageBox.alert('提示', '只能选择一个洽谈信息进行修改');
						 return;
					 }
					 var win = Ext.widget("chatsAddWin");
					 var form = win.down("form").getForm();
					 // 获取id
					 var id = selectArr[0].get('id');
					 var params = {};
					 var chatsEntity = {};
					 chatsEntity.id = id;
					 params.chatsEntity = chatsEntity;
					 crm.requestJsonAjax('saleChatsAction!queryChatsByIdGetEntity.action',params, function(response) {
						 	initChatsData(form, response);
				 		}, function() {
					 		Ext.MessageBox.alert('提示', '查询预约信息失败');
					 	})
					 win.title = '修改洽谈信息';
					 var resetbtn = win.down('button[action=reset]');
					 resetbtn.setText('关闭');
					 resetbtn.on('click',function(){
						 win.close();
					 })
					 win.show();
					 },
//					 deleteChats : function(btn) {
//					 // 选中的数据
//					 var selectArr = Ext.getCmp('chatsMainViewId').getChatsGrid().getSelectionModel().getSelection();
//					 if (selectArr.length > 0) {
//					 Ext.MessageBox.show({
//					 title: "洽谈信息删除",
//					 msg: "请填写删除原因：",
//					 width: 350,
//					 buttons: Ext.MessageBox.OKCANCEL,
//					 multiline: true,
//					 animateTarget: btn,
//					 fn: delChats
//					 });
//					 } else {
//					 Ext.MessageBox.alert('提示', '请选择需要删除的洽谈信息');
//					 }
//					 function delChats(btn,text){
//					 if (btn == 'ok') {
//					 if(text!=''){
//							 // 删除的信息集合
//							 var deleteInfoArr = [];
//							 for (var i = 0; i < selectArr.length; i++) {
//							 deleteInfoArr.push(selectArr[i].getData().id)
//							 }
//							 var params = {};
//							 var chatsEntity = {};
//							 params.ids = deleteInfoArr;
//							 chatsEntity.delDesc = text;
//							 params.chatsEntity = chatsEntity;
//							 // AJAX请求
//							 crm.requestJsonAjax('saleChatsAction!delChats.action',params, function() {
//								 Ext.MessageBox.alert('提示', '洽谈信息删除成功');
//								 Ext.getCmp('chatsMainViewId').getChatsGrid().getStore().reload();
//							 }, function() {
//								 Ext.MessageBox.alert('提示', '洽谈信息删除失败');
//							 });
//				 	}else{
//						 Ext.MessageBox.alert('提示', '删除失败,请填写删除原因');
//			 		}
//					 }
//					 }
//					 },
					submitInReserveDataForm : function(btn) {
						getparams(btn, 1);
					},
					submitInIntentionDataForm : function(btn) {
						getparams(btn, 2);
					},
					submitChatsDataForm : function(btn) {
						getparams(btn, null);
					},
					resetChatsDataForm : function(btn) {
						// 清空或还原数据
						var form = btn.up('form');
						var record = form.record;
						if (Ext.isEmpty(record)) {
							form.getForm().reset();
						} else {
							form.loadRecord(record);
						}
					},
					exportChatInfo : function(btn) {
						// 选中的数据
						var dataSize = Ext.getCmp('chatsMainViewId')
								.getChatsGrid().getStore().getCount();
						if (dataSize == 0) {
							Ext.MessageBox.alert('提示', '当前无数据需要导出');
							return;
						}
						Ext.MessageBox
								.confirm(
										'提示',
										'您确定要导出的洽谈记录？',
										function(btn) {
											if (btn == 'yes') {
												if (!Ext.fly('downForm')) {
													var downForm = document.createElement('form');
													downForm.id = 'downForm';
													downForm.name = 'downForm';
													downForm.className = 'x-hidden';
													downForm.action = 'chatInfoExport.action';
													document.charset = 'UTF-8';
													downForm.method = 'post';
													//downForm.target = '_blank'; // 打开新的下载页面

													var enterpriseNameInput = document.createElement('input');
													var contactNameInput = document.createElement('input');
													var chatTypeInput = document.createElement('input');
													var chatStartTimeInput = document.createElement('input');
													var chatEndTimeInput = document.createElement('input');
													var createUserNameInput = document.createElement('input');
													var tierCodeInput = document.createElement('input');

													enterpriseNameInput.type = 'hidden';// 隐藏域
													contactNameInput.type = 'hidden';// 隐藏域
													chatTypeInput.type = 'hidden';// 隐藏域
													chatStartTimeInput.type = 'hidden';// 隐藏域
													chatEndTimeInput.type = 'hidden';// 隐藏域
													createUserNameInput.type = 'hidden';// 隐藏域
													tierCodeInput.type = 'hidden';// 隐藏域

													enterpriseNameInput.name = 'chatsVo.chatsEntity.customerEntity.enterpriseName';// form表单参数-mainClass.mcNo
													contactNameInput.name = 'chatsVo.chatsEntity.contactEntity.contactName';
													chatTypeInput.name = 'chatsVo.chatsEntity.chatType';
													chatStartTimeInput.name = 'chatsVo.chatsEntity.chatStartTime';
													chatEndTimeInput.name = 'chatsVo.chatsEntity.chatEndTime';
													createUserNameInput.name = 'chatsVo.createUserName';
													tierCodeInput.name = 'chatsVo.chatsEntity.customerEntity.tierCode';

													enterpriseNameInput.value = Chat.enterpriseName;// form表单参数-mainClass.mcNo
													contactNameInput.value = Chat.contactName;
													chatTypeInput.value = Chat.chatType;
													if (!Ext.isEmpty(Chat.chatStartTime)) {
														chatStartTimeInput.value = Ext.util.Format.date(new Date(Chat.chatStartTime),'Y-m-d H:i:s');
													}
													if (!Ext.isEmpty(Chat.chatEndTime)) {
														chatEndTimeInput.value = Ext.util.Format.date(new Date(Chat.chatEndTime),'Y-m-d H:i:s');
													}
													createUserNameInput.value = Chat.createUserName;
													tierCodeInput.value = Chat.tierCode;
													
													downForm.appendChild(enterpriseNameInput);
													downForm.appendChild(contactNameInput);
													downForm.appendChild(chatTypeInput);
													downForm.appendChild(chatStartTimeInput);
													downForm.appendChild(chatEndTimeInput);
													downForm.appendChild(createUserNameInput);
													downForm.appendChild(tierCodeInput);
													document.body.appendChild(downForm);
												}
												Ext.fly('downForm').dom.submit();
												if (Ext.fly('downForm')) {
													document.body.removeChild(downForm);
												}
											}
										});
					}
				});
 /**
 * 初始化洽谈修改信息
 *
 * @author 丁勇
 * @date 2015-6-18
 * @update
 */
 function initChatsData(form, response) {
	 var chatsEntity = response.chatsEntity;
	 // id
	 form.findField('id').setValue(chatsEntity.id);
	 if(chatsEntity.customerEntity!=null){
		 // 客户企业名称
		 form.findField('enterpriseName').setCombValue(chatsEntity.customerEntity.enterpriseName,chatsEntity.customerEntity.enterpriseName);
		 form.findField('enterpriseName').setReadOnly(true);
		 form.findField('accountId').setValue(chatsEntity.customerEntity.id);
		 form.findField('detailedAddress').setValue(chatsEntity.customerEntity.detailedAddress);
	 }
		
	 // 洽谈日期
	 form.findField('startDate').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[0]).setReadOnly(true);
	 // 洽谈时间
	 form.findField('hour').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[1]).setReadOnly(true);
	 form.findField('minute').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[2]).setReadOnly(true);
	 //洽谈时长
	 form.findField('differentDate').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[3]+'').setReadOnly(true);
	//联系人
	if (chatsEntity.contactEntity != null) {
		var contactName = form.findField('contactName');
		contactName.getStore().setCurrContactId(chatsEntity.contactEntity.id);
		contactName.getStore().setAccountId(chatsEntity.customerEntity.id);
		contactName.getStore().load();
	}
	form.findField('contactName').setReadOnly(true)
//	//刷新关联预约信息
//	if(chatsEntity.customerEntity!=null){
//		var reserveInfo = form.findField('reserveInfo');
//		reserveInfo.getStore().setAccountId(chatsEntity.customerEntity.id);
//		if(chatsEntity.reserveEntity!=null){
//			reserveInfo.getStore().setCurrReserveId(chatsEntity.reserveEntity.id)
//		}
//		reserveInfo.reset();
//		reserveInfo.getStore().reload();
//	}
	//关联预约信息
	form.findField('reserveInfo').setReadOnly(true)
	if(chatsEntity.reserveEntity!=null){
		form.findField('reserveInfo').setValue("开始时间:"+(Ext.isEmpty(chatsEntity.reserveEntity.reserveStartTime) ? null: Ext.util.Format 
											.date(new Date(chatsEntity.reserveEntity.reserveStartTime), 'Y-m-d H:i:s'))
												+" 主题:"+chatsEntity.reserveEntity.reserveTheme);
    	form.findField('reserveId').setValue(chatsEntity.reserveEntity.id)
	}
	 // 预约方式
	 form.findField('chatType').setValue(chatsEntity.chatType);
	 if(chatsEntity.chatType == 2){
		form.findField('signAddress').allowBlank = false;
		 //关联签到信息
		if(chatsEntity.sign!=null){
			form.findField('chatType').setReadOnly(true);
			form.findField('signAddress').setValue(chatsEntity.sign.signAddress);
			form.findField('signAddress').setReadOnly(true);
			
		}
	 }else{
		 	form.findField('chatType').setReadOnly(false);
			form.findField('signAddress').allowBlank = true;
			form.findField('signAddress').setDisabled(true);
	 }
	 // 参与人员
	 var params = {};
	 var deptEntity = {};
	 if(chatsEntity.customerEntity.tierCode!=""){
		 deptEntity.logistCode = chatsEntity.customerEntity.tierCode;
		 params.deptEntity = deptEntity;
		 params.customerManageEmpCode = chatsEntity.customerEntity.manageEmpCode;
		 params.customerManageEmpName = chatsEntity.customerEntity.managePerson;
		 crm.requestJsonAjax('../bse/queryDeptSuperiorDept.action', params,
		 function(response) {
			 setEntourageValue(form, response);
		 }, function() {
		 Ext.MessageBox.alert('提示', '查询陪同人员信息失败');
		 });
	 }else{
		 Ext.MessageBox.alert('提示','无门店编号')
	 }
	 //签到陪同扫描人
	 if(chatsEntity.sign!=null&&chatsEntity.sign.signAccopmanyList.length!=0){
		 form.findField('accompanyListName').show();
			for (var i = 0; i <chatsEntity.sign.signAccopmanyList.length; i++) {
				form.findField('accompanyListName').setValue(chatsEntity.sign.signAccopmanyList[i].scanAccompanyEmpName+" ");
			}
	}else{
		// 选中上次选中的
		 if (!Ext.isEmpty(chatsEntity.comTierEmpCode)) {
			 form.findField('comTierEmpCode').setValue(true);
		 }
		 if (!Ext.isEmpty(chatsEntity.comRoadEmpCode)) {
			 form.findField('comRoadEmpCode').setValue(true);
		 }
		 if (!Ext.isEmpty(chatsEntity.comRegionsEmpCode)) {
			 form.findField('comRegionsEmpCode').setValue(true);
		 }
		 if (!Ext.isEmpty(chatsEntity.comBusinessEmpCode)) {
			 form.findField('comBusinessEmpCode').setValue(true);
		 }
		 if (!Ext.isEmpty(chatsEntity.comOdEmpCode)) {
			 form.findField('comOdEmpCode').setValue(true);
		 }
		 if (!Ext.isEmpty(chatsEntity.comCeoEmpCode)) {
			 form.findField('comCeoEmpCode').setValue(true);
		 }
		 if(!Ext.isEmpty(chatsEntity.teamManagerEmpCode)){
			 form.findField('teamManagerEmpCode').setValue(true);
		 }
		 if(!Ext.isEmpty(chatsEntity.saleManEmpCode)){
			 form.findField('saleManEmpCode').setValue(true);
		 }
		 // 预约主题
		 form.findField('chatTheme').setValue(chatsEntity.chatTheme);
		 // 预约内容
		 form.findField('chatContents').setValue(chatsEntity.chatContents);
		 form.findField('comTierEmpCode').setReadOnly(true);
		 form.findField('comRoadEmpCode').setReadOnly(true);
		 form.findField('comRegionsEmpCode').setReadOnly(true);
		 form.findField('comBusinessEmpCode').setReadOnly(true);
		 form.findField('comOdEmpCode').setReadOnly(true);
		 form.findField('comCeoEmpCode').setReadOnly(true);
		 form.findField('teamManagerEmpCode').setReadOnly(true);
		 form.findField('saleManEmpCode').setReadOnly(true);
	}
 }
/**
 * 查看详情
 * 
 * @author 丁勇
 * @date 2015-6-18
 * @update
 */
function setChatsDataInForm(form, response,win) {
	var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");
	var chatsVo = response.chatsVo;
	var chatsEntity = response.chatsVo.chatsEntity;
	if (chatsEntity.customerEntity != null) {
		// 企业名称
		form.findField('enterpriseName').setValue(
				chatsEntity.customerEntity.enterpriseName);
		 // 客户地址
		form.findField('detailedAddress').setValue(chatsEntity.customerEntity.detailedAddress);
	}
	// 预约开始时间
	form.findField('chatStartTime').setValue(
			Ext.isEmpty(chatsEntity.chatStartTime) ? null : Ext.util.Format
					.date(new Date(chatsEntity.chatStartTime), 'Y-m-d H:i:s'));
	// 预约结束时间
	form.findField('chatEndTime').setValue(
			Ext.isEmpty(chatsEntity.chatEndTime) ? null : Ext.util.Format.date(
					new Date(chatsEntity.chatEndTime), 'Y-m-d H:i:s'));
	// 联系人
	if (chatsEntity.contactEntity != null) {
		form.findField('contactName').setValue(
				chatsEntity.contactEntity.contactName);
		// 联系电话
		if(!Ext.isEmpty(chatsEntity.contactEntity.cellphone)){
			form.findField('cellphone').setValue(chatsEntity.contactEntity.cellphone);
		}else if(!Ext.isEmpty(chatsEntity.contactEntity.telephone)){
			form.findField('cellphone').setValue(chatsEntity.contactEntity.telephone);
		}else{
			form.findField('cellphone').reset();
		}
	}
	// 预约方式
	form.findField('chatType').setValue(
			getDataDictionary().rendererSubmitToDisplay(chatsEntity.chatType,
					"CUSTOMER_YXLX"));
	// 关联预约信息
	form.findField('reserveInfo').setValue(chatsVo.reserveInfo);
	// 签到信息
	form.findField('signAddress').setValue("签到时间:"+(Ext.isEmpty(chatsEntity.sign.createDate) ? null: Ext.util.Format 
			.date(new Date(chatsEntity.sign.createDate), 'Y-m-d H:i:s'))
			+"地址:"+chatsEntity.sign.signAddress);
	// 预约主题
	form.findField('chatTheme').setValue(chatsEntity.chatTheme);
	// 预约内容
	form.findField('chatContents').setValue(chatsEntity.chatContents);
	//判断签到扫描陪同人 (app)
	if(chatsEntity.sign!=null&&chatsEntity.sign.signAccopmanyList.length!=0){
		 form.findField('accompanyListName').show();
		for (var i = 0; i <chatsEntity.sign.signAccopmanyList.length; i++) {
			form.findField('accompanyListName').setValue(chatsEntity.sign.signAccopmanyList[i].scanAccompanyEmpName+" ");
		}
	}else{
		// 判断是否有陪同人员(web)
		if (chatsVo.tierEmpName != null) {
			form.findField('tierEmpName').setValue(chatsVo.tierEmpName);
			form.findField('tierEmpName').show();
		}
		
		if (chatsVo.roadEmpName != null) {
			form.findField('roadEmpName').setValue(chatsVo.roadEmpName);
			form.findField('roadEmpName').show();
		}
		if (chatsVo.regionsEmpName != null) {
			form.findField('regionsEmpName').setValue(chatsVo.regionsEmpName);
			form.findField('regionsEmpName').show();
		}
		if (chatsVo.businessUnitEmpName != null) {
			form.findField('businessUnitEmpName').setValue(
					chatsVo.businessUnitEmpName);
			form.findField('businessUnitEmpName').show();
		}
		if (chatsVo.odEmpName != null) {
			form.findField('odEmpName').setValue(chatsVo.odEmpName);
			form.findField('odEmpName').show();
		}
		if (chatsVo.ceoEmpName != null) {
			form.findField('ceoEmpName').setValue(chatsVo.ceoEmpName);
			form.findField('ceoEmpName').show();
		}
		if (chatsVo.teamManagerEmpName != null) {
			form.findField('teamManagerEmpName').setValue(chatsVo.teamManagerEmpName);
			form.findField('teamManagerEmpName').show();
		}
		if (chatsVo.saleManEmpName != null) {
			form.findField('saleManEmpName').setValue(chatsVo.saleManEmpName);
			form.findField('saleManEmpName').show();
		}
	}
	
	//获取签到信息
	if(chatsVo.chatsEntity.sign==null){
		//隐藏设置样式
		win.query('form')[1].setVisible(false);
		win.setHeight(360);
	}else{
		//设置预览图片dom
		var previewDiv = "<div id='preview'style='height:23.5em;width:34em;position:inherit;right:3em;bottom:11.1em;'><img/></div>";
		Ext.DomHelper.append("chatsDetailWinId", previewDiv);
		//显示设置样式
		win.query('form')[1].setVisible(true);
		win.setHeight(510);
		win.setPosition(89,0,false)
		//签到信息赋值
		var signInfoForm = win.query('form')[1].down('fieldset').down('form').getForm();
		Ext.getCmp('signImg').body.update("<img style='cursor:Crosshair;' src='"+ attachment_url + chatsVo.chatsEntity.sign.imgUrl +"'title='点击查看图片' height='600',width='1000' onclick='showBigPic(this)' onmouseover='showPreview(this)' onmouseout='hidePreview(this)' onerror='error(this)'/>")
		signInfoForm.findField('signEmpName').setValue(chatsVo.chatsEntity.sign.customerName);
		signInfoForm.findField('signAddress').setValue(chatsVo.chatsEntity.sign.signAddress);
		signInfoForm.findField('signDatetime').setValue(Ext.isEmpty(chatsVo.chatsEntity.sign.createDate) ? null : Ext.util.Format.date(new Date(chatsVo.chatsEntity.sign.createDate), 'Y-m-d H:i:s'));
		
	}
}
/**
 * 新增洽谈
 * 
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function ShowChatsAddWin() {
	// 新增洽谈
	var win = Ext.widget("chatsAddWin");
	var form = win.down("form").getForm();
	ChatsWin = win;
	var btn = win.down('button[action=reset]');
	// 判断是否是从其它页面跳转过来
	if (parent.allChildrenParamsMap.get('cId')) {
		btn.setText('关闭');
		btn.on('click', function() {
			win.close();
		})
		getCustomerInfo(form, parent.allChildrenParamsMap.get('cId'), null)
		parent.allChildrenParamsMap.put('cId', undefined)
		// 预约界面
	} else if (parent.allChildrenParamsMap.get('reserveInfo')) {
		btn.setText('关闭');
		btn.on('click', function() {
			win.close();
			//Ext.getCmp('chatsMainViewId').getChatsGrid().getStore().reload();
		})
		getReserve(form, parent.allChildrenParamsMap.get('reserveInfo'))
		parent.allChildrenParamsMap.put('reserveInfo', undefined)

	}
	win.show();
}
function getReserve(form, reserveEntity) {
	if (reserveEntity != null) {
		getCustomerInfo(form, reserveEntity.customerEntity.id, reserveEntity.id)
		 // 洽谈日期
		form.findField('startDate').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[0]);
		// 洽谈时间
		form.findField('hour').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[1]);
		form.findField('minute').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[2]);
		//洽谈时长
		form.findField('differentDate').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[3]+'');
	}
}
/**
 * 获取跳转界面的基本数据
 * 
 * @param id
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function getCustomerInfo(form, accountId, reserveId) {
	var customerEntity = {};
	var params = {};
	customerEntity.id = accountId;
	params.customerEntity = customerEntity;
	crm.requestJsonAjax(
			'../customer/customerAction!queryCustomerInfoById.action', params,
			function(response) {
				customerEntity = response.customerEntity;
				if (customerEntity != null) {
					form.findField('enterpriseName').setValue(
							customerEntity.enterpriseName);
					form.findField('enterpriseName').setReadOnly(true);
					// 客户id
					form.findField('accountId').setValue(customerEntity.id);
					form.findField('detailedAddress').setValue(customerEntity.detailedAddress);
					// 刷新联系人
					var contactName = form.findField('contactName');
					contactName.getStore().setAccountId(accountId);
					contactName.reset();
					contactName.getStore().load();
					// 刷新关联预约信息
					var reserveInfo = form.findField('reserveInfo');
					reserveInfo.getStore().setAccountId(accountId);
					reserveInfo.reset();
					reserveInfo.getStore().reload();
					// 刷新关联签到信息
					var signAddress = form.findField('signAddress');
					signAddress.getStore().setAccountId(accountId);
					signAddress.reset();
					signAddress.getStore().reload();
					if (reserveId != null) {
						form.findField('reserveInfo').setValue(reserveId)
								.setReadOnly(true);
					}
					
					// 参与人员
					var deptEntity = {};
					if (customerEntity.tierCode != "") {
						deptEntity.logistCode = customerEntity.tierCode;
						params.deptEntity = deptEntity;
						 params.customerManageEmpCode = customerEntity.manageEmpCode;
						 params.customerManageEmpName = customerEntity.managePerson;
						crm.requestJsonAjax(
								'../bse/queryDeptSuperiorDept.action', params,
								function(response) {
									setEntourageValue(form, response)
								}, function() {
									Ext.MessageBox.alert('提示', '查询陪同人员信息失败');
								});
					} else {
						Ext.MessageBox.alert('提示', '无门店编号')
					}
				}
			}, function() {
				Ext.MessageBox.alert('提示', '查询客户信息失败');
			});
}

