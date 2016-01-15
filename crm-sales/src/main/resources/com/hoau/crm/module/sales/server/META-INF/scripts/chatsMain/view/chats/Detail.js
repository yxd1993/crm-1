//全局变量
var isValid = 0;

Ext.define("crm.view.chats.Detail", {
	id : 'chatsDetailWinId',
	extend : "Ext.window.Window",
	alias : "widget.chatsDetailWin",
	title : "详情",
	width : 940,
	height : 350,
	modal : true,
	autoScroll: true,
	listeners:{
		beforeclose:function(){
			isValid = 0;
		}
	},
	items : [{
		xtype : "form",
		title: '洽谈基本信息',
        animCollapse: true,
        autoScroll: true,
		border : false,
		layout : "column",
		record : null,
		defaultType: 'textfield',
		fieldDefaults : {
			labelAlign : 'left',
			columnWidth : 0.329,
			labelWidth : 130,
		},
		defaults:{
			margin : '5 0 5 30 ',
    		readOnly : true
    	},
		items : [ {
			name : "enterpriseName",
			fieldLabel : '客户企业全称'
		}, {
			name : "chatStartTime",
			fieldLabel : "洽谈开始时间"
		}, {
			name : "chatEndTime",
			fieldLabel : "洽谈结束时间"
		}, {
			name : "contactName",
			fieldLabel : '联系人姓名'
		}, {
			name : "cellphone",
			fieldLabel : "联系电话"
		}, {
			name : "chatType",
			fieldLabel : "洽谈方式"
		}, {
			name : "detailedAddress",
			fieldLabel : "客户地址",
			columnWidth: .658
		}, {
			name : "reserveInfo",
			fieldLabel : "关联预约信息",
			columnWidth: .987
		}, {
			name : "signAddress",
			fieldLabel : "关联签到信息",
			columnWidth: .987
		},{
			xtype:'textfield',
			name : 'accompanyListName',
            fieldLabel: '签到扫描陪同人员',
            columnWidth: .658,
            hidden:true
		},{
			name : 'tierEmpName',
			fieldLabel: '门店经理陪同',
			hidden:true
		},{
			name : 'teamManagerEmpName',
			fieldLabel: '团队经理陪同',
			hidden:true
		},{
			name : 'saleManEmpName',
			fieldLabel: '客户经理陪同',
			hidden:true
		},{
			name : 'roadEmpName',
			fieldLabel: '路区经理陪同',
			hidden:true
		},{
			name : 'regionsEmpName',
			fieldLabel: '大区总经理陪同',
			hidden:true
		},{
			name : 'businessUnitEmpName',
			fieldLabel: '事业部总经理陪同',
			hidden:true
		},{
			name : 'odEmpName',
			fieldLabel: '营运副总陪同',
			hidden:true
		},{
			name : 'ceoEmpName',
			fieldLabel: '集团总裁陪同',
			hidden:true
		},{
			name : "chatTheme",
			fieldLabel : "洽谈主题",
			columnWidth: .987
		},{
			xtype:'textarea',
			name:"chatContents",
			fieldLabel : "洽谈内容",
			columnWidth: .987,
			height:20
		}]
	},{
		title: '关联签到信息',
		xtype:'form',
        animCollapse: true,
        autoScroll: true,
		border : false,
		layout : "column",
		items : [{
			xtype: 'fieldset',
			title: '基本信息',
			width:'35%',
			height : 125,
			margin:'0 30 0 35',
			items:[{
				xtype:'form',
				layout : "column",
				defaultType: 'textfield',
				defaults:{
					margin : '5 0 5 0 ',
		    		readOnly : true
		    	},
		    	fieldDefaults : {
					labelAlign : 'left',
					labelWidth : 120,
				},
				items: [{
					name: 'signEmpName',
					fieldLabel: '签到人'
	            },{ 
	            	name: 'signAddress',
					fieldLabel: '签到地址'
	            },{  
	            	name: 'signDatetime',
					fieldLabel: '签到时间'
	            }] 
			}]
    	},{
    		xtype: 'fieldset',
    		title: '签到图片',
    		width:'56.4%',
			items: [{
				id:'signImg',
				xtype:'panel',
				border:1,
				width:100,
	    		margin : '0 5 10 5 ',
	    		height : 95 // 图片高度
            }] 
    	}]
	}]
});

//图片加载失败
function error(img){
	Ext.MessageBox.alert('提示', '获取图片失败');
	isValid++;
}
/**
 * 鼠标移上预览图片
 * @param img
 * @author 丁勇
 * @date 2015年11月4日
 * @update
 */
function showPreview(img){
	if(isValid==0){
		//获取图片所在容器
		var preview = Ext.fly('preview');
		//图片地址获取,样式设置
		preview.dom.firstChild.src = img.src;
		preview.dom.firstChild.style.cssText = "height:23.5em;width:34em;border:1px solid black;";
		preview.setStyle({ 
			'z-index': '99999',
			display:'block'
	    })
	}
}
/**
 * 鼠标移出
 * @param img
 * @author 丁勇
 * @date 2015年11月4日
 * @update
 */
function hidePreview(img){  
	Ext.fly('preview').setStyle({ 
		display:'none'
    })
}

/**
 * 点击打开图片
 * @param img
 * @author 丁勇
 * @date 2015年11月4日
 * @update
 */
function showBigPic(img){  
    window.open(img.src,"image",'fullscreen=1,top=0,left=0,height=600,width=1000, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no');  
}