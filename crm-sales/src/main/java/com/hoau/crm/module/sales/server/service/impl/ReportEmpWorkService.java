/**
 * 
 */
package com.hoau.crm.module.sales.server.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.hoau.crm.module.appcore.api.shared.exception.ReportDataAppException;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.shared.domain.ReportDataEntity;
import com.hoau.crm.module.sales.api.server.service.IReportEmpWorkService;
import com.hoau.crm.module.sales.api.server.service.ISaleChatsService;
import com.hoau.crm.module.sales.api.shared.domain.ReportEmpWorkEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.server.dao.ReportCustomerCountMapper;
import com.hoau.crm.module.sales.server.dao.ReportEmpWorkMapper;
import com.hoau.crm.module.util.UUIDUtil;

/**
 * 个人统计 service
 * 
 * @author 丁勇
 * @date 2015年7月20日
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ReportEmpWorkService implements IReportEmpWorkService {
	@Resource
	ReportEmpWorkMapper empworkmapper;
	@Resource
	ISaleChatsService salechatsservice;
	@Resource
	ReportCustomerCountMapper customerCountMapper;
	//时间格式化
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public ReportEmpWorkEntity queryCurrentEmpWork(String empCode) {
		return empworkmapper.queryCurrentEmpWork(empCode);
	}

	@Override
	public List<ReportEmpWorkEntity> queryEmpWorkList(Map<String, Object> map,
			RowBounds rb) {
		// 分页条件
		if (StringUtils.isEmpty(rb)) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		return empworkmapper.queryEmpWorkList(getParameters(map), rb);
	}

	@Override
	public long queryEmpWorkListCount(Map<String, Object> map) {
		return empworkmapper.queryEmpWorkListCount(getParameters(map));
	}

	@Override
	public List<Map<String, Object>> queryEmpWorkListDetail(
			Map<String, Object> map, RowBounds rb) {
		// 分页条件
		if (StringUtils.isEmpty(rb)) {
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		return empworkmapper.queryEmpWorkListDetail(getParameters(map), rb);
	}

	@Override
	public long queryEmpWorkListDetailCount(Map<String, Object> map) {
		return empworkmapper.queryEmpWorkListDetailCount(getParameters(map));
	}

	@Override
	public List<Map<String, Object>> queryEmpWorkIndex(Map<String, Object> map) {
		// 当前登录用户,(门店报表需要获取工号查询是否是到货门店)
		UserEntity currentUser = (UserEntity) map.get("currUser");
		// 指标返回结果列表List Map
		List<Map<String, Object>> indexListMaps = new ArrayList<Map<String, Object>>();
		// 获取查询条件时间段
		List<String> dayList = getReportWeek(format);
		//获取年月,用于查询当前年月的指标值
		Map<String,Object> searchIndexMap = returnYearAndMonth();
		// 用于查询指定时间段的拜访次数值的map
		Map<String, Object> searchMap = new HashMap<String, Object>();
		// 指标值
		long index = 0;
		// 有选择,则按选择工号查询 (选择的为工号为门店)
		if (!StringUtils.isEmpty(map.get("account"))) {
			//选择的员工工号
			searchMap.put("empCode", map.get("empCode"));
			//查询当前登录工号的角色编码
			Set<String> roleCodes = empworkmapper.queryUserRole(map.get("account").toString());
			//事业部
			if (roleCodes.contains("1004")){
				//事业部角色编号
				searchIndexMap.put("roleCode","1004");
				//查询登录角色当前年月下的指标值
				index = empworkmapper.queryUserRoleIndex(searchIndexMap);
				//无指标设置默认值
				if(index==0){
					index = BseConstants.REPORT_BUSINESS;
				}
			//大区
			} else if (roleCodes.contains("1005")) {
				//大区角色编号
				searchIndexMap.put("roleCode","1005");
				//查询登录角色当前年月下的指标值
				index = empworkmapper.queryUserRoleIndex(searchIndexMap);
				//无指标设置默认值
				if(index==0){
					index = BseConstants.REPORT_REGION;
				}
			// 路区
			} else if (roleCodes.contains("1010")) {
				//大区角色编号
				searchIndexMap.put("roleCode","1010");
				//查询登录角色当前年月下的指标值
				index = empworkmapper.queryUserRoleIndex(searchIndexMap);
				//无指标设置默认值
				if(index==0){
					index = BseConstants.REPORT_ROAD;
				}
			// 门店
			} else if(roleCodes.contains("1006")){
				// 根据工号获取门店类型
				String storeType = empworkmapper.queryStoreType(map.get("empCode").toString());
				//判断是否是到货门店
				// 无到货门店
				if (BseConstants.NOT_ARRIVAL_STORE.equals(storeType)) {
					//无到货门店角色编码赋值
					searchIndexMap.put("roleCode", "100601");
					//查询登录角色当前年月下的指标值
					index = empworkmapper.queryUserRoleIndex(searchIndexMap);
					//无指标设置默认值
					if(index==0){
						index = BseConstants.REPORT_NOT_ARRIVAL;
					}
				// 到货门店
				} else if (BseConstants.ARRIVAL_STORE.equals(storeType)) {
					//有到货门店角色编码赋值
					searchIndexMap.put("roleCode", "100602");
					//查询登录角色当前年月下的指标值
					index = empworkmapper.queryUserRoleIndex(searchIndexMap);
					//无指标设置默认值
					if(index==0){
						index = BseConstants.REPORT_ARRIVAL;
					}
				}
			}
		} else {
			// 无选择,根据登录工号获取门店类型(登录者是门店角色)
			String storeType = empworkmapper.queryStoreType(currentUser
					.getEmpEntity().getEmpCode());
			// 登录门店的员工工号
			searchMap.put("empCode",currentUser.getEmpEntity().getEmpCode());
			//判断是否是到货门店
			// 无到货门店
			if (BseConstants.NOT_ARRIVAL_STORE.equals(storeType)) {
				//无到货门店角色编码赋值
				searchIndexMap.put("roleCode", "100601");
				//查询登录角色当前年月下的指标值
				index = empworkmapper.queryUserRoleIndex(searchIndexMap);
				//无指标设置默认值
				if(index==0){
					index = BseConstants.REPORT_NOT_ARRIVAL;
				}
			// 到货门店
			} else if (BseConstants.ARRIVAL_STORE.equals(storeType)) {
				//有到货门店角色编码赋值
				searchIndexMap.put("roleCode", "100602");
				//查询登录角色当前年月下的指标值
				index = empworkmapper.queryUserRoleIndex(searchIndexMap);
				//无指标设置默认值
				if(index==0){
					index = BseConstants.REPORT_ARRIVAL;
				}
			}
		}
		// 获取拜访频率
		for (int i = 0; i < dayList.size(); i++) {
			if(i%2==0){
				searchMap.put("startDate", dayList.get(i));
				searchMap.put("endDate", dayList.get(i + 1));
				//封装 返回结果
				Map<String, Object> resultMap = new HashMap<String, Object>();
				//指标
				resultMap.put("index", index);
				try {
					resultMap.put("startDate", format.parse(dayList.get(i)));
					resultMap.put("endDate",  format.parse(dayList.get(i + 1)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new SalesCommonException(SalesCommonException.ADD_DATETIME_INVALID);
				}
				resultMap.put("chats", empworkmapper.queryEmpWorkIndex(searchMap));
				indexListMaps.add(resultMap);
			}
		}
		return indexListMaps;
	}
	
	@Override
	public Map<String, Object> queryCountCustomerByEmp(
			Map<String, Object> map) {
		//统计结果map
		Map<String, Object> countCustomerMap = new HashMap<String, Object>();
		// 登录客户
		UserEntity currentUser;
		if(map.containsKey("account")){
			// 登录客户
			currentUser = (UserEntity) map.get("choseUser");
		}else{
			// 选择的客户
			currentUser = (UserEntity) map.get("currUser");
		}
		// 区别岗位 统计客户总数和发货客户数和新增客户数
		// 获取选择人员的岗位名称
		String jobname = currentUser.getEmpEntity().getJobname();
		// 查看客户统计是否是客户经理
		if (jobname.equals(BseConstants.MANAGENAME)) {
			// 本月新增客户数
			countCustomerMap.put("addCustomer", customerCountMapper
					.countNewCustomer(currentUser.getEmpEntity().getEmpCode()));
			// 客户总数
			countCustomerMap.put("countCustomer", customerCountMapper
					.countCustomerOfManage(currentUser.getEmpEntity()
							.getEmpCode()));
			// 发货客户数
			countCustomerMap.put("DeliveryCustomer", customerCountMapper
					.countDeliveryCustomerOfManage(currentUser.getEmpEntity()
							.getEmpCode()));
		} else {
			String deptCode = "";
			if(currentUser.getEmpEntity() != null || currentUser.getEmpEntity().getDeptEntity() != null){
				deptCode = currentUser.getEmpEntity().getDeptEntity().getDeptCode();
			}
			// 本月新增客户数
			countCustomerMap.put(
					"addCustomer",
					customerCountMapper.countNewCustomer(deptCode));
			//客户总数和发货客户数
			ReportDataEntity reportDataEntity = customerCountMapper.queryDeliverCustomerCountData(deptCode);
			// 本月客户总数
			countCustomerMap.put("countCustomer",reportDataEntity != null ? reportDataEntity.getDataOne():0);
			// 发货客户数
			countCustomerMap.put("DeliveryCustomer",reportDataEntity != null ? reportDataEntity.getDataTwo():0);
		}
		return countCustomerMap;
	}
	@Override
	public List<Map<String, Object>> querEmpAllIndex() {
		//年月指标查询参数
		Map<String,Object> map = returnYearAndMonth();
		//每周查询参数
		Calendar cal = Calendar.getInstance();
		if(cal.get(Calendar.DAY_OF_WEEK)==1){
			cal.add(Calendar.WEEK_OF_YEAR, -1);
		}
		//获取本周一的日期
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		map.put("startDate",format.format(cal.getTime()));
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		//增加一个星期(以周日结束)
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("endDate",format.format(cal.getTime()));
		//查询是否有指标
		queryIndexByDate(map);
		//本周事业部,大区,路区未完成指标的员工
		List<Map<String,Object>> empIndexNoStore = empworkmapper.querEmpIndexNoStore(map);
		//本周门店(到货和无到货门店)未完成指标的员工
		List<Map<String,Object>> empIndexByStore = empworkmapper.querEmpIndexByStore(map);
		//所有未完成指标的集合返回
		empIndexByStore.addAll(empIndexNoStore);
		return empIndexByStore;
	}
	/**
	 * 查询是否有指标,否则初始化默认指标
	 * @param map
	 * @author 丁勇
	 * @date 2015年8月25日
	 * @update 
	 */
	@Transactional
	public  void queryIndexByDate(Map<String,Object> map){
		long count = empworkmapper.queryIndexCountByDate(map);
		if(count<=0){
			for (int i = 0; i < 6; i++) {
				String uid = UUIDUtil.getUUID();
				map.put("id", uid);
				if(i==0){
					map.put("indexNum", BseConstants.REPORT_NOT_ARRIVAL);
					map.put("roleCode", "100601");
				}else if(i==1){
					map.put("indexNum", BseConstants.REPORT_ARRIVAL);
					map.put("roleCode", "100602");
				}else if(i==2){
					map.put("indexNum", BseConstants.REPORT_BUSINESS);
					map.put("roleCode", "1004");
				}else if(i==3){
					map.put("indexNum", BseConstants.REPORT_REGION);
					map.put("roleCode", "1005");
				}else if(i==4){
					map.put("indexNum", BseConstants.REPORT_ROAD);
					map.put("roleCode", "1010");
				}else if(i==5){
					map.put("indexNum", BseConstants.REPORT_CEO);
					map.put("roleCode", "1001");
				}
				empworkmapper.insertDefaultIndex(map);
			}
		}
	}
	/**
	 * 无选择参数,个人工作列表查询参数配置
	 * 
	 * @param map
	 * @return
	 * @author 丁勇
	 * @date 2015年7月21日
	 * @update
	 */
	public Map<String, Object> getParameters(Map<String, Object> map) {
		// 当前登录用户
		UserEntity currentUser = (UserEntity) map.get("currUser");
		// 返回参数
		Map<String, Object> params = new HashMap<String, Object>();
		// 判断登录用户
		if (StringUtils.isEmpty(map.get("currUser"))) {
			throw new ReportDataAppException(
					ReportDataAppException.LOGININFO_NULL);
		}
		// 判断是否是模块1的跳转,判断模块2的需要显示的报表类型
		if (!StringUtils.isEmpty(map.get("account"))) {
			// 取得选择的工号
			params.put("empCode", map.get("empCode"));
		} else {
			params.put("currUser", currentUser);
			// 登录工号
			params.put("empCode", currentUser.getEmpEntity().getEmpCode());
			if (currentUser != null
					&& currentUser.getEmpEntity() != null
					&& !StringUtils.isEmpty(currentUser.getEmpEntity()
							.getEmpCode())) {
				// 判断是否为部门负责人
				if (salechatsservice.isDeptManeger(currentUser.getEmpEntity()
						.getEmpCode())) {
					params.put("deptCode", currentUser.getEmpEntity()
							.getDeptEntity().getDeptCode());
				}
			}
		}
		return params;
	}
	/**
	 * 获取当前月的每一个星期的周一和周日日期
	 * @return
	 * @author 丁勇
	 * @date 2015年7月22日
	 * @update
	 */
	public List<String> getReportWeek(SimpleDateFormat format) {
		List<String> dayList = new ArrayList<String>();
		// 获取当前月的每一个星期的日期
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, 1);
		int month = calendar.get(Calendar.MONTH);
		while (calendar.get(Calendar.MONTH) == month) {
			if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
				dayList.add(format.format(calendar.getTime()));
				calendar.add(Calendar.DATE, 6);
				dayList.add(format.format(calendar.getTime()));
			}
			calendar.add(Calendar.DATE, 1);
		}
		return dayList;
	}
	/**
	 * 获取当前时间的年,月,对每月指标进行查询
	 * @return
	 * @author 丁勇
	 * @date 2015年8月19日
	 * @update 
	 */
	public  Map<String,Object> returnYearAndMonth(){
		Map<String,Object> map = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		map.put("year",c.get(Calendar.YEAR));
		map.put("month",c.get(Calendar.MONTH)+1);
		return map;
	}
	
	// main
	public static void main(String[] args) {
		//创建一个线程池
//		ExecutorService  exexutorService = Executors.newCachedThreadPool();
//		int cpuNums = Runtime.getRuntime().availableProcessors();
//        //获取当前系统的CPU 数目
//		ExecutorService executorService = Executors.newFixedThreadPool(cpuNums * 6);
//		System.out.println(cpuNums);
	}
}
