/**
 * 销售合同Controller
 */
Ext.define('crm.controller.Contract', {
    extend: 'Ext.app.Controller',
    stores: ['Contract'],
    models: ['Contract'],
    views: ['Viewport', 'contract.List', 'contract.Search','contract.Add'],
    init: function () {
        this.control({
            'contractSearch button[action=reset]': {
                click: this.resetContractSearchForm
            },
            'contractSearch button[action=select]': {
                click: this.reloadContractGridStore
            },
            'contractSearch button[action=add]': {
                click: this.addContract
            },
            'contractSearch button[action=update]': {
                click: this.updateContract
            },
            'contractSearch button[action=delete]': {
                click: this.deleteContract
            },
            'contractSearch button[action=export]': {
                click: this.exportContract
            },
            'contractList': {
                itemdblclick: this.lookContract
            }
        });
    },
    resetContractSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadContractGridStore : function(btn){
    	Ext.getCmp('contractMainViewId').getContractGrid().getPagingToolbar().moveFirst();
    },
    addContract :function(){
    	ShowContractAddWin();
    },
    updateContract :function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('contractMainViewId').getContractGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要修改的合同信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','只能选择一个合同信息进行修改');
			return;
		}else if(selectArr[0].get("status") != 1){
			Ext.MessageBox.alert('提示',"只能修改合同状态为'已上传'的合同信息");
			return;
		}
    	var win = Ext.widget("contractAddWin"); 
    	var form = win.down("form");
    	win.title = '修改合同';
    	form.record = selectArr[0];
    	win.down("form").loadRecord(selectArr[0]);
    	//获得DC账号文本框组件
    	var dcAccountField = form.getForm().findField('dcAccount');
    	dcAccountField.setEditable(false);
    	dcAccountField.setFieldStyle('background-color:#ececec;');
        win.show();
    },
    deleteContract : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('contractMainViewId').getContractGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
    		for(var i=0; i<selectArr.length; i++){
	    		var contractStatus = selectArr[i].get('status');
	    		if(contractStatus == 2 || contractStatus == 3 ){
	    			Ext.MessageBox.alert('提示','受理中或已归档的合同信息不能删除！');
	    			return;
	    		}
			}
			Ext.MessageBox.confirm('提示','您确定要删除选中的合同信息？',function(btn){
				if(btn == 'yes'){
					//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].get('id'))
					}
					var params = {};
					params.ids = deleteInfoArr;
					// AJAX请求
					crm.requestJsonAjax('saleContractAction!deleteContract.action', params, 
							function(){
								Ext.MessageBox.alert('提示','合同信息删除成功');
								Ext.getCmp('contractMainViewId').getContractGrid().getStore().reload();
							}, 
							function(){
								Ext.MessageBox.alert('提示','合同信息删除失败');
							});
				}
			});
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的合同信息');
		}
    },
    lookContract : function(btn){
    	// 查看合同
    	window.parent.delTabpanel('101001301');
    	var selectArr = Ext.getCmp('contractMainViewId').getContractGrid().getSelectionModel().getSelection();
    	var cId = selectArr[0].get('id');
    	var contractStatus = selectArr[0].get('status');
		if(contractStatus == 4){
			Ext.MessageBox.alert('提示','合同信息已删除,无相关合同信息！');
			return;
		}
    	var url = '/crm-web/sales/lookContract.action?cId=' + cId+'&status='+contractStatus;
    	window.parent.initTabpanel('101001301', '查看合同信息', url, true);
    },
    exportContract : function(btn){
    	var dataSize = Ext.getCmp('contractMainViewId').getContractGrid().getStore().getCount();
    	if(dataSize == 0 ){
    		Ext.MessageBox.alert('提示','没有附件需要导出');
    		return;
    	}
    	// 选中的数据
    	var selectArr = Ext.getCmp('contractMainViewId').getContractGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
    		Ext.MessageBox.alert('提示',"请选择需要批量附件下载的合同信息！");
    		return;
    	}
    	for(var i=0; i<selectArr.length; i++){
    		var status = selectArr[i].get('status');
    		if(status != '1' ){
    			Ext.MessageBox.alert('提示',"批量附件下载的合同信息只能为'已上传'的合同信息！");
    			return;
    		}
		}
    	Ext.MessageBox.confirm('提示','您确定要下载选中的合同信息？',function(btn){
			if(btn == 'yes'){
				//下载的信息集合
				var exportInfoArr = [];
				for(var i=0; i<selectArr.length; i++){
					exportInfoArr.push(selectArr[i].get('id'))
				}
				// AJAX请求
				if (!Ext.fly('downForm')) {
					var downForm = document.createElement('form');
					downForm.id = 'downForm';
					downForm.name = 'downForm';
					downForm.className = 'x-hidden';
					downForm.action = 'batchExport.action';
					document.charset='UTF-8';
					downForm.method = 'post';
					downForm.target = '_blank'; // 打开新的下载页面
					var idsInput = document.createElement('input');
					idsInput.type = 'hidden';// 隐藏域
					idsInput.name = 'exportIds';// form表单参数-mainClass.mcNo
					idsInput.value = exportInfoArr;// form表单参数-mainClass.mcNo 从全局变量里面取值
					downForm.appendChild(idsInput);
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
 * 新增合同
 * 
 * @author 何斌
 * @date 2015-6-27
 * @update
 */
function ShowContractAddWin(){
	// 新增合同
	var win = Ext.widget("contractAddWin"); 
	ContractAddWin = win;
	var dcAccountField = win.down('form').getForm().findField('dcAccount');
	// 判断是否是从其它页面跳转过来
	if(parent.allChildrenParamsMap.get('dcAccount')){
		dcAccountField.setValue(parent.allChildrenParamsMap.get('dcAccount'));
		dcAccountField.setEditable(false);
		dcAccountField.setFieldStyle('background-color:#ececec;');
		//清空参数
		parent.allChildrenParamsMap.put('dcAccount', undefined)
	}
    win.show();
}
