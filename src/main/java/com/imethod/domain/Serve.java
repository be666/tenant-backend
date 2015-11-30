package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * time : 15/11/29.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Serve extends BasicEntity {
    private Integer serviceId;
    private Integer orgId;
    private Integer contextId;
    private Date startTime;
    private Date endTime;
    private String serviceType;
    private Integer state;
    private Integer expireStatus;
    private Integer serviceMoney;

    @Id
    @Column(name = "service_id", nullable = false, insertable = true, updatable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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
    @Column(name = "context_id", nullable = true, insertable = true, updatable = true)
    public Integer getContextId() {
        return contextId;
    }

    public void setContextId(Integer contextId) {
        this.contextId = contextId;
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
    @Column(name = "service_money", nullable = true, insertable = true, updatable = true)
    public Integer getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Integer serviceMoney) {
        this.serviceMoney = serviceMoney;
    }
}
