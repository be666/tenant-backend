package com.imethod.sites.web.alarm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * time : 15/11/15.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class AlarmCtl {

    @RequestMapping("/alarm")
    public String alarm() {
        return "alarm";
    }
}
