package com.imethod.sites.web.classes.service;

import com.imethod.constant.Constants;
import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Classes;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.classes.dao.ClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

@Service
public class ClassService {

    Logger logger = LoggerFactory.getLogger(ClassService.class);

    @Autowired
    private ClassDao classDao;

    public Classes insert(Classes classes) {

        return classDao.insert(classes);
    }

    public void update(Classes classes) {
        Date now = DateTools.getCurrentDateTime();


        try {
            classDao.update(classes);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    public PageMaker pageClassesRelation(String query, Integer classesType, Long classId,Long tenantId, Long pageIndex, Long pageSize) {
        PageMaker pageMaker =  classDao.pageClassRelation(query, classesType, classId, tenantId, pageIndex, pageSize);
        return pageMaker;
    }


    public ReturnBean delete(Integer classId) {

        ReturnBean returnBean = new ReturnBean();
        Classes classes = classDao.loadById(classId);
        if(classes!=null){
            classes.setState(Constants.STATE_FALSE);
            try {
                classDao.update(classes);
            } catch (IllegalAccessException | InvocationTargetException e) {
                returnBean.setStatus(false);
                returnBean.setMsg("更新出错!");
            }
        }
        return returnBean;
    }


}
