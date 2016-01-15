/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.lookCustomer.LookCustomForm", {
    extend: "Ext.form.Panel",
    alias: 'widget.lookCustomForm',
    width : '100%',
    layout: 'fit',
    region : 'north',
	items:[{
		xtype:'tabpanel',
		layout : 'fit',
		height:200,
		activeTab:0,
		items:[{
			title:"基本信息",
			xtype: 'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        height : 280,
	        collapsible: true,
	        collapsed: false, 
	        autoScroll : true,
	        layout: 'column',
	        defaultType: 'textfield',
	        defaults:{
	    		msgTarget : 'qtip',
	    		margin : '5 0 5 30 ',
	    		labelWidth : 100,
	    		columnWidth : 0.33,
	    		labelAlign: 'left',
	    		readOnly : true
	    	},
	    	items : [{
	            xtype: 'hiddenfield',
	            name: 'cId'
	        },{
	            xtype: 'hiddenfield',
	            name: 'isCreateObhAccount'
	        },{
	    		fieldLabel: 'CRM账号',
	            name: 'accountCode'
	    	},{
	    		fieldLabel: 'DC账号',
	            name: 'dcAccount'
	    	},{
	    		fieldLabel: '负责人',
	            name: 'managePerson'
	    	},{
	    		fieldLabel: '事业部',
	            name: 'businessUnitCode'
	    	},{
	    		fieldLabel: '经营大区',
	            name: 'regionsCode'
	    	},{
	    		fieldLabel: '路区',
	            name: 'roadAreaCode'
	    	},{
	    		fieldLabel: '门店代码',
	            name: 'tierCode'
	    	},{
	    		fieldLabel: '改签门店',
	            name: 'signTierCode'
	    	},{
	    		fieldLabel: '客户级别',
	            name: 'accountRatingCode'
	    	},{
	    		fieldLabel: '客户性质',
	            name: 'accountType'
	    	},{
	    		fieldLabel: '状态描述',
	            name: 'statusCode'
	    	},{
	    		fieldLabel: '客户信用评分',
	            name: 'accountCreditGrade'
	    	},{
	    		fieldLabel: '客户渠道',
	            name: 'accountChannel'
	    	},{
	    		fieldLabel: '所属行业',
	            name: 'industryCode'
	    	},{
	    		fieldLabel: '客户企业全称',
	            name: 'enterpriseName'
	    	},
	    	{
	    		fieldLabel: '企业性质',
	            name: 'enterpriseType'
	    	},{
	    		fieldLabel: '客户分类',
	            name: 'accountSub'
	    	},{
	    		fieldLabel: '所属总公司',
	            name: 'parentCompany'
	    	},{
	    		fieldLabel: '企业发票抬头',
	            name: 'enterpriseBillHead'
	    	},{
	    		fieldLabel: '办公地址',
	    		columnWidth : 0.66,
	            name: 'detailedAddress'
	    	},{
	    		fieldLabel: '办公地址邮编',
	            name: 'detailedAddressPostalCode'
	    	},{
	    		fieldLabel: '取货详细地址',
	    		columnWidth : 0.66,
	            name: 'deliveryAddress'
	    	},{
	    		fieldLabel: '取货地址邮编',
	            name: 'deliveryAddressPostalCode'
	    	},{
	    		fieldLabel: '主要货物名称',
	            name: 'mainGoodsName'
	    	},{
	    		fieldLabel: '主要包装方式',
	            name: 'typeOfPackage'
	    	},{
	    		fieldLabel: '第1单时间',
	            name: 'startingTime'
	    	},{
	    		fieldLabel: '当前折扣率',
	            name: 'discountRate'
	    	},{
	    		fieldLabel: '客户账期',
	            name: 'accountPeriod'
	    	},{
	    		fieldLabel: '网厅账号',
	            name: 'obhAccount'
	    	},{
	    		fieldLabel: '期望营销类型',
	            name: 'marketActiveType'
	    	},{
	    		xtype : 'textareafield',
	    		fieldLabel: '营销活动详述',
	            name: 'marketActiveRemark',
	            columnWidth : 0.99
	    	},{
	    		xtype : 'textareafield',
	    		fieldLabel: '备注',
	            name: 'accountRemark',
	            columnWidth : 0.99
	    	}]
		},{
			title:"发货信息",
			xtype: 'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        collapsible: true,
	        collapsed: false,
	        layout: 'column',
	        height : 420,
	        defaultType: 'textfield',
	        autoScroll : true,
	        defaults:{
	    		msgTarget : 'qtip',
	    		margin : '5 0 5 30 ',
	    		labelWidth : 100,
	    		columnWidth : 0.33,
	    		labelAlign: 'left',
	    		readOnly : true
	    	},
	    	items : [{
	    		xtype : 'textareafield',
	    		fieldLabel: '发单日志',
	            name: 'waybillLog',
	            height : 420,
	            columnWidth : 0.99
	    	}]
		},{
			title:"联系人信息",
			xtype: 'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        collapsible: true,
	        collapsed: false,
	        layout: 'column',
	        style : 'margin-bottom:20px;',
	        //height : 200,
	        hidden : Ext.isEmpty(cId),
	        defaultType: 'textfield',
	    	items : [{
	    		columnWidth : 0.99,
	    		//height : 145,
	    		xtype : 'contactList'
	    	}]
		},{
			title:"历史跟进",
			xtype:'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        collapsible: true,
	        collapsed: false,
			layout:'fit',
	        //height : 200,
	        hidden : Ext.isEmpty(cId),
	    	items : [{
	    		columnWidth : 0.99,
	    		//height : 145,
	    		xtype : 'historyList'
	    	}]
//			items:[{
//				xtype:'treepanel',
//				border:false,
//				region: 'west',
//				width:'40%',
//			    split:true,//可以合并
//				animate:true,//动画效果
//			    useArrows: true, //使用vista风格的箭头图标，默认为false
//		        autoScroll : true,
//		        root: {
//		            text: '某某某客户',
//		            expanded: true,
//		            children: [
//		                {
//		                    text: '节点1',
//		                    leaf: true
//		                },
//		                {
//		                    text: '节点2',
//		                    leaf: true
//		                },
//		                {
//		                    text: '节点3',
//		                    expanded: true,
//		                    children: [
//		                        {
//		                            text: '孙子节点',
//		                            leaf: true
//		                        }
//		                    ]
//		                }
//		            ]
//		        }
//			},{
//				xtype:'panel',
//				width:'60%',
//				region: 'center'
//			}]
		}]
	}],
	buttons: [{
		text: '新增预约',
	    action : 'addReserve',
	    hidden : isButtonHide('103001')
	}, {
		text: '新增洽谈',
	    action : 'addChats',
	    hidden : isButtonHide('103002')
	}, {
		text: '转为意向',
	    action : 'turnToIntention'
    }, {
        text: '新增合同',
        action : 'addContract',
        hidden : isButtonHide('103004')
    }, {
        text: '账号注册',
        action : 'register',
        hidden : isButtonHide('101001018')
    }],
	listeners : {
		'beforeclose' : function(){
			intentionWin = undefined;
		}
	}
});
