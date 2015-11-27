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
public class Course {
    private Integer courseId;
    private String courseName;
    private Integer tenantId;
    private Integer courseType;
    private String info;
    private Integer score;
    private Integer videoLength;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;
    private Integer serviceId;

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
    @Column(name = "course_type", nullable = true, insertable = true, updatable = true)
    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    @Basic
    @Column(name = "info", nullable = true, insertable = true, updatable = true, length = 4000)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "score", nullable = true, insertable = true, updatable = true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "video_length", nullable = true, insertable = true, updatable = true)
    public Integer getVideoLength() {
        return videoLength;
    }

    public void setVideoLength(Integer videoLength) {
        this.videoLength = videoLength;
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

        Course course = (Course) o;

        if (courseId != null ? !courseId.equals(course.courseId) : course.courseId != null) return false;
        if (courseName != null ? !courseName.equals(course.courseName) : course.courseName != null) return false;
        if (tenantId != null ? !tenantId.equals(course.tenantId) : course.tenantId != null) return false;
        if (courseType != null ? !courseType.equals(course.courseType) : course.courseType != null) return false;
        if (info != null ? !info.equals(course.info) : course.info != null) return false;
        if (score != null ? !score.equals(course.score) : course.score != null) return false;
        if (videoLength != null ? !videoLength.equals(course.videoLength) : course.videoLength != null) return false;
        if (state != null ? !state.equals(course.state) : course.state != null) return false;
        if (createrId != null ? !createrId.equals(course.createrId) : course.createrId != null) return false;
        if (createAt != null ? !createAt.equals(course.createAt) : course.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(course.updaterId) : course.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(course.updateAt) : course.updateAt != null) return false;

        return true;
    }

}
