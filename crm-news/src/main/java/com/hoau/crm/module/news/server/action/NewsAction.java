/**
 * 
 */
package com.hoau.crm.module.news.server.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.hoau.crm.module.news.server.service.INewsArticleClassService;
import com.hoau.crm.module.news.server.service.INewsArticleService;
import com.hoau.crm.module.news.shared.domain.DataTableObject;
import com.hoau.crm.module.news.shared.domain.NewsArticleClassEntity;
import com.hoau.crm.module.news.shared.domain.NewsArticleEntity;
import com.hoau.crm.module.news.shared.vo.NewsArticleVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.components.security.SecurityNonCheckRequired;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 *
 * @author 丁勇
 * @date 2015年9月23日
 */
@Controller
@Scope("prototype")
public class NewsAction extends AbstractAction {
	
	private static final long serialVersionUID = -1971264622328738585L;
	
	@Resource
	INewsArticleService iNewsArticleService;
	
	@Resource
	INewsArticleClassService iNewsArticleClassService;
	
	/**
	 *接收表格前台数据
	 */
	private String jsonmaps;
	/**
	 *内容
	 */
	private String body;
	/**
	 *表格实体
	 */
	private DataTableObject datatableobject;
	
	/**
	 *文章实体
	 */
	private NewsArticleEntity newsArticleEntity;
	
	/**
	 *文章类型实体
	 */
	private NewsArticleClassEntity newsArticleClassEntity;
	/**
	 * 文章类型列表
	 */
	private List<NewsArticleClassEntity> newsArticleClassList;
	
	/**
	 *常见问题文章列表
	 */
	private List<NewsArticleEntity> newsArticleList;
	
	/**
	 *文章vo
	 */
	private NewsArticleVo newsArticleVo;
	
	/**
	 * 公共方法
	 * @return
	 * @author 丁勇
	 * @date 2015年11月16日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String commonMethod(){
		return returnSuccess();
	}
	/**
	 * 查看文章列表
	 * @return
	 * @author 丁勇
	 * @date 2015年9月21日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public void queryNews(){
		//获取ajax的请求参数
		Map<String,Object> map = JSONObject.parseObject(jsonmaps);
		datatableobject = iNewsArticleService.queryNews(map);
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置响应编码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.println(JSONObject.toJSON(iNewsArticleService.queryNews(map)));
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			out.flush();  
			out.close();
		}
	}
	/**
	 * 查看文章内容
	 * @return
	 * @author 丁勇
	 * @date 2015年9月30日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String queryNewsContent (){
		try{
			newsArticleEntity = iNewsArticleService.queryNewsContent(newsArticleEntity.getId());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 查询文章类型列表
	 * @return
	 * @author 丁勇
	 * @date 2015年9月25日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String queryArticeleClass(){
		try{
			newsArticleClassList =  iNewsArticleClassService.queryArticleClass(newsArticleClassEntity.getId());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 保存文章
	 * @return
	 * @author 丁勇
	 * @date 2015年9月28日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String addArticle() {
		NewsArticleEntity articleEntity  = newsArticleEntity;
		try{
			articleEntity.setArticleContent(body);
			iNewsArticleService.addArticle(articleEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 常见问题列表及搜索
	 * @return
	 * @author 丁勇
	 * @date 2015年11月11日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String queryFaqsArticle(){
		try{
			newsArticleList = (List<NewsArticleEntity>) iNewsArticleService.queryFaqsArticleClass(newsArticleVo);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 常见问题内容详情
	 * @return
	 * @author 丁勇
	 * @date 2015年11月13日
	 * @update 
	 */
	@SecurityNonCheckRequired
	public String lookFaqsContent(){
		ServletActionContext.getRequest().setAttribute("newsArticleEntity", iNewsArticleService.queryNewsContent(newsArticleEntity.getId()));
		return returnSuccess();
	}

	public String getJsonmaps() {
		return jsonmaps;
	}

	public void setJsonmaps(String jsonmaps) {
		this.jsonmaps = jsonmaps;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public DataTableObject getDatatableobject() {
		return datatableobject;
	}

	public void setDatatableobject(DataTableObject datatableobject) {
		this.datatableobject = datatableobject;
	}

	public NewsArticleEntity getNewsArticleEntity() {
		return newsArticleEntity;
	}

	public void setNewsArticleEntity(NewsArticleEntity newsArticleEntity) {
		this.newsArticleEntity = newsArticleEntity;
	}

	public NewsArticleClassEntity getNewsArticleClassEntity() {
		return newsArticleClassEntity;
	}

	public void setNewsArticleClassEntity(
			NewsArticleClassEntity newsArticleClassEntity) {
		this.newsArticleClassEntity = newsArticleClassEntity;
	}

	public List<NewsArticleClassEntity> getNewsArticleClassList() {
		return newsArticleClassList;
	}

	public void setNewsArticleClassList(
			List<NewsArticleClassEntity> newsArticleClassList) {
		this.newsArticleClassList = newsArticleClassList;
	}

	public List<NewsArticleEntity> getNewsArticleList() {
		return newsArticleList;
	}

	public void setNewsArticleList(List<NewsArticleEntity> newsArticleList) {
		this.newsArticleList = newsArticleList;
	}

	public NewsArticleVo getNewsArticleVo() {
		return newsArticleVo;
	}

	public void setNewsArticleVo(NewsArticleVo newsArticleVo) {
		this.newsArticleVo = newsArticleVo;
	}	
	
	
}
