/**
 * 客户信息GRID
 */
Ext.define("crm.view.personalcustomer.contactPanel", {
    extend: "Ext.grid.Panel",
    alias: 'widget.personalCustomerContactPanel',
    store: "PersonalCustomerContact",
    region : 'center',
    width : '100%',
    viewConfig:{
		enableTextSelection:true
	},
    columns : {
		defaults: {
		    align : 'left'
		},
		items : [
		        {text : '序号',	width : 50,	xtype : 'rownumberer'},
  		        { text: '联系人', dataIndex: 'contactName', flex:1},
  		        { text: '手机号', dataIndex: 'cellphone', flex:1},
  		        { text: '座机号', dataIndex: 'telephone', flex:1},
      	        { text: '区号', dataIndex: 'districtNumber', flex:1},
      	        { text: '发货门店', dataIndex: 'tierCode', flex:1}
      	]
    }
});