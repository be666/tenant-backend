package com.imethod.sites.web.manager.service;

import com.imethod.domain.Manager;
import com.imethod.sites.web.manager.dao.ManagerDao;
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
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public Manager insert(Manager manager) {
        return managerDao.insert(manager);
    }
}
