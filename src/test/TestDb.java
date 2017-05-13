package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
import cn.edu.cdu.practice.utils.ExcelInUtil;
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
	 * @throws FileNotFoundException 
	 */
	@Test
	public void test() throws Exception {
		InputStream in = new FileInputStream("src/123.xlsx");
		List<Student> list = ExcelInUtil.importStudentExcel("src/123.xlsx");
		System.out.println(list.size());
		for(Student student : list) {
			System.out.println(student.getName() + " "+student.getGender() + " "+student.getNo() + " "
					+student.getMailbox()+" "+student.getLevel() +" "+student.getClass_()+" "+student.getProfessional()+
					student.getGrade());
		}
	}
}
