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
 * Servlet implementation class ReviewCompanyServlet
 */
@WebServlet("/EnterpriseManagement/ReviewOOOCompanyServlet")
public class ReviewCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReviewCompanyServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//得到传入的userName参数
		String userName = request.getParameter("userName");
		System.out.println(userName+"123");
		//校验
		if (ValidateUtils.validate(userName) || userName == null) {
			System.out.println("有过滤参数");
			request.getRequestDispatcher("/404.html").forward(request, response);
			return ;
		}
		Company company = null ;
		CompanyService companyService = new CompanyServiceImpl();
		try {
			company = companyService.queryByUserName(userName);
			//根据用户名取出的Company对象为空
			if (company == null) {
				System.out.println("对象为空");
				request.getRequestDispatcher("/404.html").forward(request, response);
				return;
			}
			else {
				//如果审核日期不为空，那么意味着退审
				if (company.getAuditDate() != null) {
					company.setAuditDate(null);
					if (companyService.backReview(company)) {
						request.getRequestDispatcher("/EnterpriseManagement/ShowCompanyssServlet").forward(request, response);
					}else {
						request.getRequestDispatcher("/404.html").forward(request, response);
						return ;
					}
				}
				else {
					System.out.println(company.getCompanyName());
					//日期转换
					java.util.Date date = new java.util.Date();
					java.sql.Date sqlDate=new java.sql.Date(date.getTime());
					company.setAuditDate(sqlDate);
					//如果审核成功
					if (companyService.checkCompany(company)) {
						request.getRequestDispatcher("/EnterpriseManagement/ShowCompanyssServlet").forward(request, response);
					}
					else {
						request.getRequestDispatcher("/404.html").forward(request, response);
						return ;
					}
				}
			}
		}catch(Exception e) {
			Log4jUtils.info(e.getMessage());
			request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

}
