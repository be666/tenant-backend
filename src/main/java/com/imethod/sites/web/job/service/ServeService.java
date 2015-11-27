package com.imethod.sites.web.job.service;

import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Serve;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.job.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class ServeService {

    Logger logger = LoggerFactory.getLogger(ServeService.class);

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private CodeService codeService;

    public Service insert(Service service) {
        Date now = DateTools.getCurrentDateTime();

        return serviceDao.insert(service);
    }

    public void update(Service service) throws InvocationTargetException, IllegalAccessException {
        serviceDao.update(service);

    }


    public List<Serve> listServiceByExpire(Integer expireStatus) {

        return listServiceByType(null, expireStatus);
    }

    public List<Serve> listServiceByType(String serviceType, Integer expireStatus) {

        return serviceDao.listServiceByType(serviceType, expireStatus);
    }


    public void executeServiceJob() {
        List<Serve> serviceList = listServiceByExpire(10);

        for (Serve serve : serviceList) {
            Date endDateTime = serve.getEndTime();
            System.out.println(endDateTime);
            if (endDateTime == null) {
                continue;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDateTime);

            Date nowDate = calendar.getTime();
            System.out.println(nowDate);
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            long now = Calendar.getInstance().getTime().getTime();
            if (calendar.getTime().getTime() < now) {
                serve.setExpireStatus(20);
                try {
                    serviceDao.update(serve);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
