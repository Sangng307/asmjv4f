package com.poly.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.poly.dao.UserDAO;
import com.poly.dao.impl.UserDAOImpl;
import com.poly.dto.UserDto;
import com.poly.entity.User;
import com.poly.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDAO dao;
	
	public UserServiceImpl() {
		dao = new UserDAOImpl();
	}
	
	@Override
	public User findById(Integer Id) {
		// TODO Auto-generated method stub
		return dao.findById(Id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findByEmail(email);
	}

	@Override
	public User findByUsername(String Username) {
		// TODO Auto-generated method stub
		return dao.findByUsername(Username);
	}

	@Override
	public User login(String Username, String Password) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPassword(Username, Password);
	}

	@Override
	public User resestPassword(String email) {
		User existUser = findByEmail(email);
		if(existUser != null) {
			String newpass = String.valueOf((int) (Math.random()) *((9999 - 1000) + 1) +1000);
			existUser.setPassword(newpass);
			return dao.update(existUser);
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}

	@Override
	public User create(String username, String password, String email, String fullname) {
		// TODO Auto-generated method stub
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setFullname(fullname);
		newUser.setEmail(email);
		newUser.setIsAdmin(Boolean.FALSE);
		newUser.setIsActive(Boolean.TRUE);
		return dao.create(newUser);
	}

	@Override
	public User update(User entity) {
		return dao.update(entity);
	}

	public User delete(String username) {
	    User user = dao.findByUsername(username);
	    if (user != null) {
	        user.setIsActive(false);
	        return dao.update(user);
	    }
	    return null;
	}

	@Override
	public List<UserDto> findUsersLikedByVideoHref(String href) {
		Map<String, Object> params = new HashMap<>();
		params.put("videoHref",href);
		List<User> users = dao.findUsersLikedByVideoHref(params);
		List<UserDto> result = new ArrayList<>();
		users.forEach(user ->{
			UserDto dto = new UserDto();
			dto.setUsername(user.getUsername());
			dto.setEmail(user.getEmail());
			result.add(dto);
		});
		return result;
	}

}
