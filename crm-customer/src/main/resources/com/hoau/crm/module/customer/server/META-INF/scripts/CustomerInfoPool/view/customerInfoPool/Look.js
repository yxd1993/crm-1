Ext.define("crm.view.customerInfoPool.Look", {
    extend: "Ext.window.Window",
    alias: "widget.customerInfoPoolLookWin",
    title: "共享客户信息",
    width: 600,
    height: 380,
    layout: "fit",
    bodyPadding: 5,
    items: {
        xtype: "form",
        margin: 5,
        border: false,
        layout: "column",
        defaultType: 'textfield',
        defaults: {
            margin : '5 0 5 30 ',
    		labelWidth : 100,
    		columnWidth : 0.49,
    		labelAlign: 'left',
    		readOnly : true
        },
        items: [
            {  name: "companyName", fieldLabel: "公司名称" },
            {  name: "contactPerson", fieldLabel: "联系人" },
            {  name: "contactWay", fieldLabel: "联系方式" },
            {  name: "email", fieldLabel: "邮箱" },
            {  name: "business", fieldLabel: "所属事业部" },
            {  name: "regions", fieldLabel: "所属大区" },
            {  name: "province", fieldLabel: "省" },
            {  name: "city", fieldLabel: "市" },
            {  name: "area", fieldLabel: "区" },
            {  name: "companyAddress", fieldLabel: "公司地址" },
            {  name: "dispenseStatus", fieldLabel: "分发状态"},
            {  name: "managePerson", fieldLabel: "负责人"},
            { xtype: "textarea", editable:false, name: "backReason", fieldLabel: "退回原因", columnWidth : 0.98,}
        ],
        buttons: [ {
            text: '转为潜在客户',
            action : 'change'
        }, {
            text: '回退资源客户',
            action : 'back'
        }]
    }
});


/**
 * 分发状态渲染功能
 * 
 * @param value
 * @returns
 * @author 何斌
 * @date 2015-6-23
 * @update
 */
function dispenseStatusRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMER_DISSTATE");
}


/**
 * 事业部渲染功能
 * 
 * @param value
 * @returns
 * @author 何斌
 * @date 2015-7-23
 * @update
 */
function businessRenderer(value){
	return getDataDictionary().rendererSubmitToDisplay(value, "CUSTOMERINFOPOOL_BUSINESS");
}
