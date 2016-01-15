/**
 * 客户信息MODEL
 */
Ext.define('crm.model.resourcePool', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'enterpriseName'},//客户企业全称
        { name: 'accountType'},//客户性质
        { name: 'regions'},//大区名称
        { name: 'contactName'},//主联系人姓名
        { name: 'industryCode'},//所属行业
        { name: 'telephone'},//座机
        { name: 'cellphone'},//手机
        { name: 'address'},//详细地址
        { name: 'flowDate', type : 'date', dateFormat:'time'},//流入时间
        { name: 'createDate', type : 'date',dateFormat:'time'}//创建时间
    ]
});