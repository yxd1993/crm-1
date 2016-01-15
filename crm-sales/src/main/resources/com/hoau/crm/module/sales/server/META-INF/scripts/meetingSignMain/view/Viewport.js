Ext.define("crm.view.Viewport", {
	id : 'meetingSignMainViewId',
    extend: "Ext.container.Viewport",
    layout: "border",
    items: [{
        xtype:"meetingSignSearch"
    },{
        xtype:"meetingSignList"
    },{
    	xtype:"imgPanel"
    }],
    getMeetingSignSearchForm : function(){
    	return this.items.get(0);
    },
    getMeetingSignGrid : function(){
    	return this.items.get(1);
    },
    getMeetingSignImgGrid : function(){
    	return this.items.get(2);
    },
    tabChange : function(){
    	// 刷新数据
    	//this.getSweepSignGrid().getStore().reload();
    	if(parent.allChildrenParamsMap.get('dcAccount')){
    		if(MeetingSignWin){
        		// 关闭新增窗口
    			MeetingSignWin.close();
        		// 重新打开窗口
        		ShowMeetingSignWin();
        	} else {
        		// 重新打开窗口
        		ShowMeetingSignWin();
        	}
    	}
    }
}); 
