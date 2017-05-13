package cn.edu.cdu.practice.service.impl;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.service.UserService;
import cn.edu.cdu.practice.utils.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName UserServiceImpl.java
 * @version 1.0
 * @Description: UserService接口的实现
 * @Author 于曦
 * @Date： 2017-4-17:下午21:04:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class UserServiceImpl implements UserService{

	@Override
	//用户登录时，在页面选择角色，然后输入需要的参数，如果验证码和session中的一致，则进行下一步验证
	//如果role=1，进企业表；如果role=2，进学生表；如果role=9，进系统参数表
	public boolean login(String account, String password, String Verification_Code,String role,String vchidden) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement ps;
		String account_type = "";
		System.out.println(Verification_Code + " "+ vchidden.toLowerCase());
		//如果验证码不正确或没有得到验证码，返回false
		if(Verification_Code == null || !Verification_Code.equals(vchidden.toLowerCase())){
			Log4jUtils.info("用户验证码输入错误");
			return false;
		}
		
		//如果用户角色没有选中，则直接返回false
		if(role == null){
			Log4jUtils.info("没有选中用户角色");
			return false;
		}else{
			//根据不同的角色，生成不同的sql语句
			switch(role){
			case "1": 
				account_type = "企业";
				sql = "select * from company where username=? and password = ?"; 
				break;
			case "2": 
				account_type = "学生";
				sql = "select * from student where name=? and password = ?"; 
				break;
			case "9": 
				account_type = "管理员";
				sql = "select * from system_parameter where admin_username=? and admin_password = ?";
			}
		}
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, MdPwdUtil.MD5Password(password));
			rs = ps.executeQuery();
//			System.out.println(ps.toString());
			if(rs.next()){
				Log4jUtils.info(account_type+ "用户" + account + "登录成功");
				DbUtils.closeConnection(con, ps, rs);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log4jUtils.info(account_type+ "用户" + account + "登录不成功");
		return false;
	}

	//用户输入密保邮箱后，将生成的验证码插入到mailbox_verification表中，如果mailbox_verification表中已经有这个邮箱了，
	//就不要进行插入操作，而是将新生成的验证码更新到指定的记录。
	@SuppressWarnings("resource")
	@Override
	public Boolean getPassBack(String mailbox,int type, String identifyCode) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		int num = 0;
		PreparedStatement ps;
		ResultSet rs = null;
		try{
			sql = "select * from mailbox_verification where mailbox = ?";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, mailbox);
			rs = ps.executeQuery();
			//如果没有密保邮箱的记录，就进行插入
			if(!rs.next()){
				sql = "insert into mailbox_verification values(?,?,?)";
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, mailbox);
				ps.setInt(2, type);
				ps.setString(3, identifyCode);
				num = ps.executeUpdate();
				if(num == 1){
					Log4jUtils.info(mailbox + "验证码设置成功");
					DbUtils.closeConnection(con, ps);
					return true;
				}else{
					Log4jUtils.info(mailbox + "验证码设置不成功");
					DbUtils.closeConnection(con, ps);
					return false;
				}
			}
//			DbUtils.closeConnection(con, ps,rs);
			//如果有，就更新验证码
			sql = "update mailbox_verification set verification_code = ? where mailbox = ?";
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, identifyCode);
			ps.setString(2, mailbox);
			System.out.println(ps.toString());
			num = ps.executeUpdate();
			if(num == 1){
				Log4jUtils.info(mailbox + "验证码修改成功");
				DbUtils.closeConnection(con, ps);
				return true;
			}else{
				Log4jUtils.info(mailbox + "验证码修改不成功");
				DbUtils.closeConnection(con, ps);
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log4jUtils.info(mailbox + "验证码设置不成功");
		return false;
	}
	//将新密码进行MD5加密后存入指定数据表
	@Override
	public boolean resetPass(String password,String mbemail,String role,String account) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		int num = 0;
		PreparedStatement ps;
		sql = "UPDATE student set password=? where mailbox=?";
		String MDpass = MdPwdUtil.MD5Password(password);
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, MDpass);
			ps.setString(2, mbemail);
			num = ps.executeUpdate();
			if(num == 1){
				Log4jUtils.info(account + "重设密码成功");
				DbUtils.closeConnection(con, ps);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log4jUtils.info(account + "重设密码不成功");
		return false;
	}

	@Override
	public List<String> searchbyEmail(String mailbox) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement ps;
		List<String> list = new ArrayList<String>();
		String role = "";
		String account = ""; 
		try {
			sql = "select * from student where mailbox=?"; 
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, mailbox);
			rs = ps.executeQuery();
			//如果在student表里找到，就将flag设置为true，同时将type设置为2
			if(rs.next()){
				role = "2";
				account = rs.getString("name");
				DbUtils.closeConnection(con, ps, rs);
			}else{
				sql = "select * from company where mailbox=?"; 
				ps = (PreparedStatement) con.prepareStatement(sql);
				ps.setString(1, mailbox);
				rs = ps.executeQuery();
				//如果在company表里找到，就将flag设置为true，同时将type设置为1
				if(rs.next()){
					role = "1";
					account = rs.getString("company_name");
					DbUtils.closeConnection(con, ps, rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.add(account);
		list.add(role);
		return list;
	}

	@Override
	public boolean register(String rscode, String qyname, String qyusername, String password, String confirmPassword,
			String email, String verificationCode, String captcha) {
		// TODO Auto-generated method stub
		return false;
	}
	
}