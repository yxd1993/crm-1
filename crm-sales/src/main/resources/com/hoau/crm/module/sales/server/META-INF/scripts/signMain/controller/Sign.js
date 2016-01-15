var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");
/**
 * 客户签到Controller
 */
Ext.define('crm.controller.Sign', {
	extend : 'Ext.app.Controller',
	stores : [ 'Sign' ],
	models : [ 'Sign' ],
	views : [ 'Viewport', 'sign.List', 'sign.Search' ,'sign.Detail', 'sign.imgPanel'],
	init : function() {
		this.control({
			'signSearch button[action=reset]' : {
				click : this.resetSignSearchForm
			},
			'signSearch button[action=select]' : {
				click : this.reloadSignGridStore
			},
			'signList' : {
				itemclick : this.lookSignInfo
			}

		});
	},
	resetSignSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadSignGridStore : function(btn) {
		Ext.getCmp('signMainViewId').getSignGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('signMainViewId').getSignImgGrid().getEl().update(imgHtml);
	},
	/*lookSignInfo:function(btn){
		var selectResult=Ext.getCmp('signMainViewId').getSignGrid().getSelectionModel().getSelection();
		var win = Ext.widget("signDetailWin"); 
    	var form = win.down("form").getForm();
    	
		form.findField('customerName').setValue(selectResult[0].get('customerName'));
		form.findField('signAddress').setValue(selectResult[0].get('signAddress'));
    	form.findField('longitude').setValue(selectResult[0].get('longitude'));
    	form.findField('latitude').setValue(selectResult[0].get('latitude'));
    	form.findField('createDate').setValue(Ext.util.Format.date(new Date(selectResult[0].get('createDate')), 'Y-m-d H:i:s'));
    	var imgHtml = '<img src="'+ attachment_url + selectResult[0].get("imgUrl") +'" width=200, height=180 onclick="showBigPic(this)" />';
    	win.down("form").up('window').items.getAt(1).setHtml(imgHtml);
    	win.show();
	}*/
	lookSignInfo:function(btn){
		iNum ++;
		var selectResult=Ext.getCmp('signMainViewId').getSignGrid().getSelectionModel().getSelection();
		var imgHeight = Ext.getCmp('signMainViewId').getSignImgGrid().getHeight();
		var imgWidth = Ext.getCmp('signMainViewId').getSignImgGrid().getWidth();
    	var imgHtml = '<img src="'+ attachment_url + selectResult[0].get("imgUrl") +'" height='+ imgHeight +' width='+ imgWidth +' onclick="showBigPic(this)" />';
    	Ext.getCmp('signMainViewId').getSignImgGrid().getEl().update(imgHtml);
	}
});

var iNum = 1;