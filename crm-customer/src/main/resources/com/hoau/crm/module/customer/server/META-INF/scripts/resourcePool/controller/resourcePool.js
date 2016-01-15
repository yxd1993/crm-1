/**
 * 客户信息Controller
 */
Ext.define('crm.controller.resourcePool', {
    extend: 'Ext.app.Controller',
    stores: ['resourcePool'],
    models: ['resourcePool'],
    views: ['Viewport', 'resourcePool.List','resourcePool.Search','resourcePool.Detail'],
    init: function () {
        this.control({
            'resourcePoolSearch button[action=reset]': {
                click: this.resetResourcePoolSearchForm
            },
            'resourcePoolSearch button[action=select]': {
                click: this.reloadResourcePoolGridStore
            },
            'resourcePoollist' : {
				itemdblclick : this.lookResourceCustomerInfo
			},
            'resourcePoolSearch button[action=claim]': {
                click: this.claimResourcePool
            }
        });
    },
    resetResourcePoolSearchForm : function(btn){
    	btn.up('form').getForm().reset();
    },
    reloadResourcePoolGridStore : function(btn){
    	Ext.getCmp('resourcePoolViewId').getResourcePoolGrid().getPagingToolbar().moveFirst();
    },
    lookResourceCustomerInfo : function(){
    	var win = Ext.widget("resourcePoolDetailWin");
		win.show();
		var form = win.down("form").getForm();
		var selectArr = Ext.getCmp('resourcePoolViewId').getResourcePoolGrid().getSelectionModel().getSelection();
		var cId = selectArr[0].get('id');
		if (!Ext.isEmpty(id)) {
			var params = {};
			params.cId = cId;
			crm.requestJsonAjax('customerResourcePoolAction!queryCustomerResourcePoolById.action',params, function(response) {
							var entity = response.customerResourcePoolEntity;
							if(entity!=null){
								form.findField('enterpriseName').setValue(entity.enterpriseName);
								form.findField('industryCode').setValue(
										getDataDictionary().rendererSubmitToDisplay(entity.industryCode,
												"CUSTOMER_INDUSTRY"));
								form.findField('regions').setValue(entity.regions);
								form.findField('contactName').setValue(entity.contactName);
								form.findField('cellphone').setValue(entity.cellphone);
								form.findField('telephone').setValue(entity.telephone);
								form.findField('address').setValue(entity.address);
								// 流入时间
								form.findField('flowDate').setValue(
										Ext.isEmpty(entity.flowDate) ? null : Ext.util.Format
												.date(new Date(entity.flowDate), 'Y-m-d H:i:s'));
							}
						}, 
						function() {Ext.MessageBox.alert('提示','查询资源池客户信息失败');})
		}
    },
    claimResourcePool : function(btn){
    	var claimNum = getDataDictionary().rendererSubmitToDisplay('客户资源池最大认领数', "CUSTOMERRESOURCEPOOL_MAX_CLAIM_NUM");
    	// 选中的数据
    	var selectArr = Ext.getCmp('resourcePoolViewId').getResourcePoolGrid().getSelectionModel().getSelection();
    	if(selectArr.length>claimNum){
    		return Ext.MessageBox.alert('提示','认领资源客户超过最大认领数');
    	}
    	// id集合
		var idArray = [];
		var params = {};
    	if(selectArr.length == 0){
			Ext.MessageBox.alert('提示','请选择需要认领的资源客户');
		}else {
			for (var i = 0; i < selectArr.length; i++) {
				idArray.push(selectArr[i].getData().id)
			}
			params.ids = idArray;
			Ext.MessageBox.confirm('提示','您确定要认领这个资源客户吗？',function(btn) {
				if (btn == 'yes') {
					crm.requestJsonAjax('customerResourcePoolAction!claimCustomerResourcePool.action',params, function(response) {
						Ext.getCmp('resourcePoolViewId').getResourcePoolGrid().getStore().reload();
						if(response.claimNum<claimNum&&response.claimNum>0){
							return Ext.MessageBox.alert('提示','您当天还可以认领'+response.claimNum+'个资源客户');
						}else if(response.claimNum==0){
							return Ext.MessageBox.alert('提示','当天认领资源客户数已使用完');
						}else if(response.claimNum==-1){
							return Ext.MessageBox.alert('提示','认领失败,已使用完当天最大认领资源客户数');
						}
					},
					function() {Ext.MessageBox.alert('提示','认领资源客户失败');})
				}
			});
		}
    }
});
