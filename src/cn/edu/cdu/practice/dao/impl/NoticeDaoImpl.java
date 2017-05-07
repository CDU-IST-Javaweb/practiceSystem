package cn.edu.cdu.practice.dao.impl;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
		String registSql = "update notice_company set "
				+ "release_date = ?,audit_date = ?,content = ?,title = ? where ID = ?";
		PreparedStatement ps = null ;
		try {
			 connection.setAutoCommit(false);//设置手动提交事务
			 ps = connection.prepareStatement(registSql);
			 ps.setDate(1, companyNotice.getReleaseDate());
			 ps.setDate(2, companyNotice.getAuditDate());
			 ps.setString(3,companyNotice.getContent());
			 ps.setString(4, companyNotice.getTitle());
			 ps.setInt(5, companyNotice.getId());
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
				String registSql = "insert into notice_company(company_username,release_date,"
						+ "title,content)"
						+ " values(?,?,?,?)";
				PreparedStatement ps = null ;
				try {
					 connection.setAutoCommit(false);//设置手动提交事务
					 ps = connection.prepareStatement(registSql);
					 
					 ps.setString(1, companyNotice.getCompanyUsername());
					 ps.setDate(2, companyNotice.getReleaseDate());
					 ps.setString(3, companyNotice.getTitle());
					 ps.setString(4,companyNotice.getContent());
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

	public List<NoticeCompany> queryNoticeByCompanyName(String companyUserName, int pageNow , int pageSize) {
		//获取数据库连接
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from (select * from notice_company where company_username = ?)as a limit ?,?";
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		List<NoticeCompany> list = new ArrayList<NoticeCompany>();
		NoticeCompany noticeCompany = null ;
		try {
			 ps = connection.prepareStatement(registSql);
			 ps.setString(1, companyUserName);
			 ps.setInt(2, (pageNow-1)*pageSize);
			 ps.setInt(3, pageSize);
			 //执行查询语句
			 resultSet = ps.executeQuery();
			 //只要resultSet指向的下一个元素有内容，那么就一直执行查询与赋值操作
			 while (resultSet.next()) {
				 noticeCompany = new NoticeCompany();
				 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
				 noticeCompany.setId(resultSet.getInt("ID"));
				 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
				 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
				 noticeCompany.setTitle(resultSet.getString("title"));
				 noticeCompany.setContent(resultSet.getString("content"));
				 //添加进List中
				 list.add(noticeCompany);
			}
			 return list ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//每次操作之后必须关闭连接
			DbUtils.closeConnection(connection, ps,resultSet);
		}
	}

	/**
	 * 根据公司名字，查询出所有的通知公告
	 */
	public int queryAllByName(String companyUserName) {
				//获取数据库连接
				Connection connection = DbUtils.getConnection();
				String registSql = "select count(*) from notice_company where company_username = ?";
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				int count = 0;
				List<NoticeCompany> list = new ArrayList<NoticeCompany>();
				NoticeCompany noticeCompany = null ;
				try {
					 ps = connection.prepareStatement(registSql);
					 ps.setString(1, companyUserName);
					 //执行查询语句
					 resultSet = ps.executeQuery();
					 //只要resultSet指向的下一个元素有内容，那么就一直执行查询与赋值操作
					if (resultSet.next()) {
						count = resultSet.getInt(1);
					}
					 return count;
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally {
					//每次操作之后必须关闭连接
					DbUtils.closeConnection(connection, ps,resultSet);
				}
	}

	/**
	 * 根据通知公告的Id查询通知公告
	 */
	public NoticeCompany queryNoticeById(int companyNoticeId) {
		//获取数据库连接
		Connection connection = DbUtils.getConnection();
		String registSql = "select * from notice_company  where ID = ?";
		NoticeCompany noticeCompany = null;
		PreparedStatement ps = null ;
		ResultSet resultSet = null ;
		try {
			 //动态参数赋值
			 ps = connection.prepareStatement(registSql);
			 ps.setInt(1, companyNoticeId);
			 resultSet = ps.executeQuery();
			 while (resultSet.next()) {
				 noticeCompany = new NoticeCompany();
				 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
				 noticeCompany.setId(resultSet.getInt("ID"));
				 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
				 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
				 noticeCompany.setTitle(resultSet.getString("title"));
				 noticeCompany.setContent(resultSet.getString("content"));
			}
			 return noticeCompany ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			//每次操作之后必须关闭连接
			DbUtils.closeConnection(connection, ps);
		}
	}

	/**
	 * 分页查询出所有未审核的通知公告
	 */
	public List<NoticeCompany> queryNoticeByAuditTime(int pageNow, int pageSize) {
		//获取数据库连接
				Connection connection = DbUtils.getConnection();
				String registSql = "select * from (select * from notice_company where audit_date IS NULL)as a limit ?,?";
				PreparedStatement ps = null ;
				ResultSet resultSet = null ;
				List<NoticeCompany> list = new ArrayList<NoticeCompany>();
				NoticeCompany noticeCompany = null ;
				try {
					 ps = connection.prepareStatement(registSql);
					 ps.setInt(1, (pageNow-1)*pageSize);
					 ps.setInt(2, pageSize);
					 //执行查询语句
					 resultSet = ps.executeQuery();
					 //只要resultSet指向的下一个元素有内容，那么就一直执行查询与赋值操作
					 while (resultSet.next()) {
						 noticeCompany = new NoticeCompany();
						 noticeCompany.setCompanyUsername(resultSet.getString("company_username"));
						 noticeCompany.setId(resultSet.getInt("ID"));
						 noticeCompany.setReleaseDate(resultSet.getDate("release_date"));
						 noticeCompany.setAuditDate(resultSet.getDate("audit_date"));
						 noticeCompany.setTitle(resultSet.getString("title"));
						 noticeCompany.setContent(resultSet.getString("content"));
						 //添加进List中
						 list.add(noticeCompany);
					}
					 return list ;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					//每次操作之后必须关闭连接
					DbUtils.closeConnection(connection, ps,resultSet);
				}
	}

	/**
	 * 统计所有未审核的通知公告的数量
	 */
	public int countNoAuditTimeNotice() {
		//获取数据库连接
		Connection connection = DbUtils.getConnection();
		String registSql = "select count(*) from notice_company where audit_date IS NULL";
		Statement statement = null ;
		ResultSet resultSet = null ;
		int count = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(registSql);
			 //只要resultSet指向的下一个元素有内容，那么就一直执行查询与赋值操作
			if (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			 return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			//每次操作之后必须关闭连接
			DbUtils.closeConnection(connection, statement,resultSet);
		}
	}

}
