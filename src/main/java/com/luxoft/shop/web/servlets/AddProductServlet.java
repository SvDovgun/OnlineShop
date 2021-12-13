package com.luxoft.shop.web.servlets;

import com.luxoft.shop.entity.Product;
import com.luxoft.shop.service.ProductService;
import com.luxoft.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {
    private ProductService productService;


    public AddProductServlet(ProductService productService) {
        this.productService = productService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        String page = pageGenerator.getPage("product_add.html");

        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Product product = getProductFromRequest(req);
            productService.add(product);
            resp.sendRedirect("/products");
        } catch (Exception e) {
            String errorMessage = "Product has not been added! Please, enter correct data in the fields and try again";
            System.out.println(errorMessage);
            e.printStackTrace();
        }

    }

    private Product getProductFromRequest(HttpServletRequest req) {
        System.out.println(req.getParameter("name"));
        System.out.println(Double.parseDouble(req.getParameter("price")));
        System.out.println(req.getParameter("notes"));

        return Product.builder()
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .notes(req.getParameter("notes"))
                .build();
    }
}
