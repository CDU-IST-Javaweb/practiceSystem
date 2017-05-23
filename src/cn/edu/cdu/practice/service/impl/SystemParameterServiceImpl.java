package cn.edu.cdu.practice.service.impl;
import java.sql.Date;

import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.dao.SystemParameterDao;
import cn.edu.cdu.practice.dao.impl.SystemParameterDaoImpl;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.service.SystemParameterService;
import cn.edu.cdu.practice.utils.DateUtil;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.MdPwdUtil;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName CompanyService.java
 * @version 1.0
 * @Description: 企业信息管理操作
 * @Author 陈天雄
 * @Date： 2017-4-22:下午5:05:01
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class SystemParameterServiceImpl implements SystemParameterService {
	private SystemParameterDao systemParameterDao = new SystemParameterDaoImpl();
	/**
	 * 设置系统参数
	 */
	public boolean setOrUpdateSystemConfig(String account,String pwd,String code,
			String releaseProjectStartDate,String releaseProjectEndDate,
			String studentSelStartDate,String studentSelEndDate,String studentSelMaxnum,
			String userName) {
		//必需的参数不能为空
		if (code == null || "".equals(code) || studentSelMaxnum == null ||"".equals(studentSelMaxnum)) {
			System.out.println("有空的参数");
			return false;
		} else {
			Integer studentSelMaxnum1 = Integer.parseInt(studentSelMaxnum);
			if (studentSelMaxnum1 == 0) {
				System.out.println("有错");
				return false;
			}
			else {
				System.out.println("没错");
				Date releaseProjectStartDate1 = null;
				Date releaseProjectEndDate1 = null;
				Date studentSelStartDate1 = null;
				Date studentSelEndDate1 = null;
				if (releaseProjectStartDate != null && !"".equals(releaseProjectStartDate)) {
					System.out.println("没错1");
					releaseProjectStartDate1 = DateUtil.splitStringToDate(releaseProjectStartDate);
				}
				if (releaseProjectEndDate != null && !"".equals(releaseProjectEndDate)) {
					System.out.println("1"+releaseProjectEndDate+"1");
					System.out.println("没错2");
					releaseProjectEndDate1 = DateUtil.splitStringToDate(releaseProjectEndDate);
				}
				if (studentSelStartDate != null && !"".equals(studentSelStartDate)) {
					System.out.println("没错3");
					studentSelStartDate1 = DateUtil.splitStringToDate(studentSelStartDate);
				}
				if (studentSelEndDate != null && !"".equals(studentSelEndDate)) {
					System.out.println("没错4");
					studentSelEndDate1 = DateUtil.splitStringToDate(studentSelEndDate);
				}
				System.out.println("结果没错");
				SystemParameter systemParameter = new SystemParameter();
				if ("518855".equals(pwd)) {
					pwd = "";
				}
				boolean flag = false;
				if (pwd == null || "".equals(pwd)) {//密码为空，就不设置密码
					System.out.println("没有密码");
					systemParameter.setInvitationCode(code);
					systemParameter.setReleaseProjectStartDate(releaseProjectStartDate1);
					systemParameter.setReleaseProjectEndDate(releaseProjectEndDate1);
					systemParameter.setStudentSelStartDate(studentSelStartDate1);
					systemParameter.setStudentSelEndDate(studentSelEndDate1);
					systemParameter.setStudentSelMaxnum(studentSelMaxnum1);
				}
				else {
					//设置一系列的参数
					System.out.println("有密码");
					systemParameter.setAdminPassword(MdPwdUtil.MD5Password(pwd));
					systemParameter.setInvitationCode(code);
					systemParameter.setReleaseProjectStartDate(releaseProjectStartDate1);
					systemParameter.setReleaseProjectEndDate(releaseProjectEndDate1);
					systemParameter.setStudentSelStartDate(studentSelStartDate1);
					systemParameter.setStudentSelEndDate(studentSelEndDate1);
					systemParameter.setStudentSelMaxnum(studentSelMaxnum1);
					flag = true;
				}
				System.out.println("可以复制");
				if (userName != null && !"".equals(userName)) {
					try {
						if (flag) {
							return this.systemParameterDao.updateSystemConfig(systemParameter, userName);
						}
						else {
							return this.systemParameterDao.updateSystemConfigNoPwd(systemParameter, userName);
						}
					}catch(Exception e) {
						Log4jUtils.info(e.getMessage());
						return false;
				}
				}
				else {
					return false;
				}
			}
			
		}
	}
	
	public SystemParameter queryByAccount(String accountName) {
		//传入的用户名不为空
		if (accountName != null && !"".equals(accountName)) {
			try {
				SystemParameter systemParameter = this.systemParameterDao.queryByAccount(accountName);
				return systemParameter;
			} catch(Exception e) {
				Log4jUtils.info(e.getMessage());
				return null ;
			}
		}else {
			throw new NullPointerException("传入参数为空");
		}
	}

}
