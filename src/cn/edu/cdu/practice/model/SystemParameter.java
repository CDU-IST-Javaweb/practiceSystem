package cn.edu.cdu.practice.model;

/**
 * SystemParameter entity. @author MyEclipse Persistence Tools
 */

public class SystemParameter implements java.io.Serializable {

	// Fields

	private SystemParameterId id;

	// Constructors

	/** default constructor */
	public SystemParameter() {
	}

	/** full constructor */
	public SystemParameter(SystemParameterId id) {
		this.id = id;
	}

	// Property accessors

	public SystemParameterId getId() {
		return this.id;
	}

	public void setId(SystemParameterId id) {
		this.id = id;
	}

}