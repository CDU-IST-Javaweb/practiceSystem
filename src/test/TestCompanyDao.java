package test;

import java.util.List;

import org.junit.Test;

import cn.edu.cdu.practice.dao.CompanyDao;
import cn.edu.cdu.practice.dao.impl.CompanyDaoImpl;
import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.utils.EmailUtils;
import cn.edu.cdu.practice.utils.MdPwdUtil;

/**
 * <p>Title: TestCompanyDao</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月18日下午4:45:29
 * @version 1.0
 */
public class TestCompanyDao {
		@Test
		public void test() {
			CompanyDao companyDao = new CompanyDaoImpl();
			/*Company company = new Company();
			company.setCompanyName("致远科技");
			company.setUsername("sayHello");
			company.setMailbox("cda@163.com");
			company.setPassword(MdPwdUtil.MD5Password("nimei683"));
			company.setAddress("成都市高新区");
			company.setPhone("15842363650");
			company.setContacts("陈先生");
			company.setProfile("成都市专业培训机构");
			
			if (companyDao.updateCompanyInfo(company)) {
				System.out.println("success");
			} else
				System.out.println("failed");*/
//			companyDao.updateCompanyPassword("sayHello", MdPwdUtil.MD5Password("123456"));
			/*List<Company> list = companyDao.queryViryFyCompanys();
			System.out.println(list.get(0).getAuditDate());*/
			/*if (companyDao.deleteCompany("wanwan")) {
				System.out.println("success");
			}*/
			System.out.println(MdPwdUtil.MD5Password("f8ce70d1684ff24a50c7915ace10a0ee"));
		}
		
}
