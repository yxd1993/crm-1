var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");

/**
 * 会议签到Controller
 */
Ext.define('crm.controller.MeetingSign', {
	extend : 'Ext.app.Controller',
	stores : [ 'MeetingSign' ],
	models : [ 'MeetingSign' ],
	views : [ 'Viewport', 'meetingSign.List', 'meetingSign.Search' ,'meetingSign.Detail','meetingSign.imgPanel'],
	init : function() {
		this.control({
			'meetingSignSearch button[action=reset]' : {
				click : this.resetMeetingSignSearchForm
			},
			'meetingSignSearch button[action=select]' : {
				click : this.reloadMeetingSignGridStore
			},
			'meetingSignList' : {
				itemclick : this.lookMeetingSignInfo
			}

		});
	},
	resetMeetingSignSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadMeetingSignGridStore : function(btn) {
		Ext.getCmp('meetingSignMainViewId').getMeetingSignGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('meetingSignMainViewId').getMeetingSignImgGrid().getEl().update(imgHtml);
	},
	lookMeetingSignInfo:function(btn){
		iNum ++;
		var selectResult=Ext.getCmp('meetingSignMainViewId').getMeetingSignGrid().getSelectionModel().getSelection();
		var imgHeight = Ext.getCmp('meetingSignMainViewId').getMeetingSignImgGrid().getHeight();
		var imgWidth = Ext.getCmp('meetingSignMainViewId').getMeetingSignImgGrid().getWidth();
		var attList = selectResult[0].get("attachmentList");
		var imgHtml = '';
		if(attList && attList.length > 0){
			var imgNumm = attList.length;
			for(var i=0; i<attList.length; i++){
				imgHtml += '<img src="'+ attachment_url + attList[i].fileUrl +'" height='+ imgHeight +' width='+ imgWidth/imgNumm +' onclick="showBigPic(this)" />';
			}
		}
		Ext.getCmp('meetingSignMainViewId').getMeetingSignImgGrid().getEl().update(imgHtml);
	}
});

var iNum = 1;