package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * time : 15/11/29.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Serve {
    private Integer serviceId;
    private Integer contextId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String serviceType;
    private Integer state;
    private Integer expireStatus;
    private Integer createrId;
    private Timestamp createAt;
    private Integer updaterId;
    private Timestamp updateAt;

    @Id
    @Column(name = "service_id", nullable = false, insertable = true, updatable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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
    @Column(name = "start_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "service_type", nullable = true, insertable = true, updatable = true, length = 20)
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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
    @Column(name = "expire_status", nullable = true, insertable = true, updatable = true)
    public Integer getExpireStatus() {
        return expireStatus;
    }

    public void setExpireStatus(Integer expireStatus) {
        this.expireStatus = expireStatus;
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
    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
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
    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Serve serve = (Serve) o;

        if (serviceId != null ? !serviceId.equals(serve.serviceId) : serve.serviceId != null) return false;
        if (contextId != null ? !contextId.equals(serve.contextId) : serve.contextId != null) return false;
        if (startTime != null ? !startTime.equals(serve.startTime) : serve.startTime != null) return false;
        if (endTime != null ? !endTime.equals(serve.endTime) : serve.endTime != null) return false;
        if (serviceType != null ? !serviceType.equals(serve.serviceType) : serve.serviceType != null) return false;
        if (state != null ? !state.equals(serve.state) : serve.state != null) return false;
        if (expireStatus != null ? !expireStatus.equals(serve.expireStatus) : serve.expireStatus != null) return false;
        if (createrId != null ? !createrId.equals(serve.createrId) : serve.createrId != null) return false;
        if (createAt != null ? !createAt.equals(serve.createAt) : serve.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(serve.updaterId) : serve.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(serve.updateAt) : serve.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (contextId != null ? contextId.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (expireStatus != null ? expireStatus.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
