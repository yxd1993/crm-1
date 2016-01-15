/**
 * @memo 动态加载JavaScript文件
 */
var Hoau = Hoau || {};
Hoau.JsLoader = function() {
   this.load = function(url) {
		var me = this,
			scripts = document.getElementsByTagName("script"),
			len = scripts.length,
			i, script, head;
		for (i=0; i<len; i++) {
			if (scripts[i].src && scripts[i].src.indexOf(url) != -1) {
				me.onSuccess();
				return;
			}
		}
		script = document.createElement("script");
		script.type = "text/javascript";
		script.src = url;
		head = document.getElementsByTagName("head")[0];
		try {
			head.appendChild(script);
			script.onload = script.onreadystatechange = function() {
				if (script.readyState && script.readyState != 'loaded' && script.readyState != 'complete') return;
				script.onreadystatechange = script.onload = null; 
				me.onSuccess();
			}
		} catch(e) {
			//head.removeChild(script);
			if(typeof(me.onFailure) == 'function') {
				me.onFailure();
			} else if(Ext != 'undefined' && Ext != null) {
				Ext.MessageBox.alert("提示", "JavaScript文件加载失败.");
			}
		}
	}
}

// trim方法在IE下报错
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}  
/**
 * 修改date对象数据的JSON提交方式
 * @param date 日期对象
 * @returns dateTime 日期对象的长整型数
 */
Ext.JSON.encodeDate = function(date) {
    var dateTime = date.getTime();
    return dateTime;
};

/**
 * @author xiantao zeng
 * @since 2012-12-06
 * @memo 约束窗口在当前视图内
 */
Ext.override(Ext.window.Window, {
    constrain: true
});

/**
 * 修改Ajax的请求超时时间，方便Debug
 * 仅适用于开发环境
 */
Ext.data.proxy.Server.override({
	timeout: 3000000
});

/**
 * 日期类型数据转换器
 */
function dateConvert(value, record) {
	if (value != null) {
		var date = new Date(value);
		return date;
	} else {
		return null;
	}
}




/**
 * 修改combox下拉后的页面中的分页
 */
/*Ext.view.BoundList.override({
	createPagingToolbar: function() {
        return Ext.widget('simplepaging', {
            id: this.id + '-paging-toolbar',
            pageSize: this.pageSize,
            store: this.store,
            border: false
        });
    }
});*/

/**
*	修改默认提示框按钮的焦点
*	更改提示框中按钮的默认位置需更改源码Ext.window.MessageBox类中
*    buttonIds: [
*        'cancel', 'no', 'yes', 'ok'
*    ]
*/
Ext.override(Ext.window.MessageBox, {
    defaultFocus: 2
});


/**
*	悬浮提示
*/
//Ext.ns('Hoau.ux');
Ext.ux.Toast = function() {
    var msgCt;
	function createBox(t, s, f) {
		var result;
		if(f == 'error') {
			result = ['<div class="msg">',
				'<div class="top"><div class="title">'+ t +'</div></div>',
				'<div class="middle"><div class="erroriconstyle"></div><div class="textpanelstyle"><p>'+ s +'</p></div></div>',
				'<div class="bottom_error"></div>',
				'</div>'].join('');
		} else {
			result = ['<div class="msg">',
				'<div class="top"><div class="title">'+ t +'</div></div>',
				'<div class="middle"><div class="okiconstyle"></div><div class="textpanelstyle"><p>'+ s +'</p></div></div>',
				'<div class="bottom_ok"></div>',
				'</div>'].join('');
		}
		return result;
    };
    return {
        msg: function(title, content, flag, delay) {
            if(!msgCt) {
                msgCt = Ext.DomHelper.insertFirst(document.body, {
					id: 'msg-div'
				}, true);
            }
			if(Ext.isEmpty(flag)) flag = 'ok';
			if(Ext.isEmpty(delay)) delay = 2000;
            var m = Ext.DomHelper.append(msgCt, {
				html: createBox(title, content, flag)
			}, true);
            m.hide();
            m.slideIn('t').ghost('t', { delay: delay, remove: true});
        },
		init: function() {
		
		}
	};
}();

/**
 * 关闭Tab功能
 * @param tID 待关闭的tab id
*/
function removeTab(tID) {
	var tabPanel = Ext.getCmp('mainAreaPanel'),
		tab = Ext.getCmp(tID);
	if(tabPanel==null){
		return;
	}
	tabPanel.remove(tab, true);
}

/**
 * 新建Tab功能
 * @param tID 待打开的tab id
 * @param tText tab显示文本值
 * @param tLoc tab页面请求的URL
 * @param params 请求的参数
*/
function addTab() {
	var tID = arguments[0],
		tText = arguments[1],
		tLoc = arguments[2],
		tabPanel = Ext.getCmp('mainAreaPanel'),
		tab = Ext.getCmp(tID);
	if(tabPanel==null){
		return;
	}
	tabPanel.params = arguments[3];
	if(!tab) {
		tabPanel.add({
			id: tID,
			title: tText,
			layout: 'fit',
			closable: true,
			tabConfig: { width: 150 },
			cls: 'autoHeight',
			bodyCls: 'autoHeight',
			loader: {
				scripts: true,
				autoLoad: true,
				url: '../'+tLoc
			}
		});
		tabPanel.setActiveTab(tabPanel.child('#'+tID));
	} else {
		tabPanel.setActiveTab(tab);
	}
}

/**
 * 动态下拉框查询
 */
Ext.define('Hoau.commonselector.DynamicComboSelector', {
	extend : 'Ext.form.ComboBox',
	alias : 'widget.dynamiccomboselector',
	triggerCls : Ext.baseCSSPrefix + 'form-search-trigger',
	listWidth : this.width,// 下拉列表宽度，从外面传入
	multiSelect : false,// 从外面传入
	isPaging : false,// 是否要分页
	isEnterQuery : false, // 回车查询
	displayField : null,// 显示的字段，从外面传入
	valueField : null,// 提交时的字段,从外面传入
	showContent : null,// 需要从外面传入如：showContent:'{deptName}&nbsp;&nbsp;{deptCode}'
	queryParam : null,// 查询参数
	triggerAction : 'query',
	minChars : 1,
	hideTrigger : false,
	listConfig : {
		getInnerTpl : function() {
			return this.up('combo').showContent;
		}
	},
	getValueModel : function() {
		var models = this.valueModels;
		if (Ext.isEmpty(models) && models.length > 0) {
			return models[0];
		} else {
			return null;
		}
	},
	getChange : function(combo, newValue, oldValue) {
		if (combo.isExpanded == true) {
			combo.collapse();
		}
	},
	getKeyPress : function(combo, event, eOpts) {
		if (event.getKey() == event.ENTER) {
			combo.store.loadPage(1, {
				scope : this,
				callback : function(records, operation, success) {
					if (records.length > 0) {
						combo.expand();
					}
				}
			});
		}
	},
	getBeforeLoad : function(st, operation, e) {
		var me = this, searchParams = operation.getParams();
		if (Ext.isEmpty(searchParams)) {
			searchParams = {};
		}
		searchParams[me.queryParam] = me.rawValue;
		Ext.apply(store.proxy.extraParams, searchParams);  
	},
	initEvents : function() {
		var me = this;
		me.callParent(arguments);
		// 判断是否是回车去查询
		if (me.isEnterQuery == true) {
			me.mon(me, 'change', me.getChange, me);
			me.mon(me, 'keypress', me.getKeyPress, me);
		}
		me.mon(me.store, 'beforeLoad', me.getBeforeLoad, me);
	},
	initComponent : function() {
		var me = this;
		// 列表宽度自定义
		me.on('expand', function(combo, eOpts) {
			combo.getPicker().minWidth = me.listWidth;
			combo.getPicker().setWidth(me.listWidth);
		});
		// 判断是否要分页
		if (me.isPaging == true) {
			me.pageSize = me.store.pageSize;
		}
		// 判断下拉grid显示列
		if (me.showContent == null) {
			me.showContent = '{' + me.displayField + '}';
		}
		// 判断是否是回车去查询
		if (me.isEnterQuery == true) {
			me.enableKeyEvents = true;
			me.queryDelay = 1000000;// 此处为了使回车时间生效
		}
		this.callParent(arguments);
	}
});

/**
 * 动态下拉多选框查询
 */
Ext.define('Hoau.commonselector.DynamicMultiSelectComboSelector',{
	extend: 'Ext.form.field.ComboBox',
	alias: 'widget.dynamicmulticomboselector',
	triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
	listWidth: this.width,//下拉列表宽度，从外面传入
	isPaging: false,//是否要分页
	isEnterQuery: false,	//回车查询
	triggerOnClick: false,//得到焦点时，不进行下拉
	multiSelect: true,
	minChars:1,
	listConfig: {
		getInnerTpl: function() {
			return this.up('combo').showContent;
		}
	},
	onKeyPress:function(e, t){
		var me = this;
		if(e.getKey() == e.ENTER){
			me.doRawQuery();
		}else{
			me.collapse();
		}
	},
	initEvents: function(){
		var me = this;
        me.callParent(arguments);
        if (me.enableKeyEvents&&me.isEnterQuery) {
			me.mon(me.inputEl, 'keypress', me.onKeyPress, me);
        }
	},
	initComponent:function(){
		var me = this;
		//列表宽度自定义
		me.on('expand', function(combo, eOpts ){
				combo.getPicker().minWidth=me.listWidth;
				combo.getPicker().setWidth(me.listWidth);
		});
		//判断是否要分页
		if(me.isPaging){
			me.pageSize = me.store.pageSize;
		}
		//判断是否是回车去查询
		if(me.isEnterQuery){
			me.enableKeyEvents = true;
			me.queryDelay = 1000000;//此处为了使回车时间生效
		}
		//判断下拉grid显示列
		if(me.showContent==null){
			me.showContent = '{'+me.displayField+'}';
		}
		this.callParent(arguments);
	}
});

