/**
 * 会议签到信息GRID
 */
Ext.define("crm.view.meetingSign.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.meetingSignList',
    store: "MeetingSign",
    region : 'center',
    emptyText : '没有查询结果,换个查询条件试试.',
   // width : '100%',
    viewConfig:{
		enableTextSelection:true
	},
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		            { text: '签到类型', dataIndex:'sweepPeopType', width : 80, renderer:sweepPeopTypeRenderer},
		         	{ text: '扫描人姓名', dataIndex: 'sweepPeopName', width : 100},
		         	{ text: '扫描人岗位名称', dataIndex: 'sweepPeopJobName', width : 120,renderer : function(value, p, record) {
		         		  if (value==null)
		         		      value='';
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: '扫描人部门名称', dataIndex: 'sweepPeopDeptName', width : 120,renderer : function(value, p, record) {
		         		  if (value==null)
			         	      value='';
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: '被扫描人姓名',dataIndex: 'wasMeetingSignList', width : 120,renderer : function(value, p, record) {
		         		var values='';
		         		var list = record.get("wasMeetingSignList");
		         		if(list.length>0){
			         		for(var i=0;i<list.length;i++){
			         			if(list[i].wasSweepPeopName==null)continue;
			         			values+=list[i].wasSweepPeopName+'，';
			         		}
			         		values=values.substring(0,values.length-1);
		         		}
			        	  return '<div style="white-space:normal;">' +values+ '</div>';
			        }},
		         	{ text: '被扫描人岗位名称', dataIndex: 'wasMeetingSignList',width : 140,renderer : function(value, p, record) {
		         		var values='';
		         		var list = record.get("wasMeetingSignList");
		         		if(list.length>0){
			         		for(var i=0;i<list.length;i++){
			         			if(list[i].wasSweepPeopJobName==null)continue;
			         			values+=list[i].wasSweepPeopJobName+'，';
			         		}
			         		values=values.substring(0,values.length-1);
		         		}
			        	  return '<div style="white-space:normal;">' +values+ '</div>';
			        }},
		         	{ text: ' 被扫描人部门名称', dataIndex: 'wasMeetingSignList',width : 140,renderer : function(value, p, record) {
		         		var values='';
		         		var list = record.get("wasMeetingSignList");
		         		if(list.length>0){
			         		for(var i=0;i<list.length;i++){
			         			if(list[i].wasSweepPeopDeptName==null)continue;
			         			values+=list[i].wasSweepPeopDeptName+'，';
			         		}
			         		values=values.substring(0,values.length-1);
		         		}
			        	  return '<div style="white-space:normal;">' + values + '</div>';
			        }},
			        { text: '扫描签到地址', dataIndex: 'signAddress', width : 200,renderer : function(value, p, record) {
			        	 if (value==null)
			         		  value='';
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
			       /* { text: '附件描述', dataIndex: 'attachmentList', width : 140,renderer : function(value, p, record) {
			        	var values='';
			        	var list = record.get("attachmentList");
		         		if(list.length>0){
			         		for(var i=0;i<list.length;i++){
			         			values+=list[i].descrip+'，';
			         		}
			         		values=values.substring(0,values.length-1);
		         		}
			        	  return '<div style="white-space:normal;">' + values + '</div>';
			        }},*/
		         	{ text: '二维码生成时间', dataIndex: 'qrcodeTime', width : 150, xtype : 'datecolumn',renderer : function(value, p, record) {
		         		  value=Ext.util.Format.date(value, "Y-m-d H:i:s")
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: '客户扫描时间', dataIndex: 'sweepTime', width : 150, xtype : 'datecolumn',renderer : function(value, p, record) {
		         		  value=Ext.util.Format.date(value, "Y-m-d H:i:s")
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }}
			        
				]
 },
 pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
				store : this.store,
				dock: 'bottom',
		        displayInfo: true
			});
		}
		return this.pagingToolbar;
	},
 constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.bbar = me.getPagingToolbar();
		me.callParent([ cfg ]);
	}
});


//渲染
function sweepPeopTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "MEETING_SWEEP_SIGN_TYPE");
}

