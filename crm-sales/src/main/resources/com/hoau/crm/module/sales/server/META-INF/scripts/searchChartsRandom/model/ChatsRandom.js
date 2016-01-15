/**
 *  洽谈回访随机抽取Model
 */
Ext.define('crm.model.ChatsRandom', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'customerName'},//客户企业全称
             { name: 'cellphone'},//手机
             { name: 'chatStartTime',type : 'date', dateFormat:'time'},//预约开始时间	
             { name: 'chatType'},//洽谈方式
             { name: 'chatContent'},//洽谈方式
             { name: 'status'},//回访状态
             { name: 'createDate',type : 'date', dateFormat:'time'}, //创建人
             { name: 'createUserName'}, //创建人
             { name: 'createUserDept'}, //部门
             { name: 'oneLevelUnit'}, //一级部门
             { name: 'twoLevelUnit'}, //二级部门
             { name: 'threeLevelUnit'}, //三级部门
         ]
});