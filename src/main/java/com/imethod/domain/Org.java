package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * time : 15/11/14.
 * auth : iMethod
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Org extends BasicEntity {
    private static final long serialVersionUID = 1355436566033216395L;
    private Integer orgId;
    private String orgName;
    private String orgType;
    private String orgCode;
    private Integer orgPid;
    private Integer schoolType;
    private String city;
    private String province;
    private Integer state;
    @Id
    @Column(name = "org_id", nullable = false, insertable = true, updatable = true)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "org_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "org_type", nullable = true, insertable = true, updatable = true)
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Basic
    @Column(name = "org_code", nullable = true, insertable = true, updatable = true)
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Basic
    @Column(name = "org_pid", nullable = true, insertable = true, updatable = true)
    public Integer getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(Integer orgPid) {
        this.orgPid = orgPid;
    }

    @Basic
    @Column(name = "school_type", nullable = true, insertable = true, updatable = true)
    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    @Basic
    @Column(name = "city", nullable = true, insertable = true, updatable = true, length = 11)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "province", nullable = true, insertable = true, updatable = true, length = 11)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
