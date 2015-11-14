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
public class Service {
    private Integer serviceId;
    private Date startTime;
    private Date endTime;
    private Integer serviceType;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

    @Id
    @Column(name = "service_id", nullable = false, insertable = true, updatable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "start_time", nullable = true, insertable = true, updatable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true, insertable = true, updatable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "service_type", nullable = true, insertable = true, updatable = true)
    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
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

        Service service = (Service) o;

        if (serviceId != null ? !serviceId.equals(service.serviceId) : service.serviceId != null) return false;
        if (startTime != null ? !startTime.equals(service.startTime) : service.startTime != null) return false;
        if (endTime != null ? !endTime.equals(service.endTime) : service.endTime != null) return false;
        if (serviceType != null ? !serviceType.equals(service.serviceType) : service.serviceType != null) return false;
        if (state != null ? !state.equals(service.state) : service.state != null) return false;
        if (createrId != null ? !createrId.equals(service.createrId) : service.createrId != null) return false;
        if (createAt != null ? !createAt.equals(service.createAt) : service.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(service.updaterId) : service.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(service.updateAt) : service.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceId != null ? serviceId.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
