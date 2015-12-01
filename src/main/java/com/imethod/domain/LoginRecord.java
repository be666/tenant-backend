package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bcaring on 15/12/1.
 */
@Entity
@Table(name = "login_record")
public class LoginRecord extends BasicEntity {
    private Integer loginId;
    private Integer userId;
    private String ip;

    @Id
    @Column(name = "login_id", nullable = false)
    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ip", nullable = true, length = 45)
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
