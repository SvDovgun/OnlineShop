package com.luxoft.shop.web.filter;

import com.luxoft.shop.service.SecurityService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecurityFilter implements Filter {

    private final SecurityService securityService;

    private List<String> allowedPage = List.of("/css", "/login", "/register");

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        for (String allowedPageV : allowedPage) {
            if ( requestURI.startsWith(allowedPageV)) {
                chain.doFilter(request, response);
                return;
            }

        }

        String token = securityService.getToken(httpServletRequest);

        if (securityService.isAuthenticated(token)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("/login");
        }

    }



}
