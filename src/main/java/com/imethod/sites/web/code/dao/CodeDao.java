package com.imethod.sites.web.code.dao;

import com.imethod.core.jdbc.PageMaker;
import com.imethod.core.jdbc.mine.IJdbcTempBaseDao;
import com.imethod.core.jdbc.mine.ISqlHelp;
import com.imethod.core.util.ExceptionTools;
import com.imethod.core.util.StringTools;
import com.imethod.domain.Code;
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
public class CodeDao extends IJdbcTempBaseDao {

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


    public Code loadById(Integer codeId){
        Map<String,Object> map =  new HashMap<>();
        map.put("code_id",codeId);
        Code code = null;
        try {
            code = load(Code.class,map);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            ExceptionTools.unchecked(e);
        }
        return code;
    }

    String SQL_LIST_CODE =  "select * from code where code_type =:codeType and state = 1  ";

    public List<Code> listCodeByType(String codeType,Integer levelType,Integer parentId) {
        Map<String,Object> paramMap =  new HashMap<>();
        paramMap.put("codeType",codeType);
        StringBuffer sb = new StringBuffer();
        sb.append(SQL_LIST_CODE);
        if(StringTools.isNotEmpty(levelType)){
            sb.append(" and level_type =:levelType ");
        }

        if(StringTools.isNotEmpty(parentId)){
            sb.append(" and parent_id =:parentId ");
        }
        List<Code> list = this.queryForList(sb.toString(),paramMap,Code.class);
        return list;
    }
}
