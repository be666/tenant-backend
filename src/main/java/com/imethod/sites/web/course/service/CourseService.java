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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Date now = DateTools.getCurrentDateTime();
        return courseDao.insert(course);
    }

    public void update(Course course) {
        Course courseDB = courseDao.loadById(course.getCourseId());

        courseDB.setCourseName(course.getCourseName());
        courseDB.setState(course.getState());

        try {
            courseDao.update(course);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    public PageMaker pageCourseRelation(String query, Integer courseType, Long courseId, Long tenantId, Long pageIndex, Long pageSize) {
        return courseDao.pageCourseRelation(query, courseType, courseId, tenantId, pageIndex, pageSize);
    }


    public ReturnBean delete(Integer courseId) {

        ReturnBean returnBean = new ReturnBean();
        Course courseDB = courseDao.loadById(courseId);
        if (courseDB == null) {
            return new ReturnBean("课程不存在！");
        }
        try {
            courseDao.update(courseDB);
        } catch (IllegalAccessException | InvocationTargetException e) {
            returnBean.setStatus(ReturnBean.FALSE);
            returnBean.setMsg("删除失败！");
        }
        return returnBean;
    }

    public List<Course> listCourseAll() {
        return courseDao.listCourseAll();
    }

    public Course getById(Integer courseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("course_id", courseId);
        try {
            return courseDao.load(Course.class, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
