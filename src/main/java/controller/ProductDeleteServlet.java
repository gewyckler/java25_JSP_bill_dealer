package controller;

import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long invoiceIdToRemoveProduct = Long.valueOf(req.getParameter("invoiceId"));
        productService.removeProductById(invoiceIdToRemoveProduct);
        resp.sendRedirect("/product-list.jsp");
    }
}
