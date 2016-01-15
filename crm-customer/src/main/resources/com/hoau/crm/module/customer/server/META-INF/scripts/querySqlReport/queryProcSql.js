/**
 * 自定义创建存储过程SQL Model
 */
Ext.define('QueryProcSqlModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	},// ID
	{
		name : 'num'
	},// 序列号
	{
		name : 'tablenum'
	},// 表名
	{
		name : 'tablename'
	},// 中文名
	{
		name : 'createSql'
	},// 执行时间
	{
		name : 'execTime'
	},// 上次执行时间
	{
		name : 'lastTime'
	},// SQL
	{
		name : 'createType',
		defaultValue:'2'
	},// 创建类型
	{
		name : 'remark'
	},// 备注
	{
		name : 'alter'
	},//修改表或者存储过程
	{
		name : 'createUser'
	},// 创建人
	{
		name : 'createDate',
		type : 'date',
		dateFormat:'time',
		defaultValue : new Date()
	},// 创建时间
	{
		name : 'modifyUser'
	},// 修改人
	{
		name : 'modifyDate',
		type : 'date',
		dateFormat:'time',
		defaultValue : new Date()
	} // 修改时间

	]
});

/**
 * 创建类型渲染功能
 */
function createTypeRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOM_CREATETYPE");
}

//自定义查询SQL Store
Ext.define('QueryProcSqlStore', {
	extend : 'Ext.data.Store',
	model : 'QueryProcSqlModel',
	pageSize : 15,// 分页条数
	// 数据库取数据方法，暂时屏蔽
	proxy : {
		type : 'ajax',
		url : '../customer/showPagequeryCreateSql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			rootProperty : 'queryCreateSqls',
			totalProperty : 'totalCount'
		}
	}
});


Ext.onReady(function(){
    Ext.QuickTips.init();
    
	var win;//新增修改自定义创建SQL window
	/**
	* 查询条件form
	*/
	 var isFirstQuery=0;//是否是第一次加载0为第一次加载，1为不是第一次加载
	var form=Ext.create('Ext.form.Panel',{
		layout : {
			type : 'table',	//table布局
			columns : 4	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:70,
			border:false,
			labelAlign: 'right',
			width : 260
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:45,
		region:'north',
		items:[{
			fieldLabel: '存储过程名',
			id : 'tablenum',
			name:'tablenum'
		},{
			fieldLabel: '中文名',
			id : 'tablename',
			name:'tablename'
		},{
			fieldLabel: '功能描述',
			id : 'remark',
			name:'remark'
		},{
			xtype:'button',
			text:'查询',
			width:80,
			border : true,
			handler:function(){
				isFirstQuery=1;
				queryProcSqlStore.loadPage(1);	//查询后默认返回第一页
			}
		}
		]
	});
	
	var queryProcSqlStore=Ext.create('QueryProcSqlStore',{
		listeners: {
			beforeload : function(store, operation, eOpts) {
				if (form!= null) {
					var queryParams = form.getValues();
					var params = {
							'queryCreateSql.tablenum':queryParams.tablenum,
							'queryCreateSql.tablename':queryParams.tablename,
							'queryCreateSql.remark':queryParams.remark,
							'queryCreateSql.createType':'2',
						};
					Ext.apply(store.proxy.extraParams,params );
				}
			},
			load: function(store, operation, eOpts) {
				   if(isFirstQuery==1 && queryProcSqlStore.getCount()==0){		
					    showInfoMsg("没有查询到您需要的相关数据，请重新查询！"); 					   
				   };
				   isFirstQuery=1;
			},
			autoLoad:true
		}
	});
	
	queryProcSqlStore.loadPage(1);	
	

//	var roleStore = Ext.create('Ext.data.TreeStore', {
//		root : {
//			expanded : true,	
//			id:'0'
//		},
//		default:{expanded : true },
//		proxy:{
//			type:'ajax',
//			url:'../customer/querySqlRoleList!querySqlRoleList.action',
//			actionMethods:'POST',
//			reader:{
//				type:'json',
//				root:'roleThreeVos',	
//			}
//		 },
//		 autoLoad:true
//	});	
//
//	
//	/*加载角色菜单树*/
//	var treePanel = Ext.create('Ext.tree.TreePanel', {
//		store : roleStore,
//		//rootVisible : false,
//		region:'west',
//		collapsible : false,
//		width : 186,
//		border:true,
//		split: true, 
//		minWidth : 186,
//		maxWidth : 186,
//	    split: true,//可以合并
//	    autoScroll: true,
//		//树节点是否可见
//	    rootVisible: false,
//	  //使用vista风格的箭头图标，默认为false
//	    collapseMode:'mini'
//	});
	
	
	/**
	 * 查询结果Grid
	 */
	var grid=Ext.create('Ext.grid.Panel', {
		store: queryProcSqlStore,//创建queryProcSqlStore
		border:false,
		columnLines:true,
		selModel:Ext.create('Ext.selection.CheckboxModel'),//checkbox
		region:'center',//border布局的中间
		columns: [//列
		{xtype: 'rownumberer',
			width:40},
		{	
            dataIndex: 'num',
			text:'序列号',
			hideable:false,	
			width:180,
        },{
            dataIndex: 'tablenum',
			text:'存储过程名',
            width:180,
            menuDisabled:true
        },{
            dataIndex: 'tablename',
			text:'中文名',
            width:120,
            menuDisabled:true
        },{
            dataIndex: 'createSql',
			text:'过程SQL',    
			 menuDisabled:true,
        },{
            dataIndex: 'execTime',
			text:'执行时间',    
			 menuDisabled:true,
			 //xtype : 'datecolumn', format : 'Y-m-d H:i:s',
        },{
            dataIndex: 'lastTime',
			text:'上次执行时间',    
			 menuDisabled:true,
			// xtype : 'datecolumn', format : 'Y-m-d H:i:s',
        },{
            dataIndex: 'remark',
			text:'功能描述',
			 menuDisabled:true,
			 minWidth:240,
            flex:1//整个宽度减去定义过宽度剩余的
        },{
            dataIndex: 'createUser',
			text:'创建人',
			hidden:true,
			menuDisabled:true,
            width:120
        },{
            dataIndex: 'createDate',
			text:'创建时间',
			hidden:true,
			 menuDisabled:true,
			 xtype : 'datecolumn', format : 'Y-m-d H:i:s',
            width:140
        },{
            dataIndex: 'modifyUser',
			text:'修改人',
			hidden:true,
			 menuDisabled:true,
            width:120
        },{
            dataIndex: 'modifyDate',
			text:'修改时间',
			hidden:true,
			 menuDisabled:true,
			 xtype : 'datecolumn', format : 'Y-m-d H:i:s',
            width:140
        }],
        listeners : {
			itemClick : function() {
				Ext.getCmp('sqlContent').setValue("");
				var records = grid.getSelectionModel().getSelection();
				Ext.getCmp('sqlContent').setValue(
					records[0].get('createSql')										
					);
			

			}
		},
		 dockedItems:[{//分页
	 			xtype:'pagingtoolbar',
	 			id: 'querySqlGridPaginId',
	 			store:queryProcSqlStore,
	 			dock:'bottom',
	 			displayInfo : true,
	 			autoScroll : true
	 		}],	
        tbar:[{
			xtype:'button',
			text:'新增',
			iconCls: 'add',
			handler:function(){
				var form = addForm.getForm();
				form.reset();
				var queryCreateSqlModel = new QueryProcSqlModel();
				queryCreateSqlModel.set("id",null);
				form.loadRecord(queryCreateSqlModel);
				form.findField("id").disable();
				form.findField("id").setValue(null);
				form.findField("alter").setHidden(true);
				form.findField("createSql").setHidden(false);
				form.findField("tablenum").setReadOnly(false);
				form.findField("tablename").setReadOnly(false);
				form.findField("remark").setReadOnly(false);
				form.findField("createSql").setReadOnly(false);
				//addForm.getForm().findField("querySqlNo").readOnly=true;
				win.setTitle("新增过程SQL");
				win.show();
				
			}
		},'-',{// '-' 表示分隔符
			xtype:'button',
			text:'删除',
			iconCls: 'del',
			handler:function(){
				var records=grid.getSelectionModel().getSelection();//获取选中集合
				if(records.length == 0){
					showInfoMsg("请选择后再进行操作！");
					return false;
				};
				
				formUrl = '../customer/deleteProcSql.action';
				
				if(records.length > 0){
					var ids = new Array();//定义要删除的id对应后台String[] ids
					for (var j = 0; j < records.length; j++) {
						var querySqlModel = new QueryProcSqlModel();
						querySqlModel.id= records[j].get('id');
						querySqlModel.createType= records[j].get('createType');
						querySqlModel.tablenum= records[j].get('tablenum');
						
						ids.push(querySqlModel);
					}
					confirm("您确定要删除吗？",
							function(){
							var msgWindow=null;
						//保存成功回调函数
							var returnFn = function(json){
								msgWindow.close();
						    	showInfoMsg(json.message);
						    	Ext.getCmp('querySqlGridPaginId').moveFirst();	
						    };	
							
//							var params={'querySqlNos':ids};//传数组到后台
						    var params={'queryCreateSqls':ids};
							msgWindow=showWait();
							requestUmpAjaxJsonParams(formUrl,params,returnFn);	
							
					    	});
				
				
			    }

			}
		}
//		,'-',{//提示 '->' 让按钮居右
//			xtype:'button',
//			text:'修改',
//			iconCls: 'edit',
//			handler:function(){
//				var records=grid.getSelectionModel().getSelection();//获取选中集合
//				if(records.length == 0){
//					showInfoMsg("请选择一条记录！");
//					return false;
//				};
//				if(records.length != 1){
//					showInfoMsg("只能选择一条记录！");
//					return false;
//				};
//				var form=addForm.getForm();
//				var querySqlModel = new QueryProcSqlModel();
//				form.loadRecord(querySqlModel);
//				form.findField("id").enable();//修改的时候id为启用
//				form.findField("id").setValue(records[0].get("id"));
//				form.findField("id").setReadOnly(true);
//				form.findField("createType").setValue(records[0].get("createType"));
//				form.findField("createType").setReadOnly(true);
//				form.findField("tablenum").setValue(records[0].get("tablenum"));
//				form.findField("tablenum").setReadOnly(true);
//				form.findField("tablename").setValue(records[0].get("tablename"));
//				form.findField("tablename").setReadOnly(true);
//				form.findField("remark").setValue(replaceAll(records[0].get("remark")? records[0].get("remark"):''));
//				form.findField("remark").setReadOnly(true);
//				form.findField("createSql").setValue(records[0].get("createSql"));
//				form.findField("alter").setValue(records[0].get("alter"));
//				form.findField("alter").setHidden(false);
//				form.updateRecord(querySqlModel);
//				form.findField("createSql").setReadOnly(true);
//				form.findField("createSql").setHidden(true);
//				win.setTitle("修改自定义过程SQL");
//				win.show();
//				
//				
//			}
//		}
		]
	});
		
	function saveQuerySql(){
		var msgWindow = null;
		var form=addForm.getForm();
		var returnFn = function(json){
	    	showInfoMsg(json.message);
	    	Ext.getCmp('querySqlGridPaginId').moveFirst();
	    	if(json.returnCode==1){
	    		win.hide();
	    	}
	    };	
	    
	    var formUrl = '../customer/saveorModifyqueryCreateSql.action';
		var querySqlModel=form.getRecord();
		var querySqlData;
		form.updateRecord(querySqlModel);
		querySqlData = {'queryCreateSql':querySqlModel.data};
		msgWindow=showWait();
		requestUmpAjaxJsonParams(formUrl,querySqlData,returnFn);
	}
	
	
	//验证表单是否提交成功
	function formCheck(){
		var msg="表单验证失败，请重新输入";
		showInfoMsg(msg,function(){
			return false;
		});
	};	
	
	
	
	/**
	* 新增查询SQL Form
	*/
	var addForm=Ext.create('Ext.form.Panel',{
		id:'addForm',
		layout : {
			type : 'table',	//table布局
			columns : 1	//列数
		},
		defaults: {//统一设置宽、居右
			margin:'5 5 5 5',//间距
			labelWidth:60,
			border:false,
			labelAlign: 'right',
			//width : 200,
			//height:  100,
		},
		defaultType:'textfield',//默认类型
		frame:true,
		height:400,
		region:'center',
		buttonAlign:'center',
		items:[{
			fieldLabel: 'ID',
			xtype:'textarea',
			hidden:true,
			width:380,
			value:'',
			name:'id'
		},{
			fieldLabel: 'createType',
			xtype:'textarea',
			hidden:true,
			width:380,
			value:'2',
			name:'createType'
		},{
			fieldLabel: '存储过程名',
			xtype:'textfield',
			width:380,
			colspan: 2,
			height:25,
			allowBlank:false,//判断不能为空
			name:'tablenum'
		},{
			fieldLabel: '中文名',
			xtype:'textfield',
			width:380,
			colspan: 2,
			height:25,
			allowBlank:false,//判断不能为空
			name:'tablename'
		},{// “备注”字段长度限制不是500个字，且超出范围提交时的提示信息不对
			fieldLabel: '功能描述',
			xtype:'textarea',
			width:380,
			height:140,
			maxLength:500,
			maxLengthText:'功能描述不能超过500个字',//过长时自定义提示
			colspan: 2,
			name:'remark'
		},{
			fieldLabel: '过程SQL',
			xtype:'textarea',
			width:380,
			height:140,
			colspan: 2,
			allowBlank:false,//判断不能为空
			name:'createSql'
		},{
			fieldLabel: '修改Proc',
			xtype:'textarea',
			width:380,
			height:140,
			colspan: 2,
			hidden:true,
			name:'alter',
			value:''
		}],
		buttons:[{
			xtype:'button',
			text:'保存',
			width:80,
			handler:function(){
				var form=addForm.getForm();
				if(form.isValid()){//表单验证是否可提交
					if(checkformData())
						saveQuerySql();
				}else{//表单验证不通过
					formCheck();
				}
			
			}
		},{
			xtype:'button',
			text:'关闭',
			width:80,
			handler:function(){
				win.close();
			}
		}]
	});
	
	function checkformData(){
		var form=addForm.getForm();
		var id=form.findField("id").getValue();
		if(id == null || id == ""){
			var tablenum = form.findField("tablenum").getValue().toUpperCase();
			var createSql= form.findField("createSql").getValue().toUpperCase();
			
			if(createSql.indexOf('CREATE PROCEDURE') != 0){
				showInfoMsg("创建存储过程语法错误！开头必须包含[CREATE PROCEDURE]");
				return false;
			}
			if(createSql.indexOf(tablenum) <= 0){
				showInfoMsg("过程名和创建过程SQL不一致！");
				return false;
			}
		}else{
			var alterSql= form.findField("alter").getValue();
			if(alterSql == null || "" == alterSql){
				showInfoMsg("修改proc不能为空");
				return false;
			}
//			if(alterSql.indexOf('ALTER TABLE') != 0){
//				showInfoMsg("修改表语法错误！开头必须包含[ALTER TABLE]");
//				return false;
//			}
		}
		return true;
	}
		
	var updateData=false;
	//比较是否修改
	function isUpdateData(){
		var form=addForm.getForm();		
		//表单验证是否可提交
		// 在修改 的时候 验证数据是否已经修改
		var querySqlModel=form.getRecord();
		var sql=form.findField("sql").getValue();
		var tableHead=form.findField("tableHead").getValue();
		var param=form.findField("param").getValue();
		var remark=form.findField("remark").getValue();
		if(  compare(querySqlModel.get("sql"),sql)
		   &&compare(querySqlModel.get("tableHead"),tableHead) 
		   &&compare(querySqlModel.get("param"),param)
		   &&compare(querySqlModel.get("remark"),remark)		
		){
			//没有修改 提示信息并返回
			updateData=false;
		}else{//已经修改 把修改的数据传入后台
			updateData=true;
		};
	}
	//比较两个字符串是否相等
	function compare(str1,str2){
		if(str1==str2){
			return true;
			
		}else{
			return false;
		};
		
	};
	
	//关闭窗口
	function close(){
//		var form=addForm.getForm();
//		var querySqlModel=form.getRecord();
//		var id=form.findField("id").getValue();
//		if(!compare(id,"")&&!compare(id,null)&&form.isValid()){//只有修改中验证是否已修改数据
//			isUpdateData();
//			if(updateData){
//				//没有修改 提示信息并返回
//				var msg="数据有修改，是否保存";
//				confirm(msg,function(){
//					saveQuerySql();
//				},function (){//返回修改页面
//					return false;
//				});
//				
//			}
//		}	
	};	
	
	//添加pannel
	var addPanel =Ext.create('Ext.form.Panel',{
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		buttonAlign:'center',
		items: [addForm]
	});
	
	
	if(!win){//需要定义全局变量,如果不存在就创建一个，保证只创建一次
		win=Ext.create('Ext.window.Window',{
			width:420,
			height:480,
			modal:true,//模式窗口（带蒙层，后面不可操作）
			layout:'fit',
			closeAction:'hide',//关闭方式 （默认为销毁）
			items:[addPanel],
			listeners:{
		    	close:function(){
		    		close();
		    	}
		    }
		});
	}
	
	var bot = Ext.create('Ext.form.FieldSet', {
		 layout: {
	            type: 'table',
	            columns: 1
	        },
		defaultType:'textarea',//默认类型
		frame:true,
		
		height:220,
		region : 'south',
		items : [{
			xtype : 'label',
			text: '过程SQL'
		},{
			xtype : 'textarea',
			id : 'sqlContent',
			   width: document.body.clientWidth-30,
			height:220,
			readOnly : true
		}
		]

	});
	//整个页面布局
	var viewport = Ext.create('Ext.Viewport', {
		layout: {
			type: 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding: 5
		},
		items: [form,grid,bot]
	});
	
});