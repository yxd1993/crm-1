/**
 * 客户操作信息Model
 */
Ext.define('crm.model.UserOperation', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'userOperationName'},//客户姓名
             { name: 'userOperationType'},//客户操作类型
             { name: 'userOperationIp'},//客户IP
             { name: 'userOperationTime', type : 'date', dateFormat:'time'}//客户操作时间
             
         ]
     });