/**
 * 客户签到信息Model
 */
Ext.define('crm.model.Sign', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'customerName'},//签到人
             { name: 'longitude'},//经度
             { name: 'latitude'},//纬度
             { name: 'signAddress'},//签到地址
             { name: 'imgName'},//图片文件名
             { name: 'imgUrl'},//图片路径
             { name: 'createDate',type : 'date', dateFormat:'time'}//签到时间
         ]
     });