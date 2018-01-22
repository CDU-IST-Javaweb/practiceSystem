package cn.edu.cdu.practice.service;

import java.util.List;

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
	//用户输入密保邮箱后，将生成的动态验证码插入到mailbox_verification表中
	public Boolean getPassBack(String mailbox, int type, String identifyCode);
	//通过邮箱，在student和company表中遍历，返回用户的类型
	public List<String> searchbyEmail(String mailbox);
	//对指定密码邮箱的用户重设密码,type为用户类型
	public boolean resetPass(String password,String mailbox,String role,String account);
}
