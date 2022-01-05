package com.luxoft.shop.web.servlets;

import com.luxoft.shop.entity.Product;
import com.luxoft.shop.service.ProductService;
import com.luxoft.shop.web.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

public class EditProductServlet extends HttpServlet {
    private ProductService productService;

    public EditProductServlet(ProductService productService) {
        this.productService = productService;
    }

    public EditProductServlet() {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        int id = Integer.parseInt(req.getParameter("id"));
        //System.out.println("Edit product is " + id);

        Product product = productService.findById(id);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("product", product);
        String page = pageGenerator.getPage("product_edit.html", parameters);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        //System.out.println("Edit product is " + id);
        Product productToEdit = getProductFromRequest(req);
        productToEdit.setId(Integer.parseInt(req.getParameter("id")));
        productToEdit.setName(req.getParameter("name"));
        productToEdit.setPrice(Double.parseDouble(req.getParameter("price")));
        productToEdit.setNotes(req.getParameter("notes"));

        productService.edit(productToEdit);
        resp.sendRedirect("/products");
    }

    private Product getProductFromRequest(HttpServletRequest req) {
        //System.out.println(req.getParameter("name"));
        //System.out.println(Double.parseDouble(req.getParameter("price")));
        //System.out.println(req.getParameter("notes"));

        return Product.builder()
                .name(req.getParameter("name"))
                .price(Double.parseDouble(req.getParameter("price")))
                .notes(req.getParameter("notes"))
                .build();
    }
}
