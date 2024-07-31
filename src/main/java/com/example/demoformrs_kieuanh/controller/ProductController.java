package com.example.demoformrs_kieuanh.controller;

import com.example.demoformrs_kieuanh.model.Product;
import com.example.demoformrs_kieuanh.service.IProductService;
import com.example.demoformrs_kieuanh.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productController",value = "/products")
public class ProductController extends HttpServlet {
    private static IProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hien thi");


        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                showFormAdd(req, resp);
                break;
            case "edit" :
                showFormEdit(req,resp);
                break;
            case "delete" :
                showFormDelete(req,resp);
                break;
            default:
                List<Product> products = productService.findAll();
                System.out.println(products);
                req.setAttribute("products", products);
                RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
                dispatcher.forward(req, resp);
        }
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product1 = productService.findById(id);
        req.setAttribute("product", product1);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/delete.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        action = action == null ? "" : action;
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req,resp);
                break;
            case "delete":
                deleteProduct(req,resp);
                break;
            default:
                break;
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        productService.deleteProduct(id);
        try {
            resp.sendRedirect(req.getContextPath()+"/products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product =new Product(id,name,price);
            productService.updateProduct(id, product);


        try {
            resp.sendRedirect("/products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        Product product = new Product(id,name,price);
        productService.addProduct(product);
        resp.sendRedirect("/products");
    }

}
