package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/EnterpriseManagement/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//得到传入的userName参数
		String userName = request.getParameter("userName");
		//校验
		if (ValidateUtils.validate(userName) || userName == null || "".equals(userName)) {
				System.out.println("有过滤参数");
				request.getRequestDispatcher("/404.html").forward(request, response);
				return ;
		}
		else {
			CompanyService companyService = new CompanyServiceImpl();
			try {
				
					if (companyService.deleteCompany(userName)) {
						request.getRequestDispatcher("/EnterpriseManagement/ShowCompanyssServlet").forward(request, response);
					}
					else {
						request.getRequestDispatcher("/404.html").forward(request, response);
						return ;
					}
			}catch(Exception e) {
				Log4jUtils.info(e.getMessage());
				request.getRequestDispatcher("/404.html").forward(request, response);
				return ;
			}
		}
		
	}

}
