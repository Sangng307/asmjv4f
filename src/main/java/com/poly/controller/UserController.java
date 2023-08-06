package com.poly.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.User;
import com.poly.service.EmailService;
import com.poly.service.UserService;
import com.poly.service.Impl.EmailServiceImpl;
import com.poly.service.Impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/login", "/register", "/logout", "/forgot", "/changePass", "/edit" })
public class UserController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4436346857098834980L;

	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doGetlogin(req, resp);
			break;
		case "/register":
			doGetregister(req, resp);
			break;
		case "/logout":
			doGetlogout(session, req, resp);
			break;
		case "/forgot":
			doGetforgot(req, resp);
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/login":
			doPostlogin(session, req, resp);
			break;
		case "/register":
			doPostregister(session, req, resp);
			break;
		case "/forgot":
			doPostforgot(req, resp);
			break;
		case "/changePass":
			doPostchangePass(session, req, resp);
			break;
		case "/edit":
			doPostEdit(session, req, resp);
		
		}
	}

	private void doGetlogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/login.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void doGetregister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/register.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	private void doGetforgot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/forgot.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void doGetlogout(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		session.removeAttribute(SessionAttr.CURRENT_USER);
		resp.sendRedirect("index");
	}

	private void doPostlogin(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = userService.login(username, password);

		if (user != null) {
			emailService.sendEmail(getServletContext(), user, "welcom");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("login");
		}
	}

	private void doPostregister(HttpSession session, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String fullname = req.getParameter("fullname");
		User user = userService.create(username, password, email,fullname);

		if (user != null) {
			emailService.sendEmail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		} else {
			resp.sendRedirect("register");
		}
	}
	
	private void doPostforgot(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("application/json");
	    String email = req.getParameter("email1");
	    
	    User usernp = userService.resestPassword(email);
	    System.out.println(email);
	    if(usernp != null) {
	        emailService.sendEmail(getServletContext(), usernp, "forgot");
	        resp.setStatus(204);
	    } else {
	        resp.setStatus(400);
	    }
	}
	
	private void doPostchangePass(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
	    String crPass = req.getParameter("crPass");
	    String nwPass = req.getParameter("nwPass");
	    User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
	    
	    if(currentUser.getPassword().equals(crPass)) {
	        currentUser.setPassword(nwPass);
	        User updatedUser = userService.update(currentUser);
	        if(updatedUser != null) {
	        	session.setAttribute(SessionAttr.CURRENT_USER, updatedUser);
	        	resp.setStatus(204);
	        }else {
	        	resp.setStatus(400);
	        }
	    } else {
	        resp.setStatus(400);
	    }
	}
	private void doPostEdit(HttpSession session, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
	    String password = req.getParameter("password");
	    String fullname = req.getParameter("fullname");
	    String email = req.getParameter("email");
	    User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
	    System.out.println(password+fullname+email);
	    if(currentUser.getPassword().equals(password)) {
	    	 currentUser.setFullname(fullname);
	        currentUser.setEmail(email);
	        User updatedUser = userService.update(currentUser);
	       
	        	session.setAttribute(SessionAttr.CURRENT_USER, updatedUser);
	        	resp.setStatus(204);
	       
	    } else {
	        resp.setStatus(401);
	    }
	}
}
