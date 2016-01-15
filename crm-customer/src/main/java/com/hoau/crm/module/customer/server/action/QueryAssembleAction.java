package com.hoau.crm.module.customer.server.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.server.IQueryAssembleService;
import com.hoau.crm.module.customer.api.shared.constants.Constant;
import com.hoau.crm.module.customer.api.shared.domain.ExcelReturn;
import com.hoau.crm.module.customer.api.shared.domain.ExportQuery;
import com.hoau.crm.module.customer.api.shared.domain.Field;
import com.hoau.crm.module.customer.api.shared.domain.NameValue;
import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.ResultMessage;
import com.hoau.crm.module.customer.api.shared.domain.TableHead;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;

/**
 * @description
 * @version 1.0
 * @author zzx
 * @update 2012-10-9 上午9:51:00
 */

@Controller
@Scope("prototype")
public class QueryAssembleAction extends AbstractAction {

    
    private static final long serialVersionUID = 1L;
    /**
	 * @fields serialVersionUID
	 */
	@Resource
	IQueryAssembleService queryAssembleService;
//	@Resource
//	ILog log;
	
	private List<QuerySql> sqlLists;
    private QuerySql queryAssemble;
    private ResultMessage resultMessage;
    
    private String sql;
    private String tableHead;
    private String queryParam;
   
    // 导出Excel报表
    private String fileName;
    private InputStream excelStream;
    private int exportNumber;
    private int startNumber;
    private int limitNumber;
    
    private String id;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private List<TableHead> tableHeads;
    private List<Field> fields;
    private List<ExportQuery> exportQuerys;
    
     //下拉框数据
    private List<NameValue> comboList;    
    
  
    public String showQueryPage(){
        return this.returnSuccess();
    }
	
    /**
     *查询SQL 
     * */
	public String  showQuerySql(){
//        ActionContext context = ActionContext.getContext();
//        Map<String, Object> sessions = context.getSession();
//        UserEntity user = (UserEntity) sessions.get("user");
//        queryAssemble.setCreateUser(user.getEmpCode());
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		if(null == queryAssemble)
			queryAssemble = new QuerySql();
		queryAssemble.setCreateUser(currUser.getUserName());
	    
	    //模糊查询相关功能  自动忽略日期条件
	      if(queryAssemble!=null
	              && queryAssemble.getRemark()!=null
	              && !"".equals(queryAssemble.getRemark())){
	          queryAssemble.setStartTime(null);
	          queryAssemble.setEndTime(null);
	      }
	   	   try{ 
        	    sqlLists = queryAssembleService.showQuerySql(queryAssemble,
                        this.getStart(), this.getLimit());
        	    this.setTotalCount(queryAssembleService
                        .totalshowQuerySql(queryAssemble));
                resultMessage = new ResultMessage(Constant.RETURN_SUCCESS,
                        "查询成功");
        	}catch(BamSysException e){
//                try{
//                    log.saveExceptionLog(e.getErrPath(), e);
//                }catch(Exception ef){
//                    ef.printStackTrace();
//                }
              resultMessage=new ResultMessage(Constant.RETURN_FAIL,"JSON转换失败！");
                
            }    	    
       
        return this.returnSuccess();
        
	}
	
	 /**
	  *根据解析的sql 和台传来的参数查数据 
	  * */
	public String execSqlAll(){    
	    
	    String [] head = tableHead.split(",");
	    try {
			QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
            exportQuerys = queryAssembleService.execSqlAll(querysqlInfo.getSql(),queryParam,head.length,
                    this.getStart(), this.getLimit());
            this.setTotalCount(queryAssembleService.checkMaxNumber(querysqlInfo.getSql(), queryParam));
            
        } catch (Exception e) {        
//            try{
//                log.saveExceptionLog("com.hoau.crm.module.customer.server.action.QueryAssembleAction.showQuerySql", e);
//            }catch(Exception ef){
//                ef.printStackTrace();
//            }

            exportQuerys = new ArrayList<ExportQuery>();
            ExportQuery eq = new ExportQuery();
            eq.setStr0("执行sql错误");
            exportQuerys.add(eq);
            return this.returnSuccess();
                   
        }
	    tableHeads = queryAssembleService.queryTableHeads(head);	 
	    fields = queryAssembleService.queryField(head.length);
	    
	    resultMessage = new ResultMessage(Constant.RETURN_SUCCESS,
	                "查询成功");
	    return this.returnSuccess();
	}
	
	 /**
     * 导出Excel 
     * */  
	public  String exportExcel(){
	   	 
	    String [] head = tableHead.split(",");
	    ExcelReturn excelReturn = new ExcelReturn();
	    try{
	    	QuerySql querysqlInfo = queryAssembleService.querySqlById(id);
          excelReturn = queryAssembleService.toExcelPort(querysqlInfo.getSql(),queryParam,head.length,head, startNumber,limitNumber);
	    }catch(Exception e){
//	        try{
//                log.saveExceptionLog("com.hoau.crm.module.customer.server.action.QueryAssembleAction.exportExcel", e);
//            }catch(Exception ef){
//                ef.printStackTrace();
//            }  
	    }
        this.fileName = excelReturn.getFileName();
	     excelStream = excelReturn.getInputStream();
	    return this.returnSuccess();
	    
	}
		
	
	/**
	 * 执行下拉框查询
	 * */
	public String queryCombo(){
	    try{
	      comboList = queryAssembleService.queryCombo(queryParam,sql);	
	    }catch(Exception e){
	      resultMessage = new ResultMessage(Constant.RETURN_FAIL,
                    "执行级联查询sql有误"); 
	      return this.returnSuccess();
	    }
	    resultMessage = new ResultMessage(Constant.RETURN_SUCCESS,
                "下拉框验证通过");  
	    if(null==comboList){
	        comboList =  new ArrayList<NameValue>();
	        resultMessage = new ResultMessage(Constant.RETURN_FAIL,
	                "级联未获取到数据，将无法查询"); 
	        
	    }
	   
	    
	    return this.returnSuccess();
	            
	}
	
	
	
    public void setQueryAssemble(QuerySql queryAssemble) {
        this.queryAssemble = queryAssemble;
    }
    public List<QuerySql> getSqlLists() {
        return sqlLists;
    }
 
    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setTableHead(String tableHead) {
        this.tableHead = tableHead;
    }


    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }



    public ResultMessage getResultMessage() {
        return resultMessage;
    }


    public List<TableHead> getTableHeads() {
        return tableHeads;
    }


    public List<Field> getFields() {
        return fields;
    }


    public List<ExportQuery> getExportQuerys() {
        return exportQuerys;
    }


    public String getFileName() {
        return fileName;
    }


    public InputStream getExcelStream() {
        return excelStream;
    }
    
    public QuerySql getQueryAssemble() {
        return queryAssemble;
    }


    public List<NameValue> getComboList() {
        return comboList;
    }

    public int getExportNumber() {
        return exportNumber;
    }

    public void setExportNumber(int exportNumber) {
        this.exportNumber = exportNumber;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(int limitNumber) {
        this.limitNumber = limitNumber;
    }
	
	
    

}
