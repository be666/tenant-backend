package com.imethod.sites.web.email.service;
import com.imethod.sites.web.email.bean.EmailMessage;
import com.imethod.sites.web.email.bean.MailAuthenticator;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService{
	
	/**
	 * 发送HTML格式的邮件
	 */
	public void sendHtmlMail(EmailMessage emailMessage)
	{
		Date sendDate = new Date();
		Properties prop = new Properties();

		Authenticator auth = null;
		if (emailMessage.isValidate())
		{
			auth = new MailAuthenticator();
		}
		prop.put("mail.smtp.host", emailMessage.getEmailHost());
		prop.put("mail.smtp.auth", "true");   
		
		//设置对话和邮件服务器进行通讯
		Session session = Session.getDefaultInstance(prop, auth);   
		
		//设置邮件对象
		Message message = new MimeMessage(session);   
		try 
		{   
			//设置邮件主题
			message.setSubject(emailMessage.getEmailSubject());
			//设置邮件标题
			message.setHeader("Header", emailMessage.getEmailHeader());
			//设置发送时间
			message.setSentDate(sendDate);
			
			//设置发信人地址  和 名字
			Address address = new InternetAddress(emailMessage.getFromEmail(), emailMessage.getFromName());
			//把发件人信息添加到信息中
			message.setFrom(address);

			if(emailMessage.getEmailToList().size()==0){
				return;
			}else {
				//设置收件人
				for(String emailTo : emailMessage.getEmailToList()){
					Address toAddress = new InternetAddress(emailTo);
					message.addRecipient(Message.RecipientType.TO,toAddress);
				}
			}
			message.setContent(emailMessage.getEmailContent(), emailMessage.getHtmlContentType());

			message.saveChanges();
			System.out.println("doSendHtmlMail() 开始发送邮件……");   
			Transport.send(message);   
			System.out.println("发送成功！");   
		} catch (Exception e)
		{   
				System.out.println("出错");   
				e.printStackTrace();   
		}   
	}   

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        //new SendEmail().doSendNormalMail();
//		new SendEmail().doSendHtmlMail();
		//new SendEmail().doSendAttachmentMail();
	}

}
