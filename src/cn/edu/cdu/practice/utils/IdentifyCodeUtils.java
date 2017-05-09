package cn.edu.cdu.practice.utils;

import java.util.Random;

/**
  * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
  * @FileName TestDb.java
  * @version 1.0
  * @Description: 随机生成验证码工具类
  * @Author 陈天雄
  * @Date： 2017-4-16:下午3:48:53
  * Modification User： 程序修改时由修改人员编写
  * Modification Date： 程序修改时间
  */
public class IdentifyCodeUtils {
	/**
	 * <p>Title: getCode</p>
	 * <p>Description: </p>
	 * @return 返回随机生成的验证码
	 */
	public static String getCode() {
		String codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		char[] codeChar = codes.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < 4 ; i++) {
			char c = codeChar[random.nextInt(codeChar.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
