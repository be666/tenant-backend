package com.imethod.sites.web.permission.dao;

import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Repository
public class PermissionDao extends IJdbcTempBaseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ISqlHelp iSqlHelp;

    private static String ruleStr = "SELECT m.* FROM menu m\n" +
            "  join rule r on r.menu_id = m.menu_id\n" +
            "where r.user_id=1 and m.menu_type=1";

    public List<Map<String, Object>> getUserRule(Integer userId, Integer menuType) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("menuType", menuType);
        return queryForList(ruleStr, map);
    }

    @Override
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    @Override
    protected ISqlHelp getISqlHelp() {
        return iSqlHelp;
    }
}
