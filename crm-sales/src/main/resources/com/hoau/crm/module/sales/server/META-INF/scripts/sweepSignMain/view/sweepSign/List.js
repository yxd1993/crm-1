/**
 * 客户签到信息GRID
 */
Ext.define("crm.view.sweepSign.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.sweepSignList',
    store: "SweepSign",
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
		         	{ text: '扫描人姓名', dataIndex: 'sweepPeopName', width : 100},
		         	{ text: '扫描人岗位名称', dataIndex: 'sweepPeopJobName', width : 120,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: '扫描人部门名称', dataIndex: 'sweepPeopDeptName', width : 120,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: '被扫描人姓名', dataIndex: 'wasSweepPeopName', width : 120},
		         	{ text: ' 被扫描人岗位名称', dataIndex: 'wasSweepPeopJobName', width : 140,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
		         	{ text: ' 被扫描人部门名称', dataIndex: 'wasSweepPeopDeptName',width : 140,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
			        { text: '扫描签到地址', dataIndex: 'signAddress', width : 250,renderer : function(value, p, record) {
			        	  return '<div style="white-space:normal;">' + value + '</div>';
			        }},
			        { text: '神秘客户暗访得分', dataIndex: 'vantages', align : 'center', width : 140,renderer : function(value, p, record) {
			        	  if(Ext.isEmpty(value)){
			        		  return '';
			        	  } else {
			        		  return value;
			        	  }
			        }},
		         	//{ text: '图片路径', dataIndex: 'imgUrl', flex:0.4},
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

