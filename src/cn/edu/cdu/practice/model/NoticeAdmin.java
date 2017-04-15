package cn.edu.cdu.practice.model;

import java.sql.Timestamp;

/**
 * NoticeAdmin entity. @author MyEclipse Persistence Tools
 */

public class NoticeAdmin implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp releaseDate;
	private String content;

	// Constructors

	/** default constructor */
	public NoticeAdmin() {
	}

	/** full constructor */
	public NoticeAdmin(Timestamp releaseDate, String content) {
		this.releaseDate = releaseDate;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Timestamp releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}