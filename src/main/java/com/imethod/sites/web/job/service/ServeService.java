package com.imethod.sites.web.job.service;

import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Serve;
import com.imethod.domain.Tenant;
import com.imethod.sites.web.classes.service.ClassService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.email.bean.EmailMessage;
import com.imethod.sites.web.email.service.EmailService;
import com.imethod.sites.web.job.dao.ServiceDao;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
    private TenantService tenantService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @Autowired
    private EmailService emailService;

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

            if (endDateTime == null) {
                continue;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(endDateTime);
            calendar.add(Calendar.DAY_OF_MONTH, -2);
            long now = Calendar.getInstance().getTime().getTime();
            if (calendar.getTime().getTime() < now) {
                serve.setExpireStatus(20);
                try {

                    serviceDao.update(serve);
                    sendMail(serve);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendMail(Serve serve) {
        String serviceType = serve.getServiceType();
        String serviceName = null;
        if("tenant".equals(serviceType)){
            Tenant tenant = tenantService.getById(serve.getContextId());
            serviceName = "租户:"+tenant==null?"":tenant.getTenantName();
        }else
        if("course".equals(serviceType)){
            //TeaantCourse course = tenantService.getById(serve.getContextId());
            serviceName = "课程";
        }else
        if("class".equals(serviceType)){
            serviceName = "班次";
        }else{
            return;
        }

        String emailContent = serviceName+"即将过期,过期时间为："+DateTools.format(serve.getEndTime(), DateTools.DateType.DATE_TIME) ;
        List<String> emailToList = new ArrayList<>();
        emailToList.add("jqwang@gaoxiaobang.com");
        EmailMessage emailMessage = new EmailMessage(emailContent,emailToList);
        emailService.sendHtmlMail(emailMessage);
    }


}
