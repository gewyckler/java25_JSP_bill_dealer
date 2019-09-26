package controller;

import model.Invoice;
import model.TaxType;
import services.InvoiceService;
import services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/productAdd")
public class ProductAddServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long invoiceIdToAdd = (Long) req.getAttribute("invoiceId");
        req.setAttribute("invoiceIdAttribiute", invoiceIdToAdd);
        req.getRequestDispatcher("/product-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long invoiceIdToAddProduct = (Long) req.getAttribute("invoiceIdToAddProduct");
        req.setAttribute("invoiceId", invoiceIdToAddProduct);

        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceIdToAddProduct);

        if (optionalInvoice.isPresent()) {
            if (optionalInvoice.get().getDateOfRelease() == null || optionalInvoice.get().isIfPaid() == false) {
                Invoice invoice = optionalInvoice.get();

                String name = req.getParameter("productName");
                double price = Double.parseDouble(req.getParameter("productPrice"));
                int stock = Integer.parseInt(req.getParameter("productStock"));

                TaxType taxType = TaxType.valueOf(req.getParameter("taxType"));

                productService.addProduct(invoice, name, price, taxType, stock);
                resp.setCharacterEncoding("UTF-8");
                resp.sendRedirect("/productList");
            } else {
                resp.sendRedirect("/productList");
            }

        }
    }
}

