/**
 * 客户信息MODEL
 */
Ext.define('crm.model.CustomerInfoPool', {
    extend: 'Ext.data.Model',
    fields: [
        { name: 'id'},
        { name: 'companyName'},
        { name: 'contactPerson'},
        { name: 'contactWay'},
        { name: 'business'},
        { name: 'regions'},
        { name: 'regionsCode'},
        { name: 'email'},
        { name: 'province'},
        { name: 'provinceCode'},
        { name: 'city'},
        { name: 'cityCode'},
        { name: 'area'},
        { name: 'areaCode'},
        { name: 'companyAddress'},
        { name: 'createDate', type : 'date', dateFormat:'time'},
        { name: 'dispenseStatus'},
        { name: 'managePerson'},
        { name: 'uploadDept', mapping:'employeeEntity.deptname'}
    ]
});