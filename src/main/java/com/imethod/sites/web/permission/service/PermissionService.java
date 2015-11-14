package com.imethod.sites.web.permission.service;

import com.imethod.core.util.ListTools;
import com.imethod.domain.Menu;
import com.imethod.domain.Rule;
import com.imethod.domain.User;
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
    public List<Menu> getUserRule(Integer userId) {
        return new ArrayList<>();
    }

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
}
