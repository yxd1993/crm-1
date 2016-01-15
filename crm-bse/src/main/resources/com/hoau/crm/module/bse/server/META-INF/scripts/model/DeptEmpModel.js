/**
 * 基础model
 */
Ext.define('CRM.model.bse.UserMenuModel', {
    extend: 'Ext.data.Model',
    fields: [
        {name:'id'},//ID
        {name:'text'},
        {name:'leaf'},
        {name:'url'}
    ]
});


/**
 * 人员信息实体
 */
Ext.define('CRM.model.bse.EmployeeEntity',{
	extend : 'Ext.data.Model',
	fields : [{
		name : 'id'
	},{
		name : 'userName'
	},{
		name : 'empCode', mapping : 'empEntity.empCode'
	},{
		name : 'empName', mapping : 'empEntity.empName'
	},{
		name : 'deptCode', mapping : 'empEntity.deptEntity.deptCode'
	},{
		name : 'deptName', mapping : 'empEntity.deptEntity.deptName'
	},{
		name : 'jobName', mapping : 'empEntity.jobname'
	}]
});

/**
 * role的Model
 */
Ext.define('CRM.model.bse.roleModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id'
	}, {
		name : 'roleCode'
	}, {
		name : 'roleName'
	}, {
		name : 'roleDesc'
	}, {
		name : 'active'
	}, {
		name : 'createUser'
	}, {
		name : 'createDate',
		defaultValue : new Date(),
		type : 'number'
	}, {
		name : 'modifyUser'
	}, {
		name : 'modifyDate',
		defaultValue : new Date(),
		type : 'number'
	} ]
});