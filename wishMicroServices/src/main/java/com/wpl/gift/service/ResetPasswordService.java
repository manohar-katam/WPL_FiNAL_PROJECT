package com.wpl.gift.service;

import com.wpl.gift.model.User;
import org.springframework.stereotype.Component;

/**
 * Author Kartheek
 */
@Component
public interface ResetPasswordService {
    User resetPassword(User model);

}
