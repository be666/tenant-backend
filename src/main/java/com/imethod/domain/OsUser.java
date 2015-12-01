package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bcaring on 15/12/1.
 */
@Entity
@Table(name = "os_user")
public class OsUser extends BasicEntity {
    private static final long serialVersionUID = 8745717622447652338L;
    private Integer OsUserId;
    private Integer userId;
    private String password;

    @Id
    @Column(name = "os_user_id", nullable = false)
    public Integer getOsUserId() {
        return OsUserId;
    }

    public void setOsUserId(Integer osUserId) {
        OsUserId = osUserId;
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
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
