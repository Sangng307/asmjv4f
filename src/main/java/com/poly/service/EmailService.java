package com.poly.service;

import javax.servlet.ServletContext;

import com.poly.entity.User;
import com.poly.entity.Video;

public interface EmailService {
	void sendEmail(ServletContext context,User recipient, String type);
	
	void sendShare(ServletContext context,String email ,String videoHref);
}
