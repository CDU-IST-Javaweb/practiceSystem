package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.management.relation.Role;
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
import javafx.print.PageRange;

/**
 * Servlet implementation class ShowNoticeListServlet
 */
@WebServlet("/MoreIndexNoticesListServlet")
public class MoreIndexNoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MoreIndexNoticeListServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入新闻列表Servlet");
		String tole = request.getParameter("tole");
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 15;
		HttpSession session = request.getSession();
		try {
			if (tole != null && !"".equals(tole)) {
				PageUtils pageUtils = null;
				//学院通知公告
				if ("1".equals(tole)) {
					List<NoticeAdmin> list = noticeService.queryAllAdminNoticeOrderByDate(pageNow, pageSize);
					int count = noticeService.countAdminNotice();
					pageUtils = new PageUtils(pageNow, count);
					pageUtils.setPageSize(pageSize);
					session.setAttribute("allList", list);
				}
				else {
					List<NoticeCompany> list = noticeService.queryAllCompanyNoticeOrderByDate(pageNow, pageSize);
					int count = noticeService.countAllAuditCompanyNotice();
					pageUtils = new PageUtils(pageNow, count);
					pageUtils.setPageSize(pageSize);
					session.setAttribute("allList", list);
				}
				session.setAttribute("pager", pageUtils);
				session.setAttribute("tole", tole);
				request.getRequestDispatcher("new-lists.jsp").forward(request, response);
				return ;
			}
			else {
				request.getRequestDispatcher("/404.html").forward(request, response);
				return ;
			}
			
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

}
