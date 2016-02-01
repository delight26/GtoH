package com.project.call.hyunsu.email;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	
	@Autowired
	private JavaMailSender mailSender;
	 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendEmail(Email email) throws Exception{
		
		MimeMessage msg = mailSender.createMimeMessage();
		msg.setSubject(email.getSubject());
		msg.setText(email.getContent());
		msg.setRecipient(RecipientType.TO, new InternetAddress(email.getReciver()));
		
		mailSender.send(msg);
	}
	
	
}
