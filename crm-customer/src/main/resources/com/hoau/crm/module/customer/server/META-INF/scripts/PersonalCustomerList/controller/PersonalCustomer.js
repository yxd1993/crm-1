/**
 * 客户信息Controller
 */
Ext.define('crm.controller.PersonalCustomer', {
    extend: 'Ext.app.Controller',
    stores: ['PersonalCustomer','PersonalCustomerContact'],
    models: ['PersonalCustomer','PersonalCustomerContact'],
    views: ['Viewport', 'personalcustomer.List', 'personalcustomer.Search', 'personalcustomer.contactWin', 'personalcustomer.contactPanel'],
    init: function () {
        this.control({
            'personalCustomerSearch button[action=reset]': {
                click: this.resetPersonalCustomerSearchForm
            },
            'personalCustomerSearch button[action=select]': {
                click: this.reloadPersonalCustomerGridStore
            },
            'personalCustomerSearch button[action=turnCustomer]': {
                click: this.turnCustomer
            },
            'personalCustomerList': {
                itemdblclick: this.lookCustomerInfo
            }
        });
    },
    resetPersonalCustomerSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadPersonalCustomerGridStore : function(btn){
    	Ext.getCmp('personalCustomerViewId').getPersonalCustomerGrid().getPagingToolbar().moveFirst();
    },
    turnCustomer : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('personalCustomerViewId').getPersonalCustomerGrid().getSelectionModel().getSelection();
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要转为企业客户的客户信息');
			return;
		}else if(selectArr.length > 1){
			Ext.MessageBox.alert('提示','一次只能选择一个个人客户进行转为企业客户');
			return;
		}else{
			var isTurnCustomer = selectArr[0].get('isTurnCustomer');
			if(isTurnCustomer == 'Y'){
				Ext.MessageBox.alert('提示','该客户已转为企业客户');
				return;
			}
		}
    	// 新增潜在客户
    	window.parent.delTabpanel('101002');
    	var obhUserId = selectArr[0].get('sourceId');
    	var url = '/crm-web/customer/addCustomer.action?obhUserId=' + obhUserId;
    	window.parent.initTabpanel('101002', '新增客户', url, true);
    },
    lookCustomerInfo : function(btn){
    	var selectArr = Ext.getCmp('personalCustomerViewId').getPersonalCustomerGrid().getSelectionModel().getSelection();
    	var cId = selectArr[0].get('sourceId');
    	PersonalCustomer.sourceId = cId;
    	var win = Ext.widget("personalCustomerContactListWin");
    	win.on('beforeshow', function() {
    		win.down('personalCustomerContactPanel').getStore().reload();
    	});
        win.show();
    }
});
