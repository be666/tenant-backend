package com.imethod.sites.web.auth.dao;

import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
@Repository
public class AuthDao extends IJdbcTempBaseDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private ISqlHelp iSqlHelp;

    public User getLoginUser(String id, String password) {
        StringBuffer sql = new StringBuffer();
        sql.append("select u.* from os_user ou\n" +
                "join user u on u.user_id=ou.user_id\n" +
                "where (u.email=:id or u.user_name=:id) and ou.password=:password");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("password", password);
        List<User> userList = queryForList(sql.toString(), map, User.class);
        if (userList.isEmpty()) {
            return null;
        }
        return userList.get(0);
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
