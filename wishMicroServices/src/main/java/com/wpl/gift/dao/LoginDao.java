package com.wpl.gift.dao;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Component;

@Component
public interface LoginDao {

	boolean authenticate(User user);

	boolean validateUser(User user);

	public User register(User user);

	User login(String userName, String password);
}
