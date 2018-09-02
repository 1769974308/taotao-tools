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
 * 邮件的发送与接收
 * @author Administrator
 *
 */
public class MailUitl {

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
	public static void sendmail(MailSendBean mailSendBean)
	{
		try
		{
			Log4jUtil.i(null, "发送服务器:",mailSendBean.getHost());
			Log4jUtil.i(null, "发送邮箱:",mailSendBean.getUserName());
			Log4jUtil.i(null, "发件人邮箱生成授权码或密码:",mailSendBean.getAuthorCode());
			Log4jUtil.i(null, "收件人邮箱账号:",mailSendBean.getReceiveTo());
			Log4jUtil.i(null, "抄送人邮箱账号:",mailSendBean.getCcUser());
			Log4jUtil.i(null, "内容:",mailSendBean.getMailContent());
			Properties prop = new Properties();
			prop.setProperty("mail.host", mailSendBean.getHost());
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			// 使用JavaMail发送邮件的5个步骤
			// 1、创建session
			Session session = Session.getInstance(prop);
			// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
			session.setDebug(true);
			// 2、通过session得到transport对象
			Transport ts = session.getTransport();
			// 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
			ts.connect(mailSendBean.getHost(), mailSendBean.getUserName(), mailSendBean.getAuthorCode());
			// 4、创建邮件
			Message message = createSimpleMail(session,mailSendBean);
			// 5、发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 创建一封只包含文本的邮件
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createSimpleMail(Session session ,MailSendBean mailSendBean)
			throws Exception
	{
		// 创建邮件对象
		MimeMessage message = new MimeMessage(session);
		// 指明邮件的发件人
		message.setFrom(new InternetAddress(mailSendBean.getUserName()));
		// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailSendBean.getReceiveTo()));
		if (mailSendBean.isDuplicate())
		{
			@SuppressWarnings("static-access")
			InternetAddress[] internetAddressCC = new InternetAddress().parse(mailSendBean.getCcUser());
            message.setRecipients(Message.RecipientType.CC, internetAddressCC);
		}
		// 邮件的标题
		message.setSubject(mailSendBean.getSubject(),"UTF-8");
		// 邮件的文本内容
		
		//判断是否有附件
		if (!mailSendBean.isEnclosure()) {
			message.setContent(mailSendBean.getMailContent(), "text/html;charset=UTF-8");
		}else {
			//1、创建文本节点
			MimeBodyPart text = new MimeBodyPart();
			text.setContent(mailSendBean.getMailContent(), "text/html;charset=UTF-8");
			
			
			//2、创建附件节点
			MimeBodyPart attachment = new MimeBodyPart();
			//2.1、读取本地文件
			
			DataHandler dHandler = new DataHandler(new FileDataSource(mailSendBean.getAttachmentUrl()));
			//2.2、将附件数据添加到节点
			attachment.setDataHandler(dHandler);
			//2.3、设置附件文件名
			attachment.setFileName(MimeUtility.encodeText(dHandler.getName()));
			
			//3、设置文本和附件的关系
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(text);
			multipart.addBodyPart(attachment);
			multipart.setSubType("mixed");//混合关系
			
			message.setContent(multipart);
		}
		
		// 返回创建好的邮件对象
		return message;
	}

	
}
