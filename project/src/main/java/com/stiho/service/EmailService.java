package com.stiho.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author IS204_1
 */
@Service
public class EmailService {

    /**
     * class to send email to customer.
     *
     */
    @Autowired
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(final String fromEmail, final String toEmail, final String subject, final String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);

            MimeMessageHelper msgHelper = new MimeMessageHelper(message, true);
            msgHelper.setTo(toEmail);
            msgHelper.setFrom(fromEmail);
            msgHelper.setText(text, true);
            mailSender.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void send(final String fromEmail, final String toEmail, final String subject, final String text, final String ccEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(subject);

            MimeMessageHelper msgHelper = new MimeMessageHelper(message, true);
            msgHelper.setTo(toEmail);
            msgHelper.setCc(ccEmail);
            msgHelper.setFrom(fromEmail);
            msgHelper.setText(text, true);
            mailSender.send(message);

        } catch (MessagingException ex) {
            Logger.getLogger(EmailService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
