package com.wpl.gift.service;

import com.wpl.gift.dao.UserDao;
import com.wpl.gift.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author Kartheek
 */
@Service("resetPasswordService")
@Transactional
public class ResetPasswordServiceImpl implements  ResetPasswordService {

    @Autowired
    UserDao userDao;


    @Override
    public User resetPassword(User model) {
        return userDao.resetPassword(model);
    }
}
