package com.imethod.sites.web.service.service;

import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Service;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.service.dao.ServiceDao;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@org.springframework.stereotype.Service
public class ServiceService {

    Logger logger = LoggerFactory.getLogger(ServiceService.class);

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private CodeService codeService;

    public Service insert(Service service) {
        Date now =  DateTools.getCurrentDateTime();

        return serviceDao.insert(service);
    }

    public void update(Service service) throws InvocationTargetException, IllegalAccessException {
        serviceDao.update(service);

    }



    public List<Service> listServiceByExpire(Integer expireStatus) {

        return  listServiceByType(null,expireStatus);
    }

    public List<Service> listServiceByType(@NotNull String serviceType,Integer expireStatus) {

        return  serviceDao.listServiceByType(serviceType,expireStatus);
    }
}
