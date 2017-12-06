
package com.wpl.gift.service;


import com.wpl.gift.model.User;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * Author Kartheek
 */
@Component
public interface UserService {

	User getUserInfo(int userId);
	User updateUserInfo(User model);
	User getSecurityQuestions(User model);

    List<User> getUsers(int id) throws SQLException;
}

