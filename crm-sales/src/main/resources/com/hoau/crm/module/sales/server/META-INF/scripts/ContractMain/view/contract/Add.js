/**
 * 销售合同新增
 */
Ext.define("crm.view.contract.Add", {
	extend: "Ext.window.Window",
    alias: "widget.contractAddWin",
    title: "新增合同",
    width: 400,
    height: 200,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        id : 'form11',
        margin: 5,
        border: false,
        layout: "column",
        record : null, //客户信息
        fieldDefaults: {
            labelAlign: 'right',
            labelWidth : 80,
            margin : '15 0 5 0 ',
            columnWidth : 0.9
        },
        items : [ {
    		xtype : 'textfield',
    		fieldLabel : 'DC账号',
    		name :　'dcAccount',
    		allowBlank : false
    	},{
    		xtype : 'hiddenfield',
    		name :　'id',
    	},{
    		name : 'file',
    		xtype : 'filefield',
    		fieldLabel : '文件',
    		msgTarget : 'side',
    		allowBlank : false,
    		buttonText : '请选择文件'
    	}],
    	buttons : [{
    		text : '完成',
    		handler : function() {
    			var form = this.up('form').getForm();
    			var dcAccount = form.findField('dcAccount').getValue();
    			var id = form.findField('id').getValue();
    			var params = {};
        		var importParamsVo = {};
        		importParamsVo.id = id;
        		importParamsVo.dcAccount = dcAccount;
				params.importParamsVo = importParamsVo;
				if(!form.isValid()){
					return;
				}
    			//判断是否已存在合同附件
    			crm.requestJsonAjax('saleContractAction!isExistAttachment.action', params, 
        				function(response){
        					if(response.success){
        						submit(form,importParamsVo);
        					}
            			}, 
            			function(response) {
            				if(response.message == undefined){
            					Ext.MessageBox.alert('提示',"系统异常,请联系系统管理员!");
            				}else{
            					//业务异常
            					if("crm.sale.contract.isExistUploadedException" == response.message ){
            						Ext.MessageBox.confirm('提示','您确定要覆盖该账号的合同信息？',function(btn){
            							if(btn == 'yes'){
            								importParamsVo.isCover = "Y";
            								submit(form,importParamsVo);
            							}
            						});
            					}else{
            						Ext.MessageBox.alert('提示',response.message);
            					}
            				} 
            		});
    			}
    		}
    	]
    },
	listeners : {
		'beforeclose' : function(){
			ContractAddWin = undefined;
		}
	}
});

function submit(form,importParamsVo){
	if (form.isValid()) {
		form.submit({
			url : 'import.action',
			headers: {'Content-Type':'multipart/form-data; charset=UTF-8'},
			waitMsg : '文件上传中，请稍后...',
			params :{
				'importParamsVo.id':importParamsVo.id,
				'importParamsVo.dcAccount':importParamsVo.dcAccount,
				'importParamsVo.isCover':importParamsVo.isCover
			},
			success : function(form, response) {
				Ext.MessageBox.alert('提示','上传成功');
				Ext.getCmp('contractMainViewId').getContractGrid().getPagingToolbar().moveFirst();
			},
			failure : function(form, response) {
				var result = response.result;
				if(result.message == undefined){
					Ext.MessageBox.alert('提示',"系统异常,请联系系统管理员!");
				}else{
					//业务异常
					Ext.MessageBox.alert('提示',result.message);
				}
			}
		});
	}
}