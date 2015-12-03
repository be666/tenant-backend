package com.imethod.sites.web.sell.service;

import com.imethod.domain.Seller;
import com.imethod.sites.web.sell.dao.SellDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auth : iMethod
 * create_at:  15/11/30.
 * desc:
 * note:
 *  1.
 */
@Service
public class SellService {

    @Autowired
    private SellDao sellDao;

    public Seller insert(Seller seller){
        return sellDao.insert(seller);
    }
}
