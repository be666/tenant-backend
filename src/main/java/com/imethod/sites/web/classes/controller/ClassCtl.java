package com.imethod.sites.web.classes.controller;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.*;
import com.imethod.sites.web.classes.service.ClassService;
import com.imethod.sites.web.code.service.CodeService;
import com.imethod.sites.web.course.service.CourseService;
import com.imethod.sites.web.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = "/class", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) String query,
                       @RequestParam(required = false) Integer finishStatus,
                       @RequestParam(required = false) Long courseId,
                       @RequestParam(required = false) Long tenantId,
                       @RequestParam(required = false) Long pageIndex,
                       @RequestParam(required = false) Long pageSize) {

        Map<String, Object> map = new HashMap<>();
        try {
            PageMaker pageMaker = classService.pageClassesRelation(query, finishStatus, courseId, tenantId, pageIndex, pageSize);
            List<Course> courseList = courseService.listCourseAll();
            List<Tenant> tenantList = tenantService.listTenantAll();
            Map<Integer, Code> finishStatusMap = codeService.listCodeMap("finishStatus");
            map.put("pageMaker", pageMaker);
            map.put("courseList", courseList);
            map.put("tenantList", tenantList);
            map.put("finishStatusMap", finishStatusMap);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return "class";
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
