package com.hoau.crm.module.job.server.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.util.EnvUtil;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.config.server.util.LogUtil;
import com.hoau.crm.module.job.server.dao.DcDataEtlMapper;
import com.hoau.crm.module.job.server.service.IDcDataEtlService;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * DC数据初始化SERVICE
 * 
 * @author: 何斌
 * @create: 2015年7月28日 上午10:54:31
 */

@org.springframework.stereotype.Service
public class DcDataEtlServiceImpl implements IDcDataEtlService{

	@Resource
	private DcDataEtlMapper dataEtlMapper;
	
	
	@Override
	@Transactional
	public void executeEtlFile() {
		//查询上一次抽取结束时间
		String endTime = dataEtlMapper.queryEndTime();
		//将上次结束时间推前60分钟
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			endTime = sdf.format(new Date(sdf.parse(endTime).getTime()-(60*60*1000)).getTime());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		//执行装换
		//文件路径
		String filePath = "";
		try {
			filePath = AttachmentRootPath.getAttachRootPath()
					+ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("kettle.dir")
					+"dc.ktr";
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//抽取参数定义
		String[] params = new String[]{endTime};
		Map<String,String> map = new HashMap<String,String>();
		try {
 			this.runTransfer(params,filePath);
			//转换成功,更新结束时间
			dataEtlMapper.updateEndTime();
			map.put("isSuccess", BseConstants.YES);
			//更新转换状态
			this.saveLog(map);
		} catch (Exception e) {
			map.put("isSuccess",BseConstants.NO);
			map.put("descr", LogUtil.logFormat(e));
			//更新转换状态
			this.saveLog(map);
		}
	}
	
	/**
	 * 保存状态信息
	 * @param map
	 */
	@Override
	@Transactional
	public void saveLog(Map<String,String> map){
		dataEtlMapper.updateStatus(map);
	}
	
	/**
	 * 更新开始时间
	 */
	@Override
	@Transactional
	public void updateBeginTime(){
		dataEtlMapper.updateBeginTime();
	}

	/**
	 * kettle 转换
	 * 
	 * @param params
	 * @param ktrPath
	 */
	private void runTransfer(String[] params, String ktrPath) throws Exception{
		Trans trans = null;
		// 初始化
		// 转换元对象
		KettleEnvironment.init();// 初始化
		EnvUtil.environmentInit();
		TransMeta transMeta = new TransMeta(ktrPath);
		// 转换
		trans = new Trans(transMeta);
		// 执行转换
		trans.execute(params);
		// 等待转换执行结束
		trans.waitUntilFinished();
		// 抛出异常
		if (trans.getErrors() > 0) {
			throw new Exception(
					"There are errors during transformation exception!(传输过程中发生异常)");
		}
	}
	
}
