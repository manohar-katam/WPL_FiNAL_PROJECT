package com.wpl.gift.service;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.wpl.gift.common.Route;
import com.wpl.gift.model.Login;
import com.wpl.gift.model.UserModel;
/**
 *  Author Sneha
 */
@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Override
	public UserModel login(Login userDetails) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
	    customer = restTemplate.postForObject(Route.basePath+Route.loginUrl,userDetails, UserModel.class);
		return customer;
	}

	@Override
	public UserModel register(User user) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
	    customer = restTemplate.postForObject(Route.basePath+Route.registerUrl,user, UserModel.class);
		return customer;
	}
	
}
