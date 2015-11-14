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
public class Buyer {
    private Integer buyerId;
    private Integer userId;
    private Integer orgId;
    private Integer contentId;
    private Integer contentType;
    private Integer buyerLevle;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

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
    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "buyer_levle", nullable = true, insertable = true, updatable = true)
    public Integer getBuyerLevle() {
        return buyerLevle;
    }

    public void setBuyerLevle(Integer buyerLevle) {
        this.buyerLevle = buyerLevle;
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

        Buyer buyer = (Buyer) o;

        if (buyerId != null ? !buyerId.equals(buyer.buyerId) : buyer.buyerId != null) return false;
        if (userId != null ? !userId.equals(buyer.userId) : buyer.userId != null) return false;
        if (orgId != null ? !orgId.equals(buyer.orgId) : buyer.orgId != null) return false;
        if (contentId != null ? !contentId.equals(buyer.contentId) : buyer.contentId != null) return false;
        if (contentType != null ? !contentType.equals(buyer.contentType) : buyer.contentType != null) return false;
        if (buyerLevle != null ? !buyerLevle.equals(buyer.buyerLevle) : buyer.buyerLevle != null) return false;
        if (state != null ? !state.equals(buyer.state) : buyer.state != null) return false;
        if (createrId != null ? !createrId.equals(buyer.createrId) : buyer.createrId != null) return false;
        if (createAt != null ? !createAt.equals(buyer.createAt) : buyer.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(buyer.updaterId) : buyer.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(buyer.updateAt) : buyer.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = buyerId != null ? buyerId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (contentId != null ? contentId.hashCode() : 0);
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + (buyerLevle != null ? buyerLevle.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }
}
