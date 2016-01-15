/**
 * 预约客户Store
 */
Ext.define("crm.store.Reserve", {
    extend: "Ext.data.Store",
    model: "crm.model.Reserve",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../sales/saleReservePlanAction!queryReserveByPaging.action',
        reader: {
            type: 'json',
            rootProperty: 'reserveList',
            totalProperty: 'totalCount'
        }
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var queryForm = Ext.getCmp('reserveMainViewId').getReserveSearchForm().getForm();
			//查询条件
			var contactName = queryForm.findField('contactName').getValue();   //联系人姓名
			var reserveType = queryForm.findField('reserveType').getValue();
			var enterpriseName = queryForm.findField('enterpriseName').getValue();
			var reserveStartTime = queryForm.findField('reserveStartTime').getValue();
			var reserveEndTime = queryForm.findField('reserveEndTime').getValue();
			var isAgreement = queryForm.findField('isAgreement').getValue();
			var createUser = queryForm.findField('createUser').getValue();
			if (queryForm != null) {
				var params = {
						'reserveVo.reserveEntity.contactEntity.contactName' : contactName,
						'reserveVo.reserveEntity.reserveType' : reserveType,
						'reserveVo.reserveEntity.customerEntity.enterpriseName' : enterpriseName,
						'reserveVo.reserveEntity.reserveStartTime' : reserveStartTime,
						'reserveVo.reserveEntity.reserveEndTime' : reserveEndTime,
						'reserveVo.reserveEntity.isAgreement':isAgreement,
						'reserveVo.reserveEntity.createUser':createUser
					}
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	},
    autoLoad: false
});