package test;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

import cn.edu.cdu.practice.dao.impl.StudentDaoImpl;
import cn.edu.cdu.practice.model.Student;
import cn.edu.cdu.practice.utils.DbUtils;
import cn.edu.cdu.practice.utils.EmailUtils;
import cn.edu.cdu.practice.utils.IdentifyCodeUtils;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName TestDb.java
 * @version 1.0
 * @Description: 测试数据库操作
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class TestDb {
	/**
	 * 
	 * <p>Title: test</p>
	 * <p>Description: 测试数据库连接成功以及日志应用成功</p>
	 */
	@Test
	public void test() {
		Connection connection = DbUtils.getConnection();
		Log4jUtils.info("success");
	}
	/**
	 * 
	 * <p>Title: testCode</p>
	 * <p>Description: 测试验证码的生成</p>
	 */
	@Test
	public void testCode() {
		System.out.println(IdentifyCodeUtils.getCode());
	}
	
	@Test
	public void testMail() {
		
	}
	

}
