package cn.edu.cdu.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		String registSql = "insert into company values(?,?,?,?,?,?,?,?)";
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
				+ "password=?,contacts = ?,phone = ?,address = ?,profile = ? where username = ?";
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
			ps.setString(9, company.getUsername());
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

	@Override
	/**
	 * 
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

	@Override
	public List<Company> queryCompanys(String condition) {
		return null;
	}

	@Override
	public boolean checkCompany(Company company) {
		return false;
	}

}
