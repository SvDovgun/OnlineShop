package com.luxoft.shop.web.controller;

import com.luxoft.shop.service.SecurityService;
import com.luxoft.shop.web.util.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    protected void loginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in security Controller");
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("login.html");

        resp.getWriter().write(page);
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }
}
