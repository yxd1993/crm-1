
/**   
 * @title ILog.java
 * @package com.deppon.ump.module.platform.server.util
 * @description 
 * @author cbb   
 * @update 2012-10-12 上午11:48:29
 * @version V1.0   
 */
 
package com.hoau.crm.module.customer.api.shared.util;

import java.util.List;

import com.hoau.crm.module.customer.api.shared.exception.BamSysException;


/**
 * @description 
 * @version 1.0
 * @author wwn
 * @update 2013-06-11 上午11:59:20 
 */
public interface ILog {
	public void saveExceptionLog(String exceptionPosition,Exception e);

	void saveExceptionLogList(String exceptionPosition,List<BamSysException> es);
}
