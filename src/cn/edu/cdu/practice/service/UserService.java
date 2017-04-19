package cn.edu.cdu.practice.service;

public interface UserService {
	//用户登录时，在页面选择角色，然后输入需要的参数，如果role=1，进企业表；如果role=2，进学生表；如果role=9，进系统参数表
	public boolean login(String account, String password, String Verification_Code, String role);
	//用户输入密保邮箱后，获得动态验证码
	public String getPassBack(String mailbox);
	//用户两次输入的密码要一致，这个验证在前台页面完成
	public boolean resetPass(String password, String Verification_Code);
}
