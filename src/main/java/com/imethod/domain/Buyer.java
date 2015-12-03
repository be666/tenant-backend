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
public class Buyer extends BasicEntity {
    private static final long serialVersionUID = 567719201542677782L;
    private Integer buyerId;
    private Integer userId;
    private Integer orgId;
    private Integer contentId;
    private String contentType;
    private Integer buyerLevel;
    private Integer state;

    @Id
    @Column(name = "buyer_id", nullable = false, insertable = true, updatable = true)
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
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
    @Column(name = "org_id", nullable = true, insertable = true, updatable = true)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "content_id", nullable = true, insertable = true, updatable = true)
    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "content_type", nullable = true, insertable = true, updatable = true)
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "buyer_level", nullable = true, insertable = true, updatable = true)
    public Integer getBuyerLevel() {
        return buyerLevel;
    }

    public void setBuyerLevel(Integer buyerLevel) {
        this.buyerLevel = buyerLevel;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}
