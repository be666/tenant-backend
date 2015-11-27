package com.imethod.sites.web.bi.controller;

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
public class BiCtl {
    @RequestMapping("/bi")
    public String bi() {
        return "bi";
    }
}
