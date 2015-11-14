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

    String SQL_LIST_COURSE =  "select * from course ";
    String SQL_LIST_COURSE_LIKE =  "select * from course where course_name like :courseName ";

    public PageMaker listCourse(String query, Long pageIndex, Long pageSize) {
        Map<String,Object> map =  new HashMap<>();
        String sql = SQL_LIST_COURSE;
        if(StringTools.isNotEmpty(query)){
            sql =SQL_LIST_COURSE_LIKE;
            map.put("courseName",iSqlHelp.like(query));
        }
        PageMaker page = this.queryPageList(sql,pageIndex,pageSize,map);
        return page;
    }
}
