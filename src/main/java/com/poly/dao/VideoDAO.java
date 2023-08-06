package com.poly.dao;

import java.util.List;

import com.poly.entity.Video;

public interface VideoDAO {

	Video findById (Integer Id);
	
	Video findByHref (String href);
	
	List<Video> findAll();
	
	List<Video> findAll(int pageNumber, int pageSize);
	
	Video create (Video entity);
	
	Video update (Video entity);
	
	Video delete (String href);
}
