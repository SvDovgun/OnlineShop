package com.luxoft.shop.web.servlets;

import com.luxoft.shop.entity.User;
import com.luxoft.shop.service.UserService;
import com.luxoft.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginUserServlet extends HttpServlet {
    private UserService userService;

    public LoginUserServlet(UserService userService) {
        this.userService = userService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("login.html");

        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = getUserFromRequest(req);
            userService.checkUserOnValidation(user);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "Invalid user name of password! Please, enter correct data in the fields and try again";
            System.out.println(errorMessage);
            e.printStackTrace();
        }

    }

    private User getUserFromRequest(HttpServletRequest req) {
        System.out.println(req.getParameter("userName"));
        System.out.println(req.getParameter("password"));

        return User.builder()
                .userName(req.getParameter("userName"))
                .password(req.getParameter("password"))
                .build();
    }
}