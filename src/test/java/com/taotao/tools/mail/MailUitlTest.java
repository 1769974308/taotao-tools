package com.taotao.tools.mail;



import org.junit.Test;

import com.taotao.tools.pojo.MailSendBean;

public class MailUitlTest {

	@Test
	public void testSendmail() {
		MailSendBean sendBean = new MailSendBean();
		sendBean.setHost("smtp.qq.com");
		sendBean.setUserName("1769974308@qq.com");
		sendBean.setAuthorCode("mboqahgvbsmrehbf");
		sendBean.setReceiveTo("1769974308@qq.com");
		sendBean.setCcUser("");
		sendBean.setSubject("²âÊÔÓÊ¼ş");
		sendBean.setMailContent("²âÊÔÓÊ¼ş");
		sendBean.setEnclosure(true);
		sendBean.setAttachmentUrl("F:\\TKB±í.txt");
	
		MailUitl.sendmail(sendBean);
		
	}

}
