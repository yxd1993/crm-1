Ext.define("crm.view.role.Auth", {
    extend: "Ext.window.Window",
    alias: "widget.authWin",
    title: "角色权限配置",
    closeAction : 'destroy',
    width: 400,
    height: 400,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        layout : 'fit',
        border: false,
        record : null,
        items: [
			{xtype : 'authTree'}
        ],
        buttons: [{
            text: '全部展开',
            action : 'expand'
        },{
            text: '提交',
            action : 'submit'
        }]
    }/*,
    listeners : {
    	'close' : function(panel){
    		var form = panel.down('form');
    		var tree = form.down('treepanel');
    		tree.destroy();
    		form.destroy();
    	}
    }*/
});
