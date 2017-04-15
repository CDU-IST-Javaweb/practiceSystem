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