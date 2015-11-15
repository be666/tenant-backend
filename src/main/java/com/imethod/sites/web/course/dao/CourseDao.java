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


    public Course loadById(Integer courseId){
        Map<String,Object> map =  new HashMap<>();
        map.put("course_id",courseId);
        Course course = null;
        try {
            course = load(Course.class,map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
        return course;
    }

    /**
     * query
     * @param query
     * @param courseType
     * @param pageIndex
     * @param pageSize
     * @return
     */

    private static String  SQL_LIST_COURSE =  "select c.*,a.tenantNum from course c join " +
            "(select course_id,count(1) as tenantNum from tenant_course_rp " +
            "where state = 1 group by course_id)\n" +
            " a on a.course_id = c.course_id where c.state = 1  ";

    public PageMaker listCourse(String query,Integer courseType, Long pageIndex, Long pageSize) {
        Map<String,Object> map =  new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(SQL_LIST_COURSE);
        if(StringTools.isNotEmpty(query)){
            buffer.append(" and c.course_name like :courseName  ");
            map.put("courseName",iSqlHelp.like(query));
        }
        if(StringTools.isNotEmpty(courseType)){
            buffer.append(" and c.course_type =:courseType ");
            map.put("courseType",courseType);
        }
        PageMaker page = this.queryPageList(buffer.toString(),pageIndex,pageSize,map);
        return page;
    }
}
