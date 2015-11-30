package com.imethod.sites.web.sell.service;

import com.imethod.domain.Seller;
import com.imethod.sites.web.sell.dao.SellDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class SellService {

    @Autowired
    private SellDao sellDao;

    public Seller insert(Seller seller){
        return sellDao.insert(seller);
    }
}
