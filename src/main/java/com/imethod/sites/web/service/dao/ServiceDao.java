package com.imethod.sites.web.service.dao;

import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Service;
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
public class ServiceDao extends IJdbcTempBaseDao {

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


    public Service loadById(Integer serviceId){
        Map<String,Object> map =  new HashMap<>();
        map.put("service_id",serviceId);
        Service service = null;
        try {
            service = load(Service.class,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    String sql = "select * from service where state = 1 ";
    public List<Service> listServiceByType(String serviceType,Integer expireStatus){
        StringBuffer sb = new StringBuffer(200);
        sb.append(sql);
        Map<String,Object> map =  new HashMap<>();
        if(StringTools.isNotEmpty(serviceType)){
            sb.append(" and service_type = :serviceType ");
            map.put("serviceType",serviceType);
        }
        if(StringTools.isNotEmpty(expireStatus)){
            sb.append(" and expire_status = :expireStatus ");
            map.put("expireStatus",expireStatus);
        }
        return queryForList(sb.toString(),map,Service.class);
    }


}
