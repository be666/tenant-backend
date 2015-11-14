package com.imethod.sites.web.permission.controller;

import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.permission.service.PermissionService;
import com.imethod.sites.web.sys.auth.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/15.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class PermissionCtl {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public String permission() {
        return "permission";
    }


    @RequestMapping(value = "/permission/menu", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean menu() {
        Map<String, Object> map = new HashMap<>();
        map.put("menuList", permissionService.getUserMenu(UserContent.getLUser().getUserId()));
        return new ReturnBean(map);
    }
}
