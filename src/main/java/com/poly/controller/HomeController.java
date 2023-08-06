package com.poly.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import com.poly.constant.SessionAttr;
import com.poly.entity.History;
import com.poly.entity.User;
import com.poly.entity.Video;
import com.poly.service.HistoryService;
import com.poly.service.VideoService;
import com.poly.service.Impl.HistoryServiceImpl;
import com.poly.service.Impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/index", "/favourite", "/history" })
public class HomeController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1517115637537021552L;
	private static final int VIDEO_MAX_PAGE_SIZE = 8;
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch (path) {
		case "/index":
			doGetindex(req, resp);
			break;
		case "/favourite":
			doGetfavourite(session, req, resp);
			break;
		case "/history":
			doGethistory(session, req, resp);
			break;

		}
	}

	private void doGetindex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/index.jsp");
		requestDispatcher.forward(req, resp);
	}

	private void doGetfavourite(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> history = historyService.findByUserAndIsLiked(user.getUsername());
		List<Video> videos = new ArrayList<>();
		history.forEach(Item -> videos.add(Item.getVideo()));
		
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/favourite.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	private void doGethistory(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> history = historyService.findByUser(user.getUsername());
		List<Video> videos = new ArrayList<>();
		history.forEach(Item -> videos.add(Item.getVideo()));
		
		req.setAttribute("videos", videos);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/user/history.jsp");
		requestDispatcher.forward(req, resp);
	}
}
