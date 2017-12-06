package com.wpl.gift.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.wpl.gift.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.common.Route;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author Sneha
 */
@Controller
public class LoginController {


	@RequestMapping(value = "/unauthorized-access", method = RequestMethod.GET)
	public String unauthorizedPage() {

		return "unauthorized-access";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request,ModelMap model) {

		return new ModelAndView("login", "login", new Login());
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,ModelMap model) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "logout";
	}

	@RequestMapping(value = "/registernewUser", method = RequestMethod.GET)
	public ModelAndView registerNew(ModelMap model) {

		return new ModelAndView("register", "register", new RegistrationBean());
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request,ModelMap model,@ModelAttribute("login") Login pLogin) throws IOException {
		UserModel customer = new UserModel();
		Login userDetails = new Login();
		userDetails.setUsername(pLogin.getUsername());
		userDetails.setPassword(pLogin.getPassword());

		if(userDetails.getUsername() == null || userDetails.getPassword() == null)
		{
			model.addAttribute("errorMessage", ConstantMessages.wrongUserNameOrPassword);
			return "login-error";
		}
		try {

			RestTemplate restTemplate = new RestTemplate();
			customer = restTemplate.postForObject(Route.basePath+Route.loginUrl,userDetails, UserModel.class);
			if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
			{
				HttpSession session = request.getSession();

				session.setAttribute("id", customer.getUserDetails().getId());
				session.setAttribute("username", customer.getUserDetails().getUserName());
				session.setAttribute("firstname",customer.getUserDetails().getFirstName());
				session.setAttribute("lastname",customer.getUserDetails().getLastName());
				session.setAttribute("securityQuestion",customer.getUserDetails().getSecurityQuestion());
				session.setAttribute("securityAnswer",customer.getUserDetails().getSecurityAnswer());

				if(customer.getUserDetails().getUserName().equalsIgnoreCase("admin@giftregistry.com")){
					model.addAttribute("user", customer.getUserDetails());
					model.addAttribute("errorMessage", customer.getUserDetails().getFirstName()+" "+customer.getUserDetails().getLastName());
					return "admin-home-page";
				}else{
					model.addAttribute("user", customer.getUserDetails());
					model.addAttribute("errorMessage", customer.getUserDetails().getFirstName()+" "+customer.getUserDetails().getLastName());
					return "user-home-page";
				}

			}
			else
			{
				model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
				return "login-error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login-error";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest request,ModelMap model,@ModelAttribute("register") RegistrationBean pRegBean) throws ParseException {
		UserModel customer = new UserModel();
		//Get values from the form for registering a new user
		User userDetails = new User();
		userDetails.setUserName(pRegBean.getUsername());
		userDetails.setPassword(pRegBean.getPassword());
		userDetails.setFirstName(pRegBean.getFirstname());
		userDetails.setLastName(pRegBean.getLastname());
		userDetails.setSecurityQuestion(pRegBean.getSecurityquestion());
		userDetails.setSecurityAnswer(pRegBean.getSecurityanswer());

		try {
			RestTemplate restTemplate = new RestTemplate();
			customer = restTemplate.postForObject(Route.basePath+Route.registerUrl,userDetails, UserModel.class);
			if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.creationCode))
			{
				HttpSession session = request.getSession();
				session.setAttribute("user", customer.getUserDetails());
				model.addAttribute("name", customer.getUserDetails().getFirstName()+" "+customer.getUserDetails().getLastName());
				//MemCacheUtils.keepInCache(customer.getUserDetails().getUserName(), customer.getUserDetails());
				model.addAttribute("login",new LoginBean());
				return "login";
			}
			else
			{
				model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
				return "register-error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "register-error";
	}


}
