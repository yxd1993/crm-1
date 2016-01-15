var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");
/**
 * 资源需求Controller
 */
Ext.define('crm.controller.dailyMeeting', {
	extend : 'Ext.app.Controller',
	stores : [ 'dailyMeeting','meetingAttach' ],
	models : [ 'dailyMeeting','meetingAttach' ],
//	views : [ 'Viewport', 'dailyMeeting.List', 'dailyMeeting.Search','dailyMeeting.rightPanel','dailyMeeting.DetailsList'],
	views : [ 'Viewport', 'dailyMeeting.List', 'dailyMeeting.Search','dailyMeeting.rightPanel'],
	init : function() {
		this.control({
			'dailyMeetingSearch button[action=reset]' : {
				click : this.dailyMeetingSearchForm
			},
			'dailyMeetingSearch button[action=select]' : {
				click : this.reloadDailyMeetingGridStore
			},
			'dailyMeetingDetailsList' : {
				itemclick : this.reloadDailyMeetingDetailsList
			},
			'dailyMeetingList' : {
				itemclick : this.reloadDailyMeetingListList
			},

		});
	},
	dailyMeetingSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadDailyMeetingGridStore : function(btn) {
		Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getEl().update(imgHtml);
	},
	
	reloadDailyMeetingDetailsList : function(btn) {
//		var selectResult=Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingDetailsList().getSelectionModel().getSelection();
//		var imgHeight = Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getHeight();
//		var imgWidth = Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getWidth();
//		var attList = selectResult[0].get("fileUrl");
//		var imgHtml = '<img src="'+ attachment_url + attList +'" height='+ imgHeight +' width='+ imgWidth +' onclick="showBigPic(this)" />';
//    	Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getEl().update(imgHtml);
    	
    	crm.requestJsonAjax('customerLatlngAction!getCustomerGroupCount.action', null, 
    			function(response){
    				points = response.customerGroupEntities;
    				heatmapOverlay.setDataSet({data:points});
    			}, 
    		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户分布热力图加载失败");});
	},
	reloadDailyMeetingListList : function(btn){
		var selectResult=Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingGrid().getSelectionModel().getSelection();
		if (selectResult != null && selectResult[0] != null && selectResult[0].get('meetingSignId') != null) {
//			var selectResult=Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingDetailsList().getSelectionModel().getSelection();
			var imgHeight = Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getHeight();
			var imgWidth = Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getWidth();
			var meetingSignId = selectResult[0].get("meetingSignId");
			var params = {};
			var imgHtml = '';
			params.meetingSignId = meetingSignId;
			crm.requestJsonAjax('dailyMeetingAction!queryMeetingAttachAction.action', params, 
	    			function(response){
	    				var attachmentEntities = response.attachmentEntities;
	    				if(attachmentEntities.length > 0){
	    					var imgNumm = attachmentEntities.length;
	    					for(var i=0; i<attachmentEntities.length; i++){
	    						imgHtml += '<img src="'+ attachment_url + attachmentEntities[i].fileUrl +'" height='+ imgHeight +' width='+ imgWidth/imgNumm +' onclick="showBigPic(this)" />';
	    					}
	    				}
	    				Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getEl().update(imgHtml);
	    			}, 
	    		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "图片加载失败！");});
		//	Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingDetailsList().getStore().reload();
		}else{
			//Ext.getCmp("dailyMeetingMainViewId").getDailyMeetingDetailsList().getStore().removeAll();
			Ext.getCmp('dailyMeetingMainViewId').getDailyMeetingrightPanel().getEl().update('');
		}
	},
    
});

function showBigPic(img){  
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
} 