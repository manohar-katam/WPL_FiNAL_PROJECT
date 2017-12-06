package com.wpl.gift.dao;

import java.sql.SQLException;
import java.util.List;


import com.wpl.gift.model.User;
import org.springframework.stereotype.Component;

/**
 * Author Kartheek
 */
@Component
public interface UserDao {
    User getUserInfo(int userId);
    User updateUserInfo(User user);
    User getSecurityQuestions(User user);
    User resetPassword(User user);
    List<User> getUsers(int id) throws SQLException;
}
