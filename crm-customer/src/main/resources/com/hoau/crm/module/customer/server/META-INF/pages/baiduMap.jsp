<%@page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=utf-8">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
<%@include file="../bse/common.jsp"%>



<script type="text/javascript" src="../scripts/common/ext-hoau.js"></script>
<script type="text/javascript" src="../scripts/common/common.js"></script>
<script type="text/javascript" src="../scripts/common/crm-util.js"></script>
<script type="text/javascript" src="../scripts/common/commonSelector.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTimePicker.js"></script>
<script type="text/javascript" src="../scripts/common/datetime/DateTime.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GKpF5kBmwGGcVRGgBzUe5AvE"></script>
<script type="text/javascript" src="${scripts}/baiduMap/baiduMap.js"></script>
<script type="text/javascript" src="${scripts}/baiduMap/DistanceTool.js"></script> 
<!-- <script type="text/javascript" src="http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js"></script> -->

<link href="../styles/customer/customer.css" rel="stylesheet" type="text/css" /> 
<style type="text/css">
		body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
</style>
</head>

<body>
	<div id="allmap" style="right: auto; left: 0px; top: 70px; height: 470px; margin: 0px; " ></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,14);
	map.enableScrollWheelZoom(true); //鼠标滑动轮子可以滚动
	var myDis = new BMapLib.DistanceTool(map);   //测距插件
	var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});//添加卫星和混合地图
	//var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_RIGHT});
	var polygon = null;//设置多边形变量
	var userScopeBool = true;//确认当前用户是否用服务范围
	var ctrlNav = new window.BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE
    });
	 map.addControl(ctrlNav);
	 map.addControl(mapType1);
	// map.addControl(mapType2);
	
	 //向地图中添加缩略图控件
     var ctrlOve = new window.BMap.OverviewMapControl({
         anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
         isOpen: 1
     });
     map.addControl(ctrlOve);
      //向地图中添加比例尺控件
     var ctrlSca = new window.BMap.ScaleControl({
         anchor: BMAP_ANCHOR_TOP_LEFT
     });
     map.addControl(ctrlSca);
   	//根据IP获取当前城市
	function myFun(result){
   		if(userScopeBool){
			var cityName = result.name;
			map.setCenter(cityName);
		}
		//alert("当前定位城市:"+cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);

	var markerArr = new Array();
	var scopeEntryEntities = new Array();
	//var index=0; 
	//鼠标点击获取经纬度
	 /**
	map.addEventListener("click",function(e){
		//alert(e.point.lng + "," + e.point.lat);
		var point = new BMap.Point(e.point.lng, e.point.lat); //默认
        // 创建标注对象并添加到地图  
        marker = new BMap.Marker(point);
        var userScopeEntry = {};
        userScopeEntry.lan = e.point.lng;
        userScopeEntry.lat = e.point.lat;
        scopeEntryEntities.push(userScopeEntry);
        markerArr[index] = marker;
        var label = new BMap.Label("标记"+(index+1), { offset: new BMap.Size(20, -10) });
        marker.setLabel(label);
        map.addOverlay(marker);
        index++;
	});*/
	
	var markerArrlen = [];
	//完成测距后的时间
	myDis.addEventListener("drawend", function(e) { 
		//清空所有覆盖物
     	map.clearOverlays();
		//清理绘制的所有数据；
     	myDis._clearCurData();
     	polygon = new BMap.Polygon(e.points, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
   	    map.addOverlay(polygon);   //增加多边形
   	    //开启可编辑状态用于微调
   	 	polygon.enableEditing();
   	 //	markerArr.push(userScopeEntry);
   	 	//把每个坐标添加到数据里面
   	 	/**
   	 	for(var i = 0;i<e.points.length;i++){
   	 		var point = e.points[i];
	   	 	var userScopeEntry = {};
	        userScopeEntry.lan = point.lng;
	        userScopeEntry.lat = point.lat;
	        scopeEntryEntities.push(userScopeEntry);
	        markerArr.push(point);
   	 	}*/
		//alert(e.points);  
	});
	
   	var menu = new BMap.ContextMenu(); //右键菜单
   	//画圆
   	var circle;
     var txtMenuItem = [  //右键菜单项目
         {
         text: '重置范围',
         callback: function () {
         	/**for (var i = 0; i < markerArr.length; i++) {
         		var marker = markerArr[i];
         		map.removeOverlay(marker);
         	}*/
         	markerArr = new Array();
         	scopeEntryEntities = new Array();
         	//重新设定
         //	index=0; 
         	//清空所有覆盖物
         	map.clearOverlays(); 
         }
 		},
 		{
 	         text: '开启设定范围',
 	        callback:function(){myDis.open();}
 	 	}
     ];
     for (var i = 0; i < txtMenuItem.length; i++) {
         menu.addItem(new BMap.MenuItem(txtMenuItem[i].text, txtMenuItem[i].callback, 100)); //菜单添加项目
     }
     map.addContextMenu(menu);
     
     //查找标点之间的最长半径距离
     function findCenter(){
    	 var num = new Array();
     	var temp = 0;
     	//alert(markerArr.length);
     	if(markerArr.length > 0){
	    	 for (var i = 0; i < markerArr.length; i++) {
	       		var markerA = markerArr[i];
	       		if(markerA != null){
		       		for(var j = 1; j < markerArr.length; j++){
		       			var markerB = markerArr[j];
		       			if(markerB != null){
			       			var mm = (map.getDistance(markerA,markerB)).toFixed(2);
			       			if(mm != 0){
				       			num[temp] = (map.getDistance(markerA,markerB)).toFixed(2);
				       			var info = {};
				       			info.len =(map.getDistance(markerA,markerB)).toFixed(2);
				       			info.markerA = markerA;
				       			info.markerB = markerB;
				       			//把每一个经纬度 距离记录下来
				       			markerArrlen.push(info);
				       			temp++;
			       			}
		       			}
		       		}
	       		}
	       	}
    	 }
    	 return num;
 	}
     
     //得到距离最长的经纬度
     function maxLen(data){
	     len = markerArrlen.length;
	     if(len > 0){
	    	 for (var i=0; i<len;i++){ 
			     var v = markerArrlen[i]; 
			     if (v.len == data){ 
			     	return v;
			     } 
		     } 
	     }
     }
     
    //去除重复
     function unique(data){ 
	     data = data || []; 
	     var a = {}; 
	     len = data.length; 
	     for (var i=0; i<len;i++){ 
		     var v = data[i]; 
		     if (typeof(a[v]) == 'undefined'){ 
		     	a[v] = 1; 
		     } 
	     } 
	     data.length=0; 
	     for (var i in a){ 
	    	data[data.length] = i; 
	     } 
	     return data; 
     } 
     

     
   /**  function getMaxNum(num){
 		var temp;
 		for(var i=0;i<num.length-1;i++){
 			for(var j=0;j<num.length-i-1;j++){
 				if(num[j]<num[j+1]){
 					temp = num[j];
 					num[j]=num[j+1];
 					num[j+1]=num[j];
 				}
 			}
 		}
 		return num[0];
 	}*/
    
</script>
