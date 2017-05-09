package cn.edu.cdu.practice.model;
/** 
* @Copyright (C), 2017, 成都大学信息科学与工程学院JavaWeb教材编写组.
* @date 创建时间：2017年5月2日 下午6:26:58 
* @FileName:ProProSelStuView.java
* @version 1.0 
* @Description:  
* @Author:杨永浩
* @Modification User:
* @Modification Date:
*/
public class ProProSelStuView {
	private Project project;
	private ProjectSelect projectSelect;
	private Student student;
	
	public ProProSelStuView(){
		project=new Project();
		projectSelect=new ProjectSelect();
		student=new Student();
	}
	
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public ProjectSelect getProjectSelect() {
		return projectSelect;
	}
	public void setProjectSelect(ProjectSelect projectSelect) {
		this.projectSelect = projectSelect;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
}

