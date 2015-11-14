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
public class Seller {
    private Integer sellerId;
    private Integer userId;
    private Integer orgId;
    private Integer contentId;
    private Integer contentType;
    private Integer sellerLevel;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

    @Id
    @Column(name = "seller_id", nullable = false, insertable = true, updatable = true)
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "seller_level", nullable = true, insertable = true, updatable = true)
    public Integer getSellerLevel() {
        return sellerLevel;
    }

    public void setSellerLevel(Integer sellerLevel) {
        this.sellerLevel = sellerLevel;
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

        Seller seller = (Seller) o;

        if (sellerId != null ? !sellerId.equals(seller.sellerId) : seller.sellerId != null) return false;
        if (userId != null ? !userId.equals(seller.userId) : seller.userId != null) return false;
        if (orgId != null ? !orgId.equals(seller.orgId) : seller.orgId != null) return false;
        if (contentId != null ? !contentId.equals(seller.contentId) : seller.contentId != null) return false;
        if (contentType != null ? !contentType.equals(seller.contentType) : seller.contentType != null) return false;
        if (sellerLevel != null ? !sellerLevel.equals(seller.sellerLevel) : seller.sellerLevel != null) return false;
        if (state != null ? !state.equals(seller.state) : seller.state != null) return false;
        if (createrId != null ? !createrId.equals(seller.createrId) : seller.createrId != null) return false;
        if (createAt != null ? !createAt.equals(seller.createAt) : seller.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(seller.updaterId) : seller.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(seller.updateAt) : seller.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sellerId != null ? sellerId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (contentId != null ? contentId.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (sellerLevel != null ? sellerLevel.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
