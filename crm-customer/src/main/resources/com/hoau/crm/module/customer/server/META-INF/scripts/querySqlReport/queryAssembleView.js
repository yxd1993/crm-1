
/**
 * 查询SQL报表Model
 */
Ext.define('QuerySqlModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id'},//ID  
        {name: 'createDate',type:'date',dateFormat:'time'},//日期
        {name: 'number'},//SQL编号
        {name: 'sql'},//供应商名称
        {name: 'tableHead'},//表头
        {name: 'param'},//sql参数
        {name: 'myColumn'},//表头结果集 json格式字符串
        {name: 'remark'}//备注sql功能
        
   
    ]
});

/**
 * 动态执行sql获取结果集model
 * */
Ext.define('QueryResultModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'str0'},
        {name: 'str1'}, 
        {name: 'str2'},   
        {name: 'str3'},   
        {name: 'str4'},   
        {name: 'str5'},   
        {name: 'str6'},   
        {name: 'str7'},   
        {name: 'str8'},   
        {name: 'str9'},   
        {name: 'str10'},   
        {name: 'str11'},   
        {name: 'str12'},   
        {name: 'str13'},   
        {name: 'str14'},   
        {name: 'str15'},   
        {name: 'str16'},   
        {name: 'str17'},   
        {name: 'str18'},   
        {name: 'str19'}               
    ]
});

/**
 * 动态结果集store
 * */
Ext.define('QueryResultStore', {
	extend : 'Ext.data.Store',
	model : 'QueryResultModel',
	// 数据库取数据方法，暂时屏蔽
	pageSize : 20,// 分页条数
	proxy : {
		type : 'ajax',
		url : "../customer/execSqlAll.action",
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'exportQuerys',
			totalProperty : 'totalCount'
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}

});

/**
 * 查询SQL报表Store
 */
Ext.define('QuerySqlStore', {
	extend : 'Ext.data.Store',
	model : 'QuerySqlModel',
	// 数据库取数据方法，暂时屏蔽
	pageSize : 10,// 分页条数
	proxy : {
		type : 'ajax',
		url : '../customer/showQuerySql.action',
		actionMethods : 'POST',
		reader : {
			type : 'json',
			root : 'sqlLists',
			totalProperty : 'totalCount'
		}
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.callParent([ cfg ]);
	}

});



Ext.onReady(function() {
		  var isFirstQuery = 0;	 //判断是否第一次查询sql
		  var vSql;
		  var vQueryParam;
		  var vTableHead;
		  
		  var maxNumber = 500000; //excel最大导出条数
		  
    /**
     * 执行导出数据
     * */
	function exportExce(exportUrl,vSql,vQueryParam,vTableHead,startNum,limitNum){	     
	
					if (!Ext
								.fly('downloadAttachFileForm')) {
							var frm = document
									.createElement('form');
							frm.id = 'downloadAttachFileForm';
							frm.style.display = 'none';
							frm.enctype = "multipart";
							document.body.appendChild(frm);
						}
						Ext.Ajax
								.request({
									url : exportUrl,
									form : Ext
											.fly('downloadAttachFileForm'),
									params:{
												id:vSql,
												//sql:vSql,
												queryParam:vQueryParam,
												tableHead:vTableHead,
												startNumber:startNum,
												limitNumber:limitNum
												},
									isUpload : true
								});
						
	}

    /**
     * 点击查询调用的查询获取后台数据方法
     * */
	function getData(querygrid,vSql,vQueryParam,vTableHead){			
		Ext.Ajax.request({
			url:"../customer/execSqlAll.action",
			params:{
				sql:vSql,
				queryParam:vQueryParam,
				tableHead:vTableHead
				},									
			success:function(response){					
				var result = Ext.decode(response.responseText);
				if(result.resultMessage.returnCode == 1){
				    createGird(querygrid,result.exportQuerys,result.fields,result.tableHeads);
				    hideWait();
				}else if(result.resultMessage.returnCode == 2){
					showInfoMsg(result.resultMessage.message,function(){});
				
				}else{
					showInfoMsg("系统异常",function(){});
				}
			}
		});	
	}	

	
	 /**
	  * ExtJs 根据Param 创建自动添加查询控件
	  * @param panelId  面板id
	  * @param list  配置参数（json格式参数）
	  * @param fun 点击查询按钮调用的函数
	  * @author 张贞献
	  * @时间 2013-6-27
	  */		 
	function createQueryPanel(panelId,list,fun){

		if(list==null||list==""){                 //严重参数是否为空
			addButton(panelId,"查询",fun);
		}else{			
		var json=Ext.decode(list);
		for(var i = 0;i<json.length;i++){
			if(json[i].type=="D"){
				addTimeField(panelId,json[i].labelName,json[i].id);
			}else if(json[i].type=="T"){
				addTextField(panelId,json[i].labelName,json[i].id);
			}else if(json[i].type=="M"){
				createComboFixed(panelId,json[i].labelName,json[i].id,json[i].map);
			}else if(json[i].type == "C"){
				 var url = "../customer/queryCombo.action";
				createCombo(panelId,json[i].labelName,json[i].id,json[i].map,json[i].bindIdSql,url);
			}								
		}
		addButton(panelId,"查询",fun);
	  }
	}

	/**
	 * 获取SQL的store
	 * */	
	var store=Ext.create('QuerySqlStore',{
			listeners: {
				beforeload : function(store, operation, eOpts) {
					if (Topform!= null) {
						var queryParams = Topform.getValues();
						var params = {
								'queryAssemble.number' : queryParams.number,
								'queryAssemble.remark' : queryParams.remark,
							};
						Ext.apply(store.proxy.extraParams, params);
//						Ext.apply(operation, {
//							params : {
//								'queryAssemble.number' : queryParams.number,
//								'queryAssemble.remark' : queryParams.remark,
//							}
//						});
					}
				},
				load : function(store, operation, eOpts){
					if (isFirstQuery == 1 && store.getCount() == 0) {
						showInfoMsg("没有查询到您需要的相关数据，请重新查询！");
					}
					isFirstQuery = 1;	
					clearPanel('queryformTop');  
				    nullGrid(querygrid);
				    Ext.getCmp('export').hide();
				    
				}
			},
			autoLoad : true
		});
		
	/**
	 * 动态结果集store
	 * */	
	var queryStore=Ext.create('QueryResultStore',{
			listeners: {
					beforeload : function(store, operation, eOpts) {
					var records = sqlgrid.getSelectionModel().getSelection();
//					Ext.apply(operation, {
//						params:{
//							sql:records[0].get('sql'),
//							queryParam:getAllParam(records[0].get('param')),
//							tableHead:records[0].get('tableHead')
//							},
//					});
					var params = {
							id : records[0].get('id'),
							//sql:records[0].get('sql'),
							queryParam:getAllParam(records[0].get('param')),
							tableHead:records[0].get('tableHead')
						};
					Ext.apply(store.proxy.extraParams,params);
					
				},
				load : function(store, operation, eOpts){
					if (store.getCount() == 0) {
						showInfoMsg("没有查询到您需要的相关数据，请重新查询！");
					}else{
						hideWait();
					}	
					//动态改变列
					querygrid.reconfigure(queryStore,
		    				Ext.decode(sqlgrid.getSelectionModel().getSelection()[0].get('myColumn')));
				}
			}
		});
	

			
	/**
	 * 查询sql的顶部查询面板
	 * */		 
	var Topform = Ext.create('Ext.form.Panel',{
			    frame:true,
				height:45,
				region:'north',
				layout : {
					type : 'table', // table布局
					columns : 10    // 列数				
				},
				defaults : {// 统一设置宽、居右
					margin : '5 5 5 5',// 间距
					labelWidth : 80,
					border : false,
					labelAlign : 'right',
					width : 180
				},            
				items:[	{
							xtype : 'textfield',
							fieldLabel : '报表编号',
							name : 'number',
							id : 'number',											
							maxLength:15,
							maxLengthText:'报表编码不能超过15个字',//过长时自定义提示	
							labelWidth : 60,
							width : 150
						}, {
							xtype : 'textfield',
							fieldLabel : '功能描述',
							name : 'remark',
							id : 'remark',
							maxLength:15,
							maxLengthText:'功能描述不能超过15个字',//过长时自定义提示	
							labelWidth : 100,
							width : 280
						},
						{
							xtype:'displayfield',
							width:60
						},{
							xtype : 'button',
							text : '查询',
							width : 60,
							border : true,
							handler : function() {													
								store.loadPage(1);
						    }
						}
				       ]
			  }
			);
						
	/**
	 * 显示查询到的sql 表格面板
	 * */		
	var sqlgrid = Ext.create('Ext.grid.Panel', {
				store : store,//创建Store
				border : false,
				columnLines : true,
				layout:'fit',			
				region : 'center',//border布局的中间
				columns : [//列
				{
					xtype : 'rownumberer',
					text: "序号",
					width : 60				
				}, {
					dataIndex : 'number',
					text : '报表编号',
					width : 180
				}, {
					dataIndex : 'createDate',
					text : '报表创建时间',
				//	renderer : formatUmpDatetoMin,
					width : 150,
					xtype : 'datecolumn', 
					format : 'Y-m-d H:i:s'
				}, {
					dataIndex : 'remark',
					text : '功能描述',
					width : 180,
					flex : 1  //整个宽度减去定义过宽度剩余的
				}],
				listeners : {
					itemClick : function() {
						var  selectParam = "";
						var records = sqlgrid.getSelectionModel().getSelection();
						selectParam =records[0].get('param');
						//初始化查询面板
					    clearPanel('queryformTop');  
					    nullGrid(querygrid);					    
					    //根据参数给查询面板添加条件控件
					     createQueryPanel('queryformTop',selectParam,function(){
					     showWait();
				    	if(!checkAllParam(selectParam)){
				    		showInfoMsg("查询条件参数不能为空！");
				    	}else{
				    		
				    		  queryStore.loadPage(1);
				    		  Ext.getCmp('export').show();				    	
				    	 }						    
					    }
					  );						
					Ext.getCmp('export').show();
				  }
				},
				dockedItems : [ {//分页
					xtype : 'pagingtoolbar',
					store : store,
					dock : 'bottom',
					displayInfo : true,
					autoScroll : true
				} ],			
			});
		
		/**
		 * 动态创建查询控件面板
		 * */
		var queryformTop = Ext.create('Ext.form.Panel',{
			id:'queryformTop',
		    frame:true,
			height:45,
			region:'north',
			layout : {
				type : 'table', // table布局
				columns : 10
			// 列数
			},
			defaults : {// 统一设置宽、居右
				margin : '5 5 5 5',// 间距
				labelWidth : 50,
				border : false,
				labelAlign : 'right',
				width : 180
			},              
			items:[]
		
		  }
		);
		
		/**
		 *动态获取结果集面板 
		 * */
		var querygrid = Ext.create('Ext.grid.Panel', {
			    id:'querygrid',
				border : false,
				columnLines : true,
				layout:'fit',			
				region : 'center',//border布局的中间
				columns : [],
				dockedItems : [ {//分页
					xtype : 'pagingtoolbar',
					store : queryStore,
					dock : 'bottom',
					displayInfo : true,
					autoScroll : true
				} ],		
				tbar : [ {
					xtype : 'button',
					id:'export',
					text : '导出',
					hidden:true,
					iconCls : 'export',
					handler : function() {
																						
					  url ='../customer/querySqlExcelPort!exportExcel.action'
					  var  selectParam = "";
					  var records = sqlgrid.getSelectionModel().getSelection();
					  selectParam =records[0].get('param');
						if(!checkAllParam(selectParam)){
				    		showInfoMsg("请先查询后再导出！")
				    	}else if(querygrid.getStore().getCount()==0){
				    		showInfoMsg("当前没有要导出的数据！")
				    	}else{				    	
				    		var totalNumber = queryStore.getTotalCount()	;
				    		var fileNumber =  Math.floor(totalNumber%maxNumber==0?
				    				totalNumber/maxNumber:
				    					totalNumber/maxNumber+1);
				    		var msg = "当前导出数据量为:"+totalNumber;		

				    		confirm(msg,function(){
				    			exportExce(url,
				    					records[0].get('id'),
							    		//records[0].get('sql'),
							    		getAllParam(selectParam),
							    		records[0].get('tableHead'),
							    		0,
							    		totalNumber);
					 					    		
				    		},function(){
			    				return false
		    				});				   			
				    	}
					
					}
				} ]
			});
			
			/**
			 *动态查询结果集面板
			 * */
			var queryform = Ext.create('Ext.form.Panel',{
				region:'center',
				id:'queryform',
				 layout:'border',
				 region:'center',
				items:[queryformTop,querygrid]
				
			});
			
           /**
            * SQL查询面板
            * */
			 var form = Ext.create('Ext.form.Panel',{
				 title:"报表查询",
				 layout:'border',
				 region:'north',				 
				 frame:true,
				 height:280,
				  collapsible: true,
	              animCollapse: true,
	              titleCollapse:true,
				 items:[Topform,sqlgrid]
			 });
			
			//整个页面布局
		var viewport = Ext.create('Ext.Viewport', {
			layout : {
				type : 'border',//用border布局 ，把页面分为上下部分 form为上、grid为下
				padding : 5
			},
			 frame:true,
			items : [ form,queryform ]
		});

});
