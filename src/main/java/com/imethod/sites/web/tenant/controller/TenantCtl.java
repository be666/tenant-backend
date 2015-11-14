package com.imethod.sites.web.tenant.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.ReturnBean;
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
    @ResponseBody
    @RequestMapping(value="/tenant",method = RequestMethod.PUT)
    public ReturnBean insert(Tenant tenant) {

        ReturnBean  returnBean = new ReturnBean();
        try {
            tenantService.insert(tenant);
        }catch (Exception e){
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("保存失败， "+e.getMessage());
        }
        return returnBean;
    }

    /**
     * update tenant
     * @param tenant
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/tenant",method = RequestMethod.POST)
    public ReturnBean update(Tenant tenant) {

        ReturnBean  returnBean = new ReturnBean();
        try {
            tenantService.update(tenant);
        }catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("更新失败， "+e.getMessage());
        }
        return returnBean;
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
