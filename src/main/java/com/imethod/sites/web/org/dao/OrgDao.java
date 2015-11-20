package com.imethod.sites.web.org.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/20.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Repository
public class OrgDao extends IJdbcTempBaseDao {


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

    String SQL_LIST_USER = "select * from org where state = 1  ";

    public PageMaker listOrg(String query, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(SQL_LIST_USER);
        if (StringTools.isNotEmpty(query)) {
            buffer.append(" and org_name like :query  ");
            map.put("query", iSqlHelp.like(query));
        }
        return this.queryPageList(buffer.toString(), pageIndex, pageSize, map);
    }

}
