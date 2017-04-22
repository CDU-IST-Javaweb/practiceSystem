package cn.edu.cdu.practice.service;
import cn.edu.cdu.practice.model.SystemParameter;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName CompanyService.java
 * @version 1.0
 * @Description: 企业信息管理操作
 * @Author 陈天雄
 * @Date： 2017-4-22:下午5:05:01
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public interface SystemParameterService {
	/**
	 * <p>Title: setSystemConfig</p>
	 * <p>Description: 设置系统参数</p>
	 * @param systemConfig 系统设置实体类的引用
	 * @return 设置成功返回true，设置失败返回false
	 */
	boolean setSystemConfig(SystemParameter systemConfig);
}
