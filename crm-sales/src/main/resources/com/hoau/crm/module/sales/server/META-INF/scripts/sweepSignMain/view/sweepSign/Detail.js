Ext.define("crm.view.sweepSign.Detail", {
	extend : "Ext.window.Window",
	alias : "widget.sweepSignDetailWin",
	title : "客户扫描签到详情",
	width : 700,
	height : 390,
	layout : "column",
	modal : true,
	items : [{
		xtype : "form",
		margin : 5,
		border : false,
		columnWidth : 0.7,
		layout : "column",
		autoScroll: true,  
		record : null,
		defaultType: 'textfield',
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.99,
			labelWidth : 130,
		},
		defaults:{
			margin : '5 0 10 30 ',
    		readOnly : true
    	},
		items : [{
			name : "sweepPeopName",
			fieldLabel : "扫描人姓名"
		},{
			name : "sweepPeopJobName",
			fieldLabel : "扫描人岗位名称"
		},{
			name : "sweepPeopDeptName",
			fieldLabel : "扫描人部门名称"
		},{
			name : "wasSweepPeopName",
			fieldLabel : "被扫描人姓名"
		},{
			name : "wasSweepPeopJobName",
			fieldLabel : "被扫描人岗位名称"
		},{
			name : "wasSweepPeopDeptName",
			fieldLabel : "被扫描人部门名称"
		},{
			name : "signAddress",
			fieldLabel : "扫描签到地址"
		},{
			name : "qrcodeTime",
			fieldLabel : "二维码生成时间"
		},{
			name : "sweepTime",
			fieldLabel : "客户扫描时间"
		}
		]
	},{
		xtype:'panel', 
		margin : '10 10 0 0 ',
		columnWidth : 0.3,
		height : 190, // 图片高度
		html : ''
	}]
});

/**
 * 显示图片
 * 
 * @param img
 * @author 蒋落琛
 * @date 2015-7-29
 * @update
 */
function showBigPic(img){  
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=900, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
} 