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
	
	/*
	@Test
	public void testStudentInsert(){
		Student stu=new Student();
		stu.setNo("201401");
		stu.setName("张山");
		stu.setGrade(2014);
		stu.setLevel("见习");
		stu.setProfessional("软件工程");
		stu.setGender("男");
		stu.setClass_("3班");
		stu.setPassword("123");
		stu.setMailbox("123@qq.com");
		stu.setSubjectBackground(null);
		stu.setLearningExperience(null);
		stu.setResearchDirection(null);
		StudentDaoImpl stuDI=new StudentDaoImpl();
		stuDI.insert(stu);
	}
	*/
	@Test
	public void testQuery(){
		List<Student> list=null;
		//Student student=new Student();
		StudentDaoImpl stuDI=new StudentDaoImpl();
		//list=stuDI.findByCompany("中云测");
		list=stuDI.findByMajor("自动化(本)");
		//student=stuDI.findById("201401");
		
		if(list.size()>0)
			for(int i=0;i<list.size();i++){
				System.out.println("找到的学生有：\n"+list.get(i).getName());
			}
		else
			System.out.println("没找到指定学生!");		
			
		/*
		if(student!=null){
			System.out.println("找到的学生有：\n"+student.getName());
		}else
			System.out.println("没找到指定学生!");
	    */
	}
}
