package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
@Entity
@Table(name = "serve_ticket")
public class ServeTicket extends BasicEntity {
    private static final long serialVersionUID = -7471246590819731651L;
    private int stId;

    private Integer serviceId;

    private Date startTime;

    private Date endTime;

    private Integer serviceMoney;

    @Id
    @Column(name = "st_id", nullable = false)
    public int getStId() {
        return stId;
    }

    public void setStId(int stId) {
        this.stId = stId;
    }

    @Basic
    @Column(name = "service_id", nullable = true)
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Date getEndTime() {
        return endTime;
    }


    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "service_money", nullable = true)
    public Integer getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(Integer serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

}
