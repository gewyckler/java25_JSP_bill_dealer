package services;

import database.EntityDao;
import model.Invoice;

import java.util.List;

public class InvoiceService {
    public InvoiceService() {
    }

    EntityDao entityDao = new EntityDao();

    public List<Invoice> finAll() {
        return entityDao.getAll(Invoice.class);
    }

    public void addInvoice(Invoice invoice) {
        entityDao.saveOrUpdate(invoice);
    }
}
