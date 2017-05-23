package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class UpdateCompanyInfo
 */
@WebServlet("/EnterpriseManagement/UpdateCompanyInfos")
public class UpdateCompanyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateCompanyInfo() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post解码方式
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Company company = (Company) session.getAttribute("company");
		String profile = request.getParameter("profile");
		String contacts = request.getParameter("contacts");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		if (ValidateUtils.validate(profile) || ValidateUtils.validate(contacts)
				|| ValidateUtils.validate(address) || ValidateUtils.validate(phone)) {
			System.out.println("有可疑参数");
			//跳转到404页面,并打印错误信息
			String errorMessage = "请求附带可疑字符，访问被拒绝！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return ;
		}
		company.setAddress(address);
		company.setContacts(contacts);
		company.setPhone(phone);
		company.setProfile(profile);
		company.setAuditDate(null);
		CompanyService companyService = new CompanyServiceImpl();
		try {
			if (companyService.updateCompanyInfo(company)) {
				request.getRequestDispatcher("/EnterpriseManagement/ShowsCompanyServlet").forward(request, response);
				return;
			}
			//跳转到404页面,并打印错误信息
			String errorMessage = "访问数据库出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
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
