package com.luxoft.shop.web.servlets;

import com.luxoft.shop.entity.User;
import com.luxoft.shop.service.UserService;
import com.luxoft.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterUserServlet extends HttpServlet {
    private UserService userService;

    public RegisterUserServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("register.html");

        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = getUserFromRequest(req);
            userService.add(user);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "User has not been added! Please, enter correct data in the fields and try again";
            System.out.println(errorMessage);
            e.printStackTrace();
        }

    }

    private User getUserFromRequest(HttpServletRequest req) {
        System.out.println(req.getParameter("userName"));
        System.out.println(req.getParameter("email"));
        System.out.println(req.getParameter("password"));

        return User.builder()
                .userName(req.getParameter("userName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();
    }
}
