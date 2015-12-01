package com.imethod.sites.web.permission.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.util.ListTools;
import com.imethod.domain.Menu;
import com.imethod.domain.OsUser;
import com.imethod.domain.Rule;
import com.imethod.sites.web.permission.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 获取用户所有权限 rule 对象
     *
     * @param userId
     */
    public List<Map<String, Object>> getUserRule(Integer userId, Integer menuType) {
        return permissionDao.getUserRule(userId, menuType);
    }

    /**
     * 获取用户所有权限
     *
     * @param userId
     */
    public List<String> getUserRuleStr(Integer userId, String ruleType) {
        return new ArrayList<>();
    }

    public List<Map<String, Object>> getUserMenu(Integer userId) {
        List<Map<String, Object>> rule = getUserRule(userId, 1);
        return ListTools.buildTree(rule, "menuId", "menuPid", "childMenu");
    }

    public PageMaker getOsUser(String query, Long pageIndex, Long pageSize) {
        return permissionDao.getOsUser(query, pageIndex, pageSize);
    }

    public PageMaker getUnOsUser(String query, Long pageIndex, Long pageSize) {
        return permissionDao.getUnOsUser(query, pageIndex, pageSize);
    }

    public OsUser insert(OsUser osUser) {
        return permissionDao.insert(osUser);
    }

    public List<Menu> queryMenuList() {
        return permissionDao.queryMenuList();
    }

    public void saveUserMenu(Integer userId, List<Integer> menuList) {
        permissionDao.deleteUserMenu(userId);
        for (Integer menuId : menuList) {
            Rule rule = new Rule();
            rule.setUserId(userId);
            rule.setState(1);
            rule.setMenuId(menuId);
            permissionDao.insert(rule);
        }
    }
}
