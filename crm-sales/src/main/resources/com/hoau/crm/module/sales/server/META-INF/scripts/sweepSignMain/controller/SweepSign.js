var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");

/**
 * 客户扫描签到Controller
 */
Ext.define('crm.controller.SweepSign', {
	extend : 'Ext.app.Controller',
	stores : [ 'SweepSign' ],
	models : [ 'SweepSign' ],
	views : [ 'Viewport', 'sweepSign.List', 'sweepSign.Search' ,'sweepSign.Detail','sweepSign.imgPanel'],
	init : function() {
		this.control({
			'sweepSignSearch button[action=reset]' : {
				click : this.resetSweepSignSearchForm
			},
			'sweepSignSearch button[action=select]' : {
				click : this.reloadSweepSignGridStore
			},
			'sweepSignList' : {
				itemclick : this.lookSweepSignInfo
			}

		});
	},
	resetSweepSignSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadSweepSignGridStore : function(btn) {
		Ext.getCmp('sweepSignMainViewId').getSweepSignGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('sweepSignMainViewId').getSweepSignImgGrid().getEl().update(imgHtml);
	},
	/*lookSweepSignInfo:function(btn){
		var selectResult=Ext.getCmp('sweepSignMainViewId').getSweepSignGrid().getSelectionModel().getSelection();
		var win = Ext.widget("sweepSignDetailWin"); 
    	var form = win.down("form").getForm();
    	
		form.findField('sweepPeopName').setValue(selectResult[0].get('sweepPeopName'));
		form.findField('sweepPeopJobName').setValue(selectResult[0].get('sweepPeopJobName'));
		form.findField('sweepPeopDeptName').setValue(selectResult[0].get('sweepPeopDeptName'));
		form.findField('wasSweepPeopName').setValue(selectResult[0].get('wasSweepPeopName'));
		form.findField('wasSweepPeopJobName').setValue(selectResult[0].get('wasSweepPeopJobName'));
		form.findField('wasSweepPeopDeptName').setValue(selectResult[0].get('wasSweepPeopDeptName'));
		form.findField('signAddress').setValue(selectResult[0].get('signAddress'));
    	form.findField('qrcodeTime').setValue(Ext.util.Format.date(new Date(selectResult[0].get('qrcodeTime')), 'Y-m-d H:i:s'));
    	form.findField('sweepTime').setValue(Ext.util.Format.date(new Date(selectResult[0].get('sweepTime')), 'Y-m-d H:i:s'));
    	var imgHtml = '<img src="'+ attachment_url + selectResult[0].get("imgUrl") +'" width=200, height=180 onclick="showBigPic(this)" />';
    	win.down("form").up('window').items.getAt(1).setHtml(imgHtml);
    	win.show();
	}*/
	lookSweepSignInfo:function(btn){
		iNum ++;
		var selectResult=Ext.getCmp('sweepSignMainViewId').getSweepSignGrid().getSelectionModel().getSelection();
		var imgHeight = Ext.getCmp('sweepSignMainViewId').getSweepSignImgGrid().getHeight();
		var imgWidth = Ext.getCmp('sweepSignMainViewId').getSweepSignImgGrid().getWidth();
		var attList = selectResult[0].get("attachmentList");
		var imgHtml = '';
		if(attList && attList.length > 0){
			var imgNumm = attList.length;
			for(var i=0; i<attList.length; i++){
				imgHtml += '<img src="'+ attachment_url + attList[i].fileUrl +'" height='+ imgHeight +' width='+ imgWidth/imgNumm +' onclick="showBigPic(this)" />';
			}
		}
    	Ext.getCmp('sweepSignMainViewId').getSweepSignImgGrid().getEl().update(imgHtml);
	}
});

var iNum = 1;