package com.imethod.sites.web.tenant.service;

import com.imethod.constant.Constants;
import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Code;
import com.imethod.domain.Tenant;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.tenant.dao.TenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class TenantService {

    Logger logger = LoggerFactory.getLogger(TenantService.class);

    @Autowired
    private TenantDao tenantDao;

    @Autowired
    private CodeService codeService;

    public Tenant insert(Tenant tenant) {
        Date now = DateTools.getCurrentDateTime();
        tenant.setCreateAt(now);
        tenant.setUpdateAt(now);
        tenant.setState(Constants.STATE_TRUE);
        tenant.setCreaterId(1);
        tenant.setUpdaterId(1);
        return tenantDao.insert(tenant);
    }

    public void update(Tenant tenant) {
        Date now = DateTools.getCurrentDateTime();
        Tenant tenantDB = tenantDao.loadById(tenant.getTenantId());

        tenantDB.setPlatformTenantId(tenant.getPlatformTenantId());
        tenantDB.setTenantName(tenant.getTenantName());
        tenantDB.setState(Constants.STATE_TRUE);
        tenantDB.setUpdateAt(now);
        tenantDB.setUpdaterId(tenant.getUpdaterId());
        try {
            tenantDao.update(tenant);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    public PageMaker pageTenant(String query, Integer currentStatus, Integer serviceType, Long pageIndex, Long pageSize) {
        return tenantDao.pageTenant(query, currentStatus, serviceType, pageIndex, pageSize);
    }

    public int countTenant(Integer currentStatus) {
        return tenantDao.countTenant(currentStatus);
    }

    public int countTotalTenant() {
        return countTenant(null);
    }


    public List<Tenant> listTenantAll() {

        return tenantDao.listTenantAll();
    }
}
