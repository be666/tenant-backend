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


    public Tenant loadById(Integer tenantId){
        Map<String,Object> map =  new HashMap<>();
        map.put("tenant_id",tenantId);
        Tenant tenant = null;
        try {
            tenant = load(Tenant.class,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return tenant;
    }

    String SQL_LIST_TENANT =  "select * from tenant ";
    String SQL_LIST_TENANT_LIKE =  "select * from tenant where tenant_name like :tenantName ";

    public PageMaker listTenant(String query, Long pageIndex, Long pageSize) {
        Map<String,Object> map =  new HashMap<>();
        String sql = SQL_LIST_TENANT;
        if(StringTools.isNotEmpty(query)){
            sql =SQL_LIST_TENANT_LIKE;
            map.put("tenantName",iSqlHelp.like(query));
        }
        PageMaker page = this.queryPageList(sql,pageIndex,pageSize,map);
        return page;
    }
}
