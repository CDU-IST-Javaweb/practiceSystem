package cn.edu.cdu.practice.model;

import java.sql.Timestamp;

/**
 * NoticeCompany entity. @author MyEclipse Persistence Tools
 */

public class NoticeCompany implements java.io.Serializable {

	// Fields

	private Integer id;
	private String companyUsername;
	private Timestamp releaseDate;
	private Timestamp auditDate;
	private String content;

	// Constructors

	/** default constructor */
	public NoticeCompany() {
	}

	/** minimal constructor */
	public NoticeCompany(String companyUsername, Timestamp releaseDate,
			String content) {
		this.companyUsername = companyUsername;
		this.releaseDate = releaseDate;
		this.content = content;
	}

	/** full constructor */
	public NoticeCompany(String companyUsername, Timestamp releaseDate,
			Timestamp auditDate, String content) {
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

	public Timestamp getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Timestamp getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Timestamp auditDate) {
		this.auditDate = auditDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}