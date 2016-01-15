/**
 * 联系人信息MODEL
 */
Ext.define('crm.model.Contact', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'accountId'},
        { name: 'contactName'},
        { name: 'districtNumber'},
        { name: 'telephone'},
        { name: 'eMailAddress'},
        { name: 'cellphone'},
        { name: 'job'},
        { name : 'isDefault'},
        { name : 'modifyDate', type : 'date', dateFormat:'time'}
    ]
});