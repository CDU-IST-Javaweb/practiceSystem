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

public class MailboxVerification implements java.io.Serializable {

	// Fields

	private String mailbox;
	private Integer type;
	private String verificationCode;

	// Constructors

	/** default constructor */
	public MailboxVerification() {
	}

	/** full constructor */
	public MailboxVerification(String mailbox, Integer type,
			String verificationCode) {
		this.mailbox = mailbox;
		this.type = type;
		this.verificationCode = verificationCode;
	}

	// Property accessors

	public String getMailbox() {
		return this.mailbox;
	}

	public void setMailbox(String mailbox) {
		this.mailbox = mailbox;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVerificationCode() {
		return this.verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

}