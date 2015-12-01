package com.imethod.sites.web.statistics.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Repository
public class StatisticsDao extends IJdbcTempBaseDao {

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

    private static String STATISTICS_TENANT_SQL = "" +
            "select t.tenant_id,t.tenant_name,t.course_num,ifnull(c.classes_num,0) as classes_num ,sel.user_name,\n" +
            "ifnull(s.user_num,0) as user_num,ifnull(s.active_num,0) as active_num ,ifnull(s.pv,0) as pv,IFNULL(s.uv,0) as uv   from tenant t \n" +
            "join serve se on se.context_id = t.tenant_id and se.service_type = 'tenant' \n" +
            "left join (select tenant_id,count(1) as classes_num from classes where state = 1  group by tenant_id) c on c.tenant_id = t.tenant_id \n" +
            "left join (select * from statistics where type = 'tenant') s on s.context_id = t.tenant_id\n" +
            "left join (select ss.*,u.user_name from seller ss join user u on u.user_id = ss.user_id " +
            "       where ss.content_type = 'tenant') sel on sel.content_id = t.tenant_id  \n" +
            "where t.state = 1    ";
    public PageMaker getTenantStatistics(String query, Date startDate, Date endDate, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(STATISTICS_TENANT_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( t.tenant_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }
        if (startDate!=null) {
            map.put("startDate", startDate);
            sb.append(" and se.start_time >: startDate ");
        }
        if (endDate!=null) {
            map.put("endDate", endDate);
            sb.append(" and se.start_time <: endDate  ");
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);


    }

    public PageMaker getCourseStatistics(String query, Date startDate, Date endDate, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(STATISTICS_TENANT_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( c.course_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }
        if (startDate!=null) {
            map.put("startDate", startDate);
            sb.append(" and se.start_time >: startDate ");
        }
        if (endDate!=null) {
            map.put("endDate", endDate);
            sb.append(" and se.start_time <: endDate  ");
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);
    }

    public PageMaker getCClassStatistics(String query, Date startDate, Date endDate, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(STATISTICS_TENANT_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( c.course_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }
        if (startDate!=null) {
            map.put("startDate", startDate);
            sb.append(" and se.start_time >: startDate ");
        }
        if (endDate!=null) {
            map.put("endDate", endDate);
            sb.append(" and se.start_time <: endDate ");
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);
    }
}
