package cn.edu.cdu.practice.service.impl;
import java.sql.Date;
import java.util.List;

import com.mysql.jdbc.log.LogUtils;

import cn.edu.cdu.practice.dao.NoticeDao;
import cn.edu.cdu.practice.dao.impl.NoticeDaoImpl;
import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.utils.Log4jUtils;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName CompanyService.java
 * @version 1.0
 * @Description: 企业信息管理操作
 * @Author 陈天雄
 * @Date： 2017-4-22:下午4:58:00
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */
public class NoticeServiceImpl implements NoticeService {
	private NoticeDao noticeDao = new NoticeDaoImpl();
	/**
	 * 更新通知
	 */
	public boolean updateCompanyNotice(NoticeCompany companyNotice) {
		try {
			if (companyNotice != null ) {
				return this.noticeDao.updateCompanyNotice(companyNotice);
			}
			return false ;
			} catch(Exception exception) {
				Log4jUtils.info(exception.getMessage());
				return false;
			}
	}

	/**
	 * 根据通知Id删除通知
	 */
	public boolean deleteCompanyNotice(int companyNoticeId) {
		try {
			if (companyNoticeId != 0 ) {
				return this.noticeDao.deleteCompanyNotice(companyNoticeId);
			}
			return false ;
			} catch(Exception exception) {
				Log4jUtils.info(exception.getMessage());
				return false;
			}
	}

	/**
	 * 发布通知
	 */
	public void provideAnnouncement(NoticeCompany companyNotice) {
		try {
			if (companyNotice != null ) {
				this.noticeDao.provideAnnouncement(companyNotice);
			}
			} catch(Exception exception) {
				Log4jUtils.info(exception.getMessage());
			}
	}

	/**
	 * 管理员审核通知
	 */
	public boolean reviewCompanyNotice(int companyNoticeId, Date companyAuditDate) {
		try {
			if (companyNoticeId != 0) {
				return this.noticeDao.reviewCompanyNotice(companyNoticeId, companyAuditDate);
			}
			return false ;
			} catch(Exception exception) {
				Log4jUtils.info(exception.getMessage());
				return false;
			}
	}

	public List<NoticeCompany> queryNoticeByCompanyName(String companyUserName,int pageNow,int pageSize) {
		try {
			System.out.println(companyUserName);
			if (companyUserName != null && !"".equals(companyUserName)) {
				List<NoticeCompany> list = this.noticeDao.queryNoticeByCompanyName(companyUserName,pageNow,pageSize);
				return list;
			}
			return null;
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			return null ;
		}
	}

	
	public int queryAllByName(String companyUserName) {
		try {
			System.out.println(companyUserName);
			int count = 0 ;
			if (companyUserName != null && !"".equals(companyUserName)) {
				count = this.noticeDao.queryAllByName(companyUserName);
			}
			return count;
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			return 0 ;
		}
	}

	@Override
	public NoticeCompany queryNoticeById(int companyNoticeId) {
		if (companyNoticeId == 0) {
			throw new NullPointerException("传入参数为空");
		}
		try {
			return this.noticeDao.queryNoticeById(companyNoticeId);
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			return null ;
		}
	}

	
	public List<NoticeCompany> queryNoticeByAuditTime(int pageNow, int pageSize) {
		List<NoticeCompany> list = null ;
		try{
			list = this.noticeDao.queryNoticeByAuditTime(pageNow, pageSize);
			return list;
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			return null;
		}
		
	}

	
	public int countNoAuditTimeNotice() {
		try {
			int count = this.noticeDao.countNoAuditTimeNotice();
			return count;
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			return 0;
		}
	}

}
