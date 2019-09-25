package controller;

import services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/invoiceDelete")
public class InvoiceDeleteServlet extends HttpServlet {
    InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long invoiceIdToDelete = Long.valueOf(req.getParameter("invoiceId"));
        invoiceService.removeInvoiceById(invoiceIdToDelete);
        resp.sendRedirect("/invoiceList");
    }
}
