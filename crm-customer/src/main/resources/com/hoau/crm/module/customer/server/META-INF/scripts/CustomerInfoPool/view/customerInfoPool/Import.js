/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.customerInfoPool.Import", {
	extend: "Ext.window.Window",
    alias: "widget.customerInfoPoolImportWin",
    title: "客户信息批量导入",
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
    		xtype : 'displayfield',
    		fieldLabel : '模板',
    		value : '<a href="javascript:downLoadFile(\'\/attachment\/customer\/template.xlsx\', \'客户信息.xlsx\')">模板下载</a>'
    	},{
    		name : 'file',
    		xtype : 'filefield',
    		fieldLabel : '文件',
    		msgTarget : 'side',
    		allowBlank : false,
    		buttonText : '请选择文件'
    	}],
    	buttons : [{
    		text : '上传',
    		handler : function() {
    			var form = this.up('form').getForm();
    			if (form.isValid()) {
    				form.submit({
    					url : 'upload.action',
    					headers: {'Content-Type':'multipart/form-data; charset=UTF-8'},
    					waitMsg : '文件上传中，请稍后...',
    					success : function(form,response) {
    						Ext.MessageBox.alert('提示','上传成功');
    						Ext.getCmp('customerInfoPoolViewId').getCustomerInfoPoolGrid().getPagingToolbar().moveFirst();
    					},
    					failure : function(form,response) {
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
    		}
    	]
    }
});

/**
 * 模板下载
 * 
 * @param filePath
 * @param fileName
 * @author 蒋落琛
 * @date 2015-5-27
 * @update
 */
function downLoadFile(filePath, fileName) {
	var url = 'downloadCustomerTemplate.action?resource.attachpath=' + filePath
			+ '&resource.attachname=' + fileName;
	window.location.href = url;
}