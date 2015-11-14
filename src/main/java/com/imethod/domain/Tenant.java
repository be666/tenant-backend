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
public class Tenant {
    private Integer tenantId;
    private String tenantName;
    private Integer orgId;
    private Integer platformTenantId;
    private Integer currentStatus;
    private Integer currentStage;
    private Integer courseNum;
    private String domain;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

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
    @Column(name = "current_stage", nullable = true, insertable = true, updatable = true)
    public Integer getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Integer currentStage) {
        this.currentStage = currentStage;
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

        Tenant tenant = (Tenant) o;

        if (tenantId != null ? !tenantId.equals(tenant.tenantId) : tenant.tenantId != null) return false;
        if (tenantName != null ? !tenantName.equals(tenant.tenantName) : tenant.tenantName != null) return false;
        if (orgId != null ? !orgId.equals(tenant.orgId) : tenant.orgId != null) return false;
        if (platformTenantId != null ? !platformTenantId.equals(tenant.platformTenantId) : tenant.platformTenantId != null)
            return false;
        if (currentStatus != null ? !currentStatus.equals(tenant.currentStatus) : tenant.currentStatus != null)
            return false;
        if (currentStage != null ? !currentStage.equals(tenant.currentStage) : tenant.currentStage != null)
            return false;
        if (courseNum != null ? !courseNum.equals(tenant.courseNum) : tenant.courseNum != null) return false;
        if (domain != null ? !domain.equals(tenant.domain) : tenant.domain != null) return false;
        if (state != null ? !state.equals(tenant.state) : tenant.state != null) return false;
        if (createrId != null ? !createrId.equals(tenant.createrId) : tenant.createrId != null) return false;
        if (createAt != null ? !createAt.equals(tenant.createAt) : tenant.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(tenant.updaterId) : tenant.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(tenant.updateAt) : tenant.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tenantId != null ? tenantId.hashCode() : 0;
        result = 31 * result + (tenantName != null ? tenantName.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (platformTenantId != null ? platformTenantId.hashCode() : 0);
        result = 31 * result + (currentStatus != null ? currentStatus.hashCode() : 0);
        result = 31 * result + (currentStage != null ? currentStage.hashCode() : 0);
        result = 31 * result + (courseNum != null ? courseNum.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
