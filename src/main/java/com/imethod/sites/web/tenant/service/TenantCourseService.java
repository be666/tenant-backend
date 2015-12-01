package com.imethod.sites.web.tenant.service;

import com.imethod.domain.TenantCourseRp;
import com.imethod.sites.web.tenant.dao.TenantCourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class TenantCourseService {

    @Autowired
    private TenantCourseDao tenantCourseDao;

    public TenantCourseRp insert(TenantCourseRp tenantCourseRp) {
        return tenantCourseDao.insert(tenantCourseRp);
    }
}
