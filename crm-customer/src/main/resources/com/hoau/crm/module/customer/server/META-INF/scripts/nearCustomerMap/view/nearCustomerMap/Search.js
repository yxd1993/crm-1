/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.nearCustomerMap.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.nearCustomerSearch',
    width : '100%',
    height : 70,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '5 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [ {
		xtype : "districtProvinceSelector",
		name : "provinceCode",
		fieldLabel : "省"
	}, {
		xtype : "districtCitySelector",
		name : "cityCode",
		fieldLabel : "市"
	}, {
		xtype : "districtAreaSelector",
		name : "areaCode",
		fieldLabel : "区"
	}
	],
    buttons: [{
        text: '查询',
        action : 'select'
    }, {
        text: '清空',
        action : 'reset'
    }]
});
