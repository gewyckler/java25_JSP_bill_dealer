package controller;

import services.InvoiceService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/invoiceAdd")
public class InvoiceAddServlet extends HttpServlet {
    private final InvoiceService invoiceService = new InvoiceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/invoice-add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String clientName = req.getParameter("clientName");
        String clientNip = req.getParameter("clientNip");
        String clientAddress = req.getParameter("clientAddress");
//        String paid = req.getParameter("ifPaid");
//        boolean ifPaid = (paid != null && paid.equalsIgnoreCase("on"));

        invoiceService.addInvoice(clientName, clientNip, clientAddress);
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/invoiceList");
    }
}
