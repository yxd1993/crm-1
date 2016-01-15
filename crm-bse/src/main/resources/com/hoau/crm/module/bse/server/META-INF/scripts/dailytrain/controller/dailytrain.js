var attachment_url = getDataDictionary().rendererSubmitToDisplay("URL", "ATTACHMENT_URL");
/**
 * 资源需求Controller
 */
Ext.define('crm.controller.dailytrain', {
	extend : 'Ext.app.Controller',
	stores : [ 'dailytrain','meetingAttach' ],
	models : [ 'dailytrain','meetingAttach' ],
//	views : [ 'Viewport', 'dailytrain.List', 'dailytrain.Search','dailytrain.rightPanel','dailytrain.DetailsList'],
	views : [ 'Viewport', 'dailytrain.List', 'dailytrain.Search','dailytrain.rightPanel'],
	init : function() {
		this.control({
			'dailytrainSearch button[action=reset]' : {
				click : this.dailytrainSearchForm
			},
			'dailytrainSearch button[action=select]' : {
				click : this.reloadDailytrainGridStore
			},
			'dailytrainDetailsList' : {
				itemclick : this.reloadDailytrainDetailsList
			},
			'dailytrainList' : {
				itemclick : this.reloadDailytrainListList
			},

		});
	},
	dailytrainSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadDailytrainGridStore : function(btn) {
		Ext.getCmp('dailytrainMainViewId').getDailytrainGrid()
				.getPagingToolbar().moveFirst();
		var imgHtml='<img src="" background:"#FFFFFF">';
		Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getEl().update(imgHtml);
	},
	reloadDailytrainDetailsList : function(btn) {
		var selectResult=Ext.getCmp('dailytrainMainViewId').getDailytrainDetailsList().getSelectionModel().getSelection();
		var imgHeight = Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getHeight();
		var imgWidth = Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getWidth();
		var attList = selectResult[0].get("fileUrl");
		var imgHtml = '<img src="'+ attachment_url + attList +'" height='+ imgHeight +' width='+ imgWidth +' onclick="showBigPic(this)" />';
    	Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getEl().update(imgHtml);
	},
	reloadDailytrainListList : function(btn){
		var selectResult=Ext.getCmp('dailytrainMainViewId').getDailytrainGrid().getSelectionModel().getSelection();
		if (selectResult != null && selectResult[0] != null && selectResult[0].get('meetingSignId') != null) {
			var imgHeight = Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getHeight();
			var imgWidth = Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getWidth();
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
	    				Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getEl().update(imgHtml);
	    			}, 
	    		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "图片加载失败！");});
		}else{
//			Ext.getCmp("dailytrainMainViewId").getDailytrainDetailsList().getStore().removeAll();
			Ext.getCmp('dailytrainMainViewId').getDailytrainrightPanel().getEl().update('');
		}
	},
	
});

function showBigPic(img){  
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
} 