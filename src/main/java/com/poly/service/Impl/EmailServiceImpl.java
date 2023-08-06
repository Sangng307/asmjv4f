package com.poly.service.Impl;

import javax.servlet.ServletContext;

import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.EmailService;
import com.poly.util.SendMailUtil;

public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_WELCOME_SUBJECT = "Chao mung toi video online";
    private static final String EMAIL_FORGOT_SUBJECT = "Video online - mat khau moi";
    private static final String EMAIL_SHARE_SUBJECT = "Video online - share link";
    @Override
    public void sendEmail(ServletContext context, User recipient, String type) {
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");

        try {
            String content = null;
            String subject = null;
            switch (type) {
                case "welcome":
                    subject = EMAIL_WELCOME_SUBJECT;
                    content = "Kinh gui " + recipient.getUsername() + ", chuc mung ban den voi video online";
                    break;
                case "forgot":
                    subject = EMAIL_FORGOT_SUBJECT;
                    content = "Kinh gui " + recipient.getUsername() + ", day la mat khau cua ban: " + recipient.getPassword();
                    break;
            }
            SendMailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

	@Override
	public void sendShare(ServletContext context, String email, String entity) {
		// TODO Auto-generated method stub
		String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        try {
            String content = null;
            String subject = null;
           
                    subject = EMAIL_SHARE_SUBJECT;
                    content = "Video link: " + entity;
                   
            SendMailUtil.sendEmail(host, port, user, pass, email, subject, content);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}