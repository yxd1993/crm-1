package com.hoau.crm.module.customer.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.common.server.util.LatitudeUtils;
import com.hoau.crm.module.customer.api.server.ICustomerLatlngService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerGroupEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerLatlngEntity;
import com.hoau.crm.module.customer.server.dao.CustomerLatlngMapper;
import com.hoau.crm.module.customer.server.dao.CustomerMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerLatlngService implements ICustomerLatlngService {
	
	@Resource
	private CustomerLatlngMapper customerLatlngMapper;
	
	@Resource
	private CustomerMapper customerMapper;

	@Override
	public void addCustomerLatlng(CustomerLatlngEntity customerLatlngEntity) {
		customerLatlngMapper.addCustomerLatlngInfo(customerLatlngEntity);

	}

	@Override
	public void addBatchCustomerLatlng(List<CustomerLatlngEntity> latlngEntities) {
		customerLatlngMapper.addBatchCustomerLatlng(latlngEntities);
	}

	/**
	 * 获取附近10公里范围内的客户信息
	 * @return
	 */
	@Override
	public List<CustomerLatlngEntity> getNearCustomerScopeLatLng(String[] clientCoordinates) {
//		//根据IP地址获取城市经纬度
//		String[] centerlatlng =  LatitudeUtils.getIPXY(clientIP);
//		if(centerlatlng == null)
//			return null;
		if(clientCoordinates == null)
			return null;
		//根据中心经纬度获取10公里范围内的最大最小经纬度
		double[] ds = LatitudeUtils.getAround(Double.parseDouble(clientCoordinates[1]), Double.parseDouble(clientCoordinates[0]), 10000/4);
		Map<String,String> map = new HashMap<String,String>();
		map.put("minLat", ds[1]+"");
		map.put("minLng", ds[0]+"");
		map.put("maxLat", ds[3]+"");
		map.put("maxLng", ds[2]+"");
		map.put("lat", clientCoordinates[0]);
		map.put("lng", clientCoordinates[1]);
		
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functions = currentUser.getFunctionCodes();
		if(!StringUtil.isEmpty(currentUser.getUserName())){
			map.put("createdBy", currentUser.getUserName());
		}
		//所有数据权限
		if(!functions.contains(BseConstants.ALLDATA_CUSTOMERMANAGER)){
			//是否有新门店研究组数据权限
			if(functions.contains(BseConstants.FUNCTION_AUTH_NEWSTOREDATA)){
				map.put("newStoreData", BseConstants.MANAGENAME);
			}else if(functions.contains(BseConstants.FUNCTION_AUTH_TACTICCUSTOMER)){
				//是否有战略客户部数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
					map.put("tacticCustomerDeptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
				}
			}else if(functions.contains(BseConstants.FUNCTION_AUTH_CUSTOMERMANAGE)){
				//是否有客户管理部数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getDeptEntity().getDeptCode())){
					map.put("customerManage",BseConstants.YES);
				}
			}else if(functions.contains(BseConstants.ALLDATA_SUBCOMPANYCUSTOMERMANAGER)){
				//普通权限
				DepartmentEntity currDepartmentEntity = currentUser.getEmpEntity().getDeptEntity();
				if(!StringUtil.isEmpty(currDepartmentEntity.getDeptCode())){
					Map<String,String> map1 = customerMapper.getIsStoreInfo(currDepartmentEntity.getDeptCode());
					//是门店
					if(BseConstants.IS_STORE.equals(map1.get("isStore"))){
						map.put("storeAuth", map1.get("logistCode"));
					}else if(customerMapper.isRoadByDeptCode(currDepartmentEntity.getDeptCode())>0){
						map.put("roadDeptcode", currDepartmentEntity.getDeptCode());
					}else{
						map.put("userDeptcode", currDepartmentEntity.getDeptCode());
					}
				}
			}else{
				//客户经理数据权限
				if(!StringUtil.isEmpty(currentUser.getEmpEntity().getEmpCode())){
					map.put("salePerson", currentUser.getEmpEntity().getEmpCode());
				}
			}
		}
		List<CustomerLatlngEntity> customerLatlngEntities = new ArrayList<CustomerLatlngEntity>();
		customerLatlngEntities = customerLatlngMapper.getNearCustomerScopeLatLng(map);
		return customerLatlngEntities;
	}

	/**
	 * 初始化客户信息
	 * */
	@Override
	public void initializeCustomerLatLng() {
		long beginTime = System.currentTimeMillis();
		long count = customerLatlngMapper.countCustomer();
		int BATCH_SIZE = 1000;
		int batch = (int) (count%BATCH_SIZE==0?count/BATCH_SIZE:count/BATCH_SIZE+1);
		for(int i = 0 ; i < batch ; i++){
			
			Map<String, Integer> params = new HashMap<String,Integer>();
			params.put("start", (BATCH_SIZE*i));
			params.put("end", (BATCH_SIZE*(i+1)));
			List<CustomerEntity> customerEntities = customerLatlngMapper.getinitializeCustomerLatLng(params);
			if(!customerEntities.isEmpty()){
				List<CustomerLatlngEntity> latlngEntities = new ArrayList<CustomerLatlngEntity>();
				for(int index = 0,len=customerEntities.size();index<len;index++){
					Map map2 = LatitudeUtils.getLatitude(customerEntities.get(index).getDetailedAddress(), "");
					if(map2 != null && map2.size() > 0){
						//新增完成之后给客户添加经纬度
						CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
						customerLatlngEntity.setId(UUIDUtil.getUUID());
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
					}
				}
				if(!latlngEntities.isEmpty())
					customerLatlngMapper.addBatchCustomerLatlng(latlngEntities);
			}
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>接口数据反写结束>>>" + getHHMMSSstr(System.currentTimeMillis() - beginTime));
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

	@Override
	public List<CustomerGroupEntity> getCustomerGroupCount() {
		return customerLatlngMapper.getCustomerGroupCount();
	}

	@Override
	public List<CustomerGroupEntity> getCustomerHeatMapOutPut() {
		return customerLatlngMapper.getCustomerHeatMapOutPut();
	}

}
