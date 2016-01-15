/**
 * 权限信息MODEL
 */
Ext.define('crm.model.Auth', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'id'},
        {name:'text'},
        {name:'leaf'},
        {name:'checked'}
    ]
});