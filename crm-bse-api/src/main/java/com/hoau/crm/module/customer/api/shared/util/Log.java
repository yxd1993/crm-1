
/**   
 * @title Log.java
 * @package com.deppon.ump.module.platform.server.util
 * @description 
 * @author cbb   
 * @update 2012-10-12 上午11:59:20
 * @version V1.0   
 */
 
package com.hoau.crm.module.customer.api.shared.util;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.hbdp.framework.server.components.dataaccess.ibatis.IBatis3DaoImpl;


/**
 * @description 
 * @version 1.0
 * @author wwn
 * @update 2013-06-11 上午11:59:20 
 */
public class Log extends IBatis3DaoImpl implements ILog {
	@Override
	//保存异常日志信息
	public void saveExceptionLog(String exceptionPosition,Exception e){
		String operator=UserUitl.getEmpCode();
		if(operator==null){
			operator="用户不存在";
		}
		ExceptionLog exceptionLog = new ExceptionLog(exceptionPosition,LogUtil.logFormat(e),operator);
		this.getSqlSession().insert("com.hoau.crm.module.customer.api.shared.util.ExceptionLog.saveExceptionLog",exceptionLog);
		
	}
	
	@Override
	public void saveExceptionLogList(String exceptionPosition,List<BamSysException> es){
		for(BamSysException e:es){
			this.saveExceptionLog(exceptionPosition,e);
		}
		
		
	}
	

}
