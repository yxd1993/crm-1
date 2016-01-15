/**
 * App版本信息GRID
 */
Ext.define("crm.view.appVersion.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.appVersionList',
    store: "AppVersion",
    region : 'center',
    width : '100%',
    viewConfig:{
		enableTextSelection:true
	},
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		            { text : '序号',	width : 50,	xtype : 'rownumberer'},
		            { text: 'uuid', dataIndex: 'id',xtype :'hiddenfield'},
		         	{ text: 'App版本号', dataIndex: 'versionCode', flex:0.5},
		         	{ text: 'APK名字', dataIndex: 'apkName', flex:0.5},
		         	{ text: '是否强制更新', dataIndex: 'isMust', flex:0.5,renderer:dispenseIsMustRenderer},
		         	{ text: '是否当前版本', dataIndex: 'isNow', flex:0.5,renderer:dispenseIsNowRenderer},
		         	{ text: '安装包下载地址', dataIndex: 'url',xtype :'hiddenfield'},
		         	{ text: '版本描述', dataIndex: 'description', flex:0.5},
		         	{ text: '版本创建时间', dataIndex: 'createDate', flex:0.7, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
		         	
				]
 },
 pagingToolbar : null,
	getPagingToolbar : function() {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
				store : this.store,
				dock: 'bottom',
		        displayInfo: true
			});
		}
		return this.pagingToolbar;
	},
 constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.bbar = me.getPagingToolbar();
		me.callParent([ cfg ]);
	}
});

/**
 * 是否强制更新渲染功能
 * 
 * @param value
 * @returns
 * @author 潘强
 * @date 2015-7-31
 * @update
 */
/*function dispenseIsMustRenderer(value){
	if (value=="1"){ return "是"};
	if (value=="2"){ return "不是"}
}*/
function dispenseIsMustRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "APP_VERSION_ISMUST");
}
/**
 * 是否当前版本渲染功能
 * 
 * @param value
 * @returns
 * @author 潘强
 * @date 2015-7-31
 * @update
 */
/*function dispenseIsNowRenderer(value){
	if (value=="1"){ return "当前版本"};
	if (value=="2"){ return "过去版本"}
}*/
function dispenseIsNowRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "APP_VERSION_ISNOW");
}
