package com.imethod.sites.web.tenant.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Tenant;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class TenantCtl {

    Logger logger = LoggerFactory.getLogger(TenantService.class);

    @Autowired
    private TenantService tenantService;

    /**
     * add tenant
     * @param tenant
     * @return
     */
    @RequestMapping(value="/tenant",method = RequestMethod.PUT)
    public boolean insert(Tenant tenant) {

        boolean retState = false;
        try {
            tenantService.insert(tenant);
            retState = true;
        }catch (Exception e){

        }
        return retState;
    }

    /**
     * update tenant
     * @param tenant
     * @return
     */
    @RequestMapping(value="/tenant",method = RequestMethod.POST)
    public boolean update(Tenant tenant) {

        boolean retState = false;
        try {
            tenantService.update(tenant);
            retState = true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return retState;
    }

    @RequestMapping(value="/tenant",method = RequestMethod.GET)
    @ResponseBody
    public PageMaker list(@RequestParam(required = false) String query,
                        @RequestParam(required = false) Long pageIndex,
                        @RequestParam(required = false) Long pageSize) {

        pageIndex = pageIndex==null?1:pageIndex;
        pageSize = pageSize==null?10:pageSize;
        PageMaker pageMaker = null;
        try {
            pageMaker = tenantService.listTenant(query, pageIndex, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return pageMaker;
    }
}
