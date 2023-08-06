package com.poly.service;

import java.util.List;

import com.poly.dto.UserDto;
import com.poly.entity.User;

public interface UserService {
	User findById (Integer Id);
	
	User findByEmail (String email);
	
	User findByUsername (String Username);
	
	User login (String Username, String Password);
	
	User resestPassword (String email);
	
	List<User> findAll();
	
	List<User> findAll(int pageNumber, int pageSize);
	
	User create(String username, String password, String email,String fullname);
	
	User update(User entity);

	User delete(String username);
	
	List<UserDto> findUsersLikedByVideoHref(String href);
}
