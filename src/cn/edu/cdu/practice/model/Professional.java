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

public class Professional implements java.io.Serializable {

	// Fields

	private String professional;
	private Integer orderno;

	// Constructors

	/** default constructor */
	public Professional() {
	}

	/** full constructor */
	public Professional(String professional, Integer orderno) {
		this.professional = professional;
		this.orderno = orderno;
	}

	// Property accessors

	public String getProfessional() {
		return this.professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public Integer getOrderno() {
		return this.orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

}