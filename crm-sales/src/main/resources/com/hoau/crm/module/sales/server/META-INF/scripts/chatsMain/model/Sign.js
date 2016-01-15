/**
 * 未绑定的签到信息MODEL
 */
Ext.define('crm.model.Sign', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'customerId'},
        { name: 'signInfo'},
        { name: 'signAddress'}
    ]
});