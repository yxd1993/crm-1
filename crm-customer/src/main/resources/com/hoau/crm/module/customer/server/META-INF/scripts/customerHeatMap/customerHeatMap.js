Ext.onReady(function() {
	Ext.QuickTips.init();
	var fbHeatMap = Ext.create('crm.view.customerHeatMap.customerfbHeatMap');
	var viewport = Ext.create('Ext.Viewport', {
		layout : {
			type : 'fit',//用border布局 ，把页面分为上下部分 form为上、grid为下
			padding : 5
		},
		frame:true,
		items : [fbHeatMap],
	});
//	var panel1 = Ext.create('Ext.panel.Panel', {
//		layout : 'fit',
//		renderTo : 'container',
//		items : [ fbHeatMap ]
//	});
	var map = new BMap.Map("container1",{minZoom:5});          // 创建地图实例

	var point = new BMap.Point(116.418261, 39.921984);
	map.centerAndZoom(point, 5);             // 初始化地图，设置中心点坐标和地图级别
	map.enableScrollWheelZoom(); // 允许滚轮缩放
	map.enableContinuousZoom();
	var points = null;
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	map.addOverlay(heatmapOverlay);
	crm.requestJsonAjax('customerLatlngAction!getCustomerGroupCount.action', null, 
			function(response){
				points = response.customerGroupEntities;
				heatmapOverlay.setDataSet({data:points});
			}, 
		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户分布热力图加载失败");});
	 heatmapOverlay.hide();
});
Ext.define("crm.view.customerHeatMap.customerfbHeatMap", {
    extend: "Ext.panel.Panel",
    alias: 'widget.customerfbHeatMap',
    width : '100%',
    layout: 'fit',
    region : 'north',
	items:[{
		xtype:'tabpanel',
		layout : 'fit',
		height:200,
		activeTab:0,
		items:[{
			title:"客户分布热力图",
			xtype: 'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        height : 420,
	        collapsible: true,
	        collapsed: false, 
	        autoScroll : true,
	        layout: 'column',
	    	items : [{
	    		id : 'container1',
	    		xtype : 'panel',
	    		height : 470,
	    		columnWidth : 1,
	    	},{
	    	    id : 'image_button1', 
	    	    xtype : 'button',
	    	    text : '显示热力图',
	    	    handler : function() {
	    	    	heatmapOverlay.show();
	    	    }
	    	},{
	    	    id : 'image_button2', 
	    	    xtype : 'button',
	    	    text : '关闭热力图',
	    	    handler : function() {
	    	    	 heatmapOverlay.hide();
	    	    }
	    	}],
	    	listeners : {
	    		'activate' : function(tab) {
	    			heatmapOverlay.hide();
	    		}
	    	}
		},{
			title:"客户产值热力图",
			xtype: 'form',
			columnWidth : 1,
	        animCollapse: true,
	        bodyPadding: 5,
	        collapsible: true,
	        collapsed: false,
	        layout: 'column',
	        height : 420,
	        autoScroll : true,
	    	items : [{
	    		id : 'container2',
	    		xtype : 'panel',
	    		height : 470,
	    		columnWidth : 1,
	    	},{
	    	    id : 'image_button3', 
	    	    xtype : 'button',
	    	    text : '显示热力图',
	    	    handler : function() {
	    	    	heatmapOverlay1.show();
	    	    }
	    	},{
	    	    id : 'image_button4', 
	    	    xtype : 'button',
	    	    text : '关闭热力图',
	    	    handler : function() {
	    	    	heatmapOverlay1.hide();
	    	    }
	    	}],
	    	listeners : {
//	    		'activate' : function(tab) {
//	    			 var mapOutput= new BMap.Map("container2");          // 创建地图实例
//	    			 var point1 = new BMap.Point(116.418261, 39.921984);
//	    			 mapOutput.centerAndZoom(point1, 5);             // 初始化地图，设置中心点坐标和地图级别
//	    			 mapOutput.enableScrollWheelZoom(); // 允许滚轮缩放
//
//	    			var pointsoutput = null;
//	    			heatmapOverlay1 = new BMapLib.HeatmapOverlay({"radius":20});
//	    			mapOutput.addOverlay(heatmapOverlay1);
//	    			crm.requestJsonAjax('customerLatlngAction!getCustomerHeatMapOutPut.action', null, 
//	    					function(response){
//	    						pointsoutput = response.customerHeatOutPutEntities;
//	    						heatmapOverlay1.setDataSet({data:pointsoutput});
//	    						//alert(Ext.encode(points));
//	    					}, 
//	    				function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户产值热力图加载失败");});
//	    			heatmapOverlay1.hide();
//	    		}
	    	}
		}],listeners:{
			'tabchange':function(tabPanel, newCard, oldCard){
				if(newCard.title =="客户产值热力图"){
					var mapOutput= new BMap.Map("container2",{minZoom:5});          // 创建地图实例
		   			 var point1 = new BMap.Point(116.418261, 39.921984);
		   			 mapOutput.centerAndZoom(point1, 5);             // 初始化地图，设置中心点坐标和地图级别
		   			 mapOutput.enableScrollWheelZoom(); // 允许滚轮缩放
		
		   			var pointsoutput = null;
		   			heatmapOverlay1 = new BMapLib.HeatmapOverlay({"radius":20});
		   			mapOutput.addOverlay(heatmapOverlay1);
		   			crm.requestJsonAjax('customerLatlngAction!getCustomerHeatMapOutPut.action', null, 
		   					function(response){
		   						pointsoutput = response.customerHeatOutPutEntities;
		   						heatmapOverlay1.setDataSet({data:pointsoutput});
		   						//alert(Ext.encode(points));
		   					}, 
		   				function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户产值热力图加载失败");});
		   			heatmapOverlay1.hide();
				}
			}
		}
	}]
	
});

function setGradient(){
 	/*格式如下所示:
	{
  		0:'rgb(102, 255, 0)',
 	 	.5:'rgb(255, 170, 0)',
	  	1:'rgb(255, 0, 0)'
	}*/
 	var gradient = {};
 	var colors = document.querySelectorAll("input[type='color']");
 	colors = [].slice.call(colors,0);
 	colors.forEach(function(ele){
		gradient[ele.getAttribute("data-key")] = ele.value; 
 	});
    heatmapOverlay.setOptions({"gradient":gradient});
}
