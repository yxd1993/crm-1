/**
 * App版本信息Model
 */
Ext.define('crm.model.AppVersion', {
    extend: 'Ext.data.Model',
    fields: [
             { name: 'id'},
             { name: 'versionCode'},//App版本号
             { name: 'apkName'},//APK名字
             { name: 'isMust'},//是否强制更新
             { name: 'isNow'},//是否当前版本
             { name: 'url'},//安装包下载地址
             { name: 'userHeadUrl'},//头像下载地址
             { name: 'description'},//描述
             { name: 'createDate',type : 'date', dateFormat:'time'},//版本创建时间
         ]
     });

