/**
 * 客户信息MODEL
 */
Ext.define('crm.model.Customer', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'enterpriseName'},//客户企业全称
        { name: 'contactName', mapping: 'contactEntity.contactName'},//主联系人姓名
        { name: 'dcAccount'},//DC账号
        { name: 'accountCode'},//CRM账号
        { name: 'districtNumber', mapping: 'contactEntity.districtNumber'},//区号
        { name: 'telephone', mapping: 'contactEntity.telephone'},//座机
        { name: 'regionsName'},//大区名称
        { name: 'managePerson'},//负责人
        { name: 'cellphone', mapping: 'contactEntity.cellphone'},//手机
        { name: 'detailedAddress'},//详细地址
        { name: 'industryCode'},//所属行业
        { name: 'accountType'},//客户性质
        { name: 'createDate', type : 'date', dateFormat:'time'},//创建时间
        { name: 'manageEmpCode'}//客户负责人
    ]
});