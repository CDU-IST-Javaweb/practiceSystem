package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.org.apache.regexp.internal.RE;

import cn.edu.cdu.practice.model.Company;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.Log4jUtils;
import cn.edu.cdu.practice.utils.MdPwdUtil;
import cn.edu.cdu.practice.utils.ValidateUtils;

/**
 * Servlet implementation class UpdatePwdServlet
 */
@WebServlet("/EnterpriseManagement/UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdatePwdServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String oldPwd = request.getParameter("oldPwd");
		String newPwd1 = request.getParameter("newPwd1");
		String newPwd2 = request.getParameter("newPwd2");
		if (ValidateUtils.validate(oldPwd) || ValidateUtils.validate(newPwd1)
				|| ValidateUtils.validate(newPwd2)) {
			System.out.println("有可疑参数");
			request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
		//如果两次密码不一致
		if (!newPwd1.equals(newPwd2)) {
			request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
		else {
			HttpSession session = request.getSession();
			String account = (String) session.getAttribute("user");
			if (account == null) {
				account = "sayHello";
			}
			Company company = null ;
			try {
				CompanyService companyService = new CompanyServiceImpl();
				company = companyService.queryByUserName(account);
				System.out.println(MdPwdUtil.MD5Password(newPwd2));
				//如果输入密码和数据库中一致，则允许更改
				if (MdPwdUtil.MD5Password(oldPwd).equals(company.getPassword())) {
					companyService.updateCompanyPassword(company.getUsername(), MdPwdUtil.MD5Password(newPwd2));
					request.getRequestDispatcher("/SystemsManagement/ShowNoticeListServlet").forward(request, response);
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
