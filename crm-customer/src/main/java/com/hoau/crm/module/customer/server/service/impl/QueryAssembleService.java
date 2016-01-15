package com.hoau.crm.module.customer.server.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.customer.api.server.IQueryAssembleService;
import com.hoau.crm.module.customer.api.shared.constants.Constant;
import com.hoau.crm.module.customer.api.shared.domain.ExcelReturn;
import com.hoau.crm.module.customer.api.shared.domain.ExportQuery;
import com.hoau.crm.module.customer.api.shared.domain.Field;
import com.hoau.crm.module.customer.api.shared.domain.NameValue;
import com.hoau.crm.module.customer.api.shared.domain.ParamBean;
import com.hoau.crm.module.customer.api.shared.domain.QuerySql;
import com.hoau.crm.module.customer.api.shared.domain.TableHead;
import com.hoau.crm.module.customer.api.shared.exception.BamSysException;
import com.hoau.crm.module.customer.api.shared.util.DateUtils;
import com.hoau.crm.module.customer.api.shared.util.ExportExcel;
import com.hoau.crm.module.customer.server.dao.QueryAssembleDaoMapper;
import com.hoau.hbdp.framework.server.context.UserContext;



/**
 * @description 
 * @version 1.0
 * @author zzx
 * @update 2012-10-9 上午10:18:10 
 */
@Service("queryAssembleService")
public class QueryAssembleService implements IQueryAssembleService {
    @Resource
    QueryAssembleDaoMapper queryAssembleDao;
    
    @Resource(name="readjdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
	 * 日志
	 */
	private static Logger LOG = LoggerFactory.getLogger(QueryAssembleService.class);
	
    /**
     * 查询sql总条数
     * */
    @Override
    public Long totalshowQuerySql(QuerySql queryAssemble) {
        Long countNum = queryAssembleDao.totalshowQuerySql(queryAssemble);
        return countNum;
    }

    /**
     * 查询个和解析下拉框中的sql 
     * */
    @Override
    public List<QuerySql> showQuerySql(QuerySql queryAssemble, int start, int limit)throws BamSysException {
        //json和实体类的转换
        ObjectMapper mapper = new ObjectMapper();
        StringWriter stringWriter = null;
        RowBounds rb = new RowBounds(start,limit);
        List<QuerySql> lists = queryAssembleDao.showQuerySql(queryAssemble,rb);                  
        List<QuerySql> resultlists = new ArrayList<QuerySql>();
        
        /**
         * 1）后去解析实体类，解析param参数，将param从json 转换成实体类集合
         * 2）循环获取实体集合中的每个实体类，取得sql属性
         * 3）根据 if (存在sql) 调用dao方法执行sql ,得到 NameValue(name,value) 的集合并保持到实体类map属性中
         * 4）从新将实体类集合转换成json字符串保存到原来的param属性中
         * 
         * 功能：参数中有下列框（1：枚举类数据，2：需要从数据库中查询获取的）
         *       将第2个类型数据转换成第1类数据类型给前台解析
         * */
        for(QuerySql eq:lists){
            List<ParamBean> pbs = null;
            //组合表显示的列名
            String [] head = eq.getTableHead().split(",");
            List<TableHead> myColumns = queryTableHeads(head);
            stringWriter =   new StringWriter();
            try {                  
               mapper.writeValue(stringWriter, myColumns);
           } catch (Exception e) {              
               e.printStackTrace();
               throw new BamSysException(""+Constant.BUSERVICE_EXCEPTION_UNJSON,"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",e); 
           }
            eq.setMyColumn(stringWriter.getBuffer().toString());
            stringWriter = null;
            
            if(null==eq.getParam() || "".equals(eq.getParam())){
                resultlists.add(eq);
                continue;
            }
            try {
                //解析json 成实体类集合
                pbs = mapper.readValue(eq.getParam(), new TypeReference<List<ParamBean>>(){});                      
            } catch (Exception e) {               
                e.printStackTrace();
                throw new BamSysException(""+Constant.BUSERVICE_EXCEPTION_UNJSON,"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",e); 
            }
            
            if(pbs!=null){
                for(int i=0;i<pbs.size();i++){
                     if(pbs.get(i).getSql()!=""&&pbs.get(i).getSql()!=null){
                        //如果需要执行sql获取数据，则调用到方法获取数据，清空sql
                         pbs.get(i).setMap(null);
                             try{
                                pbs.get(i).setMap(execSqlQuery(pbs.get(i).getSql()));
                             }catch(Exception e){
                                 e.printStackTrace();
                                 pbs.get(i).setMap(new ArrayList<NameValue>());
                             }
                         pbs.get(i).setSql(null);
                     }
                }
                    stringWriter =   new StringWriter();
                 try {                  
                    mapper.writeValue(stringWriter, pbs);
                } catch (Exception e) {              
                    e.printStackTrace();
                    throw new BamSysException(""+Constant.BUSERVICE_EXCEPTION_UNJSON,"com.hoau.crm.module.customer.server.service.impl.QueryAssembleService.implshowQuerySql",e); 
                }
                
                eq.setParam(stringWriter.getBuffer().toString()) ;  
                stringWriter = null;
            }
           
          
            resultlists.add(eq);
         }
                    
        return resultlists;
    }
    
    /**
     * @param sql
     * @return
     * 获取下拉框的值
     */
    public List<NameValue> execSqlQuery(String sql) {
        List<NameValue> list = new ArrayList<NameValue>();  
        
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        NameValue nv = null;
        while(rs.next()){
            nv = new NameValue(rs.getString(1),rs.getString(2));
            list.add(nv);
            nv = null;
        }
              
        return list;
    }
    
    
    /**
     * 检查查询数量
     * */
    @Override
    public long checkMaxNumber(String sql, String queryParam) {
        String countSql ="select count(0) from ("+sql+") ss"; 
        long number = (long) 0;   
        NamedParameterJdbcTemplate  newjdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String,String> pMap =  controlParam(queryParam);
        if(sql.indexOf("&create_user") > 0)
        	pMap.put("create_user", UserContext.getCurrentUser().getUserName());
        SqlRowSet  rs =newjdbcTemplate.queryForRowSet(countSql,pMap);
        if(rs.next()){
            number = rs.getLong(1);       
        }      
//        return number;
//        long  num = queryAssembleDao.checkMaxNumber(countSql,controlParam(queryParam));
        return number;
    }

    
    /**
     * 获取数据
     * */
    @Override
    public List<ExportQuery>  execSqlAll(String sql,String queryParam,int length,int start, int limit) {
            
        List<ExportQuery> exportQuerys = new ArrayList<ExportQuery>();
        exportQuerys = execSqlAll(sql,controlParam(queryParam),length,true,start,limit);
                          
       return exportQuerys;
    }
    
	/**
	 * @param sql
	 * @param queryParam
	 * @param length
	 * @param start
	 * @param limit
	 * @return
	 * 获取导出数据数据的查询结果
	 */
	public List<ExportQuery> execSqlAll(String sql,
			Map<String, String> queryParam, int length, boolean isMaxCheck,int start, int limit) {
		//查询自定义sql
		String newSql = "select wg.* from ( " + sql + ") wg LIMIT " + start
				+ "," + limit;
		if(sql.indexOf("&create_user") > 0)
			queryParam.put("create_user", UserContext.getCurrentUser().getUserName());
		List<ExportQuery> list = new ArrayList<ExportQuery>();
		NamedParameterJdbcTemplate newjdbcTemplate = new NamedParameterJdbcTemplate(
				jdbcTemplate);
		LOG.info("select sql: " + newSql + "");
		SqlRowSet rs = newjdbcTemplate.queryForRowSet(newSql, queryParam);
		LOG.info("queryParam : " + queryParam + "");
		if (null != rs) {
			while (rs.next()) {
				/* if(isMaxCheck && ++number>Constant.QUERY_MAX_NUM){
                	break;
            	}*/
				ExportQuery eq = new ExportQuery();
				for (int i = 1; i <= length; i++) {
					eq.inValue(rs.getString(i), i);
				}
				list.add(eq);
			}
		}
		return list;
	}


    /**
     * 组装表头
     * */
    public List<TableHead> queryTableHeads(String [] head){
        List<TableHead> tableHeads =  new ArrayList<TableHead>();
   
        TableHead tab = new TableHead();   //设置序号行
        tab.setXtype("rownumberer");
        tab.setWidth(60);
        tab.setHeader("序号");
        tableHeads.add(tab);
        int index = 0;
        for(String name:head){              //组合表的所有列
            tab = new TableHead(name,index);
            tableHeads.add(tab);
            ++index;
        }
        return tableHeads;
    }

    /**
     * 组装列和值得映射关系
     * */
	public List<Field> queryField(int length){
	    List<Field> fields = new ArrayList<Field>();
	    Field fd = null;
	    for(int i = 0;i<length;i++){
	        fd = new Field("str"+i);
	        fields.add(fd);
	        fd = null;
	    }
	    return fields;
	}
	
    /**
     * 导出Excel
     * */
	@Override
	public ExcelReturn toExcelPort(String sql, String queryParam, int length, String[] head,int startNumber,int limitNumber) {
        //返回参数
        ExcelReturn excelReturn=new ExcelReturn();
        //创建Excel工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
                
         //导出Excel的文件名
         String fileName=DateUtils.getNowDateStrNoSep();
        
         try {
             excelReturn.setFileName(fileName);
         } catch (UnsupportedEncodingException e) {         
             e.printStackTrace();
//             try{
//                 log.saveExceptionLog("com.deppon.pda.module.bamCode.server.service.impl.QueryAssembleService.toExcelPort", e);
//             }catch(Exception ef){
//                 ef.printStackTrace();
//             }
         }
         
         //创建Excel 实体类 计划 获取数据
         ExportExcel<ExportQuery> ex = new ExportExcel<ExportQuery>();        
    
       //获取要导出的数据
         List<ExportQuery> exportQuerys =null;
         while(startNumber<=limitNumber){
             exportQuerys = new ArrayList<ExportQuery>();
             exportQuerys = execSqlAll(sql,controlParam(queryParam),length,false,startNumber,Constant.MAX_NUM);          
             //将数据写入Excel 返回文件流     
             workbook=exportExcel(workbook,(startNumber+1)+"-"+(startNumber+Constant.MAX_NUM),head, exportQuerys);   
             startNumber += Constant.MAX_NUM;
             exportQuerys = null;
         }
         
         excelReturn.setInputStream( ex.inputToClient(workbook));
         return excelReturn;
	}
   
    /**
     * 创建Excel
     * */
    public  HSSFWorkbook exportExcel(HSSFWorkbook workbook ,String title, String[] headers,
            List<ExportQuery> dataset) {

        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
    // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        int index = 0;
        for(ExportQuery eq:dataset){
            index++;
            row = sheet.createRow(index);
            for(int i = 0;i<headers.length;i++){
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(eq.outValue(i+1));
            }
            
        }       
        return workbook;

    }
	
	
    /**
     * 解析动态传入后台的参数 转化为Map集合
     * */
   public  Map<String,String> controlParam(String param){
       Map<String,String> map = new HashMap<String,String>();
       if(null == param || "".equals(param) ){
           return null;
       }
       String [] paramCount = param.split(",");
       for(String s:paramCount){
           map.put(s.substring(0,s.indexOf(":")),s.substring(s.indexOf(":")+1,s.length()));
       }      
       return map;
   }	
	
 
   /**
    * 级联下了框解析
    * */
    @Override
    public List<NameValue> queryCombo(String queryParam, String sql) {
        List<NameValue> list = new ArrayList<NameValue>();     
        list = queryCombo(controlParam(queryParam),sql);
        return list;
    }
    
    /**
     * @param controlParam
     * @param sql
     * @return
     * 级联下拉框获取值
     */
    public List<NameValue> queryCombo(Map<String, String> controlParam, String sql) {
        List<NameValue> list = new ArrayList<NameValue>();
        NamedParameterJdbcTemplate  newjdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        SqlRowSet  rs =newjdbcTemplate.queryForRowSet(sql,controlParam);
        NameValue nv = null;
        while(rs.next()){
            nv = new NameValue(rs.getString(1),rs.getString(2));
            list.add(nv);
            nv = null;
        }       
        return list;
    }

    public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
        QueryAssembleService qs = new QueryAssembleService();
       String sql =  "";
       System.out.println(qs.controlParam(sql));
          
     }
    
    
    public File getPath(String webContextPath){
        String path = webContextPath+"/data/";
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        return file;
    }

	@Override
	public QuerySql querySqlById(String id) {
		return queryAssembleDao.querySqlById(id);
	}

}
