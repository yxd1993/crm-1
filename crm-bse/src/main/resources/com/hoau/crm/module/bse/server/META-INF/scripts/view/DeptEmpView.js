Ext.define('CRM.view.bse.DeptEmpTreePanel', {
	extend:'Ext.tree.TreePanel',
	title:'部门结构',
	store:Ext.create('CRM.store.bse.UserMenuStore'),
	region : 'west',
	collapsible : true,
	width : 250,
	border:false,
	split: true,
	minWidth:250,
	maxWidth:250,
    split:true,//可以合并
	//动画效果
	animate:true,
    autoScroll: true,
    reserveScrollbar: true,
    containerScroll:true,
	//树节点是否可见
    rootVisible: false,
    //使用vista风格的箭头图标，默认为false
    useArrows: true,
	listeners:{
		'itemclick' : function(node, record,item,index,e,eOpts){
			//点击菜单加载页面
			this.up().getQueryForm().getForm().findField('deptcode').setValue(record.get("id"));
			this.up().getQueryForm().getForm().findField('deptname').setValue(record.get("text"));
		}
	}
});

Ext.define('CRM.view.bse.QueryForm', {
	extend : 'Ext.form.Panel',
	id : 'queryForm',
	frame : true,
	title : '用户查询',
	height : 113,
	collapsible : true,
	layout: 'column',
	region : 'north',
	titleCollapse:true,
	defaults : {
		margin : '8 10 5 10',
		labelWidth : 100,
		columnWidth : 0.23,
		labelAlign : 'right'
	},
	defaultType : 'textfield',
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.items = [{
			name : 'userName',
			fieldLabel : '账号',
			xtype : 'textfield'
		},{
			name : 'empName',
			fieldLabel : '职员姓名',
			xtype : 'textfield'
		},{
			name : 'roleCode',
			fieldLabel : '角色编码',
			xtype : 'textfield'
		},{
			name : 'deptname',
			fieldLabel : '部门名称',
			xtype : 'textfield',
			readOnly : true
		},{
			name : 'deptcode',
			fieldLabel : '部门编码',
			hidden : true,
			xtype : 'textfield',
			readOnly : true
		}],
		me.buttons = [{
			text : '查询',
			handler : function(){
				if (me.getForm().isValid()) {
					me.up().getEmployeeGrid().getPagingToolbar().moveFirst();;
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
 * 用户信息表格
 */
Ext.define('CRM.view.bse.EmployeeGrid',{
	extend : 'Ext.grid.Panel',
	frame : true,
	autoScroll : true,
	width : '100%',
	height : document.documentElement.clientHeight-150,
	stripeRows : true, // 交替行效果
	region: 'center',
	emptyText : '查询结果为空', //查询结果为空
	viewConfig:{
		enableTextSelection:true
	},
	selModel : Ext.create('Ext.selection.CheckboxModel'),
	pagingToolbar : null,
	getPagingToolbar : function () {
		if (this.pagingToolbar == null) {
			this.pagingToolbar = Ext.create('Ext.toolbar.Paging', {
					store : this.store,
					pageSize : 20
				});
		}
		return this.pagingToolbar;
	},
	tbar: [{
        text: '角色配置',
        action : 'update',
        handler : function(){
        	var selectArr = employeeGrid.getSelectionModel().getSelection();
        	if(selectArr.length == 0){
    			Ext.MessageBox.alert('提示','请选择需要配置的用户');
    			return;
    		}else if(selectArr.length > 1){
    			Ext.MessageBox.alert('提示','只能选择一个用户进行配置');
    			return;
    		}
        	var win = Ext.create("CRM.view.bse.UserEditWindow"); 
        	win.getRoleChooseGrid().getStore().load({
    			params : {
    				userCode: selectArr[0].getData().userName	            				
    			}
    		});
        	win.getUserRoleGrid().getStore().load({
    			params : {
    				userCode: selectArr[0].getData().userName	            				
    			}
    		});
            win.show();
        }
    }],
	constructor : function(config){
		var me = this,
		cfg = Ext.apply({}, config);
		me.columns = [{text : '序号',	width : 60,	xtype : 'rownumberer', align : 'center'},
		{
			dataIndex : 'id',
			hidden : true
		},{
			text : '账号', 
			dataIndex : 'userName',
			align : 'center',
			flex : 1
		},{
			text : '工号', 
			dataIndex : 'empCode',
			align : 'center',
			flex : 1
		},{
			text : '姓名', 
			dataIndex : 'empName',
			align : 'center',
			flex : 1
		},{
			text : '部门名称', 
			dataIndex : 'deptName',
			align : 'center',
			flex : 1
		},{
			text : '岗位名称', 
			dataIndex : 'jobName',
			align : 'center',
			flex : 1
		}],
		me.store = Ext.create('CRM.store.bse.UserStore', {
			autoLoad : true, //不自动加载
			pageSize : 20,
			listeners : {
				beforeload : function (store, operation, eOpts) {
					var queryForm = me.up().getQueryForm();
					if (queryForm != null) {
						var codes = [];
						if(!Ext.isEmpty(queryForm.getForm().findField('roleCode').getValue())){
							codes.push(queryForm.getForm().findField('roleCode').getValue());
						}
						var params = {
							'userEntity.userName' : queryForm.getForm().findField('userName').getValue(),
							'userEntity.empEntity.empName' : queryForm.getForm().findField('empName').getValue(),
							'userEntity.empEntity.deptcode' : queryForm.getForm().findField('deptcode').getValue(),
							'roleCodes' : codes
						}
						Ext.apply(store.proxy.extraParams, params);  
					}
				}
			}
		});
		me.bbar = me.getPagingToolbar();
		me.callParent([cfg]);
	}
});

/**
 * 选中角色列表中的一行或多行移动至另一个角色列表中
 * @param grid 选中的列表
 * @param addStore 增加记录的store
 * @param removeStore 删除记录的store
 * @name select
 */
function userSelect(grid,addStore,removeStroe) {
	var _records = grid.getSelectionModel().getSelection();
	if (_records == null||_records.length<=0) {
		return false;
	}
	Ext.each(_records, function(item) {
		addStore.insert(0,item);
	});
	removeStroe.remove(_records);
	return true;
};

/**
 * 角色列表中的所有记录移动至另一个角色列表中
 * @param grid 选中的列表
 * @param addStore 增加记录的store
 * @param removeStore 删除记录的store
 * @name select
 */
function allUserSelect(grid,addStore,removeStore) {
	var count = removeStore.getCount();
	if(count==null||count<=0){
		return false;
	}
	removeStore.each( function(_record) {
		addStore.insert(0,_record);
	},removeStore);
	// 从待选运单列表中移除该记录
	addStore.each( function(_record) {
		removeStore.remove(_record);
	},addStore);
	return true;
};

/**
 * 待分配角色选择列表
 */
Ext.define('CRM.view.bse.UserRoleChooseGrid', {
	extend : 'Ext.grid.Panel',
	height:230,
	frame: true,
	title : '待分配角色选择列表',
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	// 列模板
	columns : [{
		text : '角色名称',
		flex: 1,
		dataIndex : 'roleName'
	}, {
		text : '角色描述',
		flex: 1,
		dataIndex : 'roleDesc'
	} ],
	queryField: null,
	getQueryField: function(){
		var me = this;
		if(me.queryField==null){
			me.queryField = Ext.create('Ext.form.field.Text');
		}
		return me.queryField;
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.selModel = Ext.create("Ext.selection.CheckboxModel");
		me.store = Ext.create('CRM.store.bse.roleStore',{
			proxy : {
				type : 'ajax',
				actionMethods : 'POST',
				url : 'queryLeftRoles.action',
				reader : {
					type : 'json',
					root : 'roleList'
				}
			}
		});
		me.callParent([cfg]);
	}
});

/**
 * 已分配角色选择列表
 */
Ext.define('CRM.view.bse.UserRoleGrid', {
	extend : 'Ext.grid.GridPanel',
	title : '已分配角色',
	height:230,
	frame: true,
    sortableColumns:false,
    enableColumnHide:false,
    enableColumnMove:false,
	stripeRows : true, // 交替行效果
	selType : "rowmodel", // 选择类型设置为：行选择
	// 列模板
	columns : [{
		text : '角色名称',
		flex: 1,
		dataIndex : 'roleName'
	}, {
		text : '角色描述',
		flex: 1,
		dataIndex : 'roleDesc'
	} ],
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.selModel = Ext.create("Ext.selection.CheckboxModel");
		me.store = Ext.create('CRM.store.bse.roleStore',{
			proxy : {
				type : 'ajax',
				actionMethods : 'POST',
				url : 'queryAuthedRoles.action',
				reader : {
					type : 'json',
					root : 'roleList'
				}
			}
		});
		me.callParent([cfg]);
	}
});

/**
 * 移动按钮
 */
Ext.define('CRM.view.bse.userButtonPanel', {   
	extend : 'Ext.panel.Panel',
	height:230,
	buttonAlign : 'center',
	defaults: {
		margin : '10 0 0 0',
		minWidth: 63,
		xtype: 'button'
	},
	items : [{
		text : '&gt;&gt;',
		cls: 'first_btn',
		handler :  function() {
			var window = this.up('window'),
				roleChooseGrid = window.getRoleChooseGrid(),
				userRoleGrid = window.getUserRoleGrid();
			var choose_store = userRoleGrid.getStore();
			var noChoose_store = roleChooseGrid.getStore();
			allUserSelect(roleChooseGrid,choose_store,noChoose_store);
			allUserSelect(roleChooseGrid,choose_store,noChoose_store);
		}
	}, {
		text : '&nbsp;&gt;&nbsp;',
		cls: 'first_btn',
		handler : function() {
			var window = this.up('window'),
				roleChooseGrid = window.getRoleChooseGrid(),
				userRoleGrid = window.getUserRoleGrid();
			choose_store = userRoleGrid.getStore();
			userSelect(roleChooseGrid,choose_store,roleChooseGrid.getStore());
			/*var _records = roleChooseGrid.getSelectionModel().getSelection();
			if (_records == null||_records.length<=0) {
				Ext.MessageBox.alert('提示', '请选择角色');
				return false;
			}
			//将左边已选的角色code构成一个数组
			var lchoose = new Array();
			Ext.each(_records, function(item) {
				lchoose.push(item.get('roleCode'));
			});
			//将右边已选的角色code构成一个数组
    		var chooseRoles = new Array();
    		choose_store = userRoleGrid.getStore();
    		choose_store.each( function(_role) {
    			chooseRoles.push(_role.get('roleCode'));
    		},choose_store);
    		//如果右边已选的角色code为空，并且左边选择为单个
    		if(chooseRoles.length<1 && lchoose.length==1){
    			userSelect(roleChooseGrid,choose_store,roleChooseGrid.getStore());
    		}else{
    			//如果右边已选的角色code为空，并且左边选择为多个，就检查右边已选的角色的是否有冲突
    			if(chooseRoles.length<1 && lchoose.length > 1){
    				chooseRoles = lchoose;
    			}
    			userSelect(roleChooseGrid,choose_store,roleChooseGrid.getStore());
    		}*/
		}
	}, {
		text : '&nbsp;&lt;&nbsp;',
		cls: 'first_btn',
		handler : function() {
			var window = this.up('window'),
				roleChooseGrid = window.getRoleChooseGrid(),
				userRoleGrid = window.getUserRoleGrid();
			userSelect(userRoleGrid,roleChooseGrid.getStore(),userRoleGrid.getStore())
		}
	}, {
		text : '&lt;&lt;',
		cls: 'first_btn',
		handler : function() {
			var window = this.up('window'),
				roleChooseGrid = window.getRoleChooseGrid(),
				userRoleGrid = window.getUserRoleGrid();
			allUserSelect(userRoleGrid,roleChooseGrid.getStore(),userRoleGrid.getStore())
		}
	}]
});

/**
 * 用户编辑窗口
 */
Ext.define('CRM.view.bse.UserEditWindow',{
	extend : 'Ext.window.Window',
	closable : true,
	modal : true,
	resizable:false,
	title : '用户角色分配',
	height:300,
    width:700,
	/*userForm : null,
    getUserForm : function(){
    	if(this.userForm==null){
    		this.userForm = Ext.create('Dpap.authorization.UserForm');
    	}
    	return this.userForm;
    },*/
	roleChooseGrid : null,
	getRoleChooseGrid : function(){
		if(this.roleChooseGrid==null){
			this.roleChooseGrid = Ext.create('CRM.view.bse.UserRoleChooseGrid',{
				columnWidth: .5
			});
		}
		return this.roleChooseGrid;
	},
	roleButtonPanel : null,
	getRoleButtonPanel : function(){
		if(this.roleButtonPanel==null){
			this.roleButtonPanel = Ext.create('CRM.view.bse.userButtonPanel',{
				width: 90,
				style : 'padding:60px 10px 0px 10px;border:none'
			});
		}
		return this.roleButtonPanel;
	},
	userRoleGrid : null,
	getUserRoleGrid : function(){
		if(this.userRoleGrid==null){
			this.userRoleGrid = Ext.create('CRM.view.bse.UserRoleGrid',{
				columnWidth: .5
			});
		}
		return this.userRoleGrid;
	},
    userPanel : null,
    getUserPanel : function(){
    	var me = this;
    	if(this.userPanel==null){
    		this.userPanel = Ext.create('Ext.panel.Panel',{
    			frame: true,
    			height:380,
    			layout:'column',
    			items : [ me.getRoleChooseGrid(), me.getRoleButtonPanel(), me.getUserRoleGrid() ]
    		});
    	}
    	return this.userPanel;
    },
    constructor : function(config) {
		var me = this, 
			cfg = Ext.apply({}, config);
		me.items = [ /*me.getUserForm(), */me.getUserPanel()];
		me.listeners = {
			beforehide : function(win, Opts) {
				me.getRoleChooseGrid().getQueryField().reset();
			}
		};
		me.bbar = ['->',{
	        text: '提交',
	        align : 'right',
	        handler : function(){
	        	var selectArr = employeeGrid.getSelectionModel().getSelection();
	        	var usreName = selectArr[0].getData().userName;
	        	var win = me;
	        	// 将右边已选的角色构成数组
	        	var chooseRoles = new Array();
	    		var choose_store = win.getUserRoleGrid().getStore();
	    		choose_store.each( function(_role) {
	    			chooseRoles.push(_role.get('roleCode'));
	    		},choose_store);
	    		// 参数
	        	var params = {};
	        	var userEntity = {};
	        	userEntity.userName = usreName;
	        	params.roleCodes = chooseRoles;
	        	params.userEntity = userEntity;
	    		// AJAX请求
	    		crm.requestJsonAjax('insertUserRole.action', params, 
	    				function(response){
	    					Ext.MessageBox.alert('提示','用户角色信息保存成功');
	    					employeeGrid.getStore().reload();
	    					win.close();
	    				}, 
	    				function(response){Ext.MessageBox.alert('提示', '用户角色信息保存失败');});
	    		//关闭WIN
	            win.close();
	        }
	    }];
		me.callParent([cfg]);
	}
});

var employeeGrid = null;
Ext.onReady(function(){
	var deptEmpTreePanel = Ext.create("CRM.view.bse.DeptEmpTreePanel");
	var queryForm = Ext.create("CRM.view.bse.QueryForm");
	employeeGrid = Ext.create("CRM.view.bse.EmployeeGrid");
	Ext.create('Ext.Viewport', {
        header: {
            titlePosition: 2,
            titleAlign: 'center'
        },
        border: "0 0 0 0",
        renderTo:Ext.getBody(),
        layout: {
            type: 'border'
        },
        getQueryForm : function(){
        	return queryForm;
        },
        getEmployeeGrid : function(){
        	return employeeGrid;
        },
        items: [
			deptEmpTreePanel,
			queryForm,
            employeeGrid
		]
    });
});