package cn.edu.cdu.practice.service;
/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName UserService.java
 * @version 1.0
 * @Description: 用户业务逻辑，定义与用户登录等功能相关的接口
 * @Author 于曦
 * @Date： 2017-4-15:下午15:04:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public interface UserService {
	//用户登录时，在页面选择角色，然后输入需要的参数，如果验证码和session中的一致，则进行下一步验证
	//如果role=1，进企业表；如果role=2，进学生表；如果role=9，进系统参数表
	public boolean login(String account, String password, String Verification_Code, String role,String vchidden);
	//用户输入密保邮箱后，获得动态验证码
	public String getPassBack(String mailbox);
	//用户两次输入的密码要一致，这个验证在前台页面完成
	public boolean resetPass(String password, String Verification_Code);
	public boolean register(String rscode, String qyname, String qyusername, String password, String confirmPassword,
			String email, String verificationCode, String captcha);
}
