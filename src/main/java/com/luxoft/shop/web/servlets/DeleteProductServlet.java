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

public class DeleteProductServlet extends HttpServlet {
    private ProductService productService;

    public DeleteProductServlet(ProductService productService) {
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        int id = Integer.parseInt(req.getParameter("id"));
        System.out.println("Delete product id is " + id);

        Product product = productService.findById(id);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product", product);
        String page = pageGenerator.getPage("product_delete.html", parameters);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
     //   System.out.println("Delete product is " + id);
        productService.delete(id);
        resp.sendRedirect("/products");
    }

}
