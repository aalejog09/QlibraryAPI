package com.api.qlibrary.services;




import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.api.qlibrary.services.interfaces.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService{

    @Value("${email.sender}")
    private String emailUser;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String toUser, String subject, String message) {
    	
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(emailUser);
        mailMessage.setTo(toUser);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

    @Override
    public void sendEmailWithFile(String toUser, String subject, String message, File file) throws javax.mail.MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());

		mimeMessageHelper.setFrom(emailUser);
		mimeMessageHelper.setTo(toUser);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(message);
		mimeMessageHelper.addAttachment(file.getName(), file);

		mailSender.send(mimeMessage);
    }
    
    

}
