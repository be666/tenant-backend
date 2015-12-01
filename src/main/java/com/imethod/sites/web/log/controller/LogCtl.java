package com.imethod.sites.web.log.controller;

import com.imethod.sites.web.log.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by bcaring on 15/12/1.
 */
@Controller
public class LogCtl {

    @Autowired
    private LogService logService;
}
