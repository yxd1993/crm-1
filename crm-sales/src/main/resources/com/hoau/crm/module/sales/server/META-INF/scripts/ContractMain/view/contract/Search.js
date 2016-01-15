/**
 * 合同信息查询FORM
 */
Ext.define("crm.view.contract.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.contractSearch',
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
        fieldLabel: '合同状态',
        name: 'status',
        xtype: 'combo',
        store: getDataDictionary().getDataDictionaryStore('CONTRACT_STATUS', null, null, null),
        queryMode: 'local',
        displayField: 'valueName',
        valueField: 'valueCode',
        editable : false
    },{
        fieldLabel: '客户企业全称',
        name: 'enterpriseName'
    },{
        fieldLabel: '合同流水号',
        name: 'workflowCode'
    },{
        fieldLabel: 'CRM账号',
        name: 'crmAccount'
    },{
        fieldLabel: 'DC账号',
        name: 'dcAccount'
    },{
        fieldLabel: '合同开始时间',
        name: 'firstStartDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'firstEndDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '合同结束时间',
        name: 'secondStartDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    },{
        fieldLabel: '至',
        name: 'secondEndDate',
        xtype : 'datefield',
		format : 'Y-m-d'
    }],
    buttons: [ {
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }
/*    , {
        text: '新增',
        action : 'add',
    }, {
        text: '修改',
        action : 'update',
    }
    , {
        text: '删除',
        action : 'delete',
    }
    , {
        text: '批量附件下载',
        action : 'export',
    }*/
    ]
});
