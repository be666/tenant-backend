package com.imethod.sites.web.statistics.controller;

import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/15.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class StatisticsCtl {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String index() {
        return "statistics";
    }

    @RequestMapping(value = "/statistics.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean list(@RequestParam(required = false) String query,
                           @RequestParam(required = false, defaultValue = "1") Long pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Long pageSize,
                           @RequestParam(required = false, defaultValue = "tenant") String type,
                           @RequestParam(required = false) Date startDate,
                           @RequestParam(required = false) Date andDate) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (type.equals("tenant") || type.equals("course") || type.equals("class")) {
                map.put("pageMaker", statisticsService.pagesStatistics(type, query, startDate, andDate, pageIndex, pageSize));
                map.put("type", type);
            }
            return new ReturnBean(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ReturnBean("查询出错");
        }
    }
}
