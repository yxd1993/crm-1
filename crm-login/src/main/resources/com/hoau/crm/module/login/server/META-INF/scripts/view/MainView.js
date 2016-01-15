Ext.require([
    'Ext.tab.*',
    'Ext.window.*',
    'Ext.tip.*',
    'Ext.layout.container.Border'
]);

// MAP
function Map(){
	this.container = new Object();
}
Map.prototype.put = function(key, value){
	this.container[key] = value;
}
Map.prototype.get = function(key){
	return this.container[key];
}
// 子页面组件MAP
var allChildrenPanelMap = new Map();
// 子页面参数MAP
var allChildrenParamsMap = new Map();

Ext.onReady(function(){
	var treePanel = Ext.create('baseUx.tree.NTreePanel', {
		id: 'treePanel',
		store:Ext.create('CRM.store.login.MenuStore'),
		renderTo:Ext.getBody(),
		region : 'west',
		collapsible : false,
		width : 214,
		border:false,
		minWidth:214,
		maxWidth:214,
		bodyStyle : 'background-color:#21232b',
	    autoScroll: true,
		//树节点是否可见
        rootVisible: false,
        //使用vista风格的箭头图标，默认为false
	    useArrows: true,
		listeners:{
			'itemclick' : function(node, record,item,index,e,eOpts){
				//点击菜单加载页面
				initTabpanel(record.get('id'),record.get('text'),record.get('url'),record.get('leaf'));
			}
		}
	});
	
    Ext.create('Ext.Viewport', {
        header: {
            titlePosition: 0,
            titleAlign: 'center'
        },
        renderTo: Ext.getBody(),
//      tools: [{type: 'pin'}],
        layout: {
            type: 'border',
            padding: 0,
            margin: 0
        },
        items: [{ 
			id : 'banner',
			region : 'north',
			height:82,
			html: '<iframe src="top.action" style="height:100%;width:100%;" frameborder="0"></iframe>',
			//collapsible : true,
			//collapsed :false,
			hideBorders:true,
			border : false
		},
		treePanel,
        {
            region: 'center',
            xtype: 'tabpanel',
            bodyStyle: 'background-color:#e6e9ec; padding-left:11px; padding-right:11px;',
            id:'tabPanel',
            items: [{
                title: '欢迎首页',
                html : '<iframe id="panel0" src="../bse/homepage.action" style="height:100%;width:100%;padding:0px;margin:0px;" frameborder="0"></iframe>'
            }],
            listeners : {
            	tabchange : function(tabPanel, newCard, oldCard, eOpts) {
                    /*var panel = Ext.getCmp(newCard.id + '_content');
                    if (panel) {
                        panel.doLayout();
                        if (panel.tabChangeActive) {
                            panel.tabChangeActive();
                        }
                    }*/
            		// 客户管理刷新
            		if(allChildrenPanelMap.get(newCard.id)){
            			allChildrenPanelMap.get(newCard.id).tabChange();
            		}
            		/*if(newCard.id == '101001'){
            			if(allChildrenPanel.CustomerListPanel){
                			allChildrenPanel.CustomerListPanel.getStore().reload();
                		}
            		} else if(newCard.id == '103001'){
            			if(allChildrenPanel.reserveMainView){
                			allChildrenPanel.reserveMainView.tabChange();
                		}
            		} else if(newCard.id == '103002'){
            			if(allChildrenPanel.reserveMainView){
                			allChildrenPanel.reserveMainView.tabChange();
                		}
            		}*/
                },
                beforeremove : function(tabPanel, newCard){
                	// 客户管理刷新
            		if(allChildrenPanelMap.get(newCard.id)){
            			allChildrenPanelMap.put(newCard.id, undefined);
            		}
                	/*if(newCard.id == '101001'){
                		allChildrenPanel.CustomerListPanel = undefined;
            		} else if(newCard.id == '103001'){
            			allChildrenPanel.reserveMainView = undefined;
            		} else if(newCard.id == '103001'){
            			allChildrenPanel.reserveMainView = undefined;
            		}*/
                }
            }
        }]
    });
});

/**
 * 新增TAB
 * King 
 * 2015年5月13日14:02:58
 */
function initTabpanel(id,text,url,leaf){
	if(false == leaf){
		return;
	}
	var tabs = Ext.getCmp('tabPanel');
	if (tabs) {
		var tab = tabs.getComponent(id);
		//判断是否已经打开该面板
		if (!tab) {
			tab = tabs.add({
				'id': id,
				'title': text,
				closable:true,
				border:false,
				html: '<iframe id="'+id+'" src="'+url+'" style="height:100%;width:100%;padding:0px;margin:0px;" frameborder="0"></iframe>'
				//html:text
			});
		}
	    tabs.setActiveTab(tab);
	}
}

/**
 * 删除TAB
 * King 
 * 2015年5月13日14:02:58
 */
function delTabpanel(id){
	var tabs = Ext.getCmp('tabPanel');
	if (tabs) {
		var tab = tabs.getComponent(id);
		if (tab) {
			tabs.remove(tab);
		}
	}
}

/**
 * 退出
 */
function logout(){
	Ext.Msg.confirm('提示', '确定退出CRM系统？', function(btn) {
		if(btn == 'yes') {
			// AJAX请求
			crm.requestJsonAjax('userLogout.action', null, 
				function(){
					location = "index.action";
					var date = new Date(); 
			    	//为了删除指定名称的cookie，可以将其过期时间设定为一个过去的时间 
			    	date.setTime(date.getTime() - 10000); 
			    	document.cookie = "username=n;expires=" + date.toGMTString();
				}
			);
		}
	})
}