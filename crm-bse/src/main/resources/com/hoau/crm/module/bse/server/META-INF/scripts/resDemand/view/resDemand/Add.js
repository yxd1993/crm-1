/**
 * WINDOW
 */
Ext.define("crm.view.resDemand.Add", {
	id:"resDemandAddWin",
    extend: "Ext.window.Window",
    alias: "widget.resDemandAddWin",
    title: "新增资源需求",
    width: 700,
    height: 240,
    layout: "fit",
    modal:true,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        //fileUpload:  true,
        record : null, // 客户信息
        defaultType: 'textfield',
        fieldDefaults: {
            labelAlign: 'right',
            columnWidth : 0.49,
            margin : '5 0 5 0 ',
            labelWidth: 100,
            regex : /^\S*$/,
        },
        items: [
            {
            	xtype : 'datefield',
    			format : 'Y-m-d',
    			name : "solvetime",
    			fieldLabel : '期望解决时间<font color="#FF0000"><b>*</b></font>',
    			allowBlank : false,
    			value: new Date(),
    		},{
        		name : 'resources',
        		xtype : 'textfield',
        		fieldLabel : '需求资源'
        	},{
    			name : "isgt",
    			fieldLabel : '是否与大区总沟通<font color="#FF0000"><b>*</b></font>',
                xtype : 'dataDictionarySelector',
                store: getDataDictionary().getDataDictionaryStore('APP_VERSION_ISMUST', null, null, null),
    			allowBlank : false
    		},{
    			name : "isreply",
    			fieldLabel : '是否答复<font color="#FF0000"><b>*</b></font>',
                xtype : 'dataDictionarySelector',
                store: getDataDictionary().getDataDictionaryStore('APP_VERSION_ISNOW', null, null, null),
    			allowBlank : false
    		},{
    			name :"regviews",
    			fieldLabel : '大区总意见',
    			xtype: "textareafield",
    			maxLength : 500,
    			columnWidth : 0.98,
    			allowBlank : true
    		}
        ],
        buttons: [{
            text: '还原',
            action : 'reset'
        }, {
            text: '提交',
            handler :function(){
            	var form=this.up('form').getForm();
            	var resDemand={};
            	resDemand.solvetime = form.findField('solvetime').getValue();
            	resDemand.resources = form.findField('resources').getValue();
            	resDemand.isgt = form.findField('isgt').getValue();
            	resDemand.isreply = form.findField('isreply').getValue();
            	resDemand.regviews = form.findField('regviews').getValue();
            	if(!form.isValid()){
            		return;
            	}
            	var params = {};
            	params.resDemand = resDemand;
            	crm.requestJsonAjax('resDemandAction!addResDemand.action', params, 
    			function(response){
    				Ext.MessageBox.alert('提示','资源需求保存成功',function(){
    					Ext.getCmp('resDemandAddWin').close();
    					Ext.getCmp('resDemandMainViewId').getResDemandGrid().getStore().reload();
    				});
    			}, 
    			function(response){
    				Ext.MessageBox.alert('提示', response.message);});
            }
        }]
    }
});
