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
public class User {
    private Integer userId;
    private String userName;
    private Integer orgId;
    private Integer state;
    private String cretaId;
    private String createAt;
    private String updateId;
    private String updateAt;

    @Id
    @Column(name = "user_id", nullable = false, insertable = true, updatable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "org_id", nullable = true, insertable = true, updatable = true)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
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
    @Column(name = "creta_id", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCretaId() {
        return cretaId;
    }

    public void setCretaId(String cretaId) {
        this.cretaId = cretaId;
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
