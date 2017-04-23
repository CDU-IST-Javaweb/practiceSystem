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

public class NoticeCompany implements java.io.Serializable {

	// Fields
	private String title;
	private Integer id;
	private String companyUsername;
	private Date releaseDate;
	private Date auditDate;
	private String content;

	// Constructors

	/** default constructor */
	public NoticeCompany() {
	}

	/** minimal constructor */
	public NoticeCompany(String companyUsername, Date releaseDate,
			String content) {
		this.companyUsername = companyUsername;
		this.releaseDate = releaseDate;
		this.content = content;
	}

	/** full constructor */
	public NoticeCompany(String companyUsername, Date releaseDate,
			Date auditDate, String content) {
		this.companyUsername = companyUsername;
		this.releaseDate = releaseDate;
		this.auditDate = auditDate;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyUsername() {
		return this.companyUsername;
	}

	public void setCompanyUsername(String companyUsername) {
		this.companyUsername = companyUsername;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	

}