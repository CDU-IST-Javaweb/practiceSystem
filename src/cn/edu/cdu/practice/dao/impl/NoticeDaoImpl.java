package cn.edu.cdu.practice.dao.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import cn.edu.cdu.practice.dao.NoticeDao;
import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.utils.DbUtils;


/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName NoticeDaoImpl.java
 * @version 1.0
 * @Description: 通知公告管理操作
 * @Author 陈天雄
 * @Date： 2017-4-18:下午3:39:54
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class NoticeDaoImpl implements NoticeDao {

	/**
	 * 更新操作
	 */
	public boolean updateCompanyNotice(NoticeCompany companyNotice) {
		//获取数据库连接
		Connection connection = DbUtils.getConnection();
		String registSql = "update notice_company set ID = ? ,company_username = ?,"
				+ "release_date = ?,audit_date = ?,content = ?,title = ? where ID = ?";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//设置手动提交事务
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, companyNotice.getId());
			 ps.setString(2, companyNotice.getCompanyUsername());
			 ps.setDate(3, companyNotice.getReleaseDate());
			 ps.setDate(4, companyNotice.getAuditDate());
			 ps.setString(5,companyNotice.getContent());
			 ps.setString(6, companyNotice.getTitle());
			 ps.setInt(7, companyNotice.getId());
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
			return false;
		} finally {
			//每次操作之后必须关闭连接
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * 删除操作
	 */
	public boolean deleteCompanyNotice(int companyNoticeId) {
		//获取数据库连接
				Connection connection = DbUtils.getConnection();
				String registSql = "delete from notice_company   where ID = ?";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//设置手动提交事务
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, companyNoticeId);
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
					return false;
				} finally {
					//每次操作之后必须关闭连接
					DbUtils.closeConnection(connection, ps);
				}
	}

	/**
	 * 发布通知
	 */
	public void provideAnnouncement(NoticeCompany companyNotice) {
				//获取数据库连接
				Connection connection = DbUtils.getConnection();
				String registSql = "insert into notice_company values(?,?,?,?,?,?)";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//设置手动提交事务
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, companyNotice.getId());
					 ps.setString(2, companyNotice.getCompanyUsername());
					 ps.setDate(3, companyNotice.getReleaseDate());
					 ps.setDate(4, companyNotice.getAuditDate());
					 ps.setString(5, companyNotice.getTitle());
					 ps.setString(6,companyNotice.getContent());
					 ps.execute();
					 connection.commit();//提交事务
				} catch (Exception e) {
					e.printStackTrace();
					if (connection != null) {
						try {
							connection.rollback();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				} finally {
					//每次操作之后必须关闭连接
					DbUtils.closeConnection(connection, ps);
				}
	}

	/**
	 * 管理员审核通知
	 */
	public boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate) {
		//获取数据库连接
				Connection connection = DbUtils.getConnection();
				String registSql = "update notice_company set audit_date = ? where ID = ?";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//设置手动提交事务
					 ps = connection.prepareStatement(registSql);
					 ps.setDate(1, companyAuditDate);
					 ps.setInt(2, companyNoticeId);
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
					return false;
				} finally {
					//每次操作之后必须关闭连接
					DbUtils.closeConnection(connection, ps);
				}
	}

}
