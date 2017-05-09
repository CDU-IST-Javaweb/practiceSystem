package cn.edu.cdu.practice.service;

import java.util.List;

import cn.edu.cdu.practice.model.Student;

/** 
* @author  作者 E-mail: 
* @date 创建时间：2017年5月7日 上午1:19:19 
* @FileName:StudentService.java
* @version 1.0 
* @Description: 学生信息管理操作接口 
* @Author: 刘永红
* @Modification User:
* @Modification Date:上午1:19:19
*/
public interface StudentService {
	/**
	 * 插入一个学生记录
	 * @param: student 一个学生实体
	 * @return: true 插入成功；false 插入失败
	 */
	public boolean insert(Student student);
	
	/**
	 * 查询所有学生记录
	 * @return: 所有学生实体列表
	 */
	public List<Student> findAll();
	
	/**
	 * 查询指定学号学生记录
	 * @param id 学号
	 * @return 查到学生实体
	 */
	public Student findById(String id);
	
	/**
	 * 查询已选/未选学生（涉及多表查询，学生表+方案选择表）
	 * @param flag：1，查询已选学生；2，查询未选学生
	 * @return 查到学生实体列表
	 */
	public List<Student> findBySelected(int flag);
	
	/**
	 * 管理员根据企业名称查询学生信息
	 * @param companyName 企业名称
	 * @return 查找到学生实体列表
	 */
	public List<Student> findByCompany(String companyName);
	
	/**
	 * 查询指定年级的学生记录
	 * @param grade 年级
	 * @return 查到学生实体列表
	 */
	public List<Student> findByGrade(int grade);
	
	/**
	 * 查询指定专业的学生
	 * @param major 专业名称
	 * @return 查到学生实体列表
	 */
	public List<Student> findByMajor(String major);
	
	/**
	 * 管理员按年度查询学生记录（方案号中隐含年度）
	 * @param year 年度
	 * @return 查到学生实体列表
	 */
	public List<Student> findByYear(int year);
	
	/**
	 * 更新学生记录
	 * @param student 一个学生实体
	 * @return true:成功；false：失败
	 */
	public boolean update(Student student);
	
    /**
     * 删除指定学生
     * @param id 学号
     * @return true 删除成功；false 删除失败
     */
	public boolean delete(String id);
	
	/**
	 * 导入学生信息（从Excel文件中导入）
	 * @param fileName 文件名
	 * @return true 成功；false 失败
	 */
	public boolean importStudent(String fileName);
	
	/**
	 * 导出学生信息（导出到Excel文件中）
	 * @param fileName
	 * @return
	 */
	public boolean exportStudent(String fileName);
}
