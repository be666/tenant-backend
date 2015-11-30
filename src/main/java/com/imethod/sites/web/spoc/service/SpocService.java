package com.imethod.sites.web.spoc.service;

import com.imethod.domain.Spoc;
import com.imethod.sites.web.spoc.dao.SpocDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class SpocService {

    @Autowired
    private SpocDao spocDao;

    public Spoc insert(Spoc spoc) {
        return spocDao.insert(spoc);
    }
}
