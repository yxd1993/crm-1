<%@page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<%@include file="../bse/common.jsp"%>

<script type="text/javascript" src="../scripts/common/ext-hoau.js"></script>
<script type="text/javascript" src="../scripts/common/common.js"></script>
<script type="text/javascript" src="../scripts/common/crm-util.js"></script>
<script type="text/javascript" src="../scripts/common/commonSelector.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTimePicker.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTime.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GKpF5kBmwGGcVRGgBzUe5AvE"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js"></script> 
<script type="text/javascript" src="${scripts}/customerHeatMap/customerHeatMap.js"></script>
  <style type="text/css">
		ul,li{list-style: none;margin:0;padding:0;float:left;}
		html{height:100%}
		body{height:100%;margin:0px;padding:0px;font-family:"微软雅黑";}
		#container1{height:500px;width:100%;}
		#container2{height:500px;width:100%;}
		#r-result{width:100%;}
    </style>	
</head>
<body>

</body>
</html>
<script type="text/javascript">
//判断浏览区是否支持canvas
function isSupportCanvas(){
    var elem = document.createElement('canvas');
    return !!(elem.getContext && elem.getContext('2d'));
}
if(!isSupportCanvas()){
	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
}
</script>
<!-- 
<div id="container"></div>
	<div id="r-result">
		<input type="button"  onclick="openHeatmap();" value="显示热力图"/><input type="button"  onclick="closeHeatmap();" value="关闭热力图"/>
	</div> 
<script type="text/javascript">
    var map = new BMap.Map("container1");          // 创建地图实例

    var point = new BMap.Point(116.418261, 39.921984);
    map.centerAndZoom(point, 5);             // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(); // 允许滚轮缩放
  
    var points = null;
	heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
	map.addOverlay(heatmapOverlay);
    crm.requestJsonAjax('customerLatlngAction!getCustomerGroupCount.action', null, 
			function(response){
    			points = response.customerGroupEntities;
    			heatmapOverlay.setDataSet({data:points});
    			//alert(Ext.encode(points));
			}, 
		function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户加载失败");});
   
    if(!isSupportCanvas()){
    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
    }
	//详细的参数,可以查看heatmap.js的文档 https://github.com/pa7/heatmap.js/blob/master/README.md
	//参数说明如下:
	/* visible 热力图是否显示,默认为true
     * opacity 热力的透明度,1-100
     * radius 势力图的每个点的半径大小   
     * gradient  {JSON} 热力图的渐变区间 . gradient如下所示
     *	{
			.2:'rgb(0, 255, 255)',
			.5:'rgb(0, 110, 255)',
			.8:'rgb(100, 0, 255)'
		}
		其中 key 表示插值的位置, 0~1. 
		    value 为颜色值. 
     */
	//是否显示热力图
    function openHeatmap(){
        heatmapOverlay.show();
    }
	function closeHeatmap(){
        heatmapOverlay.hide();
    }
	closeHeatmap();
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
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }
</script>

-->


