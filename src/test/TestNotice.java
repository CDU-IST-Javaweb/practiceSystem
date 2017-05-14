package test;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import cn.edu.cdu.practice.dao.NoticeDao;
import cn.edu.cdu.practice.dao.impl.NoticeDaoImpl;
import cn.edu.cdu.practice.model.NoticeAdmin;
import cn.edu.cdu.practice.model.NoticeCompany;

/**
 * <p>Title: TestNotice</p>
 * <p>Description: </p>
 * <p>Company: www.com.panda</p> 
 * @author	陈先森
 * @date	2017年4月21日上午11:44:39
 * @version 1.0
 */
public class TestNotice {
	@Test
	public void test() {
		/*NoticeDaoImpl noticeDaoImpl = new NoticeDaoImpl();
		NoticeCompany noticeCompany = new NoticeCompany();*/
		/*noticeCompany.setId(1);
		Date date = Date.valueOf("1998-10-12 ");
		System.out.println(date.toLocaleString());
		noticeCompany.setCompanyUsername("sayHello");
		noticeCompany.setReleaseDate(date);
		noticeCompany.setContent("这是我公司的方案策划");
		noticeDaoImpl.provideAnnouncement(noticeCompany);*/
		/*noticeCompany.setId(2);
		Date date = Date.valueOf("2017-10-12");
		System.out.println(date.toLocaleString());
		noticeCompany.setCompanyUsername("sayHello");
		noticeCompany.setReleaseDate(date);
		noticeCompany.setContent("其实这是我公司的方案策划");
		noticeDaoImpl.provideAnnouncement(noticeCompany);*/
		/*noticeDaoImpl.deleteCompanyNotice(2);*/
		/*Date date = Date.valueOf("2017-6-12");
		noticeDaoImpl.reviewCompanyNotice(1, date);*/
		NoticeDao noticeDao = new NoticeDaoImpl();
		/*NoticeAdmin noticeCompany = new NoticeAdmin();
		noticeCompany.setReleaseDate(date);
		noticeCompany.setTitle("测试标题6");
		noticeCompany.setContent("测试内容5");
		noticeDao.provideAdminAnnouncement(noticeCompany);*/
		/*List<NoticeAdmin> list = noticeDao.queryAdminNotice(1, 4);
		System.out.println(list.size());*/
		/*int count = noticeDao.countAdminNotice();
		System.out.println(count);*/
		/*NoticeAdmin noticeCompany = noticeDao.queryNoticeAdminById(4);
		System.out.println(noticeCompany.getTitle());*/
		System.out.println(noticeDao.countAllAuditCompanyNotice());
	}
}
