package cn.edu.cdu.practice.model;
/**
 * @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
 * @FileName Company.java
 * @version 1.0
 * @Description: model层，与数据表对应的实体类
 * @Author 于曦
 * @Date： 2017-4-15:上午15:04:04
 * Modification User： 程序修改时由修改人员编写
 * Modification Date： 程序修改时间
 */

public class ProjectCategory implements java.io.Serializable {

	// Fields

	private String category;
	private Integer orderno;

	// Constructors

	/** default constructor */
	public ProjectCategory() {
	}

	/** full constructor */
	public ProjectCategory(String category, Integer orderno) {
		this.category = category;
		this.orderno = orderno;
	}

	// Property accessors

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

}