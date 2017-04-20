package test;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;

import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.utils.MdPwdUtil;

/**
 * <p>Title: TestSystem_Config</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月19日上午11:56:24
 * @version 1.0
 */
public class TestSystem_Config {
	@Test
	public void test() {
		SystemParameter sys = new SystemParameter();
		Date date = new Date();
		System.out.println((Timestamp) date);
//		sys.setAdminUsername("cdu");
//		sys.setAdminPassword(MdPwdUtil.MD5Password("xg2013"));
	}
}
