/**
 * 客户信息GRID
 */
Ext.define("crm.view.role.List", {
    extend: "Ext.grid.Panel",
    alias: 'widget.rolelist',
    store: "Role",
    region : 'center',
    width : '100%',
    selModel : Ext.create('Ext.selection.CheckboxModel'),
    tbar: [, {
        text: '新增',
        action : 'add'
    }, {
        text: '修改',
        action : 'update'
    }, {
        text: '删除',
        action : 'delete'
    }, {
        text: '权限配置',
        action : 'auth'
    }],
    columns : {
		defaults: {
		    align : 'center'
		},
		items : [
		    {text : '序号',	width : 50,	xtype : 'rownumberer'},
	        { text: '角色编码', dataIndex: 'roleCode', flex:1.2},
	        { text: '角色名称', dataIndex: 'roleName', flex:1.2},
	        { text: '角色描述', dataIndex: 'roleDesc', flex:1.2},
			{ text: '修改时间', dataIndex: 'modifyDate', flex:1.5, xtype : 'datecolumn', format : 'Y-m-d H:i:s'}
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
