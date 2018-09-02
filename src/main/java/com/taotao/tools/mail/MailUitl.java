package com.taotao.tools.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.taotao.tools.log4j.Log4jUtil;
import com.taotao.tools.pojo.MailSendBean;			

/**
 * �ʼ��ķ��������
 * @author Administrator
 *
 */
public class MailUitl {

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
	public static void sendmail(MailSendBean mailSendBean)
	{
		try
		{
			Log4jUtil.i(null, "���ͷ�����:",mailSendBean.getHost());
			Log4jUtil.i(null, "��������:",mailSendBean.getUserName());
			Log4jUtil.i(null, "����������������Ȩ�������:",mailSendBean.getAuthorCode());
			Log4jUtil.i(null, "�ռ��������˺�:",mailSendBean.getReceiveTo());
			Log4jUtil.i(null, "�����������˺�:",mailSendBean.getCcUser());
			Log4jUtil.i(null, "����:",mailSendBean.getMailContent());
			Properties prop = new Properties();
			prop.setProperty("mail.host", mailSendBean.getHost());
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			// ʹ��JavaMail�����ʼ���5������
			// 1������session
			Session session = Session.getInstance(prop);
			// ����Session��debugģʽ�������Ϳ��Բ鿴��������Email������״̬
			session.setDebug(true);
			// 2��ͨ��session�õ�transport����
			Transport ts = session.getTransport();
			// 3��ʹ��������û��������������ʼ��������������ʼ�ʱ����������Ҫ�ύ������û����������smtp���������û��������붼ͨ����֤֮����ܹ����������ʼ����ռ��ˡ�
			ts.connect(mailSendBean.getHost(), mailSendBean.getUserName(), mailSendBean.getAuthorCode());
			// 4�������ʼ�
			Message message = createSimpleMail(session,mailSendBean);
			// 5�������ʼ�
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * ����һ��ֻ�����ı����ʼ�
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session ,MailSendBean mailSendBean)
			throws Exception
	{
		// �����ʼ�����
		MimeMessage message = new MimeMessage(session);
		// ָ���ʼ��ķ�����
		message.setFrom(new InternetAddress(mailSendBean.getUserName()));
		// ָ���ʼ����ռ��ˣ����ڷ����˺��ռ�����һ���ģ��Ǿ����Լ����Լ���
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailSendBean.getReceiveTo()));
		if (mailSendBean.isDuplicate())
		{
			@SuppressWarnings("static-access")
			InternetAddress[] internetAddressCC = new InternetAddress().parse(mailSendBean.getCcUser());
            message.setRecipients(Message.RecipientType.CC, internetAddressCC);
		}
		// �ʼ��ı���
		message.setSubject(mailSendBean.getSubject(),"UTF-8");
		// �ʼ����ı�����
		
		//�ж��Ƿ��и���
		if (!mailSendBean.isEnclosure()) {
			message.setContent(mailSendBean.getMailContent(), "text/html;charset=UTF-8");
		}else {
			//1�������ı��ڵ�
			MimeBodyPart text = new MimeBodyPart();
			text.setContent(mailSendBean.getMailContent(), "text/html;charset=UTF-8");
			
			
			//2�����������ڵ�
			MimeBodyPart attachment = new MimeBodyPart();
			//2.1����ȡ�����ļ�
			
			DataHandler dHandler = new DataHandler(new FileDataSource(mailSendBean.getAttachmentUrl()));
			//2.2��������������ӵ��ڵ�
			attachment.setDataHandler(dHandler);
			//2.3�����ø����ļ���
			attachment.setFileName(MimeUtility.encodeText(dHandler.getName()));
			
			//3�������ı��͸����Ĺ�ϵ
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(text);
			multipart.addBodyPart(attachment);
			multipart.setSubType("mixed");//��Ϲ�ϵ
			
			message.setContent(multipart);
		}
		
		// ���ش����õ��ʼ�����
		return message;
	}

	
}
