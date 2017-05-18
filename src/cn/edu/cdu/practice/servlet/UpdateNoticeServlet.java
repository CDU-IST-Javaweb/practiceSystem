package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.service.impl.NoticeServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class UpdateNoticeServlet
 */
@WebServlet("/SystemsManagement/UpdateNoticeServlet")
public class UpdateNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNoticeServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post方式解码
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");
		System.out.println(title+" "+content);
		if (ValidateUtils.validate(title) || ValidateUtils.validate(content)
				|| ValidateUtils.validate(id)) {
			System.out.println("有可疑参数");
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
		NoticeService noticeService = new NoticeServiceImpl();
		try {
			//根据Id取出通知对象，再进行赋值
			Integer companyNoticeId = Integer.parseInt(id);
			NoticeCompany noticeCompany = noticeService.queryNoticeById(companyNoticeId);
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			//只用更新内容
			noticeCompany.setTitle(title);
			noticeCompany.setContent(content);
			noticeCompany.setReleaseDate(sqlDate);
			//更改之后必须让管理员重新审核
			noticeCompany.setAuditDate(null);
			noticeService.updateCompanyNotice(noticeCompany);
			request.getRequestDispatcher("/SystemsManagement/ShowNoticeListServlet").forward(request, response);
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
		
	}

}
