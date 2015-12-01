package com.imethod.sites.web.alarm.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.sites.web.alarm.dao.AlarmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmService {
    @Autowired
    private AlarmDao alarmDao;

    public int countServiceType(String serviceType){
        return alarmDao.countExpire(serviceType,null);
    }

    public int countTypeAndExpire(String serviceType,Integer expireStatus){
        return alarmDao.countExpire(serviceType,expireStatus);
    }

    public PageMaker getTenantAlarm(String query, Integer expireStatus,Long pageIndex,Long pageSize){
        return alarmDao.getTenantAlarm(query, expireStatus,pageIndex,pageSize);
    }



    public PageMaker getCourseAlarm(String query, Integer expireStatus,Long pageIndex,Long pageSize){
        return alarmDao.getCourseAlarm(query,expireStatus, pageIndex, pageSize);
    }


}
