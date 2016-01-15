/**
 * 联系人信息MODEL
 */
Ext.define('crm.model.History', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'contactName'},
        { name: 'mainGoodsName'},
        { name: 'reserveTheme'},
        { name: 'reserveStartTime'},
        { name: 'chatTheme'},
        { name: 'chatStartTime'},
        { name: 'signAddress'},
        { name: 'operateType'},
        { name: 'operateName'},
        { name: 'createTime',type : 'date', dateFormat:'time'},
        { name: 'empName'}
    ]
});