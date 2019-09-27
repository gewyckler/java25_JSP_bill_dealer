package controller;

import services.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        Long productIdToRemove = Long.valueOf(req.getParameter("productId"));
        Long invoiceId = productService.getInvoiceIdFromProduct(productIdToRemove);
        productService.removeProductById(productIdToRemove);
        resp.sendRedirect("/productList?invoiceId=" + invoiceId);
    }
}
