/**
 * 资源需求Model
 */
Ext.define('crm.model.dailyProblem', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'yltime',type : 'date', dateFormat:'time'},//演练日期
             { name: 'ylway'},//演练方式
             { name: 'wtdescribe'},//问题描述
             { name: 'jcdescribe'},//举措描述
             { name: 'yladdress'},//演练地址
             { name: 'cyEmp'},//参与人员
             { name: 'meetingSignId'},//关系签到ID
             { name: 'createDate',type : 'date', dateFormat:'time'},
             { name: 'createUser'}
         ]
     });

