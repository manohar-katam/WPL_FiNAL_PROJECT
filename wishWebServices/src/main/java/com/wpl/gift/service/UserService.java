
package com.wpl.gift.service;

import com.wpl.gift.model.*;
import org.springframework.stereotype.Component;
/**
 * Author Kartheek
 */
@Component
public interface UserService {

	public UserModel getUserInfo(int userId);
	public UserModel  updateUserInfo(UserModel user);
	public UserModel  getSecurityQuestions(UserModel user);
	public UserModel  resetPassword(UserModel user);

    UserListModel getUsers(int id);
}
