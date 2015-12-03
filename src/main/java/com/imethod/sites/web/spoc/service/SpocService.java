package com.imethod.sites.web.spoc.service;

import com.imethod.domain.Spoc;
import com.imethod.sites.web.spoc.dao.SpocDao;
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
public class SpocService {

    @Autowired
    private SpocDao spocDao;

    public Spoc insert(Spoc spoc) {
        return spocDao.insert(spoc);
    }
}
