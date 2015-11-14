package com.imethod.sites.web.course.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Course;
import com.imethod.domain.ReturnBean;
import com.imethod.sites.web.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Controller
public class CourseCtl {

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseService courseService;

    /**
     * add course
     * @param course
     * @return
     */
    @RequestMapping(value="/course",method = RequestMethod.PUT)
    @ResponseBody
    public ReturnBean insert(Course course) {

        ReturnBean ret = new ReturnBean();
        try {
            courseService.insert(course);
        }catch (Exception e){
            ret.setMsg("保存出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    /**
     * update course
     * @param course
     * @return
     */
    @RequestMapping(value="/course",method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(Course course) {

        ReturnBean ret = new ReturnBean();
        try {
            courseService.update(course);

        }catch (Exception e){
            ret.setMsg("更新出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    /**
     * delete course
     * @param courseId
     * @return
     */
    @RequestMapping(value="/course/{courseId}",method = RequestMethod.DELETE)
    public ReturnBean update(@PathVariable Integer courseId) {
        ReturnBean ret = new ReturnBean();
        ret =  courseService.delete(courseId);
        return ret;
    }

    @RequestMapping(value="/course",method = RequestMethod.GET)
    public String list(@RequestParam(required = false) String query,
                        @RequestParam(required = false) Long pageIndex,
                        @RequestParam(required = false) Long pageSize) {

        pageIndex = pageIndex==null?1:pageIndex;
        pageSize = pageSize==null?10:pageSize;
        PageMaker pageMaker = null;
        try {
            pageMaker = courseService.listCourse(query, pageIndex, pageSize);
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "";
    }
}
