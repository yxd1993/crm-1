package com.hoau.crm.module.common.server.datasource;

/**
 * @author：高佳
 * @create：2015年11月23日 上午9:05:20
 * @description：数据源context上下文
 */
public class DataSourceContextHolder {
	/**
	 * 
	 */
	private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<DataSourceType>();

	/**
	 * 设置数据源类型
	 * 
	 * @param dataSourceType
	 * @author 高佳
	 * @date 2015年11月23日
	 * @update
	 */
	public static void setDataSourceType(DataSourceType dataSourceType) {
		if (dataSourceType == null) {
			throw new NullPointerException();
		}
		contextHolder.set(dataSourceType);
	}

	/**
	 * 获取数据源类型
	 * 
	 * @author 高佳
	 * @date 2015年11月23日
	 * @update
	 */
	public static DataSourceType getDataSourceType() {
		return contextHolder.get();
	}

	/**
	 * 清空数据源类型
	 * @author 高佳
	 * @date 2015年11月23日
	 * @update 
	 */
	public static void clearDataSourceType() {
		contextHolder.remove();
	}
}
