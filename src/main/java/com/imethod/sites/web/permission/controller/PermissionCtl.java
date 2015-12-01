package com.imethod.sites.web.permission.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.util.StringTools;
import com.imethod.domain.OsUser;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.permission.service.PermissionService;
import com.imethod.sites.web.sys.auth.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/permission/usermenu/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean userMenu(@PathVariable String userId,
                               @RequestParam(value = "menus[]") List<Integer> menuList) {
        Map<String, Object> map = new HashMap<>();
        permissionService.saveUserMenu(StringTools.getInteger(userId),menuList);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/permission/menu/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean menuUserId(@PathVariable String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("ruleList", permissionService.getUserMenu(StringTools.getInteger(userId)));
        map.put("menuList", permissionService.queryMenuList());
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/permission/user.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean user(@RequestParam(required = false) String query,
                           @RequestParam(required = false) Long pageIndex,
                           @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageMaker pageMaker = permissionService.getOsUser(query, pageIndex, pageSize);
        map.put("pageMaker", pageMaker);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/permission/unosuser.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean unosuser(@RequestParam(required = false) String query,
                               @RequestParam(required = false) Long pageIndex,
                               @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageMaker pageMaker = permissionService.getUnOsUser(query, pageIndex, pageSize);
        map.put("pageMaker", pageMaker);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/permission/adduser/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean addUser(@PathVariable String userId) {
        Map<String, Object> map = new HashMap<>();
        OsUser osUser = new OsUser();
        osUser.setUserId(StringTools.getInteger(userId));
        permissionService.insert(osUser);
        return new ReturnBean(map);
    }

}
