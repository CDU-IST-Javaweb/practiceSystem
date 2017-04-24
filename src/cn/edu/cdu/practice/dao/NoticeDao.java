
package cn.edu.cdu.practice.dao;


import java.sql.Date;

import cn.edu.cdu.practice.model.NoticeCompany;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName NoticeDao.java
 * @version 1.0
 * @Description: Dao层公告管理操作接口
 * @Author 陈天雄
 * @Date： 2017-4-14:上午20:49:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public interface NoticeDao {
	/**
	 * <p>Title: updateCompanyNotice</p>
	 * <p>Description: 修改企业发布的通知公告</p>
	 * @param companyNotice 企业通知公告实体类的引用
	 * @return 更新成功返回true，更新失败返回false
	 */
	boolean updateCompanyNotice(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: deleteCompanyNotice</p>
	 * <p>Description: 根据公告Id删除企业发布的公告</p>
	 * @param companyNoticeId 企业公告Id
	 * @return 删除成功返回true，删除失败返回false
	 */
	boolean deleteCompanyNotice(int companyNoticeId);
	
	/**
	 * <p>Title: provideAnnouncement</p>
	 * <p>Description:发布企业公告 </p>
	 * @param companyNotice 企业通知公告实体类的引用
	 */
	void provideAnnouncement(NoticeCompany companyNotice);
	
	/**
	 * <p>Title: reviewCompanyNotice</p>
	 * <p>Description: 管理员审核企业公告</p>
	 * @param companyNoticeId 企业公告Id
	 * @param companyNoticeDate 通知公告审核日期
	 * @return 设置审核日期成功返回true，删除失败返回false
	 */
	boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate);
}
