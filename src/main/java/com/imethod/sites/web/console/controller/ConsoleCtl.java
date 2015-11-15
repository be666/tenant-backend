package com.imethod.sites.web.console.controller;

import com.imethod.sites.web.console.service.ConsoleService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ConsoleCtl {

    @Autowired
    private ConsoleService consoleService;


    @RequestMapping("/console")
    public String console() {
        return "console";
    }

}
