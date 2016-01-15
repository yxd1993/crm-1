Ext.onReady(function() {
	Ext.create('Ext.form.Panel', {
		title : '文件上传',
		width : 400,
		bodyPadding : 10,
		frame : true,
		renderTo : 'file',
		items : [ {
			xtype : 'label',
			html : '<a href="download.action?fileName=客户信息池模板.xlsx">模板下载</a>'
		},{
			xtype : 'filefield',
			name : 'file',
			fieldLabel : '文件',
			labelWidth : 50,
			msgTarget : 'side',
			allowBlank : false,
			anchor : '100%',
			buttonText : '请选择文件...'
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
						success : function(form, action) {
							Ext.Msg.alert('提示信息',"上传成功");
						},
						failure : function(form, action) {  
				            Ext.MessageBox.alert("提示信息","请求失败,文件上传失败");  
				        } 
					});
				}
			}
			}]
		});
});