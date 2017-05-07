
package cn.edu.cdu.practice.dao;

import cn.edu.cdu.practice.model.SystemParameter;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName SystemParameterDao.java
 * @version 1.0
 * @Description: Dao层系统参数管理操作接口
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public interface SystemParameterDao {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: 设置系统参数</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 新增成功返回true，设置失败返回false
	 */
	boolean setSystemConfig(SystemParameter systemConfig);
	
	/**
	 * <p>Title: updateSystemConfig</p>
	 * <p>Description: 更新系统参数表</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean updateSystemConfig(SystemParameter systemConfig,String username);
	/**
	 * <p>Title: queryByAccount</p>
	 * <p>Description: 根据用户名查询</p>
	 * @param accountName 系统参数表的用户名
	 * @return 系统设置实体类的引用
	 */
	SystemParameter queryByAccount(String accountName);
}
