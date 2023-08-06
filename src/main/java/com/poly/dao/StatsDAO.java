package com.poly.dao;

import java.util.List;

import com.poly.dto.VideoLikedInfo;


public interface StatsDAO {
	List<VideoLikedInfo> findVideoLikedInfos();
	
	
}
