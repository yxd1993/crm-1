/**
 * 会议签到信息Model
 */
Ext.define('crm.model.MeetingSign', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'sweepPeopName'},//扫描签到人姓名
             { name: 'sweepPeopJobName'},//扫描人岗位名称
             { name: 'sweepPeopDeptName'},//扫描人部门名称
             { name: 'sweepPeopType'},//会议扫描签到类型
             { name: 'wasMeetingSignList'},//被扫描人信息集合
             { name: 'signAddress'},//客户签到地址
             { name: 'attachmentList'},//保存图片路径的集合
             { name: 'qrcodeTime',type : 'date', dateFormat:'time'},//二维码生成时间
             { name: 'sweepTime',type : 'date', dateFormat:'time'}//客户扫描签到时间
         ]
     });

