Ext.define('crm.model.Authorization', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},//授权信息的id
             { name: 'authorizedPerson'},//授权人工号
             { name: 'wasAuthorizedPerson'},//被授权人工号
             { name: 'createUser'},//创建人
             { name: 'authorizedStartTime',type : 'date', dateFormat:'time'},//授权开始时间
             { name: 'authorizedEndTime',type : 'date', dateFormat:'time'},//授权结束时间
         ]
     });