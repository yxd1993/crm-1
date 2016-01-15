/**
 * 用户反馈Model
 */
Ext.define('crm.model.FeedBackInfo', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'userName'},//反馈用户姓名
             { name: 'fbTitle'},//反馈信息主题
             { name: 'fbType'},//反馈类型
             { name: 'fbContent'},//反馈内容
             { name: 'fbTime', type : 'date', dateFormat:'time'}//反馈时间
             
         ]
     });
