package com.hoau.crm.module.common.server.util;

import java.util.Random;

/**
 * 验证码生成器
 *
 * @author 蒋落琛
 * @date 2015-12-9
 */
public class ValidateCodeUtil {
	
	public static final String[] nums = { "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9" };

	public static String genCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int index = getRandomNum();
			sb.append(nums[index]);
		}
		return sb.toString();
	}

	public static int getRandomNum() {
		Random r = new Random();
		return r.nextInt(10);
	}

}
