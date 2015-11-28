package com.imethod.sites.web.course.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Code;
import com.imethod.domain.Course;
import com.imethod.domain.ReturnBean;
import com.imethod.domain.Tenant;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
@Controller
public class CourseCtl {

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private CodeService codeService;

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        try {
            List<Course> courseList = courseService.listCourseAll();
            List<Tenant> tenantList = tenantService.listTenantAll();
            modelMap.put("courseList", courseList);
            modelMap.put("tenantList", tenantList);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "course";
    }

    @RequestMapping(value = "/course.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean ajax(@RequestParam(required = false) String query,
                           @RequestParam(required = false) Integer courseType,
                           @RequestParam(required = false) Long courseId,
                           @RequestParam(required = false) Long tenantId,
                           @RequestParam(required = false) Long pageIndex,
                           @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        try {
            PageMaker pageMaker = courseService.pageCourseRelation(query, courseType, courseId, tenantId, pageIndex, pageSize);
            map.put("pageMaker", pageMaker);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return new ReturnBean(map);
    }


    /**
     * add course
     *
     * @param course
     * @return
     */
    @RequestMapping(value = "/course", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean insert(Course course) {

        ReturnBean ret = new ReturnBean();
        try {
            courseService.insert(course);
        } catch (Exception e) {
            ret.setMsg("保存出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    /**
     * update course
     *
     * @param course
     * @return
     */
    @RequestMapping(value = "/course/{curseId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(@PathVariable Long curseId, Course course) {

        ReturnBean ret = new ReturnBean();
        try {
            courseService.update(course);
        } catch (Exception e) {
            ret.setMsg("更新出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    /**
     * delete course
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/course/del/{courseId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(@PathVariable Integer courseId) {
        ReturnBean ret = new ReturnBean();
        ret = courseService.delete(courseId);
        return ret;
    }

}
