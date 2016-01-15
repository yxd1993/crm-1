/**
 * 客户扫描签到Store
 */
Ext.define("crm.store.SweepSign", {
    extend: "Ext.data.Store",
    model: "crm.model.SweepSign",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: 'sweepSignAction!querySweepSignAction.action',
        reader: {
            type: 'json',
            rootProperty: 'sweepSignList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('sweepSignMainViewId').getSweepSignSearchForm().getForm();
			//查询条件
			var sweepPeopName=queryForm.findField('sweepPeopName').getValue();
			var wasSweepPeopName=queryForm.findField('wasSweepPeopName').getValue();
			var signAddress=queryForm.findField('signAddress').getValue();
			var sweepTime=queryForm.findField('sweepTime').getValue();
			var sweepEndTime=queryForm.findField('sweepEndTime').getValue();
			
			if (queryForm != null) {
				var params = {
						'sweepSign.sweepPeopName' : sweepPeopName,
						'sweepSign.wasSweepPeopName' : wasSweepPeopName,
						'sweepSign.signAddress' : signAddress,
						'sweepSign.sweepTime' : sweepTime,
						'sweepSign.sweepEndTime' : sweepEndTime,
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
			var imgHtml='<img src="" >';
			Ext.getCmp('sweepSignMainViewId').getSweepSignImgGrid().getEl().update(imgHtml);
		}
	},
    autoLoad: true
});
