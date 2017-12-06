package com.wpl.gift.service;

import com.wpl.gift.model.Login;
import com.wpl.gift.model.User;
import com.wpl.gift.model.UserModel;
import org.springframework.stereotype.Component;
/**
 *  Author Sneha
 */
@Component
public interface LoginService {

	UserModel login(Login userDetails);
	UserModel register(User user);
	
}
