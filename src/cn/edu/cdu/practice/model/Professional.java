package cn.edu.cdu.practice.model;

/**
 * Professional entity. @author MyEclipse Persistence Tools
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