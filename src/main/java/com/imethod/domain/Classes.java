package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * auth : iMethod
 * create_at:  15/11/30.
 * desc:
 * note:
 *  1.
 */
@Entity
public class Classes extends BasicEntity {

    private static final long serialVersionUID = 5535496163127012729L;
    private Integer classId;

    private String className;

    private Integer courseId;

    private Integer tenantId;

    private Integer tcId;

    private Integer templateCourse;

    private Integer state;

    private Integer finishStatus;

    private Date classStartTime;

    private Date classEndTime;

    private Integer isWeight;

    private Integer videoWeight;

    private Integer topicWeight;

    private Integer quizWeight;

    private Integer assignmentWeight;

    private Integer examWeight;

    @Id
    @Column(name = "class_id", nullable = false)
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "class_name", nullable = true, length = 300)
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Basic
    @Column(name = "course_id", nullable = true)
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "tenant_id", nullable = true)
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }


    @Basic
    @Column(name = "tc_id", nullable = true)
    public Integer getTcId() {
        return tcId;
    }

    public void setTcId(Integer tcId) {
        this.tcId = tcId;
    }

    @Basic
    @Column(name = "template_course", nullable = true)
    public Integer getTemplateCourse() {
        return templateCourse;
    }

    public void setTemplateCourse(Integer templateCourse) {
        this.templateCourse = templateCourse;
    }

    @Basic
    @Column(name = "state", nullable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "finish_status", nullable = true)
    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
    }

    @Basic
    @Column(name = "class_start_time", nullable = true)
    public Date getClassStartTime() {
        return classStartTime;
    }

    public void setClassStartTime(Date classStartTime) {
        this.classStartTime = classStartTime;
    }

    @Basic
    @Column(name = "class_end_time", nullable = true)
    public Date getClassEndTime() {
        return classEndTime;
    }

    public void setClassEndTime(Date classEndTime) {
        this.classEndTime = classEndTime;
    }

    @Basic
    @Column(name = "is_weight", nullable = true)
    public Integer getIsWeight() {
        return isWeight;
    }

    public void setIsWeight(Integer isWeight) {
        this.isWeight = isWeight;
    }

    @Basic
    @Column(name = "video_weight", nullable = true)
    public Integer getVideoWeight() {
        return videoWeight;
    }

    public void setVideoWeight(Integer videoWeight) {
        this.videoWeight = videoWeight;
    }

    @Basic
    @Column(name = "topic_weight", nullable = true)
    public Integer getTopicWeight() {
        return topicWeight;
    }

    public void setTopicWeight(Integer topicWeight) {
        this.topicWeight = topicWeight;
    }

    @Basic
    @Column(name = "quiz_weight", nullable = true)
    public Integer getQuizWeight() {
        return quizWeight;
    }

    public void setQuizWeight(Integer quizWeight) {
        this.quizWeight = quizWeight;
    }

    @Basic
    @Column(name = "assignment_weight", nullable = true)
    public Integer getAssignmentWeight() {
        return assignmentWeight;
    }

    public void setAssignmentWeight(Integer assignmentWeight) {
        this.assignmentWeight = assignmentWeight;
    }

    @Basic
    @Column(name = "exam_weight", nullable = true)
    public Integer getExamWeight() {
        return examWeight;
    }

    public void setExamWeight(Integer examWeight) {
        this.examWeight = examWeight;
    }


}
