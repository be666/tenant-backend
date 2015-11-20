package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * time : 15/11/19.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
@javax.persistence.Table(name = "class", schema = "", catalog = "tenant")
public class Classes {
    private Long classId;

    @Id
    @javax.persistence.Column(name = "class_id", nullable = false, insertable = true, updatable = true)
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    private String className;

    @Basic
    @javax.persistence.Column(name = "class_name", nullable = true, insertable = true, updatable = true, length = 300)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private Integer courseId;

    @Basic
    @javax.persistence.Column(name = "course_id", nullable = true, insertable = true, updatable = true)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    private Integer tenantId;

    @Basic
    @javax.persistence.Column(name = "tenant_id", nullable = true, insertable = true, updatable = true)
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    private Integer templateCourse;

    @Basic
    @javax.persistence.Column(name = "template_course", nullable = true, insertable = true, updatable = true)
    public Integer getTemplateCourse() {
        return templateCourse;
    }

    public void setTemplateCourse(Integer templateCourse) {
        this.templateCourse = templateCourse;
    }

    private Integer serviceId;

    @Basic
    @javax.persistence.Column(name = "service_id", nullable = true, insertable = true, updatable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    private Integer state;

    @Basic
    @javax.persistence.Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    private Timestamp classStartTime;

    @Basic
    @javax.persistence.Column(name = "class_start_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Timestamp classStartTime) {
        this.classStartTime = classStartTime;
    }

    private Timestamp classEndTime;

    @Basic
    @javax.persistence.Column(name = "class_end_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Timestamp classEndTime) {
        this.classEndTime = classEndTime;
    }

    private Integer isWeight;

    @Basic
    @javax.persistence.Column(name = "is_weight", nullable = true, insertable = true, updatable = true)
    public Integer getIsWeight() {
        return isWeight;
    }

    public void setIsWeight(Integer isWeight) {
        this.isWeight = isWeight;
    }

    private Integer videoWeight;

    @Basic
    @javax.persistence.Column(name = "video_weight", nullable = true, insertable = true, updatable = true)
    public Integer getVideoWeight() {
        return videoWeight;
    }

    public void setVideoWeight(Integer videoWeight) {
        this.videoWeight = videoWeight;
    }

    private Integer topicWeight;

    @Basic
    @javax.persistence.Column(name = "topic_weight", nullable = true, insertable = true, updatable = true)
    public Integer getTopicWeight() {
        return topicWeight;
    }

    public void setTopicWeight(Integer topicWeight) {
        this.topicWeight = topicWeight;
    }

    private Integer quizWeight;

    @Basic
    @javax.persistence.Column(name = "quiz_weight", nullable = true, insertable = true, updatable = true)
    public Integer getQuizWeight() {
        return quizWeight;
    }

    public void setQuizWeight(Integer quizWeight) {
        this.quizWeight = quizWeight;
    }

    private Integer assignmentWeight;

    @Basic
    @javax.persistence.Column(name = "assignment_weight", nullable = true, insertable = true, updatable = true)
    public Integer getAssignmentWeight() {
        return assignmentWeight;
    }

    public void setAssignmentWeight(Integer assignmentWeight) {
        this.assignmentWeight = assignmentWeight;
    }

    private Integer examWeight;

    @Basic
    @javax.persistence.Column(name = "exam_weight", nullable = true, insertable = true, updatable = true)
    public Integer getExamWeight() {
        return examWeight;
    }

    public void setExamWeight(Integer examWeight) {
        this.examWeight = examWeight;
    }

    private Integer createrId;

    @Basic
    @javax.persistence.Column(name = "creater_id", nullable = true, insertable = true, updatable = true)
    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    private Integer updaterId;

    @Basic
    @javax.persistence.Column(name = "updater_id", nullable = true, insertable = true, updatable = true)
    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    private Timestamp createTime;

    @Basic
    @javax.persistence.Column(name = "create_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    private Timestamp updateTime;

    @Basic
    @javax.persistence.Column(name = "update_time", nullable = true, insertable = true, updatable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Classes classes = (Classes) o;

        if (classId != null ? !classId.equals(classes.classId) : classes.classId != null) return false;
        if (className != null ? !className.equals(classes.className) : classes.className != null) return false;
        if (courseId != null ? !courseId.equals(classes.courseId) : classes.courseId != null) return false;
        if (tenantId != null ? !tenantId.equals(classes.tenantId) : classes.tenantId != null) return false;
        if (templateCourse != null ? !templateCourse.equals(classes.templateCourse) : classes.templateCourse != null)
            return false;
        if (serviceId != null ? !serviceId.equals(classes.serviceId) : classes.serviceId != null) return false;
        if (state != null ? !state.equals(classes.state) : classes.state != null) return false;
        if (classStartTime != null ? !classStartTime.equals(classes.classStartTime) : classes.classStartTime != null)
            return false;
        if (classEndTime != null ? !classEndTime.equals(classes.classEndTime) : classes.classEndTime != null)
            return false;
        if (isWeight != null ? !isWeight.equals(classes.isWeight) : classes.isWeight != null) return false;
        if (videoWeight != null ? !videoWeight.equals(classes.videoWeight) : classes.videoWeight != null) return false;
        if (topicWeight != null ? !topicWeight.equals(classes.topicWeight) : classes.topicWeight != null) return false;
        if (quizWeight != null ? !quizWeight.equals(classes.quizWeight) : classes.quizWeight != null) return false;
        if (assignmentWeight != null ? !assignmentWeight.equals(classes.assignmentWeight) : classes.assignmentWeight != null)
            return false;
        if (examWeight != null ? !examWeight.equals(classes.examWeight) : classes.examWeight != null) return false;
        if (createrId != null ? !createrId.equals(classes.createrId) : classes.createrId != null) return false;
        if (updaterId != null ? !updaterId.equals(classes.updaterId) : classes.updaterId != null) return false;
        if (createTime != null ? !createTime.equals(classes.createTime) : classes.createTime != null) return false;
        if (updateTime != null ? !updateTime.equals(classes.updateTime) : classes.updateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classId != null ? classId.hashCode() : 0;
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (templateCourse != null ? templateCourse.hashCode() : 0);
        result = 31 * result + (serviceId != null ? serviceId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (classStartTime != null ? classStartTime.hashCode() : 0);
        result = 31 * result + (classEndTime != null ? classEndTime.hashCode() : 0);
        result = 31 * result + (isWeight != null ? isWeight.hashCode() : 0);
        result = 31 * result + (videoWeight != null ? videoWeight.hashCode() : 0);
        result = 31 * result + (topicWeight != null ? topicWeight.hashCode() : 0);
        result = 31 * result + (quizWeight != null ? quizWeight.hashCode() : 0);
        result = 31 * result + (assignmentWeight != null ? assignmentWeight.hashCode() : 0);
        result = 31 * result + (examWeight != null ? examWeight.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        return result;
    }
}
