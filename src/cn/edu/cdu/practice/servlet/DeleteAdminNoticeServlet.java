package cn.edu.cdu.practice.servlet;

import java.io.IOException;
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
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class DeleteNoticeServlet
 */
@WebServlet("/SystemsManagement/DeleteAdminNoticeServlet")
public class DeleteAdminNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteAdminNoticeServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//post解码方式
				request.setCharacterEncoding("UTF-8");
				String Id = request.getParameter("Id");
				HttpSession session = request.getSession();
				//参数校验，如果有非法字符，那么就跳转到404
				if (ValidateUtils.validate(Id) || Id == null) {
					request.getRequestDispatcher("/404.html").forward(request, response);
					return ;
				}
				try {
					Integer adminNoticeId = Integer.parseInt(Id);
					NoticeService noticeService = new NoticeServiceImpl();
					if (noticeService.deleteAdminNotic(adminNoticeId)) {
						request.getRequestDispatcher("/SystemsManagement/ShowAdminNotices").forward(request, response);	
					}
					else {
						request.getRequestDispatcher("/404.html").forward(request, response);
					}
				}catch(Exception e) {
					Log4jUtils.info(e.getMessage());
					request.getRequestDispatcher("/404.html").forward(request, response);
				}
	}

}
