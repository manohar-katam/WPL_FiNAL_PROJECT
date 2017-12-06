package com.wpl.gift.controller;

import com.wpl.gift.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.model.Login;
import com.wpl.gift.model.Response;
import com.wpl.gift.model.UserModel;
import com.wpl.gift.model.User;

/**
 * Author Sneha
 */
@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@RequestMapping(value="/test",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody UserModel test()
	{
		UserModel userModel = new UserModel();
		return userModel;
	}


	@RequestMapping(value = Route.registerPage, method = RequestMethod.POST,produces="application/json")	
	public @ResponseBody UserModel register(@RequestBody User user) {
		UserModel userModel = new UserModel();
		User newUser = new User();
		if(loginService.validateUser(user)){
		newUser = loginService.register(user);}
			if (user == null)
			{
				userModel.setResponse(new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode));
			}
			else
			{
				userModel.setResponse(new Response(ConstantMessages.creationMessage,ConstantMessages.creationCode));	
			}
		userModel.setUserDetails(newUser);	
		return userModel;
	}

	@RequestMapping(value = Route.loginPage, method = RequestMethod.POST,produces="application/json")	
	public @ResponseBody UserModel homePage(@RequestBody Login userDetails) {
		UserModel userModel = new UserModel();
		User user = new User();
			user = loginService.login(userDetails.getUsername(), userDetails.getPassword());
			if (user == null)
			{
				userModel.setResponse(new Response(ConstantMessages.loginFailureMessage,ConstantMessages.emptyResponseCode));
			}
			else
			{
				userModel.setResponse(new Response(ConstantMessages.loginSuccessMessage,ConstantMessages.successCode));	
			}
		userModel.setUserDetails(user);	
		return userModel;
	}
}
