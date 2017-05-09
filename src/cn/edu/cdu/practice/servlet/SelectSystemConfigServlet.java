package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.NoticeCompany;
import cn.edu.cdu.practice.model.SystemParameter;
import cn.edu.cdu.practice.service.NoticeService;
import cn.edu.cdu.practice.service.SystemParameterService;
import cn.edu.cdu.practice.service.impl.NoticeServiceImpl;
import cn.edu.cdu.practice.service.impl.SystemParameterServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class SelectSystemConfigServlet
 */
@WebServlet("/SystemsManagement/SelectSystemConfigServlet")
public class SelectSystemConfigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectSystemConfigServlet() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		String user = (String) session.getAttribute("user");
		if (user == null) {
			user = "3";
		}
		try {
			//从数据库中获取SystemParameter对象并存进session
			SystemParameterService systemParameterService = new SystemParameterServiceImpl();
			SystemParameter systemParameter = systemParameterService.queryByAccount(user);
			session.setAttribute("sys", systemParameter);
			request.getRequestDispatcher("/SystemsManagement/system-parameter.jsp").forward(request, response);
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			request.getRequestDispatcher("/404.html").forward(request, response);
			return;
		}
	}
}