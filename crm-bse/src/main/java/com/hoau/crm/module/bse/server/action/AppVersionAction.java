package com.hoau.crm.module.bse.server.action;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.appcore.api.shared.domain.AppVersionEntity;
import com.hoau.crm.module.bse.api.server.service.IAppVersionService;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @author：潘强
 * @create：2015年7月30日
 * @description：App版本信息action
 */
@Controller
@Scope("prototype")
public class AppVersionAction extends AbstractAction {

	
	private static final long serialVersionUID = 7093285224584790370L;
	
	/**
	 * 接收从数据库中查询到的App版本信息
	 */
	private List<AppVersionEntity> appVersionList;
	
	/**
	 * 接收从前台传送过来的查询条件
	 */
	private AppVersionEntity appVersion;
	
	/**
	 * 上传文件
	 */
	private File file;
	
	/**
	 * 上传真实文件名
	 */
	private String fileFileName;
	
	/**
	 * App版本信息ID集合
	 */
	private List<String> ids;
	/**
	 * 要设置为当前版本的App版本号
	 */
	private int versionCode;
	@Resource
	private IAppVersionService appVersionService;
	
	public String queryAppVersionAction()
	{
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			appVersionList = appVersionService.getAPPVersion(appVersion, rb);
			this.setTotalCount(appVersionService.countAppVersion(appVersion, rb));
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * @author：潘强
	 * @create：2015年7月31日
	 * @description：删除App版本信息
	 */
	public String deleteAppVersion()
	{
		try {
			appVersionService.deleteAppVersion(ids);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * @author：潘强
	 * @create：2015年8月3日
	 * @description：设置App当前版本信息
	 */
	public String updateVersionCode(){
		try {
			appVersionService.updateVersionCode(versionCode);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
		
	}
	/**
	 * @author：潘强
	 * @create：2015年8月3日
	 * @description：新增App版本信息
	 */
	public String addAppVersion()
	{
		try {
			appVersionService.addAppVersion(appVersion,file,fileFileName);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	} 
	
	/**
	 * @author：潘强
	 * @create：2015年8月5日
	 * @description：下载当前App版本信息
	 */
	public String exportAppVersion(){
		try {
			appVersionService.exportAppVersion();
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<AppVersionEntity> getAppVersionList() {
		return appVersionList;
	}
	public void setAppVersionList(List<AppVersionEntity> appVersionList) {
		this.appVersionList = appVersionList;
	}
	public AppVersionEntity getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(AppVersionEntity appVersion) {
		this.appVersion = appVersion;
	}
	public int getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	
	
}
