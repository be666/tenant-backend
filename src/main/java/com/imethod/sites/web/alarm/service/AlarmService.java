package com.imethod.sites.web.alarm.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.sites.web.alarm.dao.AlarmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    public PageMaker pageAlarm(String query, String serviceType, Integer expireStatus, Long pageIndex, Long pageSize) {
        PageMaker pageMaker = null;
        if(serviceType.equals("tenant")){
            pageMaker = getTenantAlarm(query,expireStatus,pageIndex,pageSize);
        }else if(serviceType.equals("course")){
            pageMaker =  getCourseAlarm(query, expireStatus, pageIndex, pageSize);
        }


        return pageMaker;
    }
}
