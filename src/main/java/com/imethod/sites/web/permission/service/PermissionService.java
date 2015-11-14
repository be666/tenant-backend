package com.imethod.sites.web.permission.service;

import com.imethod.domain.Rule;
import com.imethod.domain.User;
import com.imethod.sites.web.permission.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<Rule> getUserRule(Integer userId) {
        return new ArrayList<>();
    }

    /**
     * 获取用户所有权限
     *
     * @param userId
     */
    public List<String> getUserRuleStr(Integer userId,String ruleType) {
        return new ArrayList<>();
    }
}
