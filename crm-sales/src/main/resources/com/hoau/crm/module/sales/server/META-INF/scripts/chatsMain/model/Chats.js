/**
 * 洽谈客户Model
 */
Ext.define('crm.model.Chats', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'chatStartTime',type : 'date', dateFormat:'time'},//预约开始时间	
             { name: 'chatType'},//洽谈方式
             { name: 'chatTheme'},//洽谈主题
             { name: 'enterpriseName',mapping:'customerEntity.enterpriseName'},//客户企业全称
             { name: 'contactName', mapping: 'contactEntity.contactName'},//主联系人姓名
             { name: 'cellphone', mapping: 'contactEntity.cellphone'},//手机
             { name: 'telephone', mapping: 'contactEntity.telephone'},//住宅
             { name: 'reserveAddress'},//实际拜访地址
             { name: 'empName', mapping: 'employeeEntity.empName'}, //创建人
             { name: 'createDate',type : 'date', dateFormat:'time'},//创建时间
             { name: 'manageEmpCode', mapping: 'customerEntity.manageEmpCode'} //负责人
         ]
});