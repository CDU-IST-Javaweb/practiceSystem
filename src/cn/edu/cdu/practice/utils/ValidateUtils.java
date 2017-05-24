
package cn.edu.cdu.practice.utils;
/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName ValidateUtils.java
 * @version 1.0
 * @Description: 防止SQL注入工具类
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */

public class ValidateUtils {
	/**
	 * <p>Title: validate</p>
	 * <p>Description: 防止SQL注入</p>
	 * @param str 要过滤的字符串，一般为页面获取的参数值
	 * @return 如果有非法字符，则返回true,没有非法字符，则显示false
	 */
	public static boolean validate(String pageParameter) {
		boolean flag = false ;
		String inj_str = "|insert|select|delete|update|drop|alter|count|"
				+ "declare|or";
		String pageParameter2 = pageParameter.toLowerCase();
		String inj_stra[] = inj_str.split("\\|"); 
		for(String i : inj_stra) {
			if (pageParameter2.indexOf(i) > 0) {
				flag = true ;
			} 
		}
		return flag ;
	}
}
