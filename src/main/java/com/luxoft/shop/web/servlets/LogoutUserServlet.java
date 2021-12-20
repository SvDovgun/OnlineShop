package com.luxoft.shop.web.servlets;

import com.luxoft.shop.service.SecurityService;
import com.luxoft.shop.web.filter.SecurityFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutUserServlet extends HttpServlet {
    private SecurityService securityService;

    public LogoutUserServlet(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String token = securityService.getToken(req);
            Cookie cookie = new Cookie("user-token", token);
            if (securityService.removeToken(token)) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
                resp.sendRedirect("/login");
            }
        }
}
