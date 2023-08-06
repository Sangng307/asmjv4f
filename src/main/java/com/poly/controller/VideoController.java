package com.poly.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.EmailService;
import com.poly.service.HistoryService;

import com.poly.service.UserService;
import com.poly.service.VideoService;
import com.poly.service.Impl.EmailServiceImpl;
import com.poly.service.Impl.HistoryServiceImpl;

import com.poly.service.Impl.UserServiceImpl;
import com.poly.service.Impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/video", "/share" })

public class VideoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7388741981193937759L;

	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();
	private static final int VIDEO_MAX_PAGE_SIZE = 5;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();

		switch (actionParam) {
		case "watch":
			doGetwatch(session, href, req, resp);
			break;

		case "like":
			doGetlike(session, href, req, resp);
			break;
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		switch (path) {
		case "/share":
			doPostShare(req, resp);
			break;
		}
	}

	private void doGetwatch(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Video> countVideos = videoService.findAll();
		int maxPage = (int) Math.ceil(countVideos.size()/(double)VIDEO_MAX_PAGE_SIZE);
		req.setAttribute("maxPage", maxPage);
		String pageNumber =req.getParameter("page");
		
		List<Video> videos;
		if(pageNumber==null || maxPage < Integer.valueOf(pageNumber)) {
			videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage",1);
		}else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage",Integer.valueOf(pageNumber));
		}
		
	
		req.setAttribute("videos", videos);
		
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		User currentUser = (User)session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser != null) {
			History history = historyService.create(currentUser, video);
			req.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		videoService.update(video);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/videos-detail.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	private void doGetlike(HttpSession session,String href, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("application/json");
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnlike(currentUser, href);
		if(result==true) {
			resp.setStatus(204);
		}else {
			resp.setStatus(400);
		}
	}
	
	private void doPostShare(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    resp.setContentType("application/json");
	    String email = req.getParameter("email2");
	    String videoHref = req.getParameter("href");
	    System.out.println(email);
	    System.out.println(videoHref);
	    if(videoHref != null && email != null) {
	        emailService.sendShare(getServletContext(), email, videoHref);
	        resp.setStatus(204);
	    } else {
	        resp.setStatus(400);
	    }
	}
}
