package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * time : 15/11/14.
 * auth : iMethod
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Tenant extends BasicEntity {
    private static final long serialVersionUID = 8294706492117003543L;
    private Integer tenantId;
    private String tenantName;
    private Integer orgId;
    private String orgName;
    private Integer platformTenantId;
    private Integer currentStatus;
    private Integer serviceType;
    private Integer courseNum;
    private String domain;
    private Integer state;

    @Id
    @Column(name = "tenant_id", nullable = false, insertable = true, updatable = true)
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "tenant_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
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
    @Column(name = "platform_tenant_id", nullable = true, insertable = true, updatable = true)
    public Integer getPlatformTenantId() {
        return platformTenantId;
    }

    public void setPlatformTenantId(Integer platformTenantId) {
        this.platformTenantId = platformTenantId;
    }

    @Basic
    @Column(name = "current_status", nullable = true, insertable = true, updatable = true)
    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
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
    @Column(name = "course_num", nullable = true, insertable = true, updatable = true)
    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }

    @Basic
    @Column(name = "domain", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
    @Column(name = "org_name", nullable = false, insertable = true, updatable = true)
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
