package com.wpl.gift.service;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Component;

/**
 * Author Sneha
 */
@Component
public interface LoginService {

    User register(User user);
	User login(String username, String password);

    boolean validateUser(User user);
}
