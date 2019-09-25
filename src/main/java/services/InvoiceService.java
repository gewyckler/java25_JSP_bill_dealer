package services;

import database.EntityDao;
import model.Invoice;

import java.util.List;
import java.util.Optional;

public class InvoiceService {
    public InvoiceService() {
    }

    private EntityDao entityDao = new EntityDao();

    public List<Invoice> findAll() {
        return entityDao.getAll(Invoice.class);
    }

    public void addInvoice(String clientName, String clientNip, String clientAddress) {
        Invoice invoice = new Invoice(clientName, clientNip, clientAddress);
        entityDao.saveOrUpdate(invoice);
    }

    public Optional<Invoice> getInvoiceById(Long invoiceId) {
        return entityDao.getById(Invoice.class, invoiceId);
    }

    public void removeInvoiceById(Long invoiceIdToDelete) {
        entityDao.delete(Invoice.class, invoiceIdToDelete);
    }
}
