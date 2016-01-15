/**
 * 客户信息Controller
 */
Ext.define('crm.controller.nearCustomerMap', {
    extend: 'Ext.app.Controller',
    views: ['Viewport','nearCustomerMap.Search'],
    init: function () {
        this.control({
            'nearCustomerSearch button[action=select]': {
                click: this.reloadNearCustomerGridStore
            },'nearCustomerSearch button[action=reset]': {
                click: this.resetNearCustomerSearchForm
            }
        });
    },
    resetNearCustomerSearchForm : function(btn){
    	// 清空查询条件
    	btn.up('form').getForm().reset();
    },
    reloadNearCustomerGridStore : function(btn){
    	var form = Ext.getCmp('nearCustomerViewId').getNearCustomerSearchForm().getForm();
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
				map.clearOverlays();
				map.centerAndZoom(point, 12);
				params.clientCoordinates = [point.lng,point.lat];
				var mk = new BMap.Marker(point);    //基于定位的这个点的点位创建marker
				var label = new BMap.Label("中心位置", { offset: new BMap.Size(20, -10) });
				mk.setLabel(label);
		        map.addOverlay(mk);    //将marker作为覆盖物添加到map地图上
		     	 //跳动的动画
		        mk.setAnimation(BMAP_ANIMATION_BOUNCE); 
		        initRequestNearCustomer();
			}else{
				alert("客户信息加载失败!");
			}
		}, form.findField('cityCode').getRawValue());
    }
});
