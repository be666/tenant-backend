package com.imethod.sites.web.classes.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.ExceptionTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Classes;
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
public class ClassDao extends IJdbcTempBaseDao {

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


    public Classes loadById(Integer classId){
        Map<String,Object> map =  new HashMap<>();
        map.put("class_id",classId);
        Classes classes = null;
        try {
            classes = load(Classes.class,map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
        return classes;
    }


    private static String  SQL_LIST_CLASS =  "select c.class_id,c.class_name,c.course_id,c1.course_name,c.tenant_id,t.tenant_name,c.class_start_time,c.class_end_time,\n" +
            "c.is_weight,s.service_type,s.start_time,s.end_time,c.finish_status, co.code_name\n" +
            "from class c \n" +
            "join tenant t on c.tenant_id = t.tenant_id \n" +
            "join course c1 on c1.course_id = c.course_id \n" +
            "left join service s on s.service_id = c.service_id \n" +
            "left join code co on co.code = c.finish_status and co.code_type = 'finishStatus' \n" +
            "where c.state = 1 and t.state =1 and c1.state = 1 ";

    public PageMaker pageClassRelation(String query,Integer finishStatus,Long courseId,Long tenantId, Long pageIndex, Long pageSize) {
        Map<String,Object> map =  new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(SQL_LIST_CLASS);
        if(StringTools.isNotEmpty(query)){
            buffer.append(" and ( c.class_name like :query or t.tenant_name like :query )  ");
            map.put("query",iSqlHelp.like(query));
        }
        if(StringTools.isNotEmpty(courseId)){
            buffer.append(" and c.course_id = :courseId  ");
            map.put("courseId",courseId);
        }
        if(StringTools.isNotEmpty(finishStatus)){
            buffer.append(" and c.finish_status =:finishStatus ");
            map.put("finishStatus",finishStatus);
        }
        if(StringTools.isNotEmpty(tenantId)){
            buffer.append(" and c.tenant_id =:tenantId ");
            map.put("tenantId",tenantId);
        }
        return this.queryPageList(buffer.toString(), pageIndex, pageSize, map);
    }

}
