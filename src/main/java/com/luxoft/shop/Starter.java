package com.luxoft.shop;

import com.luxoft.shop.dao.jdbc.JdbcProductDao;
import com.luxoft.shop.dao.jdbc.JdbcUserDao;
import com.luxoft.shop.service.ProductService;
import com.luxoft.shop.service.UserService;
import com.luxoft.shop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        //config

        //dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();
    //    JdbcUserDao jdbcUserDao = new JdbcUserDao();

        //service
        ProductService productService = new ProductService(jdbcProductDao);
    //    UserService userService = new UserService(jdbcUserDao);

        //servlet
        ShowAllReviewsRequestServlet showAllReviewsRequestServlet = new ShowAllReviewsRequestServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);
        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);
     //   RegisterUserServlet registerUserServlet = new RegisterUserServlet(userService);
     //   LoginUserServlet loginUserServlet = new LoginUserServlet(userService);


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(showAllReviewsRequestServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        context.addServlet(new ServletHolder(editProductServlet), "/products/edit");
        context.addServlet(new ServletHolder(deleteProductServlet), "/products/delete");
     //   context.addServlet(new ServletHolder(registerUserServlet), "/register");
     //   context.addServlet(new ServletHolder(loginUserServlet), "/login");


        Server server = new Server(8080);
        server.setHandler(context);

        server.start();


    }
}
