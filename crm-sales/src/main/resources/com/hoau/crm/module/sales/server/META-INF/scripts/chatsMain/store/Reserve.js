/**
 * 未赴约信息STORE
 */
Ext.define("crm.store.Reserve", {
    extend: "Ext.data.Store",
    model: "crm.model.Reserve",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../sales/saleReservePlanAction!queryNoReserve.action',
        reader: {
            type: 'json',
            rootProperty: 'reserveVoList',
            totalProperty: 'totalCount'
        }
    },
    accountId : null,
    setAccountId : function(accountId){
    	this.accountId = accountId;
    },
    getAccountId : function(){
    	return this.accountId;
    },
    currReserveId:null,
    setCurrReserveId:function(currReserveId){
    	this.currReserveId = currReserveId;
    },
    getCurrReserveId:function(currReserveId){
    	return this.currReserveId;
    },
    listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var params = {
					'reserveVo.accountId' : store.getAccountId()
				}
			Ext.apply(store.proxy.extraParams, params);  
		}
//		,'load' : function(store, records){
//			console.log(store)
//			if(records.length > 0&&records!=null){
//				var form = Ext.getCmp('chatsAddWinId').items.get(0).getForm();
//				// id不为空
//				if(!Ext.isEmpty(store.getCurrReserveId())){
//					Ext.each(records, function(reocrd){
//						if(reocrd.data['id']== store.getCurrReserveId()){
//							// 默认关联预约
//							if(!Ext.isEmpty(reocrd.data['id'])){
//								form.findField('reserveInfo').setValue(reocrd.data['id']);
//							}else{
//								form.findField('reserveInfo').reset();
//							}
//						}
//					})
//				}
//			}
//		}
	}
});