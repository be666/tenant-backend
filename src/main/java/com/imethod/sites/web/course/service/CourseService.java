package com.imethod.sites.web.course.service;

import com.imethod.constant.Constants;
import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.domain.Course;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.course.dao.CourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Service
public class CourseService {

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseDao courseDao;

    public Course insert(Course course) {
        Date now =  DateTools.getCurrentDateTime();
        course.setCreateAt(now);
        course.setUpdateAt(now);
        course.setDeleteFlag(Constants.DELETED_FALSE);
        return courseDao.insert(course);
    }

    public void update(Course course) {
        Date now =  DateTools.getCurrentDateTime();
        Course courseDB =  courseDao.loadById(course.getCourseId());

        courseDB.setCourseName(course.getCourseName());
        courseDB.setState(course.getState());
        courseDB.setUpdateAt(now);
        courseDB.setUpdaterId(course.getUpdaterId());

        try {
            courseDao.update(course);
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    public PageMaker listCourse(String query, Long pageIndex, Long pageSize) {
        PageMaker pageMaker = courseDao.listCourse(query, pageIndex, pageSize);
        return pageMaker;
    }


    public ReturnBean delete(Integer courseId) {

        ReturnBean returnBean = new ReturnBean();
        Course courseDB =  courseDao.loadById(courseId);
        if(courseDB==null){
             return new ReturnBean(ReturnBean.FALSE,"课程不存在！");
        }
        courseDB.setDeleteFlag(Constants.DELETED_TRUE);
        try {
            courseDao.update(courseDB);
        } catch (IllegalAccessException | InvocationTargetException e) {
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("删除失败！");
        }
        return returnBean;
    }
}
