package com.imethod.sites.web.auth.service;

import com.imethod.domain.User;
import com.imethod.sites.web.auth.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/12/1.
 */
@Service
public class AuthService {
    @Autowired
    private AuthDao authDao;

    public User getLoginUser(String inputEmail, String inputPassword) {
        return authDao.getLoginUser(inputEmail, inputPassword);
    }
}
