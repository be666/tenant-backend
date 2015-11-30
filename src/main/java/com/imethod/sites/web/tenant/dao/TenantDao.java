package com.imethod.sites.web.tenant.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class TenantDao extends IJdbcTempBaseDao {

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


    public Tenant loadById(Integer tenantId) {
        Map<String, Object> map = new HashMap<>();
        map.put("tenant_id", tenantId);
        Tenant tenant = null;
        try {
            tenant = load(Tenant.class, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return tenant;
    }

    String SQL_LIST_TENANT = " select t.tenant_id,t.tenant_name ,ifnull(tcr.course_count,0) as course_count," +
            "           ifnull(c.class_count,0) as class_count,s.start_time,s.end_time,\n" +
            "            s.expire_status ,t.current_status,c1.code_name as current_status_name ,t.service_type,c2.code_name as service_type_name  \n" +
            "            from tenant t \n" +
            "            left join (select tenant_id,count(1) as course_count from tenant_course_rp where state = 1  group by tenant_id) tcr on tcr.tenant_id = t.tenant_id \n" +
            "            left join serve s on s.service_id = t.service_id\n" +
            "            left join code c1 on c1.code = t.current_status and c1.code_type = 'currentStatus'\n" +
            "            left join code c2 on c2.code = t.service_type and c2.code_type = 'serviceType'\n" +
            "            left join (select tenant_id,count(1) as class_count from classes where state = 1 group by tenant_id ) c on c.tenant_id = t.tenant_id \n" +
            "            where t.state = 1 ";

    public PageMaker pageTenant(String query, Integer currentStatus, Integer serviceType, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(200);
        sb.append(SQL_LIST_TENANT);
        if (StringTools.isNotEmpty(query)) {
            sb.append(" and t.tenant_name like :tenantName ");
            map.put("tenantName", iSqlHelp.like(query));
        }
        if (StringTools.isNotEmpty(currentStatus)) {
            sb.append(" and t.current_status =:currentStatus ");
            map.put("currentStatus", currentStatus);
        }
        if (StringTools.isNotEmpty(serviceType)) {
            sb.append(" and  t.service_type =:serviceType ");
            map.put("serviceType", serviceType);
        }
        return this.queryPageList(sb.toString(), pageIndex, pageSize, map);
    }

    String COUNT_SQL = " select count(1) from tenant where state = 1  ";

    public int countTenant(Integer currentStatus) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer sb = new StringBuffer(200);
        sb.append(COUNT_SQL);
        if (StringTools.isNotEmpty(currentStatus)) {
            sb.append(" and  current_status =:currentStatus ");
            map.put("currentStatus", currentStatus);
        }
        return this.queryForInt(sb.toString(), map);
    }

    String LIST_TENANT = " select * from tenant where state = 1 ";

    public List<Tenant> listTenantAll() {
        return queryForList(LIST_TENANT, null, Tenant.class);
    }
}
