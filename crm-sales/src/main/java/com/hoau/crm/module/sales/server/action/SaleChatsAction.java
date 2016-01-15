package com.hoau.crm.module.sales.server.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hoau.crm.module.bse.api.shared.domain.UserEntity;
import com.hoau.crm.module.customer.api.shared.domain.ContactEntity;
import com.hoau.crm.module.customer.api.shared.domain.CustomerEntity;
import com.hoau.crm.module.sales.api.server.service.ISaleChatsService;
import com.hoau.crm.module.sales.api.server.service.ISaleReserveService;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatRandomEntity;
import com.hoau.crm.module.sales.api.shared.domain.SaleChatsEntity;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatRandomVo;
import com.hoau.crm.module.sales.api.shared.vo.SaleChatsVo;
import com.hoau.hbdp.framework.exception.BusinessException;
import com.hoau.hbdp.framework.server.context.UserContext;
import com.hoau.hbdp.framework.server.web.action.AbstractAction;
import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 
 * @author 丁勇
 * @date 2015年6月11日
 */
@Controller
@Scope("prototype")
public class SaleChatsAction extends AbstractAction {

	/**
	 *
	 */
	private static final long serialVersionUID = 6650011635072877045L;
	
	/**
	 *注入洽谈service
	 */
	@Resource
	ISaleChatsService iChatService;
	/**
	 *注入预约service
	 */
	@Resource
	ISaleReserveService iReserveService;
	/**
	 * 洽谈列表
	 */
	private List<SaleChatsEntity> chatsList;
	/**
	 * 洽谈列表id
	 */
	private List<String> ids;

	/**
	 * 洽谈功能实体
	 */
	private SaleChatsEntity chatsEntity;

	/**
	 * 洽谈vo
	 */
	private SaleChatsVo chatsVo;

	/**
	 * 文件输入流
	 */
	private InputStream inputStream;
	
	/**
	 * 导出文件名
	 */
	private String fileName;
	
	/**
	 * 随机洽谈记录集合
	 */
	private List<SaleChatRandomEntity> saleChatRandomInfos;
	/**
	 * 随机洽谈VO
	 */
	private SaleChatRandomEntity saleChatRandomEntity;
	/**
	 * 随机洽谈VO
	 */
	private SaleChatRandomVo saleChatRandomVo;
	
	/**
	 * 分页查询
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update
	 */
	public String queryChatsByPageing() {
		try {
			RowBounds rb = new RowBounds(this.start, this.limit);
			chatsList = iChatService.getChatsInfoByPaging(chatsVo, rb);
			this.setTotalCount(iChatService.chatsCount(chatsVo));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 新增
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update
	 */
	public String saveOrUpdateChatsContents() {
		// 创建用户
		UserEntity currUser = (UserEntity) UserContext.getCurrentUser();
		try {
			//web端录入洽谈
			chatsVo.getChatsEntity().setPlatform("N");
			// 保存洽谈信息
			iChatService.saveOrUpdateSaleChat(chatsVo.getChatsEntity(),currUser);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}

	/**
	 * 按 id查询需要修改的洽谈信息
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月11日
	 * @update
	 */
	public String queryChatsByIdGetEntity() {
		try {
			chatsEntity = iChatService.getChatsDetailByIdUseUpdate(chatsEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 查看详情
	 * @return
	 * @author 丁勇
	 * @date 2015年6月18日
	 * @update 
	 */
	public String queryChatsByIdGetVo(){
		try {
			chatsVo = iChatService.getChatsDetailById(chatsEntity);
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 删除
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年6月17日
	 * @update
	 */
	public String delChats() {
		try {
			iChatService.delChats(ids,chatsEntity.getDelDesc());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 获取下载文件流
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年5月31日
	 * @update 
	 */
	public String excelExport(){
		// 转码
		if(chatsVo != null ){
			try {
				SaleChatsEntity saleChatsEntity = chatsVo.getChatsEntity();
				CustomerEntity customerEntity = chatsVo.getChatsEntity().getCustomerEntity();
				ContactEntity contactEntity = chatsVo.getChatsEntity().getContactEntity();
				// 客户全称
				if (!StringUtil.isEmpty(customerEntity.getEnterpriseName())) {
					customerEntity.setEnterpriseName(new String(customerEntity.getEnterpriseName().getBytes("iso-8859-1"), "UTF-8"));
				}
				// 联系人名称
				if (!StringUtil.isEmpty(contactEntity.getContactName())) {
					contactEntity.setContactName(new String(contactEntity.getContactName().getBytes("iso-8859-1"), "UTF-8"));
				}
				// 洽谈方式
				if (!StringUtil.isEmpty(saleChatsEntity.getChatType())) {
					saleChatsEntity.setChatType(new String(saleChatsEntity.getChatType().getBytes("iso-8859-1"), "UTF-8"));
				}
				// 创建人
				if (!StringUtil.isEmpty(chatsVo.getCreateUserName())) {
					chatsVo.setCreateUserName((new String(chatsVo.getCreateUserName().getBytes("iso-8859-1"), "UTF-8")));
				}
				// 门店代码
				if (!StringUtil.isEmpty(customerEntity.getTierCode())) {
					customerEntity.setTierCode((new String(customerEntity.getTierCode().getBytes("iso-8859-1"), "UTF-8")));
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		try {
			inputStream = iChatService.exportChatsInfo(chatsVo);
		} catch (BusinessException e) {
			return ERROR;
		}
		return returnSuccess();
	}
	
	/**
	 * 分页查询随机洽谈记录
	 * 
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public String getRandomChatInfos(){
		try {
			RowBounds rb = new RowBounds(this.start,this.limit);
			saleChatRandomInfos = iChatService.getSaleChatRandomInfos(saleChatRandomVo, rb);
			this.setTotalCount(iChatService.countSaleChatRandomInfos(saleChatRandomVo, rb));
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 按 id查询洽谈抽取信息
	 * 
	 * @return
	 * @author 丁勇
	 * @date 2015年8月18日
	 * @update
	 */
	public String queryChatsRandomById() {
		try {
			saleChatRandomEntity = iChatService.queryChatsRandomById(saleChatRandomEntity.getId());
		} catch (BusinessException e) {
			return returnError(e);
		}
		return returnSuccess();
	}
	/**
	 * 更新抽查结果
	 * @return
	 * @author: 何斌
	 * @date: 2015年8月17日
	 * @update 
	 */
	public String updateCheckResult(){
		try {
			iChatService.updateCheckResult(saleChatRandomVo);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnError(e);
		}
		return returnSuccess();
	}
	
	/**
	 * 为附件名称转码
	 * 
	 * @param fileName
	 * @return
	 * @throws UnsupportedEncodingException
	 * @author 何斌
	 * @date 2015-5-31
	 * @update
	 */
	private String encodeFilename(String fileName)
			throws UnsupportedEncodingException {
		try {
			// 支持谷歌
			fileName = URLEncoder.encode(fileName, "UTF8");
			// 如果在转换编码的时候出现异常了，则执行下面的转换操作。把UTF8的编码转换成iso-8859-1
		} catch (UnsupportedEncodingException e) {
			try {
				//
				fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
				// 抛出异常
			} catch (UnsupportedEncodingException e1) {
				throw new UnsupportedEncodingException();
			}
			throw new UnsupportedEncodingException();
		}
		return fileName;
	}

	public List<SaleChatsEntity> getChatsList() {
		return chatsList;
	}

	public void setChatsList(List<SaleChatsEntity> chatsList) {
		this.chatsList = chatsList;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public SaleChatsEntity getChatsEntity() {
		return chatsEntity;
	}

	public void setChatsEntity(SaleChatsEntity chatsEntity) {
		this.chatsEntity = chatsEntity;
	}

	public SaleChatsVo getChatsVo() {
		return chatsVo;
	}

	public void setChatsVo(SaleChatsVo chatsVo) {
		this.chatsVo = chatsVo;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		fileName = (sf.format(new Date()).toString()) + "洽谈记录.xls";
		try {
			fileName = encodeFilename(fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<SaleChatRandomEntity> getSaleChatRandomInfos() {
		return saleChatRandomInfos;
	}

	public void setSaleChatRandomInfos(
			List<SaleChatRandomEntity> saleChatRandomInfos) {
		this.saleChatRandomInfos = saleChatRandomInfos;
	}

	public SaleChatRandomEntity getSaleChatRandomEntity() {
		return saleChatRandomEntity;
	}

	public void setSaleChatRandomEntity(SaleChatRandomEntity saleChatRandomEntity) {
		this.saleChatRandomEntity = saleChatRandomEntity;
	}

	public SaleChatRandomVo getSaleChatRandomVo() {
		return saleChatRandomVo;
	}

	public void setSaleChatRandomVo(SaleChatRandomVo saleChatRandomVo) {
		this.saleChatRandomVo = saleChatRandomVo;
	}
	
}
