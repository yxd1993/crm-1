/**
 * 未绑定的签到信息STORE
 */
Ext.define("crm.store.Sign", {
    extend: "Ext.data.Store",
    model: "crm.model.Sign",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../sales/signAction!querySignInfoNoRelation.action',
        reader: {
            type: 'json',
            rootProperty: 'signNoRelationList',
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
    currSignId:null,
    setCurrSignId:function(currSignId){
    	this.currSignId = currSignId;
    },
    getCurrSignId:function(currSignId){
    	return this.currSignId;
    },
    listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var params = {
					'sign.customerId' : store.getAccountId()
				}
			Ext.apply(store.proxy.extraParams, params);  
		}
	}
});