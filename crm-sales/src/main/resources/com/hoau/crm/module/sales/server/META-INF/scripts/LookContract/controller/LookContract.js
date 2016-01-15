/**
 * 销售合同Controller
 */
Ext.define('crm.controller.LookContract', {
    extend: 'Ext.app.Controller',
    views: ['Viewport', 'lookContract.LookContractForm'],
    init: function () {
        this.control({
            'lookContractForm button[action=export]': {
                click: this.exportAttachment
            }
        });
    },
    exportAttachment : function(btn){
    	var id = cId;
    	var url = 'export.action?id='+id;
    	window.location.href = url;
    }
});
