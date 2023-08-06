package com.poly.dao.impl;

import java.util.List;
import java.util.Map;

import com.poly.constant.NamedStored;
import com.poly.dao.AbstractDAO;
import com.poly.dao.HistoryDAO;
import com.poly.entity.History;

public class HistoryDAOImpl extends AbstractDAO<History> implements HistoryDAO {

	@Override
	public List<History> findByUser(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.video.isActive = 1 " 
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public List<History> findByUserAndIsLiked(String username) {
		String sql = "SELECT o FROM History o WHERE o.user.username = ?0 AND o.isLiked = 1 AND o.video.isActive = 1 "
				+ " ORDER BY o.viewedDate DESC";
		return super.findMany(History.class, sql, username);
	}

	@Override
	public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
		String sql = "SELECT o FROM History o WHERE o.user.id = ?0 And o.video.id = ?1 "
				+" AND o.video.isActive = 1";
		return super.findOne(History.class, sql, userId, videoId);
	}

	@Override
	public History create(History enitty) {
		// TODO Auto-generated method stub
		return super.create(enitty);
	}

	@Override
	public History update(History enitty) {
		// TODO Auto-generated method stub
		return super.create(enitty);
	}

	@Override
	public List<History> findUsersLikedByVideoHref(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return super.callStored(NamedStored.FIND_USER_LIKE_VIDEO_BY_VIDEO_HREF, params);
	}

}
