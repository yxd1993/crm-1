/**
 * 用户反馈Controller
 */
Ext.define('crm.controller.FeedBackInfo', {
	extend : 'Ext.app.Controller',
	stores : [ 'FeedBackInfo' ],
	models : [ 'FeedBackInfo' ],
	views : [ 'Viewport', 'feedBackInfo.List', 'feedBackInfo.Search' ],
	init : function() {
		this.control({
			'feedBackInfoSearch button[action=reset]' : {
				click : this.resetFeedBackInfoSearchForm
			},
			'feedBackInfoSearch button[action=select]' : {
				click : this.reloadfeedBackInfoGridStore
			}

		});
	},
	resetFeedBackInfoSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadfeedBackInfoGridStore : function(btn) {
		Ext.getCmp('feedBackInfoMainViewId').getFeedBackInfoGrid()
				.getPagingToolbar().moveFirst();
	},

});
