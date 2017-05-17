package cn.edu.cdu.practice.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.cdu.practice.dao.impl.NoticeDaoImpl;
import cn.edu.cdu.practice.model.NoticeAdmin;
import cn.edu.cdu.practice.model.NoticeCompany;

/**
 * Servlet implementation class NoticeInfoService
 */
@WebServlet("/NoticeInfoService")
public class NoticeInfoService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInfoService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("type");
		String id=request.getParameter("id");
		if(type!=null&&id!=null){
			NoticeDaoImpl noticeDaoImpl=new NoticeDaoImpl();
			if(type.equals("1")){
				//学院公告
				NoticeAdmin noticeAdmin=noticeDaoImpl.queryNoticeAdminById(Integer.parseInt(id));
				request.setAttribute("noticeAdmin", noticeAdmin);
				request.setAttribute("notice", noticeAdmin);
			}else if(type.equals("2")){
				//企业公告
				NoticeCompany noticeCompany=noticeDaoImpl.queryNoticeById(Integer.parseInt(id));
				request.setAttribute("noticeCompany", noticeCompany);
				request.setAttribute("notice", noticeCompany);
			}
			request.getRequestDispatcher("/newDetails.jsp").forward(request, response);
		}else{
			response.sendRedirect("http://202.115.82.8:8080/404.jsp");
			//request.getRequestDispatcher("/404.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
