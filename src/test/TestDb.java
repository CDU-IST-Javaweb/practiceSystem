package test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.model.Student;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.service.SystemParameterService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.service.impl.NoticeServiceImpl;
import cn.edu.cdu.practice.service.impl.SystemParameterServiceImpl;
import cn.edu.cdu.practice.utils.DateUtil;
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
		/*Connection connection = DbUtils.getConnection();
		Log4jUtils.info("success"+connection);*/
		/*NoticeService noticeService = new NoticeServiceImpl();
		int list = noticeService.countNoAuditTimeNotice();
		System.out.println(list);*/
//		EmailUtils.sendMail("18482003417@163.com", "Nimei025", "betteryangyh@163.com", 1);
		SystemParameterService sys = new SystemParameterServiceImpl();
		/*SystemParameter systemConfig = new SystemParameter();
		systemConfig.setAdminUsername("3");
		systemConfig.setAdminPassword("3");
		systemConfig.setInvitationCode("147");
		systemConfig.setStudentSelMaxnum(6);
		if (sys.setOrUpdateSystemConfig(systemConfig, "2")) {
			System.out.println("true");
		}*/
		/*SystemParameter systemParameter = sys.queryByAccount("3");
		System.out.println(systemParameter.getInvitationCode());*/
		System.out.println(DateUtil.splitStringToDate("2015-03-12"));
	}
	/**
	 * 
	 * <p>Title: testCode</p>
	 * <p>Description: 测试验证码的生成</p>
	 */
	/*@Test
	public void testCode() {
		System.out.println(IdentifyCodeUtils.getCode());
	}
	
	@Test
	public void testMail() {
		
	}
	*/

}
