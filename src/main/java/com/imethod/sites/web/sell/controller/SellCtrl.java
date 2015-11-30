package com.imethod.sites.web.sell.controller;

import com.imethod.sites.web.sell.service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by bcaring on 15/11/30.
 */
@Controller
public class SellCtrl {

    @Autowired
    private SellService sellService;
}
