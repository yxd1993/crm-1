//Ext.application({
//    name: "crm",
//    appFolder: '../scripts/customer/nearCustomerMap',
//    controllers: ["nearCustomerMap"],
//    /*autoCreateViewport: true,*/
//    autoCreateViewport: true,
//    launch: function () {
//        // 页面加载完成之后执行
//    	//params.clientIP = returnCitySN["cip"];
//    	//Ext.MessageBox.alert('提示', params.clientCoordinates);
//    	 //加载初始坐标
//    }
//});

/**
 * 客户信息查询FORM
 */
Ext.define("crm.view.nearCustomerMap.Search", {
    extend: "Ext.form.Panel",
    alias: 'widget.nearCustomerSearch',
    width : '100%',
    height : 70,
    layout: 'column',
    region : 'north',
    defaults:{
		msgTarget : 'qtip',
		margin : '5 0 5 0 ',
		labelWidth : 100,
		columnWidth : 0.195,
		labelAlign: 'right'
	},
    defaultType: 'textfield',
    items: [ {
		xtype : "districtProvinceSelector",
		name : "provinceCode",
		fieldLabel : "省"
	}, {
		xtype : "districtCitySelector",
		name : "cityCode",
		fieldLabel : "市"
	}, {
		xtype : "districtAreaSelector",
		name : "areaCode",
		fieldLabel : "区"
	}
	],
    buttons: [{
        text: '查询',
        action : 'select',
        handler : function(btn) {
        	var form = this.up('form').getForm();
        	if(Ext.isEmpty(form.findField('provinceCode').getValue())){
        		Ext.MessageBox.alert('提示','省份不能为空'); 
    			return;
        	}
    		if(Ext.isEmpty(form.findField('cityCode').getValue())){
    			Ext.MessageBox.alert('提示','城市不能为空'); 
    			return;
    		}
    		var result ={};
    		//如果区为空就用城市
    		if(Ext.isEmpty(form.findField('areaCode').getRawValue())){
    			result.name = form.findField('cityCode').getRawValue();
    		}else{
    			result.name = form.findField('areaCode').getRawValue();
    		}
    		// 创建地址解析器实例
    		var myGeo = new BMap.Geocoder();
    		// 将地址解析结果显示在地图上,并调整地图视野
    		myGeo.getPoint(result.name, function(point){
    			if (point) {
    				initRequestNearCustomer(point);
    			}else{
    				alert("客户信息加载失败!");
    			}
    		}, form.findField('cityCode').getRawValue());
		}
    }, {
        text: '清空',
        action : 'reset',
        handler : function() {
        	this.up('form').getForm().reset();
        }
    }]
});
var loading;
Ext.onReady(function() {
	Ext.QuickTips.init();
	var topSarch = Ext.create('crm.view.nearCustomerMap.Search');
	var panel1 = Ext.create('Ext.panel.Panel', {
		renderTo : 'topSarch_div',
		items : [ topSarch ]
	});
	loading = new Ext.LoadMask(
	{
	        msg : 'Loading...',
	        target : panel1
	  });
});
function showWait(){
 	return Ext.Msg.show({
 		title:'请等待',
 	    msg: '客户正在努力加载中......',
 	    wait:true,
 	    closable: false ,
 	    waitConfig: {interval:150}
    });
 };
function initRequestNearCustomer(point){
	//loading.show();
	//显示进度条
	showWait();
	//清除覆盖物
	map.clearOverlays();
	//设置地图视野
	map.centerAndZoom(point, 16);
	//设置地图坐标给后台
	params.clientCoordinates = [point.lng,point.lat];
	//基于定位的这个点的点位创建marker
	var mk = new BMap.Marker(point); 
	var label = new BMap.Label("中心位置", { offset: new BMap.Size(20, -10) });
	mk.setLabel(label);
    map.addOverlay(mk);    //将marker作为覆盖物添加到map地图上
 	 //跳动的动画
    mk.setAnimation(BMAP_ANIMATION_BOUNCE); 
    //请求数据
	crm.requestJsonAjax('customerLatlngAction!getNearCustomerScopeLatLng.action', params, 
			function(response){
				var point = new Array(); //存放标注点经纬信息的数组
			    var marker = new Array(); //存放标注点对象的数组
			    var info = new Array(); //存放提示信息窗口对象的数组
			    var searchInfoWindow =new Array();//存放检索信息窗口对象的数组
				var customerLatlngEntities = response.customerLatlngEntities;
				for (var i=0; i<customerLatlngEntities.length;i++){ 
				     var v = customerLatlngEntities[i]; 
				     point[i] = new window.BMap.Point(v.lng, v.lat); //循环生成新的地图点
				     // 创建标注对象并添加到地图  
 				     marker[i] = new BMap.Marker(point[i]);
				        var label = new BMap.Label(v.enterpriseName, { offset: new BMap.Size(20, -10) });
				        marker[i].setLabel(label);
				     //跳动的动画
				      // marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); 
				       map.addOverlay(marker[i]);
				       // 创建信息窗口对象
				    var content = '<div style="margin:0;line-height:20px;padding:2px;-webkit-user-select:auto;">' +
	                    '<img src="../images/customer/Penguins.jpg" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
	                    '地址：';
	                    content += v.detailedAddress;
	                    content += '<br/>联系人：';
	                    if(null != v.contactName)
	                    	content += v.contactName;
	                    else
	                    	content += '无';
	                    content += '<br/>电话：';
	                    if(null != v.cellphone){
	                    	content += v.cellphone;}
	                    else if(null != v.districtNumber && null != v.telephone){
	                    	content += v.districtNumber;
	                    	content += '-';
	                    	content += v.telephone;
	                    }else{
	                    	content += '无';
	                    }
	                    content += '<br/>企业全称：';
	                    content += v.enterpriseName;
	                    content += '</div>';
	                info[i]=content;
		    	    //创建检索信息窗口对象
		    		searchInfoWindow[i] = new BMapLib.SearchInfoWindow(map, info[i], {
		    				title  : "客户信息",      //标题
		    				width  : 320,             //宽度
		    				height : 105,              //高度
		    				panel  : "panel",         //检索结果面板
		    				enableAutoPan : true,     //自动平移
		    				searchTypes   :[
		    					BMAPLIB_TAB_SEARCH,   //周边检索
		    					BMAPLIB_TAB_TO_HERE,  //到这里去
		    					BMAPLIB_TAB_FROM_HERE //从这里出发
		    				]
		    			});
		    		
		    		//添加点击事件
                    marker[i].addEventListener("click", 
                        (function(k){
                            // js 闭包
                            return function(){
                                //将被点击marker置为中心
                                //map.centerAndZoom(point[k], 18);
                                //在marker上打开检索信息窗口
                                searchInfoWindow[k].open(marker[k]);
                            };
                        })(i)                            
                    ); 
			     } 
				Ext.Msg.hide();
			}, 
	function(response){Ext.Msg.hide();Ext.MessageBox.alert('提示', "客户加载失败");});
}