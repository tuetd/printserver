package com.pruvn.tools.common.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtil {

	public static void sendMail(String address, String message, String subject) {
		System.out.println(address+message+subject);
		try {
			Properties props = System.getProperties(); 
			// -- Attaching to default Session, or we could start a new one -- 
			props.put("mail.smtp.host", "128.235.109.3"); 
			Session session = Session.getDefaultInstance(props, null); 
			// -- Create a new message -- 
			Message msg = new MimeMessage(session); 
			// -- Set the FROM and TO fields -- 

				msg.setFrom(new InternetAddress("sys_monitor@prufinance.com.vn"));
			
			msg.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(address, false)); 
			// -- We could include CC recipients too -- 
			// if (cc != null) 
			// msg.setRecipients(Message.RecipientType.CC 
			// ,InternetAddress.parse(cc, false)); 
			// -- Set the subject and body text -- 
			msg.setSubject(subject); 
			
//			/ Create a message part to represent the body text
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setContent(message, "text/html; charset=utf-8");

	        // use a MimeMultipart as we need to handle the file attachments
	        Multipart multipart = new MimeMultipart();

	        // add the message body to the mime message
	        multipart.addBodyPart(messageBodyPart);

	        // add any file attachments to the message
//	        if (attachments != null && attachments.length > 0) {
//	        	addAtachments(attachments, multipart);
//	        }
	        
	        // Put all message parts in the message
	        msg.setContent(multipart);
			// -- Set some other header information -- 
			msg.setHeader("X-Mailer", "LOTONtechEmail"); 
			msg.setSentDate(new Date()); 
			// -- Send the message -- 
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}}
	
	public static void main(String[] args) {
		sendMail("hc.anh@prudential.com.vn", "Prudential Tài Chính gửi Quý Khách mật khẩu mới đăng nhập Website: {0}. Vì sự bảo mật thông tin của Quý khách, vui lòng không viết hoặc tiết lộ mã truy cập và mật khẩu cho ngư�?i khác.", "Prudential Tài Chính gửi lại mật khẩu");
	}

}
