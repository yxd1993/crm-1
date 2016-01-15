/*回滚版本*/
/**
 * 联系人信息GRID
 */
Ext.define("crm.view.addCustomer.ContactList", {
    extend: "Ext.grid.Panel",
    alias: 'widget.contactList',
    store: "Contact",
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
		    {text : '序号',	width : 50,	xtype : 'rownumberer'},
	        { text: '联系人姓名', dataIndex: 'contactName', flex:1},
	        { text: '手机', dataIndex: 'cellphone', flex:1},
	        { text: '座机', dataIndex: 'telephone', flex:1},
	 		{ text: '区号', dataIndex: 'districtNumber', flex:1},
			{ text: '电子邮件', dataIndex: 'eMailAddress', flex:1},
			{ text: '职位', dataIndex: 'job', flex:1},
			{ text: '是否默认', dataIndex: 'isDefault', flex:1, renderer: isDefaultRenderer},
			{ text: '修改时间', dataIndex: 'modifyDate', flex:1, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
		 ]
    },
    tbar : [{
    	text : '新增',
    	action : 'add'
    }, {
    	text : '修改',
    	action : 'edit'
    }, {
    	text : '删除',
    	action : 'delete'
    }, {
    	text : '设为默认',
    	action : 'default'
    }]/*,
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
	}*/
});

/**
 * 是否默认联系人渲染
 * 
 * @param val
 * @returns
 * @author 蒋落琛
 * @date 2015-6-12
 * @update
 */
function isDefaultRenderer(val){
	if(val == 'Y'){
		return '是';
	}else{
		return '否';
	}
}