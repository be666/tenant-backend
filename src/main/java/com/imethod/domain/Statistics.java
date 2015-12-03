package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * time : 15/11/29.
 * auth : iMethod
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Statistics extends BasicEntity {
    private static final long serialVersionUID = -8727212463383191013L;
    private Integer id;
    private Integer contextId;
    private Date createdAt;
    private String type;
    private Integer createrId;
    private Integer userNum;
    private Integer activeNum;
    private Integer pv;
    private Integer uv;


    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "context_id", nullable = true, insertable = true, updatable = true)
    public Integer getContextId() {
        return contextId;
    }

    public void setContextId(Integer contextId) {
        this.contextId = contextId;
    }
    @Basic
    @Column(name = "created_at", nullable = true, insertable = true, updatable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "type", nullable = true, insertable = true, updatable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "user_num", nullable = true, insertable = true, updatable = true)
    public Integer getUserNum() {
        return userNum;
    }

    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
    @Basic
    @Column(name = "active_num", nullable = true, insertable = true, updatable = true)
    public Integer getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(Integer activeNum) {
        this.activeNum = activeNum;
    }

    @Basic
    @Column(name = "pv", nullable = true, insertable = true, updatable = true)
    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }
    @Basic
    @Column(name = "uv", nullable = true, insertable = true, updatable = true)
    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }
}
