package com.hoau.hbdp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.hoau.crm.module.appcore.api.shared.domain.SignEntity;
import com.hoau.crm.module.appcore.api.shared.vo.CustomerAppVo;
import com.hoau.crm.module.appcore.api.shared.vo.ReviewHistoryAppVo;
import com.hoau.crm.module.bse.api.shared.domain.DepartmentEntity;
import com.hoau.crm.module.bse.api.shared.domain.PhoneInfoEntity;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.vo.DepartmentVo;
import com.hoau.crm.module.bse.api.shared.vo.ReviewHistoryVo;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.hbdp.framework.shared.util.JsonUtils;

public class test {

	public static void main(String[] args) {
			/*ReviewHistoryAppVo v = new ReviewHistoryAppVo();
			List<ReviewHistoryVo> reviewhistoryvo = new ArrayList<ReviewHistoryVo>();
			ReviewHistoryVo v1 = new ReviewHistoryVo();
			List<ReviewHistoryEntity> v2 = new ArrayList<ReviewHistoryEntity>();
			ReviewHistoryEntity v3 = new ReviewHistoryEntity();
			v3.setCustomerEntity(new CustomerEntity());
			v3.setContactEntity(new ContactEntity());
			v3.setReserveEntity(new SaleReserveEntity());
			v3.setChatsEntity(new SaleChatsEntity());
			v3.setSignEntity(new SignEntity());
			v2.add(v3);
			v1.setReviewHistoryEntityList(v2);
			reviewhistoryvo.add(v1);
			v.setReviewhistoryvo(reviewhistoryvo);*/
		
			CustomerAppVo c1 = new CustomerAppVo();
			CustomerEntity c2 = new CustomerEntity();
			ContactEntity v1 = new ContactEntity();
			c2.setContactEntity(v1);
			c1.setCustomerEntity(c2);
			DepartmentEntity v2 = new DepartmentEntity();
			DepartmentVo c3 = new DepartmentVo();
			c3.setDeptEntity(v2);
			c1.setDepartmentVo(c3);
			CustomerEntity v3 = new CustomerEntity();
			List<CustomerEntity> c4 = new ArrayList<CustomerEntity>();
			c4.add(v3);
			c1.setCustomerEntityList(c4);
			CustomerInfoPoolEntity v4 = new CustomerInfoPoolEntity();
			List<CustomerInfoPoolEntity> c5 = new ArrayList<CustomerInfoPoolEntity>();
			c5.add(v4);
			c1.setCustomerPooList(c5);
			ContactEntity v5 = new ContactEntity();
			List<ContactEntity> c6 = new ArrayList<ContactEntity>();
			c6.add(v5);
			c1.setContactEntityList(c6);
			ContactEntity c7 = new ContactEntity();
			c1.setContactEntity(c7);
			PhoneInfoEntity c8 = new PhoneInfoEntity();
			c1.setPhoneInfoEntity(c8);
			List<String> c9 = new ArrayList<String>();
			c1.setIds(c9);
			
			String json = JSON.toJSONString(c1,SerializerFeature.WriteMapNullValue);
			System.out.println(json);
	}
	
}
