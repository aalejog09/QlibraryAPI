package com.api.qlibrary.services.interfaces;


import java.io.File;

import javax.mail.MessagingException;

public interface IEmailService {

    void sendEmail(String toUser, String subject, String message);

    void sendEmailWithFile(String toUser, String subject, String message, File file) throws MessagingException;
}