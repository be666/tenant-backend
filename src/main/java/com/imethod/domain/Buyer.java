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
public class Buyer {
    private Integer buyerId;
    private Integer userId;
    private Integer tenantId;
    private Integer orgId;
    private String buyerLevel;
    private String createId;
    private String createAt;
    private String updateId;
    private String updateAt;

    @Id
    @Column(name = "buyer_id", nullable = false, insertable = true, updatable = true)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    @Basic
    @Column(name = "user_id", nullable = true, insertable = true, updatable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "tenant_id", nullable = true, insertable = true, updatable = true)
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
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
    @Column(name = "buyer_level", nullable = true, insertable = true, updatable = true, length = 45)
    public String getBuyerLevel() {
        return buyerLevel;
    }

    public void setBuyerLevel(String buyerLevel) {
        this.buyerLevel = buyerLevel;
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
