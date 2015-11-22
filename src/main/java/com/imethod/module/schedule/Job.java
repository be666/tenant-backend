package com.imethod.module.schedule;

import com.imethod.domain.Service;
import com.imethod.sites.web.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * time : 15/11/8.
 * auth :
 * desc :
 * tips :
 * 1.
 */
public class Job {

    @Autowired
    private ServiceService serviceService;

    public void executeServiceJob() {
        List<Service> serviceList = serviceService.listServiceByExpire(10);

        for(Service service : serviceList){
            Date endDateTime = service.getEndTime();
            System.out.println(endDateTime);
            if(endDateTime== null){
                continue;
            }
            Calendar calendar =  Calendar.getInstance();
            calendar.setTime(endDateTime);

            Date nowDate = calendar.getTime();
            System.out.println(nowDate);
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            long now = Calendar.getInstance().getTime().getTime();
            if(calendar.getTime().getTime()<now){
                service.setExpireStatus(20);
                try {
                    serviceService.update(service);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
