/**
 * 资源需求Model
 */
Ext.define('crm.model.dailytrain', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'hyDate',type : 'date', dateFormat:'time'},//会议时间
             { name: 'pxType'},//培训类型
             { name: 'hyaddress'},//会议地址
             { name: 'cyEmp'},//参与人员
             { name: 'pxcontext'},//培训内容
             { name: 'meetingSignId'},//关系签到ID
             { name: 'createDate',type : 'date', dateFormat:'time'},
             { name: 'createUser'}
         ]
     });

