package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by bcaring on 15/11/30.
 */
@Entity
public class Spoc extends BasicEntity {
    private static final long serialVersionUID = 1196936478121900890L;
    private Integer spocId;
    private Integer tenantId;
    private Integer spocState;
    private Integer execUserId;

    @Id
    @Column(name = "spoc_id")
    public Integer getSpocId() {
        return spocId;
    }

    public void setSpocId(Integer spocId) {
        this.spocId = spocId;
    }

    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "spoc_state")
    public Integer getSpocState() {
        return spocState;
    }

    public void setSpocState(Integer spocState) {
        this.spocState = spocState;
    }

    @Basic
    @Column(name = "exec_user_id")
    public Integer getExecUserId() {
        return execUserId;
    }

    public void setExecUserId(Integer execUserId) {
        this.execUserId = execUserId;
    }
}
