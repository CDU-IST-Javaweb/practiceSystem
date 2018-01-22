package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.edu.cdu.practice.model.MailboxVerification;
import cn.edu.cdu.practice.service.CompanyService;
import cn.edu.cdu.practice.service.impl.CompanyServiceImpl;
import cn.edu.cdu.practice.utils.EmailUtils;
import cn.edu.cdu.practice.utils.IdentifyCodeUtils;

/**
 * Servlet implementation class SendMailServlet
 */
@WebServlet("/Login/SendMailServlet")
public class SendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendMailServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mbemail = request.getParameter("mbemail");
		CompanyService companyService = new CompanyServiceImpl();
		PrintWriter out = response.getWriter();
			String identifyCode = IdentifyCodeUtils.getCode();
			System.out.println("验证码是:"+identifyCode);
			EmailUtils.sendMail(mbemail, 1, identifyCode);
			MailboxVerification mailboxVerification = new MailboxVerification(mbemail, 1, identifyCode);
			if (companyService.setMail_verification(mailboxVerification)) {
				out.write(identifyCode);
			}
			else 
				out.write("error");
		}

}
