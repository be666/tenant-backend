package com.imethod.sites.web.tenant.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.*;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.region.service.RegionService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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

    @Autowired
    private RegionService regionService;


    @RequestMapping(value = "/tenant", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        try {
            int totalTenant = tenantService.countTotalTenant();
            int currentStatus10Count = tenantService.countTenant(10);
            modelMap.put("currentStatus", codeService.listCodeByType("currentStatus"));
            modelMap.put("serviceType", codeService.listCodeByType("serviceType"));
            modelMap.put("totalTenant", totalTenant);
            modelMap.put("currentStatus10Count", currentStatus10Count);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "tenant";
    }


    @RequestMapping(value = "/tenant/new", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        modelMap.put("currentStatus", codeService.listCodeByType("currentStatus"));
        modelMap.put("serviceType", codeService.listCodeByType("serviceType"));
        modelMap.put("orgType", codeService.listCodeByType("orgType"));
        modelMap.put("schoolType", codeService.listCodeByType("schoolType"));
        modelMap.put("region", regionService.getRegionTree());
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
     * add tenant
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tenant/save", method = RequestMethod.POST)
    public ReturnBean insert(@RequestParam  String shortName,
                             @RequestParam  String serviceType,  //fuwuleixing
                             @RequestParam  String schoolOrg,   //orgId
                             @RequestParam  String schoolUser,  //练习人
                             @RequestParam  String sellOrg,    //xiaoshouguishu
                             @RequestParam  String sellUser,    //
                             @RequestParam  String managerOrg,  //guanliyuansuoshujihou
                             @RequestParam  String managerUser,  //managerId
                             @RequestParam  String managerSell,  //xiaoshou人Id
                             @RequestParam  String serviceUser,  //本地服务负责任
                             @RequestParam  String scoreService, //学习报道是否要
                             @RequestParam  String resourceService,  //是否开启本地部署
                             @RequestParam  String tenantTime,  //交付日期
                             @RequestParam  String tenantName,
                             @RequestParam  String orgName,
                             @RequestParam  String currentStatus
    ) {


        Tenant tenant=new Tenant();
        ReturnBean returnBean = new ReturnBean();
        try {
            tenant.setCourseNum(0);
            if("10".equals(serviceType)){
                tenant.setServiceType(10);
            }else if("20".equals(serviceType)){
                tenant.setServiceType(20);
            }
            if("10".equals(currentStatus)){
                tenant.setCurrentStatus(10);
            }else if("20".equals(currentStatus)){
                tenant.setCurrentStatus(20);
            }
            tenant.setTenantName(tenantName);
            tenant.setDomain(shortName);
            tenant.setOrgName(orgName);
            tenant.setOrgId(Integer.valueOf(schoolOrg==null?"0":schoolOrg));
            Seller seller = null;
            if(sellUser!=null){
                seller = new Seller();
                seller.setUserId(Integer.valueOf(sellUser));
                seller.setOrgId(Integer.valueOf(sellOrg));
            }
            Manager manager = null;
            if(managerUser!=null){
                manager = new Manager();
                manager.setOrgId(managerOrg==null?null:Integer.valueOf(managerOrg));
                manager.setUserId(Integer.valueOf(managerUser));
            }

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

    @RequestMapping(value = "/tenant.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean list(@RequestParam(required = false) String query,
                           @RequestParam(required = false, defaultValue = "1") Long pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Long pageSize,
                           @RequestParam(required = false) Integer currentStatus,
                           @RequestParam(required = false) Integer serviceType) {
        Map<String, Object> map = new HashMap<>();
        try {
            map.put("pageMaker", tenantService.pageTenant(query, currentStatus, serviceType, pageIndex, pageSize));
            return new ReturnBean(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ReturnBean("查询出错");
        }
    }


}
