package com.poly.dao.impl;

import java.util.List;
import java.util.Map;

import com.poly.constant.NamedStored;
import com.poly.dao.AbstractDAO;
import com.poly.dao.UserDAO;
import com.poly.entity.User;

public class UserDAOImpl  extends AbstractDAO<User> implements UserDAO{

	@Override
	public User findById(Integer Id) {
		// TODO Auto-generated method stub
		return super.findById(User.class, Id);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT o  FROM User o WHERE o.email = ?0 ";
		return super.findOne(User.class, sql, email);
	}

	@Override
	public User findByUsername(String Username) {
		// TODO Auto-generated method stub
		String sql = "SELECT o  FROM User o WHERE o.username = ?0 ";
		return super.findOne(User.class, sql, Username);
	}

	@Override
	public User findByUsernameAndPassword(String Username, String Password) {
		String sql = "SELECT o FROM User o WHERE o.username = ?0 AND o.password = ?1";
		return super.findOne(User.class, sql, Username , Password);
	}

	@Override
	public List<User> findAll() {
		return super.findAll(User.class, true);
	}

	@Override
	public User create(User entity) {
		return super.create(entity);
	}

	@Override
	public User update(User entity) {
		return super.update(entity);
	}

	@Override
	public User delete(User entity) {
		return super.delete(entity);
	}

	@Override
	public List<User> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return super.findAll(User.class, true, pageNumber, pageSize);
	}

	@Override
	public List<User> findUsersLikedByVideoHref(Map<String, Object> params) {
		return super.callStored(NamedStored.FIND_USER_LIKE_VIDEO_BY_VIDEO_HREF, params);
	}

}
