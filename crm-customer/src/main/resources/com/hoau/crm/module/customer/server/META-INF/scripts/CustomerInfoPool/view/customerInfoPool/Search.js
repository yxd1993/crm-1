/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.customerInfoPool.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.customerPoolSearch',
    width : '100%',
    height : 110,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '5 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [{
        fieldLabel: '上传人部门',
        name: 'uploadDept',
        maxLength : 100
    },{
        fieldLabel: '公司名称',
        name: 'companyName',
        maxLength : 200
    },{
        fieldLabel: '联系人',
        name: 'contactPerson',
        maxLength : 100
    },{
        fieldLabel: '联系方式',
        name: 'contactWay',
        maxLength : 100
    },{
        fieldLabel: '所属大区',
        name: 'regions',
        maxLength : 100
    },{
        fieldLabel: '负责人',
        name: 'managePerson',
        maxLength : 100
    },{
        fieldLabel: '详细地址',
        name: 'companyAddress',
        maxLength : 400
    },{
        fieldLabel: '分发状态',
        name: 'dispenseStatus',
	    xtype: 'combo',
        store: getDataDictionary().getDataDictionaryStore('CUSTOMER_DISSTATE', null, null, null),
        queryMode: 'local',
        displayField: 'valueName',
        valueField: 'valueCode'
    },{
        fieldLabel: '创建时间',
        name: 'startDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'endDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    }],
    buttons: [{
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    },{
        text: '新增',
        action : 'add',
        hidden : isButtonHide('101005001')
    }, {
        text: '修改',
        action : 'update',
        hidden : isButtonHide('101005002')
    }, {
        text: '删除',
        action : 'delete',
        hidden : isButtonHide('101005003')
    }, {
        text: '批量导入',
        action : 'import',
        hidden : isButtonHide('101005004')
    }, {
        text: '批量导出',
        action : 'export',
        hidden : isButtonHide('101005005')
    }, {
        text: '转让',
        action : 'transfer',
        hidden : isButtonHide('101005009')
    }]
});
