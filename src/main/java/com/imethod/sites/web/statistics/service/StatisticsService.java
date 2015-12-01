package com.imethod.sites.web.statistics.service;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.util.ExceptionTools;
import com.imethod.domain.Statistics;
import com.imethod.sites.web.statistics.dao.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;

    public PageMaker pagesStatistics(String type,String query, Date startDate,Date endDate, Long pageIndex, Long pageSize) {
        PageMaker pageMaker = null;
        if(type.equals("tenant")){
            pageMaker = statisticsDao.getTenantStatistics(query, startDate, endDate, pageIndex, pageSize);
        }else if(type.equals("course")){
            pageMaker =  statisticsDao.getCourseStatistics(query, startDate, endDate, pageIndex, pageSize);
        }else if(type.equals("class")){
            pageMaker =  statisticsDao.getClassStatistics(query, startDate, endDate, pageIndex, pageSize);
        }

        return pageMaker;
    }

    public void save(Statistics statistics){
        statisticsDao.insert(statistics);
    }

    public void update(Statistics statistics){
        Statistics sta = statisticsDao.getByCondition(statistics.getType(), statistics.getContextId());
        if(sta==null){
            statisticsDao.insert(statistics);
        }else{
            try {
                statisticsDao.update(statistics);
            }catch (Exception e){
                ExceptionTools.unchecked(e);
            }
        }
    }


}
