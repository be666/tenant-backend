package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * time : 15/11/20.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
@javax.persistence.Table(name = "class", schema = "", catalog = "tenant")
public class Classes {
    private Integer classId;

    @Id
    @javax.persistence.Column(name = "class_id", nullable = false, insertable = true, updatable = true)
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
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

    private Integer finishStatus;

    @Basic
    @javax.persistence.Column(name = "finish_status", nullable = true, insertable = true, updatable = true)
    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    private Date classStartTime;

    @Basic
    @javax.persistence.Column(name = "class_start_time", nullable = true, insertable = true, updatable = true)
    public Date getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Date classStartTime) {
        this.classStartTime = classStartTime;
    }

    private Date classEndTime;

    @Basic
    @javax.persistence.Column(name = "class_end_time", nullable = true, insertable = true, updatable = true)
    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
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

    private Date topicWeight;

    @Basic
    @javax.persistence.Column(name = "topic_weight", nullable = true, insertable = true, updatable = true)
    public Date getTopicWeight() {
        return topicWeight;
    }

    public void setTopicWeight(Date topicWeight) {
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

    private Date createAt;

    @Basic
    @javax.persistence.Column(name = "create_at", nullable = true, insertable = true, updatable = true)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private Date updateAt;

    @Basic
    @javax.persistence.Column(name = "update_at", nullable = true, insertable = true, updatable = true)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
