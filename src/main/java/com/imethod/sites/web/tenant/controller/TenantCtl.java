package com.imethod.sites.web.tenant.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Code;
import com.imethod.domain.ReturnBean;
import com.imethod.domain.Tenant;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class TenantCtl {

    Logger logger = LoggerFactory.getLogger(TenantCtl.class);

    @Autowired
    private TenantService tenantService;

    @Autowired
    private CodeService codeService;


    @RequestMapping(value = "/tenant", method = RequestMethod.GET)
    public String index(ModelMap map) {
        PageMaker pageMaker = null;
        try {
            pageMaker = tenantService.pageTenant(null, null, null, 1l, 10l);
            int totalTenant = tenantService.countTotalTenant();
            int currentStatus10Count = tenantService.countTenant(10);
            Map<Integer, Code> currentStatusCodeMap = codeService.listCodeMap("currentStatus");
            Map<Integer, Code> serviceTypeCodeMap = codeService.listCodeMap("serviceType");
            map.put("currentStatusCodeMap", currentStatusCodeMap);
            map.put("serviceTypeCodeMap", serviceTypeCodeMap);
            map.put("pageMaker", pageMaker);
            map.put("totalTenant", totalTenant);
            map.put("currentStatus10Count", currentStatus10Count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "tenant";
    }


    @RequestMapping(value = "/tenant/new", method = RequestMethod.GET)
    public String create() {
        return "tenant.info";
    }

    /**
     * add tenant
     *
     * @param tenant
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tenant", method = RequestMethod.POST)
    public ReturnBean insert(Tenant tenant) {

        ReturnBean returnBean = new ReturnBean();
        try {
            tenantService.insert(tenant);
        } catch (Exception e) {
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("保存失败， " + e.getMessage());
        }
        return returnBean;
    }

    /**
     * update tenant
     *
     * @param tenant
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tenant/{tenantId}", method = RequestMethod.POST)
    public ReturnBean update(@PathVariable Integer tenantId, Tenant tenant) {

        ReturnBean returnBean = new ReturnBean();
        try {
            tenantService.update(tenant);
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("更新失败， " + e.getMessage());
        }
        return returnBean;
    }

    @RequestMapping(value = "/tenant/query", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean list(@RequestParam(required = false) String query,
                           @RequestParam(required = false, defaultValue = "1") Long pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Long pageSize,
                           @RequestParam(required = false) Integer currentStatus,
                           @RequestParam(required = false) Integer serviceType) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("pageMaker", tenantService.pageTenant(query, currentStatus, serviceType, pageIndex, pageSize));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return new ReturnBean(map);
    }


}
