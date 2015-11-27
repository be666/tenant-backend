package com.imethod.sites.web.user.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.StringTools;
import com.imethod.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Repository
public class UserDao extends IJdbcTempBaseDao {

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


    public User loadById(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        User user = null;
        try {
            user = load(User.class, map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return user;
    }

    String SQL_LIST_USER = "select * from user where state = 1  ";

    public PageMaker listUser(String query, Long pageIndex, Long pageSize) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(SQL_LIST_USER);
        if (StringTools.isNotEmpty(query)) {
            buffer.append(" and ( user_name like :query or mobile like :query or email like :query) ");
            map.put("query", iSqlHelp.like(query));
        }

        PageMaker page = this.queryPageList(buffer.toString(), pageIndex, pageSize, map);
        return page;
    }
}
