package com.hoau.crm.module.common.server.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobileUtil {
	/**  
     * 产生一个随机的字符串  
     *   
     * @param 字符串长度  
     * @return  
     */  
    public static String getRandomString(int length) {   
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";   
        Random random = new Random();   
        StringBuffer sb = new StringBuffer();   
        for (int i = 0; i < length; i++) {   
            int number = random.nextInt(base.length());   
            sb.append(base.charAt(number));   
        }   
        return sb.toString();   
    }
    
    public static boolean isMobileNO(String mobile){
    	Pattern p = Pattern.compile("^(1)\\d{10}$");
    	Matcher m = p.matcher(mobile);  
    	return m.matches();
    }
    public static void main(String[] args) {   
        System.out.println(isMobileNO("18356086657"));
    }   

}
