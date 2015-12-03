package com.imethod.domain;

import com.imethod.domain.base.BasicEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
@Entity
@Table(name = "os_ticket")
public class OsTicket extends BasicEntity {
    private static final long serialVersionUID = 7570379425280710740L;
    private Integer ticketId;
    private Integer userId;
    private String ticketInfo;
    private Integer state;

    @Id
    @Column(name = "ticket_id", nullable = false)
    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "ticket_info", nullable = true, length = 45)
    public String getTicketInfo() {
        return ticketInfo;
    }

    public void setTicketInfo(String ticketInfo) {
        this.ticketInfo = ticketInfo;
    }

    @Basic
    @Column(name = "user_id", nullable = true, length = 45)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "state", nullable = true, length = 45)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
