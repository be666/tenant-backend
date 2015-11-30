package com.imethod.sites.web.tenant.controller;

import com.imethod.constant.Constants;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.*;
import com.imethod.sites.web.buyer.service.BuyerService;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.job.service.ServeService;
import com.imethod.sites.web.manager.service.ManagerService;
import com.imethod.sites.web.region.service.RegionService;
import com.imethod.sites.web.report.service.ReportService;
import com.imethod.sites.web.sell.service.SellService;
import com.imethod.sites.web.spoc.service.SpocService;
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

    @Autowired
    private RegionService regionService;

    @Autowired
    private SellService sellService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ServeService serveService;

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private SpocService spocService;
    @Autowired
    private ReportService reportService;


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
        return "tenant.info";
    }

    @RequestMapping(value = "/tenant/{tenantId}/course", method = RequestMethod.GET)
    public String course(@PathVariable String tenantId, ModelMap modelMap) {
        modelMap.put("courseType", codeService.listCodeByType("courseType"));
        modelMap.put("serviceType", codeService.listCodeByType("serviceType"));
        modelMap.put("tenantId", tenantId);
        return "tenant.course";
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
    public ReturnBean insert(@RequestParam String shortName,
                             @RequestParam String serviceType,  //fuwuleixing
                             @RequestParam String schoolOrg,   //orgId
                             @RequestParam String schoolUser,  //练习人
                             @RequestParam String sellOrg,    //xiaoshouguishu
                             @RequestParam String sellUser,    //
                             @RequestParam String managerOrg,  //guanliyuansuoshujihou
                             @RequestParam String managerUser,  //managerId
                             @RequestParam String managerSell,  //xiaoshou人Id
                             @RequestParam String serviceUser,  //本地服务负责任
                             @RequestParam String scoreService, //学习报道是否要
                             @RequestParam String resourceService,  //是否开启本地部署
                             @RequestParam String tenantTime,  //交付日期
                             @RequestParam String tenantName,
                             @RequestParam String orgName,
                             @RequestParam String currentStatus
    ) {
        //创建租户
        Tenant tenant = new Tenant();
        ReturnBean returnBean = new ReturnBean();
        tenant.setCourseNum(0);
        if ("10".equals(serviceType)) {
            tenant.setServiceType(10);
        } else if ("20".equals(serviceType)) {
            tenant.setServiceType(20);
        }
        if ("10".equals(currentStatus)) {
            tenant.setCurrentStatus(10);
        } else if ("20".equals(currentStatus)) {
            tenant.setCurrentStatus(20);
        }
        tenant.setTenantName(tenantName);
        tenant.setDomain(shortName);
        tenant.setOrgName(orgName);
        tenant.setOrgId(StringTools.getInteger(schoolOrg == null ? "0" : schoolOrg));
        tenant = tenantService.insert(tenant);
        Buyer buyer = new Buyer();
        buyer.setUserId(StringTools.getInteger(schoolUser));
        buyer.setContentId(tenant.getTenantId());
        buyer.setContentType(Constants.ServiceType.Tenant.toString());
        buyerService.insert(buyer);
//        buyer
        //销售所属
        Seller seller = new Seller();
        seller.setUserId(StringTools.getInteger(sellUser));
        seller.setOrgId(StringTools.getInteger(sellOrg));
        seller.setContentId(tenant.getTenantId());
        seller.setContentType(Constants.ServiceType.Tenant.toString());
        sellService.insert(seller);
        //管理所属
        Manager manager = new Manager();
        manager.setOrgId(managerOrg == null ? null : Integer.valueOf(managerOrg));
        manager.setUserId(StringTools.getInteger(managerUser));
        manager.setContentId(tenant.getTenantId());
        manager.setContentType(Constants.ServiceType.Tenant.toString());
        managerService.insert(manager);
        Serve serve = new Serve();
        serve.setOrgId(tenant.getOrgId());
        serve.setContextId(tenant.getTenantId());
        serve.setServiceType(Constants.ServiceType.Tenant.toString());
        serve.setStartTime(DateTools.getDateTime(tenantTime));
        serve.setExpireStatus(10);
        serve.setState(1);
        serveService.insert(serve);
        Spoc spoc = new Spoc();
        spoc.setTenantId(tenant.getTenantId());
        spoc.setExecUserId(StringTools.getInteger(serviceUser));
        spoc.setSpocState(StringTools.getInteger(resourceService));
        spocService.insert(spoc);
        Report report = new Report();
        report.setTenantId(tenant.getTenantId());
        report.setReportUserId(StringTools.getInteger(managerSell));
        report.setReportState(StringTools.getInteger(scoreService));
        reportService.insert(report);
        returnBean.setStatus(true);
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
