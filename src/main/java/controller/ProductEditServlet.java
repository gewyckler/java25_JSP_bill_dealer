package controller;

import model.Invoice;
import model.Product;
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

@WebServlet("/productEdit")
public class ProductEditServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long productId = Long.valueOf(req.getParameter("productId"));

        Long invoiceId = productService.getInvoiceIdFromProduct(productId);

        Optional<Product> optionalProduct = productService.getProductById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            req.setAttribute("invoiceId", invoiceId);
            req.setAttribute("productId", product.getId());
            req.setAttribute("productName", product.getName());
            req.setAttribute("productPrice", product.getPrice());
            req.setAttribute("productStock", product.getStock());
            req.setAttribute("productTaxType", product.getTaxType());
            req.getRequestDispatcher("/product-add.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("productList?invoiceId=" + invoiceId);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Long invoiceId = Long.valueOf(req.getParameter("invoiceIdToAddProduct"));
        Long productId = Long.valueOf(req.getParameter("productIdToEdit"));

        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceId);

        String name = req.getParameter("productName");
        double price = Double.parseDouble(req.getParameter("productPrice"));
        int stock = Integer.parseInt(req.getParameter("productStock"));
        TaxType taxType = TaxType.valueOf(req.getParameter("taxType"));

        Product product = new Product(name, price, taxType, stock);

        product.setId(productId);
        product.setInvoice(optionalInvoice.get());
        productService.updateProduct(product);

        resp.sendRedirect("/productList?invoiceId=" + invoiceId);
    }
}
