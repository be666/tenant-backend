package com.imethod.sites.web.org.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.domain.Org;
import com.imethod.sites.web.org.dao.OrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/20.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class OrgService {

    @Autowired
    private OrgDao orgDao;


    public PageMaker listOrg(String query, Long pageIndex, Long pageSize) {
        return orgDao.listOrg(query, pageIndex, pageSize);
    }

    public Org insert(Org org) {
        return orgDao.insert(org);
    }

    public Org loadOrg(String orgId) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("org_id", orgId);
        try {
            return orgDao.load(Org.class, objectMap);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
