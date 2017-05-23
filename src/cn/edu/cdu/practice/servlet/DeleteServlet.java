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
				//跳转到404页面,并打印错误信息
				String errorMessage = "请求时附带非法字符，拒绝访问！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
		}
		else {
			CompanyService companyService = new CompanyServiceImpl();
			try {
				
					if (companyService.deleteCompany(userName)) {
						request.getRequestDispatcher("/EnterpriseManagement/ShowCompanyssServlet").forward(request, response);
					}
					else {
						//跳转到404页面,并打印错误信息
						String errorMessage = "访问数据库出现异常！";
						request.getSession().setAttribute("ErrorMessage", errorMessage);
						response.sendRedirect(request.getContextPath() + "/404.jsp");
						return ;
					}
			}catch(Exception e) {
				Log4jUtils.info(e.getMessage());
				//跳转到404页面,并打印错误信息
				String errorMessage = "访问数据库出现异常！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
				return ;
			}
		}
		
	}

}
