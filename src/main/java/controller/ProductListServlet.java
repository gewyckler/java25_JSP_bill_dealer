package controller;

import model.Invoice;
import model.Product;
import services.InvoiceService;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        List<Product> productList = productService.findAll();
        Long invoiceId = Long.valueOf(req.getParameter("invoiceId"));
        req.setAttribute("productList", productList);
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceId);
        if (optionalInvoice.isPresent()) {
            req.setAttribute("invoiceObj", optionalInvoice.get());
        }

        req.getRequestDispatcher("/product-list.jsp").forward(req, resp);
    }
}
