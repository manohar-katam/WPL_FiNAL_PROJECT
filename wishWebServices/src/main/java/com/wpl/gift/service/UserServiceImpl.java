
package com.wpl.gift.service;

import com.wpl.gift.model.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.wpl.gift.common.Route;

/**
 * Author Kartheek
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Override
	public UserModel getUserInfo(int userId) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getUserInfo);
		builder.queryParam("userId", userId);
		customer = restTemplate.getForObject(builder.build().encode().toUri(), UserModel.class);
		return customer;
	}
	@Override
	public UserModel updateUserInfo(UserModel user) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
		customer = restTemplate.postForObject(Route.basePath+Route.updateUserInfo,user, UserModel.class);
		return customer;
	}
	@Override
	public UserModel getSecurityQuestions(UserModel user) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
		customer = restTemplate.postForObject(Route.basePath+Route.getSecurityUrl,user, UserModel.class);
		return customer;
	}


	@Override
	public UserModel resetPassword(UserModel user) {
		UserModel customer = new UserModel();
		RestTemplate restTemplate = new RestTemplate();
		customer = restTemplate.postForObject(Route.basePath+Route.getResetPasswordUrl,user, UserModel.class);
		return customer;
	}

	@Override
	public UserListModel getUsers(int id) {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getUsers);

		builder.queryParam("id",id);
		UserListModel result = restTemplate.getForObject(builder.build().encode().toUri(), UserListModel.class);

		return result;
	}

}
