/**
 * 客户信息MODEL
 */
Ext.define('crm.model.Message', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'messageType'},
        { name: 'messageContent'},
        { name: 'createDate', type : 'date', dateFormat:'time'}//创建时间
    ]
});