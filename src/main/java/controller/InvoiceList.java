package controller;

import model.Invoice;
import services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/invoiceList")
public class InvoiceList extends HttpServlet {
    private static final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Invoice> invoiceList = invoiceService.finAll();
        req.setAttribute("invoiceList", invoiceList);

        req.getRequestDispatcher("/invoice-list.jsp").forward(req, resp);
    }
}
