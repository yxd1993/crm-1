/**
 * 客户信息Controller
 */
Ext.define('crm.controller.CustomerInfoPool', {
    extend: 'Ext.app.Controller',
    stores: ['CustomerInfoPool'],
    models: ['CustomerInfoPool'],
    views: ['Viewport', 'customerInfoPool.List', 'customerInfoPool.Search', 'customerInfoPool.Look', 'customerInfoPool.Add', 'customerInfoPool.Import', 'customerInfoPool.Back','customerInfoPool.Transfer'],
    init: function () {
        this.control({
            'customerPoolSearch button[action=reset]': {
                click: this.resetCustomerSearchForm
            },
            'customerPoolSearch button[action=select]': {
                click: this.reloadCustomerGridStore
            },
            'customerPoolSearch button[action=add]': {
                click: this.addCustomer
            },
            'customerPoolSearch button[action=update]': {
                click: this.updateCustomer
            },
            'customerPoolSearch button[action=delete]': {
                click: this.deleteCustomer
            },
            'customerPoolSearch button[action=import]': {
                click: this.importWin
            },
            'customerPoolSearch button[action=export]': {
                click: this.exportCustomer
            },
            'customerPoolSearch button[action=transfer]': {
                click: this.transferCustomer
            },
            'customerPoollist': {
                itemdblclick: this.lookCustomerInfo
            },
            'customerInfoPoolAddWin button[action=reset]': {
            	click: this.resetAddForm
            },
            'customerInfoPoolAddWin button[action=submit]': {
            	click: this.submitAddForm
            },
            'customerInfoPoolLookWin button[action=back]': {
                click: this.backCustomer
            },
            'customerInfoPoolBackWin button[action=reset]': {
            	click: this.resetBackForm
            },
            'customerInfoPoolBackWin button[action=submit]': {
            	click: this.submitBackForm
            },
            'customerInfoPoolLookWin button[action=change]': {
                click: this.changeCustomer
            },
            'customerInfoPoolTransferWin button[action=reset]': {
            	click: this.resetTransferForm
            },
            'customerInfoPoolTransferWin button[action=submit]': {
            	click: this.submitTransferForm
            }
            
        });
    },
    resetCustomerSearchForm : function(btn){
    	// 清空查询条件
    	btn.up('form').getForm().reset();
    },
    reloadCustomerGridStore : function(btn){
    	// 重新加载表格数据
    	Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getPagingToolbar().moveFirst();
    },
    addCustomer : function(){
    	// 新增客户
    	var win = Ext.widget("customerInfoPoolAddWin");
    	win.show();
    	var form = win.down('form').getForm();
    	form.findField('contactWay').setValue(null);
    },
    updateCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的客户信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个客户信息进行修改');
			return;
		}
    	var win = Ext.widget("customerInfoPoolAddWin"); 
    	var form = win.down("form");
    	win.title = '修改共享客户信息';
    	form.record = selectArr[0];
    	win.down("form").loadRecord(selectArr[0]);
    	// 省、市、区加载
    	form.getForm().findField('provinceCode').fireEvent('select', form.getForm().findField('provinceCode'));
    	form.getForm().findField('cityCode').setValue(selectArr[0].data['cityCode']);
    	form.getForm().findField('cityCode').fireEvent('select', form.getForm().findField('cityCode'));
    	form.getForm().findField('areaCode').setValue(selectArr[0].data['areaCode']);
    	form.getForm().findField('business').fireEvent('select', form.getForm().findField('business'));
    	form.getForm().findField('regionsCode').setValue(selectArr[0].data['regionsCode']);
        win.show();
    },
    lookCustomerInfo : function(grid, record){
    	var win = Ext.widget("customerInfoPoolLookWin"); 
    	var form = win.down("form");
    	win.down("form").loadRecord(record);
    	form.getForm().findField('dispenseStatus').setValue(getDataDictionary().rendererSubmitToDisplay(record.get('dispenseStatus'), "CUSTOMER_DISSTATE"));
    	form.getForm().findField('business').setValue(getDataDictionary().rendererSubmitToDisplay(record.get('business'), "CUSTOMERINFOPOOL_BUSINESS"));
        win.show();
        win.on('beforeclose', function() {
        	Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
    	});
//        Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
    },
    deleteCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
			Ext.Msg.confirm('提示', '您确定要删除选中的客户信息？', function(btn) {
				if(btn == 'yes') {
					//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].getData().id)
					}
					var params = {};
					params.ids = deleteInfoArr;
					// AJAX请求
					crm.requestJsonAjax('customerInfoPoolAction!deleteCustomerInfoPool.action', params, 
							function(){
								Ext.MessageBox.alert('提示','客户信息删除成功');
								Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
							}, 
							function(){Ext.MessageBox.alert('提示','客户信息删除失败');});
				}
			})
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的客户信息');
		}
    },
    importWin : function(){
    	var win = Ext.widget("customerInfoPoolImportWin"); 
        win.show();
    },
    exportCustomer : function(){
    	// 选中的数据
    	var dataSize = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().getCount();
    	if(dataSize == 0){
    		Ext.MessageBox.alert('提示','当前无数据需要导出');
    		return;
    	}
    	Ext.MessageBox.confirm('提示','您确定要导出的客户信息？',function(btn){
			if(btn == 'yes'){
				if (!Ext.fly('downForm')) {
					var downForm = document
							.createElement('form');
					downForm.id = 'downForm';
					downForm.name = 'downForm';
					downForm.className = 'x-hidden';
					downForm.action = 'export.action';
					document.charset='UTF-8';
					downForm.method = 'post';
					//downForm.target = '_blank'; // 打开新的下载页面
					var uploadDeptInput = document.createElement('input');
					var companyNameInput = document.createElement('input');
					var contactPersonInput = document.createElement('input');
					var contactWayInput = document.createElement('input');
					var regionsInput = document.createElement('input');
					var managePersonInput = document.createElement('input');
					var companyAddressInput = document.createElement('input');
					var dispenseStatusInput = document.createElement('input');
					var startDateInput = document.createElement('input');
					var endDateInput = document.createElement('input');
					uploadDeptInput.type = 'hidden';// 隐藏域
					companyNameInput.type = 'hidden';// 隐藏域
					contactPersonInput.type = 'hidden';// 隐藏域
					contactWayInput.type = 'hidden';// 隐藏域
					regionsInput.type = 'hidden';// 隐藏域
					managePersonInput.type = 'hidden';// 隐藏域
					dispenseStatusInput.type = 'hidden';// 隐藏域
					companyAddressInput.type = 'hidden';// 隐藏域
					startDateInput.type = 'hidden';// 隐藏域
					endDateInput.type = 'hidden';// 隐藏域
					uploadDeptInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.employeeEntity.deptname';// form表单参数-mainClass.mcNo
					companyNameInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.companyName';
					contactPersonInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.contactPerson';
					contactWayInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.contactWay';
					regionsInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.regions';
					managePersonInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.managePerson';
					dispenseStatusInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.dispenseStatus';
					companyAddressInput.name = 'customerInfoPoolVo.customerInfoPoolEntity.companyAddress';
					startDateInput.name = 'customerInfoPoolVo.startDate';
					endDateInput.name = 'customerInfoPoolVo.endDate';
					uploadDeptInput.value = CustomerInfoPool.uploadDept;// form表单参数-mainClass.mcNo
					companyNameInput.value = CustomerInfoPool.companyName;
					contactPersonInput.value = CustomerInfoPool.contactPerson;
					contactWayInput.value = CustomerInfoPool.contactWay;
					regionsInput.value = CustomerInfoPool.regions;
					managePersonInput.value = CustomerInfoPool.managePerson;
					dispenseStatusInput.value = CustomerInfoPool.dispenseStatus;
					companyAddressInput.value = CustomerInfoPool.companyAddress;
					if(!Ext.isEmpty(CustomerInfoPool.startDate)){
						startDateInput.value = Ext.util.Format.date(new Date(CustomerInfoPool.startDate), 'Y-m-d H:i:s');
					}
					if(!Ext.isEmpty(CustomerInfoPool.endDate)){
						endDateInput.value = Ext.util.Format.date(new Date(CustomerInfoPool.endDate), 'Y-m-d H:i:s');
					}
					downForm.appendChild(uploadDeptInput);
					downForm.appendChild(companyNameInput);
					downForm.appendChild(contactPersonInput);
					downForm.appendChild(contactWayInput);
					downForm.appendChild(regionsInput);
					downForm.appendChild(managePersonInput);
					downForm.appendChild(dispenseStatusInput);
					downForm.appendChild(companyAddressInput);
					downForm.appendChild(startDateInput);
					downForm.appendChild(endDateInput);
					document.body.appendChild(downForm);
				}
				Ext.fly('downForm').dom.submit();
				if (Ext.fly('downForm')) {
					document.body.removeChild(downForm);
				}
			}
		});
	},
    transferCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
    		//转让客户信息集合
			var transferInfoArr = [];
			for(var i=0; i<selectArr.length; i++){
				transferInfoArr.push(selectArr[i].getData().id)
			}
			CustomerInfoPool.ids = transferInfoArr;
			var win = Ext.widget("customerInfoPoolTransferWin"); 
	        win.show();
		}else{
			Ext.MessageBox.alert('提示','请选择需要转让的客户信息');
		}
    },
    resetAddForm : function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    },
    submitAddForm : function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	// 保存信息
    	var params = {};
    	var customerInfoPoolEntity = {};
    	customerInfoPoolEntity.id = form.findField('id').getValue();
    	customerInfoPoolEntity.companyName = form.findField('companyName').getValue();
    	customerInfoPoolEntity.contactPerson = form.findField('contactPerson').getValue();
    	customerInfoPoolEntity.contactWay = form.findField('contactWay').getValue();
    	customerInfoPoolEntity.business = form.findField('business').getValue();
    	customerInfoPoolEntity.regions = form.findField('regions').getValue();
    	customerInfoPoolEntity.email = form.findField('email').getValue();
    	customerInfoPoolEntity.province = form.findField('province').getValue();
    	customerInfoPoolEntity.city = form.findField('city').getValue();
    	customerInfoPoolEntity.area = form.findField('area').getValue();
    	//设置城市中文名称
    	customerInfoPoolEntity.cityCode = form.findField('city').getRawValue()
    	
    	customerInfoPoolEntity.companyAddress = form.findField('companyAddress').getValue();
    	
    	/*// 手机与电话二选一
		if(Ext.isEmpty(customerInfoPoolEntity.telephone) && Ext.isEmpty(customerInfoPoolEntity.cellphone)){
			Ext.MessageBox.alert('提示','"手机"与"座机"必须二选一，建议都填'); 
			return;
		}*/
    	params.customerInfoPoolEntity = customerInfoPoolEntity;
		// AJAX请求
		crm.requestJsonAjax('customerInfoPoolAction!addOrEditCustomerInfoPool.action', params, 
				function(response){
					Ext.MessageBox.alert('提示','客户信息保存成功');
					Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
					win.close();
				}, 
				function(response){Ext.MessageBox.alert('提示', response.message);});
    },
    backCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要退回的客户信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个客户信息进行退回');
			return;
		}
    	var win = Ext.widget("customerInfoPoolBackWin"); 
    	var form = win.down("form");
    	win.title = '退回信息';
    	form.record = selectArr[0];
    	win.down("form").loadRecord(selectArr[0]);
        win.show();
    },
    resetBackForm : function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    },
    submitBackForm : function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	// 保存信息
    	var params = {};
    	var customerInfoPoolEntity = {};
    	customerInfoPoolEntity.id = form.findField('id').getValue();
    	customerInfoPoolEntity.backReason = form.findField('backReason').getValue();
    	params.customerInfoPoolEntity = customerInfoPoolEntity;
		// AJAX请求
		crm.requestJsonAjax('customerInfoPoolAction!backCustomer.action', params, 
				function(response){
					Ext.MessageBox.alert('提示','客户信息退回成功',function(){
						Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
				    	win.close();
					});
				}, 
				function(response){Ext.MessageBox.alert('提示', response.message);});
    },
    changeCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要转为潜在客户的客户信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个客户信息进行转为潜在客户');
			return;
		}
    	// 新增客户
    	window.parent.delTabpanel('101002');
    	var customerInfoPoolId = selectArr[0].get('id');
    	var url = '/crm-web/customer/addCustomer.action?customerInfoPoolId=' + customerInfoPoolId;
    	window.parent.initTabpanel('101002', '新增客户', url, true);
    },
    resetTransferForm : function(btn){
    	// 清空或还原数据
    	var form = btn.up('form');
    	var record = form.record;
    	if(Ext.isEmpty(record)){
    		form.getForm().reset();
    	}else{
    		form.loadRecord(record);
    	}
    },
    submitTransferForm : function(btn){
    	var form = btn.up('form').getForm();
    	var win = btn.up('window');
    	if(!form.isValid()){
    		return;
    	}
    	Ext.Msg.confirm('提示', '您确定要转让选中的客户？', function(btn) {
			if (btn == "yes"){
				// 保存信息
		    	var params = {};
		    	var transferCustomerVO = {};
		    	transferCustomerVO.ids = CustomerInfoPool.ids;
		    	transferCustomerVO.newManagerCode = form.findField('newManagerCode').getValue();
		    	params.transferCustomerVO = transferCustomerVO;
				// AJAX请求
				crm.requestJsonAjax('customerInfoPoolAction!transferCustomer.action', params, 
					function(response){
						Ext.MessageBox.alert('提示','客户转让成功',function(){
							Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getStore().reload();
					    	win.close();
						});
					}, 
					function(response){Ext.MessageBox.alert('提示', '客户转让失败');});
			}
    	})
    }
});
