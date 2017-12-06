package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.model.User;
import com.wpl.gift.model.UserModel;
import com.wpl.gift.utils.MemCacheUtils;
import org.springframework.stereotype.Controller;
import com.wpl.gift.common.Route;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Author Kartheek
 */
@Controller
public class UserInfoController {

    @RequestMapping(value="/userprofile", method = RequestMethod.GET)
    public String getUserProfileInfo(HttpServletRequest request, HttpServletResponse response,ModelMap model){

        UserModel customer = new UserModel();
        HttpSession session = request.getSession();
        int userId =Integer.parseInt(session.getAttribute("id").toString());
        System.out.println("user data in session is "+userId);
        if( userId ==0)
        {
            model.addAttribute("errorMessage", ConstantMessages.serverError);
            return "error-404";
        }
        try {

            if(MemCacheUtils.getValuesFromCache(userId) != null){
                ModelMap user = model.addAttribute("user", MemCacheUtils.getValuesFromCache(userId));

                return "user-profile";
            }
            RestTemplate restTemplate = new RestTemplate();

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Route.basePath+Route.getUserInfo);
            builder.queryParam("userId", userId);

            customer = restTemplate.getForObject(builder.build().encode().toUri(), UserModel.class);
            if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {

                model.addAttribute("user", customer.getUserDetails());

                MemCacheUtils.storeInCache(userId, customer.getUserDetails());

                return "user-profile";
            }
            else
            {
                model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";
    }


    @RequestMapping(value="/userprofile", method = RequestMethod.POST)
    public String setUserProfileInfo(HttpServletRequest request,ModelMap model,@ModelAttribute("user") User pRegBean){

        UserModel customer = new UserModel();
        //Get values from the form for registering a new user


        HttpSession session = request.getSession();
        UserModel userModel =  new UserModel();
        User user = new User();
        user.setId(Integer.parseInt(session.getAttribute("id").toString()));

        userModel.setUserDetails(user);


        userModel.userDetails.setFirstName(pRegBean.getFirstName());
        userModel.userDetails.setLastName(pRegBean.getLastName());

        try {
            RestTemplate restTemplate = new RestTemplate();

            customer = restTemplate.postForObject(Route.basePath+Route.updateUserInfo,userModel, UserModel.class);
            if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {

                model.addAttribute("user", customer.getUserDetails());
                return "user-home-page";
            }
            else
            {
                model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";
    }


    @RequestMapping(value = "/passwordchange", method = RequestMethod.GET)
    public ModelAndView getForgotPasswordPage(HttpServletRequest request, ModelMap model) {

        return new ModelAndView("forgot-password", "user", new User());
    }

    @RequestMapping(value = "/passwordchange", method = RequestMethod.POST)
    public String getSecurityQuestionsPage( HttpServletRequest request,ModelMap model,@ModelAttribute("user") User pRegBean) {

        HttpSession session = request.getSession();

        UserModel customer = new UserModel();
        UserModel userModel =  new UserModel();
        User user = new User();
        userModel.setUserDetails(user);

        userModel.userDetails.setUserName(pRegBean.getUserName());

        try {
            RestTemplate restTemplate = new RestTemplate();

            customer = restTemplate.postForObject(Route.basePath+Route.passwordChange,userModel, UserModel.class);
            if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {
               String question =  customer.getUserDetails().getSecurityQuestion();
                session.setAttribute("id", customer.getUserDetails().getId());
                customer = new UserModel();
                User users = new User();
              customer.setUserDetails(users);
              customer.userDetails.setSecurityQuestion(question);
                model.addAttribute("user", customer.getUserDetails());
                return "get-security-questions";
            }
            else
            {
                //TO_DO delete the session
                model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";

    }



    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public String resetPassword( HttpServletRequest request,ModelMap model,@ModelAttribute("user") User pRegBean) {

        UserModel customer = new UserModel();
        UserModel userModel =  new UserModel();
        User user = new User();
        userModel.setUserDetails(user);
        HttpSession session = request.getSession();
        int userId =Integer.parseInt(session.getAttribute("id").toString());
        userModel.userDetails.setId(userId);
        userModel.userDetails.setSecurityQuestion(pRegBean.getSecurityQuestion());
        userModel.userDetails.setSecurityAnswer(pRegBean.getSecurityAnswer());
        userModel.userDetails.setPassword(pRegBean.getPassword());

        try {
            RestTemplate restTemplate = new RestTemplate();

            customer = restTemplate.postForObject(Route.basePath+Route.resetPassword,userModel, UserModel.class);
            if(customer.getResponse().getResponseCode().equalsIgnoreCase(ConstantMessages.successCode))
            {

                model.addAttribute("user", customer.getUserDetails());
                return "index";
            }
            else
            {
                model.addAttribute("errorMessage", customer.getResponse().getResponseMessage());
                return "error-404";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error-404";

    }

}
