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
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
  <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
<script type="text/javascript" src="${scripts}/nearCustomerMap/nearCustomerMap.js"></script> 
<!-- JavaScript获取客户端IP[利用搜狐接口] 
<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>-->
<link href="../styles/customer/customer.css" rel="stylesheet" type="text/css" /> 
 <style type="text/css">
  body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
  #allmap {height: 100%;width:100%;overflow: hidden;}
  #result {width:100%;font-size:12px;}
  dl,dt,dd,ul,li{
    margin:0;
    padding:0;
    list-style:none;
  }
  dt{
    font-size:14px;
    font-family:"微软雅黑";
    font-weight:bold;
    border-bottom:1px dotted #000;
    padding:5px 0 5px 5px;
    margin:5px 0;
  }
  dd{
    padding:5px 0 0 5px;
  }
  li{
    line-height:28px;
  }
  </style>
</head>

<body>
<div style="width: 100%; height: 70px; right: auto; left: 0px; top: 0px; margin: 0px;" id="topSarch_div"></div>
	<div id="allmap" style="right: auto; left: 0px; top: 0px; height: 470px; margin: 0px; " ></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,12);
	map.enableScrollWheelZoom(true); //鼠标滑动轮子可以滚动
	map.enableInertialDragging();
	var ctrlNav = new window.BMap.NavigationControl({
        anchor: BMAP_ANCHOR_TOP_LEFT,
        type: BMAP_NAVIGATION_CONTROL_LARGE,
        enableGeolocation: false
    });
	 map.addControl(ctrlNav);
	 //向地图中添加缩略图控件
     var ctrlOve = new window.BMap.OverviewMapControl({
         anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
         isOpen: 1
     });
     map.addControl(ctrlOve);
      //向地图中添加比例尺控件
     var ctrlSca = new window.BMap.ScaleControl({
         anchor: BMAP_ANCHOR_BOTTOM_LEFT
     });
     map.addControl(ctrlSca);
    //设置城市坐标请求参数
     var params = {};
	//根据IP获取当前城市
	function myFun(result){
		map.clearOverlays();
		var cityName = result.name;
		//map.setCenter(cityName);
		//维度
		//params.clientCoordinates = [result.center.lng,result.center.lat];
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(cityName, function(point){
			if (point) {
				initRequestNearCustomer(point);
			}else{
				alert("客户信息加载失败!");
			}
		}, cityName);
		//alert("当前定位城市:"+cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	
	//鼠标拖拽结束事件 
	map.addEventListener("dragend", function(evt){
		initRequestNearCustomer(evt.point);
	});
	
	 // 添加定位控件
	  var geolocationControl = new BMap.GeolocationControl();
	  geolocationControl.addEventListener("locationSuccess", function(e){
		  initRequestNearCustomer(e.point); 
	  });
	  geolocationControl.addEventListener("locationError",function(e){
	    // 定位失败事件
	    alert(e.message);
	  });
	  map.addControl(geolocationControl);
</script>
