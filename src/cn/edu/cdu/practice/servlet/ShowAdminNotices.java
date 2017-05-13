package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.NoticeAdmin;
import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.service.impl.NoticeServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class ShowAdminNotices
 */
@WebServlet("/SystemsManagement/ShowAdminNotices")
public class ShowAdminNotices extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowAdminNotices() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入学院通知公告Servlet");
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 5;
		HttpSession session = request.getSession();
		try {
			int totalSize = noticeService.countAdminNotice();
			PageUtils pager = new PageUtils(pageNow, totalSize);
			pager.setPageSize(pageSize);
			List<NoticeAdmin> list = noticeService.queryAdminNotice(pageNow, pageSize);
			session.setAttribute("adminNotice", list);
			session.setAttribute("pagerOne", pager);
			System.out.println(list.size());
			request.getRequestDispatcher("/SystemsManagement/make-adminAnnouncements.jsp").forward(request, response);
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}
}
