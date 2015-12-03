package com.imethod.sites.web.serve.controller;

import com.imethod.core.util.DateTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.ReturnBean;
import com.imethod.domain.Serve;
import com.imethod.sites.web.serve.service.ServeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcaring on 15/12/3.
 */
@Controller
public class ServeCtrl {
    @Autowired
    private ServeService serveService;

    @RequestMapping(value = "/serve/{contextType}/{contextId}.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean serve(@PathVariable String contextType,
                            @PathVariable String contextId) {
        Serve serve = serveService.getServe(contextId, contextType);
        Map<String, Object> map = new HashMap<>();
        map.put("serve", serve);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/serve/{serveId}.post", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean serve(@PathVariable String serveId,
                            @RequestParam String startTime,
                            @RequestParam String endTime,
                            @RequestParam(required = false) String serviceType) {
        Serve serve = new Serve();
        serve.setServiceId(StringTools.getInteger(serveId));
        serve.setStartTime(DateTools.getDateTime(startTime));
        serve.setEndTime(DateTools.getDateTime(endTime));
        if(StringTools.isNotEmpty(serviceType)){
            serve.setServiceType(StringTools.getInteger(serviceType));
        }
        serveService.update(serve);
        return ReturnBean.success("更新成功!");
    }
}
