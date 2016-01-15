/**
 * 未赴约信息MODEL
 */
Ext.define('crm.model.Reserve', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id',mapping:'reserveId'},
        { name: 'accountId'},
        { name: 'reserveInfo'}
    ]
});