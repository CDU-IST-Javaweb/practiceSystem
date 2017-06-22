package cn.edu.cdu.practice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.edu.cdu.practice.dao.impl.ProjectDaoImpl;
import cn.edu.cdu.practice.service.StudentService;
import cn.edu.cdu.practice.service.impl.ProjectServiceImpl;
import cn.edu.cdu.practice.service.impl.StudentServiceImpl;
import cn.edu.cdu.practice.utils.ExcelInUtil;

/**
 * Servlet implementation class UpFileToRecordScore
 */
@WebServlet("/PracticeManagement/UpFileToRecordScore")
public class UpFileToRecordScore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpFileToRecordScore() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 检测enctype是否是multipart/form-data
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (!isMultipart) {
			throw new RuntimeException("not multipart/form-data");
		}
		// 解析请求内容
		DiskFileItemFactory disk = new DiskFileItemFactory();// 产生FileItem的工厂
		ServletFileUpload su = new ServletFileUpload(disk);
		List<FileItem> item = new ArrayList<FileItem>();
		try {
			item = su.parseRequest(request);// 解析请求内容
		} catch (Exception e) {
			throw new RuntimeException("解析失败");
		}
		// 遍历
		for (FileItem item2 : item) {
			// 处理普通字段
			if (item2.isFormField()) {
				processFormField(item2);
			}
			// 处理表单字段
			else {
				try {
					processUploadField(item2, request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	// 处理表单字段
	private void processUploadField(FileItem item, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String c_username = (String) request.getSession().getAttribute("account");
			String role = (String) request.getSession().getAttribute("role");
			
			System.out.println("进入表单处理字段");
			InputStream in = item.getInputStream();
			String fileName = item.getName();// 确定上传文件名
			if (fileName != null) {
				fileName = FilenameUtils.getName(fileName);
				System.out.println(fileName);
			}
			// 找一个存放文件的位置
			String path = getServletContext().getRealPath("WEB-INF/files");
			// 确定存放的文件名
			File fileFactory = new File(path);
			if (!fileFactory.exists()) {
				fileFactory.mkdirs();
			}
			// 重命名，前缀为时间戳，防止相同文件覆盖
			fileName = c_username+"_"+Calendar.getInstance().getTime().getTime() + fileName;
			// 存储
			item.write(new File(fileFactory, fileName));
			System.out.println("存储成功" + getServletContext().getRealPath("WEB-INF/files"));
			ProjectDaoImpl projectDaoImpl = new ProjectDaoImpl();
			List<String[]> list = ExcelInUtil
					.importStudentScoreExcel(getServletContext().getRealPath("WEB-INF/files") + "/" + fileName);
			String list_pno = list.get(1)[0];
			
			ProjectServiceImpl projectServiceImpl = new ProjectServiceImpl();
			System.out.println("录入成绩的方案号: "+list_pno);
			if ((role.equals("1") && projectServiceImpl.findProjectBelongToUserByPNo(c_username, list_pno))) {

				if (projectDaoImpl.inputScore(list.get(0), list.get(2), list_pno)) {
					request.setAttribute("UpFileToRecordScoreResult", "  恭喜您，导入成功");
				} else {
					request.setAttribute("UpFileToRecordScoreResult", "  对不起，导入失败");
				}
				request.getRequestDispatcher("RecordScoreTypeChoice").forward(request, response);
			} else {
				// 跳转到404页面,并打印错误信息
				String errorMessage = "用户身份异常！";
				request.getSession().setAttribute("ErrorMessage", errorMessage);
				response.sendRedirect(request.getContextPath() + "/404.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 跳转到404页面,并打印错误信息
			String errorMessage = "访问文件出现异常！";
			request.getSession().setAttribute("ErrorMessage", errorMessage);
			response.sendRedirect(request.getContextPath() + "/404.jsp");
		}

	}

	// 处理普通字段
	private void processFormField(FileItem item) {
		String fieldName = item.getFieldName();
		String fieldValue = item.getString();
		System.out.println("普通字段的名称:" + fieldName + "*" + fieldValue);
	}
}