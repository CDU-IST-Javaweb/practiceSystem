package cn.edu.cdu.practice.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/** 
* @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName CompanyDao.java
 * @version 1.0
 * @Description: 连接数据库操作
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
*/
public class DbUtils {
	/*private static Connection connection ;
	private static String driver ;
	private static String url ;
	private static String user ;
	private static String password ;*/
	private static Connection connection ;
	private static ComboPooledDataSource cpds = null;
	/**
	 * 静态代码块，读取jdbc配置文件，将数据库连接参数赋值给本类的属性
	 */
	static {
		/*Properties properties = new Properties();
		ClassLoader loader = DbUtils.class.getClassLoader();
		try {
			properties.load(loader.getResourceAsStream("jdbc.properties"));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		 cpds = new ComboPooledDataSource();
	}
	
	/**
	 * 
	 * <p>Title: getConnection</p>
	 * <p>Description: 获得数据库连接对象</p>
	 * @return 连接成功返回一个Conneection对象，失败返回null
	 */
	public static Connection getConnection() {
		try {
			connection =  cpds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (connection != null) {
			return connection;
		}
		return null;
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: 数据库关闭操作</p>
	 * @param connection Connection对象
	 * @param statement Statement对象
	 * @param resultSet ResultSet对象
	 */
	
	public static void closeConnection(Connection connection, Statement statement,ResultSet resultSet){
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: 数据库关闭操作</p>
	 * @param connection Connection对象
	 * @param statement PreparedStatement对象
	 * @param resultSet ResultSet对象
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement,ResultSet resultSet){
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 
	 * <p>Title: closeConnection</p>
	 * <p>Description: 数据库关闭操作</p>
	 * @param connection Connection对象
	 * @param statement PreparedStatement对象
	 */
	public static void closeConnection(Connection connection, PreparedStatement statement){
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						if (connection != null) {
							try {
								connection.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
}
