package com.imethod.sites.web.user.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.User;
import com.imethod.domain.User;
import com.imethod.sites.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class UserCtl {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserService userService;

    /**
     * add user
     * @param user
     * @return
     */
    @RequestMapping(value="/user",method = RequestMethod.PUT)
    public boolean insert(User user) {

        boolean retState = false;
        try {
            userService.insert(user);
            retState = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return retState;
    }

    /**
     * update user
     * @param user
     * @return
     */
    @RequestMapping(value="/user",method = RequestMethod.POST)
    public boolean update(User user) {

        boolean retState = false;
        try {
            userService.update(user);
            retState = true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return retState;
    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    @ResponseBody
    public PageMaker list(@RequestParam(required = false) String query,
                        @RequestParam(required = false) Long pageIndex,
                        @RequestParam(required = false) Long pageSize) {

        pageIndex = pageIndex==null?1:pageIndex;
        pageSize = pageSize==null?10:pageSize;
        PageMaker pageMaker = null;
        try {
            pageMaker = userService.listUser(query, pageIndex, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return pageMaker;
    }
}
