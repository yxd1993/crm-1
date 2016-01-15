/**
 * 
 */
package com.hoau.crm.module.bse.api.server.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 数据结果返回处理类.用于自定义map
 * @author 丁勇
 * @date 2015年7月22日
 */
public class DataTypeHandler implements TypeHandler<Long> {

	@Override
	public Long getResult(ResultSet rs, String columnName) throws SQLException {
		if(rs.getLong(columnName)==0l){
			return 0l;
		}
		return rs.getLong(columnName);
	}

	@Override
	public Long getResult(ResultSet rs, int columnIndex) throws SQLException {
		if(rs.getLong(columnIndex)==0l){
			return 0l;
		}
		return null;
	}

	@Override
	public Long getResult(CallableStatement arg0, int arg1)
			throws SQLException {
		return null;
	}

	@Override
	public void setParameter(PreparedStatement arg0, int arg1, Long arg2,
			JdbcType arg3) throws SQLException {
	}


}
