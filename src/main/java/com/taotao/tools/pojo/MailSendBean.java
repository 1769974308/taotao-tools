package com.taotao.tools.pojo;

public class MailSendBean {
	
	/**
	 * 
	 * @param host ���ͷ�����host(QQ���䣺smtp.qq.com;��Ѷ��ҵ���䣺smtp.exmail.qq.com)
	 * @param userName �����������˺�
	 * @param authorCode ����������������Ȩ������루������������->����POP3/IMAP/SMTP��->������Ȩ�룩
	 * @param receiveTo  �ռ��������˺�
	 * @param ccUser  �����������˺ţ�������ʼ�ע��ӡ�,����xxx@qq.com,xxx@163.com
	 * @param subject  ˵���ʼ�����
	 * @param mailContent  �ʼ�����
	 */
	
	private String host;
	
	private String userName;
	
	private String authorCode;
	
	private String receiveTo;
	
	private String ccUser;
	
	private String subject;
	
	private String mailContent;
	/**
	 * �Ƿ���
	 */
	private boolean isDuplicate;
	/**
	 * �Ƿ��и���
	 */
	private boolean isEnclosure;
	/**
	 * �������·��
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
