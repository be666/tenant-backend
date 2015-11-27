package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Org {
    private Integer orgId;
    private String orgName;
    private String orgType;
    private String orgCode;
    private Integer orgPid;
    private Integer schoolType;
    private String city;
    private String province;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

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
    @Column(name = "org_type", nullable = true, insertable = true, updatable = true, length = 50)
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Basic
    @Column(name = "org_code", nullable = true, insertable = true, updatable = true, length = 50)
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

    @Basic
    @Column(name = "creater_id", nullable = true, insertable = true, updatable = true)
    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    @Basic
    @Column(name = "create_at", nullable = true, insertable = true, updatable = true)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "updater_id", nullable = true, insertable = true, updatable = true)
    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    @Basic
    @Column(name = "update_at", nullable = true, insertable = true, updatable = true)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Org org = (Org) o;

        if (orgId != null ? !orgId.equals(org.orgId) : org.orgId != null) return false;
        if (orgName != null ? !orgName.equals(org.orgName) : org.orgName != null) return false;
        if (orgType != null ? !orgType.equals(org.orgType) : org.orgType != null) return false;
        if (orgPid != null ? !orgPid.equals(org.orgPid) : org.orgPid != null) return false;
        if (schoolType != null ? !schoolType.equals(org.schoolType) : org.schoolType != null) return false;
        if (city != null ? !city.equals(org.city) : org.city != null) return false;
        if (province != null ? !province.equals(org.province) : org.province != null) return false;
        if (state != null ? !state.equals(org.state) : org.state != null) return false;
        if (createrId != null ? !createrId.equals(org.createrId) : org.createrId != null) return false;
        if (createAt != null ? !createAt.equals(org.createAt) : org.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(org.updaterId) : org.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(org.updateAt) : org.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgId != null ? orgId.hashCode() : 0;
        result = 31 * result + (orgName != null ? orgName.hashCode() : 0);
        result = 31 * result + (orgType != null ? orgType.hashCode() : 0);
        result = 31 * result + (orgPid != null ? orgPid.hashCode() : 0);
        result = 31 * result + (schoolType != null ? schoolType.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
