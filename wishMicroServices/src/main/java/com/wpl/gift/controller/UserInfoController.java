
package com.wpl.gift.controller;

import java.sql.SQLException;
import java.util.List;

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

	@RequestMapping(value = Route.getUserInfo, method = RequestMethod.GET,produces="application/json")
	public @ResponseBody  UserModel getUserInfo(@RequestParam(value = "userId") int userId) {
		UserModel userModel = new UserModel();
		User user = userService.getUserInfo(userId);
		if (user == null)
		{
			userModel.setResponse(new Response(ConstantMessages.noUser,ConstantMessages.emptyResponseCode));
		}
		else
		{
			userModel.setResponse(new Response(ConstantMessages.foundUser,ConstantMessages.successCode));
		}
		userModel.setUserDetails(user);
		return userModel;
	}


	@RequestMapping(value = Route.updateUserInfo, method = RequestMethod.POST,produces="application/json")
	public @ResponseBody UserModel setUserInfo(@RequestBody UserModel model) {
		UserModel userModel = new UserModel();
		User user = model.userDetails;
		user = userService.updateUserInfo(user);
		if (user == null)
		{
			userModel.setResponse(new Response(ConstantMessages.noUser,ConstantMessages.emptyResponseCode));
		}
		else
		{
			userModel.setResponse(new Response(ConstantMessages.foundUser,ConstantMessages.successCode));
		}
		userModel.setUserDetails(user);
		return userModel;
	}


	@RequestMapping(value = "/getSecurityUrl", method = RequestMethod.POST,produces="application/json")
	public @ResponseBody UserModel getSecurityQuestions(@RequestBody UserModel model) {
		UserModel userModel = new UserModel();
		User user = model.userDetails;
		user = userService.getSecurityQuestions(user);
		if (user == null)
		{
			userModel.setResponse(new Response(ConstantMessages.noUser,ConstantMessages.emptyResponseCode));
		}
		else
		{
			userModel.setResponse(new Response(ConstantMessages.foundUser,ConstantMessages.successCode));
		}
		userModel.setUserDetails(user);
		return userModel;
	}
	 @RequestMapping(value = Route.getUsers, method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody UserListModel getUsers(@RequestParam("id") int id) throws SQLException
	 {
		 UserListModel userListModel = new UserListModel();

		 Response response;
		 List<User> list = userService.getUsers(id);

		 if(list!= null) {
		 response = new Response(ConstantMessages.creationMessage,ConstantMessages.successCode);
		 }
		 else
		 {
		 response = new Response(ConstantMessages.registerFailureMessage,ConstantMessages.emptyResponseCode);
		 }
		 userListModel.setUsers(list);
		 userListModel.setResponse(response);
		 return userListModel ;
	 }

}
