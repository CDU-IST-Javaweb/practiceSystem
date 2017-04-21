package cn.edu.cdu.practice.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.cdu.practice.dao.CompanyDao;
import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.utils.DbUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName CompanyDaoImpl.java
 * @version 1.0
 * @Description: 企业信息管理操作
 * @Author 陈天雄
 * @Date： 2017-4-18:下午3:39:54
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class CompanyDaoImpl implements CompanyDao{
	/**
	 *	企业注册
	 */
	public boolean registerCompanyInfo(Company company) {
		//获取数据库连接
		Connection connection = DbUtils.getConnection();
		String registSql = "insert into company values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//设置手动提交事务
			 ps = connection.prepareStatement(registSql);
			 ps.setString(1, company.getUsername());
			 ps.setString(2, company.getCompanyName());
			 ps.setString(3, company.getMailbox());
			 ps.setString(4, company.getPassword());
			 ps.setString(5, company.getContacts());
			 ps.setString(6, company.getPhone());
			 ps.setString(7, company.getAddress());
			 ps.setString(8, company.getProfile());
			 ps.setDate(9, company.getAuditDate());
			 ps.execute();
			 connection.commit();//提交事务
			 return true ;
		} catch (Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			//每次操作之后必须关闭连接
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * 更新企业信息
	 */
	public boolean updateCompanyInfo(Company company) {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String updateSql = "update company set username = ?,company_name = ?,mailbox = ?,"
				+ "password=?,contacts = ?,phone = ?,"
				+ "address = ?,profile = ?,auditDate = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, company.getUsername());
			ps.setString(2, company.getCompanyName());
			ps.setString(3, company.getMailbox());
			ps.setString(4, company.getPassword());
			ps.setString(5, company.getContacts());
			ps.setString(6, company.getPhone());
			ps.setString(7, company.getAddress());
			ps.setString(8, company.getProfile());
			ps.setDate(9, company.getAuditDate());
			ps.setString(10, company.getUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * <p>Title: queryCompanyInfo</p>
	 * <p>Description: 查询企业信息</p>
	 * @param companyUserName 用户名
	 * @return Company实体
	 */
	public Company queryCompanyInfo(String companyUserName) {
		return null;
	}

	/**
	 * 更新密码操作
	 */
	public boolean updateCompanyPassword(String companyUserName, String newPassword) {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String updateSql = "update company set password = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			//获得PreparedStatement对象
			ps = connection.prepareStatement(updateSql);
			//动态设置参数
			ps.setString(1, newPassword);
			ps.setString(2, companyUserName);
			//执行语句
			ps.execute();
			//提交事务，如果事务不提交，将不会发出SQL语句
			connection.commit();
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
		return false;
	}

	/**
	 * <p>Title: checkCompany</p>
	 * <p>Description:该接口方法主要处理管理员审核企业信息</p>
	 * @param company Company实体类引用对象
	 * @return 返回一个检查完的标志,审核通过返回true,审核不通过返回false
	 */
	public boolean checkCompany(Company company) {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String updateSql = "update company set auditDate = ? where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(updateSql);
			ps.setDate(1, company.getAuditDate());
			ps.setString(2, company.getUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false ;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * 查询已经审核的公司，判断条件是审核日期 是否为空
	 */
	public List<Company> queryViryFyCompanys() {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String querySql = "select * from company where audit_date IS NOT NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;
		try {
			//获得Statement对象
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * 查询未审核的公司，判断条件是审核日期 是否为空
	 */
	public List<Company> queryNotVirefyCompanys() {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String querySql = "select * from company where audit_date IS  NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;;
		try {
			//获得PreparedStatement对象
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * 查询所有公司
	 */
	public List<Company> queryAllCompanys() {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String querySql = "select * from company ";
		Statement statement = null ;
		ResultSet resultSet = null ;
		Company company = null ;
		List<Company> list = new ArrayList<Company>() ;
		try {
			//获得PreparedStatement对象
			statement = connection.createStatement();
			resultSet = statement.executeQuery(querySql);
			while (resultSet.next()) {
				company = new Company();
				company.setUsername(resultSet.getString("username"));
				company.setCompanyName(resultSet.getString("company_name"));
				company.setMailbox(resultSet.getString("mailbox"));
				company.setPassword(resultSet.getString("password"));
				company.setContacts(resultSet.getString("contacts"));
				company.setPhone(resultSet.getString("phone"));
				company.setAddress(resultSet.getString("address"));
				company.setProfile(resultSet.getString("profile"));
				company.setAuditDate((resultSet.getDate("audit_date")));
				list.add(company);
			}
			return list ;
		} catch(Exception e) {
			e.printStackTrace();
			return null ;
		} finally {
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

	/**
	 * 删除公司
	 */
	public boolean deleteCompany(String companyUsername) {
		Connection connection = DbUtils.getConnection();
		//sql拼接更新语句，防止sql注入
		String updateSql = "delete from company where username = ?";
		PreparedStatement ps = null ;
		try {
			connection.setAutoCommit(false);
			//获得PreparedStatement对象
			ps = connection.prepareStatement(updateSql);
			//动态设置参数
			ps.setString(1, companyUsername);
			//执行语句
			ps.execute();
			//提交事务，如果事务不提交，将不会发出SQL语句
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

}
