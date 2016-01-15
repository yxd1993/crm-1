/*回滚版本*/
/**
 * 联系人信息STORE
 */
Ext.define("crm.store.Contact", {
    extend: "Ext.data.Store",
    model: "crm.model.Contact",
    pageSize : 15,
    proxy: {
        type: 'ajax',
        url: '../customer/customerAction!queryContactList.action',
        reader: {
            type: 'json',
            rootProperty: 'contactList',
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
    currContactId : null,
    setCurrContactId : function(currContactId){
    	this.currContactId = currContactId;
    },
    getCurrContactId : function(){
    	return this.currContactId;
    },
	listeners : {
		'beforeload' : function(store, operation, eOpts) {
			var params = {
					'customerEntity.id' : store.getAccountId()
				}
			Ext.apply(store.proxy.extraParams, params);  
		},
		'load' : function(store, records){
			if(records.length > 0){
				var form = Ext.getCmp('reserveAddWinId').items.get(0).getForm();
				// 如果是修改,不一定是默认联系人
				if(!Ext.isEmpty(store.getCurrContactId())){
					Ext.each(records, function(reocrd){
						if(reocrd.data['id']== store.getCurrContactId()){
							// 默认联系人
							if(!Ext.isEmpty(reocrd.data['id'])){
								form.findField('contactName').setValue(reocrd.data['id']);
							}else{
								form.findField('contactName').reset();
							}
							// 默认联系人联系方式
							if(!Ext.isEmpty(reocrd.data['cellphone'])){
								form.findField('cellphone').setValue(reocrd.data['cellphone']);
							} else if(!Ext.isEmpty(reocrd.data['telephone'])){
								form.findField('cellphone').setValue(reocrd.data['telephone']);
							} else {
								form.findField('cellphone').reset();
							}
						}
					})
					store.setCurrContactId(null);
				// 新增,默认联系人
				}else{
					Ext.each(records, function(reocrd){
						if(reocrd.data['isDefault'] == 'Y'){
							// 默认联系人
							if(!Ext.isEmpty(reocrd.data['id'])){
								form.findField('contactName').setValue(reocrd.data['id']);
							}else{
								form.findField('contactName').reset();
							}
							// 默认联系人联系方式
							if(!Ext.isEmpty(reocrd.data['cellphone'])){
								form.findField('cellphone').setValue(reocrd.data['cellphone']);
							} else if(!Ext.isEmpty(reocrd.data['telephone'])){
								form.findField('cellphone').setValue(reocrd.data['telephone']);
							} else {
								form.findField('cellphone').reset();
							}
						} 
					})
				}
			} else if(!Ext.isEmpty(store.getAccountId())){
				var form = Ext.getCmp('reserveAddWinId').items.get(0).getForm();
				// 默认联系人
				form.findField('contactName').reset();
				// 默认联系人联系方式
				form.findField('cellphone').reset();
			}
		}
	},
	autoLoad : true
});