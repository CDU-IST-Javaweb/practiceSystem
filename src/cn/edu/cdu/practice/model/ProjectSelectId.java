package cn.edu.cdu.practice.model;

/**
 * ProjectSelectId entity. @author MyEclipse Persistence Tools
 */

public class ProjectSelectId implements java.io.Serializable {

	// Fields

	private String studentNo;
	private Integer projectNo;

	// Constructors

	/** default constructor */
	public ProjectSelectId() {
	}

	/** full constructor */
	public ProjectSelectId(String studentNo, Integer projectNo) {
		this.studentNo = studentNo;
		this.projectNo = projectNo;
	}

	// Property accessors

	public String getStudentNo() {
		return this.studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(Integer projectNo) {
		this.projectNo = projectNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProjectSelectId))
			return false;
		ProjectSelectId castOther = (ProjectSelectId) other;

		return ((this.getStudentNo() == castOther.getStudentNo()) || (this
				.getStudentNo() != null && castOther.getStudentNo() != null && this
				.getStudentNo().equals(castOther.getStudentNo())))
				&& ((this.getProjectNo() == castOther.getProjectNo()) || (this
						.getProjectNo() != null
						&& castOther.getProjectNo() != null && this
						.getProjectNo().equals(castOther.getProjectNo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStudentNo() == null ? 0 : this.getStudentNo().hashCode());
		result = 37 * result
				+ (getProjectNo() == null ? 0 : this.getProjectNo().hashCode());
		return result;
	}

}