/**
 * 客户操作信息Controller
 */
Ext.define('crm.controller.UserOperation', {
	extend : 'Ext.app.Controller',
	stores : [ 'UserOperation' ],
	models : [ 'UserOperation' ],
	views : [ 'Viewport', 'userOperation.List', 'userOperation.Search' ],
	init : function() {
		this.control({
			'userOperationSearch button[action=reset]' : {
				click : this.resetUserOperationSearchForm
			},
			'userOperationSearch button[action=select]' : {
				click : this.reloadUserOperationGridStore
			}

		});
	},
	resetUserOperationSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadUserOperationGridStore : function(btn) {
		Ext.getCmp('userOperationMainViewId').getUserOperationGrid()
				.getPagingToolbar().moveFirst();
	},

});