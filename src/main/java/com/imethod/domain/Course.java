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
public class Course {
    private Integer courseId;
    private String courseName;
    private Integer tenantId;
    private Integer state;
    private String createId;
    private String createAt;
    private String updateId;
    private String updateAt;

    @Id
    @Column(name = "course_id", nullable = false, insertable = true, updatable = true)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "course_name", nullable = true, insertable = true, updatable = true, length = 45)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
    @Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
