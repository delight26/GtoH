package com.project.call.hyunsu.email;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailFileSender {
	
	@Autowired
	private JavaMailSender mailSender;
	 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	//객체이용시 파일 path를 같이 넣어주세요 
	public void sendEmail(Email email, String filePath) throws Exception{
		
		MimeMessage msg = mailSender.createMimeMessage();
	try{	
		MimeMessageHelper helper = new MimeMessageHelper(msg,true,"utf-8");
		helper.setSubject("회원 가입 안내 [Attachment]");
		String htmlContent="<strong>안녕하세요</strong>, 반갑습니다.";
		helper.setText(htmlContent,true);
		helper.setFrom("khskhss4563@gmail.com","김현수");

		helper.setTo(new InternetAddress(email.getReciver()));
		DataSource dataSource = new FileDataSource(filePath);
		helper.addAttachment(MimeUtility.encodeText("IJ.jpg","utf-8","B"), dataSource);
	}catch(Throwable e){
		
	}
		mailSender.send(msg);
	
	}
	
	
}
