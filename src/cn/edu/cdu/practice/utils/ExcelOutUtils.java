package cn.edu.cdu.practice.utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
 /**
  * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
  * @FileName TestDb.java
  * @version 1.0
  * @Description: Execel导出操作
  * @Author 陈天雄
  * @Date： 2017-4-16:下午5:05:48
  * Modification User： 程序修改时由修改人员编写
  * Modification Date： 程序修改时间
  */
@SuppressWarnings("hiding")
public class ExcelOutUtils<T> {
	public HSSFCellStyle getCellStyle(HSSFWorkbook workbook,boolean isHeader){
	 	//设置表格属性
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLocked(true);
        if (isHeader) {
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.BLACK.index);
            font.setFontHeightInPoints((short) 12);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            style.setFont(font);
        }        
        return style;
    }
    
   
    public  void generateHeader(HSSFWorkbook workbook,HSSFSheet sheet,String[] headerColumns){
        //样式对象
    	HSSFCellStyle style = getCellStyle(workbook,true);
    	//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        Row row = sheet.createRow(0);
        row.setHeightInPoints(30);
        //设置header值
        for(int i=0;i<headerColumns.length;i++){
        	//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            Cell cell = row.createCell(i);
            //传入的header字段是值_#_width
            String[] column = headerColumns[i].split("_#_");
            sheet.setColumnWidth(i, Integer.valueOf(column[1]));
            cell.setCellValue(column[0]);
            cell.setCellStyle(style);
        }
    }    
    
    public  HSSFSheet creatAuditSheet(HSSFWorkbook workbook,String sheetName,
            List<T> ans,String[] headerColumns,String[] fieldColumns) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	//建立新的sheet对象（excel的表单）
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表单header字段
        generateHeader(workbook,sheet,headerColumns); 
        HSSFCellStyle style = getCellStyle(workbook,false);
        //格式化时间的类
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        int rowNum = 0;
        for(T t:ans){
        	//根据传入的类的数量决定行数
            rowNum++ ;
            Row row = sheet.createRow(rowNum); 
            row.setHeightInPoints(25);
            //获取类对应字段的属性的值
            for(int i = 0; i < fieldColumns.length; i++){               
                String fieldName = fieldColumns[i] ;              
               
                //对应字段的get方法，如getName
                String getMethodName = "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);                   
                try {                    
                    Class clazz = t.getClass();
                    //获得该类的方法
                    Method getMethod = clazz.getMethod(getMethodName, new Class[]{} );
                    
                    Object value = getMethod.invoke(t, new Object[]{});
                    String cellValue = "";
                    if (value instanceof Date){
                        Date date = (Date)value;
                        cellValue = sd.format(date);
                    }else{ 
                        cellValue = null != value ? value.toString() : "";
                    }                    
                    Cell cell = row.createCell(i);
                    cell.setCellStyle(style);
                    cell.setCellValue(cellValue);
                } catch (Exception e) {
                    
                } 
            }            
        }
        return sheet;        
    }
    
    /**
     * 使用示例
     * 
public static final String[] RECORES_COLUMNS = new String[]{//标题名称（第一行的每一列单元格名称）
        "User Name_#_3000",
        "Address_#_7000"           
        };
public static final String[] RECORES_FIELDS = new String[]{//要输出类中的成员值
    "name","address"
};

public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
    List<User> users = new ArrayList<User>();
    for(int i=0; i<10;i++){
        User u = new User();
        u.setAddress("address :" + i);
        u.setName("name: "+ i);
        u.setAge(i);
        users.add(u);
    }
    
    //实际项目中，这个list 估计是从数据库中得到的
    HSSFWorkbook workbook = new HSSFWorkbook();
    ExcelOutUtils<User> userSheet = new ExcelOutUtils<User>();
    userSheet.creatAuditSheet(workbook, "user sheet xls", 
            users, RECORES_COLUMNS, RECORES_FIELDS);
    FileOutputStream fileOut = new FileOutputStream("D:/test.xls"); 
    workbook.write(fileOut); 
    fileOut.close(); 
}
     */

}
