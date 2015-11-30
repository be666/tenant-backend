package com.imethod.sites.web.user.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.User;
import com.imethod.sites.web.sys.auth.UserContent;
import com.imethod.sites.web.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public void insert(User user) {
        Integer userId = UserContent.getLUser().getUser().getUserId();
        Date now = DateTools.getCurrentDateTime();
        user.setPassword("123456");
        user.setState(1);
        userDao.insert(user);
    }

    public void update(User user) {
        User userDB = userDao.loadById(user.getUserId());
        Integer userId = UserContent.getLUser().getUser().getUserId();
        userDB.setState(user.getState());
        userDB.setOrgId(user.getOrgId());
        userDB.setUserName(user.getUserName());
        userDB.setEmail(user.getEmail());
        userDB.setMobile(user.getMobile());
        String gender = user.getGender();
        gender = ("男".equals(gender) || "女".equals(gender)) ? gender : null;
        userDB.setGender(gender);
        try {
            userDao.update(user);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    public PageMaker listUser(String query, Long pageIndex, Long pageSize) {
        return userDao.listUser(query, pageIndex, pageSize);
    }

    public User loadById(Integer userId) {
        return userDao.loadById(userId);
    }

    public PageMaker listOrgUser(String orgId, String query, Long pageIndex, Long pageSize) {
        return userDao.listOrgUser(orgId, query, pageIndex, pageSize);
    }
}
