package com.imethod.sites.web.log.service;

import com.imethod.domain.LoginRecord;
import com.imethod.domain.VisitRecord;
import com.imethod.sites.web.log.dao.LogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    public void insertVisit(VisitRecord visitRecord) {
        logDao.insert(visitRecord);
    }

    public void insertLogin(LoginRecord loginRecord) {
        logDao.insert(loginRecord);
    }
}
