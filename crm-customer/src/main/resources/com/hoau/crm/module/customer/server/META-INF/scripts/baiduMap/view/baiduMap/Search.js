/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.baiduMap.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.userScopeSearch',
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
        text: '切换城市',
        action : 'select'
    },{
        text: '开启设定范围',
        action : 'setrange'
    }, {
        text: '清空',
        action : 'reset'
    }, {
        text: '重置范围',
        action : 'tomark'
    }, {
        text: '确认范围',
        action : 'confirm'
    }]
});
