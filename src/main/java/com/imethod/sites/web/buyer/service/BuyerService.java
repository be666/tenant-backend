package com.imethod.sites.web.buyer.service;

import com.imethod.domain.Buyer;
import com.imethod.sites.web.buyer.dao.BuyerDao;
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
public class BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    public Buyer insert(Buyer buyer) {
        return buyerDao.insert(buyer);
    }
}
