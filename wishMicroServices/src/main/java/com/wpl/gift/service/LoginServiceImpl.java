package com.wpl.gift.service;

import com.wpl.gift.dao.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author Sneha
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	LoginDao loginDao;

	@Override
	public User login(String username, String password) {
		User users = loginDao.login(username, password);
		return users;
	}

	@Override
	public boolean validateUser(User user) {
		return loginDao.validateUser(user);
	}

	@Override
	public User register(User user) {
		User users = loginDao.register(user);
		return users;
	}
	 
	

}
