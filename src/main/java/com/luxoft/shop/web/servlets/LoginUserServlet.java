//package com.luxoft.shop.web.servlets;
//
//import com.luxoft.shop.entity.User;
//import com.luxoft.shop.service.SecurityService;
//import com.luxoft.shop.service.UserService;
//import com.luxoft.shop.web.util.PageGenerator;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class LoginUserServlet extends HttpServlet {
//    private UserService userService;
//
//    private SecurityService securityService;
//
//    public LoginUserServlet(SecurityService securityService) {
//        this.securityService = securityService;
//    }
//
//    public LoginUserServlet(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        PageGenerator pageGenerator = PageGenerator.instance();
//
//        String page = pageGenerator.getPage("login.html");
//
//        resp.getWriter().write(page);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String token;
//        PageGenerator pageGenerator = PageGenerator.instance();
//        User user = getUserFromRequest(req);
//
//        try {
//            token = securityService.login(user);
//            if (token.equals(null)) {
//                throw new Exception();
//            }
//            Cookie cookie = new Cookie("user-token", token);
//            resp.addCookie(cookie);
//            resp.sendRedirect("/products");
//        } catch (Exception e) {
//            String errorMessage = "Invalid user name or password! Please, enter correct data in the fields and try again";
//            System.out.println(errorMessage);
//            e.printStackTrace();
//            String page = pageGenerator.getPageWithMessage("login.html", errorMessage);
//            resp.getWriter().write(page);
//        }
//
//    }
//
//    private User getUserFromRequest(HttpServletRequest req) {
//        //System.out.println(req.getParameter("name"));
//        //System.out.println(req.getParameter("password"));
//
//        return User.builder()
//                .name(req.getParameter("name"))
//                .password(req.getParameter("password"))
//                .build();
//    }
//}
