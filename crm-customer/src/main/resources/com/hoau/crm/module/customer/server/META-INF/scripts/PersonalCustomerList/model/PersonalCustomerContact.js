/**
 * 客户联系人信息MODEL
 */
Ext.define('crm.model.PersonalCustomerContact', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'contactName'},//联系人
        { name: 'cellphone'},//手机
        { name: 'telephone'},//座机
        { name: 'districtNumber'},//区号
        { name: 'tierCode'}//发货门店
    ]
});