
Ext.define("crm.view.dailyMeeting.DetailsList", {
    extend: "Ext.grid.Panel",
    alias: 'widget.dailyMeetingDetailsList',
    store: "meetingAttach",
    region : 'south',
    width : '100%',
    height : 100,
    viewConfig:{
		enableTextSelection:true
	},
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		            { text : '序号',	width : 50,	xtype : 'rownumberer'},
		         	{ text: '附件名称', dataIndex: 'fileName', flex:0.5,},
		         	{ text: '附件上传地址', dataIndex: 'fileUrl', flex:0.5,},
		         	
				]
    },
});