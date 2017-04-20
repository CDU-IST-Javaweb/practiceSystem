package cn.edu.cdu.practice.service.impl;

import java.sql.*;

import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.service.UserService;
import cn.edu.cdu.practice.utils.*;
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
	public boolean login(String account, String password, String Verification_Code, String Session_Verification_Code,String role) {
		Connection con = (Connection) DbUtils.getConnection();
		String sql = "";
		ResultSet rs;
		PreparedStatement ps;
		//如果验证码不正确或没有得到验证码，返回false
		if(Verification_Code == null || !Verification_Code.equals(Session_Verification_Code)){
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
				sql = "select * from company where username=? and password = ?"; 
				break;
			case "2": 
				sql = "select * from company where name=? and password = ?"; 
				break;
			case "9": 
				sql = "select * from company where admin_username=? and admin_password = ?";
				break;
			}
		}
		try {
			ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				Log4jUtils.info("用户登录成功");
				//根据角色不同，跳转到不同页面。
			}
			DbUtils.closeConnection(con, ps, rs);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log4jUtils.info("用户登录不成功");
		return false;
	}

	@Override
	public String getPassBack(String mailbox) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean resetPass(String password, String Verification_Code) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
