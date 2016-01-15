/**
 * 客户信息MODEL
 */
Ext.define('crm.model.PersonalCustomer', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'sourceId'},
        { name: 'userName'},//用户名
        { name: 'enterpriseName'},//客户姓名
        { name: 'detailedAddress'},//地址
        { name: 'contactName'},//联系人
        { name: 'cellphone'},//手机
        { name: 'telephone'},//座机
        { name: 'email'},//邮箱
        { name: 'source'},//来源渠道
        { name: 'isTurnCustomer'},//客户状态
        { name: 'managePerson'}//负责人
    ]
});