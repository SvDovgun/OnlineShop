package com.luxoft.shop;

import com.luxoft.shop.dao.jdbc.JdbcProductDao;
import com.luxoft.shop.service.ProductService;
import com.luxoft.shop.web.servlets.AddProductServlet;
import com.luxoft.shop.web.servlets.EditProductServlet;
import com.luxoft.shop.web.servlets.ShowAllReviewsRequestServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        //config

        //dao
        JdbcProductDao jdbcProductDao = new JdbcProductDao();

        //service
        ProductService productService = new ProductService(jdbcProductDao);

        //servlet
        ShowAllReviewsRequestServlet showAllReviewsRequestServlet = new ShowAllReviewsRequestServlet(productService);
        AddProductServlet addProductServlet = new AddProductServlet(productService);
        EditProductServlet editProductServlet = new EditProductServlet(productService);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(showAllReviewsRequestServlet), "/products");
        context.addServlet(new ServletHolder(addProductServlet), "/products/add");
        context.addServlet(new ServletHolder(editProductServlet), "/products/edit");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();


    }
}
