package cn.edu.cdu.practice.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.cdu.practice.dao.SystemParameterDao;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.utils.DbUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName SystemParameterDaoImpl.java
 * @version 1.0
 * @Description: 系统设置
 * @Author 陈天雄
 * @Date： 2017-4-18:下午3:39:54
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class SystemParameterDaoImpl implements SystemParameterDao {

	/**
	 * 设置系统参数
	 */
	public boolean setSystemConfig(SystemParameter systemConfig) {
		//连接数据库
		Connection connection = DbUtils.getConnection();
		String configSql = "insert into system_parameter values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			//注入参数
			ps.setString(1, systemConfig.getAdminUsername());
			ps.setString(2, systemConfig.getAdminPassword());
			ps.setString(3, systemConfig.getInvitationCode());
			ps.setDate(4, systemConfig.getReleaseProjectStartDate());
			ps.setDate(5, systemConfig.getReleaseProjectEndDate());
			ps.setDate(6, systemConfig.getStudentSelStartDate());
			ps.setDate(7, systemConfig.getStudentSelEndDate());
			ps.setInt(8, systemConfig.getStudentSelMaxnum());
			//执行语句
			ps.execute();
			//提交事务
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	public boolean updateSystemConfig(SystemParameter systemConfig) {
		Connection connection = DbUtils.getConnection();
		String configSql = "update system_parameter set adminpassword = ?,invitationcode=?,releaseprojectStartDate=?,"
				+ "releaseprojectendDate=?,studentselstartdate=?,studentselenddate=?,"
				+ "studentselmaxnum=? where adminusername = ?";
		PreparedStatement ps = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			ps.setString(1, systemConfig.getAdminPassword());
			ps.setString(2, systemConfig.getInvitationCode());
			ps.setDate(3, systemConfig.getReleaseProjectStartDate());
			ps.setDate(4, systemConfig.getReleaseProjectEndDate());
			ps.setDate(5, systemConfig.getStudentSelStartDate());
			ps.setDate(6, systemConfig.getStudentSelEndDate());
			ps.setInt(7, systemConfig.getStudentSelMaxnum());
			ps.setString(8, systemConfig.getAdminUsername());
			ps.executeUpdate();
			connection.commit();
			return true ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return false;
		} finally {
			DbUtils.closeConnection(connection, ps);
		}
	}

	@Override
	public SystemParameter queryByAccount(String accountName) {
		Connection connection = DbUtils.getConnection();
		String configSql = "select * from  system_parameter where adminusername = ?";
		PreparedStatement ps = null ;
		SystemParameter systemConfig = null ;
		ResultSet resultSet = null ;
		try {
			//设置事务为手动提交
			connection.setAutoCommit(false);
			//获取PreparedStatement
			ps = connection.prepareStatement(configSql);
			ps.setString(1, accountName);
			ps.executeQuery();
			connection.commit();
			resultSet = ps.executeQuery();
			while(resultSet.next()) {
				systemConfig = new SystemParameter();
				systemConfig.setAdminUsername(resultSet.getString("adminusername"));
				systemConfig.setAdminPassword(resultSet.getString("adminpassword"));
				systemConfig.setInvitationCode(resultSet.getString("invitationcode"));
				systemConfig.setReleaseProjectStartDate(resultSet.getDate("releaseprojectStartDate"));
				systemConfig.setReleaseProjectEndDate(resultSet.getDate("releaseprojectendDate"));
				systemConfig.setStudentSelEndDate(resultSet.getDate("studentselenddate"));
				systemConfig.setStudentSelStartDate(resultSet.getDate("studentselstartdate"));
				systemConfig.setStudentSelMaxnum(resultSet.getInt("studentselmaxnum"));
			}
			return systemConfig ;
		} catch(Exception e) {
			e.printStackTrace();
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

}
