/**
 * 合同信息查询FORM
 */
Ext.define("crm.view.lookContract.LookContractForm", {
    extend: "Ext.form.Panel",
    alias: 'widget.lookContractForm',
    width : '100%',
    autoScroll : true,
    layout: 'column',
    region : 'north',
    items: [{
    	id :'uploadForm',
		xtype: 'form',
		columnWidth : 1,
        title: '上传合同信息',
        animCollapse: true,
        bodyPadding: 5,
        height : 120,
        collapsible: true,
        collapsed: false,
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
        items:[{
        	fieldLabel: '文件名',
            name: 'attachmentName'
        },{
        	fieldLabel: '上传人',
            name: 'createUser'
        },{
        	fieldLabel: '上传时间',
            name: 'createDate'
        }],
        buttons:[{
        	text:'下载',
        	action: 'export'
        }]
    },{
    	id :'approvalForm',
		xtype: 'form',
		columnWidth : 1,
        title: '审批状态基本信息',
        animCollapse: true,
        bodyPadding: 5,
        height : 125,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        defaultType: 'textfield',
        defaults:{
    		msgTarget : 'qtip',
    		margin : '5 0 5 30 ',
    		labelWidth : 130,
    		columnWidth : 0.33,
    		labelAlign: 'left',
    		readOnly : true
    	},
    	items : [{
    		fieldLabel: '合同流水号',
            name: 'contractId'
    	},{
    		fieldLabel: '当前审批部门',
            name: 'approvalDept'
    	},{
    		fieldLabel: '当前审批岗位',
            name: 'approvalJob'
    	},{
    		fieldLabel: '当前审批人工号',
            name: 'approvalEmpCode'
    	},{
    		fieldLabel: '当前审批人姓名',
            name: 'approvalEmpName'
    	},{
    		fieldLabel: '当前审批人手机号',
            name: 'approvalTelephone'
    	}]
    },{
    	id :'contractBaseInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '合同基本信息',
        animCollapse: true,
        bodyPadding: 5,
        height : 120,
        collapsible: true,
        collapsed: false,
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
    		fieldLabel: '标题',
            name: 'title'
    	},{
    		fieldLabel: '工作流说明',
            name: 'workflowExplain'
    	},{
    		fieldLabel: '紧急程度',
            name: 'emergencyDegree'
    	},{
    		fieldLabel: '流水号ID',
            name: 'workflowCode'
    	},{
    		fieldLabel: '申请日期',
            name: 'applyDate',
            xtype : 'datetimefield',
    		format : 'Y-m-d'
    	}]
	},{
    	id :'contractSubmitInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '合同提交信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 230,
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
    		fieldLabel: '申请人',
            name: 'applyUser'
    	},{
    		fieldLabel: '申请人工号',
            name: 'applyUserEmpCode'
    	},{
    		fieldLabel: '申请人岗位',
            name: 'applyUserJobName'
    	},{
    		fieldLabel: '申请人部门',
            name: 'applyUserDeptName'
    	},{
    		fieldLabel: '申请门店名称',
            name: 'applyUserDeclareName'
    	},{
    		fieldLabel: '物流代码',
            name: 'applyUserLogisticCode'
    	},{
    		fieldLabel: '所属路区',
            name: 'roadAreaCode'
    	},{
    		fieldLabel: '所属大区',
            name: 'regionsCode'
    	},{
    		fieldLabel: '所属事业部',
            name: 'businessUnitCode'
    	},{
    		fieldLabel: '所属大区财务',
            name: 'financeRegionsCode'
    	},{
    		fieldLabel: '合同管理员',
            name: 'contractAdmin'
    	},{
    		fieldLabel: '是否客户经理级别',
            name: 'ifSaleManager'
    	},{
    		fieldLabel: '流程类型',
            name: 'processType'
    	},{
    		fieldLabel: '合同版本',
            name: 'contractVersion'
    	},{
    		fieldLabel: '签约属性',
            name: 'signAttribute'
    	}]
	},{
    	id :'customerBaseInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '客户基本信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 140,
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
    		fieldLabel: 'DC账号',
            name: 'dcAccount'
    	},{
    		fieldLabel: '客户全称',
            name: 'enterpriseName'
    	},{
    		fieldLabel: '客户性质',
            name: 'customerNature'
    	},{
    		fieldLabel: '客户所属行业类型',
            name: 'industryCode'
    	},{
    		fieldLabel: '货物名称',
            name: 'mainGoodsName'
    	},{
    		fieldLabel: '包装',
            name: 'typeOfPackage'
    	}]
	},{
    	id :'priceBaseInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '价格基本信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 230,
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
    		fieldLabel: '总产值(元)',
            name: 'ddMonthCount'
    	},{
    		fieldLabel: 'DD折扣',
            name: 'ddDiscount'
    	},{
    		fieldLabel: 'DU折扣',
            name: 'duDiscount'
    	},{
    		fieldLabel: '最低保价费(元)',
            name: 'lowestValuationFee'
    	},{
    		fieldLabel: '最低保价费率',
            name: 'insuranceRate'
    	},{
    		fieldLabel: '大件货服务约定',
            name: 'cargoServiceAgreement'
    	},{
    		fieldLabel: '最低提货费(元)',
            name: 'lowestDeliveryFee'
    	},{
    		fieldLabel: '最低送货费(元)',
            name: 'lowestShippingFee'
    	},{
    		fieldLabel: '提送货费约定',
            name: 'agreedDelivery'
    	},{
    		fieldLabel: '工本费(元)',
            name: 'expense'
    	},{
    		fieldLabel: '信息费(元)',
            name: 'informationCost'
    	},{
    		fieldLabel: '最低燃油费(元)',
            name: 'lowestFuel'
    	},{
    		fieldLabel: '其他服务费约定',
            name: 'serviceAgreement'
    	},{
    		fieldLabel: '合同开始日期',
            name: 'contractStartDate',
            xtype : 'datetimefield',
    		format : 'Y-m-d'
    	},{
    		fieldLabel: '合同结束日期',
            name: 'contractEndDate',
            xtype : 'datetimefield',
    		format : 'Y-m-d'
    	}]
	},{
    	id :'finInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '财务信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 175,
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
    		fieldLabel: '是否代收货款',
            name: 'ifCod'
    	},{
    		fieldLabel: '最低代收费',
            name: 'collectionChargesMin'
    	},{
    		fieldLabel: '最低代收费率',
            name: 'chargeRateMin'
    	},{
    		fieldLabel: '结算方式',
            name: 'paymentMethod'
    	},{
    		fieldLabel: '账期描述(*日)',
            name: 'accountDescribe'
    	},{
    		fieldLabel: '客户提供合同版本',
            name: 'provideContractVersion'
    	},{
    		fieldLabel: '是否有非标合同条款',
            name: 'ifHaveNonstandardContractTerm'
    	},{
    		fieldLabel: '是否有非标运营条款',
            name: 'ifHaveNonstandardOperatTerm'
    	},{
    		fieldLabel: '是否开通网上下单',
            name: 'ifOpenOnlineOrder'
    	}]
	},{
    	id :'renewAccountForm',
		xtype: 'form',
		columnWidth : 1,
        title: '续约账号信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 220,
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
    		fieldLabel: '原DD折扣',
            name: 'oldDdDiscount'
    	},{
    		fieldLabel: '原DU折扣',
            name: 'oldDuDiscount'
    	},{
    		fieldLabel: '原保价率',
            name: 'oldInsuranceRate'
    	},{
    		fieldLabel: '原最低保价费(元)',
            name: 'oldLowestValuationFee'
    	},{
    		fieldLabel: '原最低提货费(元)',
            name: 'oldLowestDeliveryFee'
    	},{
    		fieldLabel: '原最低送货费(元)',
            name: 'oldLowestShippingFee'
    	},{
    		fieldLabel: '原工本费(元)',
            name: 'oldExpense'
    	},{
    		fieldLabel: '原信息费(元)',
            name: 'oldInformationCost'
    	},{
    		fieldLabel: '原最低燃油费(元)',
            name: 'oldLowestFuel'
    	},{
    		fieldLabel: '原代收货款费率',
            name: 'oldChargeRateMin'
    	}]
	},{
    	id :'attachmentInfoForm',
		xtype: 'form',
		columnWidth : 1,
        title: '附件信息',
        animCollapse: true,
        bodyPadding: 5,
        collapsible: true,
        collapsed: false,
        layout: 'column',
        height : 120,
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
    		xtype : 'textareafield',
    		fieldLabel: '申请事由说明',
            name: 'applyInstruction',
            columnWidth : 0.99
    	}]
	}]
});
