package controller;

import model.Invoice;
import services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/invoiceMarkAsReleased")
public class InvoiceMarkAsReleasedServlet extends HttpServlet {
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long invoiceId = Long.valueOf(req.getParameter("invoiceId"));
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceId);
        if (optionalInvoice.isPresent()) {
            invoiceService.markAsReleased(optionalInvoice.get());
            resp.sendRedirect("/invoiceList");
        }
    }
}
