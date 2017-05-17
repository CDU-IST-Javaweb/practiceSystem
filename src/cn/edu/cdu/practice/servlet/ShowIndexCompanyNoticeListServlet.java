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
 * Servlet implementation class ShowNoticeListServlet
 */
@WebServlet("/ShowIndexNoticeListServlet")
public class ShowIndexCompanyNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowIndexCompanyNoticeListServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入Servlet");
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 10;
		HttpSession session = request.getSession();
		try {
			List<NoticeCompany> list = noticeService.queryAllCompanyNoticeOrderByDate(pageNow, pageSize);
			List<NoticeAdmin> list2 = noticeService.queryAllAdminNoticeOrderByDate(pageNow, pageSize);
			session.setAttribute("companyNotices", list);
			session.setAttribute("adminyNotices", list2);
			session.setAttribute("info", "1");
			System.out.println(list.size());
			System.out.println(list2.size());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

}
