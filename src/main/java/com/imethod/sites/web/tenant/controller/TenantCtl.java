package com.imethod.sites.web.tenant.controller;

import com.imethod.domain.Tenant;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class TenantCtl {

    @Autowired
    private TenantService tenantService;

    @RequestMapping("/tenant")
    public String index() {
        Tenant tenant = new Tenant();
        tenant=tenantService.insert(tenant);
        return "tenant";
    }
}
