package com.imethod.sites.web.buyer.service;

import com.imethod.domain.Buyer;
import com.imethod.sites.web.buyer.dao.BuyerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class BuyerService {

    @Autowired
    private BuyerDao buyerDao;

    public Buyer insert(Buyer buyer) {
        return buyerDao.insert(buyer);
    }
}
