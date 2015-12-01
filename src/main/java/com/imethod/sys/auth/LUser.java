package com.imethod.sys.auth;

import com.imethod.domain.User;
import lombok.Data;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */

@Data
public class LUser {
    private User user;
    private Integer userId;
    private boolean guest = true;
    public LUser(User user) {
        if (user != null) {
            this.user = user;
            userId = user.getUserId();
            guest = false;
        }
    }
}
