var createDate = null;
Ext.define('crm.view.server.LookConnects', {
	id:'lookConnects',
    extend: 'Ext.Panel',
    region : 'center',
    alias: 'widget.lookConnects',
    autoScroll : true,
    layout: {
        type: 'anchor',
        pack: 'center'
    },
    themes: {
        classic: {
            percentChangeColumn: {
                width: 75
            }
        },
        neptune: {
            percentChangeColumn: {
                width: 100
            }
        }
    },
    initComponent: function() {
        var me = this;
        me.items = [
            getSearchDate(),
        	getServerConnectChart(createDate)
        ];
        this.callParent();
    }
});
//查询条件
function getSearchDate(){
	var searchDate = Ext.create('Ext.form.Panel', {
		id:'searchDate',
	    hidden: false,
	    maximizable: true,
	    layout: {
	        type: 'anchor',
	        pack: 'center'
	    },
	    title: '当天服务器在线波动图',
	    layout: 'fit',
	    tbar: [{
			xtype: 'datefield',
			fieldLabel: '查询时间',
			name:'createDate',
			format: 'Y-m-d',
			value:new Date()
		},{
	        text: '查询',
	        handler: function(btn) {
	        	var panel = Ext.getCmp('lookConnects');
	        	// 提示
	        	panel.getEl().mask("数据获取中...");
	        	var form = Ext.getCmp('searchDate').getForm();
	        	createDate = form.findField('createDate').getValue();
	        	
	        	panel.remove(panel.items.items[1])
	        	setTimeout(function(){
		        	panel.getEl().unmask();
	        	},2000)
	        	panel.add(getServerConnectChart(createDate));
	        	
	        }
	    }, {
	        text: '重置',
	        handler: function() {
	            Ext.MessageBox.confirm('提示', '确定重置到当天吗?', function(choice){
	                if(choice == 'yes'){
	                	var form = Ext.getCmp('searchDate').getForm();
	                	form.findField('createDate').setValue(Ext.util.Format.date(new Date()))
	                }
	            });
	        }
	    }]
	});
	return searchDate;
}
//ajax提交
function ajax(createDate){
	var chartStore = null;
	var params = {
			'serverStatusEntity.createDate':createDate
	};
	Ext.Ajax.request({
		url: '../bse/queryServerConnects.action',
		async : false,
		params:params,
		success:function(response){
			var result = Ext.decode(response.responseText);
			chartStore = result.onlineConnects;
			
		},
		failure:function(response){
			Ext.MessageBox.alert('提示', response.message);
		},
		exception:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
	return chartStore;
}

/**
 * Nginx 服务在线连接数折线图
 * @author 丁勇
 * @date 2015-8-13
 * @update
 */
function getServerConnectChart(createDate){
	var chartPanel = {
        xtype: 'chart',
        columnWidth: 1.0,
        height: 400,
        style: 'background: #fff',
        padding: '10 0 0 0',
        insetPadding: 40,
        animate: true,
        shadow: false,
        store: Ext.create('Ext.data.JsonStore', {
            fields: ['createDate', 'activeConnections','writing'],
            data: ajax(createDate)
        }),
        items: [{
            type  : 'text',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            align : 'center',
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }],
        //声明左轴与底轴分别是什么
        axes: [{
            type: 'Numeric',
            position: 'left',
            fields: ['activeConnections','writing'],
        	grid: true
        }, {
            type: 'Category',
            position: 'bottom',
            fields: '',
            label: {
                renderer: Ext.util.Format.numberRenderer('0')
                }, 
            grid: true,
        }],
      //声明填充x与y轴的数据分别是什么
        series: [{
            type: 'line',
            axis: 'left',
            title: ['连接数'],
            xField:'',
            yField:'activeConnections',
            stacked: true,
            style: {
                opacity: 0.80
            },
            highlight: {
                fill: '#000',
                'stroke-width': 2,
                stroke: '#fff'
            },
            tips: {
                trackMouse: true,
                style: 'background: #fff',
                height: 20,
                renderer: function(storeItem, item) {
                	this.setTitle(storeItem.get('createDate')+item.series.title+':'+storeItem.get('activeConnections'));
                }
            }
        }
//        ,{
//            type: 'line',
//            axis: 'left',
//            title: ['响应处理数量'],
//            xField:'createDate',
//            yField:'writing',
//            stacked: true,
//            style: {
//                opacity: 0.80
//            },
//            highlight: {
//                fill: '#000',
//                'stroke-width': 2,
//                stroke: '#fff'
//            },
//            tips: {
//                trackMouse: true,
//                style: 'background: #fff',
//                height: 20,
//                renderer: function(storeItem, item) {
//                	this.setTitle(item.series.title+':'+storeItem.get('writing'));
//                }
//            }
//        }
        ]
	}
	return chartPanel;
}
