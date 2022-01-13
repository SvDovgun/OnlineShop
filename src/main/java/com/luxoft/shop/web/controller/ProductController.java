package com.luxoft.shop.web.controller;

import com.luxoft.shop.entity.Product;
import com.luxoft.shop.service.ProductService;
import com.luxoft.shop.web.util.PageGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller // create bean with name productController
public class ProductController {

    @Autowired
    private ProductService productService;


    @RequestMapping(path = "/products", method = RequestMethod.GET)
    protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in PRODuct controller");
        List<Product> products = productService.findAll();

        PageGenerator pageGenerator = PageGenerator.instance();

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("products", products);

        String page = pageGenerator.getPage("products_list.html", parameters);

        resp.getWriter().write(page);
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
