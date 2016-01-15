/**
 * 获取浏览器高度
 */
crm.getBrowserHeight = function(){
	var browserHeight = document.documentElement.clientHeight;
	return browserHeight;
};

/**
 * 数据字典词条model
 */
Ext.define('Crm.model.DataDictionaryEntity',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'termsCode',//词条编码
		type : 'string'
	},{
		name : 'termsName',//词条名称
		type : 'string'
	},{
		name : 'notes',//备注
		type : 'string'
	}]
});
/**
 * 数据字典值model
 */
Ext.define('Crm.model.DataDictionaryValueEntity',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'termsCode',//词条编码
		type : 'string'
	},{
		name : 'termsName',//词条名称
		type : 'string'
	},{
		name : 'valueName',//值名称
		type : 'string'
	},{
		name : 'valueCode',//值代码
		type : 'string'
	},{
		name : 'valueSeq',//顺序
		type : 'string'
	},{
		name : 'language',//语言
		type : 'string'
	},{
		name : 'noteInfo',//备注
		type : 'string'
	}]
});
/**
 * 数据字典词条名称下拉框store
 */
Ext.define('Crm.commonSelector.DataDictionaryStore', {
	extend : 'Ext.data.Store',
	model : 'Crm.model.DataDictionaryEntity',
	proxy : {
		type : 'ajax',
		url : '../bse/dataDictionaryAction!queryDataDictionaryByEntity.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'dataDictionaryVo.dataDictionaryEntitys'
		}
	}
});
/**
 * 数据字典信息store
 */
Ext.define('Crm.store.DataDictionaryValueStore', {
	extend : 'Ext.data.Store',
	model : 'Crm.model.DataDictionaryValueEntity', 
	pageSize : 20,
	proxy : {
		type : 'ajax',
		actionMethods : 'post',
		url : 'dataDictionaryAction!queryDataDictionaryValueByEntity.action',
		reader : {
			type : 'json',
			rootProperty : 'dataDictionaryVo.dataDictionaryValueEntityList', 
			totalProperty : 'totalCount' //总个数
		}
	},
	listeners : {
		beforeload : function (store, operation, eOpts) {
			var queryForm = Ext.getCmp('queryForm');
			if (queryForm != null) {
				var params =  { 
						'dataDictionaryVo.dataDictionaryValueEntity.termsCode' : queryForm.getForm().findField('termsCode').getValue(),
						'dataDictionaryVo.dataDictionaryValueEntity.valueCode' : queryForm.getForm().findField('valueCode').getValue(),
						'dataDictionaryVo.dataDictionaryValueEntity.valueName' : queryForm.getForm().findField('valueName').getValue()
					};
				Ext.apply(store.proxy.extraParams, params);  
			}
		}
	}
});

/**
 * 数据字典词条名称下拉框
 */
Ext.define('Crm.selector.DataDictionarySelector', {
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.datadictionaryselector',
	displayField : 'termsName',// 显示名称
	valueField : 'termsCode',// 值
	queryParam : 'dataDictionaryVo.dataDictionaryEntity.termsName',// 查询参数
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.DataDictionaryStore');
		me.callParent([cfg]);
	}
});

/**
 * 查询表单
 */
Ext.define('Crm.view.dataDictionary.QueryForm', {
	extend : 'Ext.form.Panel',
	id : 'queryForm',
	frame : true,
	title : '查询条件',
	height : 120,
	collapsible : true,
	layout: 'hbox',
	titleCollapse:true,
	defaults : {
		colspan : 1,
		margin : '8 10 5 10'
	},
	defaultType : 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
			name : 'termsCode',
			fieldLabel : '词条名称',
			width : 250,
			xtype : 'datadictionaryselector'
		},{
			name : 'valueCode',
			fieldLabel : '值代码',
			width : 250,
			xtype : 'textfield'
		},{
			name : 'valueName',
			fieldLabel : '值名称',
			width : 250,
			xtype : 'textfield'
		}],
		me.buttons = [{
			text : '查询',
			handler : function(){
				if (me.getForm().isValid()) {
					me.up().getDataDictionaryGrid().getPagingToolbar().moveFirst();;
				}
			}
		}, {
			text : '清空',
			handler : function() {
				me.getForm().reset();
			}
		}];
		me.callParent([cfg]);
	}
});


/**
 * 数据字典信息表格
 */
Ext.define('Crm.view.dataDictionary.Grid',{
	extend : 'Ext.grid.Panel',
	frame : true,
	autoScroll : true,
	height : crm.getBrowserHeight()-120,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	emptyText : '查询结果为空', //查询结果为空
	columnLines: true,
	viewConfig:{
		enableTextSelection:true
	},
	dataDictionaryValueAddWindow : null,
	getDataDictionaryValueAddWindow : function(){
		if (this.dataDictionaryValueAddWindow == null) {
			this.dataDictionaryValueAddWindow = Ext.create('Crm.view.dataDictionary.DataDictionaryValueAddWindow');
			this.dataDictionaryValueAddWindow.parent = this; //父元素
		}
		return this.dataDictionaryValueAddWindow;
	},
	dataDictionaryAddWindow : null,
	getDataDictionaryAddWindow : function(){
		if (this.dataDictionaryAddWindow == null) {
			this.dataDictionaryAddWindow = Ext.create('Crm.view.dataDictionary.DataDictionaryAddWindow');
			this.dataDictionaryAddWindow.parent = this; //父元素
		}
		return this.dataDictionaryAddWindow;
	},
	pagingToolbar:null,
	getPagingToolbar : function () {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
					store : this.store,
					pageSize : 20
				});
		}
		return this.pagingToolbar;
	},
	deleteDataDictionaryValue:function(){
		var me = this;
		var selections = me.getSelectionModel().getSelection(); //获取选中的数据
		if (selections.length < 1) { //判断是否至少选中了一条
			crm.showWoringMessage('请选择一条进行删除操作'); //请选择一条进行作废操作！
			return; //没有则提示并返回
		}
		var dataDictionaryValueEntityList = new Array();
		for(var i = 0 ; i<selections.length ; i++){
			dataDictionaryValueEntityList.push({'valueCode':selections[i].get('valueCode')});
		}
		crm.showQuestionMes('是否要删除', function (e) { 
			if (e == 'yes') { //询问是否删除，是则发送请求
				var params = {'dataDictionaryVo':{'dataDictionaryValueEntityList':dataDictionaryValueEntityList}};
				var successFun = function (json) {
					var message = json.message;
					crm.showInfoMsg(message);
					me.getStore().load();
				};
				var failureFun = function (json) {
					if (Ext.isEmpty(json)) {
						crm.showErrorMes('请求超时'); //请求超时
					} else {
						var message = json.message;
						crm.showErrorMes(message);
					}
				};
				crm.requestJsonAjax('dataDictionaryAction!deleteDataDictionaryValue.action', params, successFun, failureFun);
			}
		});
	},
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.columns = [{
			dataIndex : 'termsName',
			flex : 1,
			text : '词名称'
		},{
			text : '值代码', 
			dataIndex : 'valueCode',
			flex : 1
		},{
			text : '值名称', 
			dataIndex : 'valueName',
			flex : 1
		},{
			text : '序号', 
			dataIndex : 'valueSeq',
			flex : 1
		},{
			text : '备注', 
			dataIndex : 'noteInfo',
			flex : 1
		}/*,{
			text : '操作', 
			width : 200
		}*/],
		me.store = Ext.create('Crm.store.DataDictionaryValueStore', {
			autoLoad : false
		});
		me.selModel = Ext.create('Ext.selection.CheckboxModel', { //多选框
			mode : 'MULTI',
			checkOnly : true
		});
		me.tbar = [ {
			text : '新增词条', // 新增
			width : 100,
			handler : function() {
				me.getDataDictionaryAddWindow().show();
			}
		}, '-', {
			text : '新增数据字典', // 作废
			width : 120,
			handler : function() {
				me.getDataDictionaryValueAddWindow().show();
			}
		} ,'-', {
			text : '删除', // 作废
			width : 80,
			handler : function() {
				me.deleteDataDictionaryValue();
			}
		}];
		me.bbar = me.getPagingToolbar();
		//Ext.setGlyphFontFamily('FontAwesome'); 
		me.callParent([cfg]);
	}
});

/**
 * 新增数据字典窗口
 */
Ext.define('Crm.view.dataDictionary.DataDictionaryValueAddWindow',{
	extend : 'Ext.window.Window',
	title : '新增数据字典',
	closable : true,
	parent : null, // 父元素
	modal : true,
	resizable : false, // 可以调整窗口的大小
	closeAction : 'hide', // 点击关闭是隐藏窗口
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners : {
		beforehide : function(me) { // 隐藏WINDOW的时候清除数据
			me.getDataDictionaryValueForm().getForm().reset(); // 表格重置
		},
		beforeshow : function(me) { // 显示WINDOW的时候清除数据
			var fielsds = me.getDataDictionaryValueForm().getForm().getFields();
			if (!Ext.isEmpty(fielsds)) {
				fielsds.each(function(item, index, length) {
					item.clearInvalid();
					item.unsetActiveError();
				});
			}
			fielsds = null;
		}
	},
	dataDictionaryValueForm : null,
	getDataDictionaryValueForm : function() {
		if (Ext.isEmpty(this.dataDictionaryValueForm)) {
			this.dataDictionaryValueForm = Ext.create('Crm.view.dataDictionary.DataDictionaryValueForm');
		}
		return this.dataDictionaryValueForm;
	},
	submitDataDictionaryValue:function(){
		var me = this;
		if (me.getDataDictionaryValueForm().getForm().isValid()) { //校验form是否通过校验
			var dataDictionaryValueModel = new Crm.model.DataDictionaryValueEntity();
			me.getDataDictionaryValueForm().getForm().updateRecord(dataDictionaryValueModel); //将FORM中数据设置到MODEL里面
			var params = {
					'dataDictionaryVo':{
						'dataDictionaryValueEntity':dataDictionaryValueModel.data
					}
			}
			var successFun = function (json) {
				var message = json.message;
				crm.showInfoMsg(message); //提示新增成功
				me.close();
				me.parent.getStore().load(); //成功之后重新查询刷新结果集
			};
			var failureFun = function (json) {
				if (Ext.isEmpty(json)) {
					crm.showErrorMes('连接超时'); //请求超时
				} else {
					var message = json.message;
					crm.showErrorMes(message); //提示失败原因
				}
			};
			crm.requestJsonAjax('dataDictionaryAction!addDataDictionaryValue.action', params, successFun, failureFun); //发送AJAX请求
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.fbar = [ {
			text : '取消', // 取消
			handler : function() {
				me.close();
			}
		}, {
			text : '重置', // 重置
			handler : function() {
				me.getDataDictionaryValueForm().reset();
			}
		}, {
			text : '保存', // 保存
			/*margin : '0 0 0 55',*/
			handler : function() {
				 me.submitDataDictionaryValue();
			}
		} ];
		me.items = [ me.getDataDictionaryValueForm() ];
		me.callParent([ cfg ]);
	}
});


Ext.define('Crm.view.dataDictionary.DataDictionaryAddWindow',{
	extend : 'Ext.window.Window',
	title : '新增词条',
	closable : true,
	parent : null, // 父元素
	modal : true,
	resizable : false, // 可以调整窗口的大小
	closeAction : 'hide', // 点击关闭是隐藏窗口
	layout : {
		type : 'vbox',
		align : 'stretch'
	},
	listeners : {
		beforehide : function(me) { // 隐藏WINDOW的时候清除数据
			me.getDataDictionaryForm().getForm().reset(); // 表格重置
		},
		beforeshow : function(me) { // 显示WINDOW的时候清除数据
			var fielsds = me.getDataDictionaryForm().getForm().getFields();
			if (!Ext.isEmpty(fielsds)) {
				fielsds.each(function(item, index, length) {
					item.clearInvalid();
					item.unsetActiveError();
				});
			}
			fielsds = null;
		}
	},
	dataDictionaryForm : null,
	getDataDictionaryForm : function() {
		if (Ext.isEmpty(this.dataDictionaryForm)) {
			this.dataDictionaryForm = Ext.create('Crm.view.dataDictionary.DataDictionaryForm');
		}
		return this.dataDictionaryForm;
	},
	submitDataDictionaryValue:function(){
		var me = this;
		if (me.getDataDictionaryForm().getForm().isValid()) { //校验form是否通过校验
			var dataDictionaryModel = new Crm.model.DataDictionaryEntity();
			me.getDataDictionaryForm().getForm().updateRecord(dataDictionaryModel); //将FORM中数据设置到MODEL里面
			var params = {
					'dataDictionaryVo':{
						'dataDictionaryEntity':dataDictionaryModel.data
					}
			}
			var successFun = function (json) {
				var message = json.message;
				crm.showInfoMsg(message); //提示新增成功
				me.close();
				me.parent.getStore().load(); //成功之后重新查询刷新结果集
			};
			var failureFun = function (json) {
				if (Ext.isEmpty(json)) {
					crm.showErrorMes('连接超时'); //请求超时
				} else {
					var message = json.message;
					crm.showErrorMes(message); //提示失败原因
				}
			};
			crm.requestJsonAjax('dataDictionaryAction!addDataDictionary.action', params, successFun, failureFun); //发送AJAX请求
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.fbar = [ {
			text : '取消', // 取消
			handler : function() {
				me.close();
			}
		}, {
			text : '重置', // 重置
			handler : function() {
				me.getDataDictionaryValueForm().reset();
			}
		}, {
			text : '保存', // 保存
			/*margin : '0 0 0 55',*/
			handler : function() {
				 me.submitDataDictionaryValue();
			}
		} ];
		me.items = [ me.getDataDictionaryForm() ];
		me.callParent([ cfg ]);
	}
});
/**
 * 数据字典表单
 */
Ext.define('Crm.view.dataDictionary.DataDictionaryValueForm',{
	extend : 'Ext.form.Panel',
	header : false,
	frame : true,
	collapsible : true,
	width: 280,
	fieldDefaults : {
        labelWidth: 80,
        margin : '8 10 5 10'
	},
	defaultType: 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
        	name : 'termsCode',
            fieldLabel: '词条名称',
            xtype : 'datadictionaryselector',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueSeq',
            fieldLabel: '序号',
            xtype : 'numberfield',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueCode',
            fieldLabel: '值代码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'valueName',
            fieldLabel: '值名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'noteInfo',
            fieldLabel: '备注',
            xtype:'textarea'
        }];
		me.callParent([cfg]);
	}
});

/**
 * 数据字典词条表单
 */
Ext.define('Crm.view.dataDictionary.DataDictionaryForm',{
	extend : 'Ext.form.Panel',
	header : false,
	frame : true,
	collapsible : true,
	width: 280,
	fieldDefaults : {
        labelWidth: 80,
        margin : '8 10 5 10'
	},
	defaultType: 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
        	name : 'termsName',
            fieldLabel: '词条名称',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'termsCode',
            fieldLabel: '词条代码',
            beforeLabelTextTpl: ['<span style="color:red;font-weight:bold" data-qtip="必填选项">*</span>'],
            allowBlank: false
        },{
        	name : 'noteInfo',
            fieldLabel: '备注',
            xtype:'textarea'
        }];
		me.callParent([cfg]);
	}
});
/**
 * 数据字典页面
 */
Ext.onReady(function () {
	Ext.QuickTips.init();
	var queryForm = Ext.create('Crm.view.dataDictionary.QueryForm'); //查询FORM
	var dataDictionaryGrid = Ext.create('Crm.view.dataDictionary.Grid');
	Ext.create('Ext.panel.Panel', {
		renderTo : Ext.getBody(),
		getQueryForm : function(){
			return queryForm;
		},
		getDataDictionaryGrid : function(){
			return dataDictionaryGrid;
		},
		items : [queryForm,dataDictionaryGrid]
	});
});