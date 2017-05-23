package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.sql.Date;

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
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class AddNoticeCompanyServlet
 */
@WebServlet("/SystemsManagement/AddNoticeAdminServlet")
public class AddNoticeAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddNoticeAdminServlet() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(title + " " + content);
		if (ValidateUtils.validate(title) || ValidateUtils.validate(content)) {
			System.out.println("有可疑参数");
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求时附带可疑参数，拒绝访问！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return;
		}
		NoticeService noticeService = new NoticeServiceImpl();
		NoticeAdmin noticeAdmin = new NoticeAdmin();
		noticeAdmin.setTitle(title);
		noticeAdmin.setContent(content);
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		System.out.println(sqlDate);
		noticeAdmin.setReleaseDate(sqlDate);
		try {
			noticeService.provideAdminAnnouncement(noticeAdmin);
			request.getRequestDispatcher("/SystemsManagement/ShowAdminNotices").forward(request, response);
		} catch (Exception e) {
			Log4jUtils.info(e.getMessage());
			//跳转到404页面,并打印错误信息
			String errorMessage = "系统错误！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}
	}

}
