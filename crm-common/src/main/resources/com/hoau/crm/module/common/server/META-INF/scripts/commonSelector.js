//下拉单选框
Ext.define('Crm.commonSelector.CommonCombSelector', {
	extend : 'Hoau.commonselector.DynamicComboSelector',
	minChars : 0,
	isPaging : false,// 分页
	isEnterQuery : true,// 回车查询
	listWidth: this.width,// 设置下拉框宽度
	active : null,
	myActive : 'Y',
	record : null,
	displayField : null,
	valueField : null, 
	displayField : null,// 显示名称
	valueField : null,// 值
	queryParam : null,// 查询参数
	realValue : null,//实际的VALUE，解决原来的输入BUG
	realName : null,//实际的realName，解决原来的输入BUG
	getRealValue : function(){
		return this.realValue;
	},
	setRealValue : function(realValue){
		this.realValue = realValue;
	},
	getRealName : function(){
		return this.realName;
	},
	setRealName : function(realName){
		this.realName = realName;
	},
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.getModel());
		m.set(key, displayText);
		m.set(value, valueText);
		me.store.loadRecords([m]);
		me.setValue(valueText);
		me.setRealValue(valueText);
		me.setRealName(displayText);
	},
	getBlur:function(ths, the, eOpts){
		if (Ext.isEmpty(ths.value)) {
			ths.setRawValue(null);
		}
	},
	getBeforeLoad : function(store, operation, e) {
		var me = this;
		var searchParams = operation.getParams();
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
		}
		var prefix = me.queryParam.substring(0, me.queryParam
						.lastIndexOf('.'))
				+ '.';
		var param = prefix + me.myQueryParam;
		if (!Ext.isEmpty(me.myQueryParam)) {
			searchParams[param] = me.getRawValue();
			searchParams[me.queryParam] = null;
		} else {
			searchParams[me.queryParam] = me.rawValue;
			if(!Ext.isEmpty(me.myQueryParam)){
				searchParams[param] = null;
			}
		}
		/**
		 * 去掉active默认设置
		 */
		/*var activeParam = prefix + 'active';
		var act = Ext.isEmpty(me.active) ? me.myActive : me.active;
		searchParams[activeParam] = act;*/
		Ext.apply(store.proxy.extraParams, searchParams);  
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.active = config.active; 
		me.store.addListener('select', function(comb, records, obs) {
			me.record = records[0];
		});
		me.callParent([cfg]);
		me.on('blur',me.getBlur,me); 
	},
	getRecord : function() {
		var me = this;
		return me.record;
	},
	setReadOnlyByCss : function(isRead){
		var me = this;
		if(isRead){
			me.setFieldStyle("background-color:#ececec;");
		}else{
			me.setFieldStyle("background-color:#ffffff;");
		}
		me.setReadOnly(isRead);
	},
	listeners : {
		'select' : function(combo){
			combo.realValue = combo.getValue();
			combo.realName = combo.getRawValue();
		}
	}
});

//数据字典下拉单选框
Ext.define('Crm.commonSelector.DataDictionaryCommonSelector', {
	extend : 'Ext.form.field.ComboBox',
	alias: 'widget.dataDictionarySelector',
    displayField: 'valueName',
    valueField: 'valueCode',
    editable : false,
    setReadOnlyByCss : function(isRead){
		var me = this;
		if(isRead){
			me.setFieldStyle("background-color:#ececec;");
		}else{
			me.setFieldStyle("background-color:#ffffff;");
		}
		me.setReadOnly(isRead);
	},
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.model.modelName);
		m.set(key, displayText);
		m.set(value, valueText);
		me.store.loadRecords([m]);
		me.setValue(valueText);
	}
});

/**
* **********************部门下拉选择器START*********************
*/
//MODEL
Ext.define('Crm.commonSelector.DepartmentModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'deptName',type:'string'
		},{
			name: 'deptCode',type:'string'
		},{
			name: 'logistCode',type: 'string'
		},{
			name: 'supdeptCode',type: 'string'
		}]
});

/** 部门store */
Ext.define('Crm.commonSelector.DepartmentStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.DepartmentModel',
	pageSize:100,
	proxy:{
		type:"ajax",
		url:"../bse/queryDeptList.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'deptList',
			totalProperty : 'totalCount'
		}
	}
});

/** 部门单选公共选择器 */
Ext.define('Crm.commonSelector.DeptSelector',{
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.deptCommonSelector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'logistCode',// 显示名称
	valueField : 'deptCode',// 值
	queryParam : 'departmentVo.selectorParam',// 查询参数
	showContent : '{logistCode}【{deptName}】',// 显示表格列
	setCombValue : function(displayText, valueText) {
		var me = this, key = me.displayField + '', value = me.valueField
				+ '';
		var m = Ext.create(me.store.getModel());
		m.set(key, displayText);
		m.set(value, valueText);
		me.store.loadRecords([m]);
		me.setValue(valueText);
	},
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.DepartmentStore');
		me.callParent([cfg]);
	},
	listeners : {
		'select' : function(combo, record){
			combo.realValue = record[0].data['logistCode'];
			combo.realName = combo.getRawValue();
		}
	}
});

/**
* **********************部门下拉选择器END*********************
*/

/**
* **********************人员下拉选择器START*********************
*/
//MODEL
Ext.define('Crm.commonSelector.EmployeeModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'empCode',type:'string'
		},{
			name: 'empName',type:'string'
		}]
});

/** 人员store */
Ext.define('Crm.commonSelector.EmployeeStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.EmployeeModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../bse/queryEmpList.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'empList',
			totalProperty : 'totalCount'
		}
	}
});

/** 人员单选公共选择器 */
Ext.define('Crm.commonSelector.EmpSelector',{
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.empCommonSelector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'empName',// 显示名称
	valueField : 'empCode',// 值
	queryParam : 'employeeVo.selectorParam',// 查询参数
	showContent : '{empName}【{empCode}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.EmployeeStore');
		me.callParent([cfg]);
	}
});

/** 客户经理及团队经理store */
Ext.define('Crm.commonSelector.SaleEmployeeStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.EmployeeModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../bse/querySaleEmpsByDeptCode.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'empList',
			totalProperty : 'totalCount'
		}
	}
});

/** 客户经理及团队经理单选公共选择器 */
Ext.define('Crm.commonSelector.SaleEmpSelector',{
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.saleEmpSelector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'empName',// 显示名称
	valueField : 'empCode',// 值
	queryParam : 'employeeVo.selectorParam',// 查询参数
	showContent : '{empName}【{empCode}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.SaleEmployeeStore');
		me.callParent([cfg]);
	}
});

/**
* **********************人员下拉选择器END*********************
*/

/**
* **********************客户下拉选择器START*********************
*/
//MODEL
Ext.define('Crm.commonSelector.CustomerModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'id',type:'string'
		},{
			name: 'enterpriseName',type:'string'
		},{
			name: 'detailedAddress',type:'string'
		},{
			name: 'contactName',type:'string',mapping:'contactEntity.contactName'
		},{
			name: 'tierCode',type:'string'
		}]
});

/** 客户store */
Ext.define('Crm.commonSelector.CustomerStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.CustomerModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerAction!queryCustomeInfoByCombo.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'customerList',
			totalProperty : 'totalCount'
		}
	}
});

/** 客户单选公共选择器 */
Ext.define('Crm.commonSelector.CustomerSelector',{
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.customerCommonSelector',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'enterpriseName',// 显示名称
	valueField : 'id',// 值
	queryParam : 'customerVo.selectorParam',// 查询参数
	showContent : '{enterpriseName}【{contactName}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.CustomerStore');
		me.callParent([cfg]);
	}
});
/********************预约洽谈按登录权限选择*******************/
Ext.define('Crm.commonSelector.CustomerStoreByAuth',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.CustomerModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerAction!queryCustomeInfo.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'customerList',
			totalProperty : 'totalCount'
		}
	}
});

Ext.define('Crm.commonSelector.CustomerSelectorByAuth',{
	extend : 'Crm.commonSelector.CommonCombSelector',
	alias : 'widget.customerCommonSelectorByAuth',
	listWidth : 200,// 设置下拉框宽度
	displayField : 'enterpriseName',// 显示名称
	valueField : 'id',// 值
	queryParam : 'customerVo.selectorParam',// 查询参数
	showContent : '{enterpriseName}【{contactName}】',// 显示表格列
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.CustomerStoreByAuth');
		me.callParent([cfg]);
	}
});
/**
* **********************客户下拉选择器END*********************
*/

/**
* **********************行政区域下拉选择器STRAT*********************
*/

//MODEL
Ext.define('Crm.commonSelector.DistrictModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'districtCode',type:'string'
		},{
			name: 'districtName',type:'string'
		},{
			name: 'parentDistrictCode',type:'string'
		}]
});

/** 行政区域省store */
Ext.define('Crm.commonSelector.DistrictProvinceStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.DistrictModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerInfoPoolAction!queryDistrictInfo.action?districtType=PROVINCE",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'districtInfoList'
		}
	},
	autoLoad:true,
	remoteSort:true
});

/** 行政区域市store */
Ext.define('Crm.commonSelector.DistrictCityStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.DistrictModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerInfoPoolAction!queryDistrictInfo.action?districtType=CITY",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'districtInfoList'
		}
	},
	autoLoad:false,
	remoteSort:true
});

/** 行政区域区县store */
Ext.define('Crm.commonSelector.DistrictAreaStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.DistrictModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerInfoPoolAction!queryDistrictInfo.action?districtType=AREA",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'districtInfoList'
		}
	},
	autoLoad:false,
	remoteSort:true
});

/** 行政区域省单选公共选择器 */
Ext.define('Crm.commonSelector.DistrictProvinceSelector',{
	extend : 'Ext.form.ComboBox',
	alias : 'widget.districtProvinceSelector',
	listWidth : 200,// 设置下拉框宽度
	name:'province',  
    id:'province',
	displayField : 'districtName',// 显示名称
	valueField : 'districtCode',// 值
	queryMode:'local',
	triggerAction:'all',
	selectOnFocus:true,
	forceSelection:true,
    editable:true,
	emptyText:'请选择省...',  
    blankText:'请选择省',
    allowBlank:false,  
	listeners:{  
        select:function(combo,record,index){  
            try{  
                var city = Ext.getCmp('city');
                var area = Ext.getCmp('area');
                city.clearValue();  
                area.clearValue();  
                city.store.load(  
                     {  
                         params:{  
                        	 parentDistrictCode:combo.getValue()  
                         }  
                     }     
                );  
            }catch(ex){  
                alert("数据加载失败！");  
            }  
        }
    },
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.DistrictProvinceStore');
		me.callParent([cfg]);
	}
});

/** 行政区域市单选公共选择器 */
Ext.define('Crm.commonSelector.DistrictCitySelector',{
	extend : 'Ext.form.ComboBox',
	alias : 'widget.districtCitySelector',
	listWidth : 200,// 设置下拉框宽度
	name:'city',  
    id:'city',
	displayField : 'districtName',// 显示名称
	valueField : 'districtCode',// 值
	queryMode:'local',
	triggerAction:'all',
	selectOnFocus:true,
	forceSelection:true,
    editable:true,
	emptyText:'请选择市...',  
    blankText:'请选择市',
    allowBlank:false,
	listeners:{  
        select:function(combo,record,index){  
            try{  
                var area = Ext.getCmp('area');
                area.clearValue();  
                area.store.load(  
                     {  
                         params:{  
                        	 parentDistrictCode:combo.getValue()  
                         }  
                     }     
                );  
            }catch(ex){  
                alert("数据加载失败！");  
            }  
        }
    },
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.DistrictCityStore');
		me.callParent([cfg]);
	}
});

/** 行政区域区县单选公共选择器 */
Ext.define('Crm.commonSelector.DistrictAreaSelector',{
	extend : 'Ext.form.ComboBox',
	alias : 'widget.districtAreaSelector',
	listWidth : 200,// 设置下拉框宽度
	name:'area',  
    id:'area',
	displayField : 'districtName',// 显示名称
	valueField : 'districtCode',// 值
	queryMode:'local',
	triggerAction:'all',
	selectOnFocus:true,
	forceSelection:true,
	emptyText:'请选择区...',  
    blankText:'请选择区',
    allowBlank:false,
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.DistrictAreaStore');
		me.callParent([cfg]);
	}
});
/**
* **********************行政区域下拉选择器END*********************
*/

/**
* **********************事业部大区下拉选择器STRAT*********************
*/

//MODEL
Ext.define('Crm.commonSelector.BusinessRegionsModel', {
	extend : 'Ext.data.Model',
	fields :[{
			name: 'deptCode',type:'string'
		},{
			name: 'deptName',type:'string'
		}]
});

/** 事业部store */
Ext.define('Crm.commonSelector.BusinessStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.BusinessRegionsModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerInfoPoolAction!queryDepts.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'depts'
		}
	},
	autoLoad:true,
	remoteSort:true
});

/** 大区store */
Ext.define('Crm.commonSelector.RegionsStore',{
	extend:'Ext.data.Store',
	model: 'Crm.commonSelector.BusinessRegionsModel',
	pageSize:10,
	proxy:{
		type:"ajax",
		url:"../customer/customerInfoPoolAction!queryDepts.action",
		actionMethods:"POST",
		reader:{
			type:'json',
			root:'depts'
		}
	},
	autoLoad:false,
	remoteSort:true
});

/** 事业部单选公共选择器 */
Ext.define('Crm.commonSelector.BusinessSelector',{
	extend : 'Ext.form.ComboBox',
	alias : 'widget.businessSelector',
	listWidth : 200,// 设置下拉框宽度
	name:'business',  
    id:'business',
	displayField : 'deptName',// 显示名称
	valueField : 'deptCode',// 值
	queryMode:'local',
	triggerAction:'all',
	selectOnFocus:true,
	forceSelection:true,
    editable:true,
	emptyText:'请选择事业部...',  
    blankText:'请选择事业部',
    allowBlank:false,
	listeners:{  
        select:function(combo,record,index){  
            try{  
                var regions = Ext.getCmp('regions');
                regions.clearValue();  
                regions.store.load(  
                     {  
                         params:{  
                        	 supDeptCode:combo.getValue()  
                         }
                     } 
                );
            }catch(ex){  
                alert("数据加载失败！");  
            }
        }
    },
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.BusinessStore');
		me.callParent([cfg]);
	}
});

/** 大区单选公共选择器 */
Ext.define('Crm.commonSelector.RegionsSelector',{
	extend : 'Ext.form.ComboBox',
	alias : 'widget.regionsSelector',
	listWidth : 200,// 设置下拉框宽度
	name:'regions',  
    id:'regions',
	displayField : 'deptName',// 显示名称
	valueField : 'deptCode',// 值
	queryMode:'local',
	triggerAction:'all',
	selectOnFocus:true,
	forceSelection:true,
	emptyText:'请选择大区...',  
    blankText:'请选择大区',
    allowBlank:false,
	constructor : function(config) {
		var me = this, cfg = Ext.apply({}, config);
		me.store = Ext.create('Crm.commonSelector.RegionsStore');
		me.callParent([cfg]);
	}
});

/**
* **********************事业部大区下拉选择器END*********************
*/