
package cn.edu.cdu.practice.utils;

import org.apache.log4j.Logger;

/** 
* @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName Log4jUtil.java
 * @version 1.0
 * @Description: 日志操作
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
*/
public class Log4jUtils {
	private static Logger log = Logger.getLogger(Log4jUtils.class);
	
	public static void debug(String message) {
		log.debug(message);
	}
	
	public static void error(String message) {
		log.error(message);
	}
	public static void info(String message) {
		log.info(message);
	}
	public static void warn(String message) {
		log.warn(message);
	}
	public static void fatal(String message) {
		log.fatal(message);
	}
}
