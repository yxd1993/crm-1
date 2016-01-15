package com.hoau.crm.module.bse.api.shared.exception;

import com.hoau.hbdp.framework.exception.BusinessException;

/**
 * 新系统。公司业务发展过快，业务专业人员沉淀不足，
 * 部分业务管理逻辑不完全清晰或缺乏积累，
 * 导致业务计划及预算的编制模型与标准缺失或不清晰，
 * 在计划预算编制过程中容易出现预算缺失（漏做预算）
 * 及预算宽松的问题。从而进一步导致年度预算考虑不足
 * 或不合理状况，影响全面预算理念在德邦的推广应用。
 * 预算编制过程难以控制。当前德邦物流多数业务计划
 * 的编制人员经验不足，对业务本身的理解不足，又缺
 * 乏计划预算编制的历史积累，导致计划预算编制返工
 * 次数过高。另外，计划预算编制过程中业务部门之间
 * 的数据交互不顺畅，各部门预算版本过多，数据及版
 * 本应用的一致性难以保证，导致完整预算编制时间过
 * 长，且数据容易出错。预算编制完成后，预算过程控
 * 制能力较弱，主要原因在于预测及预算分析能力不足。
 * 在实际业务管理中缺乏合理预测及基于预测的资源配
 * 置方法导致资源高配是影响德邦物流成本的重要因素。
 * 另外，基于计划预算编制的数据分析能力较弱、管理
 * 报表与计划预算衔接不紧密等问题使得在预算改善及
 * 责任落实方面存在一定难度。
 * */
/**
 * 用来处理“用户信息”业务操作异常类类：SUC-226
 * <p style="display:none">
 * modifyRecord
 * </p>
 * <p style="display:none">
 * version:V1.0,author:100847-foss-GaoPeng,date:2012-11-7 下午7:28:35
 * </p>
 * 
 * @author 100847-foss-GaoPeng
 * @date 2012-11-7 下午7:28:35
 * @since
 * @version
 */
public class UserException extends BusinessException {

	private static final long serialVersionUID = -1982586715098015347L;
	
	/**
	 * 用户角色不能为空
	 */
	public static final String USER_ROLE_NULL = "com.crm.user.UserRoleNullException";
	
	/**
	 * 用户ID不能为空
	 */
	public static final String USER_ID_NULL = "com.crm.user.UserIdNullException";
	
	/**
	 * 分页参数为空
	 */
	public static final String RB_NULL = "crm.bse.user.RbNullException";

	/**
	 * 登录名不能为空
	 */
	public static final String LOGINNAME_NULL = "crm.bse.user.LoginNameNullException";

	public UserException(String code) {
		super();
		super.errCode = code;
	}

	public UserException(String code, String msg, Throwable cause) {
		super(code, msg, cause);
	}

	public UserException(String code, String msg) {
		super(code, msg);
	}
}
