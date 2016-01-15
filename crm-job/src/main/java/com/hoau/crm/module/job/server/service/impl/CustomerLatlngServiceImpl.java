package com.hoau.crm.module.job.server.service.impl;

import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.common.server.util.PolygonUtil;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.api.shared.domain.UserScopeEntity;
import com.hoau.crm.module.job.server.dao.CustomerLatlngJobMapper;
import com.hoau.crm.module.job.server.service.ICustomerLatlngJobService;

/**
 * @author 275636
 *客户坐标Service
 */
@org.springframework.stereotype.Service
public class CustomerLatlngServiceImpl implements ICustomerLatlngJobService {

	/**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(CustomerLatlngServiceImpl.class);
	
	@Resource
	private CustomerLatlngJobMapper customerLatlngJobMapper;
	
	/** 
	 * 客户坐标初始化
	 */
	@Override
	public void customerInitLatlngJob() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		LOG.info("customerInitLatlngJob start : "
				+ sDateFormat.format(new Date()) + "");
		RowBounds rb = new RowBounds(0, 2000);		
		List<CustomerEntity> customerEntities = customerLatlngJobMapper.getinitializeCustomerLatLng(rb);
		if(customerEntities != null){
			LOG.info("customerEntities size: " + customerEntities.size());
		} else {
			LOG.info("customerEntities is null");
		}
		if(!customerEntities.isEmpty()){
			List<CustomerLatlngEntity> latlngEntities = new ArrayList<CustomerLatlngEntity>();
			List<String> ids = new ArrayList<String>();
			for(int index = 0,len=customerEntities.size();index<len;index++){
				Map<String,String> map2 = LatitudeUtils.getLatitude(customerEntities.get(index).getDetailedAddress(), "");
				if(map2 == null){
					continue;
				}else if(map2.size() > 0){
					//新增完成之后给客户添加经纬度
					CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
					customerLatlngEntity.setId(UUID.randomUUID().toString());
					//代表客户共享里面添加
					customerLatlngEntity.setType("1");
					//设置客户ID
					customerLatlngEntity.setCustomerId(customerEntities.get(index).getId());
					//维度
					customerLatlngEntity.setLat(Double.parseDouble(map2.get("lat").toString()));
					//经度
					customerLatlngEntity.setLng(Double.parseDouble(map2.get("lng").toString()));
					//是否精准
					customerLatlngEntity.setPrecise(map2.get("precise").toString());
					//可信度
					customerLatlngEntity.setConfidence(map2.get("confidence").toString());
					latlngEntities.add(customerLatlngEntity);
				}else
					// 得不到坐标的下次不再扫描
					ids.add(customerEntities.get(index).getId());
			}
			if(!latlngEntities.isEmpty())
				customerLatlngJobMapper.addBatchCustomerLatlng(latlngEntities);
			if(ids.size() > 0)
				customerLatlngJobMapper.updateBatchCustomerIsUpdate(ids);
		}
		LOG.info("customerInitLatlngJob end : "
				+ sDateFormat.format(new Date()) + "");
	}

	public static String getHHMMSSstr(long timeLong) {
		long h = 60 * 60 * 1000;
		long m = 60 * 1000;
		long s = 1000;

		long hh = timeLong / h;
		long mm = (timeLong % h) / m;
		long ss = ((timeLong % h) % m) / s;
		long mill = ((timeLong % h) % m) % s;

		String msg = String.valueOf(hh) + ":" + String.valueOf(mm) + ":"
				+ String.valueOf(ss) + "   " + String.valueOf(mill);
		return msg;
	}

	/** 
	 * 客户自动分配范围
	 */
	@Override
	public void customerScopeJob() {
		long beginTime = System.currentTimeMillis();
		RowBounds rb = new RowBounds(0, 2000);
		List<CustomerInfoPoolEntity> customerInfoPoolEntities = customerLatlngJobMapper.getCustomerNotdistributionInfo(rb);
		if(!customerInfoPoolEntities.isEmpty() && customerInfoPoolEntities.size() > 0){
			//客户坐标集合
			List<CustomerLatlngEntity> latlngEntities = new ArrayList<CustomerLatlngEntity>();
			//可以更新客户负责人的客户集合
			List<CustomerInfoPoolEntity> infoPoolEntities = new ArrayList<CustomerInfoPoolEntity>();
			
			// 获取公司所有用户服务范围
			List<UserScopeEntity> entities = customerLatlngJobMapper.queryByUserScopeInfo();
			
			for(int index = 0,len = customerInfoPoolEntities.size();index<len;index++){
				CustomerInfoPoolEntity customerInfoPoolEntity = customerInfoPoolEntities.get(index);
				//得到经纬度坐标
				//用公司地址和城市得到坐标
				Map<String,String> map = LatitudeUtils.getLatitude(customerInfoPoolEntity.getCompanyAddress(), customerInfoPoolEntity.getCity());
				if(map != null && map.size() > 0){
					//新增完成之后给客户添加经纬度
					CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
					customerLatlngEntity.setId(UUID.randomUUID().toString());
					//代表客户共享里面添加
					customerLatlngEntity.setType("0");
					customerLatlngEntity.setCustomerId(customerInfoPoolEntity.getId());
					//维度
					customerLatlngEntity.setLat(Double.parseDouble(map.get("lat").toString()));
					//经度
					customerLatlngEntity.setLng(Double.parseDouble(map.get("lng").toString()));
					//是否精准
					customerLatlngEntity.setPrecise(map.get("precise").toString());
					//可信度
					customerLatlngEntity.setConfidence(map.get("confidence").toString());
					//保存经纬度之后确定客户范围
//					List<UserScopeEntity> entities = customerLatlngJobMapper.queryCustomerByUserScopeInfo(map);
//					if(!entities.isEmpty() && entities.size() > 0){
//						customerInfoPoolEntity.setManageEmpCode(entities.get(0).getUser_id());
//						customerInfoPoolEntity.setManagePerson(entities.get(0).getUser_name());
//						//已分发
//						customerInfoPoolEntity.setDispenseStatus("2");
//						infoPoolEntities.add(customerInfoPoolEntity);
//					}
					//创建一个客户2d坐标
					Point2D.Double point = new Point2D.Double(Double.parseDouble(map.get("lng").toString()), Double.parseDouble(map.get("lat").toString()));
					//得到客户负责人
					String[] khManage = getCustomerPerson(point,entities);
					if(null != khManage){
						latlngEntities.add(customerLatlngEntity);
						customerInfoPoolEntity.setManageEmpCode(khManage[0]);
						customerInfoPoolEntity.setManagePerson(khManage[1]);
						//已分发
						customerInfoPoolEntity.setDispenseStatus("2");
						infoPoolEntities.add(customerInfoPoolEntity);
					}
				}
			}
			//批量添加客户坐标
			if(!latlngEntities.isEmpty())
				customerLatlngJobMapper.addBatchCustomerLatlng(latlngEntities);
			//批量更新客户负责人
			if(!infoPoolEntities.isEmpty())
				customerLatlngJobMapper.updateBatchCustomerManagePerson(infoPoolEntities);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>确认客户所属那个销售范围使用时间>>>" + getHHMMSSstr(System.currentTimeMillis() - beginTime));
	}

	/**
	 * @param point
	 * @param entities
	 * @return
	 */
	private String[] getCustomerPerson(java.awt.geom.Point2D.Double point,
			List<UserScopeEntity> entities) {
		String[] khManage = new String[2];
		if(!entities.isEmpty() && entities.size() > 0){
			List<UserScopeEntity> userScopes = new ArrayList<UserScopeEntity>();
			for (UserScopeEntity scopeEntity : entities) {
				if(null != scopeEntity.getPloygongeo() && !"".equals(scopeEntity.getPloygongeo())){
					String[] points = scopeEntity.getPloygongeo().split(";");
					List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
					for(int j=0;j<points.length;j++){
						String[] xy  = points[j].split(" ");
						Point2D.Double double1 = new Point2D.Double(Double.parseDouble(xy[0]), Double.parseDouble(xy[1]));
						polygon.add(double1);
					}
					if(polygon.size() > 0){
						boolean temp = PolygonUtil.checkWithJdkGeneralPath(point, polygon);
						if(temp)//添加所属的用户范围
							userScopes.add(scopeEntity);
					}else
						continue;
				}
			}
			
			if(userScopes.size() > 0)
			{
				//只找到一个用户就直接return
				if(userScopes.size() == 1)
					return new String[]{userScopes.get(0).getUser_id(),userScopes.get(0).getUser_name()};
				//门店 用户
				List<String> md = new ArrayList<String>();
				List<UserScopeEntity> mdkeyValue = new ArrayList<UserScopeEntity>();
				//客户经理 用户
				List<String> xs = new ArrayList<String>();
				List<UserScopeEntity> xskeyValue = new ArrayList<UserScopeEntity>();
				for(int j = 0;j<userScopes.size();j++){
					if(userScopes.get(j).getRole_code().equals("1006")){
						md.add(userScopes.get(j).getUser_id());
						mdkeyValue.add(userScopes.get(j));
					}
					else if(userScopes.get(j).getRole_code().equals("1007")){
						xs.add(userScopes.get(j).getUser_id());
						xskeyValue.add(userScopes.get(j));
					}
				}
				//证明有门店用户，就不考虑客户经理，优先给门店产值高的门店
				if(md.size() > 0){
					String users = customerLatlngJobMapper.getMaxStoresOutputUsers(md);
					if(null != users && !"".equals(users)){
						khManage = users.split(";");
						return khManage;
					}else 
						return new String[]{mdkeyValue.get(0).getUser_id(),mdkeyValue.get(0).getUser_name()};
				}else{//没有门店就按照客户经理负责的客户产值高的人员分配
					String users = customerLatlngJobMapper.getMaxSalesManagerOutputUsers(xs);
					if(null != users && !"".equals(users)){
						khManage = users.split(";");
						return khManage;
					}else 
						return new String[]{xskeyValue.get(0).getUser_id(),xskeyValue.get(0).getUser_name()};
				}
			}else
				return null;
		}
		return null;
	}
}
