
package cn.edu.cdu.practice.dao;

import java.util.List;

import cn.edu.cdu.practice.utils.Company;

/**
 * <p>Title: CompanyDao</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月15日上午11:14:51
 * @version 1.0
 */
public interface CompanyDao {
	/**
	 * <p>Title: registerCompanyInfo</p>
	 * <p>Description: 该接口方法主要处理公司注册</p>
	 * @param company Company实体类的对象引用
	 * @return 返回注册成功与否的标志，成功返回true，失败返回false
	 */
	boolean registerCompanyInfo(Company company);
	
	/**
	 * <p>Title: updateCompanyInfo</p>
	 * <p>Description:该接口方法主要处理企业修改注册页面信息 </p>
	 * @param company Company实体类的对象引用
	 * @return 返回修改成功与否的标志，成功返回true，失败返回false
	 */
	boolean updateCompanyInfo(Company company);
	
	/**
	 * <p>Title: queryCompanyInfo</p>
	 * <p>Description: 该接口方法主要处理企业查询信息 </p>
	 * @param companyName 企业注册的用户名
	 * @return 根据传入用户名，返回Company实体类
	 */
	Company queryCompanyInfo(String companyName);
	
	/**
	 * <p>Title: updatePassword</p>
	 * <p>Description: 该接口方法主要处理修改密码</p>
	 * @param companyName 企业注册的用户名
	 * @param newPassword 企业登录密码
	 * @return 返回修改成功与否的标志，成功返回true，失败返回false
	 */
	boolean updateCompanyPassword(String companyName,String newPassword);
	
	/**
	 * <p>Title: queryCompanys</p>
	 * <p>Description: 该接口方法主要处理根据条件,查询企业</p>
	 * @param condition 查询条件
	 * @return 返回List<Company>集合
	 */
	List<Company> queryCompanys(String condition);
	
	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:该接口方法主要处理管理员审核企业信息</p>
	 * @param company Company实体类引用对象
	 * @return 返回一个检查完的标志,审核通过返回true,审核不通过返回false
	 */
	boolean checkCompany(Company company);
}
