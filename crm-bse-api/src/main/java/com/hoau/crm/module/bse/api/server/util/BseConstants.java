package com.hoau.crm.module.bse.api.server.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hoau.hbdp.framework.shared.util.string.StringUtil;

/**
 * 模块常量类
 * 
 * @author 蒋落琛
 * @date 2015-5-13
 */
public class BseConstants {

	/** 生效 */
	public static final String ACTIVE = "Y";
	/** 未生效 */
	public static final String INACTIVE = "N";

	/**
	 * 布尔类型
	 */
	public static final String YES = "Y";
	public static final String NO = "N";

	/**
	 * 菜单类型
	 */
	public static final String FUNCTION_MODULE = "MODULE";
	public static final String FUNCTION_BUTTON = "BUTTON";

	/**
	 * CRM客户账号
	 */
	public static final String SEQ_TYPE = "ACCOUNT_CODE";

	/**
	 * 客户管理全国数据功能信息ID
	 */
	public static final String ALLDATA_CUSTOMERMANAGER = "101001004";

	/**
	 * 客户黄页全国数据功能信息ID
	 */
	public static final String ALLDATA_CUSTOMERPOOL = "101005006";
	
	/**
	 * 客户黄页客户管理部全国数据
	 */
	public static final String ALLDATA_CUSTOMERPOOL_CUSTOMERMANAGE = "101005007";
	
	/**
	 * 客户黄页新门店研究组全国数据
	 */
	public static final String ALLDATA_CUSTOMERPOOL_NEWSTORE = "101005008";
	
	/**
	 *合同列表全国数据功能信息ID
	 */
	public static final String ALLDATA_CONTRACT = "103004001";

	/**
	 * 客户管理查看子公司客户功能信息ID
	 */
	public static final String ALLDATA_SUBCOMPANYCUSTOMERMANAGER = "101001008";
	
	/**
	 *洽谈列表全国数据功能信息ID
	 */
	public static final String ALLDATA_CHAT = "103002001";
	
	/**
	 *预约列表全国数据功能信息ID
	 */
	public static final String ALLDATA_RESERVE = "103001001";

	/**
	 * 菜单权限 客户状态为GAM时否有修改权限
	 */
	public static final String FUNCTION_AUTH_RATING = "101001006";

	/**
	 * 菜单权限 全国门店数据
	 */
	public static final String FUNCTION_AUTH_ALLSTORE = "101001007";
	
	/**
	 * 菜单权限  新门店研究组门店数据
	 */
	public static final String FUNCTION_AUTH_NEWSTOREDATA = "101001009";
	
	/**
	 * 菜单权限  战略客户部数据
	 */
	public static final String FUNCTION_AUTH_TACTICCUSTOMER = "101001010";
	
	/**
	 * 菜单权限  客户管理部数据
	 */
	public static final String FUNCTION_AUTH_CUSTOMERMANAGE = "101001011";
	
	/**
	 * 角色名称--客户经理
	 */
	public static final String MANAGENAME = "客户经理";
	public static final String TEAMMANNAME = "团队经理";
	
	/**
	 * 新增客户权限
	 */
	public static final String FUNCTION_INSERT_CUSTOMER = "101001001";
	public static final String FUNCTION_MESSAGE_LIST = "104001";   // 消息列表权限

	/**
	 * 客户状态
	 */
	public static final String CUSTOMER_NATURE_INTENTION = "3";//意向客户
	public static final String CUSTOMER_NATURE_POTENTIAL = "1";//潜在客户
	public static final String CUSTOMER_NATURE_CHAT = "6";//洽谈客户
	public static final String CUSTOMER_NATURE_SIGN = "5";//签约客户
	public static final String CUSTOMER_NATURE_RUNOFF = "4"; //流失客户
	public static final String CUSTOMER_NATURE_DELIVER = "7"; //发货客户

	/**
	 * 客户等级
	 */
	public static final String CUSTOMER_LEVEL_GAM = "1";

	/**
	 * 信息共享上传表名
	 */
	public static final String TABLE_NAME = "t_upload_account";
	
	/**
	 * 洽谈记录导出表名
	 */
	public static final String CHAT_EXPORT_TABLENAME = "洽谈记录";

	/**
	 * 客户信息共享导出
	 */
	public static final String OPERATION_TYPE = "客户信息共享导出";
	
	/**
	 * 洽谈记录导出
	 */
	public static final String OPERATION_TYPE_CHAT = "洽谈记录导出";

	/**
	 * 合同状态
	 */
	public static final String CONTRACT_STATUS_UPLOADED = "1";
	public static final String CONTRACT_STATUS_REVIEW = "2";
	public static final String CONTRACT_STATUS_FILE = "3";
	public static final String CONTRACT_STATUS_DELETE = "4";
	public static final String CONTRACT_STATUS_OUTOFTIME = "5";

	/**
	 * 状态描述(1-可用)
	 */
	public static final String STATUE_CODE_YES = "1";
	/**
	 * 状态描述(200000-黑名单)
	 */
	public static final String STATUE_CODE_NO = "200000";

	/**
	 * 陪同人员数据字典标识
	 */
	public static final String ENTOURAGE = "ENTOURAGE";

	/**
	 * 登录操作
	 */
	public static final String OPERATION_TYPE_LOGIN = "登录操作";
	
	/**
	 * APP登录操作
	 */
	public static final String OPERATION_TYPE_APP_LOGIN = "APP登录操作";

	/**
	 * 分发状态
	 */
	public static final String DISPENSE_STATU_NO = "1";
	public static final String DISPENSE_STATU_YES = "2";
	public static final String DISPENSE_STATU_BACK = "3";
	
	/**
	 * 洽谈方式
	 * 1.电话
	 * 2.上门
	 */
	public static final String CHATSTYPE_PHONE = "1";
	public static final String CHATSTYPE_VISIT = "2";
	
	/**
	 * 消息类型
	 */
	public static final String MESSAGE_TYPE_BFZBL = "1"; //拜访指标类
	public static final String MESSAGE_TYPE_BFZQL = "2"; //拜访周期类
	public static final String MESSAGE_TYPE_XTBFL = "3"; //协同拜访类
	public static final String MESSAGE_TYPE_XSHTL = "4"; //销售合同类
	public static final String MESSAGE_TYPE_KHFHL = "5"; //客户发货类
	public static final String MESSAGE_TYPE_HWYCL = "6"; //货物异常类
	public static final String MESSAGE_TYPE_HWQSL = "7"; //货物签收类
	public static final String MESSAGE_TYPE_KHTSL = "8"; //客户投诉类
	public static final String MESSAGE_TYPE_KHLPL = "9"; //客户理赔类
	public static final String MESSAGE_TYPE_KHJCL = "10"; //客户减产类
	public static final String MESSAGE_TYPE_KHLSL = "11"; //客户流失类
	public static final String MESSAGE_TYPE_ZYKHTXL = "12"; //资源客户提醒类
	public static final String MESSAGE_TYPE_ZSBFL = "13"; //赴约提醒类
	public static final String MESSAGE_TYPE_CRMYXHDTX = "14"; //CRM营销活动提醒类
	public static final String MESSAGE_TYPE_SALEGZTX = "15";  //销售经理工作提醒类
	/**
	 * 消息大类型
	 */
	public static final String MESSAGE_BUSTYPE_ZXL = "1"; //执行类
	public static final String MESSAGE_BUSTYPE_ZXL2 = "2"; //知晓类
	public static final String MESSAGE_BUSTYPE_XXL = "3"; //学习类
	public static final String MESSAGE_BUSTYPE_YXL = "4"; //营销类
	/**
	 * 默认消息主题
	 */
	public static final String MESSAGE_DEFAULT_TITLE = "CRM系统消息";
	/**
	 * 广播消息接收人
	 */
	public static final String MESSAGE_RECEIVE_ALL_ANDROID = "ALL_ANDROID";
	public static final String MESSAGE_RECEIVE_ALL_IOS = "ALL_IOS";
	/**
	 * 消息推送方式  1：单播  2：ANDROID广播 3：IOS广播
	 */
	public static final String MESSAGE_PUSH_TYPE_SINGLE = "1";
	public static final String MESSAGE_PUSH_TYPE_ANDROID_All = "2";
	public static final String MESSAGE_PUSH_TYPE_IOS_All = "3";

	/**
	 * 历程和回顾
	 */
	/**
	 * 签到
	 */
	public static final String OPER_SIGNIN  = "1";     //实地签到
	/**
	 * 客户操作
	 * 2.创建潜在客户
	 * 3.创建意向客户
	 * 9.修改客户信息
	 * 13.删除客户信息
	 */
	public static final String OPER_CREATE_CUSTOMER_HIDDEN = "2"; // 创建潜在客户
	public static final String OPER_CREATE_CUSTOMER_INTENT = "3"; // 创建意向客户
	public static final String OPER_UPDATE_CUSTOMER = "9";        // 修改客户信息
	public static final String OPER_DELETE_CUSTOMER = "13";       // 删除客户信息
	/**
	 *联系人操作
	 * 4.创建联系人信息
	 * 10.修改联系人信息
	 * 14.删除联系人信息
	 */
	public static final String OPER_CREATE_CONTACT = "4";  // 创建联系人信息
	public static final String OPER_UPDATE_CONTACT = "10"; // 修改联系人信息
	public static final String OPER_DELETE_CONTACT = "14"; // 删除联系人信息
	/**
	 *预约操作
	 * 5.创建预约信息
	 * 11.修改预约信息
	 * 15.预约信息作废
	 */
	public static final String OPER_CREATE_RESERVE = "5"; // 创建预约信息
	public static final String OPER_UPDATE_RESERVE = "11"; // 修改预约信息
	public static final String OPER_DELETE_RESERVE = "15"; // 预约信息作废
	
	/**
	 *洽谈操作
	 * 6.创建电话洽谈信息
	 * 7.创建上门洽谈信息
	 * 12.修改洽谈信息
	 * 16.删除洽谈信息
	 */
	public static final String OPER_CREATE_CHATS_PHONE = "6"; // 创建电话洽谈信息
	public static final String OPER_CREATE_CHATS_VISIT = "7"; // 创建上门洽谈信息
	public static final String OPER_UPDATE_CHATS = "12"; // 修改洽谈信息
	public static final String OPER_DELETE_CHATS = "16"; // 删除洽谈信息
	public static final String OPER_SWEEPSIGN = "17";  //门店走访,扫描签到
	
	/**
	 * 系统消息状态   0，未发送  1，已发送  2，无需推送 3，用户未注册APP 4，没有匹配客户的条件  5，没有匹配到负责人  6，发送失败  7，有成功也有失败
	 */
	public static final String MESSAGE_STATUS_NO_SEND = "0";
	public static final String MESSAGE_STATUS_IS_SEND = "1";
	public static final String MESSAGE_STATUS_WITHOUT_SEND = "2";
	public static final String MESSAGE_STATUS_NO_REGISTER = "3";
	public static final String MESSAGE_STATUS_NO_CONDITION = "4";
	public static final String MESSAGE_STATUS_NO_CHIEF = "5";
	public static final String MESSAGE_STATUS_SEND_FAILURE = "6";
	public static final String MESSAGE_STATUS_SEND_SUCCESSANDFAILURE = "7";
	
	/**
	 * 运单数据类型  1、运单 2、修改金额  3、签收 4、异常 5、投诉 6、理赔 
	 */
	public static final String WAY_BILL = "1";
	public static final String WAY_BILL_MONEY = "2";
	public static final String WAY_BILL_SIGN = "3";
	public static final String WAY_BILL_ABNORMAL = "4";
	public static final String WAY_BILL_COMPLAINT = "5";
	public static final String WAY_BILL_COMPENSATION = "6";
	public static final String WAY_BILL_DELETE = "7";
	
	/**
	 * APP报表数据类型
	 */
	public static final String REPORTDATA_STORES = "105001001";     //门店
	public static final String REPORTDATA_SALES = "105001002";      //销售
	public static final String REPORTDATA_BUSINESS = "105001003";   //事业部\大区\路区报表
	public static final String REPORTDATA_GROUP = "105001004";   //集团报表
	
	/**
	 * APP报表数据子类型类型
	 */
	public static final String MOUDLE_ONE = "1";     //模块1
	public static final String MOUDLE_TWO = "2";      //模块2 
	
	/**
	 *周报指标
	 *层级	拜访频率          角色编码(对照数据表字段)
     *无到货门店	1周4次    100601  
	 *有到货门店	1周2次    100602   
	 *路区经理	1周5次    1010
	 *大区总经理	1周2次    1005
	 *事业部总经理   1周1次    1004
	 *总裁/副总裁     1周1次    1001
	 */
	public static final long REPORT_NOT_ARRIVAL = 4;   
	public static final long REPORT_ARRIVAL = 2;		
	public static final long REPORT_ROAD = 5;
	public static final long REPORT_REGION = 2;
	public static final long REPORT_BUSINESS = 1;
	public static final long REPORT_CEO = 1;
	
	/**
	 *查看报表的门店类型
	 *0:无到货门店
	 *1:有到货门店
	 */
	public static final String NOT_ARRIVAL_STORE = "0";
	public static final String ARRIVAL_STORE = "1";
	
	/**
	 * 消息阅读状态
	 */
	public static final String MESSAGE_NO_READ = "0";
	public static final String MESSAGE_IS_READ = "1";
	
	/**
	 *意向操作
	 * 1.转为意向
	 */
	public static final String OPER_TURN_INTENT = "8"; // 转为意向信息
	
	
	/**
	 * 合同系统修改人
	 */
	public static final String CONTRACT_OA = "OA-admin";
	
	/**
	 * 系统管理员
	 */
	public static final String SYSADMIN = "sysadmin";
	
	
	/**
	 * 子部门查询条件
	 */
	public static final String SUBQUERYPARAM = "事业部";
	
	/**
	 * 集团名称
	 */
	public static final String GROUP = "天地华宇集团";
	
	/**
	 * 事业部角色
	 */
	public static final String ROLE_BUSINESS = "1004";
	/**
	 * 大区角色
	 */
	public static final String ROLE_REGIONS = "1005";
	
	/**
	 * 路区角色
	 */
	public static final String ROLE_ROAD = "1010";

	/**
	 * 运输类型转换
	 */
	public static String convertTransportType(String typeCode){
		if(StringUtil.isEmpty(typeCode)){
			return "";
		}else if(typeCode.equals("10000000000000000001")){
			return "定日达";
		} else if(typeCode.equals("20000000000000000001")){
			return "整车";
		} else if(typeCode.equals("30000000000000000001")){
			return "普通零担";
		} else if(typeCode.equals("40000000000000000001")){
			return "经济快运";
		} else if(typeCode.equals("50000000000000000001")){
			return "偏线";
		}
		return "";
	}
	/**
	 * 集团部门编码
	 */
	public static String GROUPCODE = "DP010000";
	
	/**
	 * 岗位编码转换
	 */
	public static String convertJobCode(String typeName){
		if(StringUtil.isEmpty(typeName)){
			return "";
		}else if(typeName.equals("客户经理")){
			return "1007";
		} else if(typeName.equals("门店经理")){
			return "1006";
		} else if(typeName.equals("路区经理")){
			return "1010";
		}
		return "";
	}
	/**
	 * 结束时间加1天
	 * @param endDate
	 * @return
	 * @author 丁勇
	 * @date 2015年8月14日
	 * @update 
	 */
	public static Date getEndDate(Date endDate){
		Calendar c = Calendar.getInstance();  
		c.setTime(endDate);  
		c.set(Calendar.DATE, c.get(Calendar.DATE) + 1); 
		return c.getTime();
	}
	/**
	 * 客户所属组织 0-门店,1-客户经理
	 */
	public static String CUSTOMEROFORG_STOER = "0";
	public static String CUSTOMEROFORG_SALE = "1";
	
	/**
	 * 随机洽谈记录全国数据
	 */
	public static String ALLDATA_RANDOM_CHAT = "103007001";
	
	/**
	 * 判断是否包含中文
	 */
	private static String regEx = "[\u4e00-\u9fa5]";
	private static Pattern pat = Pattern.compile(regEx);
	public static boolean isContainsChinese(String str) {
		Matcher matcher = pat.matcher(str);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;
	}
	/**
	 * 判断是否全是数字
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 判断是否全是字母
	 */
	public static boolean isAllString(String str) {
		// 判断字符串是否全为英文字母，是则返回true
		boolean isWord = str.matches("[a-zA-Z]+");
		return isWord;
	}
	
	/**
	 * 折扣率默认值
	 */
	public static double DISCOUNT_RATE = 1.0;
	
	/**
	 * 是否门店
	 */
	public static String IS_STORE  = "1";
	
	/**
	 * 自定义报表组织类型 : 1-事业部,2-大区,3-路区,4-销售团队,5-门店
	 */
	public static String REPORT_ANALYSIS_ORGTYPE_BUSINESS = "1";
	public static String REPORT_ANALYSIS_ORGTYPE_REGIONS = "2";
	public static String REPORT_ANALYSIS_ORGTYPE_ROAD = "3";
	public static String REPORT_ANALYSIS_ORGTYPE_SALETERM = "5";
	public static String REPORT_ANALYSIS_ORGTYPE_STORE = "4";
	public static String REPORT_ANALYSIS_ORGTYPE_SALE = "6";
	
	/**
	 * 自定义报表数据类型  ： 1-1-新增客户(降序),1-2-新增客户(升序),2-1-新增洽谈(降序),2-2-新增洽谈(升序)
	 * 	,3-1-新增签约(降序),3-2-新增签约(升序),4-1-发货产值(降序),4-2-发货产值(升序),5-1-门店走访(降序),5-2-门店走访(升序)	
	 */
	public static String REPORT_ANALYSIS_DATATYPE_CUSTOMER_DESC = "1-1";
	public static String REPORT_ANALYSIS_DATATYPE_CUSTOMER_ASC = "1-2";
	public static String REPORT_ANALYSIS_DATATYPE_CHAT_DESC = "2-1";
	public static String REPORT_ANALYSIS_DATATYPE_CHAT_ASC = "2-2";
	public static String REPORT_ANALYSIS_DATATYPE_SIGN_DESC = "3-1";
	public static String REPORT_ANALYSIS_DATATYPE_SIGN_ASC = "3-2";
	public static String REPORT_ANALYSIS_DATATYPE_PRODUCT_DESC = "4-1";
	public static String REPORT_ANALYSIS_DATATYPE_PRODUCT_ASC = "4-2";
	public static String REPORT_ANALYSIS_DATATYPE_VISIT_DESC = "5-1";
	public static String REPORT_ANALYSIS_DATATYPE_VISIT_ASC = "5-2";
	
	/**
	 * 自定义报表时间类型  : 1-本周,2-本月,3-上月,4-全部
	 */
	public static String REPORT_ANALYSIS_TIMETYPE_CURWEEK = "1";
	public static String REPORT_ANALYSIS_TIMETYPE_LASTMONTH = "2";
	public static String REPORT_ANALYSIS_TIMETYPE_CURMONTH = "3";
	public static String REPORT_ANALYSIS_TIMETYPE_ALL = "4";
	
	/**
	 * 获取排序关键字
	 * 
	 * @param sortType
	 * @return
	 * @author: 何斌
	 * @date: 2015年9月8日
	 * @update 
	 */
	public static String getSortKey(String sortType){
		String sortKey = "";
		if("2".endsWith(sortType)){
			sortKey = "ASC";
		}else if("1".endsWith(sortType)){
			sortKey = "DESC";
		}
		return sortKey;
	}
	
	/**
	 * 拜访签到权限id
	 */
	public static String SIGN_ALLDATA = "103005001";
	public static String SIGN_NEWSTORE = "103005002";
	public static String SIGN_TACTICCUSTOMER = "103005003";
	public static String SIGN_CUSTOMERMANAGE = "103005004";
	public static String SIGN_BUSINESSREGIONROAD = "103005005";
	public static String SIGN_STOREORSALE = "103005006";
	public static String SIGN_TERMMANAGE = "103005007";
	
	/**
	 * 扫描签到签到权限id
	 */
	public static String SWEEP_SIGN_ALLDATA = "103006001";
	
	/**
	 * 自定义报表全国数据
	 */
	public static String REPORT_ANALYSIS_ALLDATA = "105001005";
	
	/**
	 * 团队经理角色编码
	 */
	public static String TERMMANAGE_CODE = "2006";
	
	/**
	 * 自定义报表百分比取值
	 */
	public static String REPORT_ANALYSIS_PERCENT_1 = "1";
	public static String REPORT_ANALYSIS_PERCENT_2 = "2";
	public static String REPORT_ANALYSIS_PERCENT_3 = "3";
	
	/**
	 * 自定义报表百分比存储过程名称
	 */
	public static String REPORT_ANALYSIS_PERCENT_EXEC = "proc_exec_percent";
	public static String REPORT_ANALYSIS_PERCENT_EXEC_SALE = "proc_exec_percent_sale";
	
	/**
	 * 自定义报表返回控制(1-是,0-否)
	 */
	public static String REPORT_ANALYSIS_ISRETURN = "1";
	
	public static String REPORT_ANALYSIS_NAME = "统计分析";
	
	/**
	 * 每日会议全国数据
	 * */
	public static String ALLDATA_MEETING = "104003001";
	/**
	 * 每日问题全国数据
	 * */
	public static String ALLDATA_PROBLEM = "104004001";
	/**
	 * 每日培训全国数据
	 * */
	public static String ALLDATA_TRAIN = "104005001";
	/**
	 * 资源需求全国数据
	 * */
	public static String ALLDATA_RESDEMAND = "104002002";
	
	/**
	 * 陪同人员显示名称
	 */
	public static String ACCOMPANY_PEOP_TIER = "门店经理";  // 门店
	public static String ACCOMPANY_PEOP_ROAD = "路区经理";  // 路区
	public static String ACCOMPANY_PEOP_REGIONS= "大区总经理";  // 大区
	public static String ACCOMPANY_PEOP_BUSSINESS = "事业部总经理";  // 事业部
	public static String ACCOMPANY_PEOP_OD = "营运副总";  // 营运副总
	public static String ACCOMPANY_PEOP_CEO = "集团总裁";  // 总裁
	public static String ACCOMPANY_PEOP_TEAMMAN = "团队经理";  // 团队经理
	public static String ACCOMPANY_PEOP_SALEMAN = "客户经理";  // 客户经理
	
	/**
	 * 客户资源池最大认领数
	 */
	public static String CUSTOMERRESOURCEPOOL_MAX_CLAIM_NUM_VALUENAME = "客户资源池最大认领数";
	
	/**
	 * 客户资源池全国数据
	 */
	public static String CUSTOMERRESOURCEPOOL_ALLDATA = "101002001";
	
	/**
	 * APP类型 
	 */
	public static String APP_TYPE_IOS = "IOS";
	public static String APP_TYPE_ANDROID = "ANDROID";
	
	/**
	 * APP老版本临界值
	 */
	public static double APP_VERSION_IOS_OLD = 2.3;
	public static int APP_VERSION_ANDROID_OLD = 9;
	
	/**
	 * 客户经理和团队经理全部数据
	 */
	public static String ALLDATA_SALEEMP = "101001015";
	
	/**
	 * 转换客户类型(1--客户列表,2--共享客户列表)
	 */
	public static String TRANSFERCUSTOMER_TYPE_CUSTOMER = "1";
	public static String TRANSFERCUSTOMER_TYPE_CUSTOMERINFOPOOL = "2";
	
	/**
	 * 导出允许最大数
	 */
	public static String EXPORT_MAX_NUM = "EXPORT_MAX_NUM";
	
	/**
	 * 短信格式信息
	 */
	public static String SMS_CONTENT_START = "您正在一台新设备上登录行销，本次的登录验证码为";
	public static String SMS_CONTENT_END = "。【行销】";
	
	/**
	 * 官网客户数据同步类型定义(1-个人客户,2-联系人)
	 */
	public static String DATASYNC_TYPE_OBH_CUSTOMER = "1";
	public static String DATASYNC_TYPE_OBH_SHIPPER = "2";
	
	/**
	 * 个人客户渠道来源
	 */
	public static String PERSONALCUSTOMER_SOURCE_OBH = "网厅";
	
	/**
	 * 网厅接口返回状态
	 */
	public static String OBH_WEBSERVICE_STATUS_CELLPHONE = "1101";
	public static String OBH_WEBSERVICE_STATUS_EMAIL = "1201";

    /**
     * 手机号已存在,绑定成功提示
     */
    public static String OBH_SUCCESS = "obh.success.info";
}
