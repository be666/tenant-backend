package com.imethod.sites.web.org.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.domain.Org;
import com.imethod.sites.web.org.dao.OrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
