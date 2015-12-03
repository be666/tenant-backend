package com.imethod.sites.web.manager.controller;

import com.imethod.sites.web.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * auth : iMethod
 * create_at:  15/11/30.
 * desc:
 * note:
 *  1.
 */
@Controller
public class ManagerCtl {
    @Autowired
    private ManagerService managerService;
}
