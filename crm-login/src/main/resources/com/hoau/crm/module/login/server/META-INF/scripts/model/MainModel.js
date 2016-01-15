/**
 * 基础modelModel
 */
Ext.define('CRM.model.login.MenuModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'id'},//ID
        {name:'text'},
        {name:'leaf'},
        {name:'url'}
    ]
});