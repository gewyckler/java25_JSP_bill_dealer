package controller;

import model.Invoice;
import services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet("/invoiceEdit")
public class InvoiceEditServlet extends HttpServlet {
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long invoiceId = Long.valueOf(req.getParameter("invoiceId"));
        Optional<Invoice> optionalInvoice = invoiceService.getInvoiceById(invoiceId);
        if (optionalInvoice.isPresent()) {
            Invoice invoiceToEdit = optionalInvoice.get();
            req.setAttribute("invoiceId", invoiceToEdit.getId());
            req.setAttribute("clientName", invoiceToEdit.getClientName());
            req.setAttribute("clientNip", invoiceToEdit.getClientNip());
            req.setAttribute("clientAddress", invoiceToEdit.getClientAddress());
            req.setAttribute("dateOfCreation", invoiceToEdit.getDateOfCreation());
            req.getRequestDispatcher("/invoice-add.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/invoiceList");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long invoiceId = Long.valueOf(req.getParameter("invoiceId"));

        String clientName = req.getParameter("clientName");
        String clientNip = req.getParameter("clientNip");
        String clientAddress = req.getParameter("clientAddress");
        LocalDateTime dateOfCreation = LocalDateTime.parse(req.getParameter("dateOfCreation"));

        Invoice invoice = new Invoice(clientName, clientNip, clientAddress);
        invoice.setId(invoiceId);
        invoice.setDateOfCreation(dateOfCreation);

        invoiceService.update(invoice);
        resp.sendRedirect("/invoiceList");
    }
}
