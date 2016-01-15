/**
 * 预约客户Model
 */
Ext.define('crm.model.Reserve', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'isAgreement'},
        { name: 'reserveStartTime',type : 'date', dateFormat:'time'},//预约开始时间	
        { name: 'reserveType'},//预约方式
        { name: 'reserveTheme'},//预约主题
        { name: 'enterpriseName',mapping:'customerEntity.enterpriseName'},//客户企业全称
        { name: 'contactName', mapping: 'contactEntity.contactName'},//主联系人姓名
        { name: 'cellphone', mapping: 'contactEntity.cellphone'},//手机
        { name: 'telephone', mapping: 'contactEntity.telephone'},//住宅
        { name: 'detailedAddress', mapping: 'customerEntity.detailedAddress'},//详细地址
        { name: 'empName', mapping: 'employeeEntity.empName'}, //创建人
        { name: 'manageEmpCode', mapping: 'customerEntity.manageEmpCode'} //负责人
    ]
});