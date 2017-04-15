package cn.edu.cdu.practice.dao;

import java.util.List;

import cn.edu.cdu.practice.utils.Company;

/**
 * <p>Title: Dao</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月14日下午10:35:33
 * @version 1.0
 */
public interface UserDao {
	//企业注册
	boolean registerCompanyInfo(Company company);
	
	//企业修改注册页面信息
	boolean updateCompanyInfo(Company company);
	
	//企业查询信息
	Company queryCompanyInfo(String userName);
	
	//修改密码
	boolean updatePassword(String userName,String newPassword);
	
	//根据审核条件筛选企业
	List<Company> queryCompanys(String condition);
	
	//管理员审核企业信息，通过，返回true，失败，返回false
	boolean checkCompany(Company company);
}
