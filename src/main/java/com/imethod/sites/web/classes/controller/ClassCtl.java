package com.imethod.sites.web.classes.controller;

import com.imethod.constant.Constants;
import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.core.util.DateTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.*;
import com.imethod.sites.web.classes.service.ClassService;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.job.service.ServeService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ClassCtl {

    Logger logger = LoggerFactory.getLogger(ClassCtl.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private ServeService serveService;

    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public String index(ModelMap modelMap) {

        List<Course> courseList = courseService.listCourseAll();
        List<Tenant> tenantList = tenantService.listTenantAll();
        modelMap.put("courseList", courseList);
        modelMap.put("tenantList", tenantList);
        modelMap.put("finishStatus", codeService.listCodeByType("finishStatus"));
        return "class";
    }

    @RequestMapping(value = "/course/{courseId}/class", method = RequestMethod.GET)
    public String courseIndex(@PathVariable String courseId,
                              ModelMap modelMap) {

        modelMap.put("finishStatus", codeService.listCodeByType("finishStatus"));
        modelMap.put("courseId", courseId);
        return "course.class";
    }

    @RequestMapping(value = "/course/{courseId}/class.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean courseList(@RequestParam(required = false) String query,
                                 @RequestParam(required = false) Integer finishStatus,
                                 @PathVariable Long courseId,
                                 @RequestParam(required = false) Long pageIndex,
                                 @RequestParam(required = false) Long pageSize) {

        Map<String, Object> map = new HashMap<>();
        try {
            PageMaker pageMaker = classService.pageClassesRelation(query, finishStatus, courseId, null, pageIndex, pageSize);
            map.put("pageMaker", pageMaker);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return new ReturnBean(map);
    }

    @RequestMapping(value = "/class.ajax", method = RequestMethod.GET)
    @ResponseBody
    public ReturnBean list(@RequestParam(required = false) String query,
                           @RequestParam(required = false) Integer finishStatus,
                           @RequestParam(required = false) Long courseId,
                           @RequestParam(required = false) Long tenantId,
                           @RequestParam(required = false) Long pageIndex,
                           @RequestParam(required = false) Long pageSize) {

        Map<String, Object> map = new HashMap<>();
        try {
            PageMaker pageMaker = classService.pageClassesRelation(query, finishStatus, courseId, tenantId, pageIndex, pageSize);
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
     * @param classes
     * @return
     */
    @RequestMapping(value = "/class", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean insert(Classes classes) {

        ReturnBean ret = new ReturnBean();
        try {
            classService.insert(classes);
        } catch (Exception e) {
            ret.setMsg("保存出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    @RequestMapping(value = "/course/{courseId}/class", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean insert(@PathVariable String courseId,
                             @RequestParam String openTime,
                             @RequestParam String className,
                             @RequestParam String endTime,
                             @RequestParam String template,
                             @RequestParam String score,
                             @RequestParam String video,
                             @RequestParam String topic,
                             @RequestParam String quiz,
                             @RequestParam String exam,
                             @RequestParam String task) {

        ReturnBean ret = new ReturnBean();
        try {
            Course course = courseService.getById(StringTools.getInteger(courseId));
            Classes classes = new Classes();
            classes.setClassName(className);
            classes.setTenantId(course.getTenantId());
            classes.setCourseId(course.getCourseId());
            classes.setClassEndTime(DateTools.getDateTime(endTime));
            classes.setClassStartTime(DateTools.getDateTime(openTime));
            classes.setExamWeight(StringTools.getInteger(exam));
            classes.setAssignmentWeight(StringTools.getInteger(task));
            classes.setTopicWeight(StringTools.getInteger(topic));
            classes.setQuizWeight(StringTools.getInteger(quiz));
            classes.setVideoWeight(StringTools.getInteger(video));
            classes.setIsWeight(StringTools.getInteger(score));
            classes.setTemplateCourse(StringTools.getInteger(template));
            classes.setFinishStatus(Constants.FINISH_STATUS_INIT);
            classes.setState(1);
            classes = classService.insert(classes);
            Tenant tenant = tenantService.getById(course.getTenantId());
            Serve serve = new Serve();
            serve.setOrgId(tenant.getOrgId());
            serve.setContextId(classes.getClassId());
            serve.setContextType(Constants.ServiceType.Class.toString());
            serve.setStartTime(DateTools.getDateTime(openTime));
            serve.setEndTime(DateTools.getDateTime(endTime));
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
     * @param classes
     * @return
     */
    @RequestMapping(value = "/class/{classId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(@PathVariable Long classId, Classes classes) {

        ReturnBean ret = new ReturnBean();
        try {
            classService.update(classes);

        } catch (Exception e) {
            ret.setMsg("更新出错");
            ret.setStatus(ReturnBean.FALSE);
        }
        return ret;
    }

    /**
     * delete course
     *
     * @param classId
     * @return
     */
    @RequestMapping(value = "/class/del/{classId}", method = RequestMethod.POST)
    @ResponseBody
    public ReturnBean update(@PathVariable Integer classId) {
        ReturnBean ret = classService.delete(classId);
        return ret;
    }

}
