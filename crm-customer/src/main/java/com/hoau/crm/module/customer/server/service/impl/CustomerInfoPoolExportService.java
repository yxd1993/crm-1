package com.hoau.crm.module.customer.server.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.hoau.crm.module.bse.api.server.service.IOperationLogService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.server.util.GetHostAddressUtil;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.customer.api.server.ICustomerInfoPoolService;
import com.hoau.crm.module.customer.api.server.IExcelExportService;
import com.hoau.crm.module.customer.api.shared.domain.CustomerInfoPoolEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerInfoPoolException;
import com.hoau.crm.module.customer.api.shared.vo.CustomerInfoPoolVo;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;

/**
 * 客户信息池导出SERVICE
 * 
 * @author: 何斌
 * @create: 2015年5月31日 下午12:19:45
 */
@Service
public class CustomerInfoPoolExportService implements IExcelExportService {

	@Resource
	private DataDictionaryValueMapper dataDictionaryValueMapper;

	@Resource
	private IOperationLogService iOperationLogService;
	
	@Resource
	private ICustomerInfoPoolService customerInfoPoolService;

	@Override
	public InputStream export(CustomerInfoPoolVo customerInfoPoolVo) {
		//判断数据量太小
		long dataSize = customerInfoPoolService.countAllUploadCustomer(customerInfoPoolVo);
		if(dataSize > this.getExportMaxNum()){
			throw new CustomerInfoPoolException(CustomerInfoPoolException.DATA_TOO_BIG);
		}
		List<CustomerInfoPoolEntity> list = customerInfoPoolService.queryAllUploadCustomer(customerInfoPoolVo);
		// 创建一个HSSFWorkbook
		HSSFWorkbook wb = new HSSFWorkbook();
		// 由HSSFWorkbook创建一个HSSFSheet
		HSSFSheet sheet = wb.createSheet();
		// 设置sheet的名称
		wb.setSheetName(0, BseConstants.TABLE_NAME);
		// 创建第一行标题
		this.setTitleRow(sheet);
		// 封装数据
		this.setRecord(list, sheet);
		// 使用apache的commons-lang.jar产生随机的字符串作为文件名
		String fileName = RandomStringUtils.randomAlphanumeric(10);
		// 生成xls文件名必须要是随机的，确保每个线程访问都产生不同的文件
		StringBuilder sb = new StringBuilder(fileName);
		File file = null;
		try {
			file = new File(AttachmentRootPath.getAttachRootPath()
					+ ConfigFileLoadUtil.getPropertiesForClasspath(
							"config.properties").getProperty("download.dir")
					+ sb.append(".xls").toString());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		OutputStream os = null;
		try {
            if (file != null) {
                os = new FileOutputStream(file);
            }
            try {
				// 写入xls文件
				wb.write(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		InputStream is = null;
		try {
            if (file != null) {
                is = new FileInputStream(file);
            }
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 获取当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		// 操作日志保存
		OperationLogEntity operationLogEntity = new OperationLogEntity();
		operationLogEntity.setOperationType(BseConstants.OPERATION_TYPE);
		operationLogEntity.setOperationUser(currentUser.getUserName());
		operationLogEntity.setOperationTime(new Date());
		operationLogEntity.setOperationIp(GetHostAddressUtil
				.getIpAddr(ServletActionContext.getRequest()));
		iOperationLogService.saveOperationLog(operationLogEntity);
		return is;
	}

	private void setTitleRow(HSSFSheet sheet) {
		// 由HSSFSheet创建HSSFRow 列
		HSSFRow row = sheet.createRow(0);
		// 单元格1
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("公司简称");
		// 单元格2
		cell = row.createCell(1);
		cell.setCellValue("联系人");
		// 单元格3
		cell = row.createCell(2);
		cell.setCellValue("联系方式");
		// 单元格4
		cell = row.createCell(3);
		cell.setCellValue("邮箱");
		// 单元格5
		cell = row.createCell(4);
		cell.setCellValue("省");
		// 单元格6
		cell = row.createCell(5);
		cell.setCellValue("市");
		// 单元格7
		cell = row.createCell(6);
		cell.setCellValue("区");
		// 单元格8
		cell = row.createCell(7);
		cell.setCellValue("公司地址");
		// 单元格9
		cell = row.createCell(8);
		cell.setCellValue("所属事业部");
		// 单元格10
		cell = row.createCell(9);
		cell.setCellValue("所属大区");
		// 单元格11
		cell = row.createCell(10);
		cell.setCellValue("分发状态");
		// 单元格12
		cell = row.createCell(11);
		cell.setCellValue("负责人");
		// 单元格13
		cell = row.createCell(12);
		cell.setCellValue("上传人工号");
		// 单元格14
		cell = row.createCell(13);
		cell.setCellValue("上传人姓名");
		// 单元格15
		cell = row.createCell(14);
		cell.setCellValue("上传人部门");
		// 单元格16
		cell = row.createCell(15);
		cell.setCellValue("上传人岗位");
		// 单元格17
		cell = row.createCell(16);
		cell.setCellValue("上传时间");
	}

	private void setRecord(List<CustomerInfoPoolEntity> list, HSSFSheet sheet) {
		for (int i = 1; i < list.size() + 1; i++) {
			CustomerInfoPoolEntity cEntity = list.get(i - 1);
			HSSFRow row = sheet.getRow(i);
			if (row == null) {
				row = sheet.createRow(i);
				// 填充着一列的数据
				this.setRowRecord(row, cEntity);
			}
		}
	}

	private void setRowRecord(HSSFRow row, CustomerInfoPoolEntity cEntity) {
		HSSFCell cell = row.getCell(0);
		// 公司简称
		if (cell == null) {
			cell = row.createCell(0);
			cell.setCellValue(cEntity.getCompanyName());
		}
		// 联系人
		cell = row.getCell(1);
		if (cell == null) {
			cell = row.createCell(1);
			cell.setCellValue(cEntity.getContactPerson());
		}
		// 联系方式
		cell = row.getCell(2);
		if (cell == null) {
			cell = row.createCell(2);
			cell.setCellValue(cEntity.getContactWay());
		}
		// 邮箱
		cell = row.getCell(3);
		if (cell == null) {
			cell = row.createCell(3);
			cell.setCellValue(cEntity.getEmail());
		}
		// 省
		cell = row.getCell(4);
		if (cell == null) {
			cell = row.createCell(4);
			cell.setCellValue(cEntity.getProvince());
		}
		// 市
		cell = row.getCell(5);
		if (cell == null) {
			cell = row.createCell(5);
			cell.setCellValue(cEntity.getCity());
		}
		// 区
		cell = row.getCell(6);
		if (cell == null) {
			cell = row.createCell(6);
			cell.setCellValue(cEntity.getArea());
		}
		// 公司地址
		cell = row.getCell(7);
		if (cell == null) {
			cell = row.createCell(7);
			cell.setCellValue(cEntity.getCompanyAddress());
		}
		// 所属事业部
		cell = row.getCell(8);
		if (cell == null) {
			cell = row.createCell(8);
			cell.setCellValue(this.getBusinessName(cEntity.getBusiness()));
		}
		// 所属大区
		cell = row.getCell(9);
		if (cell == null) {
			cell = row.createCell(9);
			cell.setCellValue(cEntity.getRegions());
		}
		// 分发状态
		cell = row.getCell(10);
		if (cell == null) {
			cell = row.createCell(10);
			cell.setCellValue(this.getDispenseStatusName(cEntity.getDispenseStatus()));
		}
		// 负责人
		cell = row.getCell(11);
		if (cell == null) {
			cell = row.createCell(11);
			cell.setCellValue(cEntity.getManagePerson());
		}
		// 上传人工号
		cell = row.getCell(12);
		if (cell == null) {
			cell = row.createCell(12);
			if(cEntity.getEmployeeEntity() != null){
				cell.setCellValue(cEntity.getEmployeeEntity().getEmpCode());
			}else{
				cell.setCellValue("");
			}
		}
		// 上传人姓名
		cell = row.getCell(13);
		if (cell == null) {
			cell = row.createCell(13);
			if(cEntity.getEmployeeEntity() != null){
				cell.setCellValue(cEntity.getEmployeeEntity().getEmpName());
			}else{
				cell.setCellValue("");
			}
		}
		// 上传人部门
		cell = row.getCell(14);
		if (cell == null) {
			cell = row.createCell(14);
			if(cEntity.getEmployeeEntity() != null){
				cell.setCellValue(cEntity.getEmployeeEntity().getDeptname());
			}else{
				cell.setCellValue("");
			}
		}
		// 上传人岗位
		cell = row.getCell(15);
		if (cell == null) {
			cell = row.createCell(15);
			if(cEntity.getEmployeeEntity() != null){
				cell.setCellValue(cEntity.getEmployeeEntity().getJobname());
			}else{
				cell.setCellValue("");
			}
		}
		// 上传时间
		cell = row.getCell(16);
		if (cell == null) {
			cell = row.createCell(16);
			if(cEntity.getEmployeeEntity() != null){
				cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cEntity.getCreateDate()));
			}else{
				cell.setCellValue("");
			}
		}
	}

	/**
	 * 分发状态
	 * 
	 * @param businessName
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update
	 */
	private String getDispenseStatusName(String dispenseStatusCode) {
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMER_DISSTATE");
		entity.setActive(BseConstants.ACTIVE);
		entity.setValueCode(dispenseStatusCode);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if (lists.size() > 0) {
			value = lists.get(0).getValueName();
		}
		return value;
	}
	
	/**
	 * 事业部名称
	 * 
	 * @param businessName
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月28日
	 * @update
	 */
	private String getBusinessName(String businessCode) {
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMERINFOPOOL_BUSINESS");
		entity.setActive(BseConstants.ACTIVE);
		entity.setValueCode(businessCode);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if (lists.size() > 0) {
			value = lists.get(0).getValueName();
		}
		return value;
	}
	
	/**
	 * 从数据字典查询最大导出数
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年12月15日
	 * @update 
	 */
	private long getExportMaxNum() {
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode(BseConstants.EXPORT_MAX_NUM);
		entity.setActive(BseConstants.ACTIVE);
		entity.setValueCode(BseConstants.EXPORT_MAX_NUM);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper.queryDataDictionaryValueByCodeName(entity);
		long value = 0;
		if (lists.size() > 0) {
			value = Long.parseLong(lists.get(0).getValueName());
		}
		return value;
	}
}
