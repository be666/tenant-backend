package com.imethod.sites.web.user.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Code;
import com.imethod.domain.ReturnBean;
import com.imethod.domain.User;
import com.imethod.domain.User;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class UserCtl {

    Logger logger = LoggerFactory.getLogger(UserCtl.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CodeService codeService;

    @RequestMapping(value="/user/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public User load(@PathVariable Integer userId) {
        User user = null;
        try {

            user = userService.loadById(userId);
        }catch (Exception e){
            logger.error(e);

        }
        return user;
    }

    /**
     * add user
     * @param user
     * @return
     */
    @RequestMapping(value="/user",method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean insert (User user) {

        ReturnBean  returnBean = new ReturnBean();
        try {
            userService.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("保存失败， "+e.getMessage());
        }
        return returnBean;
    }

    /**
     * update user
     * @param user
     * @return
     */
    @RequestMapping(value="/user/{userId}",method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(@PathVariable Integer userId,User user) {

        ReturnBean  returnBean = new ReturnBean();
        try {
            user.setUserId(userId);
            userService.update(user);
        }catch (Exception e){
            logger.error(e);
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("更新失败， "+e.getMessage());
        }
        return returnBean;
    }

    @RequestMapping(value="/user",method = RequestMethod.GET)
    @ResponseBody
    public PageMaker list(@RequestParam(required = false) String query,
                        @RequestParam(required = false) Long pageIndex,
                        @RequestParam(required = false) Long pageSize) {

        PageMaker pageMaker = null;
        try {
            pageMaker = userService.listUser(query,pageIndex, pageSize);
        }catch (Exception e){
            logger.error(e);

        }
        return pageMaker;
    }
}
