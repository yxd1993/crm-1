package com.hoau.crm.module.sales.server.service.impl;

import com.hoau.crm.module.bse.api.server.service.IOperationLogService;
import com.hoau.crm.module.bse.api.server.service.IReviewHistoryService;
import com.hoau.crm.module.bse.api.server.util.BseConstants;
import com.hoau.crm.module.bse.api.server.util.GetHostAddressUtil;
import com.hoau.crm.module.bse.api.shared.domain.DataDictionaryValueEntity;
import com.hoau.crm.module.bse.api.shared.domain.OperationLogEntity;
import com.hoau.crm.module.bse.api.shared.domain.ReviewHistoryEntity;
import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.bse.server.dao.DataDictionaryValueMapper;
import com.hoau.crm.module.customer.api.server.ICustomerService;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerNatureConvertEntity;
import com.hoau.crm.module.customer.api.shared.exception.CustomerException;
import com.hoau.crm.module.customer.server.service.impl.CustomerSyncService;
import com.hoau.crm.module.sales.api.server.service.ISaleChatsService;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatRandomEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleReserveEntity;
import com.hoau.crm.module.sales.api.shared.exception.SalesCommonException;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatRandomVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatsVo;
import com.hoau.crm.module.sales.server.dao.SaleChatsMapper;
import com.hoau.crm.module.sales.server.dao.SaleReserveMapper;
import com.hoau.crm.module.sales.server.dao.SignMapper;
import com.hoau.crm.module.util.AttachmentRootPath;
import com.hoau.crm.module.util.UUIDUtil;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.shared.util.ConfigFileLoadUtil;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author 丁勇
 * @date 2015年6月9日
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class SaleChatsServiceImpl implements ISaleChatsService {
	/**
	 * 注入洽谈表dao接口
	 */
	@Resource
	SaleChatsMapper chatsmapper;
	@Resource
	SaleReserveMapper reserveMapper;
	@Resource
	ICustomerService icustomerservice;
	@Resource
	IReviewHistoryService ireviewhistoty;
	@Resource
	IOperationLogService iOperationLogService;
	@Resource
	DataDictionaryValueMapper dataDictionaryValueMapper;
    @Resource
    CustomerSyncService customerSyncService;
    @Resource
	SignMapper signMapper;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
	@Override
	@Transactional
	public void saveOrUpdateSaleChat(SaleChatsEntity salechats,
			UserEntity currUser) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 洽谈信息不能为空
		if (StringUtils.isEmpty(salechats)) {
			throw new SalesCommonException(SalesCommonException.ADD_CHATS_NULL);
		}
		// 时间参数不能为空
		if (StringUtils.isEmpty(salechats.getChatStartTime())
				&& StringUtils.isEmpty(salechats.getChatEndTime())) {
			throw new SalesCommonException(
					SalesCommonException.ADD_DATETIME_ISNULL);
		}
		// 洽谈时间不能大于当前时间
		if(salechats.getChatStartTime().getTime() >= new Date().getTime()){
			throw new SalesCommonException(
					SalesCommonException.ADD_CHATS_DATETIME_INVALID);
		}
		// 洽谈开始结束时间不符合
		if (salechats.getChatStartTime().getTime() >= salechats.getChatEndTime()
				.getTime()) {
			throw new SalesCommonException(
					SalesCommonException.ADD_DATETIME_INVALID);
		}
		// 判断是否传入客户信息
		if (StringUtils.isEmpty(salechats.getCustomerEntity())
				|| StringUtils.isEmpty(salechats.getCustomerEntity().getId())) {
			throw new CustomerException(CustomerException.ADD_CUSTOMER_NULL);
		}
		// 判断是添加还是修改
		if (StringUtils.isEmpty(salechats.getId())) {
			// 主键赋值
			String uuid = UUIDUtil.getUUID();
			salechats.setId(uuid);
			salechats.setCreateUser(currUser.getEmpEntity().getEmpCode());
			if(!StringUtils.isEmpty(currUser.getEmpEntity().getDeptEntity())){
				salechats.setCreateDeptCode(currUser.getEmpEntity().getDeptEntity().getDeptCode());
			}
			// 保存洽谈
			chatsmapper.addSaleChat(salechats);

			// 查询出客户信息
			CustomerEntity customerEntity = icustomerservice
					.queryCustomerInfoById(salechats.getCustomerEntity());
			// 判断数据是否有效
			if (customerEntity == null || StringUtils.isEmpty(customerEntity.getActive())
					|| customerEntity.getActive().equals(BseConstants.INACTIVE)) {
				throw new CustomerException(CustomerException.UPDATE_IS_DELETE);
			}
			map.put("accountId", customerEntity.getId());
			// 如果客户是潜在客户,是潜在客户则转为洽谈用户
			if (BseConstants.CUSTOMER_NATURE_POTENTIAL.equals(customerEntity
					.getAccountType())) {
				//转为洽谈客户
				map.put("accountType", BseConstants.CUSTOMER_NATURE_CHAT);
				chatsmapper.updateCustomerType(map);
				this.saveCustomerNatureConvertRecord(customerEntity,currUser,BseConstants.CUSTOMER_NATURE_POTENTIAL,BseConstants.CUSTOMER_NATURE_CHAT);
			//如果客户是流失客户
			} else if (BseConstants.CUSTOMER_NATURE_RUNOFF.equals(customerEntity.getAccountType())) {
				if (!StringUtils.isEmpty(customerEntity.getContractEndTime())){
					//合同时间在有效时间内 转为签约客户
					if(customerEntity.getContractEndTime().getTime() > new Date().getTime()){
						map.put("accountType",BseConstants.CUSTOMER_NATURE_SIGN);
						chatsmapper.updateCustomerType(map);
                        // 数据同步DC
                        CustomerEntity newCustomerEntity = icustomerservice.queryCustomerInfoById(salechats.getCustomerEntity());
                        if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                            customerSyncService.updateDcAccount(newCustomerEntity);
                        }
						this.saveCustomerNatureConvertRecord(customerEntity,currUser,BseConstants.CUSTOMER_NATURE_RUNOFF,BseConstants.CUSTOMER_NATURE_SIGN);
					//合同时间不在有效时间内 转为意向客户
					}else if(customerEntity.getContractEndTime().getTime() < new Date().getTime()){
						map.put("accountType",BseConstants.CUSTOMER_NATURE_INTENTION);
						chatsmapper.updateCustomerType(map);
                        // 数据同步DC
                        CustomerEntity newCustomerEntity = icustomerservice.queryCustomerInfoById(salechats.getCustomerEntity());
                        if(!StringUtil.isEmpty(newCustomerEntity.getDcAccount())){
                            customerSyncService.updateDcAccount(newCustomerEntity);
                        }
						this.saveCustomerNatureConvertRecord(customerEntity,currUser,BseConstants.CUSTOMER_NATURE_RUNOFF,BseConstants.CUSTOMER_NATURE_INTENTION);
					}
				}
			}
			// 判断是否是赴约或者手动关联了预约信息
			if (!StringUtils.isEmpty(salechats.getReserveEntity())) {
				if (!StringUtils.isEmpty(salechats.getReserveEntity().getId())) {
					map.put("id", salechats.getReserveEntity().getId());
					Set<String> functions = currUser.getFunctionCodes();
					if(!functions.contains(BseConstants.ALLDATA_RESERVE)){
						map.put("userCode", currUser.getEmpEntity().getEmpCode());
					}
					// 获取关联的预约信息
					SaleReserveEntity reserveEntity = reserveMapper.getReserveDetailByIdUseUpdate(map);
					// 判断数据是否有效
					if (StringUtils.isEmpty(reserveEntity)||StringUtils.isEmpty(reserveEntity.getActive())
							||reserveEntity.getActive().equals(
									BseConstants.INACTIVE)) {
						throw new CustomerException(
								CustomerException.UPDATE_IS_DELETE);
					}
					// 修改预约状态
					reserveEntity.setIsAgreement("Y");
					reserveMapper.updateReserveplan(reserveEntity);
				}
			}
			//签到信息已被否关联
			if(salechats.getSign()!=null&&signMapper.isHasThereSign(salechats.getSign().getId())>0){
				throw new SalesCommonException("此签到信息已经在其他终端绑定,请重置重新选择！");
			}
			//修改签到的状态(上门拜访)
			if (BseConstants.CHATSTYPE_VISIT.equals(salechats.getChatType()) && !StringUtils.isEmpty(salechats.getSign().getId())) {
				signMapper.updateRelationChatStatus(salechats.getSign().getId());
			}
			// 判断洽谈方式
			String operate = salechats.getChatType().equals(
					BseConstants.CHATSTYPE_VISIT) ? BseConstants.OPER_CREATE_CHATS_VISIT
					: BseConstants.OPER_CREATE_CHATS_PHONE;
			// 保存新增洽谈记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, null, salechats, null, operate, currUser
							.getEmpEntity().getEmpCode()));
		} else {
			salechats.setModifyUser(currUser.getEmpEntity().getEmpCode());
			// 判断原来的数据是否已经删除
			SaleChatsEntity chats = chatsmapper
					.getChatsDetailByIdUseUpdate(salechats.getId());
			if (StringUtils.isEmpty(chats)
					|| StringUtils.isEmpty(chats.getActive())||chats.getActive().equals(BseConstants.INACTIVE)) {
				throw new SalesCommonException(
						SalesCommonException.UPDATE_IS_DELETE);
			}
			// 判断是否是赴约或者手动关联了预约信息
//			if (!StringUtils.isEmpty(salechats.getReserveEntity())) {
//				if (!StringUtils.isEmpty(salechats.getReserveEntity().getId())) {
//					// 获取关联的预约信息
//					SaleReserveEntity reserveEntity = reserveMapper
//							.getReserveDetailByIdUseUpdate(salechats
//									.getReserveEntity().getId());
//					// 判断数据是否有效
//					if (StringUtils.isEmpty(reserveEntity.getActive())
//							&& reserveEntity.getActive().equals(
//									BseConstants.INACTIVE)) {
//						throw new CustomerException(
//								CustomerException.UPDATE_IS_DELETE);
//					}
//					// 修改预约状态
//					reserveEntity.setIsAgreement("Y");
//					reserveMapper.updateReserveplan(reserveEntity);
//				}
//			}
			chatsmapper.updateSaleChats(salechats);
			// 保存修改洽谈记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, null, salechats, null,
					BseConstants.OPER_UPDATE_CHATS, currUser.getEmpEntity()
							.getEmpCode()));
		}
	}

	/**
	 * 保存客户性质转换
	 * 
	 * @param currUser
	 * @param oldStatus
	 * @param newStatus
	 * @author: 何斌
	 * @date: 2015年8月15日
	 * @update 
	 */
	private void saveCustomerNatureConvertRecord(CustomerEntity customerEntity,UserEntity currUser,String oldStatus,String newStatus) {
		CustomerNatureConvertEntity convertEntity = new CustomerNatureConvertEntity();
		//主键
		convertEntity.setId(UUIDUtil.getUUID());
		//客户id
		convertEntity.setAccountId(customerEntity.getId());
		//原状态
		convertEntity.setOldStatus(oldStatus);
		//新状态
		convertEntity.setNewStatus(newStatus);
		//转换人
		convertEntity.setConvertUser(currUser.getUserName());
		icustomerservice.addCustomerConvertRecord(convertEntity);
	}

	@Override
	public SaleChatsEntity getChatsDetailByIdUseUpdate(SaleChatsEntity chats) {
		return chatsmapper.getChatsDetailByIdUseUpdate(chats.getId());
	}

	@Override
	public SaleChatsVo getChatsDetailById(SaleChatsEntity chats) {
		return chatsmapper.getChatsDetailById(chats.getId());
	}

	@Override
	public List<SaleChatsEntity> getChatsInfoByPaging(SaleChatsVo chatsVo,
			RowBounds rb) {
		if (rb == null) {
			new SalesCommonException(SalesCommonException.RB_NULL);
		}
		return chatsmapper.getChatsInfoByPaging(this.getQueryParams(chatsVo),
				rb);
	}

	@Override
	public long chatsCount(SaleChatsVo chatsVo) {
		return chatsmapper.chatsCount(this.getQueryParams(chatsVo));
	}

	@Override
	public void delChats(List<String> ids, String delDesc) {
		if (ids.size() <= 0 || ids == null) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		// 用户信息
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Map<String,Object>	map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("username", currUser.getEmpEntity().getEmpCode());
		map.put("delDesc", delDesc);
		chatsmapper.deleteChats(map);
		for (int i = 0; i < ids.size(); i++) {
			SaleChatsEntity chatsEntity = chatsmapper
					.getChatsDetailByIdUseUpdate(ids.get(i));
			// 保存删除记录
			ireviewhistoty.insertReviewOrHistory(new ReviewHistoryEntity(null,
					null, null, chatsEntity, null,
					BseConstants.OPER_DELETE_CHATS, currUser.getEmpEntity()
							.getEmpCode()));
		}
	}

	@Override
	public boolean isDeptManeger(String empCode) {
		if (StringUtils.isEmpty(empCode)) {
			throw new SalesCommonException(
					SalesCommonException.ADD_RESVERVER_NULL);
		}
		if (chatsmapper.isDeptManeger(empCode) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public InputStream exportChatsInfo(SaleChatsVo saleChatsVo) {
		//数据量大小
		long dataSize = chatsmapper.countChatsInfoToExport(this.getQueryParams(saleChatsVo));
		//数据量大小判断
		if(dataSize > this.getExportMaxNum()){
			throw new SalesCommonException(SalesCommonException.DATA_TOO_BIG);
		}
		// 获得需要导出的记录
		List<SaleChatsVo> saleChatsVoLists = this.getChatsInfoToExport(saleChatsVo);
		// 创建一个HSSFWorkbook
		HSSFWorkbook wb = new HSSFWorkbook();
		// 由HSSFWorkbook创建一个HSSFSheet
		HSSFSheet sheet = wb.createSheet();
		// 设置sheet的名称
		wb.setSheetName(0, BseConstants.CHAT_EXPORT_TABLENAME);
		// 创建第一行标题
		this.setTitleRow(sheet);
		// 封装数据
		this.setRecord(saleChatsVoLists, sheet);
		// 使用apache的commons-lang.jar产生随机的字符串作为文件名
		String fileName = RandomStringUtils.randomAlphanumeric(10);
		// 生成xls文件名必须要是随机的，确保每个线程访问都产生不同的文件
		StringBuffer sb = new StringBuffer(fileName);
		File file = null;
		try {
			file = new File(AttachmentRootPath.getAttachRootPath()
					+ ConfigFileLoadUtil.getPropertiesForClasspath(
							"config.properties").getProperty("chat.export.dir")
					+ sb.append(".xls").toString());
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
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
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 获取当前用户
		UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
		// 操作日志保存
		OperationLogEntity operationLogEntity = new OperationLogEntity();
		operationLogEntity.setOperationType(BseConstants.OPERATION_TYPE_CHAT);
		operationLogEntity.setOperationUser(currentUser.getUserName());
		operationLogEntity.setOperationTime(new Date());
		operationLogEntity.setOperationIp(GetHostAddressUtil
				.getIpAddr(ServletActionContext.getRequest()));
		iOperationLogService.saveOperationLog(operationLogEntity);
		return is;
	}

	/**
	 * 获得导出记录
	 * 
	 * @param saleChatsVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月14日
	 * @update
	 */
	private List<SaleChatsVo> getChatsInfoToExport(SaleChatsVo saleChatsVo) {
		return chatsmapper.getChatsInfoToExport(this.getQueryParams(saleChatsVo));
	}

	/**
	 * 查询参数
	 * 
	 * @param saleChatsVo
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月14日
	 * @update
	 */
	private Map<String, Object> getQueryParams(SaleChatsVo saleChatsVo) {
		//查询参数
	Map<String,Object>	map = new HashMap<String, Object>();
		if (!StringUtils.isEmpty(saleChatsVo)) {
			if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity())) {
				if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getCustomerEntity())) {
					// 获取客户对象
					CustomerEntity customerEntity = saleChatsVo.getChatsEntity().getCustomerEntity();
					// 客户名称
					if (!StringUtils.isEmpty(customerEntity.getEnterpriseName())) {
						map.put("enterpriseName","%" + customerEntity.getEnterpriseName() + "%");
					}
				}
				if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getContactEntity())) {
					// 获取联系人对象
					ContactEntity contactEntity = saleChatsVo.getChatsEntity().getContactEntity();
					// 联系人名称
					if (!StringUtils.isEmpty(contactEntity.getContactName())) {
						map.put("contactName","%" + contactEntity.getContactName() + "%");
					}
				}
			}
			// 洽谈开始时间
			if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getChatStartTime())) {
				map.put("chatStartTime", sdf.format(saleChatsVo.getChatsEntity().getChatStartTime()));
			}
			// 洽谈结束时间
			if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getChatEndTime())) {
				map.put("chatEndTime", sdf.format(BseConstants.getEndDate(saleChatsVo.getChatsEntity().getChatEndTime())));
			}
			// 洽谈方式
			if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getChatType())) {
				map.put("chatType", saleChatsVo.getChatsEntity().getChatType());
			}
			// 创建人
			if (!StringUtils.isEmpty(saleChatsVo.getCreateUserName())) {
				map.put("createUserName", saleChatsVo.getCreateUserName());
			}
			// 门店代码
			if (!StringUtils.isEmpty(saleChatsVo.getChatsEntity().getCustomerEntity())) {
				// 获取客户对象
				CustomerEntity customerEntity = saleChatsVo.getChatsEntity().getCustomerEntity();
				// 门店代码
				if (!StringUtils.isEmpty(customerEntity.getTierCode())) {
					map.put("tierCode", customerEntity.getTierCode());
				}
			}
			// 当前用户
			UserEntity currentUser = (UserEntity) UserContext.getCurrentUser();
			Set<String> functionCodes = currentUser.getFunctionCodes();
			if (!functionCodes.contains(BseConstants.ALLDATA_CHAT)) {
				if (currentUser != null	&& currentUser.getEmpEntity() != null
						&& !StringUtils.isEmpty(currentUser.getEmpEntity().getEmpCode())) {
					// 判断是否为部门负责人
					if (this.isDeptManeger(currentUser.getEmpEntity().getEmpCode())) {
						map.put("deptCode", currentUser.getEmpEntity().getDeptEntity().getDeptCode());
					} else {
						map.put("empCode", currentUser.getEmpEntity().getEmpCode());
					}
				}
			}
		}
		return map;
	}

	private void setTitleRow(HSSFSheet sheet) {
		// 由HSSFSheet创建HSSFRow 列
		HSSFRow row = sheet.createRow(0);
		// 单元格1
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("客户企业全称");
		// 单元格2
		cell = row.createCell(1);
		cell.setCellValue("DC账号");
		// 单元格3
		cell = row.createCell(2);
		cell.setCellValue("洽谈开始时间");
		// 单元格4
		cell = row.createCell(3);
		cell.setCellValue("洽谈结束时间");
		// 单元格5
		cell = row.createCell(4);
		cell.setCellValue("联系人姓名");
		// 单元格6
		cell = row.createCell(5);
		cell.setCellValue("联系电话");
		// 单元格7
		cell = row.createCell(6);
		cell.setCellValue("洽谈方式");
		// 单元格8
		cell = row.createCell(7);
		cell.setCellValue("关联预约");
		// 单元格9
		cell = row.createCell(8);
		cell.setCellValue("拜访地址");
		// 单元格10
		cell = row.createCell(9);
		cell.setCellValue("洽谈主题");
		// 单元格11
		cell = row.createCell(10);
		cell.setCellValue("洽谈内容");
		// 单元格12
		cell = row.createCell(11);
		cell.setCellValue("门店经理陪同");
		// 单元格13
		cell = row.createCell(12);
		cell.setCellValue("路区经理陪同");
		// 单元格14
		cell = row.createCell(13);
		cell.setCellValue("大区总经理陪同");
		// 单元格15
		cell = row.createCell(14);
		cell.setCellValue("事业部总经理陪同");
		// 单元格16
		cell = row.createCell(15);
		cell.setCellValue("营运副总陪同");
		// 单元格17
		cell = row.createCell(16);
		cell.setCellValue("集团总裁陪同");
		// 单元格18
		cell = row.createCell(17);
		cell.setCellValue("创建人工号");
		// 单元格19
		cell = row.createCell(18);
		cell.setCellValue("创建人");
		// 单元格20
		cell = row.createCell(19);
		cell.setCellValue("创建人岗位");
		// 单元格21
		cell = row.createCell(20);
		cell.setCellValue("创建人部门");
		// 单元格22
		cell = row.createCell(21);
		cell.setCellValue("一级部门");
		// 单元格23
		cell = row.createCell(22);
		cell.setCellValue("二级部门");
		// 单元格24
		cell = row.createCell(23);
		cell.setCellValue("三级部门");
		// 单元格25
		cell = row.createCell(24);
		cell.setCellValue("创建时间");
		// 单元格25
		cell = row.createCell(25);
		cell.setCellValue("录入平台");
	}

	private void setRecord(List<SaleChatsVo> list, HSSFSheet sheet) {
		for (int i = 1; i < list.size() + 1; i++) {
			SaleChatsVo saleChatsVo = list.get(i - 1);
			HSSFRow row = sheet.getRow(i);
			if (row == null) {
				row = sheet.createRow(i);
				// 填充着一列的数据
				this.setRowRecord(row, saleChatsVo);
			}
		}
	}

	private void setRowRecord(HSSFRow row, SaleChatsVo saleChatsVo) {
		// 企业全称
		HSSFCell cell = row.getCell(0);
		if (saleChatsVo.getChatsEntity() != null) {
			if (cell == null) {
				cell = row.createCell(0);
				if (saleChatsVo.getChatsEntity().getCustomerEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getCustomerEntity().getEnterpriseName());
				} else {
					cell.setCellValue("");
				}
			}
			// DC账号
			cell = row.getCell(1);
			if (cell == null) {
				cell = row.createCell(1);
				cell.setCellValue(saleChatsVo.getDcAccount());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:m:s");
			// 洽谈开始时间
			cell = row.getCell(2);
			if (cell == null) {
				cell = row.createCell(2);
				cell.setCellValue(sdf.format(saleChatsVo.getChatsEntity()
						.getChatStartTime()));
			}
			// 洽谈结束时间
			cell = row.getCell(3);
			if (cell == null) {
				cell = row.createCell(3);
				cell.setCellValue(sdf.format(saleChatsVo.getChatsEntity()
						.getChatEndTime()));
			}
			// 联系人姓名
			cell = row.getCell(4);
			if (cell == null) {
				cell = row.createCell(4);
				if (saleChatsVo.getChatsEntity().getContactEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getContactEntity().getContactName());
				} else {
					cell.setCellValue("");
				}

			}
			// 联系电话
			cell = row.getCell(5);
			if (cell == null) {
				cell = row.createCell(5);
				if (saleChatsVo.getChatsEntity().getContactEntity() != null) {
					if (StringUtils.isEmpty(saleChatsVo.getChatsEntity()
							.getContactEntity().getCellphone())) {
						cell.setCellValue(saleChatsVo.getChatsEntity()
								.getContactEntity().getTelephone());
					} else {
						cell.setCellValue(saleChatsVo.getChatsEntity()
								.getContactEntity().getCellphone());
					}
				} else {
					cell.setCellValue("");
				}

			}
			// 洽谈方式
			cell = row.getCell(6);
			if (cell == null) {
				cell = row.createCell(6);
				cell.setCellValue(this.getChatTypeName(saleChatsVo
						.getChatsEntity().getChatType()));
			}
			// 关联预约
			cell = row.getCell(7);
			if (cell == null) {
				cell = row.createCell(7);
				cell.setCellValue(saleChatsVo.getReserveInfo());
			}
			// 拜访地址
			cell = row.getCell(8);
			if (cell == null) {
				cell = row.createCell(8);
				if (saleChatsVo.getChatsEntity().getCustomerEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getReserveAddress());
				} else {
					cell.setCellValue("");
				}
			}
			// 洽谈主题
			cell = row.getCell(9);
			if (cell == null) {
				cell = row.createCell(9);
				cell.setCellValue(saleChatsVo.getChatsEntity().getChatTheme());
			}
			// 洽谈内容
			cell = row.getCell(10);
			if (cell == null) {
				cell = row.createCell(10);
				cell.setCellValue(saleChatsVo.getChatsEntity()
						.getChatContents());
			}
			// 门店经理陪同
			cell = row.getCell(11);
			if (cell == null) {
				cell = row.createCell(11);
				cell.setCellValue(saleChatsVo.getTierEmpName());
			}
			// 路区经理陪同
			cell = row.getCell(12);
			if (cell == null) {
				cell = row.createCell(12);
				cell.setCellValue(saleChatsVo.getRoadEmpName());
			}
			// 大区总经理陪同
			cell = row.getCell(13);
			if (cell == null) {
				cell = row.createCell(13);
				cell.setCellValue(saleChatsVo.getRegionsEmpName());
			}
			// 事业部总经理陪同
			cell = row.getCell(14);
			if (cell == null) {
				cell = row.createCell(14);
				cell.setCellValue(saleChatsVo.getBusinessUnitEmpName());
			}
			// 营运副总陪同
			cell = row.getCell(15);
			if (cell == null) {
				cell = row.createCell(15);
				cell.setCellValue(saleChatsVo.getOdEmpName());
			}
			// 集团总裁陪同
			cell = row.getCell(16);
			if (cell == null) {
				cell = row.createCell(16);
				cell.setCellValue(saleChatsVo.getCeoEmpName());
			}
			// 创建人工号
			cell = row.getCell(17);
			if (cell == null) {
				cell = row.createCell(17);
				if (saleChatsVo.getChatsEntity().getEmployeeEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getEmployeeEntity().getEmpCode());
				} else {
					cell.setCellValue("");
				}
			}
			// 创建人
			cell = row.getCell(18);
			if (cell == null) {
				cell = row.createCell(18);
				if (saleChatsVo.getChatsEntity().getEmployeeEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getEmployeeEntity().getEmpName());
				} else {
					cell.setCellValue("");
				}
			}
			// 创建人岗位
			cell = row.getCell(19);
			if (cell == null) {
				cell = row.createCell(19);
				if (saleChatsVo.getChatsEntity().getEmployeeEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getEmployeeEntity().getJobname());
				} else {
					cell.setCellValue("");
				}
			}
			// 创建人部门
			cell = row.getCell(20);
			if (cell == null) {
				cell = row.createCell(20);
				if (saleChatsVo.getChatsEntity().getEmployeeEntity() != null) {
					cell.setCellValue(saleChatsVo.getChatsEntity()
							.getEmployeeEntity().getDeptname());
				} else {
					cell.setCellValue("");
				}
			}
			// 一级部门
			cell = row.getCell(21);
			if (cell == null) {
				cell = row.createCell(21);
				cell.setCellValue(saleChatsVo.getOneLevelUnit());
			}
			// 二级部门
			cell = row.getCell(22);
			if (cell == null) {
				cell = row.createCell(22);
				cell.setCellValue(saleChatsVo.getTwoLevelUnit());
			}
			// 三级部门
			cell = row.getCell(23);
			if (cell == null) {
				cell = row.createCell(23);
				cell.setCellValue(saleChatsVo.getThreeLevelUnit());
			}
			// 创建时间
			cell = row.getCell(24);
			if (cell == null) {
				cell = row.createCell(24);
				cell.setCellValue(sdf.format(saleChatsVo.getChatsEntity()
						.getCreateDate()));
			}
			// 创建时间
			cell = row.getCell(25);
			if (cell == null) {
				cell = row.createCell(25);
				if (saleChatsVo.getChatsEntity().getPlatform() != null) {
					if("N".equals(saleChatsVo.getChatsEntity().getPlatform())){
						cell.setCellValue("web客户端");
					}else{
						cell.setCellValue("app客户端");
					}
				}else{
					cell.setCellValue("");
				}
			}
		}
	}

	/**
	 * 根据洽谈方式编码转化成洽谈名称
	 * 
	 * @param businessName
	 * @return
	 * @author: 何斌
	 * @date: 2015年7月14日
	 * @update
	 */
	private String getChatTypeName(String chatTypeCode) {
		DataDictionaryValueEntity entity = new DataDictionaryValueEntity();
		entity.setTermsCode("CUSTOMER_YXLX");
		entity.setActive(BseConstants.ACTIVE);
		entity.setValueCode(chatTypeCode);
		List<DataDictionaryValueEntity> lists = dataDictionaryValueMapper
				.queryDataDictionaryValueByCodeName(entity);
		String value = "";
		if (lists.size() > 0) {
			value = lists.get(0).getValueName();
		}
		return value;
	}

	@Override
	public List<SaleChatRandomEntity> getSaleChatRandomInfos(SaleChatRandomVo saleChatRandomVo,
			RowBounds rb) {
		if(rb == null){
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		if(saleChatRandomVo == null ){
			throw new SalesCommonException(SalesCommonException.PARAM_NULL);
		}
		//查询参数配置
		Map<String,Object> params = getParamsByChatsRandom(saleChatRandomVo);
		//当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functionCodes = currUser.getFunctionCodes();
		if(!functionCodes.contains(BseConstants.ALLDATA_RANDOM_CHAT)){
			//当前用户部门
			params.put("deptCode", currUser.getEmpEntity().getDeptEntity().getDeptCode());
		}
		return chatsmapper.getSaleChatRandomInfos(params, rb);
	}

	@Override
	public long countSaleChatRandomInfos(SaleChatRandomVo saleChatRandomVo, RowBounds rb) {
		if(rb == null){
			throw new SalesCommonException(SalesCommonException.RB_NULL);
		}
		if(saleChatRandomVo == null ){
			throw new SalesCommonException(SalesCommonException.PARAM_NULL);
		}
		//查询参数配置
		Map<String,Object> params = getParamsByChatsRandom(saleChatRandomVo);
		
		//当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		Set<String> functionCodes = currUser.getFunctionCodes();
		if(!functionCodes.contains(BseConstants.ALLDATA_RANDOM_CHAT)){
			//当前用户部门
			params.put("deptCode", currUser.getEmpEntity().getDeptEntity().getDeptCode());
		}
		return chatsmapper.countSaleChatRandomInfos(params, rb);
	}

	@Override
	@Transactional
	public void updateCheckResult(SaleChatRandomVo saleChatRandomVo) {
		if(saleChatRandomVo == null || StringUtils.isEmpty(saleChatRandomVo.getIds())&&saleChatRandomVo.getIds().size()<=0){
			throw new SalesCommonException(SalesCommonException.PARAM_NULL);
		}
		
		Map<String,Object> params = new HashMap<String,Object>();
		//当前用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		params.put("status", saleChatRandomVo.getSaleChatRandomEntity().getStatus());
		if(StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getCheckResult())){
			params.put("checkResult", null);
		}else{
			params.put("checkResult", saleChatRandomVo.getSaleChatRandomEntity().getCheckResult());
		}
		params.put("ids", saleChatRandomVo.getIds());
		params.put("modifyUser", currUser.getUserName());
		chatsmapper.updateCheckResult(params);
	}
	@Override
	public SaleChatRandomEntity queryChatsRandomById(String id) {
		return chatsmapper.queryChatsRandomById(id);
	}
	/**
	 * 回访抽查列表中,查询参数公共配置方法
	 * @param saleChatRandomVo
	 * @return
	 * @author 丁勇
	 * @date 2015年8月18日
	 * @update 
	 */
	public Map<String,Object> getParamsByChatsRandom(SaleChatRandomVo saleChatRandomVo){
		Map<String,Object> params = new HashMap<String,Object>();
		//客户名称
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getCustomerName())){
			params.put("customerName", "%"+saleChatRandomVo.getSaleChatRandomEntity().getCustomerName()+"%");
		}
		//联系人手机号
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getCellphone())){
			params.put("cellphone",saleChatRandomVo.getSaleChatRandomEntity().getCellphone());
		}
		//创建开始时间
		if(!StringUtils.isEmpty(saleChatRandomVo.getStartDate())){
			params.put("startDate",sdf.format(saleChatRandomVo.getStartDate()));
		}
		//创建结束时间
		if(!StringUtils.isEmpty(saleChatRandomVo.getEndDate())){
			params.put("endDate",sdf.format(BseConstants.getEndDate(saleChatRandomVo.getEndDate())));
		}
		//洽谈方式
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getChatType())){
			params.put("chatType",saleChatRandomVo.getSaleChatRandomEntity().getChatType());
		}
		//状态
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getStatus())){
			params.put("status",saleChatRandomVo.getSaleChatRandomEntity().getStatus());
		}
		//创建人
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getCreateUserName())){
			params.put("createUserName",saleChatRandomVo.getSaleChatRandomEntity().getCreateUserName());
		}
		//部门
		if(!StringUtils.isEmpty(saleChatRandomVo.getSaleChatRandomEntity().getCreateUserDept())){
			params.put("createUserDept",saleChatRandomVo.getSaleChatRandomEntity().getCreateUserDept());
		}
		return params;
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
