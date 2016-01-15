var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");
/**
 * 资源需求Controller
 */
Ext.define('crm.controller.dailyProblem', {
	extend : 'Ext.app.Controller',
	stores : [ 'dailyProblem','meetingAttach' ],
	models : [ 'dailyProblem','meetingAttach' ],
//	views : [ 'Viewport', 'dailyProblem.List', 'dailyProblem.Search','dailyProblem.rightPanel','dailyProblem.DetailsList'],
	views : [ 'Viewport', 'dailyProblem.List', 'dailyProblem.Search','dailyProblem.rightPanel'],
	init : function() {
		this.control({
			'dailyProblemSearch button[action=reset]' : {
				click : this.dailyProblemSearchForm
			},
			'dailyProblemSearch button[action=select]' : {
				click : this.reloadDailyProblemGridStore
			},
			'dailyProblemDetailsList' : {
				itemclick : this.reloadDailyProblemDetailsList
			},
			'dailyProblemList' : {
				itemclick : this.reloadDailyProblemListList
			},
			
		});
	},
	dailyProblemSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadDailyProblemGridStore : function(btn) {
		Ext.getCmp('dailyProblemMainViewId').getDailyProblemGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getEl().update(imgHtml);
	},
	
	reloadDailyProblemDetailsList : function(btn) {
		var selectResult=Ext.getCmp('dailyProblemMainViewId').getDailyProblemDetailsList().getSelectionModel().getSelection();
		var imgHeight = Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getHeight();
		var imgWidth = Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getWidth();
		var attList = selectResult[0].get("fileUrl");
		var imgHtml = '<img src="'+ attachment_url + attList +'" height='+ imgHeight +' width='+ imgWidth +' onclick="showBigPic(this)" />';
    	Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getEl().update(imgHtml);
	},
	reloadDailyProblemListList : function(btn){
		var selectResult=Ext.getCmp('dailyProblemMainViewId').getDailyProblemGrid().getSelectionModel().getSelection();
		if (selectResult != null && selectResult[0] != null && selectResult[0].get('meetingSignId') != null) {
			var imgHeight = Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getHeight();
			var imgWidth = Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getWidth();
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
	    				Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getEl().update(imgHtml);
	    			}, 
	    		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "图片加载失败！");});
//			Ext.getCmp('dailyProblemMainViewId').getDailyProblemDetailsList().getStore().reload();
		}else{
//			Ext.getCmp("dailyProblemMainViewId").getDailyProblemDetailsList().getStore().removeAll();
			Ext.getCmp('dailyProblemMainViewId').getDailyProblemrightPanel().getEl().update('');
		}
	},
    
});

function showBigPic(img){  
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
} 