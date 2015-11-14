package com.imethod.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
@Table(name = "log_record")
public class LogRecord {
    private Long logId;
    private Integer userId;
    private Integer actionType;
    private String requestBody;
    private Date time;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateId;

    @Id
    @Column(name = "log_id", nullable = false, insertable = true, updatable = true)
    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
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
    @Column(name = "action_type", nullable = true, insertable = true, updatable = true)
    public Integer getActionType() {
        return actionType;
    }

    public void setActionType(Integer actionType) {
        this.actionType = actionType;
    }

    @Basic
    @Column(name = "request_body", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Basic
    @Column(name = "time", nullable = true, insertable = true, updatable = true)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
    @Column(name = "update_id", nullable = true, insertable = true, updatable = true)
    public Date getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Date updateId) {
        this.updateId = updateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogRecord logRecord = (LogRecord) o;

        if (logId != null ? !logId.equals(logRecord.logId) : logRecord.logId != null) return false;
        if (userId != null ? !userId.equals(logRecord.userId) : logRecord.userId != null) return false;
        if (actionType != null ? !actionType.equals(logRecord.actionType) : logRecord.actionType != null) return false;
        if (requestBody != null ? !requestBody.equals(logRecord.requestBody) : logRecord.requestBody != null)
            return false;
        if (time != null ? !time.equals(logRecord.time) : logRecord.time != null) return false;
        if (state != null ? !state.equals(logRecord.state) : logRecord.state != null) return false;
        if (createrId != null ? !createrId.equals(logRecord.createrId) : logRecord.createrId != null) return false;
        if (createAt != null ? !createAt.equals(logRecord.createAt) : logRecord.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(logRecord.updaterId) : logRecord.updaterId != null) return false;
        if (updateId != null ? !updateId.equals(logRecord.updateId) : logRecord.updateId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (actionType != null ? actionType.hashCode() : 0);
        result = 31 * result + (requestBody != null ? requestBody.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateId != null ? updateId.hashCode() : 0);
        return result;
    }
}
