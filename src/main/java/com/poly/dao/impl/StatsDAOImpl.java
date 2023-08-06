package com.poly.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.poly.dao.AbstractDAO;
import com.poly.dao.StatsDAO;
import com.poly.dto.VideoLikedInfo;
import com.poly.entity.User;

public class StatsDAOImpl extends AbstractDAO<Object[]> implements StatsDAO{

	@Override	
	public List<VideoLikedInfo> findVideoLikedInfos() {
		String sql = "SELECT v.id, v.title, v.href, sum(CAST(h.isLiked AS int)) as totalLike"
		         + " FROM Video v"
		         + " LEFT JOIN history h ON v.id = h.id"
		         + " WHERE v.isActive = 1"
		         + " GROUP BY v.id, v.title, v.href"
		         + " ORDER BY totalLike DESC";
	    List<Object[]> objects = super.findManyByNativeQuery(sql);
	    List<VideoLikedInfo> result = new ArrayList<>();
	    objects.forEach(object -> {
	        VideoLikedInfo videoLikedInfo = setDataVideoLikedInfo(object);	        
	        result.add(videoLikedInfo);
	    });
	    return result;
	}
	
	private VideoLikedInfo setDataVideoLikedInfo(Object[] object) {
		VideoLikedInfo videoLikedInfo = new VideoLikedInfo();
		videoLikedInfo.setVideoId((Integer) object[0]);
        videoLikedInfo.setTitle((String) object[1]);
        videoLikedInfo.setHref((String) object[2]);
        videoLikedInfo.setTotalLike(object[3] == null ? 0 : (Integer) object[3]);
        return videoLikedInfo;
	}


}
