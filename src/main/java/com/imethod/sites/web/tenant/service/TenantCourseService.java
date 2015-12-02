package com.imethod.sites.web.tenant.service;

import com.imethod.domain.Course;
import com.imethod.domain.TenantCourseRp;
import com.imethod.sites.web.tenant.dao.TenantCourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bcaring on 15/11/30.
 */
@Service
public class TenantCourseService {

    @Autowired
    private TenantCourseDao tenantCourseDao;

    public TenantCourseRp insert(TenantCourseRp tenantCourseRp) {
        return tenantCourseDao.insert(tenantCourseRp);
    }

    public TenantCourseRp getById(Integer courseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tc_id", courseId);
        try {
            return tenantCourseDao.load(TenantCourseRp.class, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
