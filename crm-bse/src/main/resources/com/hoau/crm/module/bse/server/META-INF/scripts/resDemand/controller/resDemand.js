/**
 * 资源需求Controller
 */
Ext.define('crm.controller.resDemand', {
	extend : 'Ext.app.Controller',
	stores : [ 'resDemand' ],
	models : [ 'resDemand' ],
	views : [ 'Viewport', 'resDemand.List', 'resDemand.Search' ,'resDemand.Add'],
	init : function() {
		this.control({
			'resDemandSearch button[action=reset]' : {
				click : this.resDemandSearchForm
			},
			'resDemandSearch button[action=select]' : {
				click : this.reloadResDemandGridStore
			},
			'resDemandSearch button[action=delete]' : {
				click : this.deleteResDemand
			},
			'resDemandSearch button[action=add]' : {
				click : this.addResDemand
			},

		});
	},
	resDemandSearchForm : function(btn) {
		// 重置表单
		btn.up('form').getForm().reset();
	},
	reloadResDemandGridStore : function(btn) {
		Ext.getCmp('resDemandMainViewId').getResDemandGrid()
				.getPagingToolbar().moveFirst();
	},
	
	deleteResDemand : function(btn){
    	// 选中的数据
    	var selectArr = Ext.getCmp('resDemandMainViewId').getResDemandGrid().getSelectionModel().getSelection();
    	if(selectArr.length > 0){
			Ext.Msg.confirm('提示', '您确定要删除吗？', function(btn) {
				if(btn == 'yes') {
					//删除的信息集合
					var deleteInfoArr = [];
					for(var i=0; i<selectArr.length; i++){
						deleteInfoArr.push(selectArr[i].getData().id)
					}
					var params = {};
					params.ids = deleteInfoArr;
					// AJAX请求
					crm.requestJsonAjax('resDemandAction!deleteResDemand.action', params, 
							function(){
								Ext.MessageBox.alert('提示','资源需求删除成功');
								Ext.getCmp('resDemandMainViewId').getResDemandGrid().getStore().reload();
							}, 
							function(){Ext.MessageBox.alert('提示','资源需求删除失败');});
				}
			})
		}else{
			Ext.MessageBox.alert('提示','请选择需要删除的资源需求信息');
		}
		
	},
	
	addResDemand :function(btn){
    	// 新增App版本信息
    	var win = Ext.widget("resDemandAddWin");
    	win.show();
	},
    
});