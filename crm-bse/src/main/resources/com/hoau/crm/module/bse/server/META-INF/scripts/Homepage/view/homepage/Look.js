Ext.define('crm.view.homepage.Look', {
    extend: 'Ext.Panel',
    region : 'center',
    alias: 'widget.homepageLook',
    autoScroll : true,
    layout: {
        type: 'column',
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
           getNatureChart(),
           getIndustryChart()
        ];
        this.callParent();
    }
});

/**
 * 本月客户数据一览图
 * 
 * @author 蒋落琛
 * @date 2015-6-3
 * @update
 */
function getNatureChart(){
	var chartPanel = null;
	var chartStore = null;
	Ext.Ajax.request({
		url: '../customer/reportNature.action',
		async : false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			chartStore = result.customerNatureDataLists;
		},
		failure:function(response){
			Ext.MessageBox.alert('提示', response.message);
		},
		exception:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
	if(chartStore == null){
		return {};
	}
	var chartPanel =  {
        xtype: 'chart',
        columnWidth: 0.4,
        height: 400,
        style: 'background: #fff',
        padding: '10 0 0 0',
        insetPadding: 40,
        animate: true,
        shadow: false,
        store: Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'type', 'dataOne', 'dataTwo'],
            data: chartStore
        }),
        items: [{
            type  : 'text',
            text  : '本月新增客户数据一览图',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            align : 'center',
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }],
        axes: [{
            type: 'Numeric',
            position: 'bottom',
            fields: 'dataTwo',
            grid: true,
            /*label: {
                renderer: function(v) { return v; }
            },*/
            minimum: 0
        }, {
            type: 'Category',
            position: 'left',
            fields: 'type',
            grid: true
        }],
        series: [{
            type: 'bar',
            axis: 'bottom',
            title: [ /*'原数量', */'本月新增'],
            xField: 'type',
            yField: [ /*'dataOne', */'dataTwo'],
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
                style: 'background: #FFF',
                height: 20,
                renderer: function(storeItem, item) {
                    var browser = item.series.title[Ext.Array.indexOf(item.series.yField, item.yField)];
                    this.setTitle(browser + ': ' + storeItem.get(item.yField));
                }
            }
        }]
    }
	return chartPanel;
}

/**
 * 客户行业分布图
 * 
 * @returns
 * @author 蒋落琛
 * @date 2015-6-3
 * @update
 */
function getIndustryChart(){
	var chartPanel = null;
	var chartStore = null;
	Ext.Ajax.request({
		url: '../customer/reportIndustry.action',
		async : false,
		success:function(response){
			var result = Ext.decode(response.responseText);
			chartStore = result.customerIndustryDataLists;
		},
		failure:function(response){
			Ext.MessageBox.alert('提示', response.message);
		},
		exception:function(response){
			var result = Ext.decode(response.responseText);
			failFn(result);
		}
	});
	if(chartStore == null){
		return {};
	}
	var chartPanel =  {
        xtype: 'chart',
        columnWidth: 0.6,
        height: 400,
        style: 'background: #fff',
        padding: '10 0 0 0',
        insetPadding: 40,
        animate: true,
        shadow: false,
        store: Ext.create('Ext.data.JsonStore', {
            fields: ['id', 'type', 'dataOne', 'dataTwo'],
            data: chartStore
        }),
        items: [{
            type  : 'text',
            text  : '客户行业分布图',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            align : 'center',
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }],
        axes: [{
            type: 'Numeric',
            position: 'bottom',
            fields: 'dataOne',
            grid: true,
            /*label: {
                renderer: function(v) { return v; }
            },*/
            minimum: 0
        }, {
            type: 'Category',
            position: 'left',
            fields: 'type',
            grid: true
        }],
        series: [{
            type: 'bar',
            axis: 'bottom',
            title: [ /*'原数量', */'本月新增'],
            xField: 'type',
            yField: [ /*'dataOne', */'dataOne'],
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
                style: 'background: #FFF',
                height: 20,
                renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('type') + ': ' + storeItem.get('dataOne'));
                }
            }
        }]
        /*axes: [{
            type: 'Numeric',
            position: 'left',
            fields: ['dataOne'],
            label: {
                renderer: function(v) { return v + '%'; }
            },
            grid: true,
            minimum: 0
        }, {
            type: 'Category',
            position: 'bottom',
            fields: ['type'],
            grid: true,
            label: {
                rotate: {
                    degrees: 90
                }
            }
        }],
        series: [{
            type: 'column',
            axis: 'left',
            xField: 'type',
            yField: 'dataOne',
            style: {
                opacity: 0.80
            },
            highlight: {
                fill: '#000',
                'stroke-width': 20,
                stroke: '#fff'
            },
            tips: {
                trackMouse: true,
                style: 'background: #FFF',
                height: 20,
                renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('type') + ': ' + storeItem.get('dataOne'));
                }
            }
        }]*/
    }
	return chartPanel;
}
