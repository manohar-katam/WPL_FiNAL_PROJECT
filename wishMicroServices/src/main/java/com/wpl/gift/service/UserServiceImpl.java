/**
 * 
 */
package com.wpl.gift.service;

import com.wpl.gift.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Author Kartheek
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	public User getUserInfo(int userId) {
		return userDao.getUserInfo(userId);
	}
	public User updateUserInfo(User model) {

		return userDao.updateUserInfo(model);
	}

	public User getSecurityQuestions(User model) {
		return userDao.getSecurityQuestions(model);
	}
	@Override
	public List<User> getUsers(int id) throws SQLException {
		return userDao.getUsers(id);
	}


}
