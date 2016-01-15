/**
 * 获取当前登录用户信息
 * 从父页面中获取当前登录用户信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getUserContext(){
	return window.parent.UserContext;
}
/**
 * 获取数据字典信息
 * 从父页面中获取数据字典信息
 * @returns
 * @author 高佳
 * @date 2015年5月15日
 * @update
 */
function getDataDictionary(){
	return window.parent.DataDictionary;
}

/**
 * 判断当前用户是否有此权限
 * 
 * @param authCode
 * @returns
 * @author 蒋落琛
 * @date 2015-6-2
 * @update
 */
function isButtonHide(functionCode){
	return window.parent.UserContext.isButtonHide(functionCode);
}
/**
 * 时间格式化
 * 
 * @param form
 * @returns {Array}
 * @author 丁勇
 * @date 2015年7月17日
 * @update
 */
function getDateTime(form){
	var startDate = form.findField('startDate').getValue();
	var hour = form.findField('hour').getValue();
	var minute = form.findField('minute').getValue();
	var endDate = form.findField('differentDate').getValue();
	var dateArr = [];
	var reserveStartTime = new Date(Date.parse(Ext.util.Format.date(startDate,'Y-m-d')+" "+hour+":"+minute));
	var reserveEndTime = new Date(Date.parse(Ext.util.Format.date(startDate,'Y-m-d')+" "+hour+":"+minute)+(endDate*60*1000));
    dateArr.push(reserveStartTime,reserveEndTime);
	return dateArr;
}
/**
 * 获取时间后格式化时间
 * 
 * @param startDateTime
 * 开始
 * @param endDateTime
 * 结束
 * @returns {Array}
 * @author 丁勇
 * @date 2015年7月18日
 * @update
 */
function setDateTime(startDateTime,endDateTime){
	var dateArr =[];
	//开始时间
	var start = Ext.isEmpty(startDateTime) ? null : Ext.util.Format.date(new Date(startDateTime), 'Y-m-d H:i:s');
	//结束时间
	var end = Ext.isEmpty(endDateTime) ? null : Ext.util.Format.date(new Date(endDateTime), 'Y-m-d H:i:s');
	//预约时长
	var differentDate = (Date.parse(end)-Date.parse(start))/60/1000;
	//时间截取
	var date  = start.split(' ');
	var time = date[1].toString().split(':');
	//约定日期
	var startDate = date[0];
	//约定时
	var hour = time[0];
	//约定分
	var minute = time[1];
	dateArr.push(startDate,hour,minute,differentDate);
	return  dateArr;
}
/**
 * 陪同信息赋值
 * @author 丁勇
 * @date 2015-10-09
 * @update
 */
function setEntourageValue(form, response){
		var departmentVo = response.departmentVo;
		var currUserEmpCode = getUserContext().getCurrentUserEmp().empCode;
		//门店
		if(departmentVo.tierDisplayName==null){
			form.findField('comTierEmpCode').hide();
			form.findField('tierEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.tierEmpCode){
				form.findField('comTierEmpCode').hide();
				form.findField('tierEmpName').hide();
			}else{
				form.findField('comTierEmpCode').show();
				form.findField('tierEmpName').show();
				form.findField('tierEmpName').setValue(departmentVo.tierEmpName);
				form.findField('tierEmpName').setEmpCode(departmentVo.tierEmpCode);
			}
		}
		//路区
		if(departmentVo.roadDisplayName==null){
			form.findField('comRoadEmpCode').hide();
			form.findField('roadEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.roadEmpCode){
				form.findField('comRoadEmpCode').hide();
				form.findField('roadEmpName').hide();
			}else{
				form.findField('comRoadEmpCode').show();
				form.findField('roadEmpName').show();
				form.findField('roadEmpName').setValue(departmentVo.roadEmpName);
				form.findField('roadEmpName').setEmpCode(departmentVo.roadEmpCode);
			}
		}
		//大区
		if(departmentVo.regionsDisplayName==null){
			form.findField('comRegionsEmpCode').hide();
			form.findField('regionsEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.regionsEmpCode){
				form.findField('comRegionsEmpCode').hide();
				form.findField('regionsEmpName').hide();
			}else{
				form.findField('comRegionsEmpCode').show();
				form.findField('regionsEmpName').show();
				form.findField('regionsEmpName').setValue(departmentVo.regionsEmpName);
				form.findField('regionsEmpName').setEmpCode(departmentVo.regionsEmpCode);
			}
		}
		//事业部
		if(departmentVo.businessUnitDisplayName==null){
			form.findField('comBusinessEmpCode').hide();
			form.findField('businessUnitEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.businessUnitEmpCode){
				form.findField('comBusinessEmpCode').hide();
				form.findField('businessUnitEmpName').hide();
			}else{
				form.findField('comBusinessEmpCode').show();
				form.findField('businessUnitEmpName').show();
				form.findField('businessUnitEmpName').setValue(departmentVo.businessUnitEmpName);
				form.findField('businessUnitEmpName').setEmpCode(departmentVo.businessUnitEmpCode);
			}
		}
		//副总
		if(departmentVo.odDisplayName==null){
			form.findField('comOdEmpCode').hide();
			form.findField('odEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.odEmpCode){
				form.findField('comOdEmpCode').hide();
				form.findField('odEmpName').hide();
			}else{
				form.findField('comOdEmpCode').show();
				form.findField('odEmpName').show();
				form.findField('odEmpName').setValue(departmentVo.odEmpName);
				form.findField('odEmpName').setEmpCode(departmentVo.odEmpCode);
			}
		}
		//总裁
		if(departmentVo.ceoDisplayName==null){
			form.findField('comCeoEmpCode').hide();
			form.findField('ceoEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.ceoEmpCode){
				form.findField('comCeoEmpCode').hide();
				form.findField('ceoEmpName').hide();
			}else{
				form.findField('comCeoEmpCode').show();
				form.findField('ceoEmpName').show();
				form.findField('ceoEmpName').setValue(departmentVo.ceoEmpName);
				form.findField('ceoEmpName').setEmpCode(departmentVo.ceoEmpCode);
			}
		}
		
		//团队经理
		if(departmentVo.teamManagerDisplayName==null){
			form.findField('teamManagerEmpCode').hide();
			form.findField('teamManagerEmpName').hide();
		}else{
			if(currUserEmpCode==departmentVo.teamManagerEmpCode){
				form.findField('teamManagerEmpCode').hide();
				form.findField('teamManagerEmpName').hide();
			}else{
				form.findField('teamManagerEmpCode').show();
				form.findField('teamManagerEmpName').show();
				form.findField('teamManagerEmpName').setValue(departmentVo.teamManagerName);
				form.findField('teamManagerEmpName').setEmpCode(departmentVo.teamManagerCode);
			}
		}
		//客户经理
		if(departmentVo.saleManDisplayName==null){
			form.findField('saleManEmpCode').hide();
			form.findField('saleManEmpName').hide();	
		}else{
			if(currUserEmpCode==departmentVo.saleManEmpCode){
				form.findField('saleManEmpCode').hide();
				form.findField('saleManEmpName').hide();
			}else{
				form.findField('saleManEmpCode').show();
				form.findField('saleManEmpName').show();
				form.findField('saleManEmpName').setValue(departmentVo.saleManEmpName);
				form.findField('saleManEmpName').setEmpCode(departmentVo.saleManEmpCode);
			}
		}
}
/**
 * 地址,手机隐藏
 * @author 丁勇
 * @date 2015-8-14
 * @update
 */
//当前登录工号
var currenEmpCode = getUserContext().getCurrentUserEmp().empCode;

//手机,住宅联系方式处理
function contactTypeRenderer(value,meta,record){
	if(record.get("manageEmpCode")!=currenEmpCode){
		return "***********";
	}else{
		if(Ext.isEmpty(value)){
			return record.get('telephone');
		}else{
			return value;
		}
	}
}
//客户联系地址,手机隐藏处理
function isHidden(value,meta,record){
	if(record.get("manageEmpCode")!=currenEmpCode){
		return "***********";
	}else{
		return value;
	}
}
/**
 * 按规则隐藏
 * @param manageEmpCode
 * @returns {Boolean}
 * @author 丁勇
 * @date 2015年10月27日
 * @update
 */
function isHiddenByRules(manageEmpCode){
	//先判断是否是负责人
	if(manageEmpCode==currenEmpCode){
		return true;
	}else{
		//根据角色的配置隐藏功能,默认显示,配置
		if(isButtonHide('101001013')){
			return true;
		}else{
			return false;
		}
	}
}