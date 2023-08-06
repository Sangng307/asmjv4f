package com.poly.service.Impl;

import java.util.List;

import com.poly.dao.StatsDAO;
import com.poly.dao.impl.StatsDAOImpl;
import com.poly.dto.VideoLikedInfo;
import com.poly.service.StatsService;

public class StatServiceImpl implements StatsService{
	
	private StatsDAO statsDAO;
	
	public StatServiceImpl() {
		statsDAO = new StatsDAOImpl();
	}

	@Override
	public List<VideoLikedInfo> findVideoLikedInfos() {
		
		return statsDAO.findVideoLikedInfos();
	}

}
