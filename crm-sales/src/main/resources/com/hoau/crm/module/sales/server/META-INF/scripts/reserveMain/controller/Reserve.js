/*回滚版本*/
/**
 * 预约客户Controller
 */
Ext.define('crm.controller.Reserve', {
    extend: 'Ext.app.Controller',
    stores: ['Reserve', 'Contact'],
    models: ['Reserve', 'Contact'],
    views: ['Viewport', 'reserve.List', 'reserve.Search','reserve.Add','reserve.Detail'],
    init: function () {
        this.control({
            'reserveSearch button[action=reset]': {
                click: this.resetReserveSearchForm
            },
            'reserveSearch button[action=select]': {
                click: this.reloadReserveGridStore
            },
            'reserveSearch button[action=add]': {
                click: this.addReserve
            },
            'reserveSearch button[action=update]': {
                click: this.updateReserve
            },
            'reserveSearch button[action=delete]':{
            	click:this.delReserveDataForm
            },
            'reserveAddWin button[action=submit]':{
            	click:this.submitReserveDataForm
            },
            'reserveAddWin button[action=reset]':{
            	click:this.resetReserveDataForm
            },
            'reservelist': {
                itemdblclick: this.lookReserveInfo
            },
            'reserveDetailWin button[action=addChats]':{
            	click:this.addChats
            },
            
        });
    },
    resetReserveSearchForm : function(btn){
    	// 重置表单
    	btn.up('form').getForm().reset();
    },
    reloadReserveGridStore : function(btn){
    	Ext.getCmp('reserveMainViewId').getReserveGrid().getPagingToolbar().moveFirst();
    },
    lookReserveInfo:function(btn){
    	var selectArr = Ext.getCmp('reserveMainViewId').getReserveGrid().getSelectionModel().getSelection();
    	var id = selectArr[0].get('id');
    	var win = Ext.widget("reserveDetailWin"); 
    	var form = win.down("form").getForm();
    	if(!Ext.isEmpty(id)){
    		var params = {};
    		var reserveEntity = {};
    		reserveEntity.id = id;
    		params.reserveEntity = reserveEntity;
    		crm.requestJsonAjax('saleReservePlanAction!queryReserveByIdGetVo.action', params, 
    				function(response){
    					setReserveDataInForm(form,response);
    				},
    				function(){Ext.MessageBox.alert('提示','查询预约信息失败');
    				})
    	}
    	win.show();
    },
    addReserve : function(){
    	ShowReserveAddWin();
    },
    updateReserve:function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('reserveMainViewId').getReserveGrid().getSelectionModel().getSelection();
    	var currDate = new Date();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的预约信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个预约信息进行修改');
			return;
		}else if(selectArr[0].get('isAgreement')=='Y'){
    		return Ext.MessageBox.alert('提示','请选择未赴约的拜访计划');
		}else if(selectArr[0].get('reserveStartTime')<currDate){
    		return Ext.MessageBox.alert('提示','该预约时间已过期,无法修改');
    	}else{
	    	var win = Ext.widget("reserveAddWin"); 
	    	var form = win.down('form').getForm();
	    	// 获取id
	        var id = selectArr[0].get('id');
	        var params = {};
			var reserveEntity = {};
			reserveEntity.id = id;
			params.reserveEntity = reserveEntity;
	        crm.requestJsonAjax('saleReservePlanAction!queryReserveByIdGetEntity.action', params, 
					function(response){
	        			initReserveData(form, response);
					},
					function(){Ext.MessageBox.alert('提示','查询预约信息失败');
					})
	        win.title = '修改预约信息';
	        ReserveWin = win;
	        win.show();
    	}
    },
    delReserveDataForm:function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('reserveMainViewId').getReserveGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
//	    		Ext.MessageBox.prompt('删除预约信息', '请填写删除预约的原因：', function(btn, text){
//	    		    if (btn == 'ok'){
//	    		        // 处理文本值并且关闭...
//	    		    }
//	    		});
			for(var i = 0;i < selectArr.length;i++){
				if(selectArr[i].get('isAgreement')=='Y')
	        		return Ext.MessageBox.alert('提示','请选择未赴约的拜访计划');
			}
			  Ext.MessageBox.show({
			        title: "预约信息作废",
			        msg: "请填写作废原因：",
			        width: 350,
			        buttons: Ext.MessageBox.OKCANCEL,
			        multiline: true,
			        animateTarget: btn,
			        fn: delReserve,
			    });
		}else{
			Ext.MessageBox.alert('提示','请选择需要作废的预约信息');
		}
    	function delReserve(btn,text){
    		if (btn == 'ok') {
    			if(text.length>500){
    				Ext.MessageBox.alert('提示','删除预约信息原因过长');
    				return ;
    			}else{
	    			if(text!=''){
	    				// 删除的信息集合
	    				var deleteInfoArr = [];
	    				for (var i = 0; i < selectArr.length; i++) {
	    					deleteInfoArr.push(selectArr[i].getData().id)
	    				}
	    				var params = {};
	    				var reserveEntity = {};
	    				params.ids = deleteInfoArr;
	    				reserveEntity.delDesc = text;
	    				params.reserveEntity = reserveEntity;
	    				// AJAX请求
	    				crm.requestJsonAjax(
	    						'../sales/saleReservePlanAction!delReserve.action', params,
	    						function() {
	    							Ext.MessageBox.alert('提示', '预约信息作废成功');
	    							Ext.getCmp('reserveMainViewId').getReserveGrid()
	    									.getStore().reload();
	    						}, function() {
	    							Ext.MessageBox.alert('提示', '预约信息作废失败');
	    						});
	    			}else{
	    				Ext.MessageBox.alert('提示', '删除失败,尚未填写删除原因');
	    				return;
	    			}
    			}
			}
    	}
    },
    addChats:function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	var params = {};
    	var reserveEntity = {};
    	var customerEntity = {};
    	var contactEntity = {};
    	// id
    	reserveEntity.id = form.findField('id').getValue();    
    	// 客户企业名称
        customerEntity.id = form.findField('accountId').getValue();
        // 预约开始时间
    	reserveEntity.reserveStartTime = form.findField('reserveStartTime').getValue();
    	// 预约结束时间
    	reserveEntity.reserveEndTime =  form.findField('reserveEndTime').getValue();
    	reserveEntity.customerEntity = customerEntity;
    	params.reserveEntity  = reserveEntity;
    	crm.requestJsonAjax('saleReservePlanAction!queryReserveByIdGetEntity.action', params, 
				function(response){
        			if(response.reserveEntity.isAgreement=='Y'){
        				Ext.MessageBox.alert('提示','该预约信息已被赴约')
        			}else{
        				win.close();
        				window.parent.allChildrenParamsMap.put('reserveInfo', reserveEntity);
        				window.parent.initTabpanel('103002', '洽谈列表', '/crm-web/sales/chatsMain.action', true);
        			}
				},
				function(){Ext.MessageBox.alert('提示','查询预约信息失败');
				})
    	
    },
    submitReserveDataForm:function(btn){
    	submitReserve(btn);
    },
    resetReserveDataForm:function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    }
});
/**
 * 提交方法
 * 
 * @param btn
 * @param submittype
 * @author 丁勇
 * @date 2015年7月3日
 * @update
 */
function submitReserve(btn){
	var form = btn.up('form').getForm();
	var win = btn.up('window');
	if(!form.isValid()){
		Ext.MessageBox.alert('提示','必填信息或输入信息有误');
		return;
	}
	// 保存信息
	var params = {};
	var reserveEntity = {};
	var customerEntity = {};
	var contactEntity = {};
	// id
	reserveEntity.id = form.findField('id').getValue();    
	// 客户企业名称
    customerEntity.id = form.findField('accountId').getValue();
	// 预约开始时间
	reserveEntity.reserveStartTime = getDateTime(form)[0];
	// 预约结束时间
	reserveEntity.reserveEndTime = getDateTime(form)[1];
	// 联系人
	contactEntity.id = form.findField('contactName').getValue();
	// 预约方式
	reserveEntity.reserveType =form.findField('reserveType').getValue();
	//提醒时间
	reserveEntity.warningTime = form.findField('warningTime').getValue();
	
	// 参与人员
	if(!form.findField('tierEmpName').isDisabled()){
		reserveEntity.comTierEmpCode  = form.findField('tierEmpName').getEmpCode();
	}
	
	if(!form.findField('roadEmpName').isDisabled()){
		reserveEntity.comRoadEmpCode  = form.findField('roadEmpName').getEmpCode();
	}
	if(!form.findField('regionsEmpName').isDisabled()){
		reserveEntity.comRegionsEmpCode  = form.findField('regionsEmpName').getEmpCode();
	}
	if(!form.findField('businessUnitEmpName').isDisabled()){
		reserveEntity.comBusinessEmpCode  = form.findField('businessUnitEmpName').getEmpCode();
	}
	if(!form.findField('odEmpName').isDisabled()){
		reserveEntity.comOdEmpCode  = form.findField('odEmpName').getEmpCode();
	}
	if(!form.findField('ceoEmpName').isDisabled()){
		reserveEntity.comCeoEmpCode  = form.findField('ceoEmpName').getEmpCode();
	}
	if(!form.findField('teamManagerEmpName').isDisabled()){
		reserveEntity.teamManagerEmpCode  = form.findField('teamManagerEmpName').getEmpCode();
	}
	if(!form.findField('saleManEmpName').isDisabled()) {
		reserveEntity.saleManEmpCode = form.findField('saleManEmpName').getEmpCode();
	}
	if(Ext.isEmpty(customerEntity.id)){
		Ext.MessageBox.alert('提示','客户选择错误，输入条件并点击查询，查询结果中选择客户'); 
		return;
	}
	// 预约主题
	reserveEntity.reserveTheme = form.findField('reserveTheme').getValue();
	// 预约内容
	reserveEntity.reserveContents = form.findField('reserveContents').getValue();
	reserveEntity.customerEntity = customerEntity;
	reserveEntity.contactEntity = contactEntity;
	params.reserveEntity = reserveEntity;
	// 提示正在保存中
	win.getEl().mask("数据保存中...");
	// AJAX请求
	crm.requestJsonAjax('saveOrUpdateReserve.action', params, 
			function(response){
			// 提示
			win.getEl().unmask();
				Ext.MessageBox.alert('提示','预约信息保存成功',function(){
					window.parent.delTabpanel('101001201');
					Ext.getCmp('reserveMainViewId').getReserveGrid().getStore().reload();
				});
				win.close();
			}, 
			function(response){
				// 提示
				win.getEl().unmask();
				Ext.MessageBox.alert('提示', response.message);});
}
/**
 * 查看详情
 * 
 * @author 丁勇
 * @date 2015-6-18
 * @update
 */
function setReserveDataInForm(form,response){
	var reserveVo = response.reserveVo;
	var reserveEntity = response.reserveVo.reserveEntity;
	if(reserveEntity.customerEntity!=null){
		form.findField('id').setValue(reserveEntity.id);
		form.findField('accountId').setValue(reserveEntity.customerEntity.id);
		// 客户企业名称
	    form.findField('enterpriseName').setValue(reserveEntity.customerEntity.enterpriseName);
	    // 办公地址
		form.findField('detailedAddress').setValue(reserveEntity.customerEntity.detailedAddress);
	}
	// 预约开始时间
	form.findField('reserveStartTime').setValue(Ext.isEmpty(reserveEntity.reserveStartTime) ? null : Ext.util.Format.date(new Date(reserveEntity.reserveStartTime), 'Y-m-d H:i:s'));
	// 预约结束时间
	form.findField('reserveEndTime').setValue(Ext.isEmpty(reserveEntity.reserveEndTime) ? null : Ext.util.Format.date(new Date(reserveEntity.reserveEndTime), 'Y-m-d H:i:s'));
	//提醒时间
	form.findField('warningTime').setValue(getDataDictionary().rendererSubmitToDisplay(reserveEntity.warningTime, "REMIND_DATE"));
	// 联系人
	if(reserveEntity.contactEntity!=null){
		form.findField('contactName').setValue(reserveEntity.contactEntity.contactName);
		// 联系电话
		if(!Ext.isEmpty(reserveEntity.contactEntity.cellphone)){
			form.findField('cellphone').setValue(reserveEntity.contactEntity.cellphone);
		}else if(!Ext.isEmpty(reserveEntity.contactEntity.telephone)){
			form.findField('cellphone').setValue(reserveEntity.contactEntity.telephone);
		}else{
			form.findField('cellphone').reset();
		}
	}
	// 预约方式
	form.findField('reserveType').setValue(getDataDictionary().rendererSubmitToDisplay(reserveEntity.reserveType, "CUSTOMER_YXLX"));
	// 预约主题
	form.findField('reserveTheme').setValue(reserveEntity.reserveTheme);
	// 预约内容
	form.findField('reserveContents').setValue(reserveEntity.reserveContents);
	// 判断是否有陪同人员
	if(reserveVo.tierEmpName!=null){
		form.findField('tierEmpName').setValue(reserveVo.tierEmpName);
		form.findField('tierEmpName').show();
	}
	if(reserveVo.roadEmpName!=null){
		form.findField('roadEmpName').setValue(reserveVo.roadEmpName);
		form.findField('roadEmpName').show();
	}
	if(reserveVo.regionsEmpName!=null){
		form.findField('regionsEmpName').setValue(reserveVo.regionsEmpName);
		form.findField('regionsEmpName').show();
	}
	if(reserveVo.businessUnitEmpName!=null){
		form.findField('businessUnitEmpName').setValue(reserveVo.businessUnitEmpName);
		form.findField('businessUnitEmpName').show();
	}
	if(reserveVo.odEmpName!=null){
		form.findField('odEmpName').setValue(reserveVo.odEmpName);
		form.findField('odEmpName').show();
	}
	if(reserveVo.ceoEmpName!=null){
		form.findField('ceoEmpName').setValue(reserveVo.ceoEmpName);
		form.findField('ceoEmpName').show();
	}
	if (reserveVo.teamManagerEmpName != null) {
		form.findField('teamManagerEmpName').setValue(reserveVo.teamManagerEmpName);
		form.findField('teamManagerEmpName').show();
	}
	if (reserveVo.saleManEmpName != null) {
		form.findField('saleManEmpName').setValue(reserveVo.saleManEmpName);
		form.findField('saleManEmpName').show();
	}
}


/**
 * 初始化预约修改信息
 * 
 * @author 蒋落琛
 * @date 2015-6-18
 * @update
 */
function initReserveData(form, response){
	var reserveEntity = response.reserveEntity;
	// id
	form.findField('id').setValue(reserveEntity.id);    
	if(reserveEntity.customerEntity!=null){
		// 客户企业名称
		form.findField('enterpriseName').setCombValue(
				reserveEntity.customerEntity.enterpriseName,
				reserveEntity.customerEntity.enterpriseName);
	    // 办公地址
		form.findField('detailedAddress').setValue(reserveEntity.customerEntity.detailedAddress);
	}
    form.findField('accountId').setValue(reserveEntity.customerEntity.id);
	// 约定日期
	form.findField('startDate').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[0]);
	//约定时间
	form.findField('hour').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[1]);
	form.findField('minute').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[2]);
	// 预约时长
	form.findField('differentDate').setValue(setDateTime(reserveEntity.reserveStartTime,reserveEntity.reserveEndTime)[3]+'');
	
	// 提醒时间
	form.findField('warningTime').setValue(reserveEntity.warningTime);
	// 联系人
	if (reserveEntity.contactEntity != null) {
		var contactName = form.findField('contactName');
		contactName.getStore().setCurrContactId(reserveEntity.contactEntity.id);
		contactName.getStore().setAccountId(reserveEntity.customerEntity.id);
		contactName.getStore().load();
	}
	// 预约方式
	form.findField('reserveType').setValue(reserveEntity.reserveType);
	// 参与人员
	var params = {};
	var deptEntity = {};
	if(reserveEntity.customerEntity.tierCode!=""){
		deptEntity.logistCode = reserveEntity.customerEntity.tierCode;
		params.deptEntity = deptEntity;
		params.customerManageEmpCode = reserveEntity.customerEntity.manageEmpCode;
		params.customerManageEmpName = reserveEntity.customerEntity.managePerson;
		crm.requestJsonAjax('../bse/queryDeptSuperiorDept.action', params, 
				function(response){
					setEntourageValue(form, response);
				}, 
				function(){Ext.MessageBox.alert('提示','查询陪同人员信息失败');});
	}else{
		Ext.MessageBox.alert('提示','无门店编号')
	}
	
	// 选中上次选中的
	if(!Ext.isEmpty(reserveEntity.comTierEmpCode)){
		form.findField('comTierEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.comRoadEmpCode)){
		form.findField('comRoadEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.comRegionsEmpCode)){
		form.findField('comRegionsEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.comBusinessEmpCode)){
		form.findField('comBusinessEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.comOdEmpCode)){
		form.findField('comOdEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.comCeoEmpCode)){
		form.findField('comCeoEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.teamManagerEmpCode)){
		form.findField('teamManagerEmpCode').setValue(true);
	}
	if(!Ext.isEmpty(reserveEntity.saleManEmpCode)){
		form.findField('saleManEmpCode').setValue(true);
	}
	// 预约主题
	form.findField('reserveTheme').setValue(reserveEntity.reserveTheme);
	// 预约内容
	form.findField('reserveContents').setValue(reserveEntity.reserveContents);
}

/**
 * 新增预约
 * 
 * @author 蒋落琛
 * @date 2015-6-26
 * @update web_dingyong
 */
function ShowReserveAddWin(){
	// 新增预约
	var win = Ext.widget("reserveAddWin"); 
	var form = win.down("form").getForm();
	var btn = Ext.getCmp('reset')
	ReserveWin = win;
	// 判断是否是从其它页面跳转过来
	if(parent.allChildrenParamsMap.get('ChartsInfo')){
		btn.setText('关闭');
		btn.on('click',function(){
			win.close();
			Ext.getCmp('reserveMainViewId').getReserveGrid().getStore().reload();
		})
		getChatsInfo(form,parent.allChildrenParamsMap.get('ChartsInfo'))
		parent.allChildrenParamsMap.put('ChartsInfo', undefined)
	}else if(parent.allChildrenParamsMap.get('cId')){
		btn.setText('关闭');
		btn.on('click',function(){
			win.close();
			Ext.getCmp('reserveMainViewId').getReserveGrid().getStore().reload();
		})
		getCustomerInfo(form,parent.allChildrenParamsMap.get('cId'))
		parent.allChildrenParamsMap.put('cId', undefined)
	}
    win.show();
}
/**
 * 获取洽谈一些基本信息,和客户id
 * 
 * @param form
 * @param vo
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function  getChatsInfo(form,vo){
	var chatsEntity = vo.chatsEntity;
	var customerEntity = chatsEntity.customerEntity;
	if(chatsEntity!=null){
		getCustomerInfo(form,customerEntity.id)
		// 约定日期
	form.findField('startDate').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[0]);
	//约定时间
	form.findField('hour').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[1]);
	form.findField('minute').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[2]);
	// 预约时长
	form.findField('differentDate').setValue(setDateTime(chatsEntity.chatStartTime,chatsEntity.chatEndTime)[3]+'');
	}
}
/**
 * 获取客户基本数据
 * 
 * @param id
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function getCustomerInfo(form,id){
	var customerEntity = {};
	var params ={};
	customerEntity.id = id;
	params.customerEntity = customerEntity;
	crm.requestJsonAjax('../customer/customerAction!queryCustomerInfoById.action', params, 
			function(response){
				customerEntity = response.customerEntity;
				setCustomerEntity(form,customerEntity);
			}, 
			function(){Ext.MessageBox.alert('提示','查询客户信息失败');});
}
/**
 * 根据洽谈信息页面进行赋值
 * 
 * @param form
 * @param customerEntity
 * @author 丁勇
 * @date 2015年7月1日
 * @update
 */
function setCustomerEntity(form,customerEntity){
	//console.log(customerEntity)
	if(customerEntity!=null){
		form.findField('enterpriseName').setValue(customerEntity.enterpriseName);
		form.findField('enterpriseName').setReadOnly(true);
		form.findField('accountId').setValue(customerEntity.id);
		form.findField('detailedAddress').setValue(customerEntity.detailedAddress);
		// 刷新联系人
		var contactName = form.findField('contactName');
		contactName.getStore().setAccountId(customerEntity.id);
		contactName.reset();
		contactName.getStore().load();
		// 参与人员
		var params = {};
		var deptEntity = {};
		if(customerEntity.tierCode!=""){
			deptEntity.logistCode = customerEntity.tierCode;
			params.deptEntity = deptEntity;
			params.customerManageEmpCode = customerEntity.manageEmpCode;
			params.customerManageEmpName = customerEntity.managePerson;
			crm.requestJsonAjax('../bse/queryDeptSuperiorDept.action', params, 
					function(response){
						setEntourageValue(form, response);
					}, 
					function(){Ext.MessageBox.alert('提示','查询陪同人员信息失败');});
		}else{
			Ext.MessageBox.alert('提示','无门店编号')
		}
	}
}