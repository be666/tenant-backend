package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * time : 15/11/13.
 * auth :
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Org {
    private Integer orgId;
    private String orgName;
    private String orgType;
    private String orgPid;
    private String state;
    private String createId;
    private String createAt;
    private String updateId;
    private String updateAt;

    @Id
    @Column(name = "org_id", nullable = false, insertable = true, updatable = true)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId( Integer  orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "org_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Basic
    @Column(name = "org_type", nullable = true, insertable = true, updatable = true, length = 45)
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    @Basic
    @Column(name = "org_pid", nullable = true, insertable = true, updatable = true, length = 45)
    public String getOrgPid() {
        return orgPid;
    }

    public void setOrgPid(String orgPid) {
        this.orgPid = orgPid;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true, length = 45)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Basic
    @Column(name = "create_id", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    @Basic
    @Column(name = "create_at", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "update_id", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    @Basic
    @Column(name = "update_at", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }


}
