/**
 * 意向客户Model
 */
Ext.define('crm.model.Intention', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'enterpriseName',mapping: 'customerEntity.enterpriseName'},//客户企业全称
        { name: 'contactName' ,mapping:'contactEntity.contactName'},//主联系人姓名
        { name: 'customerScore',mapping:'customerScore'},//客户信用评分
        { name: 'cellphone',mapping:'contactEntity.cellphone'},//手机
        { name: 'detailedAddress', mapping: 'customerEntity.detailedAddress'},//详细地址
        { name: 'deliveryAddress', mapping: 'customerEntity.deliveryAddress'},//上门取货地址
        { name: 'deliveryAddressPostalCode', mapping: 'customerEntity.deliveryAddressPostalCode'}//取货邮编地址
    ]
});