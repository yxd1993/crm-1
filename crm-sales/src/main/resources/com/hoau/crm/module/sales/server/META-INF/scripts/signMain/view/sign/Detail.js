Ext.define("crm.view.sign.Detail", {
	id : 'signDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.signDetailWin",
	title : "客户拜访签到详情",
	width : 700,
	height : 260,
	layout:'column',
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
			name : "customerName",
			fieldLabel : "客户姓名",
		},  {
			name : "signAddress",
			fieldLabel : "客户签到地址",
		},{
			name : "createDate",
			fieldLabel : "客户签到时间",
		},{
			name : 'longitude',
			fieldLabel: '经度',
		},{
			name : 'latitude',
			fieldLabel: '纬度',
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
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
} 