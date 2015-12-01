package com.imethod.sites.web.alarm.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class AlarmDao extends IJdbcTempBaseDao {

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

    private static String SQL_COURSE_BY_TYPE = "select IFNULL(count(1),0) as count from serve where " +
            "service_type =:serviceType   and state = 1";
    public int countExpire( String serviceType, Integer expireStatus) {
        Map<String,Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        sb.append(SQL_COURSE_BY_TYPE);
        if(!(expireStatus==null || expireStatus == 0)){
            map.put("expireStatus",expireStatus);
            sb.append(" and expire_status =:expireStatus " );
        }
        map.put("serviceType",serviceType);
        return this.queryForInt(sb.toString(), map);
    }

    private static String QUERY_TENANT_SQL = "" +
            "select t.tenant_id,t.tenant_name,t.course_num,s.start_time," +
            "s.end_time,s.expire_status,c.code_name as expire_status_name \n" +
            "from serve s join tenant t  \n" +
            "left join code c on s.expire_status = c.`code` and c.code_type = 'expireStatue' \n" +
            "where s.service_type = 'tenant' and s.state = 1 and t.state = 1 ";
    public PageMaker getTenantAlarm(String query, Integer expireStatus, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(QUERY_TENANT_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and t.tenant_name like :tenantName ");
            map.put("tenantName", iSqlHelp.like(query));
        }
        if(!(expireStatus==null || expireStatus == 0)){
            map.put("expireStatus",expireStatus);
            sb.append(" and s.expire_status =:expireStatus " );
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);

    }

    private static String QUERY_COURSE_SQL = "" +
            "select course.course_id,course.course_name,s.start_time,s.end_time," +
            "s.expire_status,c.code_name as expire_status_name,\n" +
            "course.course_type ,c2.code_name as course_type_name ,t.tenant_name \n" +
            "from serve s join tenant_course_rp tcr on tcr.tc_id = s.context_id  \n" +
            "join course course on tcr.course_id = course.course_id  \n" +
            "join tenant t on t.tenant_id = tcr.tenant_id    \n" +
            "left join code c on s.expire_status = c.`code` and c.code_type = 'expireStatue' \n" +
            "left join code c2 on c2.code = course.course_type and c2.code_type = 'courseType' \n" +
            "where s.service_type = 'tenant' and s.state = 1 and course.state = 1 ";
    public PageMaker getCourseAlarm(String query, Integer expireStatus, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(QUERY_COURSE_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( course.course_name like :query or t.tenant_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }
        if(!(expireStatus==null || expireStatus == 0)){
            map.put("expireStatus",expireStatus);
            sb.append(" and s.expire_status =:expireStatus " );
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);

    }
}
