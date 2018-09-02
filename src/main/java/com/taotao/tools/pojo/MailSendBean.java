package com.taotao.tools.pojo;

public class MailSendBean {
	
	/**
	 * 
	 * @param host 发送服务器host(QQ邮箱：smtp.qq.com;腾讯企业邮箱：smtp.exmail.qq.com)
	 * @param userName 发件人邮箱账号
	 * @param authorCode 发件人邮箱生成授权码或密码（进入邮箱设置->开启POP3/IMAP/SMTP等->生成授权码）
	 * @param receiveTo  收件人邮箱账号
	 * @param ccUser  抄送人邮箱账号：（多个邮件注意加‘,’）xxx@qq.com,xxx@163.com
	 * @param subject  说明邮件主题
	 * @param mailContent  邮件内容
	 */
	
	private String host;
	
	private String userName;
	
	private String authorCode;
	
	private String receiveTo;
	
	private String ccUser;
	
	private String subject;
	
	private String mailContent;
	/**
	 * 是否抄送
	 */
	private boolean isDuplicate;
	/**
	 * 是否有附件
	 */
	private boolean isEnclosure;
	/**
	 * 附件存放路径
	 */
	private String attachmentUrl;

	
	public MailSendBean() {
		super();
		this.isDuplicate = false;
		this.isEnclosure = false;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String authorCode) {
		this.authorCode = authorCode;
	}

	public String getReceiveTo() {
		return receiveTo;
	}

	public void setReceiveTo(String receiveTo) {
		this.receiveTo = receiveTo;
	}

	public String getCcUser() {
		return ccUser;
	}

	public void setCcUser(String ccUser) {
		this.ccUser = ccUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}

	public boolean isDuplicate() {
		return isDuplicate;
	}

	public void setDuplicate(boolean isDuplicate) {
		this.isDuplicate = isDuplicate;
	}

	public boolean isEnclosure() {
		return isEnclosure;
	}

	public void setEnclosure(boolean isEnclosure) {
		this.isEnclosure = isEnclosure;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	

}
