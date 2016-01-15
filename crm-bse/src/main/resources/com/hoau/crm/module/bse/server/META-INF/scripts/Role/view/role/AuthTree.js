Ext.define("crm.view.role.AuthTree", {
	extend : 'Ext.tree.TreePanel',
	alias: "widget.authTree",
	store: 'Auth',
	border:false,
	split: true,
    split:true,//可以合并
	//动画效果
	animate:true,
    autoScroll: true,
	//树节点是否可见
    rootVisible: false,
    //使用vista风格的箭头图标，默认为false
    useArrows: true
});
