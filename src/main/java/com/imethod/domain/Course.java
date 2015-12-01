package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Course extends BasicEntity {
    private static final long serialVersionUID = 1520051600335085246L;
    private Integer courseId;
    private String courseName;
    private Integer tenantId;
    private Integer courseType;
    private Integer serviceType;
    private String info;
    private Integer score;
    private Integer videoLength;
    private Integer state;
    private Integer chapterMoney;
    private Integer chapterNum;
    private Integer chapterAll;
    private Integer peopleMoney;
    private Integer peopleNum;
    private Integer peopleAll;

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

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
    @Column(name = "chapter_money", nullable = true)
    public Integer getChapterMoney() {
        return chapterMoney;
    }

    public void setChapterMoney(Integer chapterMoney) {
        this.chapterMoney = chapterMoney;
    }

    @Basic
    @Column(name = "chapter_num", nullable = true)
    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    @Basic
    @Column(name = "chapter_all", nullable = true)
    public Integer getChapterAll() {
        return chapterAll;
    }

    public void setChapterAll(Integer chapterAll) {
        this.chapterAll = chapterAll;
    }

    @Basic
    @Column(name = "people_money", nullable = true)
    public Integer getPeopleMoney() {
        return peopleMoney;
    }

    public void setPeopleMoney(Integer peopleMoney) {
        this.peopleMoney = peopleMoney;
    }

    @Basic
    @Column(name = "people_num", nullable = true)
    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    @Basic
    @Column(name = "people_all", nullable = true)
    public Integer getPeopleAll() {
        return peopleAll;
    }

    public void setPeopleAll(Integer peopleAll) {
        this.peopleAll = peopleAll;
    }

    @Basic
    @Column(name = "service_type", nullable = true)
    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }
}