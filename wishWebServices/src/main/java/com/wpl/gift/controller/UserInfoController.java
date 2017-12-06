
package com.wpl.gift.controller;

import com.wpl.gift.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import com.wpl.gift.service.UserService;

/**
 * Author Kartheek
 */
@Controller
public class UserInfoController {

	@Autowired UserService userService;

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET,produces = "application/json")
	public  @ResponseBody  UserModel getUserInfo(@RequestParam(value = "userId") int userId) {
		UserModel userModel = new UserModel();
		User user = new User();
		if (userId == 0)
		{
			userModel.setResponse(new Response(ConstantMessages.MandatoryParamsMissing, ConstantMessages.mandatoryParameterMissingCode));
			userModel.setUserDetails(user);
			return userModel;
		}
		else {
			userModel = userService.getUserInfo(userId);
		}
		return userModel;
	}

	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST,produces = "application/json")
	public  @ResponseBody   UserModel updateUserInfo(@RequestBody UserModel usermodel) {
		User user = new User();
		UserModel model = new UserModel();
		if (usermodel.userDetails.getId() == 0)
		{
			usermodel.setResponse(new Response(ConstantMessages.MandatoryParamsMissing, ConstantMessages.mandatoryParameterMissingCode));
			usermodel.setUserDetails(user);
			return usermodel;
		}
		else {
			model = userService.updateUserInfo(usermodel);
		}
		return model;
	}

	@RequestMapping(value = "/passwordChange", method = RequestMethod.POST,produces = "application/json")
	public  @ResponseBody   UserModel getSecurityQuestions(@RequestBody UserModel usermodel) {
		User user = new User();
		UserModel model = new UserModel();
		String str = usermodel.userDetails.getUserName();

		if (str == null || str.isEmpty())
		{
			usermodel.setResponse(new Response(ConstantMessages.MandatoryParamsMissing, ConstantMessages.mandatoryParameterMissingCode));
			usermodel.setUserDetails(user);
			return usermodel;
		}
		else {
			model = userService.getSecurityQuestions(usermodel);
		}
		return model;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST,produces = "application/json")
	public  @ResponseBody   UserModel resetPassword(@RequestBody UserModel usermodel) {
		User user = new User();
		UserModel model = new UserModel();
		String str = usermodel.userDetails.getSecurityAnswer();

		if (str == null || str.isEmpty())
		{
			usermodel.setResponse(new Response(ConstantMessages.MandatoryParamsMissing, ConstantMessages.mandatoryParameterMissingCode));
			usermodel.setUserDetails(user);
			return usermodel;
		}
		else {
			model = userService.resetPassword(usermodel);
		}
		return model;
	}

	@RequestMapping(value = Route.getUsers, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody UserListModel viewInventory(@RequestParam("userId") int id){

		return userService.getUsers(id);
	}
}
