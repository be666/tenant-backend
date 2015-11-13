package com.imethod.sites.web.tenant.service;

import com.imethod.domain.Tenant;
import com.imethod.sites.web.tenant.dao.TenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class TenantService {

    @Autowired
    private TenantDao tenantDao;

    public Tenant insert(Tenant tenant) {
        return tenantDao.insert(tenant);
    }
}
