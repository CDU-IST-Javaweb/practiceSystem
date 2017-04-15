package cn.edu.cdu.practice.model;

import java.sql.Timestamp;

/**
 * ProjectSelect entity. @author MyEclipse Persistence Tools
 */

public class ProjectSelect implements java.io.Serializable {

	// Fields

	private ProjectSelectId id;
	private String selReason;
	private Timestamp companySelDate;
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
			Timestamp companySelDate, String score, String companyName) {
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

	public Timestamp getCompanySelDate() {
		return this.companySelDate;
	}

	public void setCompanySelDate(Timestamp companySelDate) {
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