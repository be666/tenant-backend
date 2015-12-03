package com.imethod.sites.web.serve.dao;

import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Serve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
public class ServeDao extends IJdbcTempBaseDao {

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


    public Serve loadById(Integer serviceId) {
        Map<String, Object> map = new HashMap<>();
        map.put("service_id", serviceId);
        Serve service = null;
        try {
            service = load(Serve.class, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    String sql = "select * from serve where state = 1 ";

    public List<Serve> listServiceByType(String serviceType, Integer expireStatus) {
        StringBuffer sb = new StringBuffer(200);
        sb.append(sql);
        Map<String, Object> map = new HashMap<>();
        if (StringTools.isNotEmpty(serviceType)) {
            sb.append(" and context_type = :serviceType ");
            map.put("serviceType", serviceType);
        }
        if (StringTools.isNotEmpty(expireStatus)) {
            sb.append(" and expire_status = :expireStatus ");
            map.put("expireStatus", expireStatus);
        }
        return queryForList(sb.toString(), map, Serve.class);
    }


    public List<Serve> getServe(String contextId, String contextType) {
        StringBuffer sb = new StringBuffer(200);
        sb.append("select * from serve where context_id=:contextId and context_type=:contextType");
        Map<String, Object> map = new HashMap<>();
        map.put("contextId", contextId);
        map.put("contextType", contextType);
        return queryForList(sb.toString(), map, Serve.class);
    }
}
