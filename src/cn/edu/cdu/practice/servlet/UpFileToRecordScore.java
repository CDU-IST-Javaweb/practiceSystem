package cn.edu.cdu.practice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

import cn.edu.cdu.practice.service.StudentService;
import cn.edu.cdu.practice.service.impl.StudentServiceImpl;

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
			// 存储
			item.write(new File(fileFactory, fileName));
			System.out.println("存储成功" + getServletContext().getRealPath("WEB-INF/files"));
//			StudentService studentService = new StudentServiceImpl();
//			if (studentService.importStudent(getServletContext().getRealPath("WEB-INF/files") + "/" + fileName)) {
//				request.getSession().setAttribute("message", "恭喜您，导入成功");
//			} else {
//				request.getSession().setAttribute("message", "对不起，导入失败");
//			}
//			request.getRequestDispatcher("NewFile.jsp").forward(request, response);
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