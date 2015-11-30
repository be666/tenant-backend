package com.imethod.sites.web.org.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Org;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.org.service.OrgService;
import com.imethod.sites.web.region.service.RegionService;
import com.imethod.sites.web.sys.auth.UserContent;
import com.imethod.sites.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/20.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class OrgCtrl {

    Logger logger = LoggerFactory.getLogger(OrgCtrl.class);

    @Autowired
    private OrgService orgService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/org", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("orgType", codeService.listCodeByType("orgType"));
        modelMap.put("schoolType", codeService.listCodeByType("schoolType"));
        modelMap.put("region", regionService.getRegionTree());
        return "org";
    }

    @RequestMapping(value = "/org.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean query(@RequestParam(required = false) String query,
                            @RequestParam(required = false) Long pageIndex,
                            @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageMaker pageMaker = null;
        try {
            pageMaker = orgService.listOrg(query, pageIndex, pageSize);
            map.put("pageMaker", pageMaker);
        } catch (Exception e) {
            logger.error(e);

        }
        return new ReturnBean(map);
    }

    /**
     * add user
     *
     * @param org
     * @return
     */
    @RequestMapping(value = "/org", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean insert(Org org) {
        try {
            org.setState(1);
            org = orgService.insert(org);
            Map<String, Object> map = new HashMap<>();
            map.put("org", org);
            return new ReturnBean(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnBean("保存失败， " + e.getMessage());
        }
    }


    @RequestMapping(value = "/org/{orgId}/user", method = RequestMethod.GET)
    public String orgUser(@PathVariable String orgId,
                          ModelMap modelMap) {
        modelMap.put("orgId", orgId);
        modelMap.put("org", orgService.loadOrg(orgId));
        return "org.user";
    }

    @RequestMapping(value = "/org/{orgId}/user.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean orgUser(@PathVariable String orgId,
                              @RequestParam(required = false) String query,
                              @RequestParam(required = false) Long pageIndex,
                              @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        PageMaker pageMaker = null;
        try {
            pageMaker = userService.listOrgUser(orgId,query, pageIndex, pageSize);
            map.put("pageMaker", pageMaker);
        } catch (Exception e) {
            logger.error(e);
        }
        return new ReturnBean(map);
    }
}
