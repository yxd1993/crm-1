package com.hoau.crm.module.common.server.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author：高佳
 * @create：2015年11月23日 上午9:10:01
 * @description：动态路由数据源
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDataSourceType();
	}

}
