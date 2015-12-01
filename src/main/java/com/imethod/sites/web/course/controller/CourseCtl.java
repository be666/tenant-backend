package com.imethod.sites.web.course.controller;

import com.imethod.constant.Constants;
import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.*;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.job.service.ServeService;
import com.imethod.sites.web.tenant.service.TenantCourseService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CourseCtl {

    Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private ServeService serveService;
    @Autowired
    private TenantCourseService tenantCourseService;

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

    @RequestMapping(value = "/tenant/{tenantId}/course/new", method = RequestMethod.GET)
    public String info(@PathVariable String tenantId, ModelMap modelMap) {
        modelMap.put("tenantId", tenantId);

        return "course.info";
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

    @RequestMapping(value = "/tenant/{tenantId}/course.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean course(@PathVariable Long tenantId,
                             @RequestParam(required = false) String query,
                             @RequestParam(required = false) Integer courseType,
                             @RequestParam(required = false) Long courseId,
                             @RequestParam(required = false) Long pageIndex,
                             @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("tenantId", tenantId);
        PageMaker pageMaker = courseService.pageCourseRelation(query, courseType, courseId, tenantId, pageIndex, pageSize);
        map.put("pageMaker", pageMaker);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/tenant/{tenantId}/course/buy.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean courseBuy(@PathVariable Long tenantId,
                                @RequestParam(required = false) String query,
                                @RequestParam(required = false) Integer courseType,
                                @RequestParam(required = false) Long courseId,
                                @RequestParam(required = false) Long pageIndex,
                                @RequestParam(required = false) Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("tenantId", tenantId);
        PageMaker pageMaker = courseService.pageCourseUnRelation(query, courseType, courseId, tenantId, pageIndex, pageSize);
        map.put("pageMaker", pageMaker);
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/tenant/{tenantId}/course/buy/{courseId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean courseBuy(@PathVariable Long tenantId,
                                @PathVariable Long courseId) {
        Map<String, Object> map = new HashMap<>();
        Course course = courseService.getById(StringTools.getInteger(courseId));
        Tenant tenant = tenantService.getById(StringTools.getInteger(tenantId));
        TenantCourseRp tenantCourseRp = new TenantCourseRp();
        tenantCourseRp.setCourseId(course.getCourseId());
        tenantCourseRp.setTenantId(tenant.getTenantId());
        tenantCourseRp.setState(1);
        tenantCourseRp.setIsOwner(0);
        tenantCourseService.insert(tenantCourseRp);
        Serve serve = new Serve();
        serve.setOrgId(tenant.getOrgId());
        serve.setContextId(course.getCourseId());
        serve.setServiceType(Constants.ServiceType.Course.toString());
        serve.setStartTime(DateTools.getCurrentDateTime());
        serve.setEndTime(DateTools.getCurrentDateTime());
        serve.setServiceMoney(0);
        serve.setExpireStatus(10);
        serve.setState(1);
        serve.setForever(0);
        serveService.insert(serve);
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
     * add course
     *
     * @return
     */
    @RequestMapping(value = "/tenant/{tenantId}/course/save", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean save(
            @PathVariable String tenantId,
            @RequestParam String name,
            @RequestParam String courseType,
            @RequestParam String serviceType,
            @RequestParam String serviceTime,
            @RequestParam String courseMoney,
            @RequestParam String videoTime,
            @RequestParam String courseScore,
            @RequestParam String chapterMoney,
            @RequestParam String chapterNum,
            @RequestParam String chapterAll,
            @RequestParam String peopleMoney,
            @RequestParam String peopleNum,
            @RequestParam String peopleAll
    ) {

        ReturnBean ret = new ReturnBean();
        try {
            Course course = new Course();
            course.setCourseName(name);
            course.setCourseType(StringTools.getInteger(courseType));
            course.setTenantId(StringTools.getInteger(tenantId));
            course.setChapterNum(StringTools.getInteger(chapterNum));
            course.setChapterMoney(StringTools.getInteger(chapterMoney));
            course.setChapterAll(StringTools.getInteger(chapterAll));
            course.setPeopleAll(StringTools.getInteger(peopleAll));
            course.setPeopleNum(StringTools.getInteger(peopleNum));
            course.setPeopleMoney(StringTools.getInteger(peopleMoney));
            course.setState(1);
            course.setScore(StringTools.getInteger(courseScore));
            course.setVideoLength(StringTools.getInteger(videoTime));
            course = courseService.insert(course);
            TenantCourseRp tenantCourseRp = new TenantCourseRp();
            tenantCourseRp.setCourseId(course.getCourseId());
            tenantCourseRp.setEndTime(new Date());
            tenantCourseRp.setTenantId(course.getTenantId());
            tenantCourseRp.setState(1);
            tenantCourseRp.setIsOwner(1);
            tenantCourseService.insert(tenantCourseRp);
            Tenant tenant = tenantService.getById(StringTools.getInteger(tenantId));
            Serve serve = new Serve();
            serve.setOrgId(tenant.getOrgId());
            serve.setContextId(course.getCourseId());
            serve.setServiceType(Constants.ServiceType.Course.toString());
            serve.setStartTime(DateTools.getCurrentDateTime());
            serve.setEndTime(DateTools.getDateTime(serviceTime));
            serve.setServiceMoney(StringTools.getInteger(courseMoney));
            serve.setForever(1);
            serve.setExpireStatus(10);
            serve.setState(1);
            serveService.insert(serve);
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
