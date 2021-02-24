package com.pruvn.rms.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.jdbc.StringUtils;

public class MailUtil {

	public static void sendMail(String address, String message, String subject) {
		InputStream input = null;
		try {
			Properties props = System.getProperties(); 
			// -- Attaching to default Session, or we could start a new one -- 
			input = MailUtil.class.getClassLoader().getResourceAsStream("config.properties");
			//new FileInputStream("config.properties");
			 
			// load a properties file
			props.load(input);
			//props.put("mail.smtp.host", "128.235.109.3"); 
			Session session = Session.getDefaultInstance(props, null);
			// -- Create a new message -- 
			Message msg = new MimeMessage(session); 
			// -- Set the FROM and TO fields -- 
			//msg.setFrom(new InternetAddress("sys_monitor@prufinance.com.vn")); 
			msg.setFrom(new InternetAddress(props.getProperty("mail.smtp.sender"))); 
			msg.setRecipients(Message.RecipientType.TO, 
					InternetAddress.parse(address, false)); 
			// -- We could include CC recipients too -- 
			// if (cc != null) 
			// msg.setRecipients(Message.RecipientType.CC 
			// ,InternetAddress.parse(cc, false)); 
			// -- Set the subject and body text -- 
			msg.setSubject(subject); 
			msg.setText(message); 
			// -- Set some other header information -- 
			msg.setHeader("X-Mailer", "LOTONtechEmail"); 
			msg.setSentDate(new Date()); 
			// -- Send the message -- 
			Transport.send(msg);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static boolean isValidEmailAddress(String aEmailAddress){
	    if (aEmailAddress == null) return false;
	    boolean result = true;
	    try {
	      InternetAddress emailAddr = new InternetAddress(aEmailAddress);
	      if ( ! hasNameAndDomain(aEmailAddress) ) {
	        result = false;
	      }
	    }
	    catch (AddressException ex){
	      result = false;
	    }
	    return result;
	  }

	  private static boolean hasNameAndDomain(String aEmailAddress){
	    String[] tokens = aEmailAddress.split("@");
	    return 
	     tokens.length == 2 &&
	     !StringUtils.isEmptyOrWhitespaceOnly( tokens[0] ) && 
	     !StringUtils.isEmptyOrWhitespaceOnly( tokens[1] ) ;
	  }
}

