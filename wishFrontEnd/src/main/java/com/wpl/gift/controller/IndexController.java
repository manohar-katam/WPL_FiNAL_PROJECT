package com.wpl.gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Author Manohar
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage() {

        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String user = session.getAttribute("username").toString();
        if(!user.equals("admin@giftregistry.com"))
        {
            return "user-home-page";
        }
        else
        {
            return  "admin-home-page";
        }


    }

}
