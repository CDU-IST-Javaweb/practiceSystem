package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.service.impl.NoticeServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.PageUtils;

/**
 * Servlet implementation class AdminLookNoticesServlet
 */
@WebServlet("/SystemsManagement/AdminLookNoticesServlet")
public class AdminLookNoticesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminLookNoticesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<NoticeCompany> list = null;
		NoticeService noticeService = new NoticeServiceImpl();
		String pageNows = request.getParameter("pageNow");
		if (pageNows == null) {
			pageNows = "1";
		}
		int pageNow = Integer.parseInt(pageNows);
		int pageSize = 5;
		HttpSession session = request.getSession();
		try {
			//查询出所有数量
			int totalSize = noticeService.countNoAuditTimeNotice();
			PageUtils pager = new PageUtils(pageNow, totalSize);
			pager.setPageSize(pageSize);
			list = noticeService.queryNoticeByAuditTime(pageNow, pageSize);
			session.setAttribute("aditNotice", list);
			session.setAttribute("pager1", pager);
			request.getRequestDispatcher("/SystemsManagement/audit-notice.jsp").forward(request, response);
		} catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
	}
    
    

}
