package com.hoau.crm.module.bse.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;
import com.hoau.crm.module.bse.api.server.service.IAppVersionService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.server.dao.AppVersionMapper;
import com.hoau.crm.module.bse.shared.exception.AppVersionException;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * App版本信息SERVICE
 * 
 * @author 潘强
 * @date 2015-7-30
 */
@Service
public class AppVersionService implements IAppVersionService {

	@Resource
	private AppVersionMapper appVersionMapper;
	
	@Override
	public List<AppVersionEntity> getAPPVersion(AppVersionEntity appVersion,
			RowBounds rb) {
		
		if (rb == null) {
			throw new AppVersionException(AppVersionException.QUERY_APPVERSION_RB_NULL);
		}
		if (appVersion == null) {
			throw new AppVersionException(AppVersionException.QUERY_APPVERSION_PARAM_NULL);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		//if (!StringUtil.isEmpty(appVersion.getVersionCode())) {
			params.put("versionCode",appVersion.getVersionCode());
		//}
		if (!StringUtil.isEmpty(appVersion.getApkName())) {
			params.put("apkName", "%" + appVersion.getApkName() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (appVersion.getCreateDate() != null) {
			params.put("createDate", sdf.format(appVersion.getCreateDate()));
		}
		if (appVersion.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(appVersion.getCreateEndDate())));
		}
	
		return appVersionMapper.getAPPVersion(params, rb);
	}
	
	@Override
	public Long countAppVersion(AppVersionEntity appVersion, RowBounds rb) {
		if (rb == null) {
			throw new AppVersionException(AppVersionException.QUERY_APPVERSION_RB_NULL);
		}
		if (appVersion == null) {
			throw new AppVersionException(AppVersionException.QUERY_APPVERSION_PARAM_NULL);
		}
		
		Map<String, Object> params = new HashMap<String, Object>();
		//if (!StringUtil.isEmpty(appVersion.getVersionCode())) {
			params.put("versionCode",appVersion.getVersionCode());
		//}
		if (!StringUtil.isEmpty(appVersion.getApkName())) {
			params.put("apkName", "%" + appVersion.getApkName() + "%");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		if (appVersion.getCreateDate() != null) {
			params.put("createDate", sdf.format(appVersion.getCreateDate()));
		}
		if (appVersion.getCreateEndDate() != null) {
			params.put("createEndDate", sdf.format(BseConstants.getEndDate(appVersion.getCreateEndDate())));
		  //params.put("sweepEndTime", sdf.format(appVersion.getCreateEndDate()));
		}
		return appVersionMapper.countAppVersion(params, rb);
	}

	@Override
	@Transactional
	public void deleteAppVersion(List<String> ids) {
		if(ids==null || ids.size()==0){
			throw new AppVersionException(AppVersionException.APPVERSIONIDS_NULL);
		}
		for (String id : ids) {
			try {
				String url=appVersionMapper.getUrlFromId(id);
				url=AttachmentRootPath.getAttachRootPath()+url;
				System.out.println(url);
				File file=new File(url);
				if(file.exists()){
					file.delete();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		appVersionMapper.deleteAppVersion(map);
		
	}

	@Override
	@Transactional
	public void addAppVersion(AppVersionEntity appVersion,File file,String fileName) {
		if(appVersion == null){
			throw new AppVersionException(AppVersionException.APPVERSION_NULL);
		}
		if(appVersion.getVersionCode()<=appVersionMapper.getMaxAppVersionCode()){
			throw new AppVersionException(AppVersionException.APPVERSIONCODE);
		}
		//将App版本号加入到appName中
		int n=fileName.lastIndexOf(".");
		fileName=fileName.substring(0, n)+appVersion.getVersionCode()+fileName.substring(n);
		
		String url=write(file,fileName);
		appVersion.setUrl(url);
		appVersion.setApkName(fileName);
		if(appVersion.getIsNow().equals("1")){
				appVersionMapper.updateAppVersionIsNow();
				appVersionMapper.addAppVersion(appVersion);
		}else{
			appVersionMapper.addAppVersion(appVersion);
	         }
	}


	@Override
	public void updateVersionCode(int versionCode) {
		appVersionMapper.updateAppVersionIsNow();
		appVersionMapper.setIsNowFromMaxCode(versionCode);
	}
	
	@Override
	public void exportAppVersion() {
		//获取当前版本的App的url
		String url=appVersionMapper.getUrlFromIsNow();
		String apkName=appVersionMapper.getApkNameFromIsNow();
		InputStream is = null;
		OutputStream os = null;
		try {
			//输入
			url=AttachmentRootPath.getAttachRootPath()+url;
			File file=new File(url);
			is = new FileInputStream(file);
			//输出
			try {
/*				url=ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.version.dir")+fileName;
				attachmentUrl = AttachmentRootPath.getAttachRootPath()
						+ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.version.dir")
						+fileName;*/
				//File file1=new File("/D:/Users/"+apkName);
				File file1=new File("/C:/Users/Administrator/Desktop/"+apkName);
				os = new FileOutputStream(file1);
				byte[] buffer = new byte[1024];
				int len = 0 ;
				while((len =is.read(buffer))!= -1){
					os.write(buffer,0,len);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new AppVersionException(AppVersionException.APPVERSIONFILE);
		} finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//将App版本写入到对应地址
	private String write(File file, String fileName){
		InputStream is = null;
		OutputStream os = null;
		String url="";
		String attachmentUrl = "";
		try {
			//输入
			is = new FileInputStream(file);
			//输出
			try {
				url=ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.version.dir");
				attachmentUrl = AttachmentRootPath.getAttachRootPath()
						+ConfigFileLoadUtil.getPropertiesForClasspath("config.properties").getProperty("app.version.dir")
						+fileName;
				os = new FileOutputStream(attachmentUrl);
				byte[] buffer = new byte[1024];
				int len = 0 ;
				while((len =is.read(buffer))!= -1){
					os.write(buffer,0,len);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(os != null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return url;
	}

	
	
}
