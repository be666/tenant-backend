package com.imethod.sites.web.serve.service;

import com.imethod.constant.Constants;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Serve;
import com.imethod.domain.Tenant;
import com.imethod.domain.User;
import com.imethod.sites.web.classes.service.ClassService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.email.bean.EmailMessage;
import com.imethod.sites.web.email.service.EmailService;
import com.imethod.sites.web.serve.dao.ServeDao;
import com.imethod.sites.web.tenant.service.TenantService;
import com.imethod.sys.auth.LUser;
import com.imethod.sys.auth.UserContent;
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
    private ServeDao serviceDao;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassService classService;

    @Autowired
    private EmailService emailService;

    public Serve insert(Serve service) {
        return serviceDao.insert(service);
    }

    public void update(Serve service) {
        try {
            serviceDao.update(service);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public List<Serve> listServiceByExpire(Integer expireStatus) {

        return listServiceByType(null, expireStatus);
    }

    public List<Serve> listServiceByType(String serviceType, Integer expireStatus) {

        return serviceDao.listServiceByType(serviceType, expireStatus);
    }


    public void executeServiceJob() {
        User user = new User();
        user.setUserId(Constants.SYS_USER_JOB);
        UserContent.setLUser(new LUser(user));
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
        UserContent.clear();
    }

    private void sendMail(Serve serve) {
        String ContextType = serve.getContextType();
        String serviceName = null;
        if ("tenant".equals(ContextType)) {
            Tenant tenant = tenantService.getById(serve.getContextId());
            serviceName = "租户:" + tenant == null ? "" : tenant.getTenantName();
        } else if ("course".equals(ContextType)) {
            //TeaantCourse course = tenantService.getById(serve.getContextId());
            serviceName = "课程";
        } else if ("class".equals(ContextType)) {
            serviceName = "班次";
        } else {
            return;
        }

        String emailContent = serviceName + "即将过期,过期时间为：" + DateTools.format(serve.getEndTime(), DateTools.DateType.DATE_TIME);
        List<String> emailToList = new ArrayList<>();
        emailToList.add("iMethod@gaoxiaobang.com");
        EmailMessage emailMessage = new EmailMessage(emailContent, emailToList);
        emailService.sendHtmlMail(emailMessage);
    }


    public Serve getServe(String contextId, String contextType) {
        List<Serve> serveList = serviceDao.getServe(contextId, contextType);
        if (!serveList.isEmpty()) {
            return serveList.get(0);
        }
        return null;
    }
}
