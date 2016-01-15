/**
 * 资源需求Model
 */
Ext.define('crm.model.resDemand', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'solvetime',type : 'date', dateFormat:'time'},//期望解决时间
             { name: 'resources'},//需求资源
             { name: 'isgt'},//是否与大区总沟通
             { name: 'regviews'},//大区总意见
             { name: 'groupopin'},//集团意见
             { name: 'isreply'},//是否答复
             { name: 'createUser'},
             { name: 'createDate',type : 'date', dateFormat:'time'}
         ]
     });

