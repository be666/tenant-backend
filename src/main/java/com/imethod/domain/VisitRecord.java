package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
@Entity
@Table(name = "visit_record")
public class VisitRecord extends BasicEntity {
    private static final long serialVersionUID = -3850045348989223747L;
    private Integer visitRecordId;
    private Integer userId;
    private String visitUrl;

    @Id
    @Column(name = "visit_record_id", nullable = false)
    public Integer getVisitRecordId() {
        return visitRecordId;
    }

    public void setVisitRecordId(Integer visitRecordId) {
        this.visitRecordId = visitRecordId;
    }

    @Basic
    @Column(name = "visit_url", nullable = true)
    public String getVisitUrl() {
        return visitUrl;
    }

    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
