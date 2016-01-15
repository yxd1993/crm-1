/**
 * 资源需求Model
 */
Ext.define('crm.model.dailyMeeting', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'hyDate',type : 'date', dateFormat:'time'},//会议时间
             { name: 'hyform'},//会议类型
             { name: 'hyType'},//会议形式
             { name: 'hyaddress'},//会议地址
             { name: 'cyEmp'},//参与人员
             { name: 'hycontext'},//会议内容
             { name: 'meetingSignId'},//关系签到ID
             { name: 'createDate',type : 'date', dateFormat:'time'},
             { name: 'createUser'}
         ]
     });

