package cn.edu.cdu.practice.model;

import java.sql.Date;

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

public class ProjectSelect implements java.io.Serializable {

	// Fields

	private ProjectSelectId id;
	private String selReason;
	private Date companySelDate;
	private String score;
	private String companyName;

	// Constructors

	/** default constructor */
	public ProjectSelect() {
	}

	/** minimal constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companyName = companyName;
	}

	/** full constructor */
	public ProjectSelect(ProjectSelectId id, String selReason,
			Date companySelDate, String score, String companyName) {
		this.id = id;
		this.selReason = selReason;
		this.companySelDate = companySelDate;
		this.score = score;
		this.companyName = companyName;
	}

	// Property accessors

	public ProjectSelectId getId() {
		return this.id;
	}

	public void setId(ProjectSelectId id) {
		this.id = id;
	}

	public String getSelReason() {
		return this.selReason;
	}

	public void setSelReason(String selReason) {
		this.selReason = selReason;
	}

	public Date getCompanySelDate() {
		return this.companySelDate;
	}

	public void setCompanySelDate(Date companySelDate) {
		this.companySelDate = companySelDate;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}