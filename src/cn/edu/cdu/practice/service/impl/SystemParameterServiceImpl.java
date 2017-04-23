package cn.edu.cdu.practice.service.impl;
import cn.edu.cdu.practice.dao.SystemParameterDao;
import cn.edu.cdu.practice.dao.impl.SystemParameterDaoImpl;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.service.SystemParameterService;
import cn.edu.cdu.practice.utils.Log4jUtils;

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
public class SystemParameterServiceImpl implements SystemParameterService {
	private SystemParameterDao systemParameterDao = new SystemParameterDaoImpl();
	/**
	 * 设置系统参数
	 */
	public boolean setSystemConfig(SystemParameter systemConfig) {
		try {
			if (systemConfig != null ) {
				return this.systemParameterDao.setSystemConfig(systemConfig);
			}
			return false ;
			} catch(Exception exception) {
				Log4jUtils.info(exception.getMessage());
				return false;
			}
	}

}
