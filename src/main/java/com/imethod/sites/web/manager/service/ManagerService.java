package com.imethod.sites.web.manager.service;

import com.imethod.domain.Manager;
import com.imethod.sites.web.manager.dao.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    public Manager insert(Manager manager) {
        return managerDao.insert(manager);
    }
}
