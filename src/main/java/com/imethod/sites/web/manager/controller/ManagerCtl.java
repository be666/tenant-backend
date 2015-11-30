package com.imethod.sites.web.manager.controller;

import com.imethod.sites.web.manager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by bcaring on 15/11/30.
 */
@Controller
public class ManagerCtl {
    @Autowired
    private ManagerService managerService;
}
