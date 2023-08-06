package com.poly.dao;

import java.util.List;
import java.util.Map;

import com.poly.entity.History;


public interface HistoryDAO {
	List<History> findByUser(String username);
	
	List<History> findByUserAndIsLiked(String username);
	
	History findByUserIdAndVideoId(Integer userId, Integer videoId);
	
	History create(History enitty);
	
	History update(History enitty);
	
	List<History> findUsersLikedByVideoHref(Map<String, Object> params);
}
