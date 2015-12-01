package com.imethod.sites.web.alarm.controller;

import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.alarm.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class AlarmCtl {

    Logger logger = LoggerFactory.getLogger(AlarmCtl.class);
    @Autowired
    private AlarmService alarmService;

    @RequestMapping(value = "/alarm", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean list(@RequestParam(required = false) String query,
                           @RequestParam(required = false, defaultValue = "1") Long pageIndex,
                           @RequestParam(required = false, defaultValue = "10") Long pageSize,
                           @RequestParam(required = false, defaultValue = "tenant") String serviceType,
                           @RequestParam(required = false) Integer expireStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            if(serviceType.equals("tenant")||serviceType.equals("course")){
                map.put("pageMaker", alarmService.pageAlarm(query, serviceType, expireStatus, pageIndex, pageSize));
                map.put("countAll",alarmService.countServiceType(serviceType));
                map.put("count30", alarmService.countTypeAndExpire(serviceType, 30));
                map.put("count20", alarmService.countTypeAndExpire(serviceType, 20));
            }
            return new ReturnBean(map);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return new ReturnBean("查询出错");
        }
    }
}
