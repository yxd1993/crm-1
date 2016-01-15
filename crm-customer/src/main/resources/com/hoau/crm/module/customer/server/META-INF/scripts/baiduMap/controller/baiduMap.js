/**
 * 客户信息Controller
 */
Ext.define('crm.controller.baiduMap', {
    extend: 'Ext.app.Controller',
    views: ['Viewport','baiduMap.Search'],
    init: function () {
        this.control({
            'userScopeSearch button[action=reset]': {
                click: this.resetUserScopeSearchForm
            },
            'userScopeSearch button[action=select]': {
                click: this.reloadUserScopeGridStore
            },
            'userScopeSearch button[action=setrange]': {
                click: this.reloadUserScopeSetrange
            },
            'userScopeSearch button[action=tomark]': {
                click: this.reloadUserScopeToMark
            },
            'userScopeSearch button[action=confirm]': {
                click: this.reloadUserScopeConfirm
            }
        });
        
        //加载初始坐标
        crm.requestJsonAjax('userScopeAction!queryUserScopeInfo.action', null, 
				function(response){
    				var scopeEntryArr = response.scopeEntryEntities;
    				var points = new Array();
    				if(scopeEntryArr.length > 0){
    					userScopeBool = false;
	    				for (var i=0; i<scopeEntryArr.length;i++){ 
	    				     var v = scopeEntryArr[i]; 
	    				     var point = new BMap.Point(v.lan, v.lat); //默认
					        //根据坐标设置切换城市
//					        if(i == 0)
//					        	map.panTo(point); 
						     // 创建标注对象并添加到地图  
	//	   				     marker = new BMap.Marker(point);
	//				        var label = new BMap.Label("标记"+(i+1), { offset: new BMap.Size(20, -10) });
	//				        marker.setLabel(label);
					        points.push(point);
	//				        //传后台值
	//				        var userScopeEntry = {};
	//				        userScopeEntry.lan = v.lan;
	//				        userScopeEntry.lat = v.lat;
	//				        scopeEntryEntities.push(userScopeEntry);
	//				        map.addOverlay(marker);
	    			     }
						polygon = new BMap.Polygon(points, {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
				   	    map.addOverlay(polygon);   //增加多边形
				   	    //开启可编辑状态用于微调
				   	 	polygon.enableEditing();
				   	 	map.setViewport(points);
	    				//index = scopeEntryArr.length;
    				}
				}, 
		function(response){
					Ext.MessageBox.alert('提示', response.message);});
        
    },reloadUserScopeSetrange:function(btn){
    	myDis.open();
    },
    resetUserScopeSearchForm : function(btn){
    	// 清空查询条件
    	btn.up('form').getForm().reset();
    },
    reloadUserScopeGridStore : function(btn){
    	var form = Ext.getCmp('userScopeViewId').getUserScopeSearchForm().getForm();
    	if(Ext.isEmpty(form.findField('provinceCode').getValue())){
    		Ext.MessageBox.alert('提示','省份不能为空'); 
			return;
    	}
		if(Ext.isEmpty(form.findField('cityCode').getValue())){
			Ext.MessageBox.alert('提示','城市不能为空'); 
			return;
		}
		
//		if(markerArr.length > 0 || scopeEntryEntities.length){
//			Ext.MessageBox.alert('提示','切换城市前请先重新标记'); 
//			return;
//		}
		
		//如果区为空就用城市
		if(Ext.isEmpty(form.findField('areaCode').getRawValue())){
			map.setCenter(form.findField('cityCode').getRawValue());
		}
		else{
			map.setCenter(form.findField('areaCode').getRawValue());
		}
    },
    //重新标记
    reloadUserScopeToMark : function(btn){
    	/**for (var i = 0; i < markerArr.length; i++) {
     		var marker = markerArr[i];
     		map.removeOverlay(marker);
     	}*/
     	markerArr = new Array();
     	scopeEntryEntities = new Array();
     	//index = 0;
     	//清空所有覆盖物
     	map.clearOverlays(); 
    },
    //确认标记
    reloadUserScopeConfirm : function(btn){
    	var ploygongeo = "";
    	if(polygon != null && polygon.getPath().length > 0){
    		//先清空历史标记
    		markerArr = new Array();
         	scopeEntryEntities = new Array();
    		//得到多边形所有的坐标
    		var points = polygon.getPath();
    		for(var i = 0;i<points.length;i++){
       	 		var point = points[i];
    	   	 	var userScopeEntry = {};
    	        userScopeEntry.lan = point.lng;
    	        userScopeEntry.lat = point.lat;
    	        userScopeEntry.sort = i;//设置排序规则
    	        scopeEntryEntities.push(userScopeEntry);
    	        markerArr.push(point);
    	        //拼接经纬度
    	        ploygongeo +=  point.lng + ' ' + point.lat +";";
    	        
       	 	}
    	}
    	var num = new Array();
     	num = findCenter();
     	if(num.length >0 && map.getOverlays().length > 0){
     		//alert(num);
     		//去重复
	        	num = unique(num);
	        	//排序之后的数组
	        	num = num.sort(function(a,b){return b-a;});
//	        	alert(num);
	        	//根据距离得到两点的经纬度
	        	var data = maxLen(num[0]);
	        	//中心点坐标经纬度
	        	var lngA = data.markerA.lng;
	        	var latA = data.markerA.lat;
	        	var lngB = data.markerB.lng;
	        	var latB = data.markerB.lat;
	        	//(A+((B-A)/2)),(A+((B-A)/2))
	        	//通过扣减得到中心点坐标
	        	var pointC = new BMap.Point((lngA+((lngB-lngA)/2)),(latA+((latB-latA)/2)));
	        	//alert(pointC);
	        	//写范围之前删除覆盖物
	        	var win = btn.up('window');
	        	
	        	// 保存信息
	        	var params = {};
	        	var userScopeEntity = {};
	        	userScopeEntity.maxlength = num[0];
	        	userScopeEntity.lngA = lngA;
	        	userScopeEntity.latA = latA;
	        	userScopeEntity.lngB = lngB;
	        	userScopeEntity.latB = latB;
	        	userScopeEntity.centerlng = (lngA+((lngB-lngA)/2));
	        	userScopeEntity.centerlat = (latA+((latB-latA)/2));
	        	//设置拼接的经纬度
	        	userScopeEntity.ploygongeo = ploygongeo;
	        	//设置每个坐标的值
	        	userScopeEntity.scopeEntryEntities = scopeEntryEntities;
	        	params.userScopeEntity = userScopeEntity;
	    		// AJAX请求
	    		crm.requestJsonAjax('userScopeAction!addUserScopeInfo.action', params, 
	    				function(response){
	    					Ext.MessageBox.alert('提示','用户确认服务范围成功');
	    					//win.close();
	    				}, 
	    				function(response){Ext.MessageBox.alert('提示', response.message);});
	        	
	        	
     	}else{
     		Ext.MessageBox.alert('提示','请先设定范围'); 
     		return;
     	}
    }
});
