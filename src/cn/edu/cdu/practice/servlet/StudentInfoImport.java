package cn.edu.cdu.practice.servlet;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.edu.cdu.practice.utils.IdGenertor;

/**
 * Servlet implementation class StudentInfoImport
 */
@WebServlet("/StudentManagement/StudentInfoImport")
public class StudentInfoImport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfoImport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入学生信息导入的servlet");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//检测form是否是multipart/form-data类型的
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(!isMultipart){
			throw new RuntimeException("The form's enctype attribute value must be multipart/form-data");
		}
		//解析请求内容
		DiskFileItemFactory factory = new DiskFileItemFactory();//产生FileItem的工厂
		factory.setRepository(new File("d:/"));//指定临时文件的存放目录
		ServletFileUpload sfu = new ServletFileUpload(factory);
		
//		sfu.setFileSizeMax(3*1024*1024);//单个文件大小限制
//		sfu.setSizeMax(5*1024*1024);//总文件大小
		System.out.println("hello");
		List<FileItem> items = new ArrayList<FileItem>();
		try {
			items = sfu.parseRequest(request);
		}catch(FileUploadBase.FileSizeLimitExceededException e) {
			response.getWriter().write("单个文件不能超过3M");
		}
		catch(FileUploadBase.SizeLimitExceededException e) {
			response.getWriter().write("总文件不能超过5M");
		}catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException("解析请求失败");
		}
		//遍历：
		for(FileItem item:items){
			//处理普通字段
			if(item.isFormField()){
				processFormField(item);
				System.out.println("hello2");
			}else{
			//处理上传字段
				processUploadField(item);
				System.out.println("hello3");
			}
		}
	}

	protected void processUploadField(FileItem item) {
		try {
			
			
//			InputStream in = item.getInputStream();
			//找一个存放文件的位置；存放的文件名
			String fileName = item.getName();//上传的文件的文件名  C:\Users\wzhting\Desktop\a.txt  a.txt(浏览器不同)
			if(fileName!=null&&!fileName.equals("")){
				fileName = FilenameUtils.getName(fileName);
				fileName = IdGenertor.genGUID()+"_"+fileName;
				
				//存放路径
				String realPath = getServletContext().getRealPath("/WEB-INF/files");
				
				//生成一个子目录
				String childDirectory = genChildDirectory(realPath,fileName);
				
				File storeDirectory = new File(realPath+File.separator+childDirectory);
				if(!storeDirectory.exists()){
					storeDirectory.mkdirs();
					System.out.println("success");
				}
//				OutputStream out = new FileOutputStream(new File(storeDirectory, fileName));
//				
//				int len = -1;
//				byte b[] = new byte[1024];
//				while((len=in.read(b))!=-1){
//					out.write(b, 0, len);
//				}
//				in.close();
//				out.close();
//				
//				item.delete();//清除临时文件
				
				item.write(new File(storeDirectory, fileName));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String genChildDirectory(String realPath, String fileName) {
		int hashCode = fileName.hashCode();
		int dir1 = hashCode&0xf;
		int dir2 = (hashCode&0xf0)>>4;
		
		String str = dir1+File.separator+dir2;
		
		File file = new File(realPath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return str;
		
	}

	//按照日期生成子目录
	private String genChildDirectory(String realPath) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(now);
		
		File file = new File(realPath,str);
		if(!file.exists()){
			file.mkdirs();
		}
		
		return str;
	}

	protected void processFormField(FileItem item) {
		//打印到控制台
		String fieldName = item.getFieldName();
		String fieldValue = "";
		try {
			fieldValue = item.getString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(fieldName+"="+fieldValue);
	}
}
