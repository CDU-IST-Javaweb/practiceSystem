package cn.edu.cdu.practice.dao.impl;
import java.util.Date;

import cn.edu.cdu.practice.dao.NoticeDao;
import cn.edu.cdu.practice.model.NoticeCompany;


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

	@Override
	public boolean updateCompanyNotice(NoticeCompany companyNotice) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCompanyNotice(int companyNoticeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void provideAnnouncement(NoticeCompany companyNotice) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate) {
		// TODO Auto-generated method stub
		return false;
	}

}
