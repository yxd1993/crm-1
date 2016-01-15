#CRM系统对外接口说明
-------
##目录

[TOC]

##CRM系统对外接口介绍
CRM系统对外接口是天地华宇CRM系统对周边系统开放并提供相应服务的系统接口，周边系统获得接入许可后，可以通过阅读本接口文档来帮助开发。

##接口模块列表
>* 用户模块
>* 基础模块
>* 客户与联系人模块
>* 销售模块
>* 历史跟进和工作回顾模块
>* 消息模块
>* 网点、价格时效、运单查询模块
>* 周边系统调用接口模块
>* 统计分析模块
>* 自定义报表模块
>* 团队管理
##接口调用地址

>* 测试服务器地址: http://10.39.251.223:8180/crmservice
>* 正式服务器地址: 暂无

<pre>
请求url示例:
http://10.39.116.62:8180/crmservice/rs/bse/queryAllDataDictionary
</pre>

##接口调用请求说明
<pre>
1.接口发布方式：Restful
2.接口请求方式：GET,POST,PUT,DELETE
3.数据传输方式：json
4.Content-Type: application/json;charset=UTF-8
</pre>

##注意事项
1、调用相关接口时，请统一在消息头中带上当前用户的登录名，这样可以避免在调用每个接口时都需要单独处理传递用户登录名参数，参数名：loginName。
2、接口调用返回值采用统一格式，如下：
<pre>{
    "result": {...},
    "errcode": "1000",
    "errmsg": null
}
</pre>
result：返回给调用方的详细业务信息
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空


## 一、用户模块
### 相关实体说明
####用户信息实体：UserEntity
	/**
	 *  用户登录名
	 */
	private String userName;
	/**
	 *  用户登录密码
	 */
	private String password;
	/**
	 *  用户最后登录时间
	 */
	private Date lastLogin;
	/**
	 *  用户状态
	 */
	private String active;
	/**
	 *  用户启用时间
	 */
	private Date beginTime;
	/**
	 *  用户禁用时间
	 */
	private Date endTime;
	/**
	 *  职员对象
	 */
	private EmployeeEntity empEntity;
	/**
	 *  用户所拥有的功能信息ID集合
	 */
	private Set<String> functionCodes;
	/**
	 *  存入用户uri地址信息
	 */
	private Set<String> functionUris;
	/**
	 *  用户所拥有的角色信息ID集合
	 */
	private Set<String> roleids;

####验证码信息实体：ValidateCodeEntity
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 验证码
	 */
	private String validateCode;	
	/**
	 * 操作类型  1为重新发送
	 */
	private String operType;

####用户信息VO：UserUseMobileVo
	/**
	 *  验证码信息
	 */
	private ValidateCodeEntity validateCodeEntity;	
	/**
	 * 用户登录信息
	 */
	private UserEntity userEntity;


####百度推送账号信息实体：PushUserEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 用户ID
	 */
	private String userid;
	/**
	 * 百度推送应用端用户ID
	 */
	private String baidu_userid;
	/**
	 * 百度推送通道ID
	 */
	private String channelid;
	/**
	 * 应用ID
	 */
	private String appid;
	/**
	 * API Key
	 */
	private String apikey;
	/**
	 * Secret Key
	 */
	private String secretkey;
	/**
	 * 创建时间
	 */
	private Date createdate;
	/**
	 * 是否已注销
	 */
	private int cancel;

	/**
	 * android 自定义样式标识
	 */
	private int notification_builder_id;
	/**
	 * android 自定义铃声样式 //5：响铃 3:振动 1:无声音无振动 7:响铃加振动
	 */
	private int notification_basic_style;
							
	/**
	 * ios 音频文件名
	 */
	private String sound;
	/**
	 * ios 音频标识
	 */
	private int badge;
	/**
	 * 消息接收控制状态 1:全天接收 2:时间段接收 3:不接收
	 */
	private int sendstate;
	/**
	 * 接收时间段开始值
	 */
	private int beginhour;
	/**
	 * 接收时间段结束值
	 */
	private int endhour;
	
####百度推送用户VO：PushUserVo
	/**
	 * 百度推送用户信息
	 */
	private PushUserEntity pushUserEntity;

####版本信息实体：AppVersionEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 版本号
	 */
	private int versionCode;

	/**
	 * APK名字
	 */
	private String apkName;

	/**
	 * 是否强制更新
	 */
	private String isMust;

	/**
	 * 安装包下载地址
	 */
	private String url;
	
	/**
	 * 头像下载地址
	 */
	private String userHeadUrl;

	/**
	 * 描述
	 */
	private String description;

####APP版本信息VO：AppVersionVo
	/**
	 * APP版本信息
	 */
	private AppVersionEntity  appVersionEntity;

####反馈信息实体：FeedBackEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 反馈标题
	 */
	private String fbTitle;

	/**
	 * 反馈类型
	 */
	private String fbType;

	/**
	 * 反馈内容
	 */
	private String fbContent;

####反馈信息VO：FeedBackVo
	/**
	 * 反馈信息
	 */
	private FeedBackEntity feedBackEntity;

####手机信息实体：MobileInfoEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * IMEI
	 */
	private String imei;
	/**
	 * IMSI
	 */
	private String imsi;
	/**
	 * 手机型号
	 */
	private String mtype;
	/**
	 * 手机品牌
	 */
	private String mtyb;
	/**
	 * SDK版本
	 */
	private String androidsdk;
	/**
	 * 系统版本
	 */
	private String androidv;

####手机信息VO：MobileInfoAppVo
	/**
	 * 手机信息
	 */
	private MobileInfoEntity mobileInfoEntity;

### 1、用户登录
##### 请求路径：/rs/user/login
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| userName     | 是 |   用户名     |
| password        |   是   |   密码   |

	例：{"userName":"277610", "password":"1"}

#####接口返回信息：
用户信息实体：UserEntity

### 2、用户登录（需输验证码）
##### 请求路径：/rs/user/verificationCodeLogin
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| userEntity.userName     | 是 |   用户名     |
| userEntity.password        |   是   |   密码   |

	例：{"userEntity":{"userName":"277610", "password":"1"}}

1、返回值无异常
   处理：进入系统

2、返回值是业务异常：1
   异常信息：此用户手机号为空
   如：

	{
		"result": null,
		"errorCode": "1",
		"errorMessage": "此用户手机号为空"
	}

   处理：不进入验证码输入界面，直接提示给用户

3、返回值是业务异常：1
   异常信息：当前用户在此手机上登录需要输入验证码
   如：

	{
	  "result": {
	    "validateCode": null,
	    "phone": "18017085289",
	    "isSend": null,
	    "operType": null,
	    "id": null,
	    "createDate": null,
	    "createUser": null,
	    "modifyDate": null,
	    "modifyUser": null
	  },
	  "errorCode": "1",
	  "errorMessage": "当前用户在此手机上登录需要输入验证码"
	}

   处理：进入输入验证码界面，手机号取result.phone，重新生成验证码按钮60秒后才生效，此时后台已发送验证码


4、用户点击重新获取验证码：
   APP传递参数：

	{
	    "userEntity": {
	        "userName": "277610",
	        "password": "1"
	    },
	    "validateCodeEntity": {
	        "operType": "1"
	    }
	}

   处理：此时后台会重新获取验证码，APP无需处理

5、用户输入验证码，点击提交
   APP传递参数：

	{
	    "userEntity": {
	        "userName": "277610",
	        "password": "1"
	    },
	    "validateCodeEntity": {
	        "validateCode": "6586"
	    }
	}

   返回值是业务异常：1
   异常信息：验证码不正确
   处理：提示用户重新输入验证码

6、用户输入验证码，点击提交
   后台无异常信息返回
   处理：进入系统


#####接口返回信息：
用户信息实体：UserEntity

### 3、用户登录百度推送账号处理
##### 请求路径：/rs/user/pushUserLogin
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| PushUserVo.pushUserEntity.baidu_userid     | 是 |   百度推送应用端用户ID     |
| PushUserVo.pushUserEntity.channelid     | 是 |   百度推送通道ID     |
| PushUserVo.pushUserEntity.appid     | 是 |   应用系统类型：ANDROID、IOS   |

	例：{"pushUserEntity":{"baidu_userid":"123","channelid":"456","appid":"ANDROID"}}

#####接口返回信息：
响应基础实体，无业务信息

### 4、注销百度推送用户信息绑定
##### 请求路径：/rs/user/pushUserLogout
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| PushUserVo.pushUserEntity.baidu_userid     | 是 |   百度推送应用端用户ID     |
| PushUserVo.pushUserEntity.channelid     | 是 |   百度推送通道ID     |
| PushUserVo.pushUserEntity.appid     | 是 |   应用系统类型：ANDROID、IOS   |

	例：{"pushUserEntity":{"baidu_userid":"123","channelid":"456","appid":"ANDROID"}}

#####接口返回信息：
响应基础实体，无业务信息

### 5、根据用户id和百度用户id 查询绑定消息
##### 请求路径：/rs/user/findPushState
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| PushUserVo.pushUserEntity.baidu_userid     | 是 |   百度推送应用端用户ID     |
| PushUserVo.pushUserEntity.channelid     | 是 |   百度推送通道ID     |
| PushUserVo.pushUserEntity.appid     | 是 |   应用系统类型：ANDROID、IOS   |

	例：{"pushUserEntity":{"baidu_userid":"123","channelid":"456","appid":"ANDROID"}}

#####接口返回信息：
PushUserVo.pushUserEntity：推送账号信息

### 6、修改消息声音状态
说明： 5：响铃 3:振动 1:无声音无振动 7:响铃加振动
##### 请求路径：/rs/user/modifyPushSound
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| PushUserVo.pushUserEntity.baidu_userid     | 是 |   百度推送应用端用户ID     |
| PushUserVo.pushUserEntity.channelid     | 是 |   百度推送通道ID     |
| PushUserVo.pushUserEntity.appid     | 是 |   应用系统类型：ANDROID、IOS   |
| PushUserVo.pushUserEntity.notification_basic_style     | 是 |   声音状态   |

	例：{"pushUserEntity":{"baidu_userid":"123","channelid":"456","notification_basic_style":"3","appid":"ANDROID"}}

#####接口返回信息：
响应基础实体，无业务信息

### 7、修改接收消息状态
说明：消息接收控制状态 1:全天接收 2:时间段接收 3:不接收。
如果是2，按时间段接收，需要填写两个字段：beginhour，开始时间，endhour，结束时间
##### 请求路径：/rs/user/modifyPushSendState
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   是否必填   |  参数说明  |
| --------   | :-----:  | :----:  |
| PushUserVo.pushUserEntity.baidu_userid     | 是 |   百度推送应用端用户ID     |
| PushUserVo.pushUserEntity.channelid     | 是 |   百度推送通道ID     |
| PushUserVo.pushUserEntity.appid     | 是 |   应用系统类型：ANDROID、IOS   |
| PushUserVo.pushUserEntity.sendstate     | 是 |   接收消息状态   |
| PushUserVo.pushUserEntity.beginhour     | 是 |   开始时间   |
| PushUserVo.pushUserEntity.endhour     | 是 |   结束时间   |

	例：{"pushUserEntity":{"baidu_userid":"123","channelid":"456","sendstate":2,"beginhour":"5","endhour":"10","appid":"ANDROID"}}

#####接口返回信息：
响应基础实体，无业务信息

### 8、获取APP当前最新版本信息（IOS无需此方法）
说明：请求方式为GET
##### 请求路径：/rs/user/getVersionInfo
##### 接口请求方式：POST
##### 传入参数：
无
#####接口返回信息：
AppVersionVo.appVersionEntity：当前最新版本信息

### 9、用户反馈
##### 请求路径：/rs/user/feedbackInfo
##### 接口请求方式：POST
##### 传入参数：
AppVersionVo.feedBackEntity：反馈信息

	例：{"feedBackEntity":{"fbTitle":"123","fbType":"1","fbContent":"456"}}

#####接口返回信息：
响应基础实体，无业务信息

### 10、用户使用APP时记录手机信息
##### 请求路径：/rs/user/mobileInfo
##### 接口请求方式：POST
##### 传入参数：
MobileInfoAppVo.mobileInfoEntity：手机信息

	例：{"mobileInfoEntity":{"imei":"353925062892410","imsi":"460023268229654","mtype":"Xiaomi","mtyb":"MI 3","androidsdk":"17","androidv":"4.4.4"}}

#####接口返回信息：
响应基础实体，无业务信息

### 11、查询欢迎页图片信息
##### 请求路径：/rs/user/getWelcomeImg
##### 接口请求方式：GET
##### 传入参数：
无

#####接口返回信息：
imgUrl：图片路径
imgName：图片名称

## 二、基础模块
### 相关实体说明
####数据字典类型信息实体：DataDictionaryEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 词条代码
	 */
	private String termsCode;
	/**
	 * 词条名称
	 */
	private String termsName;
	/**
	 * 词条拼音
	 */
	private String termsPinyin;
	/**
	 * 备注
	 */
	private String notes;
	/**
	 * 是否启用
	 */
	private String active;
	/**
	 * 子词条的LIST
	 */
	private List<DataDictionaryValueEntity> dataDictionaryValueEntityList;

####数据字典子词条信息实体：DataDictionaryValueEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
    *上级词条
    */	
    private String termsCode;    
    /**
    *上级词条名称-数据字典值不记录，用于显示到前台界面
    */	
    private String termsName;
    /**
    *序号
    */	
    private String valueSeq;
    /**
    *值名称
    */	
    private String valueName;
    /**
    *值代码
    */	
    private String valueCode;
    /**
    *语言
    */	
    private String language;
    /**
    * 数据版本号
    */	
    private Long versionNo;    
    /**
    *是否启用
    */	
    private String active;    
    /**
     * 备注信息
     */
    private String noteInfo;

####数据字典信息VO：DataDictionaryAppVo
	/**
	 * 数据字典信息
	 */
	private List<DataDictionaryEntity> dataDictList;
	/**
	 * 数据字典版本号
	 */
	private long dataVersion;

####组织信息：DepartmentEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 部门编号
	 */
	private String deptCode;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 部门状态 0 正常，1 取消
	 */
	private String canceled;
	/**
	 * 上级部门编号
	 */
	private String supdeptCode;
	/**
	 * 上级部门名称
	 */
	private String supdeptName;
	/**
	 * 部门性质
	 */
	private String deptNature;
	/**
	 * 部门代码
	 */
	private String logistCode;
	/**
	 * 部门负责人
	 */
	private String managerId;
	/**
	 * 部门负责人姓名
	 */
	private String lastName;
	/**
	 * 所属分部编码
	 */
	private String subCompanyId;
	/**
	 * 所属分部名称
	 */
	private String subCompanyName;
	/**
	 * 父组织
	 */
	private DepartmentEntity parentDeptEntity;
	/**
	 * 子组织
	 */
	private List<DepartmentEntity> childDeptList;

####组织信息VO：DepartmentVo
	/**
	 * 当前门店编码
	 */
	private String tierCode;	
	/**
	 * 当前门店名称
	 */
	private String tierName;	
	/**
	 * 门店负责人显示名称
	 */
	private String tierDisplayName;	
	/**
	 * 门店负责人工号
	 */
	private String tierEmpCode;	
	/**
	 * 门店负责人姓名
	 */
	private String tierEmpName;	
	/**
	 * 当前组织的路区编码
	 */
	private String roadAreaCode;	
	/**
	 * 当前组织的路区名称
	 */
	private String roadAreaName;	
	/**
	 * 路区负责人显示名称
	 */
	private String roadDisplayName;	
	/**
	 *  路区负责人
	 */
	private String roadEmpCode;	
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;	
	/**
	 * 当前组织的大区编码
	 */
	private String regionsCode;	
	/**
	 * 当前组织的大区名称
	 */
	private String regionsName;	
	/**
	 * 大区负责人显示名称
	 */
	private String regionsDisplayName;	
	/**
	 *  大区负责人
	 */
	private String regionsEmpCode;	
	/**
	 *  大区负责人名称
	 */
	private String regionsEmpName;	
	/**
	 * 当前组织的事业部编码
	 */
	private String businessUnitCode;	
	/**
	 * 当前组织事业部名称
	 */
	private String businessUnitName;	
	/**
	 * 事业部负责人显示名称
	 */
	private String businessUnitDisplayName;	
	/**
	 *  事业部负责人
	 */
	private String businessUnitEmpCode;	
	/**
	 *  事业部负责人名称
	 */
	private String businessUnitEmpName;	
	/**
	 * 营运副总显示名称
	 */
	private String odDisplayName;	
	/**
	 * 营运副总工号
	 */
	private String odEmpCode;	
	/**
	 * 营运副总名称
	 */
	private String odEmpName;	
	/**
	 * 总裁显示名称
	 */
	private String ceoDisplayName;	
	/**
	 * 总裁工号
	 */
	private String ceoEmpCode;	
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;	
	/**
	 * 团队经理显示名称
	 */
	private String teamManagerDisplayName;	
	/**
	 * 团队经理工号
	 */
	private String teamManagerCode;	
	/**
	 * 团队经理名称
	 */
	private String teamManagerName;	
	/**
	 * 客户经理显示名称
	 */
	private String saleManDisplayName;	
	/**
	 * 客户经理工号
	 */
	private String saleManEmpCode;	
	/**
	 * 客户经理名称
	 */
	private String saleManEmpName;	
	/**
	 * 部门公共选择器查询参数
	 */
	private String selectorParam;	
	/**
	 * 部门信息
	 */
	private DepartmentEntity deptEntity;

####人员信息：EmployeeEntity
	/**
	 * 工号
	 */
	private String empCode;
	/**
	 * 姓名
	 */
	private String empName;
	/**
	 * 性别
	 */
	private String gender;
	/**
	 * 拼音名
	 */
	private String pinYinName;
	/**
	 * 拼音简写名称
	 */
	private String pinYinShortName;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 部门名称
	 */
	private String deptname;
	/**
	 * 岗位名称
	 */
	private String jobname;
	/**
	 * 部门编码
	 */
	private String deptcode;
	/**
	 * 岗位编码
	 */
	private String jobcode;
	/**
	 * 直接上级
	 */
	private String managerCode;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 公司邮箱
	 */
	private String email;
	/**
	 * 座机
	 */
	private String telePhone;
	/**
	 * 人员状态 0 试用 1 正式 2 实习 5离职
	 */
	private String status;
	/**
	 * 部门实体
	 */
	private DepartmentEntity deptEntity;

####基础信息VO：BseAppVo
	/**
	 * 人员信息集合
	 */
	private List<EmployeeEntity> empList;	
	/**
	 * 组织信息实体
	 */
	private DepartmentEntity deptEntity;	
	/**
	 * 组织信息VO
	 */
	private DepartmentVo deptVo;	
	/**
	 * 组织信息集合
	 */
	private List<DepartmentEntity> deptEntityList;	
	/**
	 * 组织编码
	 */
	private String logistCode;	
	/**
	 * 当前客户负责人工号
	 */
	private String customerManageEmpCode;	
	/**
	 * 当前客户负责人姓名
	 */
	private String customerManageEmpName;	
	/**
	 * 客户信息公共选择器查询参数 CRM账号、企业名称、联系人
	 */
	private String selectorParam;
	/**
	 * 每页数据量
	 */
	private int limit;
	/**
	 * 分布起始位置
	 */
	private int start;
	/**
	 * 分页数据总长度
	 */
	private Long totalCount;
	
#### 扫描名片信息实体 appSweepCardsEntity
	/**
	 *主键编号
	 */
	private String id;
	/**
	 *公司名称
	 */
	private String enterpriseName;
	/**
	 *地址
	 */
	private String address;
	/**
	 *姓名
	 */
	private String empName;
	/**
	 *职位名称
	 */
	private String empDeptName;
	/**
	 *电话
	 */
	private String telephone;
	/**
	 *手机
	 */
	private String mobilephone;
	/**
	 *邮箱
	 */
	private String email;
	
	/**
	 *是否有效
	 */
	private String active;

### 1、获取数据字典最新版本
##### 请求路径：/rs/bse/queryDataDictionaryVersion
##### 接口请求方式：GET
##### 传入参数：
无
#####接口返回信息：
DataDictionaryAppVo.dataVersion：当前数据版本号，长整形

### 2、获取所有数据字典
##### 请求路径：/rs/bse/queryAllDataDictionary
##### 接口请求方式：GET
##### 传入参数：
无
#####接口返回信息：
DataDictionaryAppVo.dataDictList：数据字典信息集合
DataDictionaryAppVo.dataVersion：当前数据版本号，长整形

### 3、查询当前用户的二级公司
##### 请求路径：/rs/bse/queryDeptListByUser
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| BseAppVo.start     | 整形 | 是 |   分页起始位置     |
| BseAppVo.limit        | 整形 |   是   |   每页条数   |
| BseAppVo.selectorParam    | 字符 |   否   |   模糊搜索输入条件   |

	例：{"start":0,"limit":15,"selectorParam":"武汉"}

#####接口返回信息：
BseAppVo.deptEntityList：组织信息集合
BseAppVo.totalCount：分页所需的数据总长度，长整形

### 4、查询当前组织的路区、大区、事业部信息
##### 请求路径：/rs/bse/queryDeptSuperiorDeptByCurrUser
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| BseAppVo.logistCode     | 字符串 | 是 |   组织编码     |
| BseAppVo.customerManageEmpCode     | 字符串 | 是 |   当前客户负责人工号     |
| BseAppVo.customerManageEmpName     | 字符串 | 是 |   当前客户负责人姓名     |

	例：{"logistCode":"上海0","customerManageEmpCode":"00006045","customerManageEmpName":"孙晓春"}

#####接口返回信息：
BseAppVo.deptVo：组织信息VO（DepartmentVo）
tierDisplayName：门店负责人显示名称;	
tierEmpCode：门店负责人工号;	
tierEmpName：门店负责人姓名;	
roadDisplayName：路区负责人显示名称;	
roadEmpCode：路区负责人;	
roadEmpName：路区负责人名称;	
regionsDisplayName：大区负责人显示名称;	
regionsEmpCode：大区负责人;	
regionsEmpName：大区负责人名称;	
businessUnitDisplayName：事业部负责人显示名称;	
businessUnitEmpCode：事业部负责人;	
businessUnitEmpName：事业部负责人名称;	
odDisplayName：营运副总显示名称;	
odEmpCode：营运副总工号;	
odEmpName：营运副总名称;	
ceoDisplayName：总裁显示名称;	
ceoEmpCode：总裁工号;	
ceoEmpName：总裁名称;	
teamManagerDisplayName：团队经理显示名称;	
teamManagerCode：团队经理工号;	
teamManagerName：团队经理名称;	
saleManDisplayName：客户经理显示名称;	
saleManEmpCode：客户经理工号;	
saleManEmpName：客户经理名称;	
### 5、查询人员信息
##### 请求路径：/rs/bse/queryEmpList
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| BseAppVo.start     | 整形 | 是 |   分页起始位置     |
| BseAppVo.limit        | 整形 |   是   |   每页条数   |
| BseAppVo.selectorParam    | 字符 |   否   |   模糊搜索输入条件   |

	例：{"start":0, "limit":15, "selectorParam":"277610"}

#####接口返回信息：
BseAppVo.empList：人员信息集合（EmployeeEntity）
BseAppVo.totalCount：分页所需的数据总长度，长整形
### 6、保存扫描名片的信息
##### 请求路径：/rs/bse/insertSweepCardsInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| appSweepCardsEntity     | Object | 是 |   实体对象     |

	例：{"enterpriseName":"123","empName":"123","empDeptName":"213","address":"12312313","telephone":"400123123","mobilephone":"12312312321"}

#####接口返回信息：
无
### 7、查询客户经理及团队经理列表
##### 请求路径：/rs/bse/querySaleEmpLists
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| BseAppVo.selectorParam     | 字符 | 否 |   模糊搜索输入条件     |

	例:{"selectorParam":"张"}

#####接口返回信息
	BseAppVo.empList：人员信息集合（EmployeeEntity）(包含工号和姓名)
响应基础实体，无业务信息

## 三、客户信息模块
### 相关实体说明
####客户信息：CustomerEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 客户性质
	 */
	private String accountType;
	/**
	 * CRM客户账号
	 */
	private String accountCode;
	/**
	 * 客户企业全称
	 */
	private String enterpriseName;
	/**
	 * 客户企业缩写
	 */
	private String enterpriseShortName;
	/**
	 * 企业发票抬头
	 */
	private String enterpriseBillHead;
	/**
	 * 所属总公司
	 */
	private String parentCompany;
	/**
	 * 所属总公司名称
	 */
	private String parentCompanyName;
	/**
	 * 上级单位
	 */
	private String parentUnit;
	/**
	 * 上级单位名称
	 */
	private String parentUnitName;
	/**
	 * 企业性质
	 */
	private String enterpriseType;
	/**
	 * 客户等级分类
	 */
	private String accountSub;
	/**
	 * 状态描述
	 */
	private String statusCode;
	/**
	 * 二级物流公司代码
	 */
	private String tierCode;
	/**
	 * 所属行业
	 */
	private String industryCode;
	/**
	 * 客户级别
	 */
	private String accountRatingCode;
	/**
	 * 第一单时间
	 */
	private Date startingTime;
	/**
	 * 详细地址
	 */
	private String detailedAddress;
	/**
	 * 详细地址邮编
	 */
	private String detailedAddressPostalCode;
	/**
	 * 取货详细地址
	 */
	private String deliveryAddress;
	/**
	 * 取货详细地址邮编
	 */
	private String deliveryAddressPostalCode;
	/**
	 * 当前折扣率
	 */
	private Double discountRate;
	/**
	 * 客户备注
	 */
	private String accountRemark;
	/**
	 * 主要货物名称
	 */
	private String mainGoodsName;
	/**
	 * 主要包装方式
	 */
	private String typeOfPackage;
	/**
	 * 迪辰账号
	 */
	private String dcAccount;
	/**
	 * 负责人
	 */
	private String managePerson;
	/**
	 * 负责人工号
	 */
	private String manageEmpCode;
	/**
	 * 事业部
	 */
	private String businessUnitCode;
	/**
	 * 事业部名称
	 */
	private String businessUnitName;
	/**
	 * 大区
	 */
	private String regionsCode;
	/**
	 * 大区名称
	 */
	private String regionsName;
	/**
	 * 所属路区
	 */
	private String roadAreaCode;	
	/**
	 * 路区名称
	 */
	private String roadAreaName;
	/**
	 * 客户信用评级
	 */
	private String accountCreditGrade;
	/**
	 * 期望营销活动类型
	 */
	private String marketActiveType;
	/**
	 * 营销活动详述
	 */
	private String marketActiveRemark;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 客户渠道
	 */
	private String accountChannel;
	/**
	 * 客户账期
	 */
	private String accountPeriod;
	/**
	 * 最后拜访时间
	 */
	private Date lastChatsDate;
	/**
	 * 合同开始时间
	 */
	private Date contractStartTime;
	/**
	 * 合同结束时间
	 */
	private Date contractEndTime;
	/**
	 * 资源客户ID
	 */
	private String customerInfoPoolId;
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
    /**
     * 近三个月平均产值
     */
    private Double productValueOfThreeMonthAvg;

####联系人信息实体：ContactEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * 客户UUID
	 */
	private String accountId;
	/**
	 * 联系人姓名
	 */
	private String contactName;
	/**
	 * 区号
	 */
	private String districtNumber;
	/**
	 * 座机
	 */
	private String telephone;
	/**
	 * 电子邮件
	 */
	private String eMailAddress;
	/**
	 * 手机
	 */
	private String cellphone;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 是否默认
	 */
	private String isDefault;
	/**
	 * 职位
	 */
	private String job;

####资源客户信息实体：CustomerInfoPoolEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 联系人
	 */
	private String contactPerson;
	/**
	 * 联系方式
	 */
	private String contactWay;
	/**
	 * 所属事业部
	 */
	private String business;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 省编码
	 */
	private String provinceCode;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 市编码
	 */
	private String cityCode;
	/**
	 * 区
	 */
	private String area;
	/**
	 * 区编码
	 */
	private String areaCode;
	/**
	 * 公司地址
	 */
	private String companyAddress;
	/**
	 * 负责人
	 */
	private String managePerson;
	/**
	 * 负责人工号
	 */
	private String manageEmpCode;
	/**
	 * 分发状态
	 */
	private String dispenseStatus;
	/**
	 * 退回原因
	 */
	private String backReason;
	
	/**
	 * 上传人
	 */
	private EmployeeEntity employeeEntity;
	/**
	 * 是否有效
	 */
	private String active;

####客户信息VO：CustomerAppVo
	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	/**
	 *是否有签到
	 */
	private String IsSign;
	/**
	 * 客户陪同人员信息
	 */
	private DepartmentVo departmentVo;	
	/**
	 * 客户信息集合
	 */
	private List<CustomerEntity> customerEntityList;	
	/**
	 * 资源客户信息集合
	 */
	private List<CustomerInfoPoolEntity> customerPooList;	
	/**
	 * 资源客户信息
	 */
	private CustomerInfoPoolEntity customerPooLEntity;
	/**
	 * 联系人信息集合
	 */
	private List<ContactEntity> contactEntityList;	
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;	
	/**
	 * 手机号信息
	 */
	private PhoneInfoEntity phoneInfoEntity;	
	/**
	 * 客户信息公共选择器查询参数  CRM账号、企业名称、联系人
	 */
	private String selectorParam;	
	/**
	 * 开始时间
	 */
	private Date startDate;	
	/**
	 * 结束时间
	 */
	private Date endDate;	
	/**
	 * 洽谈开始时间
	 */
	private Date chatsStartDate;	
	/**
	 * 洽谈结束时间
	 */
	private Date chatsEndDate;
	/**
	 * 客户性质
	 */
	private String accountType;	
	/**
	 * 合同状态
	 */
	private String contractStatus;	
	/**
     * 每页数据量
     */
	private int limit;    
    /**
     * 分布起始位置
     */
	private int start;    
    /**
     * 分页数据总长度
     */
	private Long totalCount;    
    /**
     * 客户信息ID集合
     */
	private List<String> ids;
	/**
	 * 转让客户
	 */
	private TransferCustomerVO transferCustomerVO;
	/**
	 * 排序字段
	 */
	private String sortType;
	
	/**
	 * 排序规则
	 */
	private String sortTypeSub;
	/**
	 * 共享客户状态
	 */
	private String customerInfoPoolStatus;

####手机号信息实体：PhoneInfoEntity
	/**
	 * 主键
	 */
	private String id;	
	/**
	 * 手机号
	 */
	private String Mobile;	
	/**
	 * 省
	 */
	private String Province;	
	/**
	 * 市
	 */
	private String City;	
	/**
	 * 区号
	 */
	private String AreaCode;	
	/**
	 * 邮编
	 */
	private String PostCode;	
	/**
	 * 服务商
	 */
	private String Operators;	
	/**
	 * 卡类型
	 */
	private String Card;
	/**
	 * 地址
	 */
	private String address;

####客户经纬度信息 CustomerLatlngAppVo
	/**
	 *维度
	 */
	private double lat;
	/**
	 *经度
	 */
	private double lng;
	/**
	 * 距离范围
	 * /
	private double distanceScope;
	/**
	 * 客户范围集合
	 */
	private List<CustomerLatlngEntity> customerLatlngEntities;

####转让客户VO TransferCustomerVO
	/**
	 * 客户id集合
	 */
	private List<String> ids;
	/**
	 * 新负责人工号
	 */
	private String newManagerCode;
	
	/**
	 * 客户类型(1-客户列表,2-共享客户) 
	 */
	private String customerType;

####各客户总数实体 CustomerTotalEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 工号
	 */
	private String userCode;
	/**
	 * 客户列表总数
	 */
	private long customerTotal;
	/**
	 * 客户池总数
	 */
	private long customerResourceTotal;
	/**
	 * 共享客户总数
	 */
	private long customerPoolTotal;

### 1、分页查询客户信息
##### 请求路径：/rs/customer/queryCustomerInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.start     | 整形 | 是 |   分页起始位置     |
| CustomerAppVo.limit        | 整形 |   是   |   每页条数   |
| CustomerAppVo.accountType    | 字符 |   否   |   客户性质   |
| CustomerAppVo.contractStatus   | 字符  |   否   |   合同状态   |
| CustomerAppVo.startDate    | 时间 |   否   |   开始时间  |
| CustomerAppVo.endDate    | 时间 |   否   |   结束时间   |
| CustomerAppVo.selectorParam    | 字符 |   否   |   模糊搜索输入条件   |

	例：{"start":0,"limit":15,"accountType":"1","contractStatus":"2","selectorParam":"1"}

#####接口返回信息：
CustomerAppVo.customerEntityList：客户实体信息集合
CustomerAppVo.totalCount：分页所需的数据总长度，长整形

### 2、新增客户信息
##### 请求路径：/rs/customer/addCustomer
##### 接口请求方式：POST
##### 传入参数：
CustomerAppVo.customerEntity：客户信息实体

	例：{"customerEntity":{"enterpriseName":"上海XXXXXX","tierCode":"上海0","detailedAddress":"上海市XXXX路","contactEntity":{"contactName":"张三","districtNumber":"021","telephone":"1234567"}}}

#####接口返回信息：
响应基础实体，无业务信息

### 3、修改客户信息
##### 请求路径：/rs/customer/editCustomer
##### 接口请求方式：POST
##### 传入参数：
CustomerAppVo.customerEntity：客户信息实体

	例：{"customerEntity":{"id":"30d7a1f2-2c91-4995-b9cc-3bc1e0509768","enterpriseName":"上海AAAA","tierCode":"上海A","detailedAddress":"上海市AAA路"}}

#####接口返回信息：
响应基础实体，无业务信息

### 4、根据ID查询客户信息
##### 请求路径：/rs/customer/queryCustomerInfoById
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.customerEntity.id     | 字符串 | 是 |   客户主键ID     |

	例：{"customerEntity":{"id":"06b96e32-eede-4f74-bbdf-57fd60f4ca97"}}

#####接口返回信息：
CustomerAppVo.customerEntity：客户信息实体

### 5、查询当前客户的总公司
##### 请求路径：/rs/customer/querySuperiorCustomerInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.start     | 整形 | 是 |   分页起始位置     |
| CustomerAppVo.limit        | 整形 |   是   |   每页条数   |
| CustomerAppVo.selectorParam    | 字符 |   否   |   模糊搜索输入条件   |

	例：{"start":0,"limit":15,"selectorParam":"12"}

#####接口返回信息：
CustomerAppVo.customerEntityList：客户实体信息集合
CustomerAppVo.totalCount：分页所需的数据总长度，长整形

### 6、根据客户ID集合删除客户信息
##### 请求路径：/rs/customer/deleteCustomerByIds
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.ids     | 数组 | 是 |   客户主键ID数组     |

	例：{"ids":["06b96e32-eede-4f74-bbdf-57fd60f4ca97"]}

#####接口返回信息：
响应基础实体，无业务信息

### 7、根据手机号查询手机信息
##### 请求路径：/rs/customer/queryPhoneInfoByPhone
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.phoneInfoEntity.mobile     | 字符串 | 是 |   手机号     |

	例：{"phoneInfoEntity":{"mobile":"13774202749"}}

#####接口返回信息：
CustomerAppVo.phoneInfoEntity：手机信息实体

### 8、根据客户ID查询联系人信息
##### 请求路径：/rs/customer/queryContactList
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.customerEntity.id     | 字符串 | 是 |   客户主键ID     |

	例：{"customerEntity":{"id":"8c86e1d0-c793-4484-bf6a-ab525e2e96bc"}}

#####接口返回信息：
CustomerAppVo.contactEntityList：联系人实体信息集合

### 9、新增联系人信息
##### 请求路径：/rs/customer/addContact
##### 接口请求方式：POST
##### 传入参数：
CustomerAppVo.contactEntity：联系人信息

	例：{"contactEntity":{"accountId":"230a3de0-a5da-4501-ad4b-eb04ea07bbd5","contactName":"张三","districtNumber":"021","telephone":"1234567","eMailAddress":"123@123.com","cellphone":"13888888888"}}

#####接口返回信息：
响应基础实体，无业务信息

### 10、修改联系人信息
##### 请求路径：/rs/customer/editContact
##### 接口请求方式：POST
##### 传入参数：
CustomerAppVo.contactEntity：联系人信息，联系人主键不能为空

	例：{"contactEntity":{"id":"7e71f08a-47e0-4ad3-9d53-a128039c2409","contactName":"李四","districtNumber":"0211","telephone":"12345678","eMailAddress":"123456@123.com","cellphone":"13666666666"}}

#####接口返回信息：
响应基础实体，无业务信息

### 11、根据联系人ID集合删除联系人信息
##### 请求路径：/rs/customer/deleteContact
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.ids     | 数组 | 是 |   联系人主键ID数组     |

	例：{"ids":["0a59bec4-60d6-4d03-bbf0-11c0fff5c7b9"]}

#####接口返回信息：
响应基础实体，无业务信息

### 12、根据联系人ID设置默认联系人
##### 请求路径：/rs/customer/updateContactIsDefault
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.contactEntity.id     | 字符串 | 是 |   联系人主键ID     |

	例：{"contactEntity":{"id":"0a59bec4-60d6-4d03-bbf0-11c0fff5c7b9"}}

#####接口返回信息：
响应基础实体，无业务信息

### 13、新增意向信息
##### 请求路径：/rs/customer/addIntention
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.customerEntity.id   | 字符串 | 是   |   客户主键  |
| CustomerAppVo.customerEntity.mainGoodsName   | 字符串 | 是   |   货物名称  |
| CustomerAppVo.customerEntity.typeOfPackage   | 字典(CUSTOMER_PACKAGING) | 是   |   主要包装方式  |
| CustomerAppVo.customerEntity.deliveryAddressPostalCode   | 字符串 | 是   |   取货邮编 |
| CustomerAppVo.customerEntity.deliveryAddress   | 字符串 | 是   |   上门取货地址 |
| CustomerAppVo.customerEntity.marketActiveType   | 字典(MARKETING_TYPE) | 是   |   期望营销活动 |

	例:{"customerEntity":{"id":"a6e63edb-861d-47fc-9299-93daca52f78e","mainGoodsName":"医疗用品","typeOfPackage":"1","marketActiveType":"2","deliveryAddressPostalCode":"210000","deliveryAddress":"上海市"}}
#####接口返回信息：
响应基础实体，无业务信息

### 14、附近客户信息
##### 请求路径：/rs/customer/nearCustomer
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerLatlngAppVo.lat   | 浮点型 | 是   |   纬度  |
| CustomerLatlngAppVo.lng   | 浮点型 | 是   |   经度  |
| CustomerLatlngAppVo.distanceScope   | 浮点型 | 是   |   距离范围  |

	例:{"lat":"31.15","lng":"121.1","distanceScope":"10"}

#####接口返回信息
result：CustomerLatlngAppVo.customerLatlngEntities 客户范围的集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 15、分页查询资源客户信息
##### 请求路径：/rs/customer/queryCustomerInfoPool
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.start     | 整形 | 是 |   分页起始位置     |
| CustomerAppVo.limit     | 整形 | 是 |   每页条数   |

	例:{"start":0,"limit":15}

#####接口返回信息
CustomerAppVo.customerPooList：资源客户信息集合

### 16、根据资源客户ID查询资源客户信息
##### 请求路径：/rs/customer/getCustomerInfoPoolById
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.customerPooLEntity.id     | 字符 | 是 |   资源客户ID     |

	例:{"customerPooLEntity":{"id":"03664fb9-b6fb-4e6c-b8ca-a4ba9b65dca2"}}

#####接口返回信息
CustomerAppVo.customerPooLEntity：资源客户信息

### 17、资源客户退回
##### 请求路径：/rs/customer/updateBackReason
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.customerPooLEntity.id     | 字符 | 是 |   资源客户ID     |
| CustomerAppVo.customerPooLEntity.backReason     | 字符 | 是 |   退回原因     |

	例:{"customerPooLEntity":{"id":"03664fb9-b6fb-4e6c-b8ca-a4ba9b65dca2","backReason":"退回原因123"}}

#####接口返回信息
响应基础实体，无业务信息

### 18、转让客户
##### 请求路径：/rs/customer/transferCustomer
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| CustomerAppVo.transferCustomerVO.ids     | 集合 | 是 |   客户ID集合    |
| CustomerAppVo.transferCustomerVO.newManagerCode     | 字符 | 是 |   新负责人工号     |
| CustomerAppVo.transferCustomerVO.customerType     | 字符 | 是 |   客户类型(1-客户列表,2-共享客户)      |

	例:{"transferCustomerVO":{"ids":["03664fb9-b6fb-4e6c-b8ca-a4ba9b65dca2"],"newManagerCode":"00273339","customerType":"1"}}

#####接口返回信息
响应基础实体，无业务信息

### 18、各类型客户总数
##### 请求路径：/rs/customer/queryCustomerTotal
##### 接口请求方式：GET
##### 接口返回信息
{"result":{"id":null,"customerTotal":679,"customerResourceTotal":647,"customerPoolTotal":2,"userCode":"273339"},"errorCode":"0","errorMessage":null}
响应基础实体，无业务信息

## 四、销售模块	
### 相关实体说明
####附件信息实体：AttachmentEntity
	/**
	 * 主键id
	 */
	private String id;
	/**
	 *附件所关联数据的id
	 */
	private String fileId;
	/**
	 * 附件描述
	 */
	private String descrip;
	/**
	 * 附件名称
	 */
	private String fileName;
	/**
	 * 附件上传地址
	 */
	private String fileUrl;
	/**
	 * 附件大小
	 */
	private int fileSize;
	/**
	 * 附件顺序
	 */
	private int reorder;
	/**
	 * 附件后缀
	 */
	private String postfix;
	/**
	 * 图片内容
	 */
	private byte[] imgContent;
####预约信息实体：SaleReserveEntity
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
	/**
	 * 预约开始时间
	 */
	private Date reserveStartTime;

	/**
	 * 预约结束时间
	 */
	private Date reserveEndTime;

	/**
	 * 提醒时间
	 */
	private String warningTime;
	/**
	 * 预约方式
	 */
	private String reserveType;
	/**
	 * 门店负责人
	 */
	private String comTierEmpCode;
	/**
	 *团队负责人
	 */
	private String teamManagerEmpCode;
	/**
	 * 路区负责人
	 */
	private String comRoadEmpCode;

	/**
	 * 大区负责人
	 */
	private String comRegionsEmpCode;

	/**
	 * 事业部负责人
	 */
	private String comBusinessEmpCode;
	/**
	 * 营运副总
	 */
	private String comOdEmpCode;
	/**
	 * 集团总裁
	 */
	private String comCeoEmpCode;
	/**
	 * 预约主题
	 */
	private String reserveTheme;
	/**
	 * 预约内容
	 */
	private String reserveContents;
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 删除备注信息
	 */
	private String delDesc;
	/**
	 *是否预约
	 */
	private String isAgreement;

####预约信息VO：ReserveAppVo	
	 /**
	 * 门店负责人姓名
	 */
	private String tierEmpName;
	/**
	 * 团队经理名称
	 */
	private String teamManagerEmpName;
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;
	/**
	 * 大区负责人名称
	 */
	private String regionsEmpName;
	/**
	 * 事业部负责人名称
	 */
	private String businessUnitEmpName;
	/**
	 * 营运副总名称
	 */
	private String odEmpName;
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;
	/**
	 * 预约开始时间
	 */
	private Date startDate;
	/**
	 * 预约结束时间
	 */
	private Date endDate;
	/**
     * 每页数据量
     */
	private int limit;
    /**
     * 分布起始位置
     */
	private int start;
    
    /**
     * 分页数据总长度
     */
	private Long totalCount;
	/**
	 * 预约id集合
	 */
	private List<String> ids;
	/**
	 * 预约信息集合
	 */
	private List<SaleReserveEntity> reserveDayList;

####洽谈信息实体：SaleChatsEntity
	/**
	 * 主键id
	 */
	private String id;
    /**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
	
	/**
	 * 关联预约信息
	 */
	private SaleReserveEntity reserveEntity;
	
	/**
	 *签到信息
	 */
	private SignEntity sign;

	
	/**
	 * 员工信息
	 */
	private EmployeeEntity employeeEntity;
	/**
	 * 洽谈开始时间
	 */
	private Date chatStartTime;
	/**
	 * 洽谈结束时间
	 */
	private Date chatEndTime;
	/**
	 * 洽谈方式
	 */
	private String chatType;
	/**
	 * 门店负责人
	 */
	private String comTierEmpCode;
	/**
	 *团队负责人
	 */
	private String teamManagerEmpCode;
	/**
	 * 路区负责人
	 */
	private String comRoadEmpCode;
	/**
	 * 大区负责人
	 */
	private String comRegionsEmpCode;
	/**
	 * 事业部负责人
	 */
	private String comBusinessEmpCode;
	/**
	 * 营运副总
	 */
	private String comOdEmpCode;
	/**
	 * 集团总裁
	 */
	private String comCeoEmpCode;
	/**
	 * 实际地址
	 */
	private String reserveAddress;
	/**
	 * 洽谈主题
	 */
	private String chatTheme;
	/**
	 * 洽谈内容
	 */
	private String chatContents;
	/**
	 * 删除原因
	 */
	private String delDesc;
	/**
	 * 是否有效
	 */
	private String active;

####洽谈信息Vo：ChatsAppVo
	/**
	 * 门店负责人姓名
	 */
	private String tierEmpName;
	/**
	 * 团队经理名称
	 */
	private String teamManagerEmpName;
	/**
	 * 路区负责人名称
	 */
	private String roadEmpName;

	/**
	 * 大区负责人名称
	 */
	private String regionsEmpName;
	/**
	 * 事业部负责人名称
	 */
	private String businessUnitEmpName;
	/**
	 * 营运副总名称
	 */
	private String odEmpName;
	/**
	 * 总裁名称
	 */
	private String ceoEmpName;
	/**
	 * 关联预约信息
	 */
	private String reserveInfo;
	/**
	 * 洽谈实体
	 */
	private SaleChatsEntity chatsEntity;
	/**
	 * 签到信息VO
	 */
	private SignVo signVo;
	/**
	 *签到扫描陪同人工号列表
	 */
	private List<Map<String,Object>> empCodeList;

	/**
	 * 标记是签到陪同人版本
	 */
	private String isAccompanyVersion;

####签到信息实体：SignEntity
	/**
	 * 主键id
	 */
	private String id;
	/**
	 *客户ID
	 */
	private String customerId;

	/**
	 * 经度
	 */
	private Double longitude;
	
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 签到地址 
	 */
	private String signAddress;
	/**
	 * 图片文件名
	 */
	private String imgName;

	/**
	 * 图片路径
	 */
	private String imgUrl;

	/**
	 *签到扫描陪同人列表
	 */
	private  List<SignAccompanyEntity> signAccopmanyList;
	
####扫一扫签到信息实体：SweepSignEntity
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 扫描人工号
	 */
	private String sweepPeop;	
	/**
	 * 被扫描人工号
	 */
	private String wasSweepPeop;	
	/**
	 * 二维码生成时间
	 */
	private Date qrcodeTime;	
	/**
	 * 扫描时间
	 */
	private Date sweepTime;
	/**
	 * 经度
	 */
	private Double longitude;	
	/**
	 * 纬度
	 */
	private Double latitude;	
	/**
	 * 签到地址 
	 */
	private String signAddress;	
	/**
	 * 图片文件名
	 */
	private String imgName;
	/**
	 * 图片路径
	 */
	private String imgUrl;
	/**
	 * 积分
	 */
	private String vantages;
	/**
	 * 多张图片信息
	 */
	private List<AttachmentEntity> attachmentList;
	
####会议签到信息实体：MeetingSignEntity
	/**
	 * 扫描人工号
	 */
	private String sweepPeop;	
	/**
	 * 扫描人姓名
	 */
	private String sweepPeopName;	
	/**
	 * 扫描人岗位编码
	 */
	private String sweepPeopJobCode;	
	/**
	 * 扫描人岗位名称
	 */
	private String sweepPeopJobName;	
	/**
	 * 扫描人部门编码
	 */
	private String sweepPeopDeptCode;	
	/**
	 * 扫描人部门名称
	 */
	private String sweepPeopDeptName;	
	/**
	 * 扫描签到类型
	 */
	private String sweepPeopType; 	
	/**
	 * 二维码生成时间
	 */
	private Date qrcodeTime;
	/**
	 * 扫描时间
	 */
	private Date sweepTime;	
	/**
	 * 扫描结束时间
	 */
	private Date sweepEndTime;	
	/**
	 * 经度
	 */
	private Double longitude;
	/**
	 * 纬度
	 */
	private Double latitude;
	/**
	 * 签到地址
	 */
	private String signAddress;
	/**
	 * 被扫描签到信息
	 */
	private List<WasMeetingSignEntity> wasMeetingSignList;	
	/**
	 * 附件信息
	 */
	private List<AttachmentEntity> attachmentList;
	
####会议签到被扫描人信息实体：WasMeetingSignEntity
	/**
	 * 扫描人id
	 */
	private String wasSweepPeopSignId;	/**
	 * 被扫描人工号
	 */
	private String wasSweepPeop;	
	/**
	 * 被扫描人姓名
	 */
	private String wasSweepPeopName;	
	/**
	 * 被扫描人岗位编码
	 */
	private String wasSweepPeopJobCode;	
	/**
	 * 被扫描人岗位名称
	 */
	private String wasSweepPeopJobName;	
	/**
	 * 被扫描人部门编码
	 */
	private String wasSweepPeopDeptCode;	
	/**
	 * 被扫描人部门名称
	 */
	private String wasSweepPeopDeptName;
	

####签到信息VO：SignVo
	/**
	 * 客户ID
	 */
	private String accountId;	
	/**
	 * 会议类型
	 */
	private String meetingType;
	/**
	 * 签到实体
	 */
	private SignEntity signEntity;	
	/**
	 * 扫描签到实体
	 */
	private SweepSignEntity sweepSignEntity;	
	/**
	 * 会议签到信息实体
	 */
	private MeetingSignEntity meetingSignEntity;	
	/**
	 *未被关联签到地址
	 */
	private List<Sign> noRelationSignList;	
	/**
	 * 图片内容
	 */
	private byte[] imgContent;	
	/**
	 * 未被关联会议签到信息
	 */
	private List<MeetingSignEntity> meetingSignList;	

####签到陪同人信息：SignAccompanyEntity
	/**
	 *主键
	 */
	 private String id;
	/**
	 *邀请签到人编号
	 */
	private String signId;
	/**
	 *被扫描人工号
	 */
	private String scanAccompanyEmpCode;
	/**
	 *被扫描人姓名
	 */
	private String scanAccompanyEmpName;
	/**
	 *被扫描陪同人岗位编号
	 */
	private String scanAccompanyJobCode;
	/**
	 *被扫描陪同人岗位名称
	 */
	private String scanAccompanyJobName;
	/**
	 *被扫描陪同人部门编号
	 */
	private String scanAccompanyDeptCode;
	/**
	 *被扫描陪同人部门名称
	 */
	private String scanAccompanyDeptName;
	/**
	 *是否有效
	 */
	private String active;

### 1、分页查询预约信息(工作日历)
##### 请求路径：/rs/reserve/getReserveByPaging
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.start     | 整型 | 是 |   分页起始位置     |
| ReserveAppVo.limit        | 整型 |   是   |   每页条数   |
| ReserveAppVo.startDate    | 时间 |   是   |   开始时间  |
| ReserveAppVo.endDate   | 时间 |   是   |   结束时间   |

	例：{"start":0,"limit":15,"startDate":"1436198400000","endDate":"1436284800000"}

#####接口返回信息：
ReserveAppVo.reserveDayList: 预约信息集合
ReserveAppVo.totalCount：分页所需的数据总长度，长整型

### 2、新增预约信息
##### 请求路径：/rs/reserve/merge
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.reserveEntity.customerEntity.id  | 字符串 |   是   |   客户id |
| ReserveAppVo.reserveEntity.contactEntity.id   | 字符串 |   否   |   联系人编号  |
| ReserveAppVo.reserveEntity.reserveStartTime   | 时间 |   是   |   预约开始时间  |
| ReserveAppVo.reserveEntity.reserveEndTime   | 时间 |  是   |   预约结束时间  |
| ReserveAppVo.reserveEntity.reserveType   | 数据字典值 |  是   |   预约类型  |
| ReserveAppVo.reserveEntity.warningTime   | 数据字典值 |  是   |   提醒时间  |

ReserveAppVo.reserveEntity：预约信息实体

	例:
	{
		"reserveEntity": {
			"customerEntity": {
				"id": "3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"
			},
			"contactEntity":{
			   	"id":"3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"
			},
			"reserveEndTime": 1436325626000,
			"reserveStartTime": 1436235263000,
			"reserveType": "1",
			"warningTime": "120"
		}
	}
#####接口返回信息：
响应基础实体，无业务信息

### 3、修改预约信息
##### 请求路径：/rs/reserve/merge
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.reserveEntity.id  | 字符串 |   是   |  预约id |
| ReserveAppVo.reserveEntity.customerEntity.id  | 字符串 |   是   |   客户id |
| ReserveAppVo.reserveEntity.contactEntity.id   | 字符串 |   否   |   联系人编号  |
| ReserveAppVo.reserveEntity.reserveStartTime   | 时间 |   是   |   预约开始时间  |
| ReserveAppVo.reserveEntity.reserveEndTime   | 时间 |  是   |   预约结束时间  |
| ReserveAppVo.reserveEntity.reserveType   | 数据字典值 |  是   |   预约类型  |
| ReserveAppVo.reserveEntity.warningTime   | 数据字典值 |  是   |   提醒时间  |

ReserveAppVo.reserveEntity：预约信息实体

	例:
	{
		"reserveEntity": {
			"id": e14e45f2-85b4-4f9a-8681-753d85408379,
			"customerEntity": {
				"id": "3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"
			},
			"contactEntity":{
				"id":"3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"
			},
			"reserveEndTime": 1436325626000,
			"reserveStartTime": 1436235263000,
			"reserveType": "1",
			"warningTime": "120"
		}
	}	
#####接口返回信息：
响应基础实体，无业务信息

### 4、按id查询预约信息
##### 请求路径：/rs/reserve/queryReserveById
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.reserveEntity.id     | 字符串 | 是 |  预约主键ID     |

	例：{"reserveEntity":{"id":"e14e45f2-85b4-4f9a-8681-753d85408379"}}

#####接口返回信息：
   
result: ReserveAppVo 预约业务信息
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 5、删除预约信息
##### 请求路径：/rs/reserve/delReserve
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.ids     | 集合 | 是 |   预约ID集合     |
| ReserveAppVo.resverEntity.delDesc  | 字符串 | 是 |  预约删除原因 |

	例：{"ids":["ac1e06fd-6413-4b9b-a522-d39246163eff","948d4dca-f179-49ab-802e-e14601cc5a0a"],"reserveEntity":{"delDesc":"测试删除"}}

#####接口返回信息：
响应基础实体，无业务信息

### 6、查询未赴约的客户预约信息
##### 请求路径：/rs/reserve/queryNoReserve
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReserveAppVo.accountId | 字符串 | 是 |  客户编号  |

	例:{"accountId":"3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"}

#####接口返回信息：
	例:
	{
		"result":{
			"noReserveList":
			 [
				  {
				    "reserveEntity": null,
				    "accountId": null,
				    "tierEmpName": null,
				    "roadEmpName": null,
				    "regionsEmpName": null,
				    "businessUnitEmpName": null,
				    "odEmpName": null,
				    "ceoEmpName": null,
				    "reserveInfo": "开始:2015-07-09 10:20:45 结束:2015-07-09 10:20:46 主题:111111111",
				    "reserveId": "0febdcf8-c5b0-402f-93d7-61910be60357"
				  },
				  {
				    "reserveEntity": null,
				    "accountId": null,
				    "tierEmpName": null,
				    "roadEmpName": null,
				    "regionsEmpName": null,
				    "businessUnitEmpName": null,
				    "odEmpName": null,
				    "ceoEmpName": null,
				    "reserveInfo": "开始:2015-07-10 15:02:09 结束:2015-07-11 15:02:11 主题:顶顶顶顶顶顶顶顶",
				    "reserveId": "860b0803-f5db-415e-9511-78ed4efc7faa"
				  }
			  ]
		}
	}
result：ReserveAppVo.noReserveList 未赴约集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 7、新增洽谈信息(赴约)
##### 请求路径：/rs/chats/merge
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ChatsAppVo.chatsEntity.customerEntity.id     | 字符串 |   是   |   客户编号  |
| ChatsAppVo.chatsEntity.contactEntity.id   | 字符串 |   是   |   联系人编号  |
| ChatsAppVo.chatsEntity.reserveEntity.id   | 字符串 |  否  |   关联预约编号  |
| ChatsAppVo.chatsEntity.sign.id   | 字符串 |  是  |   签到地址id  |
| ChatsAppVo.chatsEntity.reserveAddress   | 字符串 |  是  |   签到地址文本  |
| ChatsAppVo.chatsEntity.chatStartTime   | 时间 |   是   |   洽谈开始时间  |
| ChatsAppVo.chatsEntity.chatEndTime   | 时间 |  是   |   洽谈结束时间  |
| ChatsAppVo.chatsEntity.chatType   | 字典(CUSTOMER_YXLX) |  是   |   洽谈类型  |

	例:
	{
		"chatsEntity":{
			"customerEntity":{
				"id":"855ff9d0-3ea9-4157-bace-38605c7afb55"
			},
			"contactEntity":{
			   	"id":"3d3c0d99-b0ba-4429-8dd9-7d9b22344efe"
			},
			"sign":{
				"id":"11211c3a-e0e8-4c72-bcf5-122e3acb6b41"
			},
			"reserveAddress":"华翔路2239号",
			"chatStartTime": 1436325626000,
			"chatEndTime": 1437235263000,
			"chatType":"1"
		}
	}
#####接口返回信息：
响应基础实体，无业务信息

### 8、新增洽谈信息(离线签到绑定上传)
##### 请求路径：/rs/chats/addChartsAndSign
##### 接口请求方式：POST
##### 注：相比原来新增洽谈，增加了签到实体sign
Vo，并且signVo实体中的ID需在APP端生成36位的主键。
##### 2015-11-06 添加签到扫描的工号集合字段。
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| signVo.imgContent | byte | 是   |  图片的二进制流  |
| signVo.signEntity.id | 字符串 | 是   |  离线签到id  |
| signVo.signEntity.customerId | 字符串 | 是   |  客户id  |
| signVo.signEntity.longitude  | 浮点 | 是   |   经度  |
| signVo.signEntity.latitude   | 浮点 | 是   |   纬度  |
| signVo.signEntity.signAddress| 字符串| 是| |  签到地址 |
| signVo.signEntity.imgName   | 字符串 | 是   |   图片名称  |
| signVo.signEntity.createDate   | 时间 | 是   |   离线签到实际时间  |
| chatsEntity.customerEntity.id     | 字符串 |   是   |   客户编号  |
| chatsEntity.contactEntity.id   | 字符串 |   是   |   联系人编号  |
| chatsEntity.reserveEntity.id   | 字符串 |  否  |   关联预约编号  |
| chatsEntity.sign.id   | 字符串 |  是  |   签到地址id  |
| chatsEntity.reserveAddress   | 字符串 |  是  |   签到地址文本  |
| chatsEntity.chatStartTime   | 时间 |   是   |   洽谈开始时间  |
| chatsEntity.chatEndTime   | 时间 |  是   |   洽谈结束时间  |
| chatsEntity.chatType   | 字典(CUSTOMER_YXLX) |  是   |   洽谈类型  |
| empCodeList   | list(集合) |  否   |   陪同人工号  |

	例:
	{
	    "signVo": {
	        "signEntity": {
	            "id":"63baec03-4ac6-4b10-8f97-79df86095fb6",
	            "customerId": "469ff944-a4dd-4047-87df-58849f16a779",
	            "longitude": "31.15",
	            "latitude": "121.1",
	            "signAddress": "华翔路2239号",
	            "imgName": "图片1.jpg"
	        },
	        "imgContent": [1,2]
	    },
	    "chatsEntity":{
	        "customerEntity":{
	            "id":"469ff944-a4dd-4047-87df-58849f16a779"
	        },
	        "contactEntity":{
	            "id":"83c1c96b-bfd4-4815-9236-2c41cdea23a4"
	        },
	        "sign":{
	            "id":"63baec03-4ac6-4b10-8f97-79df86095fb6"
	        },
	        "reserveAddress":"华翔路2239号",
	        "chatStartTime": 1436325626000,
	        "chatEndTime": 1437235263000,
	        "chatType":"2"
	    },
        "empCodeList":[
        {
            "empCode":"00200097"
        },{
            "empCode":"00275686"
        }]
	}
#####接口返回信息：
响应基础实体，无业务信息

### 9、按id查询洽谈信息
##### 请求路径：rs/chats/queryChatsById
##### 2015-11-06 添加是否是新版本查询洽谈详情的字段,包含签到信息以及签到陪同人集合。
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ChatsAppVo.chatsEntity.id   | 整型 | 是   |   洽谈主键  |

	例: {"chatsEntity":{"id":"b9b449f2-1ffa-4588-b720-d17bc47963c8"}
}

#####接口返回信息

	result:{
			chatsEntity:{   //洽谈信息
				customerEntity:{...}    //客户信息
				contactEntity:{...}     //联系人信息
				sign:{					//签到信息
					signAccopmanyList:[{    //陪同人集合
						...
					},{
						...
					}]
				...
				} 
			}    
	} 

errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空



### 10、创建签到
##### 请求路径：rs/sign/addSignInfo
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| imgContent | 文件 | 是   |  图片  |
| signEntity.customerId | 字符串 | 是   |  客户id  |
| signEntity.longitude  | 浮点 | 是   |   经度  |
| signEntity.latitude   | 浮点 | 是   |   纬度  |
| signEntity.signAddress| 字符串| 是| |  签到地址 |
| signEntity.imgName   | 字符串 | 是   |   图片名称  |

	例: {"signEntity":{"customerId":"a6e63edb-861d-47fc-9299-93daca52f78e","longitude":"31.15","latitude":"121.1","signAddress":"华翔路2239号","imgName":"图片1.jpg"},"imgContent":[]}

#####接口返回信息
响应基础实体，无业务信息

### 11、查询客户未关联的签到地址
##### 请求路径：/rs/sign/queryNoRelationSignList
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| SignVo.accountId | 字符串 | 是   |  客户id  |

	例: 	{"accountId":"a6e63edb-861d-47fc-9299-93daca52f78e"}
	
#####接口返回信息
SignVo.noRelationSignList

### 12、扫一扫签到
##### 请求路径：/rs/sign/addSweepSignInfo
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| SignVo.imgContent | 文件二进制流数组 | 是   |  图片  |
| SignVo.sweepSignEntity | 扫一扫签到信息对象 | 是   |  签到信息实体  |

	例: {"sweepSignEntity":{"sweepPeop":"00277610","wasSweepPeop":"00277620","qrcodeTime":"1437621881000","sweepTime":"1437621895","longitude":"100.5","latitude":"500.62","signAddress":"上海市@@@","imgName":"签到1.jpeg"},"imgContent":[]}

#####接口返回信息
响应基础实体，无业务信息

### 13、扫一扫签到（多张图片）
##### 请求路径：/rs/sign/addMoreImgSweepSignInfo
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| SignVo.sweepSignEntity | 扫一扫签到信息对象 | 是   |  签到信息实体  |
| SignVo.sweepSignEntity.vantages | 整形 | 是   |  积分  |
| SignVo.sweepSignEntity.attachmentList | 附件信息数组 | 是   |  多张图片信息  |
| SignVo.sweepSignEntity.attachmentList.fileName | 字符串 | 是   |  图片名称  |
| SignVo.sweepSignEntity.attachmentList.reorder | 整形 | 是   |  当前图片在所有图片中的顺序   |
| SignVo.sweepSignEntity.attachmentList.imgContent | BYTE数组 | 是   |  图片内容二进制流  |

	例: {"sweepSignEntity":{"sweepPeop":"00277610","wasSweepPeop":"00277630","qrcodeTime":"1437621881000","sweepTime":"1437621895","longitude":"100.5","latitude":"500.62","signAddress":"上海市@@@","imgName":"签到1.jpeg","vantages":100,"attachmentList":[{"descrip":"123","fileName":"1.jpg","reorder":"1","imgContent":[1,2,3]},{"descrip":"456","fileName":"2.jpg","reorder":"2","imgContent":[4,5,6]}]}}

#####接口返回信息
响应基础实体，无业务信息

### 14、会议签到
##### 请求路径：/rs/sign/addMeetingSignInfo
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| SignVo.meetingSignEntity | 会议签到信息对象 | 是   |  签到信息实体  |
| SignVo.meetingSignEntity.sweepPeopType | 字符串 | 是   |  签到类型  |
| SignVo.meetingSignEntity.sweepPeop | 字符串 | 是   |  扫描人  |
| SignVo.meetingSignEntity.qrcodeTime | 字符串 | 是   |  二维码生成时间  |
| SignVo.meetingSignEntity.sweepTime | 时间 | 是   |  扫描时间   |
| SignVo.meetingSignEntity.longitude | 字符串 | 是   |  经度  |
| SignVo.meetingSignEntity.latitude | 字符串 | 是   |  纬度  |
| SignVo.meetingSignEntity.signAddress | 字符串 | 是   |  签到地址   |
| SignVo.meetingSignEntity.wasMeetingSignList | 被扫描人对象数组 | 是   |  被扫描人信息  |
| SignVo.meetingSignEntity.wasMeetingSignList.wasSweepPeop | 字符串 | 是   |  被扫描人工号  |
| SignVo.meetingSignEntity.attachmentList | 附件对象数组 | 是   |  附件信息  |
| SignVo.meetingSignEntity.attachmentList.fileName | 字符串 | 是   |  附件文件名  |
| SignVo.meetingSignEntity.attachmentList.reorder | 整形 | 是   |  附件顺序  |
| SignVo.meetingSignEntity.attachmentList.imgContent | BYTE数组 | 是   |  图片内容  |

	例: {
		    "meetingSignEntity": {
		        "sweepPeopType":"1","sweepPeop": "00277630","qrcodeTime":"1437621881000","sweepTime":"1437621895000","longitude":"100.5","latitude":"500.62","signAddress":"上海市@@@",
		        "wasMeetingSignList": [
		            {"wasSweepPeop": "00277610"},
		            {"wasSweepPeop": "00277630"}
		        ],
		        "attachmentList": [
		            {"fileName": "1.jpg","reorder": "1","imgContent": [1,2,3]},
		            {"fileName": "2.jpg","reorder": "2","imgContent": [4,5,6]
		            }
		        ]
		    }
		}

#####接口返回信息
响应基础实体，无业务信息

### 15、查询未关联的会议签到信息
##### 请求路径：/rs/sign/queryNoRelationMeetingSignList
##### 接口请求方式：POST
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| SignVo.meetingType | 字符串 | 是   |  签到类型  |

	例: 	{"meetingType":"1"}
	
#####接口返回信息
SignVo.meetingSignList：会议签到信息集合

## 五、历史跟进和工作回顾模块
### 相关实体说明
####历史和回顾信息实体：ReviewHistoryEntity
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;
	/**
	 * 联系人信息
	 */
	private ContactEntity contactEntity;
	/**
	 * 预约信息
	 */
	private SaleReserveEntity reserveEntity;
	/**
	 * 洽談信息
	 */
	private SaleChatsEntity chatsEntity;
	/**
	 *签到信息
	 */
	private SignEntity signEntity;
	/**
	 * 执行操作描述
	 */
	private String operateType;
	/**
	 * 操作类型名称
	 */
	private String operateName;
	/**
	 *操作人
	 */
	private String empName;

####历史和回顾信息VO：ReviewHistoryVo
	/**
	 * 历史回顾创建时间
	 */
	private Date createDate;
	/**
	 *用于历史跟进显示的企业名称
	 */
	private String enterpriseName;
	/**
	 * 明细的集合
	 */
	private List<ReviewHistoryEntity> reviewHistoryEntityList;
	/**
	 * 预约创建时间
	 */
	private Date reserveTime;
	/**
	 * 工作日历集合(预约)
	 */
	private List<SaleReserveEntity> reserveList;

####历史和回顾信息APP端VO：ReviewHistoryAppVo
	/**
	 * 客户id
	 */
	private String accountId;
	/**
	 * 开始时间
	 */
	private Date startDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 结果集合
	 */
	private List<ReviewHistoryVo> reviewhistoryvo;

### 1、查询历史跟进信息
##### 请求路径：/rs/reviewHistory/getHistory
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReviewHistoryAppVo.start     | 整型 | 是 |   分页起始位置     |
| ReviewHistoryAppVo.limit        | 整型 |   是   |   每页条数   |
| ReviewHistoryAppVo.accountId    | 字符串 |   是   |   客户编号  |
	
	例：{"start":0,"limit":15,"accountId":"a6e63edb-861d-47fc-9299-93daca52f78e"}

#####接口返回信息
result：ReviewHistoryAppVo.reviewhistoryvo 集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 2、查询工作回顾信息
##### 请求路径：/rs/reviewHistory/getReview
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReviewHistoryAppVo.start     | 整型 | 是 |   分页起始位置     |
| ReviewHistoryAppVo.limit        | 整型 |   是   |   每页条数   |
| ReviewHistoryAppVo.startDate    | 日期 |   是   |   开始时间  |
| ReviewHistoryAppVo.endDate    | 日期 |   是   |   结束时间  |
	
例：{"start":0,"limit":15,"startDate":"1436198400000","endDate":"1436284800000"}

#####接口返回信息
result：ReviewHistoryAppVo.reviewhistoryvo 集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

## 六、消息模块
### 相关实体说明
####消息信息实体：MessageInfoEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
    private Date createDate;
	/**
     * 创建用户
	 */
    private String createUser;
	/**
	 * 修改时间
	 */
    private Date modifyDate;
	/**
	 * 修改用户
	 */
    private String modifyUser;
	/**
	 * DC账号
	 */
	private String dcAccount;	
	/**
	 * CRM账号
	 */
	private String crmAccount;
	/**
	 * 客户信息
	 */
	private CustomerEntity customerEntity;	
	/**
	 * 业务类型
	 */
	private String busType;	
	/**
	 * 消息类型
	 */
	private String messageType;	
	/**
	 * 消息标题
	 */
	private String messageTitle;	
	/**
	 * 消息内容
	 */
	private String messageContent;	
	/**
	 * 消息超链接地址
	 */
	private String messageUrl;	
	/**
	 * 消息发送人
	 */
	private String sendUserId;	
	/**
	 * 消息接收人
	 */
	private String receiveUserId;	
	/**
	 * 匹配条件
	 */
	private String condition;	
	/**
	 * 消息是否已经推送，0，未发送  1，已发送  2，无需推送
	 */
	private String isSend;	
	/**
	 * 消息是否已经阅读，0：未读  1：已读
	 */
	private String isRead;	
	/**
	 * 消息ID
	 */
	private String msgId;	
	/**
	 * 发送时间
	 */
	private Date sendTime;
	
####消息信息VO实体：MessageInfoVo
	/**
	 * 消息信息
	 */
	private MessageInfoEntity messageInfoEntity;	
	/**
	 * 消息信息集合
	 */
	private List<MessageInfoEntity> messageList;	
	/**
	 * 消息VO的集合
	 */
	private List<MessageInfoVo> messageVoList;	
	/**
	 * 消息的业务类型
	 */
	private String busType;	
	/**
	 * 当前类型的未读条数
	 */
	private int countMessage;	
	/**
	 * 当前类型的所有消息条数
	 */
	private int allCountMessage;
	/**
     * 分页数据总长度
     */
	private Long totalCount;    
    /**
     * 客户信息ID集合
     */
	private List<String> ids;	
	/**
	 * 分页开始
	 */
	private int start;	
	/**
	 * 分页结束
	 */
	private int limit;	
	/**
	 * 开始时间
	 */
	private Date startDate;	
	/**
	 * 结束时间
	 */
	private Date endDate;	
	/**
	 * 排序方式
	 */
	private String sortType;

### 1、查询消息大类与未读条数（执行类、知晓类... ...）
##### 请求路径：/rs/message/countMessageType
##### 接口请求方式：GET
##### 传入参数：
无
#####接口返回信息：
messageVoList：消息VO实体集合

### 2、分页查询某类型的消息
##### 请求路径：/rs/message/queryMessageInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| MessageInfoVo.start     | 整形 | 是 |   分页起始位置     |
| MessageInfoVo.limit        | 整形 |   是   |   每页条数   |
| MessageInfoVo.busType    | 字符 |   是   |   业务消息类型   |

	例：{"start":0,"limit":10,"busType":"2"}

#####接口返回信息：
MessageInfoVo.messageList： 消息信息集合

### 3、修改消息已读状态
##### 请求路径：/rs/message/updateMessageReadStatus
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| MessageInfoVo.ids     | 字符 | 是 |   消息ID集合     |

	例：{"ids":["3bb3ce9f-431a-4be0-b72a-c715dae75d7e","7f9010ab-5e72-4838-9a5e-b631faef914a"]}
	
#####接口返回信息：
响应基础实体，无业务信息

## 七、网点、价格时效、运单查询模块
### 此模块接口调用官网APP系统后台，测试环境调用地址为：http://10.39.251.177:8080/hoauapp，正式环境地址为：http://hma.hoau.net:8080/hoauapp
### 相关实体说明
####网点信息实体：CompanyVo
	/**
	 * 公司编号
	 */
	private String companyCode;
	/**
	 * 公司简称
	 */
	private String companyName;
	/**
	 * 公司详细名称
	 */
	private String companyNameDetail;
	/**
	 * 详细地址
	 */
	private String addressDetail;
	/**
	 * 纬度
	 */
	private double lat;
	/**
	 * 经度
	 */
	private double lng;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 是否提供定日达
	 */
	private boolean isSpecifiedTime;
	/**
	 * 是否可出发
	 */
	private boolean isDepart;
	/**
	 * 是否可自提
	 */
	private boolean isTakeSelf;
	/**
	 * 是否可送货
	 */
	private boolean isDeliver;

####网点信息返回值对象VO：NearDeptsVo
	/**
	 * 网点信息集合
	 */
	List<CompanyVo> companys;
	/**
	 * 本次方法是否调用成功，0：成功，1：业务异常，2：系统异常
	 */
	private String errcode;
	/**
	 * 异常信息
	 */
	private String errmsg;

####省份信息实体：DistrictInfo
	/**
	 * 省份编码
	 */
	private String  districtCode;
	/**
	 * 省份名称
	 */
	private String  districtName;
	/**
	 * 简称
	 */
	private String  districtShortName;
	/**
	 * 上级id
	 */
	private String  districtParentId;
	/**
	 * 是否是子级
	 */
	private boolean hasChild;

####热门城市信息实体：HotCityInfo
	/**
	 * 上级（省级信息）
	 */
	private DistrictInfo province;

####热门城市信息返回VO：HotCityResVo
	/**
	 * 热闹城市信息集合
	 */
	List<HotCityInfo> districtInfos;

####省份、城市、区域信息返回VO：DistrictResVo
	/**
	 * 省份信息集合
	 */
	List<DistrictInfo> districtInfos;

####价格时效查询参数实体：PriceQueryEntity
	//发货城市
	private String shipperCity;
	
	//发货区域
	private String shipperCounty;
	
	//收货城市
	private String recCity;
	
	//收货区域
	private String recCounty;

####价格时效信息实体：PriceTimeVo
	//产品类型ID
	private Integer ebpdEbptId;
	//运营时间
	private Double ebpdOpHour;
	//可提货时间
	private String ebpdDeliveryHour;
	private String ebpdSendHour;
	//起步价(元/票)
	private Double ebpdStartPrice;
	//重货(元/公斤)
	private Double ebpdHeavyCargo;
	//轻货(元/立方)
	private Double ebpdLightCargo;
	//发货市ID
	private Integer ebpdShipperCityId;
	//发货市名称
	private String ebpdShipperCityName;
	//收货市ID
	private Integer ebpdConsigneeCityId;
	//收货市名称
	private String ebpdConsigneeCityName;
	//重货折扣率
	private Double ebdiHeavyGoods;
	//轻货折扣率
	private Double ebdiLightGoods;
	//重货折扣价格
	private Double heavyDisPrice;
	//轻货折扣价格
	private Double lightDisPrice;
	//产品类型
	private String ebptNameCn; 
	//顺序
	private String sunxu;
	
	/**
	 * 收发货区县
	 */
	private Integer ebpdShipperCountyId;
	private String ebpdShipperCountyName;
	private Integer ebpdConsigneeCountyId;
	private String ebpdConsigneeCountyName;

####价格时效信息返回值对象VO：PriceTimeList
	/**
	 * 价格时效信息集合
	 */
	private List<PriceTimeVo> list ;
	/**
	 * 本次方法是否调用成功，0：成功，1：业务异常，2：系统异常
	 */
	private String errcode;
	/**
	 * 异常信息
	 */
	private String errmsg;

####运单信息实体：TraceInfo
	/**
	 * 描述
	 */
	private String desc;
	/**
	 * 时间
	 */
	private String time;
	/**
	 * 是否最新状态
	 */
	private boolean isNewestStatus;

####运单信息返回值对象VO：GoodsTraceResponse
	private List<TraceInfo> traceInfos;
	private String pickComCustomerCare;
	private String pickCompanyAddr;
	private String pickCompanyName;
	private String pickCompanyTel;

### 1、附近网点查询
##### 请求路径：/rs/location/near/
##### 接口请求方式：GET
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| lng     | 字符 | 是 |  经度    |
| lat     | 字符 | 是 |  纬度     |

#####接口返回信息：
网点信息VO：NearDeptsVo

### 2、价格时效查询
##### 请求路径：/rs/price/query
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| PriceQueryEntity.shipperCity     | 字符 | 是 |  发货城市    |
| PriceQueryEntity.shipperCounty     | 字符 | 是 |  发货区域     |
| PriceQueryEntity.recCity     | 字符 | 是 |  收货城市    |
| PriceQueryEntity.recCounty     | 字符 | 是 |  收货区域     |

#####接口返回信息：
价格时效信息：PriceTimeList

### 3、获取热门城市
##### 请求路径：/rs/district/hotcity
##### 接口请求方式：GET
##### 传入参数：
无

#####接口返回信息：
热闹城市信息：HotCityResVo

### 4、获取省份
##### 请求路径：/rs/district/province
##### 接口请求方式：GET
##### 传入参数：
无

#####接口返回信息：
省份信息：DistrictResVo

### 5、获得城市
##### 请求路径：/rs/district/city
##### 接口请求方式：GET
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
|   province   | 字符 | 是 |  省份名称    |

#####接口返回信息：
城市信息：DistrictResVo

### 6、获得区县
##### 请求路径：/rs/district/district
##### 接口请求方式：GET
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
|   city   | 字符 | 是 |  城市名称    |

#####接口返回信息：
区县信息：DistrictResVo

### 7、运单查询
##### 请求路径：/hoauapp/rs/goods/trace
##### 接口请求方式：GET
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| waybill     | 字符 | 是 |  运单号    |

#####接口返回信息：
运单信息：GoodsTraceResponse

## 八、周边系统调用接口模块
### 相关实体说明
####运单信息实体：WayBillEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 数据类型 1、运单 2、修改金额  3、签收 4、异常 5、投诉 6、理赔 
	 */
	private String dataType;
	/**
	 * 运单编号
	 */
	private String billNum;
	/**
	 * 迪辰账号
	 */
	private String dcAccount;
	/**
	 * 运输类型
	 */
	private String transportType;
	/**
	 * 开单时间
	 */
	private Date billingDate;
	/**
	 * 托运日期
	 */
	private Date checkDate;
	/**
	 * 起运地
	 */
	private String startingPoint;
	/**
	 * 目的地
	 */
	private String destination;
	/**
	 * 发货人
	 */
	private String shipper;
	/**
	 * 收货人
	 */
	private String consignee;
	/**
	 * 开单金额
	 */
	private double billingAmount;
	/**
	 * 实收金额
	 */
	private double incomeAmount;
	/**
	 * 收款门店
	 */
	private String incomeDept;	
	/**
	 * 是否有效
	 */
	private String active;
	/**
	 * 签收状态
	 */
	private String signStatus;

####运单信息VO对象：WayBillVo
	/**
	 * 运单数据集合
	 */
	private List<WayBillEntity> wayBillList;
	/**
	 * 成功数据的ID
	 */
	private List<String> successIdList;
	/**
	 * 失败数据的ID
	 */
	private List<String> failureIdList;

####个人客户信息：PersonalCustomerEntity
	/**
	 *主键id
	 */
	 private String id;
	/**
	 * 客户姓名
	 */
	private String name;	
	/**
	 * 手机号
	 */
	private String cellphone;	
	/**
	 * 座机号
	 */
	private String telephone;	
	/**
	 * 省
	 */
	private String province;	
	/**
	 * 市
	 */
	private String city;	
	/**
	 * 区县
	 */
	private String area;	
	/**
	 * 详细地址
	 */
	private String address;	
	/**
	 * 邮箱
	 */
	private String email;	
	/**
	 * 来源渠道主键ID
	 */
	private String sourceId;	
	/**
	 * 来源渠道
	 */
	private String source;	
	/**
	 * 是否有效
	 */
	private String active;

####个人客户信息VO：PersonalCustomerAppVo
	/**
	 * 个人客户
	 */
	private PersonalCustomerEntity personalCustomerEntity;

####销售合同实体:SaleContractEntity
	/**
	 * 合同状态
	 */
	private String status;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 工作流说明
	 */
	private String workflowExplain;
	/**
	 * 紧急程度
	 */
	private String emergencyDegree;
	/**
	 * 流水号ID
	 */
	private String workflowCode;
	/**
	 * 申请日期
	 */
	private Date applyDate;
	/**
	 * 申请人
	 */
	private String applyUser;
	/**
	 * 申请人工号
	 */
	private String applyUserEmpCode;
	/**
	 * 申请人岗位
	 */
	private String applyUserJobName;
	/**
	 * 申请人部门
	 */
	private String applyUserDeptName;
	/**
	 * 申请门店名称
	 */
	private String applyUserDeclareName;
	/**
	 * 物流代码
	 */
	private String applyUserLogisticCode;
	/**
	 * 所属路区
	 */
	private String roadAreaCode;
	/**
	 * 所属大区
	 */
	private String regionsCode;
	/**
	 * 所属事业部
	 */
	private String businessUnitCode;
	/**
	 * 所属财务大区
	 */
	private String financeRegionsCode;
	/**
	 * 合同管理员
	 */
	private String contractAdmin;
	/**
	 * 是否客户经理级别
	 */
	private String ifSaleManager;
	/**
	 * 流程类型
	 */
	private String processType;
	/**
	 * 合同版本
	 */
	private String contractVersion;
	/**
	 * 签约属性
	 */
	private String signAttribute;
	/**
	 * DC账号
	 */
	private String dcAccount;
	/**
	 * 客户全称
	 */
	private String enterpriseName;
	/**
	 * 客户性质
	 */
	private String customerNature;
	/**
	 * 客户所属行业类型
	 */
	private String industryCode;
	/**
	 * 货物名称
	 */
	private String mainGoodsName;
	/**
	 * 包装
	 */
	private String typeOfPackage;
	/**
	 * 总产值
	 */
	private double ddMonthCount;
	/**
	 * DD折扣
	 */
	private double ddDiscount;
	/**
	 * DU折扣
	 */
	private double duDiscount;
	/**
	 * 最低保价费
	 */
	private double lowestValuationFee;
	/**
	 * 最低保价费率
	 */
	private double insuranceRate;
	/**
	 * 大件货服务约定
	 */
	private String cargoServiceAgreement;
	/**
	 * 最低提货费
	 */
	private double lowestDeliveryFee;
	/**
	 * 最低送货费
	 */
	private double lowestShippingFee;
	/**
	 * 提送货费约定
	 */
	private String agreedDelivery;
	/**
	 * 工本费
	 */
	private double expense;
	/**
	 * 信息费
	 */
	private double informationCost;
	/**
	 * 最低燃油费
	 */
	private double lowestFuel;
	/**
	 * 其他服务费约定
	 */
	private String serviceAgreement;
	/**
	 * 合同开始日期
	 */
	private Date contractStartDate;
	/**
	 * 合同结束日期
	 */
	private Date contractEndDate;
	/**
	 * 是否代收货款
	 */
	private String ifCod;
	/**
	 * 最低代收费
	 */
	private double collectionChargesMin;
	/**
	 * 最低代收费率
	 */
	private double chargeRateMin;
	/**
	 * 结算方式
	 */
	private String paymentMethod;
	/**
	 * 账期描述 
	 */
	private String accountDescribe;
	/**
	 * 客户提供合同版本
	 */
	private String provideContractVersion;
	/**
	 * 是否有非标合同条款
	 */
	private String ifHaveNonstandardContractTerm;
	/**
	 * 是否有非标运营条款
	 */
	private String ifHaveNonstandardOperatTerm;
	/**
	 * 是否开通网上下单
	 */
	private String ifOpenOnlineOrder;
	/**
	 * 原DD折扣
	 */
	private double oldDdDiscount;
	/**
	 * 原DU折扣
	 */
	private double oldDuDiscount;
	/**
	 * 原保价率
	 */
	private double oldInsuranceRate;
	/**
	 * 原最低保价费
	 */
	private double oldLowestValuationFee;
	/**
	 * 原最低提货费
	 */
	private double oldLowestDeliveryFee;
	/**
	 * 原最低送货费
	 */
	private double oldLowestShippingFee;
	/**
	 * 原工本费
	 */
	private double oldExpense;
	/**
	 * 原信息费
	 */
	private double oldInformationCost;
	/**
	 * 原最低燃油费
	 */
	private double oldLowestFuel;
	/**
	 * 原代收货款费率
	 */
	private double oldChargeRateMin;
	/**
	 * 申请事由说明
	 */
	private String applyInstruction;

####销售合同VO:ContractAppVo
	/**
	 * 合同信息
	 */
	private SaleContractEntity saleContractEntity;


### 1、新增运单信息
##### 请求路径：/rs/waybill/addWayBillInfo
##### 接口请求方式：POST
##### 传入参数：
WayBillVo.wayBillList：WayBillEntity运单信息集合

	例：{
		    "wayBillList": [
		        {
		            "id": "1",
		            "dataType":"2",
		            "billNum": "运单号",
		            "dcAccount": "迪辰账号",
		            "transportType": "1",
		            "billingDate": "1436232042000",
		            "checkDate": "1436232040000",
		            "startingPoint": "起运地",
		            "destination": "目的地",
		            "shipper": "张三",
		            "consignee": "收货人",
		            "signStatus":"2",
		            "billingAmount":"100.23",
		            "incomeAmount":"50",
		            "incomeDept":"上海1"
		        },{
		            "id": "2",
		            "dataType":"2",
		            "billNum": "运单号",
		            "dcAccount": "迪辰账号",
		            "transportType": "1",
		            "billingDate": "1436232042000",
		            "checkDate": "1436232040000",
		            "startingPoint": "起运地",
		            "destination": "目的地",
		            "shipper": "张三",
		            "consignee": "收货人",
		            "signStatus":"2",
		            "billingAmount":"100.23",
		            "incomeAmount":"50",
		            "incomeDept":"上海1"
		        }
		    ]
		}

#####接口返回信息：
result：WayBillVo对象，其中包含此次请求成功的数据ID集合与失败的数据ID集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空
### 2、新增个人客户
##### 请求路径：/rs/personalcustomer/addPersonalCustomer
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| PersonalCustomerAppVo.personalCustomerEntity.name     | 字符 | 是 |   客户姓名    |
| PersonalCustomerAppVo.personalCustomerEntity.cellphone     | 字符 | 是 |  手机号     |
| PersonalCustomerAppVo.personalCustomerEntity.telephone     | 字符 | 是 |  座机号    |
| PersonalCustomerAppVo.personalCustomerEntity.province     | 字符 | 是 |  省     |
| PersonalCustomerAppVo.personalCustomerEntity.city     | 字符 | 是 |  市     |
| PersonalCustomerAppVo.personalCustomerEntity.area    | 字符 | 否 |  区县     |
| PersonalCustomerAppVo.personalCustomerEntity.address    | 字符 | 否 |  地址     |
| PersonalCustomerAppVo.personalCustomerEntity.email    | 字符 | 是 |  邮箱     |
| PersonalCustomerAppVo.personalCustomerEntity.sourceId    | 字符 | 是 |  来源渠道编号     |
| PersonalCustomerAppVo.personalCustomerEntity.source    | 字符 | 是 |  来源渠道名称     |

#####接口返回信息：
响应基础实体，无业务信息

### 3、新增销售合同
##### 请求路径：/rs/contract/addSaleContract
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ContractAppVo.saleContractEntity.status     | 字符 | 是 |   合同状态    |
| ContractAppVo.saleContractEntity.workflowCode     | 字符 | 是 |  OA流水号     |
| ContractAppVo.saleContractEntity.dcAccount     | 字符 | 是 |  DC账号    |

#####接口返回信息：
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

## 九、统计分析模块
### 相关实体说明
####查询报表参数实体:ReportDataQueryParamEntity
	/**
	 * 报表类型
	 */
	private String reportType;
	
	/**
	 * 报表子类型
	 */
	private String reportChildType;
	
	/**
	 * 选择查看的账号
	 */
	private String account;
####客户统计报表结果实体:ReportCustomerCountEntity
	/**
	 * 部门名称
	 */
	private String deptName;
	
	/**
	 * 客户总数
	 */
	private long totalCustomer;
	
	/**
	 * 客户性质分布
	 */
	private List<ReportDataEntity> customerNatureDistributions;
	
	/**
	 * 客户详情
	 */
	private List<ReportDataEntity> customerDetails;
####报表数据实体:ReportDataEntity
	/**
	 * 类型
	 */
	private String type;
	/**
	 * 数据1
	 */
	private int dataOne;
	/**
	 * 数据2
	 */
	private int dataTwo;
	/**
	 * 数据3
	 */
	private double dataThree;
	/**
	 * 数据4
	 */
	private double dataFour;
	
####销售收入分析实体:SaleIncomeAnalysisEntity
	/**
	 * 奖金分布集合
	 */
	List<ReportDataEntity> bonusDistributionLists;
	
	/**
	 * 运输类型分布集合
	 */
	List<ReportDataEntity> transportTypeDistributionLists;
####销售收入明细实体:SaleIncomeDetailEntity
	/**
	 * 开单时间
	 */
	private Date billingDate;
	
	/**
	 * 企业名称
	 */
	private String enterpriseName;
	
	/**
	 * 运输类型
	 */
	private String transportType;
	
	/**
	 * 奖金
	 */
	private double bonus;
####销售收入明细VO:SaleIncomeDetailVo
	/**
	 * 当前月份
	 */
	private String currentMonth;
	
	/**
	 * 总收入
	 */
	private double totalIncome;
	
	/**
	 * 收入明细
	 */
	private List<SaleIncomeDetailEntity> saleIncomeDetails;
#### 个人工作,工作详情实体: ReportEmpWorkEntity
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 员工姓名
	 */
	private String empName;
	/**
	 * 工号
	 */
	private String account;
	/**
	 * 当月新增预约次数
	 */
	private String reserveCount;
	/**
	 * 当月新增洽谈次数
	 */
	private String chatsCount;
	/**
	 * 当月新增意向次数
	 */
	private String intentionCount;

#### 报表数据交互Vo: ReportDataAppVo
	/**
	 *当前登录者
	 */
	private ReportEmpWorkEntity empEntity;
	/**
	 *报表参数
	 */
	private ReportDataQueryParamEntity queryParam;
	/**
	 *个人工作报表结果
	 */
	private List<ReportEmpWorkEntity> empworkList;
	
	/**
	 *工作详情报表
	 */
	private List<Map<String,Object>> empworkDetail;
	
	/**
	 *工作详情指标
	 */
	private List<Map<String,Object>> empworkIndex;
	
	/**
	 * 当月客户统计
	 */
	private Map<String,Object> countCustomerByEmp;
	/**
	 *统计分析报表结果
	 */
	private ReportCustomerCountEntity reportCustomerCountEntity;
	/**
	 *分页起始
	 */
	private int start;
	/**
	 *分页数量
	 */
	private int limit;
	/**
	 * 分页数据总长度
	 */
	private long totalCount;
	
	/**
	 * 销售收入分析
	 */
	private SaleIncomeAnalysisEntity saleIncomeAnalysisEntity;
	
	/**
	 * 销售收入明细
	 */
	private SaleIncomeDetailVo saleIncomeDetailVo;

### 1、统计分析(门店)
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportDataAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |

	例:{"start":0,"limit":15,"queryParam":{"reportType":"105001001"}}

#####接口返回信息：
	例:
	{
		  "result": {
		    "totalCount": 11,												  //客户列表返回总数
		    "empworkDetail": [
		      {
		        "accountId": "31690fbf-3194-4ef7-82d8-9afdf038254f",          //客户id
		        "billingAmount": 0,											  //产值(元) 
		        "reserve": 0,												  //预约次数    
		        "enterPriseName": "HH",										  //客户名称  
		        "chats": 0 													  //拜访次数
		      },
		      {
		        "accountId": "49186fa2-a033-4e89-af9b-0d6b79afc00e",
		        "billingAmount": 0,
		        "reserve": 0,
		        "enterPriseName": "中国",
		        "chats": 0
		      }
		      ...
		    ],
		    "empworkIndex": [							//指标统计
		      {
		        "startDate": 1435680000000,
		        "index": "4",
		        "endDate": 1436284800000,
		        "chats": 0
		      },
		      {
		        "startDate": 1436284800000,
		        "index": "4",
		        "endDate": 1436889600000,
		        "chats": 4
		      },
		      {
		        "startDate": 1436889600000,
		        "index": "4",
		        "endDate": 1437494400000,
		        "chats": 2
		      },
		      {
		        "startDate": 1437494400000,
		        "index": "4",
		        "endDate": 1438099200000,
		        "chats": 0
		      },
		      {
		        "startDate": 1438099200000,			//开始日期
		        "index": "4",						//目标指标
		        "endDate": 1438272000000,			//结束日期
		        "chats": 0 							//实际指标
		      }
		    ],
		    "countCustomerByEmp": {					//客户统计
		      "DeliveryCustomer": 0,				//发货客户数
		      "countCustomer": 0,					//客户总数
		      "addCustomer": 0                      //当月新增客户数
		    }
		  },
		  "errorCode": "0",
		  "errorMessage": null
	}
result：empworkDetail , empworkIndex ,countCustomerByEmp
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 2、收入明细(销售)
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportDataAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |
| ReportDataAppVo.queryParam.reportChildType     | 字符 | 是 |  报表子类型     |

	例:{"start":0,"limit":15,"queryParam":{"reportType":"105001002","reportChildType":"1"}}

#####接口返回信息：
result：ReportDataAppVo.saleIncomeDetailVo.SaleIncomeDetailEntity对象
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 3、收入分析(销售)
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |
| ReportDataAppVo.queryParam.reportChildType     | 字符 | 是 |  报表子类型     |

	例:{"queryParam":{"reportType":"105001002","reportChildType":"2"}}

#####接口返回信息：
	省略....
		bonusDistributionLists:[{
			"type":"7",			 //第几天
			"dataThree":"150",   //奖金(元)
		}]
		transportTypeDistributionLists:[{
			"type":"定日达",      //运输类型
			"dataFour":"19.12",   	//百分百比
			"dataThree":"150",  //金额(元)
		}]
		
result：ReportDataAppVo.saleIncomeAnalysisEntity.bonusDistributionLists 奖金分布集合
	    ReportDataAppVo.saleIncomeAnalysisEntity.transportTypeDistributionLists 运输类型分布集合
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 4、统计分析(集团)
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |

	例:{"queryParam":{"reportType":"105001004"}}

#####接口返回信息：
	省略....
		reportCustomerCountEntity:{
			"deptname":"XX部门",
			"totalCustomer":"400",   //客户总数
			"customerNatureDistributions":[{
				"type":"发货客户",    //客户类型
				"dataOne":"200",    //金额(元)
			},{
				"type":"意向客户",
				"dataOne":"100",
			}],
			"customerDetails":[{
				"type":"XX事业部"
				"dataOne":"500",
				"dataTwo":"200",
			}]
		}
result：ReportDataAppVo.saleIncomeAnalysisEntity.  ,
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 5、统计分析(事业部,大区,路区) ------个人工作
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportDataAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |
| ReportDataAppVo.queryParam.reportChildType     | 字符 | 是 |  报表子类型  |

	例:{"start":0,"limit":15,"queryParam":{"reportType":"105001003","reportChildType":"1"}}

#####接口返回信息
	省略部分......
		 "totalCount": 10,
		 "empEntity": {
		      "account": "182308",
		      "empName": "高礼",
		      "deptName": "上海闵行路区",
		      "reserveCount": "1",				
		      "chatsCount": null,				
		      "intentionCount": null
	    },
	    "empworkList": [
	      {
	        "account": "014240",
	        "empName": "何勇",
	        "deptName": "上海闵行区景西路分公司",
	        "reserveCount": null,
	        "chatsCount": null,
	        "intentionCount": null
	      },
	      {
	        "account": "194059",
	        "empName": "伍文",
	        "deptName": "上海闵行区北松路分公司",
	        "reserveCount": null,
	        "chatsCount": null,
	        "intentionCount": null
	      }
	     省略..... 
result：empEntity,empworkList 
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 6.统计分析(事业部,大区,路区) ------工作详情
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportDataAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportDataAppVo.queryParam.account  | 字符 | 是 |   选择工号    |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |
| ReportDataAppVo.queryParam.reportChildType     | 字符 | 是 |  报表子类型  |

	例:{"start":0,"limit":15,"queryParam":{"reportType":"105001003","reportChildType":"1","account":"280585"}}

#####接口返回信息：同统计分析(门店)
result：empworkDetail , empworkIndex ,countCustomerByEmp
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 7.统计分析(事业部,大区,路区) ------客户统计
##### 请求路径：/rs/reportdata/queryAppReportData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportDataAppVo.queryParam.reportType     | 字符 | 是 |   报表类型    |
| ReportDataAppVo.queryParam.reportChildType     | 字符 | 是 |  报表子类型  |

	例:{"queryParam":{"reportType":"105001003","reportChildType":"2"}}

#####接口返回信息：同统计分析(集团)
result：ReportDataAppVo.saleIncomeAnalysisEntity.reportCustomerCountEntity对象
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

## 十、自定义报表模块
### 相关实体说明
####自定义报表实体:ReportAnalysisEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 组织编码
	 */
	private String orgCode;
	/**
	 * 组织名称
	 */
	private String orgName;
	/**
	 * 总数
	 */
	private int	total;
	/**
	 * 所占百分比
	 */
	private double percent;
	/**
	 * 当前部门负责人
	 */
	private String managerName;
	/**
	 * 当前部门负责人电话
	 */
	private String managerCellphone;
	/**
	 * 是否能点击
	 */
	private String isClick;
	
	/**
	 * 组织级别
	 */
	private String orgType;
	
	/**
	 * 排序顺序
	 */
	private String sortNum;
	/**
	 * 人均
	 */
	private double avgOfPeople;
	
	/**
	 * 顶部开始
	 */
	private String head;
	/**
	 * 人均是否隐藏
	 */
	private String hidden;


####自定义报表组织类型权限实体:ReportAnalysisAuthEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 *  角色编号
	 */
	private String roleCode;
	/**
	 * 功能id
	 */
	private String functionCode;
	/**
	 * 功能名称
	 */
	private String functionName;
	/**
	 * 排序
	 */
	private String sort;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 是否有效
	 */
	private String active;
####自定义报表实体VO:ReportAnalysisAppVo
	/**
	 * 时间类型
	 */
	private String timeType;
	/**
	 * 组织类型
	 */
	private String orgType;
	/**
	 * 数据类型
	 */
	private String dataTypeBySort;
	/**
	 * 组织编码
	 */
	private String orgCode;
	/**
	 * 每页数据量
	 */
	private int limit;
	/**
	 * 分布起始位置
	 */
	private int start;
	/**
	 * 分页数据总长度
	 */
	private long totalCount;
	
	/**
	 * 数据明细
	 */
	private List<ReportAnalysisEntity> reportAnalysisEntities;
	
	/**
	 * 组织权限菜单
	 */
	private List<ReportAnalysisAuthEntity> reportAnalysisAuthEntities;
	
	/**
	 * 是否返回
	 */
	private String isReturn;
	
	/**
	 * 返回图标是否隐藏
	 */
	private String isHidden;
	
	/**
	 * 上级部门编码
	 */
	private String supOrgCode;
	
	/**
	 * 上级组织类型
	 */
	private String supOrgType;

	/**
	 * 是否顶部
	 */
	private String isHead;
	
	/**
	 * 第一个排序号
	 */
	private String firstSort;
	
	/**
	 * 组织名称
	 */
	private String orgName;

### 1、自定义报表列表
##### 请求路径：/rs/reportAnalysis/queryReportAnalysisInfos
##### 接口请求方式：POST
##### 传入参数：
	第一次请求时,orgType参数置为空
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportAnalysisAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportAnalysisAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportAnalysisAppVo.timeType | 字符 | 是 |   时间段(数据字典:TERMS_CODE = 'REPORT_TIMETYPE')    |
| ReportAnalysisAppVo.orgType 	| 字符 | 是 |   管理区域(第一次请求时,后台给)    |
| ReportAnalysisAppVo.dataTypeBySort | 字符 | 是 |   统计维度(数据字典:TERMS_CODE = 'REPORT_DATATYPE')     |

	例:第一次请求时：{"start":0,"limit":15},
		非第一次请求: {"start":0,"limit":15,"timeType":"1","orgType":"1","dataTypeBySort":"1-1"}

#####接口返回信息：
	例:第一次请求:
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "统计分析",													//组织名称
	    "orgType": "1",															//组织类型
	    "timeType": null,
	    "orgCode": null,
	    "isHead": null,
	    "supOrgType": null,
	    "isReturn": null,
	    "supOrgCode": "DP010000",												//上级部门
	    "firstSort": null,
	    "isHidden": null,
	    "reportAnalysisEntities": [
	      {
	        "id": "af048960-6dea-11e5-b17b-a0369f659c83",
	        "total": 651,														//总数
	        "percent": 100,														//所占百分比
	        "head": "Y",														//是否钻取顶部
	        "orgName": "深圳事业部",											//组织名称
	        "orgType": "1",														//组织类型
	        "orgCode": "DP141106",												//组织编号
	        "isClick": "Y",														//是否能点击
	        "sortNum": "1",														//管理区域顺序
	        "managerName": "丘文通",											//负责人
	        "managerCellphone": "18017085889",									//负责人电话
	        "avgOfPeople": 1.7,    												//人均
	        "hidden":null														//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": [											//管理区域权限
	      {
	        "id": "b385f32a-5607-11e5-a932-005056af54e6",
	        "sort": "1",														//管理区域顺序
	        "active": null,
	        "createDate": null,
	        "roleCode": "2001",													//角色编号
	        "functionCode": "1",												//权限id
	        "functionName": "事业部"											//权限名称
	      },
	      ...
	    ],
	    "totalCount": 8,														//分页总数
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
	非第一次请求:
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "统计分析",													//组织名称
	    "orgType": "1",
	    "timeType": null,
	    "orgCode": null,
	    "isHead": null,
	    "supOrgType": null,
	    "isReturn": null,
	    "supOrgCode": "DP010000",												//上级部门
	    "firstSort": null,
	    "isHidden": null,
	    "reportAnalysisEntities": [
	      {
	        "id": "af048960-6dea-11e5-b17b-a0369f659c83",
	        "total": 651,														//总数
	        "percent": 100,														//所占百分比
	        "head": "Y",														//是否钻取顶部
	        "orgName": "深圳事业部",											//组织名称
	        "orgType": "1",														//组织类型
	        "orgCode": "DP141106",												//组织编号
	        "isClick": "Y",														//是否能点击
	        "sortNum": "1",														//管理区域顺序
	        "managerName": "丘文通",											//负责人
	        "managerCellphone": "18017085889",									//负责人电话
	        "avgOfPeople": 1.7, 												//人均
	        "hidden":null														//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": null,
	    "totalCount": 8,														//分页总数
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
result：reportAnalysisEntities , totalCount,reportAnalysisAuthEntities
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 2、当前部门明细列表
##### 请求路径：/rs/reportAnalysis/queryReportAnalysisInfoByDeptCode
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportAnalysisAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportAnalysisAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportAnalysisAppVo.timeType | 字符 | 是 |   时间段(数据字典:TERMS_CODE = 'REPORT_TIMETYPE')    |
| ReportAnalysisAppVo.orgCode 	| 字符 | 是 |   部门编码    |
| ReportAnalysisAppVo.dataTypeBySort | 字符 | 是 |   统计维度(数据字典:TERMS_CODE = 'REPORT_DATATYPE')     |

	例:{"start":0,"limit":15,"timeType":"1","orgCode":"DP141105","dataTypeBySort":"1-1"}

#####接口返回信息：
	例：
	{
	  "result": {
	    "start": 0,
	    "dataTypeBySort": null,
	    "limit": 0,
	    "timeType": null,
	    "orgType": null,
	    "orgCode": null,
	    "totalCount": 2,
	    "reportAnalysisEntities": [
	      {
	        "id": "ac386087-56a0-11e5-a932-005056af54e6",
	        "total": 12,
	        "percent": 100,
	        "managerName": "崔强",
	        "managerCellphone": "18665632666",
	        "orgCode": "DP140983",
	        "orgName": "佛山经营大区",
	        "isClick": "Y"
	      },
	      {
	        "id": "ac3449c5-56a0-11e5-a932-005056af54e6",
	        "total": 8,
	        "percent": 60,
	        "managerName": "梁诗伟",
	        "managerCellphone": "18620680678",
	        "orgCode": "DP140170",
	        "orgName": "广州经营大区",
	        "isClick": "Y",
	        "hidden":null														//门店,客户经理，路区&&门店走访 --"Y"
	      }
	    ],
	    "reportAnalysisAuthEntities": null
	  },
	  "errorCode": "0",
	  "errorMessage": null
	}
	
result：reportAnalysisEntities
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 3、当前部门明细列表(新)
##### 请求路径：/rs/reportAnalysis/queryReportAnalysisData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportAnalysisAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportAnalysisAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportAnalysisAppVo.timeType | 字符 | 是 |   时间段(数据字典:TERMS_CODE = 'REPORT_TIMETYPE')    |
| ReportAnalysisAppVo.orgType 	| 字符 | 是 |   部门类型    |
| ReportAnalysisAppVo.firstSort 	| 字符 | 是 |   第一次点击的管理区域序列号    |
| ReportAnalysisAppVo.supOrgCode 	| 字符 | 否 |   上级部门编码    |
| ReportAnalysisAppVo.orgCode 	| 字符 | 是 |   部门编码    |
| ReportAnalysisAppVo.dataTypeBySort | 字符 | 是 |   统计维度(数据字典:TERMS_CODE = 'REPORT_DATATYPE')     |

	例:		根据列表reportAnalysisEntities：head属性判断
	第一次点击列表(列表reportAnalysisEntities：head属性:"Y")：{"start":0,"limit":15,"timeType":"1","orgType":"1",
	"dataTypeBySort":"1-1","firstSort":"1","orgCode":"DP141106","supOrgCode":"DP010000"},
		非第一次点击列表(列表reportAnalysisEntities：head属性:null):{"start":0,"limit":15,"timeType":"1","orgType":"2",
	"dataTypeBySort":"1-1","firstSort":"1","orgCode":"DP140160"}


#####接口返回信息：
	例：
	第一次点击列表:
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "深圳事业部",														//组织名称
	    "orgType": "2",																	//组织类型
	    "timeType": null,
	    "orgCode": null,
	    "isHead": "Y",																	//是否钻取顶部
	    "supOrgType": "1",																//上级组织类型
	    "isReturn": null,
	    "supOrgCode": "DP010000",														//上级部门编号
	    "firstSort": null,
	    "isHidden": null,
	    "reportAnalysisEntities": [
	      {
	        "id": "971127f9-6dea-11e5-b17b-a0369f659c83",
	        "total": 115,																//总数
	        "percent": 100,																//所占百分比
	        "head": null,
	        "orgName": "潮汕大区",														//组织名称
	        "orgType": "2",																//组织类型
	        "orgCode": "DP141103",														//组织编号
	        "isClick": "Y",																//是否能点击
	        "sortNum": "2",																//管理区域排序号
	        "managerName": "于守信",													//负责人
	        "managerCellphone": "18666688055",											//负责人电话
	        "avgOfPeople": 4.4,															//人均
	        "hidden":null																//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": null,
	    "totalCount": 6,																//分页总数
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
	非第一次点击列表：
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "深惠大区",															//组织名称
	    "orgType": "3",																	//组织类型
	    "timeType": null,	
	    "orgCode": null,
	    "isHead": null,
	    "supOrgType": "2",																//上级组织类型
	    "isReturn": null,
	    "supOrgCode": "DP140160",														//上级组织编码
	    "firstSort": null,
	    "isHidden": null,
	    "reportAnalysisEntities": [
	      {
	        "id": "23a7da29-6dea-11e5-b17b-a0369f659c83",
	        "total": 55,																//总数
	        "percent": 100,																//所占百分比
	        "head": null,
	        "orgName": "深圳沙井路区",													//组织名称
	        "orgType": "3",																//组织类型
	        "orgCode": "DP140677",														//组织编号
	        "isClick": "Y",																//是否能点击
	        "sortNum": "3",																//管理区域排序号
	        "managerName": "赵奇虎",													//负责人
	        "managerCellphone": "13316999917",											//负责人电话
	        "avgOfPeople": 5, 															//人均
	        "hidden":null																//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": null,
	    "totalCount": 9,																//分页总数
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
	
result：reportAnalysisEntities
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空

### 4、返回按钮接口
##### 请求路径：/rs/reportAnalysis/queryReportAnalysisData
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ReportAnalysisAppVo.start    | 整型 | 是 |   分页起始位置     |
| ReportAnalysisAppVo.limit    | 整型 | 是 |   每页条数         |
| ReportAnalysisAppVo.timeType | 字符 | 是 |   时间段(数据字典:TERMS_CODE = 'REPORT_TIMETYPE')    |
| ReportAnalysisAppVo.orgType 	| 字符 | 是 |   部门类型    |
| ReportAnalysisAppVo.firstSort 	| 字符 | 是 |   第一次点击的管理区域序列号    |
| ReportAnalysisAppVo.isReturn 	| 字符 | 是 |   是否返回(1:"返回")    |
| ReportAnalysisAppVo.isHead 	| 字符 | 否 |   是否返回顶部    |
| ReportAnalysisAppVo.orgCode 	| 字符 | 是 |   部门编码    |
| ReportAnalysisAppVo.dataTypeBySort | 字符 | 是 |   统计维度(数据字典:TERMS_CODE = 'REPORT_DATATYPE')     |

	例:		根据isHead属性判断,"Y"为返回顶部
	返回：{"start":0,"limit":15,"timeType":"1","supOrgType":"2",
	"dataTypeBySort":"1-1","firstSort":"1","orgCode":"DP140160","isReturn":"1"},
	返回顶部:{"start":0,"limit":15,"timeType":"1","supOrgType":"1",
	"dataTypeBySort":"1-1","firstSort":"1","orgCode":"DP141106","isReturn":"1","isHead":"Y"}

#####接口返回信息：
	例：
	返回:
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "深圳事业部",													//组织名称
	    "orgType": "2",																//组织类型
	    "timeType": null,
	    "orgCode": null,
	    "isHead": "Y",																//是否返回顶部
	    "supOrgType": "1",
	    "isReturn": null,
	    "supOrgCode": "DP141106",													//上级部门编码
	    "firstSort": null,
	    "isHidden": null,
	    "reportAnalysisEntities": [
	      {
	        "id": "971127f9-6dea-11e5-b17b-a0369f659c83",
	        "total": 115,															//总数
	        "percent": 100,															//所占百分比
	        "head": null,
	        "orgName": "潮汕大区",													//组织名称
	        "orgType": "2",															//组织类型
	        "orgCode": "DP141103",													//组织编码
	        "isClick": "Y",															//是否能点击
	        "sortNum": "2",															//管理区域排序号
	        "managerName": "于守信",												//负责人
	        "managerCellphone": "18666688055",										//负责人电话
	        "avgOfPeople": 4.4,														//人均
	        "hidden":null																//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": null,
	    "totalCount": 6,															//分页总数
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
	返回顶部：
	{
	  "result": {
	    "limit": 0,
	    "start": 0,
	    "orgName": "统计分析",														//组织名称
	    "orgType": "1",																//组织类型
	    "timeType": null,
	    "orgCode": null,
	    "isHead": null,
	    "supOrgType": "0",															//上级组织类型
	    "isReturn": null,
	    "supOrgCode": "DP010000",													//上级部门编码
	    "firstSort": null,
	    "isHidden": "Y",															//是否返回顶部
	    "reportAnalysisEntities": [
	      {
	        "id": "af048960-6dea-11e5-b17b-a0369f659c83",
	        "total": 651,
	        "percent": 100,
	        "head": "Y",
	        "orgName": "深圳事业部",
	        "orgType": "1",
	        "orgCode": "DP141106",
	        "isClick": "Y",
	        "sortNum": "1",
	        "managerName": "丘文通",
	        "managerCellphone": "18017085889",
	        "avgOfPeople": 1.7
	        "hidden":null																//门店,客户经理，路区&&门店走访 --"Y"
	      },
	      ...
	    ],
	    "reportAnalysisAuthEntities": null,
	    "totalCount": 8,
	    "dataTypeBySort": null
	  },
	  "errorMessage": null,
	  "errorCode": "0"
	}
	
result：reportAnalysisEntities
errorCode：字符串类型，调用是否成功，0，调用成功；1：业务异常，2：系统异常
errorMessage：字符串类型，详细的失败信息，errorCode为0时此信息为空


## 十一、团队管理模块
### 相关实体说明
####资源需求实体:ResDemandEntity
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 期望解决时间
	 */
	private Date solvetime;
	/**
	 * 需求资源
	 */
	private String resources;
	/**
	 * 是否与大区总沟通
	 */
	private String isgt;
	/**
	 * 大区总意见
	 * 1:是
	 * 2：否
	 */
	private String regviews;
	/**
	 * 是否答复
	 * 1:未答复
	 * 2:已答复
	 */
	private String isreply;
	/**
	 * 创建结束日期
	 * */
	private Date createEndDate;
	/**
	 * 集团意见
	 */
	private String groupopin;

####每日会议实体:DailyMeetingEntity
	/**
	 * 会议形式
	 * 1:早会
	 * 2:晚会
	 */
	private String hyform;
	/**
	 * 会议类型
	 * 1:现场会议
	 * 2:电话会议
	 */
	private String hyType;
	/**
	 * 会议地址
	 */
	private String hyaddress;
	/**
	 * 会议时间
	 */
	private Date hyDate;
	/**
	 * 参与人员
	 */
	private String cyEmp;
	/**
	 * 会议内容
	 */
	private String hycontext;
	/**
	 * 会议签到ID
	 */
	private String meetingSignId;

####每日问题实体:DailyProblemEntity
	/**
	 * 演练时间
	 */
	private Date yltime;
	/**
	 * 演练地址
	 */
	private String yladdress;
	/**
	 * 参与人员
	 */
	private String cyEmp;
	/**
	 * 演练方式
	 * 1:现场演练
	 * 2:电话演练
	 */
	private String ylway;
	/**
	 * 问题描述
	 */
	private String wtdescribe;
	/**
	 * 举措描述
	 */
	private String jcdescribe;
	/**
	 * 会议签到ID
	 */
	private String meetingSignId;
	
####每日培训实体:DailyTrainEntity
	/**
	 * 培训类型
	 * 1.业务考试
	 * 2.销售技巧
	 * 3.模拟演练
	 */
	private String pxType;
	/**
	 * 会议地址
	 */
	private String hyaddress;
	/**
	 * 会议时间
	 */
	private Date hyDate;
	/**
	 * 参与人员
	 */
	private String cyEmp;
	/**
	 * 培训内容
	 */
	private String pxcontext;
	/**
	 * 会议签到ID
	 */
	private String meetingSignId;
	
#### 每日查询实体:DailyQueryEntity
	/**
	 * 排序规则
	 */
	private String sortType;
	/**
	 * 创建时间查询
	 */
	private Date queryCreateTime;

####附件实体:AttachmentEntity
	/**
	 *附件所关联数据的id
	 */
	private String fileId;
	/**
	 * 附件描述
	 */
	private String descrip;
	/**
	 * 附件名称
	 */
	private String fileName;
	/**
	 * 附件上传地址
	 */
	private String fileUrl;
	/**
	 * 附件大小
	 */
	private int fileSize;
	/**
	 * 附件顺序
	 */
	private int reorder;
	/**
	 * 附件后缀
	 */
	private String postfix;
	/**
	 * 图片内容
	 */
	private byte[] imgContent;

####资源需求实体VO:ResDemandAppVo
	/**
	 * 资源需求实体
	 */
	private ResDemandEntity resDemandEntity;
	/**
	 * 资源需求集合
	 */
	private List<ResDemandEntity> resDemandList;
	/**
     * 分布起始位置
     */
	private int start;
    /**
     * 分页数据总长度
     */
	private Long totalCount;
	/**
     * 每页数据量
     */
	private int limit;
	/**
     * ID集合
     */
	private List<String> ids;

####每日会议、问题、培训实体DailyAppVo
	/**
	 * 每日会议实体
	 */
	private DailyMeetingEntity meetingEntity;
	/**
	 * 每日问题实体
	 */
	private DailyProblemEntity problemEntity;
	/**
	 * 每日培训实体
	 */
	private DailyTrainEntity trainEntity;
	/**
	 * 每日会议实体集合
	 */
	private List<DailyMeetingEntity> meetingList;
	/**
	 * 每日问题实体集合
	 */
	private List<DailyProblemEntity> problemList;
	/**
	 * 每日培训实体集合
	 */
	private List<DailyTrainEntity> trainList;
	/**
     * 分布起始位置
     */
	private int start;
    /**
     * 分页数据总长度
     */
	private Long totalCount;
	/**
     * 每页数据量
     */
	private int limit;
	/**
     * ID集合
     */
	private List<String> ids;
	/**
	 * 图片内容
	 */
	private List<AttachmentEntity> attachmentEntityList;
	/**
	 * 参与人员集合
	 * */
	private List<EmployeeEntity> empList;
	/**
	 * 查询参数
	 */
	private String selectorParam;
	
	/**
	 * 查询结果集
	 */
	private List<String> empAndDeptNames;

### 1、分页查询资源需求信息
##### 请求路径：/rs/resdemand/queryResDemandInfo
##### 接口请求方式：POST
##### 传入参数： 
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ResDemandAppVo.start     | 整形 | 是 |   分页起始位置     |
| ResDemandAppVo.limit        | 整形 |   是   |   每页条数   |
| ResDemandAppVo.resDemandEntity.solvetime    | 时间 |   否   |   期望解决时间   |
| ResDemandAppVo.resDemandEntity.resources   | 字符  |   否   |   需求资源   |
| ResDemandAppVo.resDemandEntity.createDate    | 时间 |   否   |   开始时间  |
| ResDemandAppVo.resDemandEntity.createEndDate    | 时间 |   否   |   结束时间   |

	例：{"resDemandEntity":{"resources":"43"},"limit":15,"start":0}

#####接口返回信息：
ResDemandAppVo.resDemandList：资源需求信息集合
ResDemandAppVo.totalCount：分页所需的数据总长度，长整形

### 2、新增资源需求信息
##### 请求路径：/rs/resdemand/addResDemand
##### 接口请求方式：POST
##### 传入参数：
ResDemandAppVo.resDemandEntity：资源需求实体

	例：{"resDemandEntity":{"resources": "资源需求22","solvetime": "2015-09-10","isgt": "1","regviews": "323","isreply": "1"}}

#####接口返回信息：
响应基础实体，无业务信息

### 3、根据资源需求ID集合删除资源需求
##### 请求路径：/rs/resdemand/deleteResDemand
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ResDemandAppVo.ids     | 数组 | 是 |   主键ID数组     |

	例：{"ids":["06b96e32-eede-4f74-bbdf-57fd60f4ca97"]}

#####接口返回信息：
响应基础实体，无业务信息

### 4、根据id查询资源需求信息
##### 请求路径：/rs/resdemand/queryResDemandInfoById
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ResDemandAppVo.resDemandEntity.id     | 数组 | 是 |   客户主键ID数组     |

	例：{"resDemandEntity":{"id":"32bae1e1-576f-11e5-a932-005056af54e6"}}

#####接口返回信息：
ResDemandAppVo.resDemandEntity：资源需求信息

### 5、根据资源需求实体修改资源需求
##### 请求路径：/rs/resdemand/updateResDemandInfoById
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ResDemandAppVo.resDemandEntity.id        | 字符 |   是   |   主键ID   |
| ResDemandAppVo.resDemandEntity.solvetime    | 时间 |   是   |   期望解决时间   |
| ResDemandAppVo.resDemandEntity.resources   | 字符  |   是   |   需求资源   |
| ResDemandAppVo.resDemandEntity.isgt    | 字符 |   是   |   是否与大区总沟通  |
| ResDemandAppVo.resDemandEntity.regviews    | 字符 |   否   |   大区总意见   |

	例：{"resDemandEntity": {
            "resources": "资源需求测试",
            "solvetime": 1441857175000,
            "isgt": "1",
            "regviews": "大区总意见",
            "id": "e0c3afc6-576e-11e5-a932-005056af54e6"

        }}

#####接口返回信息：
响应基础实体，无业务信息

### 6、根据资源需求实体修改是否已答复
##### 请求路径：/rs/resdemand/updateResDemandInfoByIsreply
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| ResDemandAppVo.resDemandEntity.id        | 字符 |   是   |   主键ID   |
| ResDemandAppVo.resDemandEntity.groupopin    | 字符 |   否   |   集团意见   |

	例：{"resDemandEntity": {
            "groupopin": "集团意见",
            "id": "e0c3afc6-576e-11e5-a932-005056af54e6"

        }}
#####接口返回信息：
响应基础实体，无业务信息

### 7、分页查询每日会议信息
##### 请求路径：/rs/daily/queryDailyMeetingInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.start     | 整形 | 是 |   分页起始位置     |
| DailyAppVo.limit        | 整形 |   是   |   每页条数   |

	例：{"meetingEntity":{},"limit":15,"start":0}

#####接口返回信息：
DailyAppVo.meetingList：每日会议信息集合
DailyAppVo.totalCount：分页所需的数据总长度，长整形

### 8、新增每日会议信息
##### 请求路径：/rs/daily/addDailyMeeting
##### 接口请求方式：POST
##### 传入参数：
DailyAppVo.meetingEntity：每日会议实体

	例：{"meetingEntity":{"meetingSignId": "06b96e32-eede-4f74-bbdf-57fd60f4ca97",
                "hyform": "1",
                "hyaddress": "华翔路22",
                "hyType": "2",
                "hyDate": 1441943026000,
                "cyEmp": "张三,李四,王五",
                "hycontext": "java基础"}}

#####接口返回信息：
响应基础实体，无业务信息

### 9、根据每日会议ID集合删除每日会议信息
##### 请求路径：/rs/daily/deleteDailyMeeting
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.ids     | 数组 | 是 |   主键ID数组     |

	例：{"ids":["06b96e32-eede-4f74-bbdf-57fd60f4ca97"]}

#####接口返回信息：
响应基础实体，无业务信息

### 10、根据id查询每日会议信息
##### 请求路径：/rs/daily/queryDailyMeetingInfoById
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.meetingEntity.id     | 数组 | 是 |   主键ID数组     |

	例：{"meetingEntity":{"id":"32bae1e1-576f-11e5-a932-005056af54e6"}}

#####接口返回信息：
DailyAppVo.meetingList：每日会议信息集合

### 11、分页查询每日问题信息
##### 请求路径：/rs/daily/queryDailyProblemInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.start     | 整形 | 是 |   分页起始位置     |
| DailyAppVo.limit        | 整形 |   是   |   每页条数   |

	例：{"problemEntity":{},"limit":15,"start":0}

#####接口返回信息：
DailyAppVo.problemList：每日问题信息集合
DailyAppVo.totalCount：分页所需的数据总长度，长整形

### 12、新增每日问题信息
##### 请求路径：/rs/daily/addDailyProblem
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.problemEntity     | 实体 | 是 |   每日问题实体   |

	例：{"problemEntity":{
				"meetingSignId": "06b96e32-eede-4f74-bbdf-57fd60f4ca97",
				"yladdress":"演练地址",
				"cyEmp":"张三、李四",
                "yltime": 1441950744000,
                "wtdescribe": "问题描述接口测试",
                "ylway": "1",
                "jcdescribe": "举措描述"
            }}

#####接口返回信息：
响应基础实体，无业务信息

### 13、根据每日问题ID集合删除每日问题信息
##### 请求路径：/rs/daily/deleteDailyProblem
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.ids     | 数组 | 是 |   主键ID数组     |

	例：{"ids":["06b96e32-eede-4f74-bbdf-57fd60f4ca97"]}

#####接口返回信息：
响应基础实体，无业务信息

### 14、根据id查询每日问题信息
##### 请求路径：/rs/daily/queryDailyProblemInfoById
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.problemEntity.id     | 数组 | 是 |   主键ID数组     |

	例：{"problemEntity":{"id":"32bae1e1-576f-11e5-a932-005056af54e6"}}

#####接口返回信息：
DailyAppVo.problemList：每日问题信息集合

### 15、分页查询每日培训信息
##### 请求路径：/rs/daily/queryDailyTrainInfo
##### 接口请求方式：POST
##### 传入参数：
| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.start     | 整形 | 是 |   分页起始位置     |
| DailyAppVo.limit        | 整形 |   是   |   每页条数   |

	例：{"trainEntity":{},"limit":15,"start":0}

#####接口返回信息：
DailyAppVo.trainList：每日培训信息集合
DailyAppVo.totalCount：分页所需的数据总长度，长整形

### 16、新增每日培训信息
##### 请求路径：/rs/daily/addDailyTrain
##### 接口请求方式：POST
##### 传入参数：
DailyAppVo.trainEntity：每日培训实体

	例：{"trainEntity":{"meetingSignId": "06b96e32-eede-4f74-bbdf-57fd60f4ca97",
                "hyaddress": "华翔路2239号",
                "hyDate": 1441951757000,
                "pxType": "1",
                "cyEmp": "张灿,李四,王五",
                "pxcontext": "培训23"}}

#####接口返回信息：
响应基础实体，无业务信息

### 17、根据每日培训ID集合删除每日培训信息
##### 请求路径：/rs/daily/deleteDailyTrain
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.ids     | 数组 | 是 |   主键ID数组     |

	例：{"ids":["06b96e32-eede-4f74-bbdf-57fd60f4ca97"]}

#####接口返回信息：
响应基础实体，无业务信息

### 18、根据id查询每日培训信息
##### 请求路径：/rs/daily/queryDailyTrainInfoById
##### 接口请求方式：POST
##### 传入参数：

| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.trainEntity.id     | 数组 | 是 |   主键ID数组     |

	例：{"trainEntity":{"id":"32bae1e1-576f-11e5-a932-005056af54e6"}}

#####接口返回信息：
DailyAppVo.trainList：每日培训信息集合

### 19、根据当前登录人查询培训、会议的参与人员
##### 请求路径：/rs/daily/queryEmployeeInfoById
##### 接口请求方式：POST
##### 传入参数：{}
	例：{}
#####接口返回信息：
DailyAppVo.empList：每日培训信息集合

### 20、团队管理搜索接口
##### 请求路径：/rs/daily/queryEmpAndDeptNames
##### 接口请求方式：POST
##### 传入参数：


| 参数    |   类型   |是否必填   |  参数说明  |
| --------   | :-----: | :-----:  | :----:  |
| DailyAppVo.selectorParam     | 字符串 | 是 |   搜索内容   |

	例：{"selectorParam":"刘"}

#####接口返回信息：
DailyAppVo.empAndDeptNames：人员部门信息集合