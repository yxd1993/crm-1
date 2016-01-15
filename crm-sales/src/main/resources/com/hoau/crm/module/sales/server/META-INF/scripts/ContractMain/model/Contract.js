/**
 * 销售合同Model
 */
Ext.define('crm.model.Contract', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'status'},//合同状态
        { name: 'crmAccount'},//crm账号
        { name: 'dcAccount'},//dc账号
        { name: 'workflowCode'},//合同流水号
        { name: 'enterpriseName'},//客户企业全称
        { name: 'contractStartDate', type : 'date', dateFormat:'time'},//合同开始时间
        { name: 'contractEndDate', type : 'date', dateFormat:'time'},//合同结束时间
        { name: 'applyDate', type : 'date', dateFormat:'time'},//合同申请时间
        { name: 'fileDate', type : 'date', dateFormat:'time'},//合同归档时间
        { name: 'applyUserDeclareName'},//门店
        { name: 'road'},//路区
        { name: 'regions'},//大区
        { name: 'businessUnit'}//事业部
    ]
});