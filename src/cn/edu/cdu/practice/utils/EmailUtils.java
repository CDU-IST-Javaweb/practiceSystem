package cn.edu.cdu.practice.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.edu.cdu.practice.model.MailboxVerification;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName TestDb.java
 * @version 1.0
 * @Description: 发送邮件工具类
 * @Author 陈天雄
 * @Date： 2017-4-16:下午3:37:43
 * Modification User： 于曦
 * Modification Date： 2017-5-13:上午10:32:43
 */
public class EmailUtils {
	public static boolean sendMail(String emailTo,int type,String content) {
		 Properties p = new Properties();  
		 //smtp服务器信息
	     p.put("mail.smtp.host", "smtp.exmail.qq.com");  
	     p.put("mail.smtp.auth", "true");  
	     p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  //使用JSSE的SSL socketfactory来取代默认的socketfactory
	     p.put("mail.smtp.socketFactory.fallback", "false");  // 只处理SSL的连接,对于非SSL的连接不做处理
	     p.put("mail.smtp.port", "465");  
	     p.put("mail.smtp.socketFactory.port", "465");
	     //设置发送邮件的账号和密码
	     Session session = Session.getDefaultInstance(p, new Authenticator() {
	         @Override
	         protected PasswordAuthentication getPasswordAuthentication() {
	         //两个参数分别是发送邮件的账户和密码
	         return new PasswordAuthentication("computer_sys@cdu.edu.cn","Sie#1234");
	                 }
	      });  
	     //将传入的验证码
//	     String identifyCode = IdentifyCodeUtils.getCode();
//	     MailboxVerification mailboxVerification = new MailboxVerification(emailTo, type, identifyCode);
	     //创建邮件对象
	     Message mailMessage = new MimeMessage(session);  
	     try {  
	         System.out.println("I'm sending...");  
	         
	         Address from = new InternetAddress("computer_sys@cdu.edu.cn");  
	         //设置发出方  
	         mailMessage.setFrom(from);  
	         Address to = new InternetAddress(emailTo);
	         //设置接收人员  
	         mailMessage.setRecipient(Message.RecipientType.TO, to); 
	         System.out.println(emailTo);
	         mailMessage.setSubject("成都大学信工学院实训系统企业验证");//设置邮件标题  
	         mailMessage.setContent("您的验证码是"+content+"，请确认是本人操作","text/html;charset=utf-8"); //设置邮件内容  
	         // 发送邮件  
	         Transport.send(mailMessage);  
	         return true;  
	     } catch (Exception e) {  
	         e.printStackTrace();  
	     }  
	     	return false;  
	 }  
	public static void main(String[] args) {
//		sendMail("oliveryx@163.com","yuxiytx912","5374664@qq.com",1,"abcd");
		sendMail("5374664@qq.com",1,"abcd222");
	}
}
