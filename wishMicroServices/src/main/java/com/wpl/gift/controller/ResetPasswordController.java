package com.wpl.gift.controller;

import com.wpl.gift.common.ConstantMessages;
import com.wpl.gift.model.Response;
import com.wpl.gift.model.User;
import com.wpl.gift.model.UserModel;
import com.wpl.gift.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author Kartheek
 */
@Controller
public class ResetPasswordController {


    @Autowired
    ResetPasswordService resetPasswordService;

    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST,produces="application/json")
    public @ResponseBody UserModel resetPassword(@RequestBody UserModel model) {
        UserModel userModel = new UserModel();
        User user = model.userDetails;
        user =resetPasswordService.resetPassword(user);
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

}
