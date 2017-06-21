package cn.edu.cdu.practice.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import cn.edu.cdu.practice.model.ProjectSelect;
import cn.edu.cdu.practice.model.Student;

/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName TestDb.java
 * @version 1.0
 * @Description: Execel导出操作
 * @Author 陈天雄 @Date： 2017-5-13:下午4:09:37 Modification User： 程序修改时由修改人员编写
 *         Modification Date： 程序修改时间
 */
public class ExcelInUtil {
	static ClassLoader loader = ExcelInUtil.class.getClassLoader();

	public static String formatCell3(XSSFCell xssfCell) {
		if (xssfCell == null) {
			return "";
		}
		switch (xssfCell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			// 日期格式的处理
			if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue())).toString();
			}

			return String.valueOf(xssfCell.getNumericCellValue());

		// 字符串
		case HSSFCell.CELL_TYPE_STRING:
			return xssfCell.getStringCellValue();

		// 公式
		case HSSFCell.CELL_TYPE_FORMULA:
			return xssfCell.getCellFormula();

		// 空白
		case HSSFCell.CELL_TYPE_BLANK:
			return "";

		// 布尔取值
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return xssfCell.getBooleanCellValue() + "";

		// 错误类型
		case HSSFCell.CELL_TYPE_ERROR:
			return xssfCell.getErrorCellValue() + "";
		}

		return "";
	}

	/*
	 * 针对excel 2007
	 */
	public static List<Student> importStudentExcel(String file) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet hssfSheet = wb.getSheetAt(0);
		List<Student> list = new ArrayList<Student>();
		Student student = null;
		if (hssfSheet != null) {
			// 遍历excel,从第二行开始 即 rowNum=1,逐个获取单元格的内容,然后进行格式处理,最后插入数据库
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				student = new Student();
				student.setMailbox(formatCell3(hssfRow.getCell(1)));
				student.setGrade((int) hssfRow.getCell(2).getNumericCellValue());
				student.setLevel(formatCell3(hssfRow.getCell(3)));
				student.setProfessional(formatCell3(hssfRow.getCell(4)));
				student.setClass_(formatCell3(hssfRow.getCell(5)));
				student.setName(formatCell3(hssfRow.getCell(6)));
				student.setGender(formatCell3(hssfRow.getCell(7)));
				student.setNo(formatCell3(hssfRow.getCell(8)));
				list.add(student);
			}
			return list;
		} else {
			return null;
		}

	}

	/*
	 * @Test public void test() throws Exception { List<Student> list =
	 * ExcelInUtil.importStudentExcel("123.xlsx");
	 * System.out.println(list.size()); for(Student student : list) {
	 * System.out.println(student.getName() + " "+student.getGender() +
	 * " "+student.getNo() + " " +student.getMailbox()+" "+student.getLevel()
	 * +" "+student.getClass_()+" "+student.getProfessional()+
	 * student.getGrade()); } }
	 */

	public static List<String[]> importStudentScoreExcel(String file) throws Exception {
		XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
		XSSFSheet hssfSheet = wb.getSheetAt(0);
		List<String[]> list = new ArrayList<String[]>();
		Student student = null;
		if (hssfSheet != null) {
			//确定列所对应的对象属性
			int rowNum =0;
			
			// 遍历excel,从第二行开始 即 rowNum=1,逐个获取单元格的内容,然后进行格式处理,最后插入数据库
			for (rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					continue;
				}
				student = new Student();
				student.setMailbox(formatCell3(hssfRow.getCell(1)));
				student.setGrade((int) hssfRow.getCell(2).getNumericCellValue());
				student.setLevel(formatCell3(hssfRow.getCell(3)));
				student.setProfessional(formatCell3(hssfRow.getCell(4)));
				student.setClass_(formatCell3(hssfRow.getCell(5)));
				student.setName(formatCell3(hssfRow.getCell(6)));
				student.setGender(formatCell3(hssfRow.getCell(7)));
				student.setNo(formatCell3(hssfRow.getCell(8)));
				
			}
			return list;
		} else {
			return null;
		}

	}
}
