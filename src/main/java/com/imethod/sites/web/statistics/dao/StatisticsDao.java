package com.imethod.sites.web.statistics.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
            "join serve se on se.context_id = t.tenant_id and se.context_type = 'Tenant' \n" +
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
    private static  String STATISTICS_COURSE_SQL = "" +
            "select c.course_id,c.course_name,ifnull(tenant_num,0) as tenant_num ,ifnull(cl.class_num,0) as class_num ,\n" +
            "ifnull(s.pv,0) as pv,IFNULL(s.uv,0) as uv  from course c \n" +
            "left join  (select course_id,count(1) as tenant_num from tenant_course_rp tcr where state = 1 group by course_id )tcr \n" +
            "on tcr.course_id = c.course_id \n" +
            "left join (select course_id,count(1) as class_num from classes where state = 1 group by course_id ) cl on cl.course_id = c.course_id \n" +
            "left JOIN (select * from statistics where type = 'course') s on s.context_id = c.course_id where c.state = 1 \n";
    public PageMaker getCourseStatistics(String query, Date startDate, Date endDate, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(STATISTICS_COURSE_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( c.course_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }
//        if (startDate!=null) {
//            map.put("startDate", startDate);
//            sb.append(" and se.start_time >: startDate ");
//        }
//        if (endDate!=null) {
//            map.put("endDate", endDate);
//            sb.append(" and se.start_time <: endDate  ");
//        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);
    }

    private static  String STATISTICS_CLASS_SQL = "" +
            "select c.class_id,c.class_name,co.course_name,t.tenant_name ,c.class_start_time,c.class_end_time,\n" +
            "ifnull(s.pv,0) as pv,IFNULL(s.uv,0) as uv ,ifnull(s.user_num,0) as user_num,IFNULL(s.active_num,0) as active_num \n" +
            "from classes c \n" +
            "join course co on co.course_id = c.course_id \n" +
            "join tenant t on t.tenant_id = c.tenant_id \n" +
            "left JOIN (select * from statistics where type = 'class') s on s.context_id = c.course_id where c.state = 1 ";
    public PageMaker getClassStatistics(String query, Date startDate, Date endDate, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(500);
        sb.append(STATISTICS_CLASS_SQL);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and ( c.class_name like :query ) ");
            map.put("query", iSqlHelp.like(query));
        }

        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);
    }

    String querySql = "select * from statistics where context_id =:contextId and type = :type ";
    public Statistics getByCondition(String type, Integer contextId) {
        Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        map.put("contextId",contextId);
        List<Statistics> list = queryForList(querySql,map,Statistics.class);
        Statistics statistics = null;
        if(list.size()>0){
            statistics = list.get(0);
        }
        return statistics;
    }
}
