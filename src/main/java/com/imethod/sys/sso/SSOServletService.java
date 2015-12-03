package com.imethod.sys.sso;

import com.imethod.core.util.IdentitieTools;
import com.imethod.domain.LoginRecord;
import com.imethod.domain.OsTicket;
import com.imethod.sites.web.log.service.LogService;
import com.imethod.sites.web.permission.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * auth : iMethod
 * create_at:  15/12/1.
 * desc:
 * note:
 *  1.
 */
public class SSOServletService implements SSOService {

    private static final String USER_TICKET = "USER_TICKET";

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private LogService logService;

    @Override
    public boolean login(HttpServletRequest request, Integer userId) {
        String user_ticket = IdentitieTools.uuid2();
        request.getSession().setAttribute("USER_TICKET", user_ticket);
        OsTicket osTicket = new OsTicket();
        osTicket.setUserId(userId);
        osTicket.setTicketInfo(user_ticket);
        osTicket.setState(1);
        permissionService.saveTicket(osTicket);
        LoginRecord loginRecord=new LoginRecord();
        loginRecord.setUserId(userId);
        loginRecord.setIp(request.getRemoteAddr());
        logService.insertLogin(loginRecord);
        request.getServletContext().setAttribute("USER_TICKET_" + user_ticket, osTicket);
        return true;
    }

    @Override
    public boolean logout(HttpServletRequest request) {
        Object USER_TICKET = request.getSession().getAttribute("USER_TICKET");
        if (USER_TICKET != null) {
            String user_ticket = (String) USER_TICKET;
            permissionService.deleteTicket(user_ticket);
            request.getServletContext().setAttribute("USER_TICKET_" + user_ticket, null);
            request.getSession().setAttribute("USER_TICKET", null);
        }
        return false;
    }

    @Override
    public Integer getUserId(HttpServletRequest request) {
        Object USER_TICKET = request.getSession().getAttribute("USER_TICKET");
        if (USER_TICKET != null) {
            String user_ticket = (String) USER_TICKET;
            Object osTicket = request.getServletContext().getAttribute("USER_TICKET_" + user_ticket);
            if (osTicket == null) {
                osTicket = permissionService.queryTicket(user_ticket);
            }
            if (osTicket != null) {
                return ((OsTicket) osTicket).getUserId();
            }
        }
        return null;
    }
}
