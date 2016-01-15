package com.hoau.crm.module.customer.server.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.customer.api.server.ITableUploadService;
import com.hoau.crm.module.customer.api.shared.domain.DistrictEntity;
import com.hoau.crm.module.customer.api.shared.domain.TableUploadEntity;
import com.hoau.crm.module.customer.api.shared.exception.TableUploadException;
import com.hoau.crm.module.customer.server.dao.CustomerInfoPoolMapper;
import com.hoau.crm.module.customer.server.dao.CustomerLatlngMapper;
import com.hoau.crm.module.customer.server.dao.TableUploadMapper;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;


/**
 * 上传SERVICE
 * @author: 何斌
 * @create: 2015年5月25日 下午3:09:01
 */

@Service
public class TableUploadService implements ITableUploadService {
	
	private static Logger LOG = LoggerFactory.getLogger(TableUploadService.class);
	
	/**
	 * 批量导入大小
	 */
	private final static int BATCH_SIZE = 100;
	
	private final static String PROVINCE = "PROVINCE";
	private final static String CITY = "CITY";
	private final static String AREA = "AREA";
	
	@Resource
	private CustomerInfoPoolMapper customerInfoPoolMapper;
	
	@Resource
	private TableUploadMapper tableUploadMapper;
	
	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;
	
	@Resource
	private CustomerLatlngMapper customerLatlngMapper;
	
	@Override
	@Transactional
	public void tableUpload(InputStream in) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//创建工作簿
			Workbook wb = WorkbookFactory.create(in);
			//获取sheet
			Sheet sheet = wb.getSheetAt(0);
			//先获取上传excel对应的表名
			String tableName = sheet.getSheetName().trim();
			//根据表名查找该表相关字段信息
			TableUploadEntity tableUploadEntity =
					tableUploadMapper.queryTableUploadInfoByTableName(tableName);
			if(tableUploadEntity == null ){
				throw new TableUploadException(TableUploadException.TABLE_SHEET_ERROR);
			}
			
			//绑定参数到map中
			map.put("tableName", tableName);
			map.put("columns", tableUploadEntity.getColumns());
			//字段的值集合
			List<List<Object>> columnValues =  new ArrayList<List<Object>>();
			//获取第一行列数
			int firstRowCellNum = sheet.getRow(0).getLastCellNum();
			if(tableUploadEntity.getColumns().size() != firstRowCellNum+4){
				throw new TableUploadException(TableUploadException.TABLE_COLUMN_ERROR);
			}
			//循环读取数据
			for(int i=1;i<=sheet.getLastRowNum();i++){
				//获得一行数据
				Row row = sheet.getRow(i);
				//没有数据返回
				if(row == null){
					throw new TableUploadException(TableUploadException.TABLE_RECORD_NULL);
				}
				List<Object> values = new ArrayList<Object>();
				//循环获得该列每个单元格的值
				for(int j=0;j < firstRowCellNum;j++){
					//获取单元格
					Cell cell = row.getCell(j);
					if(cell != null){
						switch (j) {
						case 4:	 cell.setCellType(Cell.CELL_TYPE_STRING);
						 		values.add(this.getDistrictCode(cell.getStringCellValue().trim(),null,PROVINCE ));
						 		break;
						case 5:  cell.setCellType(Cell.CELL_TYPE_STRING);
						 		 values.add(this.getDistrictCode(cell.getStringCellValue().trim(),null,CITY ));
						 		 break;
						case 6:  cell.setCellType(Cell.CELL_TYPE_STRING);
						 		 values.add(this.getDistrictCode(cell.getStringCellValue().trim(),this.getDistrictCode(row.getCell(5).getStringCellValue().trim(),null,CITY ),AREA ));
						 		 break;
						case 8:  cell.setCellType(Cell.CELL_TYPE_STRING);
								 values.add(this.getBusinessCode(cell.getStringCellValue().trim()));
								 break;
						case 9:  cell.setCellType(Cell.CELL_TYPE_STRING);
								 values.add(this.getRegionsCode(cell.getStringCellValue().trim()));
								 break;
						default:cell.setCellType(Cell.CELL_TYPE_STRING);
								values.add(cell.getStringCellValue().trim());
								break;
						}
					}else{
						throw new TableUploadException(TableUploadException.TABLE_COLUMN_RECORD_NULL);
					}
				}
				//创建人
				values.add(UserContext.getCurrentUser().getUserName());
				//创建时间
				values.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				//是否有效  Y 有效
				values.add(BseConstants.ACTIVE);
				
				//id
				values.add(UUIDUtil.getUUID());
				
				//将一行的数据放入值集合
				columnValues.add(values);
			}
			if(columnValues.size() > 0){
				//分批插入,每100条提交一次
				//计算插入需要的次数
				int batch = columnValues.size()%BATCH_SIZE==0?columnValues.size()/BATCH_SIZE:columnValues.size()/BATCH_SIZE+1;
				for(int i = 0 ; i < batch ; i++){
					List<List<Object>> batchValues = new ArrayList<List<Object>>();
					
//					List<CustomerLatlngEntity> latlngEntities = new ArrayList<CustomerLatlngEntity>();
					for(int j = BATCH_SIZE*i ; j < BATCH_SIZE*(i+1) && j < columnValues.size();j++){
						batchValues.add(columnValues.get(j));
//						Map map2 = LatitudeUtils.getLatitude(columnValues.get(j).get(9).toString(), columnValues.get(j).get(7).toString());
//						if(map2 != null){
//							//新增完成之后给客户添加经纬度
//							CustomerLatlngEntity customerLatlngEntity = new CustomerLatlngEntity();
//							customerLatlngEntity.setId(UUIDUtil.getUUID());
//							//代表客户共享里面添加
//							customerLatlngEntity.setType("0");
//							//设置客户ID
//							customerLatlngEntity.setCustomerId(columnValues.get(j).get(13).toString());
//							//维度
//							customerLatlngEntity.setLat(Double.parseDouble(map2.get("lat").toString()));
//							//经度
//							customerLatlngEntity.setLng(Double.parseDouble(map2.get("lng").toString()));
//							latlngEntities.add(customerLatlngEntity);
//						}
						
					}
					map.put("columnValues", batchValues);
					tableUploadMapper.tableImport(map);
//					customerLatlngMapper.addBatchCustomerLatlng(latlngEntities);
				}
			}
			//重复数据清理
			tableUploadMapper.clearRepeatData();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					LOG.info("没有数据流");
				}
			}
		}
	}
	
	/**
	 * 根据行政区域名称获得编码
	 * 
	 * @param districtName
	 * @param districtType
	 * @param area2 城市代码，可能存在区县重复的名称
	 * @return
	 * @author: 何斌
	 * @param area2 
	 * @date: 2015年6月24日
	 * @update 
	 */
	private String getDistrictCode(String districtName,String area2,String districtType){
		DistrictEntity districtEntity = new DistrictEntity();
		districtEntity.setDistrictName(districtName);
		districtEntity.setDistrictType(districtType);
		if(area2 != null)
			districtEntity.setParentDistrictCode(area2);
		DistrictEntity dEntity = customerInfoPoolMapper.getDistrictByNameOrCode(districtEntity);
		if(dEntity == null){
			return "";
		}
		return dEntity.getDistrictCode();
	}
	
	
	/**
	 * 事业部编号
	 * 
	 * @param businessName
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update
	 */
	private String getBusinessCode(String businessName) {
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMERINFOPOOL_BUSINESS");
		entity.setActive(BseConstants.ACTIVE);
		entity.setValueName(businessName);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if (lists.size() > 0) {
			value = lists.get(0).getValueCode();
		}
		return value;
	};

	/**
	 * 大区编号
	 * @param regionsName
	 * @return
	 * @author: 何斌
	 * @date: 2015年11月19日
	 * @update 
	 */
	private String getRegionsCode(String regionsName){
		return customerInfoPoolMapper.getRegionsDeptCode(regionsName);
	}
}