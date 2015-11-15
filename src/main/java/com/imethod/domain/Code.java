package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.HashMap;
import java.util.Map;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Code {
    private Integer codeId;
    private String codeType;
    private Integer levelType;
    private Integer code;
    private String codeName;
    private Integer parentId;

    private Integer state;
    //private String codeEnName;

    //private Map<Integer,Code> codeMap = new HashMap<>();

    @Id
    @Column(name = "code_id", nullable = false, insertable = true, updatable = true)
    public Integer getCodeId() {
        return codeId;
    }

    public void setCodeId(Integer codeId) {
        this.codeId = codeId;
    }

    @Basic
    @Column(name = "code_type", nullable = true, insertable = true, updatable = true, length = 20)
    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    @Basic
    @Column(name = "level_type", nullable = true, insertable = true, updatable = true)
    public Integer getLevelType() {
        return levelType;
    }

    public void setLevelType(Integer levelType) {
        this.levelType = levelType;
    }

    @Basic
    @Column(name = "code", nullable = true, insertable = true, updatable = true, length = 20)
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Basic
    @Column(name = "code_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Basic
    @Column(name = "parent_id", nullable = true, insertable = true, updatable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Code code1 = (Code) o;

        if (codeId != null ? !codeId.equals(code1.codeId) : code1.codeId != null) return false;
        if (codeType != null ? !codeType.equals(code1.codeType) : code1.codeType != null) return false;
        if (levelType != null ? !levelType.equals(code1.levelType) : code1.levelType != null) return false;
        if (code != null ? !code.equals(code1.code) : code1.code != null) return false;
        if (codeName != null ? !codeName.equals(code1.codeName) : code1.codeName != null) return false;
        if (parentId != null ? !parentId.equals(code1.parentId) : code1.parentId != null) return false;
        if (state != null ? !state.equals(code1.state) : code1.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codeId != null ? codeId.hashCode() : 0;
        result = 31 * result + (codeType != null ? codeType.hashCode() : 0);
        result = 31 * result + (levelType != null ? levelType.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }


}
