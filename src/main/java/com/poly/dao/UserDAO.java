package com.poly.dao;

import java.util.List;
import java.util.Map;

import com.poly.entity.User;

public interface UserDAO {
	User findById (Integer Id);
	
	User findByEmail (String email);
	
	User findByUsername (String Username);
	
	User findByUsernameAndPassword (String Username, String Password);
	
	List<User> findAll();
	
	List<User> findAll(int pageNumber, int pageSize);
	
	User create(User entity);
	
	User update(User entity);

	User delete(User entity);
	
	List<User> findUsersLikedByVideoHref(Map<String, Object> params);
}
