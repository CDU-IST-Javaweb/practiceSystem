
package cn.edu.cdu.practice.dao;

import cn.edu.cdu.practice.model.SystemParameter;

/**
 * <p>Title: SystemDao</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月15日上午11:35:49
 * @version 1.0
 */
public interface SystemParameterDao {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: 设置系统参数</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean setSystemConfig(SystemParameter systemConfig);
}
