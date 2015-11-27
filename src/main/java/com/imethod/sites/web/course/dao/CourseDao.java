package com.imethod.sites.web.course.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.ExceptionTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
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
@Repository
public class CourseDao extends IJdbcTempBaseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ISqlHelp iSqlHelp;


    @Override
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    protected ISqlHelp getISqlHelp() {
        return iSqlHelp;
    }


    public Course loadById(Integer courseId) {
        Map<String, Object> map = new HashMap<>();
        map.put("course_id", courseId);
        Course course = null;
        try {
            course = load(Course.class, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
        return course;
    }


    private static String SQL_LIST_COURSE = "select tcr.tc_id,tcr.course_id,c.course_name,tcr.tenant_id,t.tenant_name,c.course_type,c1.code_name as course_type_name\n" +
            ",s.start_time,s.end_time,s.expire_statue,c2.code_name as expire_statue_name\n" +
            "from tenant_course_rp tcr \n" +
            " join course c on c.course_id = tcr.course_id \n" +
            " join tenant t on tcr.tenant_id = t.tenant_id \n" +
            "left join serve s on tcr.service_id = s.service_id \n" +
            "left join code c1 on c1.code = c.course_type \n" +
            "left join code c2 on c2.code = s.expire_statue \n" +
            " where tcr.state = 1 and c1.code_type = 'courseType' and c2.code_type = 'expireStatue'";

    /**
     * @param query
     * @param courseType
     * @param courseId
     * @param tenantId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public PageMaker pageCourseRelation(String query, Integer courseType, Long courseId, Long tenantId, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(SQL_LIST_COURSE);
        if (StringTools.isNotEmpty(query)) {
            buffer.append(" and ( c.course_name like :query or t.tenant_name like :query )  ");
            map.put("query", iSqlHelp.like(query));
        }
        if (StringTools.isNotEmpty(courseId)) {
            buffer.append(" and c.course_id = :courseId  ");
            map.put("courseId", courseId);
        }
        if (StringTools.isNotEmpty(courseType)) {
            buffer.append(" and c.course_type =:courseType ");
            map.put("courseType", courseType);
        }
        if (StringTools.isNotEmpty(tenantId)) {
            buffer.append(" and tcr.tenant_id =:tenantId ");
            map.put("tenantId", tenantId);
        }
        return this.queryPageList(buffer.toString(), pageIndex, pageSize, map);
    }

    String LIST_COURSE_SQL = "select * from course where state = 1";

    public List<Course> listCourseAll() {
        return queryForList(LIST_COURSE_SQL, null, Course.class);
    }
}
